package com.brunostaine.apitodoList.services;

import com.brunostaine.apitodoList.entities.Task;
import com.brunostaine.apitodoList.exceptions.EntityNotFoundException;
import com.brunostaine.apitodoList.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Transactional
    public Task salvar(Task task) {
        return taskRepository.save(task);
    }

    @Transactional(readOnly = true)
    public List<Task> buscarTodos() {
        return taskRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Task buscarPorId(Long id) {
        return taskRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Usuário id=%s não encontrado", id))
        );
    }
}
