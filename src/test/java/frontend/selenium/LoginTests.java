package frontend.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

public class LoginTests extends BaseTestConfiguration {

    @Test
    public void shouldOpenLoginPage() throws InterruptedException {
        // when
        String loginTitle = openLoginPage();

        // then
        assertEquals(LOGIN_PAGE_TITLE, loginTitle);
    }

    @Test
    public void shouldLogin() throws InterruptedException {
        // given
        String loginTitle = openLoginPage();
        assertEquals(LOGIN_PAGE_TITLE, loginTitle);

        // when
        String loggedInTitle = logIn();

        // then
        assertEquals(LOGGED_IN_PAGE_TITLE, loggedInTitle);
    }

    @Test
    public void shouldLogout() throws InterruptedException {
        // given
        String loginTitle = openLoginPage();
        assertEquals(LOGIN_PAGE_TITLE, loginTitle);
        String loggedInTitle = logIn();
        assertEquals(LOGGED_IN_PAGE_TITLE, loggedInTitle);

        // when
        WebElement logoutButton = webDriver.findElement(By.linkText("Logout"));
        logoutButton.click();
        sleep(700);

        // then
        String loggedOutTitle = webDriver.getTitle();
        assertEquals(LOGIN_PAGE_TITLE, loggedOutTitle);
    }
}
