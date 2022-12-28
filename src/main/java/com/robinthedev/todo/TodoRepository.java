package com.robinthedev.todo;

import com.robinthedev.todo.domain.Todo;
import com.robinthedev.todo.domain.TodoError;
import io.vavr.control.Either;
import java.util.List;

public interface TodoRepository {
    Either<TodoError, Todo> save(Todo todo);

    Either<TodoError, List<Todo>> findAll();
}
