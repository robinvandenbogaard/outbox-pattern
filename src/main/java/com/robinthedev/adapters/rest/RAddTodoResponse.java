package com.robinthedev.adapters.rest;

import com.robinthedev.todo.AddTodoResponse;
import com.robinthedev.todo.domain.Todo;
import com.robinthedev.todo.domain.TodoError;
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
