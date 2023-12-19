package com.brunostaine.apitodoList.repositories;

import com.brunostaine.apitodoList.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
