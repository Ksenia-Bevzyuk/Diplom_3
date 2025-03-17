package model.site.stellar.burgers;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Objects;

public class ConstructorPage {
    private static final String STELLAR_BURGERS_CONSTRUCTOR_URL =
            "https://stellarburgers.nomoreparties.site";
    private static final By LOGIN_BUTTON_CONSTRUCTOR =
            By.xpath(".//button[text() = 'Войти в аккаунт']");
    private static final By PERSONAL_ACCOUNT_BUTTON =
            By.xpath(".//p[text() = 'Личный Кабинет']");
    private static final By LOGIN_BUTTON =
            By.xpath(".//button[text()='Войти']"); //
    private static final By BUN =
            By.xpath(".//span[text() = 'Булки']/parent::div");
    private static final By SOUSE =
            By.xpath(".//span[text() = 'Соусы']/parent::div");
    private static final By FILLING =
            By.xpath(".//span[text() = 'Начинки']/parent::div");

    private WebDriver driver;

    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открыть главную страницу")
    public void open() {
        driver.get(STELLAR_BURGERS_CONSTRUCTOR_URL);
    }

    @Step("Переход между вкладками ингредиентов кликом")
    public void clickToTab(String tab) {
        By tabLocator;
        if(Objects.equals(tab, "Булки")) {
            tabLocator = BUN;
        } else if (Objects.equals(tab, "Соусы")) {
            tabLocator = SOUSE;
        } else if (Objects.equals(tab, "Начинки")) {
            tabLocator = FILLING;
        } else {
            tabLocator = BUN;
            System.out.println("Выбрана вкладка по умолчанию");
        }
        WebElement tabButton = driver.findElement(tabLocator);
        tabButton.isEnabled();
        tabButton.click();
    }

    @Step("Проверка видимости ингредиента из соответствующей вкладки при переходе на неё")
    public boolean isTabSelected(String tab) {
        By tabLocator;
        if(Objects.equals(tab, "Булки")) {
            tabLocator = BUN;
        } else if (Objects.equals(tab, "Соусы")) {
            tabLocator = SOUSE;
        } else if (Objects.equals(tab, "Начинки")) {
            tabLocator = FILLING;
        } else {
            tabLocator = BUN;
            System.out.println("Выбрана вкладка по умолчанию");
        }
        WebElement tabButton = driver.findElement(tabLocator);
        return new WebDriverWait(driver, Duration.ofSeconds(1))
                .until(ExpectedConditions.attributeContains(tabButton,
                        "class", "tab_tab_type_current__2BEPc"));
    }

    @Step("Клик по кнопке 'Войти' для перехода в форму входа в аккаунт")
    public void clickToLoginButton() {
        WebElement loginButton = driver.findElement(LOGIN_BUTTON_CONSTRUCTOR);
        loginButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(1))
                .until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
    }

    @Step("Клик по кнопке 'Личный кабинет' для перехода в форму входа в аккаунт")
    public void clickToPersonalAccountButton() {
        WebElement loginButton = driver.findElement(PERSONAL_ACCOUNT_BUTTON);
        loginButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(1))
                .until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
    }
}
