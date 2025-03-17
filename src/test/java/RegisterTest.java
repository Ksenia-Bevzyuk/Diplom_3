import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.site.stellar.burgers.RegisterPage;
import model.site.stellar.burgers.client.clientModel.Credentials;
import model.site.stellar.burgers.client.StellarBurgersClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import webDriver.WebDriverFactory;
import static constanceTest.DataUser.*;
import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.junit.Assert.assertTrue;

public class RegisterTest {
    private WebDriver driver;

    private String name;
    private String email;
    RegisterPage objRegisterPage;
    String correctPass;

    @Before
    @DisplayName("Создание драйвера, генерация name и email")
    @Description("Создание драйвера, генерация name и email для регистрации пользователя" +
            " перед каждым тестом")
    public void start() {
        name = generationName();
        email = generationEmail();

        driver = WebDriverFactory.createWebDriver();
    }

    @Test
    @DisplayName("Проверка успешной регистрации пользователя")
    @Description("Проверка регистрации пользователя при вводе корректных данных")
    public void registerSuccessTest() {
        correctPass = generationCorrectPass();

        objRegisterPage = new RegisterPage(driver);
        objRegisterPage.open();
        objRegisterPage.setRegisterFields(name, email, correctPass);
        objRegisterPage.clickToRegister();

        assertTrue(objRegisterPage.isSuccessRegister());
    }

    @Test
    @DisplayName("Проверка отображения ошибки при регистрации пользователя")
    @Description("Проверка отображения ошибки при вводе некорректного password")
    public void registerErrorWithIncorrectPassTest() {
        String incorrectPass = generationIncorrectPass();

        objRegisterPage = new RegisterPage(driver);
        objRegisterPage.open();
        objRegisterPage.setRegisterFields(name, email, incorrectPass);
        objRegisterPage.clickToRegister();

        assertTrue(objRegisterPage.isVisionErrorInputIncorrectPass());
    }

    @After
    @DisplayName("DELETE /api/auth/user и закрытие браузера")
    @Description("Удаление записей о пользователе и закрытие браузера после каждого теста")
    public void quitAndDeleteUser() {
        driver.quit();
        if(correctPass != null) {
            StellarBurgersClient client = new StellarBurgersClient("");
            ValidatableResponse response = client.loginUser(new Credentials(email, correctPass));
            String accessToken = client.getAccessToken(response);

            StellarBurgersClient clientForDelete = new StellarBurgersClient(accessToken);
            ValidatableResponse responseDelUser = clientForDelete.deleteUser();
            responseDelUser.assertThat().statusCode(SC_ACCEPTED);
        }
    }
}
