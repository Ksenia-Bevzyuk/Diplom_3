package model.Stellar_Burgers;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Objects;
import static model.Stellar_Burgers.constanceModel.Account.*;
import static model.Stellar_Burgers.constanceModel.Constructor.*;
import static model.Stellar_Burgers.constanceModel.Login.*;

public class PersonalAccountPage {
    private WebDriver driver;

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик по кнопке для перехода из личного кабинета")
    public void clickToTransition(String button) {
        By buttonLocator;
        if(Objects.equals(button, "Логотип")) {
            buttonLocator = LOGO;
        } else if (Objects.equals(button, "Конструктор")) {
            buttonLocator = CONSTRUCTOR;
        } else if (Objects.equals(button, "Кнопка выхода из аккаунта")) {
            buttonLocator = EXIT_BUTTON;
        } else {
            buttonLocator = LOGO;
            System.out.println("Выбрана кнопка по умолчанию");
        }
        WebElement buttonForClick = driver.findElement(buttonLocator);
        buttonForClick.click();
    }

    @Step("Проверка успешности перехода")
    public boolean isSuccessTransition(String element) {
        By elementLocator;
        if(Objects.equals(element, "Оформить заказ")) {
            elementLocator = ORDER_BUTTON;
        } else if (Objects.equals(element, "Кнопка 'Войти'")) {
            elementLocator = LOGIN_BUTTON;
        } else {
            elementLocator = ORDER_BUTTON;
            System.out.println("Выбран элемент по умолчанию");
        }
        WebElement isSuccessTransitionToConstructor =
                new WebDriverWait(driver, Duration.ofSeconds(1))
                        .until(ExpectedConditions
                                .visibilityOfElementLocated(elementLocator));
        return isSuccessTransitionToConstructor.isDisplayed();
    }
}
