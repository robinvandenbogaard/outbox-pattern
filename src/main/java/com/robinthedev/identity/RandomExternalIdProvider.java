package com.robinthedev.identity;

import com.robinthedev.outbox.todos.ExternalIdProvider;
import com.robinthedev.outbox.todos.domain.ExternalId;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RandomExternalIdProvider implements ExternalIdProvider {
    @Override
    public ExternalId next() {
        return new ExternalId(UUID.randomUUID());
    }
}
