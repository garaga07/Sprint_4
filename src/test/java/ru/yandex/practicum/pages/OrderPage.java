package ru.yandex.practicum.pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {
    private WebDriver driver;
    // Локатор поля Имя
    private final By inputFirstName = By.cssSelector("input[placeholder='* Имя']");
    // Локатор поля Фамилия
    private final By inputLastName = By.cssSelector("input[placeholder='* Фамилия']");
    // Локатор поля Адрес
    private final By inputAddress = By.cssSelector("input[placeholder='* Адрес: куда привезти заказ']");
    // Локатор поля селекта с выбором станции
    private final By inputRailWayStation = By.cssSelector("input[placeholder='* Станция метро']");
    // Локатор элемента из списка станций
    private final By inputStationValue = By.xpath(".//li[@data-value='1']");
    // Локатор поля Телефон
    private final By inputTelephone = By.cssSelector("input[placeholder = '* Телефон: на него позвонит курьер']");
    // Локатор кнопки Далее
    private final By nextButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    // Локатор поля даты
    private final By inputData = By.cssSelector("input[placeholder='* Когда привезти самокат']");
    // Локатор поля выбора срока аренды
    private final By dropDownRentPeriod = By.xpath(".//div[@class='Dropdown-placeholder']");
    // Локатор значения из выпадающего списка со сроком аренды
    private final By rentPeriodValue = By.xpath(".//div[contains(@class, 'Dropdown-option') and text()='двое суток']");
    // Локатор поля Комментарий
    private final By inputComment = By.cssSelector("input[placeholder = 'Комментарий для курьера']");
    // Локатор кнопки Заказать
    private final By buttonOrder = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    // Локатор кнопки подтверждения заказа
    private final By buttonConfirmation = By.xpath("//button[contains(@class, 'Button_Button__ra12g') and text()='Да']");
    // Локатор модального окна оформленного заказа
    private final By orderModalHeader = By.xpath("//div[contains(@class, 'Order_ModalHeader__3FDaJ') and contains(text(), 'Заказ оформлен')]");

    // Конструктор класса
    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }
    //Метод нажатия кнопки Заказать на главной странице
    public void clickButtonOrderMain(By buttonOrderMain) {
        WebElement buttonOrderMainElement = driver.findElement(buttonOrderMain);
        // Прокрутка содержимого главной страницы до кнопки Заказать
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",buttonOrderMainElement);
        buttonOrderMainElement.click();
    }

    // Метод ожидания видимости поля Имя на форме оформления заказа
    public void waitVisibleInputFirstName() {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputFirstName));
    }
    // Метод заполнения поля Имя
    public void firstNameSetValue(String name) {
        waitVisibleInputFirstName();
        driver.findElement(inputFirstName).sendKeys(name);
    }

    // Метод заполнения поля Фамилия
    public void lastNameSetValue(String lastName) {
        driver.findElement(inputLastName).sendKeys(lastName);
    }

    // Метод заполнения поля Адрес
    public void addressSetValue(String address) {
        driver.findElement(inputAddress).sendKeys(address);
    }

    // Метод нажатия в поле Станция метро
    public void inputRailWayStationClick() {
        driver.findElement(inputRailWayStation).click();
    }

    // Метод ожидания кликабельности элемента из выпадающего списка станций метро
    public void waitClickableInputStationValue() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(inputStationValue));
    }

    // Метод выбора значения из выпадающего списка станций метро
    public void inputStationValueClick() {
        waitClickableInputStationValue();
        driver.findElement(inputStationValue).click();
    }
    // Метод заполнения поля Телефон
    public void inputTelephoneSetValue(String telephone) {
        driver.findElement(inputTelephone).sendKeys(telephone);
    }

    // Метод нажатия кнопки Далее для продолжения оформления заказа
    public void nextButtonClick() {
        driver.findElement(nextButton).click();
    }

    // Метод установки значения даты заказа
    public void setOrderDate(String date) {
        driver.findElement(inputData).sendKeys(date);
        driver.findElement(inputData).sendKeys(Keys.ENTER);
    }

    // Метод нажатия в поле срок аренды
    public void dropDownRentPeriodClick() {
        driver.findElement(dropDownRentPeriod).click();
    }
    // Метод ожидания кликабельности элемента из выпадающего срока аренды
    public void waitClickableRentPeriodValue() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(rentPeriodValue));
    }

    // Метод выбора значения из выпадающего списка срока аренды
    public void rentPeriodValueClick() {
        waitClickableRentPeriodValue();
        driver.findElement(rentPeriodValue).click();
    }

    // Метод выбора цвета самоката
    public void colorCheckBoxClick(By colorCheckBox) {
        driver.findElement(colorCheckBox).click();
    }

    // Метод заполнения поля комментарий
    public void inputCommentSetValue(String comment) {
        driver.findElement(inputComment).sendKeys(comment);
    }

    // Метод нажатия кнопки Заказать
    public void buttonOrderClick() {
        driver.findElement(buttonOrder).click();
    }

    // Метод ожидания кликабельности кнопки подтверждения заказа в модальном окне
    public void waitClickableButtonConfirmation() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(buttonConfirmation));
    }
    // Метод нажатия кнопки подтверждения заказа
    public void buttonConfirmationClick() {
        waitClickableButtonConfirmation();
        driver.findElement(buttonConfirmation).click();
    }

    //Ожидание видимости модального окна об успешном оформлении заказа
    public void waitVisibleOrderModalHeader() {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderModalHeader));
    }

    // Метод проверки видимости модального окна с оформленным заказом
    public boolean isOrderModalHeaderVisible() {
        waitVisibleOrderModalHeader();
        return driver.findElement(orderModalHeader).isDisplayed();
    }

    // Метод для открытия указанного URL
    public void open(String url) {
        driver.get(url);
    }
}