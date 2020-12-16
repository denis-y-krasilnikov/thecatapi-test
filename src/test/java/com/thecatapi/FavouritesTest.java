package com.thecatapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thecatapi.api.BreedsApi;
import com.thecatapi.api.FavouritesApi;
import com.thecatapi.api.ImagesApi;
import com.thecatapi.model.breeds.Breed;
import com.thecatapi.model.images.Image;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.restassured.response.Response;
import junit.extensions.AllureRestAssuredConnector;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import utils.MapConverter;

import java.util.Map;

import static java.util.Map.entry;
import static org.hamcrest.Matchers.*;

@ExtendWith(AllureRestAssuredConnector.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
@Epic("Favourites")
public class FavouritesTest {
    private final BreedsApi breedsApi = new BreedsApi();
    private final ImagesApi imagesApi = new ImagesApi();
    private final FavouritesApi favouritesApi = new FavouritesApi();

    private Image image;
    private int favouriteId;

    @Test
    @Order(1)
    @DisplayName("Add favourite")
    @Description("Image should be added to favourites")
    public void addFavourite() throws JsonProcessingException {
        String breedName = "Scottish Fold";
        Breed breed = breedsApi.searchByName(breedName).as(Breed[].class)[0];

        Map<String, Object> searchParams = new ImagesApi.SearchParamsBuilder().setBreedId(breed.id).build();
        Response imagesResponse = imagesApi.search(searchParams);
        imagesResponse.then().statusCode(200).body("size()", greaterThan(0));
        image = imagesResponse.as(Image[].class)[0];

        Map usedData = Map.ofEntries(
            entry("Breed name", breedName),
            entry("Breed id", breed.id),
            entry("Image url", image.url)
        );
        Allure.addAttachment("Used data", "application/json", new MapConverter(usedData).convertToJson());

        Response addFavouritesResponse = favouritesApi.add(image.id);
        favouriteId = addFavouritesResponse.then().body("message", is("SUCCESS")).extract().path("id");

        Allure.step("Image should be in favourites", (context) -> {
            context.parameter("id", favouriteId);
            context.parameter("image_id", image.id);
            favouritesApi
                .get()
                .then()
                .body(".", hasItem(both(hasEntry("id", favouriteId)).and(hasEntry("image_id", image.id))));
        });
    }

    @Test
    @Order(2)
    @DisplayName("Remove favourite")
    @Description("Image should be removed to favourites")
    public void removeFavourite() {
        favouritesApi.remove(favouriteId).then().body("message", is("SUCCESS"));

        Allure.step("Image should not be in favourites", (context) -> {
            context.parameter("id", favouriteId);
            context.parameter("image_id", image.id);
            favouritesApi
                .get()
                .then()
                .body(".", not(hasItem(both(hasEntry("id", favouriteId)).and(hasEntry("image_id", image.id)))));
        });
    }
}
