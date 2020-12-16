package com.thecatapi.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BreedsApi extends Api {
    public BreedsApi() {
        super("breeds");
    }

    @Step("Search breed by name")
    public Response searchByName(String breedName) {
        return given(defaultSpec).param("q", breedName).get("search");
    }
}
