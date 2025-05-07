package TC_3_3;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class imageCount_3_3 {
  
    private WebDriver driver;
    private WebDriverWait wait;

    private void checkImages(int expectedCount) throws InterruptedException {
        WebElement entryContent = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.entry-content")));
        List<WebElement> images = entryContent.findElements(By.tagName("img"));
        Thread.sleep(2000);
        
        int actualCount = images.size();
        System.out.println("🔹 **Expected Image Count:** " + expectedCount);
        System.out.println("🔹 **Actual Image Count:** " + actualCount);
        
        if (actualCount == expectedCount) {
            System.out.println("✅ Test Passed!");
        } else {
            System.out.println("❌ Test Failed! Số lượng ảnh không khớp.");
        }
    }
        
    private void clickPrevious() {
        WebElement previousButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.nav-previous")));
        previousButton.click();
    }

    public void clickMinecraft(String path) {
        WebElement minecraftButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(path)));
        minecraftButton.click();
    }
    
    @BeforeTest
    public void beforeTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.inmotionhosting.com/support/product-guides/minecraft/");
        Thread.sleep(3000);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testImages() throws InterruptedException {
        try {
            WebElement acceptCookiesBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("onetrust-accept-btn-handler")));
            acceptCookiesBtn.click();
            System.out.println("✅ Đã chấp nhận cookie!");
        } catch (Exception e) {
            System.out.println("⚠ Không tìm thấy popup cookie, bỏ qua...");
        }
        
        try {
            WebElement newContentBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("onesignal-slidedown-cancel-button")));
            newContentBtn.click();
            System.out.println("✅ Đã hủy nhận thông báo nội dung mới!");
        } catch (Exception e) {
            System.out.println("⚠ Không tìm thấy thông báo, bỏ qua...");
        }
        
        WebElement entrytittle = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/main/div/div[2]/div[1]/article[1]/header/h2/a")));
        entrytittle.click();
        
        // Testcase 3.9: Add a Modpack to Your Server
        System.out.println("🔹 **Testcase 3.9: Add a Modpack to Your Server**");
        checkImages(12);
        clickPrevious();
        
        // Testcase 3.10: How to start your Minecraft Server with a Custom JAR
        System.out.println("🔹 **Testcase 3.10: How to start your Minecraft Server with a Custom JAR**");
        checkImages(11);
        clickPrevious();
        
        // Testcase 3.11: How to Connect to your Minecraft Server
        System.out.println("🔹 **Testcase 3.11: How to Connect to your Minecraft Server**");
        checkImages(5);
        clickPrevious();
        
        // Testcase 3.12: How to Set a Custom World Seed on your Minecraft Server
        System.out.println("🔹 **Testcase 3.12: How to Set a Custom World Seed on your Minecraft Server**");
        checkImages(5);
        clickPrevious();
        
        // Testcase 3.13: How to Create a New World on your Minecraft Server
        System.out.println("🔹 **Testcase 3.13: How to Create a New World on your Minecraft Server**");
        checkImages(4);
        driver.get("https://www.inmotionhosting.com/support/product-guides/minecraft/");
        Thread.sleep(3000);
        
        // Testcase 3.14: Minecraft Game Panel – Using Plugins
        WebElement entrytittle2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/main/div/div[2]/div[1]/article[6]/header/h2/a")));
        entrytittle2.click();
        System.out.println("🔹 **Testcase 3.14: Minecraft Game Panel – Using Plugins**");
        checkImages(7);
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}