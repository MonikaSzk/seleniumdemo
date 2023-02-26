package pl.testeroprogramowania.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.testeroprogramowania.pages.HomePage;

public class RegisterTest extends BaseTest {

    @Test
    public void registerUserTest() throws InterruptedException {
        int random = (int) (Math.random() * 1000);
        WebElement dashboardLink = new HomePage(driver)
                .openMyAccountPage()
                .registerUserValidData("test" + random + "@test.pl", "test" + random + "@test.pl")
                .getDashboardLink();

        Assert.assertTrue(dashboardLink.isDisplayed());
        Assert.assertEquals(dashboardLink.getText(), "Dashboard");
    }

    @Test
    public void registerUserWithTheSameEmailTest() throws InterruptedException {
        int random = (int) (Math.random() * 1000);
        WebElement error = new HomePage(driver)
                .openMyAccountPage()
                .registerUserInvalidData("test@test.pl", "test@test.pl")
                .getError();

        Assert.assertTrue(error.isDisplayed());
        Assert.assertEquals(error.getText(), "Error: An account is already registered with your email address. Please log in.");
    }
}
