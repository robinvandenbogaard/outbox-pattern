package com.robinthedev.adapters.panache;

import com.robinthedev.outbox.OutboxItemRepository;
import com.robinthedev.outbox.domain.OutboxId;
import com.robinthedev.outbox.domain.OutboxItem;
import com.robinthedev.outbox.domain.Payload;
import com.robinthedev.todo.events.EventType;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
class PanacheOutboxItemRepository implements OutboxItemRepository {

    @Override
    public void save(OutboxItem outboxItem) {
        var entity = new OutboxItemEntity();
        entity.uuid = outboxItem.id().uuid();
        entity.persist();
    }

    @Override
    public void delete(OutboxId id) {
        OutboxItemEntity.delete("uuid", id.uuid());
    }

    @Override
    public List<OutboxItem> getEvents() {
        List<OutboxItemEntity> items = OutboxItemEntity.findAll().list();
        return items.stream().map(this::toOutboxItem).toList();
    }

    private OutboxItem toOutboxItem(OutboxItemEntity entity) {
        return new OutboxItem(
                new OutboxId(entity.uuid),
                EventType.valueOf(entity.type),
                new Payload(entity.payload));
    }
}
