package TC_3_5;

import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.BeforeClass;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class fieldAndViewPopUp_3_5 {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void beforeTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.inmotionhosting.com/support/product-guides/minecraft/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test
    public void checkPopUp() {
        try {
            // Chờ PopUp xuất hiện
            WebElement popUp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("normal-slidedown")));
            Assert.assertNotNull(popUp, "\n ❌ [FAILED] PopUp không hiển thị!");
            System.out.println(" ✅ [SUCCESS] PopUp hiển thị thành công.");

            // Kiểm tra nội dung thông báo
            WebElement message = popUp.findElement(By.className("slidedown-body-message"));
            String actualMessage = message.getText();
            String expectedMessage = "Subscribe to our notifications for the latest news and updates. You can disable anytime.";

            System.out.println("\n 🔹 [EXPECT] Nội dung PopUp mong đợi: '" + expectedMessage + "'");
            System.out.println(" ✅ [ACTUAL] Nội dung PopUp thực tế: '" + actualMessage + "'");

            // So sánh nội dung PopUp
            if (actualMessage.equals(expectedMessage)) {
                System.out.println(" 🎯 [CONFIRMED] Kết quả thực tế đúng như mong đợi!");
            } else {
                System.out.println(" ❌ [ERROR] Nội dung PopUp không đúng!");
            }
            Assert.assertEquals(actualMessage, expectedMessage, "❌ [ERROR] Nội dung PopUp không đúng!");
            
            // Kiểm tra tên nút "Subscribe"
            WebElement subscribeButton = popUp.findElement(By.id("onesignal-slidedown-allow-button"));
            String actualButton1 = subscribeButton.getText();
            String expectedButton1 = "Subscribe";
            System.out.println("\n 🔹 [EXPECT] Tên button mong đợi: '" + expectedButton1 + "'");
            System.out.println(" ✅ [ACTUAL] Tên nút thực tế: '" + actualButton1 + "'");

            // So sánh tên nút "Subscribe"
            if (actualButton1.equals(expectedButton1)) {
                System.out.println(" 🎯 [CONFIRMED] Tên nút 'Subscribe' đúng như mong đợi!");
            } else {
                System.out.println(" ❌ [ERROR] Tên nút 'Subscribe' không đúng!");
            }
            Assert.assertEquals(actualButton1, expectedButton1, "❌ [ERROR] Tên nút 'Subscribe' không đúng!");

            // Kiểm tra tên nút "Later"
            WebElement laterButton = popUp.findElement(By.id("onesignal-slidedown-cancel-button"));
            String actualButton2 = laterButton.getText();
            String expectedButton2 = "Later";

            System.out.println("\n 🔹 [EXPECT] Tên button mong đợi: '" + expectedButton2 + "'");
            System.out.println(" ✅ [ACTUAL] Tên nút thực tế: '" + actualButton2 + "'");

            // So sánh tên nút "Later"
            if (actualButton2.equals(expectedButton2)) {
                System.out.println(" 🎯 [CONFIRMED] Tên nút 'Later' đúng như mong đợi!");
            } else {
                System.out.println(" ❌ [ERROR] Tên nút 'Later' không đúng!");
            }
            Assert.assertEquals(actualButton2, expectedButton2, "❌ [ERROR] Tên nút 'Later' không đúng!");

        } catch (Exception e) {
            System.out.println("❌ [FAILED] Không tìm thấy PopUp! Lỗi: " + e.getMessage());
            Assert.fail("❌ [ERROR] PopUp không hiển thị!");
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}