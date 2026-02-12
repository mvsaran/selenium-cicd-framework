package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;

public class SanityTest {
    @Test(groups = { "sanity" })
    public void invalidLoginTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        LoginPage login = new LoginPage(driver);
        login.enterUsername("invalid_user");
        login.enterPassword("invalid_pass");
        login.clickLogin();
        Assert.assertTrue(driver.getPageSource().contains("Epic sadface"));
        driver.quit();
    }
}
