package com.brunostaine.apitodoList.services;

import com.brunostaine.apitodoList.entities.Task;
import com.brunostaine.apitodoList.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public Task salvar(Task task) {
        return taskRepository.save(task);
    }
}
