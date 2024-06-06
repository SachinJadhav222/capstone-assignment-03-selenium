import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class Selenium_Test02 {
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

            // Click on Request Demo button
            WebElement requestDemoButton = driver.findElement(By.xpath("//a[@href='/request-live-demo' and contains(text(),'Request Demo')]"));
            requestDemoButton.click();

            // Verify the URL
            String expectedUrl = "https://www.automationanywhere.com/request-live-demo";
            String actualUrl = driver.getCurrentUrl();
            if (expectedUrl.equals(actualUrl)) {
                System.out.println("Test Passed: Navigated to the correct URL.");
            } else {
                System.out.println("Test Failed: Navigated to incorrect URL.");
                System.out.println("Actual URL: " + actualUrl);
            }

            // Verify the Label Names for First Name and Last Name
            WebElement firstNameLabel = driver.findElement(By.xpath("//label[contains(text(), 'First Name')]"));
            WebElement lastNameLabel = driver.findElement(By.xpath("//label[contains(text(), 'Last Name')]"));

            if (firstNameLabel != null && firstNameLabel.getText().contains("First Name")) {
                System.out.println("First Name label is correct.");
            } else {
                System.out.println("First Name label is incorrect.");
            }

            if (lastNameLabel != null && lastNameLabel.getText().contains("Last Name")) {
                System.out.println("Last Name label is correct.");
            } else {
                System.out.println("Last Name label is incorrect.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
