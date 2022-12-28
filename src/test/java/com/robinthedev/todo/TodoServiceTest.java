package com.robinthedev.todo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.robinthedev.todo.domain.ExternalId;
import com.robinthedev.todo.domain.Summary;
import com.robinthedev.todo.events.NewTodoCreatedEvent;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TodoServiceTest {

    private TodoService service;
    private FakeClock fakeClock;
    private FakeIdGenerator fakeIdGenerator;
    private FakeTodoRepository fakeRepository;
    private FakeOutboxEventRepository fakeOutboxRepository;

    @BeforeEach
    void beforeEach() {
        service = new TodoService();
        service.clock = fakeClock = new FakeClock();
        service.repository = fakeRepository = new FakeTodoRepository();
        service.externalIdProvider = fakeIdGenerator = new FakeIdGenerator();
        service.outbox = fakeOutboxRepository = new FakeOutboxEventRepository();
    }

    @Test
    void createsTodoWithProvidedSummary() {
        var response = new TestAddTodoResponse();

        service.addTodo(new AddTodoRequest(new Summary("Laundry")), response);
        assertThat(response.getCreatedTodo().summary(), is(new Summary("Laundry")));

        service.addTodo(new AddTodoRequest(new Summary("Laundry 2")), response);
        assertThat(response.getCreatedTodo().summary(), is(new Summary("Laundry 2")));
    }

    @Test
    void createsAtTheTimeDeterminedByTheClock() {
        var response = new TestAddTodoResponse();

        // default time
        service.addTodo(new AddTodoRequest(new Summary("Laundry")), response);
        assertThat(response.getCreatedTodo().createdAt(), is(FakeClock.DEFAULT_TIME));

        // alternative time
        fakeClock.setDateTime(LocalDateTime.of(2022, Month.FEBRUARY, 1, 6, 0));
        service.addTodo(new AddTodoRequest(new Summary("Laundry")), response);
        assertThat(
                response.getCreatedTodo().createdAt(),
                is(LocalDateTime.of(2022, Month.FEBRUARY, 1, 6, 0)));
    }

    @Test
    void newlyCreatedTodoIsNotCompleted() {
        var response = new TestAddTodoResponse();

        service.addTodo(new AddTodoRequest(new Summary("Laundry")), response);
        assertThat(response.getCreatedTodo().isCompleted(), is(false));
    }

    @Test
    void storesTheTodoInTheRepository() {
        var response = new TestAddTodoResponse();
        service.addTodo(new AddTodoRequest(new Summary("Laundry")), response);
        assertThat(fakeRepository.getSavedTodo(), is(response.getCreatedTodo()));
    }

    @Test
    void respondsWithErrorIfCannotSave() {
        fakeRepository.failOnSave(new IOException("Not enough space"));

        var response = new TestAddTodoResponse();
        service.addTodo(new AddTodoRequest(new Summary("Laundry")), response);
        assertThat(response.getError().message(), is("Not enough space"));
    }

    @Test
    void createsExternalId() {
        var response = new TestAddTodoResponse();

        // default time
        service.addTodo(new AddTodoRequest(new Summary("Laundry")), response);
        assertThat(response.getCreatedTodo().externalId(), is(FakeIdGenerator.DEFAULT_ID));
        assertThat(response.getCreatedTodo().externalId(), is(FakeIdGenerator.DEFAULT_ID));

        // alternative time
        fakeIdGenerator.setNext(
                new ExternalId(UUID.fromString("8114ffb5-8a63-43a9-8d6d-cb45a4d92d63")));
        service.addTodo(new AddTodoRequest(new Summary("Laundry")), response);
        assertThat(
                response.getCreatedTodo().externalId().uuid(),
                is(UUID.fromString("8114ffb5-8a63-43a9-8d6d-cb45a4d92d63")));
    }

    @Test
    void addEventToOutbox() {
        var response = new TestAddTodoResponse();

        service.addTodo(new AddTodoRequest(new Summary("Laundry")), response);

        assertThat(
                fakeOutboxRepository.getInsertedEvent(),
                is(new NewTodoCreatedEvent(response.getCreatedTodo())));
    }
}
