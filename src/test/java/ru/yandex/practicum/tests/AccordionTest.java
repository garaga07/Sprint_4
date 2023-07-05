package ru.yandex.practicum.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;
import ru.yandex.practicum.pages.MainPage;

@RunWith(Parameterized.class)
public class AccordionTest {

    private WebDriver driver;
    private  By accordionHeading;
    private  By accordionContent;
    private  String expectedText;

    public AccordionTest(By accordionHeading, By accordionContent, String expectedText) {
        this.accordionHeading = accordionHeading;
        this.accordionContent = accordionContent;
        this.expectedText = expectedText;
    }

    @Parameterized.Parameters
    public static Object[][] getAccordionData() {
        return new Object[][]{
                {MainPage.getAccordionHeading()[0], MainPage.getAccordionContent()[0], "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {MainPage.getAccordionHeading()[1], MainPage.getAccordionContent()[1], "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {MainPage.getAccordionHeading()[2], MainPage.getAccordionContent()[2], "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {MainPage.getAccordionHeading()[3], MainPage.getAccordionContent()[3], "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {MainPage.getAccordionHeading()[4], MainPage.getAccordionContent()[4], "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {MainPage.getAccordionHeading()[5], MainPage.getAccordionContent()[5], "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {MainPage.getAccordionHeading()[6], MainPage.getAccordionContent()[6], "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {MainPage.getAccordionHeading()[7], MainPage.getAccordionContent()[7], "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void verifyAccordionTextTest() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.open(MainPage.getBaseUrl());
        objMainPage.clickCookieButton();
        objMainPage.clickAccordion(accordionHeading);
        String actualText = objMainPage.getTextAccordion(accordionContent);
        objMainPage.verifyAccordionText(expectedText, actualText);
    }
    //Закрываем браузер
    @After
    public void teardown() {
        driver.quit();
    }
}