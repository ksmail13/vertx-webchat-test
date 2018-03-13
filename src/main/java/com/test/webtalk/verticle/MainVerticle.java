package com.test.webtalk.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;

public class MainVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        DeploymentOptions deploymentOptions = new DeploymentOptions().setInstances(4);
        vertx.deployVerticle(HttpServerVerticle.class.getName(), deploymentOptions);
        vertx.deployVerticle(ChatVerticle.class.getName(), deploymentOptions);
    }
}
