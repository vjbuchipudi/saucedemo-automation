package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductsPage extends BasePage {

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    @FindBy(className = "inventory_item")
    private List<WebElement> inventoryItems;

    @FindBy(id = "add-to-cart-sauce-labs-bolt-t-shirt")
    private WebElement addBoltTShirtButton;

    @FindBy(id = "remove-sauce-labs-bolt-t-shirt")
    private WebElement removeBoltTShirtButton;

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductsPageDisplayed() {
        try {
            waitForElementToBeVisible(pageTitle);
            return pageTitle.getText().equals("Products");
        } catch (Exception e) {
            return false;
        }
    }

    public String getPageTitle() {
        return getText(pageTitle);
    }

    public void addProductToCart(String productName) {
        String buttonId = "add-to-cart-" + productName.toLowerCase().replace(" ", "-");
        WebElement addButton = driver.findElement(By.id(buttonId));
        click(addButton);
        
        String removeButtonId = "remove-" + productName.toLowerCase().replace(" ", "-");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(removeButtonId)));
    }

    public void addBoltTShirtToCart() {
        click(addBoltTShirtButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("remove-sauce-labs-bolt-t-shirt")));
    }

    public boolean isProductAddedToCart(String productName) {
        try {
            String buttonId = "remove-" + productName.toLowerCase().replace(" ", "-");
            WebElement removeButton = driver.findElement(By.id(buttonId));
            return removeButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getCartItemCount() {
        try {
            waitForElementToBeVisible(cartBadge);
            return getText(cartBadge);
        } catch (Exception e) {
            return "0";
        }
    }

    public void clickCartIcon() {
        click(cartIcon);
    }

    public int getNumberOfProducts() {
        return inventoryItems.size();
    }
}