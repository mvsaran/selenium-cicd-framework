package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.InventoryPage;

public class SmokeTest {
    @Test(groups = { "smoke" })
    public void validLoginTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        LoginPage login = new LoginPage(driver);
        login.enterUsername("standard_user");
        login.enterPassword("secret_sauce");
        login.clickLogin();
        InventoryPage inventory = new InventoryPage(driver);
        Assert.assertTrue(inventory.isInventoryDisplayed());
        driver.quit();
    }
}
