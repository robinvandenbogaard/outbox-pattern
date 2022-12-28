package com.robinthedev.todo;

import com.robinthedev.todo.events.NewTodoCreatedEvent;

public interface OutboxEventRepository {
    void save(NewTodoCreatedEvent newTodoCreatedEvent);
}
