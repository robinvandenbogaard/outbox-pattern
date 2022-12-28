package com.robinthedev.todo.core;

import com.robinthedev.todo.core.domain.Todo;
import com.robinthedev.todo.core.domain.TodoError;
import io.vavr.control.Either;
import java.util.List;

public interface TodoRepository {
    Either<TodoError, Todo> save(Todo todo);

    Either<TodoError, List<Todo>> findAll();
}
