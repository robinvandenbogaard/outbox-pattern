package com.robinthedev.todo.core;

import com.robinthedev.todo.core.events.NewTodoCreatedEvent;

public interface OutboxRepository {
    void insert(NewTodoCreatedEvent newTodoCreatedEvent);
}
