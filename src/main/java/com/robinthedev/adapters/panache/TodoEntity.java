package com.robinthedev.adapters.panache;

import com.robinthedev.todo.domain.Todo;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "todos")
class TodoEntity extends PanacheEntity {

    @Column(nullable = false)
    public LocalDateTime createdAt;

    @Column(nullable = false, length = 180)
    public String summary;

    public LocalDateTime completedAt;

    @Column(nullable = false)
    public UUID externalId;

    public TodoEntity() {}

    public TodoEntity(Todo todo) {
        this.externalId = todo.externalId().uuid();
        this.createdAt = todo.createdAt();
        this.summary = todo.summary().summary();
        this.completedAt = todo.completedAt();
    }
}
