package com.robinthedev.adapters.nats;

import com.robinthedev.outbox.MessageBroker;
import com.robinthedev.outbox.domain.OutboxItem;
import io.nats.client.Message;
import io.nats.client.impl.NatsMessage;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class NatsMessageBroker implements MessageBroker {

    private static Logger log = LoggerFactory.getLogger(NatsMessageBroker.class);

    @Inject NatsClient client;

    @Override
    public boolean publish(OutboxItem outboxItem) {
        var message = toMessage(outboxItem);

        log.debug(
                "Publishing outbox item {} to subject {}.",
                outboxItem.id(),
                outboxItem.getSubject());
        // TODO: use a stream, return false if unable to publish
        client.getConnection().publish(message);
        return true;
    }

    private Message toMessage(OutboxItem outboxItem) {
        return NatsMessage.builder()
                .subject(outboxItem.getSubject())
                .data(outboxItem.getPayload())
                .build();
    }
}
