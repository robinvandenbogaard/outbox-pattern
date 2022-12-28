package com.robinthedev.todo.domain;

public record TodoError(String message, Exception cause) {}
