package com.robinthedev.todo;

import com.robinthedev.adapters.rest.RListTodosResponse;
import com.robinthedev.todo.domain.Todo;
import com.robinthedev.todo.events.NewTodoCreatedEvent;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class TodoService {

    @Inject ExternalIdProvider externalIdProvider;

    @Inject Clock clock;

    @Inject TodoRepository repository;

    @Inject OutboxEventRepository outbox;

    @Transactional
    public void addTodo(AddTodoRequest request, AddTodoResponse response) {
        var todo = Todo.incomplete(externalIdProvider.next(), clock.now(), request.summary());

        var result = repository.save(todo);
        if (result.isRight()) {
            outbox.save(new NewTodoCreatedEvent(todo));
            response.createdTodo(todo);
        } else {
            response.failedToCreateTodo(result.getLeft());
        }
    }

    public void listAll(RListTodosResponse response) {
        var result = repository.findAll();
        if (result.isRight()) {
            response.foundTodos(result.get());
        } else {
            response.failedToListTodos(result.getLeft());
        }
    }
}
