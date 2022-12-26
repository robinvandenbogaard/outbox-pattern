package com.robinthedev.outbox.rest;

import com.robinthedev.outbox.todos.AddTodoRequest;
import com.robinthedev.outbox.todos.domain.Summary;

record RAddTodoRequest(String summary) {
    public AddTodoRequest toDomainRequest() {
        return new AddTodoRequest(new Summary(summary));
    }
}
