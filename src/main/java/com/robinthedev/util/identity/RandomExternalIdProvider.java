package com.robinthedev.util.identity;

import com.robinthedev.todo.ExternalIdProvider;
import com.robinthedev.todo.domain.ExternalId;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
class RandomExternalIdProvider implements ExternalIdProvider {
    @Override
    public ExternalId next() {
        return new ExternalId(UUID.randomUUID());
    }
}
