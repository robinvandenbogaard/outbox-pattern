package com.robinthedev.todo.panache;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinthedev.todo.core.Clock;
import com.robinthedev.todo.core.ExternalIdProvider;
import com.robinthedev.todo.core.OutboxRepository;
import com.robinthedev.todo.core.events.NewTodoCreatedEvent;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
class PanacheOutboxRepository implements OutboxRepository {

    @Inject ObjectMapper objectMapper;

    @Inject Clock clock;

    @Inject ExternalIdProvider externalIdProvider;

    @Override
    public void insert(NewTodoCreatedEvent newTodoCreatedEvent) {
        var todo = newTodoCreatedEvent.todo();
        OutboxEventEntity event = new OutboxEventEntity();
        event.uuid = externalIdProvider.next().uuid();
        event.aggregatedId = todo.externalId().uuid();
        event.type = newTodoCreatedEvent.type().name();
        event.payload = toPayload(newTodoCreatedEvent);
        event.created = clock.now();
        event.persist();
    }

    private String toPayload(NewTodoCreatedEvent newTodoCreatedEvent) {
        return objectMapper.convertValue(newTodoCreatedEvent, JsonNode.class).toString();
    }
}
