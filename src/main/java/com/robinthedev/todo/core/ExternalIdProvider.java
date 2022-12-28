package com.robinthedev.todo.core;

import com.robinthedev.todo.core.domain.ExternalId;

public interface ExternalIdProvider {
    ExternalId next();
}
