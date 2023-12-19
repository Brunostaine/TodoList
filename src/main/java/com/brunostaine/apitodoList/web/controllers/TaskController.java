package com.brunostaine.apitodoList.web.controllers;

import com.brunostaine.apitodoList.entities.Task;
import com.brunostaine.apitodoList.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Tasks", description = "Contém todas as operações relativas aos recursos de tasks, edição e leitura de um usuário.")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class TaskController {
    private final TaskService taskService;

    @Operation(summary = "Criar uma nova tarefa", description = "Recurso para criar uma nova tarefa", responses = {
            @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso",
                    content = @Content(mediaType = "application/json;charset=UTF-8",
                            schema = @Schema(implementation = Task.class))),
    })
    @PostMapping("task")
    public ResponseEntity<Task> create(@RequestBody @Valid Task task) {
        Task newTask = taskService.salvar(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
    }
}
