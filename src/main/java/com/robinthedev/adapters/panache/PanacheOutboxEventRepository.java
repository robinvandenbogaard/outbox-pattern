package com.robinthedev.adapters.panache;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinthedev.todo.Clock;
import com.robinthedev.todo.ExternalIdProvider;
import com.robinthedev.todo.OutboxEventRepository;
import com.robinthedev.todo.events.NewTodoCreatedEvent;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
class PanacheOutboxEventRepository implements OutboxEventRepository {

    @Inject ObjectMapper objectMapper;

    @Inject Clock clock;

    @Inject ExternalIdProvider externalIdProvider;

    @Override
    public void save(NewTodoCreatedEvent newTodoCreatedEvent) {
        var todo = newTodoCreatedEvent.todo();
        OutboxItemEntity event = new OutboxItemEntity();
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
