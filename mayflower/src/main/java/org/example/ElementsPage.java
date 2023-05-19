package org.example;

import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ElementsPage {
    private WebDriver driver;
    private By runButton = By.className("ws-btn");
    private By entryField = By.id("textareaCodeSQL");
    private By rowNumberOfRecords = By.xpath("//*[@id='divResultSQL']//div[contains(text(),'Number of Records')]");
    private By tableRows = By.xpath("//table[@class='ws-table-all notranslate']/tbody//tr[position() > 1]");

    public ElementsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickRunButton() {
        driver.findElement(runButton).click();
    }

    public void enterSqlQuery(String request) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String escapedRequest = request.replace("'", "\\'");
        String script = "window.editor.setValue('" + escapedRequest + "');";
        js.executeScript(script);
    }

    public WebElement getRowNumberOfRecords() {
        return driver.findElement(rowNumberOfRecords);
    }

    public int getTableRowCount() {
        return driver.findElements(tableRows).size();
    }
    @Description("Метод ищет значение ячейки по её содержимому, после чего находит в этой же строке значение столбца, который указан явно")
    public String getTableCellText(String rowText, String columnText) {
        String rowXpath = "//tbody/tr[td[contains(text(),'" + rowText + "')]]";
        WebElement row = driver.findElement(By.xpath(rowXpath));

        int columnIndex = getColumnIndex(columnText);
        String addressCellXpath = "./td[" + columnIndex + "]";
        WebElement addressCell = row.findElement(By.xpath(addressCellXpath));

        return addressCell.getText();
    }

    private int getColumnIndex(String columnText) {
        String headerXpath = "//tbody/tr[1]/th[contains(text(),'" + columnText + "')]";
        WebElement headerCell = driver.findElement(By.xpath(headerXpath));

        List<WebElement> precedingSiblingCells = headerCell.findElements(By.xpath("./preceding-sibling::th"));
        return precedingSiblingCells.size() + 1;
    }
}