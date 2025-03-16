package model.site.stellar.burgers.client;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import model.site.stellar.burgers.client.clientModel.Credentials;
import model.site.stellar.burgers.client.clientModel.User;
import static io.restassured.RestAssured.given;

public class StellarBurgersClient {
    RequestSpecification requestSpec;
    String accessToken;

    public StellarBurgersClient(String accessToken) {
        RequestSpecBuilder builder = new RequestSpecBuilder()

                .setBaseUri("https://stellarburgers.nomoreparties.site")
                .setContentType("application/json")
                .addHeader("Authorization", accessToken);

        requestSpec = builder.build();
    }
    @Step ("Создание пользователя")
    public ValidatableResponse createUser(User user) {
        return given()
                .filter(new AllureRestAssured())
                .log()
                .all()
                .spec(requestSpec)
                .body(user)
                .post(EndPoints.CREATE_USER)
                .then()
                .log()
                .all();
    }

    @Step ("Получение токена")
    public String getAccessToken(ValidatableResponse response) {
        return accessToken = response.extract().jsonPath().getString("accessToken");
    }

    @Step ("Логин пользователя")
    public ValidatableResponse loginUser(Credentials credentials) {
        return given()
                .filter(new AllureRestAssured())
                .log()
                .all()
                .spec(requestSpec)
                .body(credentials)
                .post(EndPoints.LOGIN_USER)
                .then()
                .log()
                .all();
    }

    @Step("Удаление пользователя")
    public ValidatableResponse deleteUser() {
        return given()
                .filter(new AllureRestAssured())
                .log()
                .all()
                .spec(requestSpec)
                .delete(EndPoints.DELETE_USER)
                .then()
                .log()
                .all();
    }
}
