package com.refunddag;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;

import java.util.Map;

public final class App {

    public static final int DEFAULT_PORT = 7070;

    private App() {
    }

    public static Javalin create() {
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return Javalin.create(config -> config.jsonMapper(new JavalinJackson(mapper, false)))
                .get("/health", ctx -> ctx.json(Map.of("status", "ok")));
    }

    public static void main(String[] args) {
        create().start(DEFAULT_PORT);
    }
}
