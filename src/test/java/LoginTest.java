import constanceTest.DataUser;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.site.stellar.burgers.ConstructorPage;
import model.site.stellar.burgers.ForGotPasswordPage;
import model.site.stellar.burgers.LoginPage;
import model.site.stellar.burgers.RegisterPage;
import model.site.stellar.burgers.client.StellarBurgersClient;
import model.site.stellar.burgers.client.clientModel.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import webDriver.WebDriverFactory;
import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.junit.Assert.assertTrue;


public class LoginTest {
    private WebDriver driver;
    private String accessToken;
    private String email;
    private String password;

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
    @DisplayName("Проверка входа в аккаунт с главной страницы")
    @Description("Проверка входа в аккаунт со страницы конструктора, кнопка 'Войти в аккаунт'")
    public void loginFromConstructorLoginButtonTest() {
        ConstructorPage objConstructorPage = new ConstructorPage(driver);
        objConstructorPage.open();
        objConstructorPage.clickToLoginButton();

        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.setLoginFields(email, password);
        assertTrue(objLoginPage.isSuccessLogin());
    }

    @Test
    @DisplayName("Проверка входа в аккаунт с главной страницы")
    @Description("Проверка входа в аккаунт со страницы конструктора, кнопка 'Личный кабинет'")
    public void loginFromConstructorPersonalAccountButtonTest() {
        ConstructorPage objConstructorPage = new ConstructorPage(driver);
        objConstructorPage.open();
        objConstructorPage.clickToPersonalAccountButton();

        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.setLoginFields(email, password);
        assertTrue(objLoginPage.isSuccessLogin());
    }

    @Test
    @DisplayName("Проверка входа в аккаунт со страницы восстановления пароля")
    @Description("Проверка входа в аккаунт со страницы восстановления пароля, кнопка 'Войти'")
    public void loginFromForGotPassLoginButtonTest() {
        ForGotPasswordPage objForGotPassPage = new ForGotPasswordPage(driver);
        objForGotPassPage.open();
        objForGotPassPage.clickToLoginButton();

        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.setLoginFields(email, password);
        assertTrue(objLoginPage.isSuccessLogin());
    }

    @Test
    @DisplayName("Проверка входа в аккаунт со страницы восстановления пароля")
    @Description("Проверка входа в аккаунт со страницы восстановления пароля, кнопка 'Войти'")
    public void loginFromRegisterLoginButtonTest() {
        RegisterPage objRegisterPage = new RegisterPage(driver);
        objRegisterPage.open();
        objRegisterPage.clickToLoginButton();

        LoginPage objLoginPage = new LoginPage(driver);
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
