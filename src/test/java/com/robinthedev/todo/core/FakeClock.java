package com.robinthedev.todo.core;

import java.time.LocalDateTime;
import java.time.Month;

public class FakeClock implements Clock {

    static LocalDateTime DEFAULT_TIME = LocalDateTime.of(2022, Month.JANUARY, 1, 13, 30);
    private LocalDateTime time;

    public void setDateTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public LocalDateTime now() {
        return time != null ? time : DEFAULT_TIME;
    }
}
