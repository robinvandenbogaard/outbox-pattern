package com.robinthedev.todo.core;

import com.robinthedev.todo.core.events.NewTodoCreatedEvent;

public class FakeOutboxRepository implements OutboxRepository {

    private NewTodoCreatedEvent newTodoCreatedEvent;

    @Override
    public void insert(NewTodoCreatedEvent newTodoCreatedEvent) {
        this.newTodoCreatedEvent = newTodoCreatedEvent;
    }

    public NewTodoCreatedEvent getInsertedEvent() {
        return newTodoCreatedEvent;
    }
}
