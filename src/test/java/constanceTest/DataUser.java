package constanceTest;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;

public class DataUser {

    @Step("Генерация name пользователя")
    public static String generationName() {
        Faker faker = new Faker();
        return faker.name().firstName();
    }

    @Step("Генерация email пользователя")
    public static String generationEmail() {
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }

    @Step("Генерация корректного password пользователя")
    public static String generationCorrectPass() {
        Faker faker = new Faker();
        return faker.internet().password(6, 8);
    }

    @Step("Генерация некорректного password пользователя")
    public static String generationIncorrectPass() {
        Faker faker = new Faker();
        return faker.internet().password(2, 5);
    }
}
