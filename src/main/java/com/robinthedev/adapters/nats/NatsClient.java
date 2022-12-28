package com.robinthedev.adapters.nats;

import io.nats.client.Connection;
import io.nats.client.Nats;
import io.quarkus.runtime.StartupEvent;
import java.io.IOException;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class NatsClient {

    private Connection connection;

    void setup(@Observes StartupEvent ignored) throws NatsException {
        try {
            connection = Nats.connect();
        } catch (IOException | InterruptedException e) {
            throw new NatsException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
