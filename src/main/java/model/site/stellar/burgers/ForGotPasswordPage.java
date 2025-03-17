package model.site.stellar.burgers;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ForGotPasswordPage {
    private static final String STELLAR_BURGERS_FORGOT_PASS =
            "https://stellarburgers.nomoreparties.site/forgot-password";
    private static final By LOGIN_BUTTON_FOR_GOT_PASS =
            By.className("Auth_link__1fOlj");
    private static final By LOGIN_BUTTON =
            By.xpath(".//button[text()='Войти']");

    private WebDriver driver;

    public ForGotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открыть страницу")
    public void open() {
        driver.get(STELLAR_BURGERS_FORGOT_PASS);
    }

    @Step("Клик по кнопке 'Войти' для перехода в форму входа в аккаунт")
    public void clickToLoginButton() {
        WebElement loginButton = driver.findElement(LOGIN_BUTTON_FOR_GOT_PASS);
        loginButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(1))
                .until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
    }
}
