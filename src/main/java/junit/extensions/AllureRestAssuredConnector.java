package junit.extensions;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class AllureRestAssuredConnector implements BeforeAllCallback {
    private static volatile boolean isAdded = false;

    private static synchronized void addFilter() {
        if (isAdded) return;
        RestAssured.filters(new AllureRestAssured());
        isAdded = true;
    }

    @Override
    public void beforeAll(ExtensionContext context) {
        addFilter();
    }
}
