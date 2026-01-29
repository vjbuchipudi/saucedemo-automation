package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductsPage;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class ShoppingSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @Before
    public void setUp() {
        // Setup WebDriverManager to automatically manage ChromeDriver
        WebDriverManager.chromedriver().setup();
        
        // Configure Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        
        // Disable password manager and leak detection warnings
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);

        // Uncomment the line below to run in headless mode
        // options.addArguments("--headless");
        
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        // Initialize page objects
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I navigate to the SauceDemo website")
    public void iNavigateToTheSauceDemoWebsite() {
        driver.get("https://www.saucedemo.com/");
    }

    @When("I login with username {string} and password {string}")
    public void iLoginWithUsernameAndPassword(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("I should see the Products page")
    public void iShouldSeeTheProductsPage() {
        Assert.assertTrue("Products page is not displayed", 
                productsPage.isProductsPageDisplayed());
        Assert.assertEquals("Page title is incorrect", 
                "Products", productsPage.getPageTitle());
    }

    @When("I add {string} to the cart")
    public void iAddToTheCart(String productName) {
        if (productName.equals("Sauce Labs Bolt T-Shirt")) {
            productsPage.addBoltTShirtToCart();
        } else {
            productsPage.addProductToCart(productName);
        }
    }

    @Then("the cart should show {int} item")
    public void theCartShouldShowItem(int expectedCount) {
        String actualCount = productsPage.getCartItemCount();
        Assert.assertEquals("Cart item count is incorrect", 
                String.valueOf(expectedCount), actualCount);
    }

    @Then("I should see {int} products displayed")
    public void iShouldSeeProductsDisplayed(int expectedProductCount) {
        int actualCount = productsPage.getNumberOfProducts();
        Assert.assertEquals("Number of products is incorrect", 
                expectedProductCount, actualCount);
    }

    @Then("I should see an error message")
    public void iShouldSeeAnErrorMessage() {
        Assert.assertTrue("Error message is not displayed", 
                loginPage.isErrorMessageDisplayed());
    }

    @When("I proceed to checkout")
    public void iProceedToCheckout() {
        productsPage.clickCartIcon();
        Assert.assertTrue("Cart page is not displayed", cartPage.isCartPageDisplayed());
        cartPage.clickCheckout();
    }

    @When("I enter shipping information {string}, {string}, {string}")
    public void iEnterShippingInformation(String firstName, String lastName, String zipCode) {
        Assert.assertTrue("Checkout step one is not displayed", checkoutPage.isCheckoutStepOneDisplayed());
        checkoutPage.enterCheckoutInformation(firstName, lastName, zipCode);
        checkoutPage.clickContinue();
    }

    @When("I finish the checkout")
    public void iFinishTheCheckout() {
        Assert.assertTrue("Checkout step two is not displayed", checkoutPage.isCheckoutStepTwoDisplayed());
        checkoutPage.clickFinish();
    }

    @Then("I should see the order confirmation message {string}")
    public void iShouldSeeTheOrderConfirmationMessage(String message) {
        Assert.assertEquals("Order confirmation message is incorrect", 
                message, checkoutPage.getOrderCompleteMessage());
    }
}