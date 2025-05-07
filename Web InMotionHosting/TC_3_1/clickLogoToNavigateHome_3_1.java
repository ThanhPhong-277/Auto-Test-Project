package TC_3_1;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class clickLogoToNavigateHome_3_1 {
    WebDriver driver;
    WebDriverWait wait;
    
    @Test
    public void Navigate() {
        // Click vào logo
        driver.findElement(By.xpath("//img[@alt='InMotion Hosting Logo']")).click();
        
        // Chờ cho đến khi URL thay đổi về trang chủ
        wait.until(ExpectedConditions.urlToBe("https://www.inmotionhosting.com/"));

        // Lấy URL hiện tại
        String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://www.inmotionhosting.com/";

        // In kết quả ra console
        System.out.println("\n Expected URL: " + expectedURL);
        System.out.println("\n Actual URL: " + actualURL);

        // Kiểm tra kết quả
        Assert.assertEquals(actualURL, expectedURL, "Navigation failed!");
    }

    @BeforeTest
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.inmotionhosting.com/domains");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterTest
    public void afterTest() {
        if (driver != null) {
            driver.quit(); // Đóng toàn bộ trình duyệt
        }
    }
}
