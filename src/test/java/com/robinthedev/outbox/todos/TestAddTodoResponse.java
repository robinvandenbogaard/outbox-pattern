package com.robinthedev.outbox.todos;

import com.robinthedev.outbox.todos.domain.SaveError;
import com.robinthedev.outbox.todos.domain.Todo;

public class TestAddTodoResponse implements AddTodoResponse {
    private Todo todo;
    private SaveError error;

    public Todo getCreatedTodo() {
        return todo;
    }

    @Override
    public void createdTodo(Todo todo) {
        this.todo = todo;
    }

    @Override
    public void failedToCreateTodo(SaveError error) {
        this.error = error;
    }

    public SaveError getError() {
        return error;
    }
}
