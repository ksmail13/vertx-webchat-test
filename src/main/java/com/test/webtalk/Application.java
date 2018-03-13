package com.test.webtalk;

import com.test.webtalk.verticle.MainVerticle;
import io.vertx.core.Launcher;
import io.vertx.core.Vertx;

public class Application {

    public static void main(String[] args) {
        String[] arg = {"run", MainVerticle.class.getName()};
        Launcher.main(arg);
    }
}
