package com.brunostaine.apitodoList.services;

import com.brunostaine.apitodoList.entities.Task;
import com.brunostaine.apitodoList.exceptions.EntityNotFoundException;
import com.brunostaine.apitodoList.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Slf4j
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
    @Transactional
    public Task deletarPorId(Long id) {
        Task task = buscarPorId(id);
        if (task.getId() == null) {
            log.info("Id não encontrado: " + id);
            throw new EntityNotFoundException(String.format("Usuário id=%s não encontrado", id));
        }
        taskRepository.deleteById(id);
        return task;
    }
    @Transactional
    public Task editarTarefa(Long id, Task task) {
        Task TaskExists = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada com o ID: " + id));

        TaskExists.setTitle(task.getTitle());
        TaskExists.setDescription(task.getDescription());
        TaskExists.setStatus(task.getStatus());

        return taskRepository.save(TaskExists);
    }
}
