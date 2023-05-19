import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.example.ElementsPage;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.example.RequestsSql.*;

public class Tests
{
    private static WebDriver driver;
    private static ElementsPage page;
    private static String id;

    @BeforeClass
    public static void setUp() throws MalformedURLException
    {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        WebDriverManager.chromedriver().setup();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        page = new ElementsPage(driver);
        driver.get("https://www.w3schools.com/sql/trysql.asp?filename=trysql_select_all");
        Random random = new Random();
        id = String.valueOf(random.nextInt(50));
    }

    @Test
    @Description(" Вывести все строки таблицы Customers и убедиться, что запись с ContactName равной 'Giovanni Rovelli' имеет Address = 'Via Ludovico il Moro 22'")
    public void checkAddressInRow()
    {
        page.enterSqlQuery(ALL_FROM_CUSTOMERS);
        page.clickRunButton();
        String expectedValue = page.getTableCellText("Giovanni Rovelli", "Address");
        Assert.assertEquals("Via Ludovico il Moro 22", expectedValue);
    }

    @Test
    @Description("Вывести только те строки таблицы Customers, где city='London'. Проверить, что в таблице ровно 6 записей.")
    public void checkNumberRows()
    {
        page.enterSqlQuery(CUSTOMERS_FROM_LONDON);
        page.clickRunButton();
        Assert.assertEquals(6, page.getTableRowCount());
    }

    @Test
    @Description("Добавить новую запись в таблицу Customers и проверить, что эта запись добавилась.")
    public void checkNewValueInTable()
    {
        page.enterSqlQuery(NEW_DATA);
        page.clickRunButton();
        //можно было бы так же проверить сообщение внизу с текстом, что изменения внесены
        page.enterSqlQuery(CONTACT_NAME_QA);
        page.clickRunButton();
        Assert.assertEquals(1, page.getTableRowCount());
    }

    @Test
    @Description("Обновить все поля (кроме CustomerID) в любой записи таблицы Customers и проверить, что изменения записались в базу.")
    public void checkChangesAfterRefreshRecord()
    {
        String sqlQuery = String.format(CHANGE_DATA, id);
        page.enterSqlQuery(sqlQuery);
        page.clickRunButton();
        String sqlCheck = String.format(CHECK_CHANGE_DATA, id);
        page.enterSqlQuery(sqlCheck);
        page.clickRunButton();
        String expectedValue = page.getTableCellText("New Name", "City");
        Assert.assertEquals("New City", expectedValue);
    }

    @Test
    @Description("Проверяем кол-во выводимых строк с тем, которое считается и выводится в результате")
    public void testNumberOfRowsEqualsRows()
    {
        page.enterSqlQuery(CUSTOMERS_FROM_LONDON);
        page.clickRunButton();
        int expectedRowCount = Integer.parseInt(page.getRowNumberOfRecords().getText().replaceAll("\\D+", ""));
        int actualRowCount = page.getTableRowCount();
        Assert.assertEquals(expectedRowCount, actualRowCount);
    }

    @AfterClass
    public static void tearDown()
    {
        driver.quit();
    }
}
