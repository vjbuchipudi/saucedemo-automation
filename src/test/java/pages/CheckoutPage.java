package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {

    @FindBy(id = "first-name")
    private WebElement firstNameInput;

    @FindBy(id = "last-name")
    private WebElement lastNameInput;

    @FindBy(id = "postal-code")
    private WebElement postalCodeInput;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(className = "complete-header")
    private WebElement completeHeader;

    @FindBy(className = "title")
    private WebElement pageTitle;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCheckoutStepOneDisplayed() {
        try {
            waitForElementToBeVisible(pageTitle);
            return pageTitle.getText().equals("Checkout: Your Information");
        } catch (Exception e) {
            return false;
        }
    }

    public void enterCheckoutInformation(String firstName, String lastName, String postalCode) {
        sendKeys(firstNameInput, firstName);
        sendKeys(lastNameInput, lastName);
        sendKeys(postalCodeInput, postalCode);
    }

    public void clickContinue() {
        click(continueButton);
    }

    public boolean isCheckoutStepTwoDisplayed() {
        try {
            waitForElementToBeVisible(pageTitle);
            return pageTitle.getText().equals("Checkout: Overview");
        } catch (Exception e) {
            return false;
        }
    }

    public void clickFinish() {
        click(finishButton);
    }

    public String getOrderCompleteMessage() {
        waitForElementToBeVisible(completeHeader);
        return getText(completeHeader);
    }
}
