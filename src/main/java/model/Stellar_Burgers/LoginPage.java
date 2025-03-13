package model.Stellar_Burgers;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Objects;
import static model.Stellar_Burgers.constanceModel.Account.EXIT_BUTTON;
import static model.Stellar_Burgers.constanceModel.Constructor.*;
import static model.Stellar_Burgers.constanceModel.Login.LOGIN_BUTTON;
import static model.Stellar_Burgers.constanceModel.Login.STELLAR_BURGERS_FORGOT_PASS;
import static model.Stellar_Burgers.constanceModel.Registration.*;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открыть страницу")
    public void open(String page) {
        if(Objects.equals(page, "Конструктор")) {
            driver.get(STELLAR_BURGERS_CONSTRUCTOR_URL);
        } else if (Objects.equals(page, "Страница регистрации")) {
            driver.get(STELLAR_BURGERS_REGISTER_URL);
        } else if (Objects.equals(page, "Страница восстановления пароля")) {
            driver.get(STELLAR_BURGERS_FORGOT_PASS);
        }
    }

    @Step("Клик по кнопке 'Войти' для перехода в форму")
    public void clickToLoginButton(String button) {
        By buttonLocator;
        if(Objects.equals(button, "Кнопка 'Войти' на главной странице")) {
            buttonLocator = LOGIN_BUTTON_CONSTRUCTOR;
        } else if (Objects.equals(button, "Кнопка личный кабинет")) {
            buttonLocator = PERSONAL_ACCOUNT_BUTTON;
        } else if (Objects.equals(button, "Кнопка 'Войти' на странице регистрации")) {
            buttonLocator = LOGIN_BUTTON_REGISTER;
        } else if (Objects.equals(button, "Кнопка 'Войти' на странице восстановления пароля")) {
            buttonLocator = LOGIN_BUTTON_REGISTER;
        } else {
            buttonLocator = LOGIN_BUTTON_CONSTRUCTOR;
            System.out.println("Выбрана кнопка по умолчанию");
        }
        WebElement loginButton = driver.findElement(buttonLocator);
        loginButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(1))
                .until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
    }

    @Step("Заполнить поле email в форме входа")
    public void setEmailField(String email) {
        driver.findElement(NAME_AND_EMAIL_FIELD).sendKeys(email);
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
