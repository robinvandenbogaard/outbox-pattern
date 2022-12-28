package com.robinthedev.adapters.rest;

import com.robinthedev.todo.AddTodoRequest;
import com.robinthedev.todo.domain.Summary;

record RAddTodoRequest(String summary) {
    public AddTodoRequest toDomainRequest() {
        return new AddTodoRequest(new Summary(summary));
    }
}
