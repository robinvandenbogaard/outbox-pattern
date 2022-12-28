package com.robinthedev.todo.core.domain;

public record TodoError(String message, Exception cause) {}
