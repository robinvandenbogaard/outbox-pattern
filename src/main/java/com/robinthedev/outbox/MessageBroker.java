package com.robinthedev.outbox;

import com.robinthedev.outbox.domain.OutboxItem;

public interface MessageBroker {
    boolean publish(OutboxItem outboxItem);
}
