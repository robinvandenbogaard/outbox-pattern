package com.robinthedev.outbox.todos;

import com.robinthedev.outbox.todos.domain.Todo;
import com.robinthedev.outbox.todos.domain.TodoError;
import io.vavr.control.Either;
import java.util.List;

public interface TodoRepository {
    Either<TodoError, Todo> save(Todo todo);

    Either<TodoError, List<Todo>> findAll();
}
