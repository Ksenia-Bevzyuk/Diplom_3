package model.Stellar_Burgers;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import static model.Stellar_Burgers.constanceModel.Login.LOGIN_BUTTON;
import static model.Stellar_Burgers.constanceModel.Registration.*;

public class RegisterPage {
    private WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открыть страницу регистрации")
    public void open() {
        driver.get(STELLAR_BURGERS_REGISTER_URL);
    }

    @Step("Заполнение полей name и email в форме регистрации")
    public void setNameAndEmailFields(String name, String email) {
        List<WebElement> fields = driver.findElements(NAME_AND_EMAIL_FIELD);
        fields.get(0).sendKeys(name);
        fields.get(1).sendKeys(email);
    }

    @Step("Заполнение поля password в форме регистрации")
    public void setPassField(String password) {
        driver.findElement(PASS_FIELD).sendKeys(password);
    }

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public void clickToRegister() {
        WebElement registerButton = driver.findElement(REGISTER_BUTTON);
        registerButton.isEnabled();
        registerButton.click();
    }

    @Step("Проверка успешности регистрации")
    public boolean isSuccessRegister() {
        WebElement isVisibleLoginFormSuccessRegister =
                new WebDriverWait(driver, Duration.ofSeconds(1))
                        .until(ExpectedConditions
                                .visibilityOfElementLocated(LOGIN_BUTTON));
        return isVisibleLoginFormSuccessRegister.isDisplayed();
    }

    @Step("Проверка вывода ошибки при вводе некорректного password")
    public boolean isVisionErrorInputIncorrectPass() {
        WebElement isVisibleErrorPassField =
                new WebDriverWait(driver, Duration.ofSeconds(1))
                        .until(ExpectedConditions
                                .visibilityOfElementLocated(ERROR_INCORRECT_PASS));
        return isVisibleErrorPassField.isDisplayed();
    }

    @Step("Шаг: заполнение полей формы регистрации")
    public void setRegisterFields(String name, String email, String password) {
        setNameAndEmailFields(name, email);
        setPassField(password);
    }
}
