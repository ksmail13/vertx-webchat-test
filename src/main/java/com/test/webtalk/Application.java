package com.test.webtalk;

import com.test.webtalk.verticle.HttpServerVerticle;
import io.vertx.core.Launcher;

public class Application {

    public static void main(String[] args) {
        String[] arg = {"run", HttpServerVerticle.class.getName()};
        Launcher.main(arg);
    }
}
