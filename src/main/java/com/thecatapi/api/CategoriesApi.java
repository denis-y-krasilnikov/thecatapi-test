package com.thecatapi.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CategoriesApi extends Api {
    public CategoriesApi() {
        super("categories");
    }

    @Step("Get categories")
    public Response get() {
        return given(defaultSpec).get("/");
    }
}
