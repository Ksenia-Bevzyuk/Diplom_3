package model.Stellar_Burgers.constanceModel;

import org.openqa.selenium.By;

public class Registration {
    public static final String STELLAR_BURGERS_REGISTER_URL =
            "https://stellarburgers.nomoreparties.site/register";
    public static final By NAME_AND_EMAIL_FIELD =
            By.xpath(".//input[@name = 'name']");
    public static final By PASS_FIELD =
        By.xpath(".//input[@name = 'Пароль']");
    public static final By REGISTER_BUTTON =
            By.xpath(".//button[text() = 'Зарегистрироваться']");
    public static final By ERROR_INCORRECT_PASS =
            By.xpath(".//p[text()='Некорректный пароль']");
    public static final By LOGIN_BUTTON_REGISTER =
            By.className("Auth_link__1fOlj");
}
