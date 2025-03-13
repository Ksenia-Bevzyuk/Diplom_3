import constanceTest.DataUser;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.Stellar_Burgers.LoginPage;
import model.Stellar_Burgers.client.StellarBurgersClient;
import model.Stellar_Burgers.client.clientModel.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import webDriver.WebDriverFactory;
import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class LoginTest {
    private WebDriver driver;
    private String accessToken;
    private String email;
    private String password;

    private String url;
    private String button;

    public LoginTest(String url, String button) {
        this.url = url;
        this.button = button;
    }

    @Parameterized.Parameters(name = "URL и локаторы кнопок 'Войти': {0} {1} {2} {3}")
    public static Object [][] data() {
        return new Object[][] {
                {"Конструктор", "Кнопка 'Войти' на главной странице"},
                {"Конструктор", "Кнопка личный кабинет"},
                {"Страница регистрации", "Кнопка 'Войти' на странице регистрации"},
                {"Страница восстановления пароля", "Кнопка 'Войти' на странице восстановления пароля"}
        };
    }
    @Before
    @DisplayName("POST /api/auth/register и создание драйвера")
    @Description
            ("Создание user со сгенерированными данными, получение токена перед каждым тестом")
    public void start() {
        email = DataUser.generationEmail();
        password = DataUser.generationCorrectPass();
        User user = new User(DataUser.generationName(), email, password);
        StellarBurgersClient client = new StellarBurgersClient("");
        ValidatableResponse response = client.createUser(user);
        accessToken = client.getAccessToken(response);

        driver = WebDriverFactory.createWebDriver();
    }

    @Test
    @DisplayName("Проверка входа в аккаунт")
    @Description("Проверка входа в аккаунт с разными URL и кнопками 'Войти'")
    public void loginTest() {
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.open(url);
        objLoginPage.clickToLoginButton(button);
        objLoginPage.setLoginFields(email, password);
        assertTrue(objLoginPage.isSuccessLogin());
    }

    @After
    @DisplayName("DELETE /api/auth/user и закрытие браузера")
    @Description("Удаление записей о пользователе и закрытие браузера после каждого теста")
    public void quitAndDeleteUser() {
        driver.quit();

        StellarBurgersClient clientForDelete = new StellarBurgersClient(accessToken);
        ValidatableResponse responseDelUser = clientForDelete.deleteUser();
        responseDelUser.assertThat().statusCode(SC_ACCEPTED);
    }
}
