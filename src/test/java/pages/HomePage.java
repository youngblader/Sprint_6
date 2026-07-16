package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private final WebDriver driver;

    private static final String URL = "https://qa-scooter.education-services.ru/";

    private final By topOrderButton = By.cssSelector(".Button_Button__ra12g");
    private final By bottomOrderButton = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");
    private final By accordionQuestions = By.xpath(".//div[contains(@class, 'accordion__button')]");
    private final By accordionAnswers = By.xpath(".//div[contains(@class, 'accordion__panel')]");
    private final By cookieButton = By.xpath(".//button[contains(@class, 'App_CookieButton') and text()='да все привыкли']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(URL);
    }

    public void clickTopOrderButton() {
       driver.findElement(topOrderButton).click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(topOrderButton)).click();
    }

    public void clickBottomOrderButton() {
        WebElement element = driver.findElement(bottomOrderButton);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView();",
                element
        );

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(bottomOrderButton)).click();
    }

    public void clickQuestion(int index) {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(driver -> driver.findElements(accordionQuestions).get(index));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                element
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                element
        );
    }

    public String getAnswerText(int index) {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(driver -> !driver.findElements(accordionAnswers).get(index).getText().isEmpty());

        return driver.findElements(accordionAnswers).get(index).getText();
    }

    public void clickCloseCookie() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(cookieButton)).click();
    }
}