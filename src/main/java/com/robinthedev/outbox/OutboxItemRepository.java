package com.robinthedev.outbox;

import com.robinthedev.outbox.domain.OutboxId;
import com.robinthedev.outbox.domain.OutboxItem;
import java.util.List;

public interface OutboxItemRepository {

    void save(OutboxItem outboxItem);

    void delete(OutboxId uuid);

    List<OutboxItem> getEvents();
}
