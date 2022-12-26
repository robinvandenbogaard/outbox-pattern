package com.robinthedev.outbox.panache;

import com.robinthedev.outbox.todos.domain.Todo;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class TodoEntity extends PanacheEntity {

    @Column(nullable = false)
    public LocalDateTime createdAt;

    @Column(nullable = false, length = 180)
    public String summary;

    public LocalDateTime completedAt;

    public TodoEntity() {

    }

    public TodoEntity(Todo todo) {
        this.createdAt = todo.createdAt();
        this.summary = todo.summary().summary();
        this.completedAt = todo.completedAt();
    }
}
