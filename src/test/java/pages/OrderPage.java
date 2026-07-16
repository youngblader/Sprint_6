package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private final WebDriver driver;

    private final By nameField = By.xpath(".//input[@placeholder='* Имя']");
    private final By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By subwayField = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    private final By nextButton = By.xpath(".//button[text()='Далее']");

    private final By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By periodField = By.xpath("//div[contains(@class, 'Dropdown-placeholder') and text()='* Срок аренды']");
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    private final By orderButton = By.xpath(".//div[contains(@class, 'Order_Buttons')]//button[text()='Заказать']");
    private final By confirmOrderButton = By.xpath(".//button[text()='Да']");
    private final By successOrderModal = By.xpath(".//div[contains(@class, 'Order_ModalHeader') and contains(text(), 'Заказ оформлен')]");
    private final By firstOrderContentSection = By.xpath(".//div[contains(@class, 'Order_Header') and contains(text(), 'Для кого самокат')]");
    private final By secondOrderContentSection = By.xpath(".//div[contains(@class, 'Order_Header') and contains(text(), 'Про аренду')]");

    private By checkSubwayOption(String metro) {
        return By.xpath(".//div[contains(@class, 'select-search__select')]//div[text()='" + metro + "']");
    }

    private By checkPeriodOption(String period) {
        return By.xpath(".//div[contains(@class, 'Dropdown-option') and text()='" + period + "']");
    }

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setSubway(String subway) {
        driver.findElement(subwayField).sendKeys(subway);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(checkSubwayOption(subway)))
                .click();
    }

    public void clickNextButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(nextButton))
                .click();
    }

    public void clickOrderButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(orderButton))
                .click();
    }

    public void clickConfirmOrderButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(confirmOrderButton))
                .click();
    }

    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void setSurname(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
    }

    public void setAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    public void setPhone(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }

    public void setDate(String date) {
        driver.findElement(dateField).sendKeys(date, Keys.ENTER);
    }

    public void setComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    public void setColor(String color) {
        driver.findElement(By.id(color)).click();
    }

    public void setPeriodDays(String period) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(periodField))
                .click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(checkPeriodOption(period)))
                .click();
    }

    public boolean isSuccessOrderModalDisplayed() {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(successOrderModal))
                    .isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void waitForFirstOrderContentSection() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(firstOrderContentSection));
    }

    public void waitForSecondOrderContentSection() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(secondOrderContentSection));
    }

    public void fillCustomerInfo(String name, String surname, String address, String subway, String phone) {
        setName(name);
        setSurname(surname);
        setAddress(address);
        setSubway(subway);
        setPhone(phone);

        clickNextButton();
    }

    public void fillRentInfo(String date, String period, String color, String comment) {
        setDate(date);
        setPeriodDays(period);
        setColor(color);
        setComment(comment);

        clickOrderButton();
        clickConfirmOrderButton();
    }
}