package ru.yandex.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static junit.framework.TestCase.assertEquals;

public class MainPage {
    // URL для перехода
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";
    private WebDriver driver;
    //Локатор кнопки подтверждения использования настроек Куки
    private By buttonCookie = By.id("rcc-confirm-button");

    //Локаторы кнопки Заказать (кнопка находится на главной странице)
    private static final By[] BUTTON_ORDER = {
            By.xpath(".//button[@class = 'Button_Button__ra12g']"),
            By.xpath(".//button[@class='Button_Button__ra12g Button_UltraBig__UU3Lp']")
    };
    //Массивы с локаторами для параметризации
    private static final By[] ACCORDION_HEADING = {
        By.id("accordion__heading-0"),
        By.id("accordion__heading-1"),
        By.id("accordion__heading-2"),
        By.id("accordion__heading-3"),
        By.id("accordion__heading-4"),
        By.id("accordion__heading-5"),
        By.id("accordion__heading-6"),
        By.id("accordion__heading-7")
    };
    private static final By[] ACCORDION_CONTENT = {
        By.id("accordion__panel-0"),
        By.id("accordion__panel-1"),
        By.id("accordion__panel-2"),
        By.id("accordion__panel-3"),
        By.id("accordion__panel-4"),
        By.id("accordion__panel-5"),
        By.id("accordion__panel-6"),
        By.id("accordion__panel-7")
    };

    // Конструктор класса
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод для нажатия на кнопку подтверждения использования Куки
    public void clickCookieButton() {
        WebElement cookieElement = driver.findElement(buttonCookie);
        // Прокрутка содержимого главной страницы до кнопки подтверждения куки
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cookieElement);
        // Нажатие на элемент
        cookieElement.click();
    }

    // Метод ждет пока аккардеон отобразиться на странице и станет кликабельным
    public void waitClickableAccordion(By accordionHeading) {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(accordionHeading));
    }

    // Метод ждет отображение содержимого аккардеона на странице
    public void waitVisibleAccordionContent(By accordionContent) {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(accordionContent));
    }

    // Метод для нажатия аккардеона
    public void clickAccordion(By accordionHeading) {
        WebElement accordionElement = driver.findElement(accordionHeading);
        // Прокрутка содержимого главной страницы до аккордеона
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", accordionElement);
        waitClickableAccordion(accordionHeading);
        // Нажатие на элемент
        accordionElement.click();
    }

    // Метод для получения текста аккардеона
    public String getTextAccordion(By accordionContent) {
        waitVisibleAccordionContent(accordionContent);
        return driver.findElement(accordionContent).getText();
    }

    // Метод для открытия указанного URL
    public void open(String url) {
        driver.get(url);
    }

    // Метод для проверки текста аккардеона
    public void verifyAccordionText(String expectedText, String actualText) {
        assertEquals("Текст не совпадает с ожидаемым результатом!!!", expectedText, actualText);
    }

    //Геттер для передачи BASE_URL
    public static String getBaseUrl(){
        return BASE_URL;
    }


    //Геттеры для передачи поисковых локаторов в параметрах
    public static By[] getAccordionHeading(){
        return ACCORDION_HEADING;
    }

    public static By[] getAccordionContent(){
        return ACCORDION_CONTENT;
    }
    //Геттер для кнопки заказать в параметризации OrderTest
    public static By[] getButtonOrder(){
        return BUTTON_ORDER;
    }
}