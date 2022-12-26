package com.robinthedev.outbox.todos;

import com.robinthedev.outbox.todos.domain.Summary;

public record AddTodoRequest(Summary summary) {
}
