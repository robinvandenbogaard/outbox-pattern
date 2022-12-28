package com.robinthedev.todo.core.events;

import com.robinthedev.todo.core.domain.Todo;

public record NewTodoCreatedEvent(Todo todo, EventType type) {

    public NewTodoCreatedEvent(Todo todo) {
        this(todo, EventType.todoCreated);
    }
}
