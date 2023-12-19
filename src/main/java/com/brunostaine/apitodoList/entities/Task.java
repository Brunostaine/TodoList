package com.brunostaine.apitodoList.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "titulo", nullable = false, length = 30)
    @Size(min = 5, max = 30)
    @NotBlank
    private String title;
    @Column(name = "descricao", nullable = false, length = 300)
    @Size(min = 5, max = 300)
    @NotBlank
    private String description;
    @Column(name = "status", nullable = false, length = 25)
    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDENTE;

    public enum Status {
        PENDENTE, CONCLUIDA
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
