package com.robinthedev.outbox.todos;

import com.robinthedev.outbox.todos.domain.SaveError;
import com.robinthedev.outbox.todos.domain.Todo;
import io.vavr.control.Either;

public interface TodoRepository {
    Either<SaveError, Todo> save(Todo todo);
}
