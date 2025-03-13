package model.Stellar_Burgers;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Objects;
import static model.Stellar_Burgers.constanceModel.Constructor.*;

public class ConstructorPage {
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
    public boolean isElementIntoView(String ingredient) {
        By ingredientLocator;
        if(Objects.equals(ingredient, "Флюоресцентная булка R2-D3")) {
            ingredientLocator = BUN_ELEMENT;
        } else if (Objects.equals(ingredient, "Соус Spicy-X")) {
            ingredientLocator = SOUSE_ELEMENT;
        } else if (Objects.equals(ingredient, "Мясо бессмертных моллюсков Protostomia")) {
            ingredientLocator = FILLING_ELEMENT;
        } else {
            ingredientLocator = BUN_ELEMENT;
            System.out.println("Элемент не найден");
        }
        WebElement element = driver.findElement(ingredientLocator);
        return new WebDriverWait(driver, Duration.ofSeconds(3))
        .until(
                driver1 -> {
                    Rectangle rect = element.getRect();
                    Dimension windowSize = driver.manage().window().getSize();
                    return rect.getX() >= 0
                            && rect.getY() >= 0
                            && rect.getX() + rect.getWidth() <= windowSize.getWidth()
                            && rect.getY() + rect.getHeight() <= windowSize.getHeight();
                });
    }
}
