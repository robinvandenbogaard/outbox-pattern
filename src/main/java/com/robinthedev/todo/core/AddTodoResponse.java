package com.robinthedev.todo.core;

import com.robinthedev.todo.core.domain.Todo;
import com.robinthedev.todo.core.domain.TodoError;

public interface AddTodoResponse {
    void createdTodo(Todo todo);

    void failedToCreateTodo(TodoError error);
}
