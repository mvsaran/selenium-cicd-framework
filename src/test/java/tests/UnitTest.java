package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UnitTest {
    @Test(groups = { "unit" })
    public void stringConcatTest() {
        String a = "Sauce";
        String b = "Demo";
        Assert.assertEquals(a + b, "SauceDemo");
    }
}
