package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class IntegrationTest {
    @Test(groups = { "integration" })
    public void integrationSampleTest() {
        int x = 2;
        int y = 3;
        Assert.assertEquals(x * y, 6);
    }
}
