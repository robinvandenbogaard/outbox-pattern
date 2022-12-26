package com.robinthedev.outbox.rest;

import com.robinthedev.outbox.todos.domain.Todo;
import com.robinthedev.outbox.todos.domain.TodoError;
import java.util.List;
import javax.ws.rs.core.Response;

public class RListTodosResponse {
    private Response response;

    public void foundTodos(List<Todo> todos) {
        response = Response.ok(toRestEntity(todos)).build();
    }

    private List<RTodo> toRestEntity(List<Todo> todos) {
        return todos.stream().map(RTodo::new).toList();
    }

    public void failedToListTodos(TodoError error) {
        response = Response.serverError().entity(error.message()).build();
    }

    public Response getResponse() {
        return response;
    }
}
