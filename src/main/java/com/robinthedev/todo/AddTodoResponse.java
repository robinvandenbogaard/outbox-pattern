package com.robinthedev.todo;

import com.robinthedev.todo.domain.Todo;
import com.robinthedev.todo.domain.TodoError;

public interface AddTodoResponse {
    void createdTodo(Todo todo);

    void failedToCreateTodo(TodoError error);
}
