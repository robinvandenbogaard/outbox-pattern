package com.robinthedev.outbox;

import com.robinthedev.outbox.domain.OutboxItem;
import io.quarkus.scheduler.Scheduled;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
class OutboxRelayJob {

    private static final Logger log = LoggerFactory.getLogger(OutboxRelayJob.class);

    @Inject OutboxItemRepository outbox;

    @Inject MessageBroker messageBroker;

    @Scheduled(every = "1s")
    @Transactional
    void publishEvents() {
        var events = outbox.getEvents();
        log.debug("Found {} events to process", events.size());
        events.forEach(this::deleteAndPublishEvent);
    }

    private void deleteAndPublishEvent(OutboxItem outboxItem) {
        outbox.delete(outboxItem.id());

        if (!messageBroker.publish(outboxItem)) {
            outbox.save(outboxItem);
        }
    }
}
