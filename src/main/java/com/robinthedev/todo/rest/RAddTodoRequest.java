package com.robinthedev.todo.rest;

import com.robinthedev.todo.core.AddTodoRequest;
import com.robinthedev.todo.core.domain.Summary;

record RAddTodoRequest(String summary) {
    public AddTodoRequest toDomainRequest() {
        return new AddTodoRequest(new Summary(summary));
    }
}
