package com.robinthedev.outbox.panache;

import com.robinthedev.outbox.todos.TodoRepository;
import com.robinthedev.outbox.todos.domain.SaveError;
import com.robinthedev.outbox.todos.domain.Todo;
import io.quarkus.panache.common.exception.PanacheQueryException;
import io.vavr.control.Either;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PancheTodoRepository implements TodoRepository {

    @Override
    public Either<SaveError, Todo> save(Todo todo) {
        try {
            var entity = new TodoEntity(todo);
            entity.persist();
            return Either.right(todo);
        } catch (PanacheQueryException e) {
            return Either.left(new SaveError(e.getMessage(), e));
        }
    }
}
