package com.test.webtalk.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.handler.sockjs.SockJSSocket;

import java.util.ArrayList;
import java.util.List;

public class ChatVerticle extends AbstractVerticle {

    private List<SockJSSocket> socks = new ArrayList<>();

    @Override
    public void start() throws Exception {

        vertx.eventBus().<SockJSSocket>consumer("addsocket")
                .handler(event -> socks.add(event.body()))
                .exceptionHandler(Throwable::printStackTrace);

        vertx.eventBus().<String>consumer("message")
                .handler(event -> sendMessage(event.body()))
                .exceptionHandler(Throwable::printStackTrace);
    }

    private void sendMessage(String s) {
        socks.forEach(sock -> sock.write(s));
    }
}
