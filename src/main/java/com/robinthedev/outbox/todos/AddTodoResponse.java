package com.robinthedev.outbox.todos;

import com.robinthedev.outbox.todos.domain.SaveError;
import com.robinthedev.outbox.todos.domain.Todo;

public interface AddTodoResponse {
    void createdTodo(Todo todo);

    void failedToCreateTodo(SaveError error);
}
