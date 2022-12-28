package com.robinthedev.todo;

import com.robinthedev.todo.events.NewTodoCreatedEvent;

public class FakeOutboxEventRepository implements OutboxEventRepository {

    private NewTodoCreatedEvent newTodoCreatedEvent;

    @Override
    public void save(NewTodoCreatedEvent newTodoCreatedEvent) {
        this.newTodoCreatedEvent = newTodoCreatedEvent;
    }

    public NewTodoCreatedEvent getInsertedEvent() {
        return newTodoCreatedEvent;
    }
}
