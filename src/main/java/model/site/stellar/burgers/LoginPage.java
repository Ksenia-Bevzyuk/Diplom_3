package model.site.stellar.burgers;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {

    private static final By LOGIN_BUTTON =
            By.xpath(".//button[text()='Войти']");
    private static final By PERSONAL_ACCOUNT_BUTTON =
            By.xpath(".//p[text() = 'Личный Кабинет']");
    private static final By EMAIL_FIELD =
            By.xpath(".//input[@name = 'name']");
    private static final By PASS_FIELD =
            By.xpath(".//input[@name = 'Пароль']");
    private static final By EXIT_BUTTON =
            By.xpath(".//button[text() = 'Выход']");
    private static final By BUNS_TAB =
            By.xpath(".//span[text() = 'Булки']");

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Заполнить поле email в форме входа")
    public void setEmailField(String email) {
        driver.findElement(EMAIL_FIELD).sendKeys(email);
    }

    @Step("Заполнить поле password в форме входа")
    public void setPassField(String password) {
        driver.findElement(PASS_FIELD).sendKeys(password);
    }

    @Step("Клик по кнопке 'Войти' для входа в аккаунт")
    public void clickToLogin() {
        WebElement registerButton = driver.findElement(LOGIN_BUTTON);
        registerButton.isEnabled();
        registerButton.click();
    }

    @Step("Проверка успешности входа в аккаунт")
    public boolean isSuccessLogin() {
        new WebDriverWait(driver, Duration.ofSeconds(1))
                        .until(ExpectedConditions
                                .visibilityOfElementLocated(BUNS_TAB));
        driver.findElement(PERSONAL_ACCOUNT_BUTTON).click();
        WebElement isVisibleButtonExitFromAccount =
                new WebDriverWait(driver, Duration.ofSeconds(1))
                        .until(ExpectedConditions
                                .visibilityOfElementLocated(EXIT_BUTTON));
        return isVisibleButtonExitFromAccount.isDisplayed();
    }

    @Step("Шаг: заполнение полей и клик 'Войти' для входа в аккаунт")
    public void setLoginFields(String email, String password) {
        setEmailField(email);
        setPassField(password);
        clickToLogin();
    }
}
