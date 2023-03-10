package com.robinthedev.todo;

import com.robinthedev.todo.domain.Todo;
import com.robinthedev.todo.domain.TodoError;

public class TestAddTodoResponse implements AddTodoResponse {
    private Todo todo;
    private TodoError error;

    public Todo getCreatedTodo() {
        return todo;
    }

    @Override
    public void createdTodo(Todo todo) {
        this.todo = todo;
    }

    @Override
    public void failedToCreateTodo(TodoError error) {
        this.error = error;
    }

    public TodoError getError() {
        return error;
    }
}
