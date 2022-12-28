package com.robinthedev.todo.events;

import com.robinthedev.todo.domain.Todo;

public record NewTodoCreatedEvent(Todo todo, EventType type) {

    public NewTodoCreatedEvent(Todo todo) {
        this(todo, EventType.todoCreated);
    }
}
