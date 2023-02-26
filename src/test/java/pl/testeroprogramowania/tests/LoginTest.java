package pl.testeroprogramowania.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.testeroprogramowania.pages.HomePage;

public class LoginTest extends BaseTest {

    @Test
    public void logInTest() throws InterruptedException {
        int random = (int) (Math.random() * 1000);
        
        WebElement dashboardLink = new HomePage(driver)
                .openMyAccountPage()
                .logInValidData("test1@test.pl", "test@test.pl")
                .getDashboardLink();

        Assert.assertTrue(dashboardLink.isDisplayed());
        Assert.assertEquals(dashboardLink.getText(), "Dashboard");
    }

    @Test
    public void logInWithInvalidPasswordTest() throws InterruptedException {
        int random = (int) (Math.random() * 1000);
        WebElement error = new HomePage(driver)
                .openMyAccountPage()
                .logInInvalidData("test@test.pl", "te@test.pl")
                .getError();

        Assert.assertTrue(error.isDisplayed());
        Assert.assertTrue(error.getText().contains("Too many failed login attempts."));
    }
}
