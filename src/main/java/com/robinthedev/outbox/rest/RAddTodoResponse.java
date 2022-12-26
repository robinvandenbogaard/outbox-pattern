package com.robinthedev.outbox.rest;

import com.robinthedev.outbox.todos.AddTodoResponse;
import com.robinthedev.outbox.todos.domain.SaveError;
import com.robinthedev.outbox.todos.domain.Todo;

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
    public void failedToCreateTodo(SaveError error) {
        response = Response.serverError().entity(new RError(error)).build();
    }
}
