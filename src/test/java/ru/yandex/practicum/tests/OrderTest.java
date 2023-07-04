package ru.yandex.practicum.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import ru.yandex.practicum.pages.OrderPage;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class OrderTest {
    //Поля класса
    private WebDriver driver;
    private final String browser;
    private final By buttonOrderMain;
    public final String name;
    public final String lastName;
    public final String address;
    public final String phone;
    public final String orderDate;
    public final String comment;
    private final By colorCheckBox;
    private final boolean isVisibleExpected;
    //Конструктор класса
    public OrderTest(String browser, By buttonOrderMain, String name, String lastName, String address,
                     String phone, String orderDate, String comment, By colorCheckBox, boolean isVisibleExpected) {
        this.browser = browser;
        this.buttonOrderMain = buttonOrderMain;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.orderDate = orderDate;
        this.comment = comment;
        this.colorCheckBox = colorCheckBox;
        this.isVisibleExpected = isVisibleExpected;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][]{
                {"chrome",  By.xpath(".//button[@class = 'Button_Button__ra12g']"),"Егор","Иванов","Полевая 88","77777777771","01.01.2024","Самокат заряжен", By.id("black"), true},
                {"chrome",  By.xpath(".//button[@class='Button_Button__ra12g Button_UltraBig__UU3Lp']"),"Василий","Петров","Луговая 98","77777777772","03.04.2025","Позвоните перед выездом", By.id("grey"), true},
                {"firefox", By.xpath(".//button[@class = 'Button_Button__ra12g']"),"Виктор","Чернов","Ветряная 96","77777777773","12.12.2026","Позвоните по приезду", By.id("black"), true},
                {"firefox", By.xpath(".//button[@class='Button_Button__ra12g Button_UltraBig__UU3Lp']"),"Дмитрий","Туйнов","Береговая 18","77777777774","13.10.2023","Оплата картой", By.id("grey"), true},
        };
    }

    @Before
    public void setup() {
        if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
            driver = new FirefoxDriver(options);
        } else if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
            driver = new ChromeDriver(options);
        } else {
            throw new IllegalArgumentException("Invalid browser parameter.");
        }
        driver.manage().window().setSize(new Dimension(1920, 1080));
    }

    @Test
    public void makeOrderTest() {
        OrderPage objOrderPage = new OrderPage(driver);
        //Открываем страницу в браузере (сначала в хроме, потом в мозиле)
        objOrderPage.open("https://qa-scooter.praktikum-services.ru/");
        //Нажимаем кнопку Заказать для перехода к форме оформления заказа (сначала верхнюю, потом нижнюю из параметризации)
        objOrderPage.clickButtonOrderMain(buttonOrderMain);
        //Заполняем поля ввода на первой странице оформления заказа
        objOrderPage.firstNameSetValue(name);
        objOrderPage.lastNameSetValue(lastName);
        objOrderPage.addressSetValue(address);
        objOrderPage.inputRailWayStationClick();
        objOrderPage.inputStationValueClick();
        objOrderPage.inputTelephoneSetValue(phone);
        //Нажимаем кнопку для перехода ко второй странице оформления заказа
        objOrderPage.nextButtonClick();
        //Заполняем поля ввода на второй странице оформления заказа
        objOrderPage.setOrderDate(orderDate);
        objOrderPage.dropDownRentPeriodClick();
        objOrderPage.rentPeriodValueClick();
        objOrderPage.colorCheckBoxClick(colorCheckBox);
        objOrderPage.inputCommentSetValue(comment);
        objOrderPage.buttonOrderClick();
        //Нажимаем кнопку подтверждения заказа
        objOrderPage.buttonConfirmationClick();
        //Осуществляем запись фактического результата видимости модального окна с оформленным заказом
        boolean isVisibleActual = objOrderPage.isOrderModalHeaderVisible();
        //Сравниваем ожидаемый и фактический результаты
        assertEquals("Модальное окно с оформленным заказом не отображается", isVisibleExpected, isVisibleActual);
    }
    //Закрываем браузер
    @After
    public void teardown() {
        driver.quit();
    }
}