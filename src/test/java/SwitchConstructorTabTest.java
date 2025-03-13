import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.Stellar_Burgers.ConstructorPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Objects;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class SwitchConstructorTabTest {
    private WebDriver driver;

    private String tab;
    private String element;

    public SwitchConstructorTabTest(String tab, String element) {
        this.tab = tab;
        this.element = element;
    }

    @Parameterized.Parameters(name = "Локаторы для переключения вкладок: {0} {1} {2}")
    public static Object [][] data() {
        return new Object[][] {
                {"Булки", "Флюоресцентная булка R2-D3"},
                {"Соусы", "Соус Spicy-X"},
                {"Начинки", "Мясо бессмертных моллюсков Protostomia"}
        };
    }

    @Before
    @DisplayName("Создание драйвера")
    @Description
            ("Создание драйвера перед каждым тестом")
    public void start() {
        driver = new ChromeDriver();
    }

    @Test
    @DisplayName("Проверка перехода между вкладками")
    @Description("Проверка перехода между вкладками ингредиентов конструктора")
    public void checkSwitchingConstructorTab() {
        ConstructorPage objConstructorPage = new ConstructorPage(driver);
        objConstructorPage.open();
        if (Objects.equals(tab, "Булки")) {
            objConstructorPage.clickToTab("Начинки");
        }
        objConstructorPage.clickToTab(tab);

        assertTrue(objConstructorPage.isElementIntoView(element));
    }

    @After
    @DisplayName("Закрытие браузера")
    @Description("Закрытие браузера после каждого теста")
    public void quit() {
        driver.quit();
    }
}
