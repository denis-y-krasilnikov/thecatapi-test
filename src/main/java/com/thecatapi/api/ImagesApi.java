package com.thecatapi.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ImagesApi extends Api {
    public ImagesApi() {
        super("images");
    }

    @Step("Search images")
    public Response search(Map<String, Object> searchParams) {
        return given(defaultSpec).params(searchParams).get("search");
    }

    public static class SearchParamsBuilder {
        private final Map<String, Object> params = new HashMap<>();

        public SearchParamsBuilder setBreedId(String breedId) {
            params.put("breed_id", breedId);
            return this;
        }

        public Map<String, Object> build() {
            return params;
        }
    }
}
