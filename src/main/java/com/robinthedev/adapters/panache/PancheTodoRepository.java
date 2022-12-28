package com.robinthedev.adapters.panache;

import com.robinthedev.todo.TodoRepository;
import com.robinthedev.todo.domain.ExternalId;
import com.robinthedev.todo.domain.Summary;
import com.robinthedev.todo.domain.Todo;
import com.robinthedev.todo.domain.TodoError;
import io.quarkus.panache.common.exception.PanacheQueryException;
import io.vavr.control.Either;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
class PancheTodoRepository implements TodoRepository {

    @Override
    public Either<TodoError, Todo> save(Todo todo) {
        try {
            var entity = new TodoEntity(todo);
            entity.persist();
            return Either.right(todo);
        } catch (PanacheQueryException e) {
            return Either.left(new TodoError(e.getMessage(), e));
        }
    }

    @Override
    public Either<TodoError, List<Todo>> findAll() {
        try {
            List<TodoEntity> entities = TodoEntity.findAll().list();
            return Either.right(entities.stream().map(this::toTodo).toList());
        } catch (PanacheQueryException e) {
            return Either.left(new TodoError(e.getMessage(), e));
        }
    }

    private Todo toTodo(TodoEntity entity) {
        return new Todo(
                new ExternalId(entity.externalId),
                entity.createdAt,
                new Summary(entity.summary),
                entity.completedAt);
    }
}
