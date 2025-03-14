import constanceTest.DataUser;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.Stellar_Burgers.LoginPage;
import model.Stellar_Burgers.PersonalAccountPage;
import model.Stellar_Burgers.client.StellarBurgersClient;
import model.Stellar_Burgers.client.clientModel.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import webDriver.WebDriverFactory;

import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

@RunWith(Parameterized.class)
public class PersonalAccountTest {
    private WebDriver driver;
    private String accessToken;
    private String email;
    private String password;

    private String button;
    private String transitionTo;

    public PersonalAccountTest(String button, String transitionTo) {
        this.button = button;
        this.transitionTo = transitionTo;
    }

    @Parameterized.Parameters(name = "Кнопки для перехода из личного кабинета: {0} {1} {2}")
    public static Object [][] data() {
        return new Object[][] {
                {"Логотип", "Оформить заказ"},
                {"Конструктор", "Оформить заказ"},
                {"Кнопка выхода из аккаунта", "Кнопка 'Войти'"}
        };
    }

    @Before
    @DisplayName("POST /api/auth/register, создание драйвера и вход в личный кабинет")
    @Description
            ("Создание user со сгенерированными данными, получение токена, " +
                    "вход в личный кабинет перед каждым тестом")
    public void start() {
        email = DataUser.generationEmail();
        password = DataUser.generationCorrectPass();
        User user = new User(DataUser.generationName(), email, password);
        StellarBurgersClient client = new StellarBurgersClient("");
        ValidatableResponse response = client.createUser(user);
        accessToken = client.getAccessToken(response);

        driver = WebDriverFactory.createWebDriver();

        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.open("Конструктор");
        objLoginPage.clickToLoginButton("Кнопка 'Войти' на главной странице");
        objLoginPage.setLoginFields(email, password);
        assumeTrue(objLoginPage.isSuccessLogin());
    }

    @Test
    @DisplayName("Проверка перехода из личного кабинета")
    @Description("Проверка перехода из личного кабинета в конструктор" +
            " и выхода из личного кабинета")
    public void accountTest() {
        PersonalAccountPage objPersonalAccountPage = new PersonalAccountPage(driver);
        objPersonalAccountPage.clickToTransition(button);

        assertTrue(objPersonalAccountPage.isSuccessTransition(transitionTo));
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
