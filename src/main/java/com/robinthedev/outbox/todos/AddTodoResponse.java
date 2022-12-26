package com.robinthedev.outbox.todos;

import com.robinthedev.outbox.todos.domain.Todo;
import com.robinthedev.outbox.todos.domain.TodoError;

public interface AddTodoResponse {
    void createdTodo(Todo todo);

    void failedToCreateTodo(TodoError error);
}
