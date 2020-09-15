package frontend.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProductListTests extends BaseTestConfiguration {

    @Test
    public void shouldOpenProductList() throws InterruptedException {
        // given
        String loginTitle = openLoginPage();
        assertEquals(LOGIN_PAGE_TITLE, loginTitle);
        String loggedInTitle = logIn();
        assertEquals(LOGGED_IN_PAGE_TITLE, loggedInTitle);

        // when
        WebElement productListButton = webDriver.findElement(By.linkText(PRODUCT_LIST));
        productListButton.click();
        sleep(500);

        // then
        WebElement searchProductPlaceholder = webDriver.findElement(By.id(SEARCH_PRODUCT_PLACEHOLDER));
        assertNotNull(searchProductPlaceholder);
    }
}
