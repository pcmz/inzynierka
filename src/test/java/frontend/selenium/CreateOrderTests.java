package frontend.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CreateOrderTests extends BaseTestConfiguration {

    @Test
    public void shouldCreateOrder() throws InterruptedException {
        // given
        getLogged();

        // when
        addProductToCart();
        completeTheOrder();

        // then
        WebElement orderButton = webDriver.findElement(By.linkText("Order"));
        orderButton.click();
        sleep(500);

        WebElement orderRow = webDriver.findElement(By.id("orderRow"));
        assertNotNull(orderRow);
    }

    private void getLogged() throws InterruptedException {
        String loginTitle = openLoginPage();
        assertEquals(LOGIN_PAGE_TITLE, loginTitle);
        String loggedInTitle = logIn();
        assertEquals(LOGGED_IN_PAGE_TITLE, loggedInTitle);
    }

    private void addProductToCart() throws InterruptedException {
        WebElement productListButton = webDriver.findElement(By.linkText(PRODUCT_LIST));
        productListButton.click();
        sleep(500);
        WebElement detailsButton = webDriver.findElement(By.id("details"));
        detailsButton.click();
        sleep(500);

        WebElement addToCartButton = webDriver.findElement(By.id("addToCart"));
        addToCartButton.click();
        sleep(500);
    }

    private void completeTheOrder() throws InterruptedException {
        WebElement cartsButton = webDriver.findElement(By.linkText("Cart"));
        cartsButton.click();
        sleep(500);

        WebElement selectACustomerButton = webDriver.findElement(By.id("selectACustomer"));
        selectACustomerButton.click();
        sleep(500);

        WebElement selectACustomer = webDriver.findElement(By.id("customerOption"));
        selectACustomer.click();
        sleep(500);

        WebElement completeTheOrder = webDriver.findElement(By.id("completeTheOrder"));
        completeTheOrder.click();
        sleep(1000);
    }
}
