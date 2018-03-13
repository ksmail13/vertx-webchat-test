package com.test.webtalk.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.LoggerHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;

import java.nio.charset.Charset;

public class HttpServerVerticle extends AbstractVerticle {



    @Override
    public void start() throws Exception {
        HttpServer httpServer = vertx.createHttpServer();

        Router router = Router.router(vertx);

        SockJSHandler sockJSHandler = SockJSHandler.create(vertx)
                .socketHandler(sockJsSock -> {

                    vertx.eventBus().consumer("sendmsg", msg -> sockJsSock.write(msg.body().toString()));
                    sockJsSock
                            .handler(msg->vertx.eventBus().publish("sendmsg", Buffer.buffer().appendBuffer(msg).appendString(" from : ").appendString(sockJsSock.localAddress().host())))
                            .exceptionHandler(Throwable::printStackTrace);
                });

        router.route().handler(LoggerHandler.create());
        router.route("/talk/*").handler(sockJSHandler);
        router.route("/static/*").handler(StaticHandler.create());
        router.route("/static/*").handler(StaticHandler.create("lib"));
        router.route("/")
                .handler(req-> req.response().end(vertx.fileSystem().readFileBlocking("webroot/html/index.html").toString(Charset.forName("UTF-8"))));

        httpServer.requestHandler(router::accept).listen(8080);
    }
}
