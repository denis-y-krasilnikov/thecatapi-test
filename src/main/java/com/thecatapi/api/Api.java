package com.thecatapi.api;

import com.thecatapi.config.Config;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public abstract class Api {
    protected RequestSpecification defaultSpec;

    public Api(String path) {
        defaultSpec = new RequestSpecBuilder()
            .setAccept(ContentType.JSON.getAcceptHeader())
            .setContentType(ContentType.JSON)
            .addHeader("x-api-key", Config.get("API_KEY"))
            .setBaseUri(Config.get("APP_URL"))
            .setBasePath(path)
            .build();
    }
}
