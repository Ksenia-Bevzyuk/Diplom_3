package model.Stellar_Burgers.constanceModel;

import org.openqa.selenium.By;

public class Constructor {
    public static final String STELLAR_BURGERS_CONSTRUCTOR_URL =
            "https://stellarburgers.nomoreparties.site";
    public static final By BUNS_TAB =
            By.xpath(".//span[text() = 'Булки']");
    public static final By LOGIN_BUTTON_CONSTRUCTOR =
            By.xpath(".//button[text() = 'Войти в аккаунт']");
    public static final By PERSONAL_ACCOUNT_BUTTON =
            By.xpath(".//p[text() = 'Личный Кабинет']");
    public static final By ORDER_BUTTON =
            By.xpath(".//button[text() = 'Оформить заказ']");

    public static final By BUN =
            By.xpath(".//span[text() = 'Булки']/parent::div");
    public static final By SOUSE =
            By.xpath(".//span[text() = 'Соусы']/parent::div");
    public static final By FILLING =
            By.xpath(".//span[text() = 'Начинки']/parent::div");

    public static final By BUN_ELEMENT =
            By.xpath(".//img[@alt = 'Флюоресцентная булка R2-D3']");
    public static final By SOUSE_ELEMENT =
            By.xpath(".//img[@alt = 'Соус Spicy-X']");
    public static final By FILLING_ELEMENT =
            By.xpath(".//img[@alt = 'Мясо бессмертных моллюсков Protostomia']");
}
