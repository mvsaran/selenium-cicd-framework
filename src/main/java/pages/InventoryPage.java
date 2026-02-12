package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class InventoryPage {
    private WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isInventoryDisplayed() {
        return driver.findElement(By.className("inventory_list")).isDisplayed();
    }
}
