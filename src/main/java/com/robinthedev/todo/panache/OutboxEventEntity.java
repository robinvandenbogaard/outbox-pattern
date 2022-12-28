package com.robinthedev.todo.panache;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "ouboxevents")
class OutboxEventEntity extends PanacheEntity {

    @Column(nullable = false)
    public UUID uuid;

    @Column(nullable = false)
    public UUID aggregatedId;

    @Column(nullable = false, length = 128)
    public String type;

    @Column(nullable = false, length = 2000)
    public String payload;

    @Column(nullable = false)
    public LocalDateTime created;
}
