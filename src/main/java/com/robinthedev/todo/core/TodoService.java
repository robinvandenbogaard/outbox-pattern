package com.robinthedev.todo.core;

import com.robinthedev.time.Clock;
import com.robinthedev.todo.core.domain.Todo;
import com.robinthedev.todo.rest.RListTodosResponse;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class TodoService {

    @Inject ExternalIdProvider externalIdProvider;

    @Inject Clock clock;

    @Inject TodoRepository repository;

    @Transactional
    public void addTodo(AddTodoRequest request, AddTodoResponse response) {
        var todo = Todo.incomplete(externalIdProvider.next(), clock.now(), request.summary());

        var result = repository.save(todo);
        if (result.isRight()) {
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
