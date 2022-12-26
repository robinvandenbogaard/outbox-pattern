package com.robinthedev.outbox.rest;

import com.robinthedev.outbox.todos.TodoService;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/todo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TodoResource {

    @Inject TodoService service;

    @POST
    @Path("add")
    public Response createTodo(RAddTodoRequest request) {
        var response = new RAddTodoResponse();

        service.addTodo(request.toDomainRequest(), response);

        return response.getResponse();
    }
}
