package TC_3_6;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
//import org.testng.Assert;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// Class chứa email và kết quả mong đợi
class TestCaseData {
    public String email;
    public boolean expectedValid;
}

public class emailFieldWithJSON_3_6 {
  
	private WebDriver driver;
	private WebDriverWait wait;
	private List<TestCaseData> testCases;

	// Đọc JSON
	private void loadTestCases() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        testCases = objectMapper.readValue(new File("src\\TC_3_6\\data\\testCasesEmail.json"),
                objectMapper.getTypeFactory().constructCollectionType(List.class, TestCaseData.class));
    }

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
        
        // Kết quả mong đợi so với thực tế
        boolean actualValid = !isErrorDisplayed;
        boolean testPassed = (expectedValid == actualValid);
        
        // In kết quả theo định dạng chuẩn
        System.out.println("Kết quả mong đợi: " + (expectedValid ? "✅ Không có lỗi" : "❌ Lỗi hiển thị"));
        System.out.println("Kết quả thực tế: " + (isErrorDisplayed ? "❌ Lỗi hiển thị" : "✅ Không có lỗi"));
        System.out.println("Test Result: " + (testPassed ? "✅ Passed" : "❌ Failed"));

        // Nếu test thất bại, chỉ in thông báo lỗi nhưng không dừng test
        if (!testPassed) {
            System.out.println("⚠️ Lỗi: Kết quả không khớp với mong đợi!");
        }

        System.out.println("-------------------------------------------------\n");
	}

	@BeforeTest
	public void beforeTest() throws InterruptedException, IOException {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.inmotionhosting.com/about-us");
        Thread.sleep(3000);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        loadTestCases();
	}

	@Test
	public void runTests() throws InterruptedException {
		try {
            WebElement acceptCookiesBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("onetrust-accept-btn-handler")));
            acceptCookiesBtn.click();
            System.out.println("Đã chấp nhận cookie!");
        } catch (Exception e) {
            System.out.println("Không tìm thấy popup cookie, bỏ qua...");
        }
		
		for (TestCaseData testCase : testCases) {
			resetPage();
			findIframe();
			
			try {
				findNInputEmail(testCase.email, testCase.expectedValid);
			} catch (Exception e) {
				System.out.println("⚠️ Lỗi khi test email: " + testCase.email);
				e.printStackTrace();
			}
		}
	}

	@AfterTest
	public void afterTest() {
		 driver.quit();
	}
}
