package com.robinthedev.outbox.todos;

import com.robinthedev.outbox.todos.domain.Todo;
import com.robinthedev.outbox.todos.domain.TodoError;
import io.vavr.control.Either;
import java.io.IOException;
import java.util.List;
import org.apache.commons.lang3.NotImplementedException;

public class FakeTodoRepository implements TodoRepository {
    private Todo savedTodo;
    private boolean failOnSave = false;
    private Exception cause;

    public Todo getSavedTodo() {
        return savedTodo;
    }

    @Override
    public Either<TodoError, Todo> save(Todo todo) {
        this.savedTodo = todo;
        if (failOnSave) return Either.left(new TodoError(cause.getMessage(), cause));
        return Either.right(todo);
    }

    @Override
    public Either<TodoError, List<Todo>> findAll() {
        return Either.left(new TodoError("not implemented", new NotImplementedException()));
    }

    public void failOnSave(IOException cause) {
        this.failOnSave = true;
        this.cause = cause;
    }
}
