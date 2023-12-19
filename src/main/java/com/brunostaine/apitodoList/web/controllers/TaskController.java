package com.brunostaine.apitodoList.web.controllers;

import com.brunostaine.apitodoList.entities.Task;
import com.brunostaine.apitodoList.services.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class TaskController {
    private final TaskService taskService;
    @PostMapping("task")
    public ResponseEntity<Task> create(@RequestBody @Valid Task task){
        Task newTask = taskService.salvar(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
    }
}
