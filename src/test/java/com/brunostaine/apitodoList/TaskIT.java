package com.brunostaine.apitodoList;

import com.brunostaine.apitodoList.entities.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.brunostaine.apitodoList.entities.Task.Status.PENDENTE;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/tasks/tasks-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/tasks/tasks-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class TaskIT {
    @Autowired
    WebTestClient testClient;

    private Task createTask(String title, String description, String status) {
        return new Task(null, title, description, Task.Status.valueOf(status.toUpperCase()));
    }

    @Test
    public void createTask_ComDadosValidos_RetornaTaskComStatus201() {
        Task taskToCreate = createTask("Nova Tarefa", "Descrição da nova tarefa", "PENDENTE");

        Task responseBody = testClient
                .post().uri("/api/v1/task")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(taskToCreate)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Task.class)
                .returnResult().getResponseBody();
        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getId()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getTitle()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getDescription()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(PENDENTE);
    }
}
