package model.site.stellar.burgers;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class PersonalAccountPage {
    private final By EXIT_BUTTON =
            By.xpath(".//button[text() = 'Выход']");
    private final By LOGO =
            By.xpath(".//a[@href = '/']");
    private final By CONSTRUCTOR =
            By.xpath(".//p[text() = 'Конструктор']");
    private final By ORDER_BUTTON =
            By.xpath(".//button[text() = 'Оформить заказ']");
    private final By LOGIN_BUTTON =
            By.xpath(".//button[text()='Войти']");

    private WebDriver driver;

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик по кнопке LOGO для перехода из личного кабинета в конструктор")
    public void clickToLogoTransition() {
        WebElement buttonForClick = driver.findElement(LOGO);
        buttonForClick.click();
    }

    @Step("Клик по кнопке Конструктор для перехода из личного кабинета в конструктор")
    public void clickToConstructorTransition() {
        WebElement buttonForClick = driver.findElement(CONSTRUCTOR);
        buttonForClick.click();
    }

    @Step("Проверка успешности перехода по LOGO в конструктор")
    public boolean isSuccessTransitionOnClickLogoOrConstructorToConstructor() {
        WebElement isSuccessTransitionToConstructor =
                new WebDriverWait(driver, Duration.ofSeconds(1))
                        .until(ExpectedConditions
                                .visibilityOfElementLocated(ORDER_BUTTON));
        return isSuccessTransitionToConstructor.isDisplayed();
    }

    @Step("Клик по кнопке Выйти для выхода из личного кабинета")
    public void clickToExitButtonTransition() {
        WebElement buttonForClick = driver.findElement(EXIT_BUTTON);
        buttonForClick.click();
    }

    @Step("Проверка успешности перехода по LOGO в конструктор")
    public boolean isSuccessTransitionOnClickExit() {
        WebElement isSuccessTransitionToConstructor =
                new WebDriverWait(driver, Duration.ofSeconds(1))
                        .until(ExpectedConditions
                                .visibilityOfElementLocated(LOGIN_BUTTON));
        return isSuccessTransitionToConstructor.isDisplayed();
    }
}
