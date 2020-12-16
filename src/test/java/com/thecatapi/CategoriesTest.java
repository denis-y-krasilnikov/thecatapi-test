package com.thecatapi;

import com.thecatapi.api.CategoriesApi;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import junit.extensions.AllureRestAssuredConnector;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.Matchers.hasItem;

@ExtendWith(AllureRestAssuredConnector.class)
@Epic("Categories")
public class CategoriesTest {
    @ParameterizedTest(name = "Category {arguments} should exist")
    @ValueSource(strings = {"boxes", "clothes"})
    @Description("Category should exist")
    public void categoryShouldExist(String category) {
        Allure.step(String.format("Category %s should exist", category), () -> {
            CategoriesApi categoriesApi = new CategoriesApi();
            categoriesApi.get().then().body("name", hasItem(category));
        });
    }
}
