package com.robinthedev.outbox.todos;

import com.robinthedev.outbox.rest.RListTodosResponse;
import com.robinthedev.outbox.todos.domain.Todo;
import com.robinthedev.time.Clock;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class TodoService {

    @Inject Clock clock;

    @Inject TodoRepository repository;

    @Transactional
    public void addTodo(AddTodoRequest request, AddTodoResponse response) {
        var todo = Todo.incomplete(clock.now(), request.summary());

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
