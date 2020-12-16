package com.thecatapi.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class FavouritesApi extends Api {
    public FavouritesApi() {
        super("favourites");
    }

    @Step("Add favourite")
    public Response add(String imageId) {
        return given(defaultSpec).body(Map.of("image_id", imageId)).post("/");
    }

    @Step("Remove favourite")
    public Response remove(int id) {
        return given(defaultSpec).delete(String.valueOf(id));
    }

    @Step("Get favourites")
    public Response get() {
        return given(defaultSpec).get("/");
    }
}
