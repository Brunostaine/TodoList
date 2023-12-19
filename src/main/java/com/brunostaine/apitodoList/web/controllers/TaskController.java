package com.brunostaine.apitodoList.web.controllers;

import com.brunostaine.apitodoList.entities.Task;
import com.brunostaine.apitodoList.services.TaskService;
import com.brunostaine.apitodoList.web.exceptions.ErrorMessage;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            @ApiResponse(responseCode = "422", description = "Recurso não processado por dados de entrada inválidos", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorMessage.class)))
    })
    @PostMapping("/task")
    public ResponseEntity<Task> create(@RequestBody @Valid Task task) {
        Task newTask = taskService.salvar(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
    }

    @Operation(summary = "Listar todas as tarefas", description = "Recurso que lista todas as tarefas", responses = {
            @ApiResponse(responseCode = "200", description = "Recurso recuperado com sucesso",
                    content = @Content(mediaType = "application/json;charset=UTF-8",
                            schema = @Schema(implementation = Task.class)))
    })
    @GetMapping("/task")
    public ResponseEntity<List<Task>> getAll() {
        List<Task> tasks = taskService.buscarTodos();
        return ResponseEntity.ok(tasks);
    }

    @Operation(summary = "Listar tarefa pelo Id", description = "Recurso que lista tarefa pelo Id", responses = {
            @ApiResponse(responseCode = "200", description = "Recurso recuperado com sucesso",
                    content = @Content(mediaType = "application/json;charset=UTF-8",
                            schema = @Schema(implementation = Task.class))),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorMessage.class))),
    })
    @GetMapping("/task/{id}")
    public ResponseEntity<Task> getById(@PathVariable Long id) {
        Task task = taskService.buscarPorId(id);
        return ResponseEntity.ok(task);
    }
}
