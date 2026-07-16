package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.OrderPage;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderTest {
    private WebDriver driver;
    private HomePage objHomePage;
    private OrderPage objOrderPage;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();

        objHomePage = new HomePage(driver);
        objOrderPage = new OrderPage(driver);

        objHomePage.open();
        objHomePage.clickCloseCookie();
    }

    @ParameterizedTest
    @MethodSource("orderFirstFormData")
    void successOrderTopButton(String name, String surname, String address, String subway, String phone, String date, String period, String color, String comment) {
        objHomePage.clickTopOrderButton();

        objOrderPage.waitForFirstOrderContentSection();
        objOrderPage.fillCustomerInfo(name, surname, address, subway, phone);
        objOrderPage.waitForSecondOrderContentSection();
        objOrderPage.fillRentInfo(date, period, color, comment);

        boolean actual = objOrderPage.isSuccessOrderModalDisplayed();
        assertTrue(actual, "Сообщение об успешном заказе не отображается");
    }

    @ParameterizedTest
    @MethodSource("orderSecondFormData")
    void successOrderBottomButton(String name, String surname, String address, String subway, String phone, String date, String period, String color, String comment) {
        objHomePage.clickBottomOrderButton();

        objOrderPage.waitForFirstOrderContentSection();
        objOrderPage.fillCustomerInfo(name, surname, address, subway, phone);
        objOrderPage.waitForSecondOrderContentSection();
        objOrderPage.fillRentInfo(date, period, color, comment);

        boolean actualResult = objOrderPage.isSuccessOrderModalDisplayed();
        assertTrue(actualResult, "Сообщение об успешном заказе не отображается");
    }

    private static Stream<Arguments> orderFirstFormData() {
        return Stream.of(
                Arguments.of(
                        "Андрей",
                        "Богомолов",
                        "Московская",
                        "Лубянка",
                        "+79912328382",
                        "16.07.2026",
                        "двое суток",
                        "black",
                        "Не звонить"
                )
        );
    }

    private static Stream<Arguments> orderSecondFormData() {
        return Stream.of(
                Arguments.of(
                        "Екатерина",
                        "Высоцкая",
                        "Питерская",
                        "Комсомольская",
                        "+79912371829",
                        "16.07.2026",
                        "сутки",
                        "grey",
                        "Звонить днем"
                )
        );
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
