package com.robinthedev.time;

import com.robinthedev.todo.core.Clock;
import java.time.LocalDateTime;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
class LocalSystemClock implements Clock {
    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
