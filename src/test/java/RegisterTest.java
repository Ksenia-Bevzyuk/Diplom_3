import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.Stellar_Burgers.RegisterPage;
import model.Stellar_Burgers.client.clientModel.Credentials;
import model.Stellar_Burgers.client.StellarBurgersClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import webDriver.WebDriverFactory;

import static constanceTest.DataUser.*;
import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class RegisterTest {
    private WebDriver driver;
    private boolean isVisionLoginButton;
    private boolean isVisionErrorIncorrectPass;
    private String name;
    private String email;
    private String password;

    public RegisterTest(boolean isVisionLoginButton, boolean isVisionErrorIncorrectPass,
                        String name, String email, String password) {
        this.isVisionLoginButton = isVisionLoginButton;
        this.isVisionErrorIncorrectPass = isVisionErrorIncorrectPass;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Parameterized.Parameters(name = "Данные для регистрации пользователя" +
            "и проверки корректности ввода password: {0} {1}")
    public static Object [][] data() {
        return new Object[][] {
                {true, false, generationName(), generationEmail(), generationCorrectPass()},
                {false, true, generationName(), generationEmail(), generationIncorrectPass()}
        };
    }

    @Before
    @DisplayName("Создание драйвера")
    @Description("Создание драйвера перед каждым тестом")
    public void start() {
        driver = WebDriverFactory.createWebDriver();
    }

    @Test
    @DisplayName("Проверка регистрации пользователя")
    @Description("Проверка регистрации пользователя и отображения ошибки " +
            "некорректного password")
    public void registerTest() {
        RegisterPage objRegisterPage = new RegisterPage(driver);
        objRegisterPage.open();
        objRegisterPage.setRegisterFields(name, email, password);
        objRegisterPage.clickToRegister();
        if(isVisionLoginButton) {
            assertEquals(isVisionLoginButton, objRegisterPage.isSuccessRegister());
        } else {
            assertEquals(isVisionErrorIncorrectPass,
                    objRegisterPage.isVisionErrorInputIncorrectPass());
        }
    }

    @After
    @DisplayName("DELETE /api/auth/user и закрытие браузера")
    @Description("Удаление записей о пользователе и закрытие браузера после каждого теста")
    public void quitAndDeleteUser() {
        driver.quit();
        if(isVisionLoginButton) {
            StellarBurgersClient client = new StellarBurgersClient("");
            ValidatableResponse response = client.loginUser(new Credentials(email, password));
            String accessToken = client.getAccessToken(response);

            StellarBurgersClient clientForDelete = new StellarBurgersClient(accessToken);
            ValidatableResponse responseDelUser = clientForDelete.deleteUser();
            responseDelUser.assertThat().statusCode(SC_ACCEPTED);
        }
    }
}
