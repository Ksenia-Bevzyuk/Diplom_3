import constanceTest.DataUser;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.site.stellar.burgers.ConstructorPage;
import model.site.stellar.burgers.LoginPage;
import model.site.stellar.burgers.PersonalAccountPage;
import model.site.stellar.burgers.client.StellarBurgersClient;
import model.site.stellar.burgers.client.clientModel.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import webDriver.WebDriverFactory;
import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

public class PersonalAccountTest {
    private WebDriver driver;
    private String accessToken;
    private String email;
    private String password;

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

        ConstructorPage objConstructorPage = new ConstructorPage(driver);
        objConstructorPage.open();
        objConstructorPage.clickToLoginButton();

        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.setLoginFields(email, password);
        assumeTrue(objLoginPage.isSuccessLogin());
    }

    @Test
    @DisplayName("Проверка перехода из личного кабинета в конструктор")
    @Description("Проверка перехода при клике на Лого из личного кабинета в конструктор")
    public void accountCheckTransitionOnClickToLogoToConstructorTest() {
        PersonalAccountPage objPersonalAccountPage = new PersonalAccountPage(driver);
        objPersonalAccountPage.clickToLogoTransition();

        assertTrue(objPersonalAccountPage.isSuccessTransitionOnClickLogoOrConstructorToConstructor());
    }

    @Test
    @DisplayName("Проверка перехода из личного кабинета в конструктор")
    @Description("Проверка перехода при клике на Конструктор из личного кабинета в конструктор")
    public void accountCheckTransitionOnClickToConstructorToConstructorTest() {
        PersonalAccountPage objPersonalAccountPage = new PersonalAccountPage(driver);
        objPersonalAccountPage.clickToConstructorTransition();

        assertTrue(objPersonalAccountPage.isSuccessTransitionOnClickLogoOrConstructorToConstructor());
    }

    @Test
    @DisplayName("Проверка выхода из личного кабинета")
    @Description("Проверка выхода из личного кабинета при клике Выход")
    public void accountCheckTransitionOnClickToExitTest() {
        PersonalAccountPage objPersonalAccountPage = new PersonalAccountPage(driver);
        objPersonalAccountPage.clickToExitButtonTransition();

        assertTrue(objPersonalAccountPage.isSuccessTransitionOnClickExit());
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
