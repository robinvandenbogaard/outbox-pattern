package com.robinthedev.adapters.rest;

import com.robinthedev.todo.TodoService;
import javax.inject.Inject;
import javax.ws.rs.*;
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

    @GET
    public Response listAll() {
        var response = new RListTodosResponse();

        service.listAll(response);

        return response.getResponse();
    }
}
