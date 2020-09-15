package frontend.selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.lang.Thread.sleep;

public class BaseTestConfiguration {

    static final String LOGIN_PAGE = "http://localhost:4200";
    static final String LOGIN_PAGE_TITLE = "Log in to e-Concrete precasts";
    static final String LOGGED_IN_PAGE_TITLE = "e-Concrete precasts";
    static final String EMAIL = "test@example.com";
    static final String PASSWORD = "123";
    static final String SEARCH_PRODUCT_PLACEHOLDER = "SearchProduct";
    static final String PRODUCT_LIST = "Product List";

    WebDriver webDriver;

    @Before
    public void before() {
        webDriver = new ChromeDriver();
//        webDriver = new FirefoxDriver();
    }

    @After
    public void after() {
        webDriver.quit();
    }

    String openLoginPage() throws InterruptedException {
        webDriver.navigate().to(LOGIN_PAGE);
        sleep(300);
        webDriver.manage().window().maximize();
        return webDriver.getTitle();
    }

    String logIn() throws InterruptedException {
        WebElement emailInput = webDriver.findElement(By.name("username"));
        emailInput.sendKeys(EMAIL);

        WebElement passwordInput = webDriver.findElement(By.name("password"));
        passwordInput.sendKeys(PASSWORD);

        passwordInput.submit();
        sleep(1500);
        return webDriver.getTitle();
    }
}
