package com.robinthedev.outbox.rest;

import com.robinthedev.outbox.todos.AddTodoResponse;
import com.robinthedev.outbox.todos.domain.Todo;
import com.robinthedev.outbox.todos.domain.TodoError;
import javax.ws.rs.core.Response;

public class RAddTodoResponse implements AddTodoResponse {
    private Response response;

    public Response getResponse() {
        return response;
    }

    @Override
    public void createdTodo(Todo todo) {
        response = Response.ok(new RTodo(todo)).build();
    }

    @Override
    public void failedToCreateTodo(TodoError error) {
        response = Response.serverError().entity(new RError(error)).build();
    }
}
