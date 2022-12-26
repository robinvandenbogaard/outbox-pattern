package com.robinthedev.outbox.todos;

import com.robinthedev.outbox.todos.domain.SaveError;
import com.robinthedev.outbox.todos.domain.Todo;
import io.vavr.control.Either;
import java.io.IOException;

public class FakeTodoRepository implements TodoRepository {
    private Todo savedTodo;
    private boolean failOnSave = false;
    private Exception cause;

    public Todo getSavedTodo() {
        return savedTodo;
    }

    @Override
    public Either<SaveError, Todo> save(Todo todo) {
        this.savedTodo = todo;
        if (failOnSave) return Either.left(new SaveError(cause.getMessage(), cause));
        return Either.right(todo);
    }

    public void failOnSave(IOException cause) {
        this.failOnSave = true;
        this.cause = cause;
    }
}
