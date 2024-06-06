import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestNG_Test01 {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        boolean isWindowsPlatform = System.getProperty("os.name").contains("Windows");
        // Set the path to your ChromeDriver
        if (isWindowsPlatform) {
            System.setProperty("webdriver.chrome.driver", "src/test/java/drivers/chromedriver-win64/chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", "src/test/java/drivers/chromedriver-mac-arm64/chromedriver");
           }


        // Initialize WebDriver
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void navigationTest() {
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
    }

    @Test
    public void testRequestDemo() {
        // Navigate to the website

        driver.get("https://www.automationanywhere.com/");

        // Click on Request Demo button
        WebElement requestDemoButton = driver.findElement(By.xpath("//a[@href='/request-live-demo' and contains(text(),'Request Demo')]"));
        requestDemoButton.click();

        // Verify the URL
        String expectedUrl = "https://www.automationanywhere.com/request-live-demo";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Navigated to incorrect URL.");

        // Verify the Label Names for First Name and Last Name
        WebElement firstNameLabel = driver.findElement(By.xpath("//label[contains(text(), 'First Name')]"));
        WebElement lastNameLabel = driver.findElement(By.xpath("//label[contains(text(), 'Last Name')]"));

        Assert.assertTrue(firstNameLabel.getText().contains("First Name"), "First Name label is incorrect.");
        Assert.assertTrue(lastNameLabel.getText().contains("Last Name"), "Last Name label is incorrect.");
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
