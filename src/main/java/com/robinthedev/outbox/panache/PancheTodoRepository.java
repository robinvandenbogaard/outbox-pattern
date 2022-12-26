package com.robinthedev.outbox.panache;

import com.robinthedev.outbox.todos.TodoRepository;
import com.robinthedev.outbox.todos.domain.ExternalId;
import com.robinthedev.outbox.todos.domain.Summary;
import com.robinthedev.outbox.todos.domain.Todo;
import com.robinthedev.outbox.todos.domain.TodoError;
import io.quarkus.panache.common.exception.PanacheQueryException;
import io.vavr.control.Either;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PancheTodoRepository implements TodoRepository {

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

    private Todo toTodo(TodoEntity todoEntity) {
        return new Todo(
                new ExternalId(todoEntity.externalId),
                todoEntity.createdAt,
                new Summary(todoEntity.summary),
                todoEntity.completedAt);
    }
}
