package com.refunddag;

import io.javalin.Javalin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    private Javalin app;

    @BeforeEach
    void startApp() {
        // Port 0 picks a free port so the test doesn't collide with a running instance on 7070.
        app = App.create().start(0);
    }

    @AfterEach
    void stopApp() {
        app.stop();
    }

    @Test
    void healthReturnsOk() throws Exception {
        HttpResponse<String> response = HttpClient.newHttpClient().send(
                HttpRequest.newBuilder(URI.create("http://localhost:" + app.port() + "/health")).GET().build(),
                HttpResponse.BodyHandlers.ofString());

        assertEquals(200, response.statusCode());
        assertEquals("{\"status\":\"ok\"}", response.body());
    }
}
