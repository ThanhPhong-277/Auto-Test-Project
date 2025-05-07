package TC_3_2;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class emailField_3_2 {
  
	private WebDriver driver;
	private WebDriverWait wait;
	
	private void findIframe() {
        WebElement iframeElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("hs-form-iframe-0")));
        driver.switchTo().frame(iframeElement);
	}
	
	private void resetPage() throws InterruptedException {
		driver.get("https://www.inmotionhosting.com/about-us");
		System.out.println("Reseted page");
        Thread.sleep(3000);
	}
	
	private void findNInputEmail(String inputData, boolean expectedValid) throws InterruptedException {
		System.out.println("Nhập email: " + inputData);
		WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='email']")));
        emailField.click();
        emailField.clear();
        Thread.sleep(1000);
        emailField.sendKeys(inputData);
        emailField.submit();
        Thread.sleep(3000);
        
        boolean isErrorDisplayed;
        try {
            WebElement errorElement = driver.findElement(By.xpath("//span[contains(text(),'Please enter a valid email address')]"));
            isErrorDisplayed = errorElement.isDisplayed();
        } catch (Exception e) {
            isErrorDisplayed = false;
        }
        
        System.out.println("Kết quả thực tế: " + (isErrorDisplayed ? "Lỗi hiển thị" : "Không có lỗi"));
        
        try {
            if (expectedValid) {
                assert !isErrorDisplayed : "Test thất bại: Email hợp lệ nhưng báo lỗi.";
            } else {
                assert isErrorDisplayed : "Test thất bại: Email không hợp lệ nhưng không báo lỗi.";
            }
            System.out.println("Testcase thành công!\n");
        } catch (AssertionError e) {
            System.out.println(e.getMessage());
        }
	}
	
	@BeforeTest
	public void beforeTest() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.inmotionhosting.com/about-us");
        Thread.sleep(3000);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}
  
	@Test
	public void f() throws InterruptedException {
		try {
            WebElement acceptCookiesBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("onetrust-accept-btn-handler")));
            acceptCookiesBtn.click();
            System.out.println("Đã chấp nhận cookie!");
        } catch (Exception e) {
            System.out.println("Không tìm thấy popup cookie, bỏ qua...");
        }
		
		findIframe();	
        
        findNInputEmail("le82318@gmail.com", true);
        
        resetPage();
        findIframe();
        findNInputEmail("le82318@fakeemail.com", false);
        
        resetPage();
        findIframe();
        findNInputEmail(",./;'[]", false);
        
        resetPage();
        findIframe();
        findNInputEmail("        ", false);
        
        resetPage();
        findIframe();
        findNInputEmail("", false);
        
        resetPage();
        findIframe();
        findNInputEmail("012291", false);
        
        resetPage();
        findIframe();
        findNInputEmail("le82318@outlook.com.vn", true);
	}

	@AfterTest
	public void afterTest() {
		 driver.quit();
	}
}