package com.robinthedev.identity;

import com.robinthedev.todo.core.ExternalIdProvider;
import com.robinthedev.todo.core.domain.ExternalId;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
class RandomExternalIdProvider implements ExternalIdProvider {
    @Override
    public ExternalId next() {
        return new ExternalId(UUID.randomUUID());
    }
}
