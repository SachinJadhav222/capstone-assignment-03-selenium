import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class AutomationAnywhereTest {
    public static void main(String[] args) {
        boolean isWindowsPlatform = System.getProperty("os.name").contains("Windows");
        // Set the path to your ChromeDriver
        if (isWindowsPlatform) {
            System.setProperty("webdriver.chrome.driver", "src/test/java/drivers/chromedriver-win64/chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", "src/test/java/drivers/chromedriver-mac-arm64/chromedriver");
        }

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        try {
            // Navigate to the website
            driver.get("https://www.automationanywhere.com/");

            // Maximize the browser window
            driver.manage().window().maximize();

            // Locate the Products menu item
            WebElement productsMenu = driver.findElement(By.xpath("//span[text()='Products']"));

            // Perform mouse over on Products
            Actions actions = new Actions(driver);
            actions.moveToElement(productsMenu).perform();

            // Locate and click on Process Discovery
            WebElement processDiscoveryLink = driver.findElement(By.xpath("//a[text()='Process Discovery']"));
            processDiscoveryLink.click();

            // Verify the URL
            String expectedUrl = "https://www.automationanywhere.com/products/process-discovery";
            String actualUrl = driver.getCurrentUrl();

            if (expectedUrl.equals(actualUrl)) {
                System.out.println("Test Passed: Navigated to the correct URL.");
            } else {
                System.out.println("Test Failed: Navigated to incorrect URL.");
                System.out.println("Actual URL: " + actualUrl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
