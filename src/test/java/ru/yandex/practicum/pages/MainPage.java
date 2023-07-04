package ru.yandex.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static junit.framework.TestCase.assertEquals;

public class MainPage {
    // Переменные для передачи локаторов аккордеона в конструктор MainPage
    private WebDriver driver;
    //Локатор кнопки подтверждения использования настроек Куки
    private final By buttonCookie = By.id("rcc-confirm-button");

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
        // Вызываем метод для закрытия элемента с куками, чтобы все вопросы из списка были кликабельными
        clickCookieButton();
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
}