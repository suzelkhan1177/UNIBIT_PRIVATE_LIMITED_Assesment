import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import org.junit.Assert;

public class LoginPageAutomation {
    public static void main(String[] args) {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Create an instance of ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Navigate to the login page
        driver.get("https://sakshingp.github.io/assignment/login.html");

        // Find the username and password fields
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));

        // Enter the username and password
        usernameField.sendKeys("any_username");
        passwordField.sendKeys("any_password");

        // Find and click the login button
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        // Wait for the home page to load
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlContains("home.html"));

        // Verify that the user is logged in successfully
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue("Login failed!", currentUrl.contains("home.html"));

        // Click the AMOUNT header to sort
        WebElement amountHeader = driver.findElement(By.cssSelector("th[data-testid='amount-header']"));
        amountHeader.click();

        // Get the values in the amount column
        // Assuming the amounts are in a table with the 'transaction-table' id
        WebElement transactionTable = driver.findElement(By.id("transaction-table"));
        java.util.List<WebElement> amountElements = transactionTable.findElements(By.cssSelector("td[data-testid='amount']"));
        java.util.List<String> amounts = new java.util.ArrayList<>();
        for (WebElement amountElement : amountElements) {
            amounts.add(amountElement.getText());
        }

        // Verify that the amounts are sorted in ascending order
        boolean isSorted = true;
        for (int i = 1; i < amounts.size(); i++) {
            double currentAmount = Double.parseDouble(amounts.get(i).replace("$", "").replace(",", ""));
            double previousAmount = Double.parseDouble(amounts.get(i - 1).replace("$", "").replace(",", ""));
            if (currentAmount < previousAmount) {
                isSorted = false;
                break;
            }
        }
        Assert.assertTrue("Amounts are not sorted!", isSorted);

        // Close the browser
        driver.quit();
    }
}
