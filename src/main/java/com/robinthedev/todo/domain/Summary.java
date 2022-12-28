package com.robinthedev.todo.domain;

public record Summary(String summary) {
    public Summary(String summary) {
        this.summary = summary;
        if (summary.length() > 180)
            throw new IllegalStateException("Summary must be max 180 characters");
    }
}
