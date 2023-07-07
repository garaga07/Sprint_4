package ru.yandex.practicum.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import ru.yandex.practicum.pages.MainPage;
import ru.yandex.practicum.pages.OrderPage;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class OrderBaseTest extends BaseTest {
    //Поля класса
    private By buttonOrderMain;
    private String name;
    private String lastName;
    private String address;
    private String phone;
    private String orderDate;
    private String comment;
    private By colorCheckBox;
    private boolean isVisibleExpected;

    //Конструктор класса
    public OrderBaseTest(By buttonOrderMain, String name, String lastName, String address,
                         String phone, String orderDate, String comment, By colorCheckBox, boolean isVisibleExpected) {
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

    //Параметризация
    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][]{
                {MainPage.getButtonOrderTop(), "Егор", "Иванов", "Полевая 88", "77777777771", "01.01.2024", "Самокат заряжен", OrderPage.getCheckBox()[0], true},
                {MainPage.getButtonOrderBottom(), "Василий", "Петров", "Луговая 98", "77777777772", "03.04.2025", "Позвоните по прибытию", OrderPage.getCheckBox()[1], true},
        };
    }

    @Test
    public void makeOrderTest() {
        OrderPage objOrderPage = new OrderPage(driver);
        //Открываем страницу в браузере (сначала в хроме, потом в мозиле)
        objOrderPage.open(MainPage.getBaseUrl());
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
}
