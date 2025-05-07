package T_3_4;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;

//import java.io.IOException;
import java.time.Duration;

//import org.json.simple.parser.ParseException;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class dataDriver_3_4 {
  
	private WebDriver driver;
	
	public String getPackagePath() {
	    String className = this.getClass().getName(); // Lấy tên đầy đủ của class (bao gồm package)
	    String packageName = className.substring(0, className.lastIndexOf(".")); // Cắt phần package
	    return packageName.replace(".", "/"); // Chuyển dấu `.` thành `/` để tạo đường dẫn
	}
	
	// 📌 Phương thức đọc file JSON bằng JSON Simple
	public List<String> readExpectedOptionsFromJson(String fileName) {
	    List<String> expectedOptions = new ArrayList<>();
	    try {
	        // Đường dẫn tương đối đến thư mục data
	    	String packagePath = getPackagePath();
	    	String filePath = "src/" + packagePath + "/data/" + fileName;
	        File file = new File(filePath); 
	        
	        if (!file.exists()) {
	            System.out.println("❌ File JSON không tồn tại: " + file.getAbsolutePath());
	            return expectedOptions;
	        }

	        JSONParser jsonParser = new JSONParser();
	        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(file));

	        JSONArray jsonArray = (JSONArray) jsonObject.get("expectedResults");
	        for (Object item : jsonArray) {
	            expectedOptions.add((String) item);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return expectedOptions;
	}
	
	public void acceptCookies() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement cookie = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.id("onetrust-banner-sdk")
		));
		
		WebElement acceptBtn = cookie.findElement(By.id("onetrust-accept-btn-handler"));
		acceptBtn.click();
		
		try {
            WebElement newContentBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("onesignal-slidedown-cancel-button")));
            newContentBtn.click();
            System.out.println("Đã hủy nhận thông báo nội dung mới!");
        } catch (Exception e) {
            System.out.println("Không tìm thấy thông báo, bỏ qua...");
        }
		
	}

	@BeforeTest
	public void beforeTest() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.inmotionhosting.com/support/product-guides/minecraft/");
	}

	@Test(priority = 1)
	public void testTitleOfItemMinecraft() throws InterruptedException {
		 acceptCookies();
		 Thread.sleep(1000);
        // 📌 Đọc danh sách mong đợi từ file JSON
		List<String> expectedOptions = readExpectedOptionsFromJson("TitleOfItemMinecraft.json");

        // Tìm tất cả các mục con trong dropdown
        List<WebElement> title = driver.findElements(By.xpath("/html/body/div[3]/main/div/div[2]/div[1]/article[1]/header/h2/a"));
        
        System.out.println("Tên tiêu đề hướng dẫn của mục Minecraft:");
        
        List<String> actualOptions = new ArrayList<>();
        
        // Lặp qua danh sách các mục và lấy text
        for (WebElement item : title) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String text = (String) js.executeScript("return arguments[0].textContent;", item);
            actualOptions.add(text.trim()); // Thêm vào danh sách thực tế
            System.out.println("- " + text.trim());
        }

        // So sánh danh sách thực tế với mong đợi
        if (actualOptions.equals(expectedOptions)) {
            System.out.println("✅ Kết quả đúng: Tên tiêu đề khớp với mong đợi!");
        } else {
            System.out.println("❌ Kết quả sai! Tên tiêu đề thực tế không khớp với mong đợi.");
            System.out.println("Expected: " + expectedOptions);
            System.out.println("Actual: " + actualOptions);
        }

        Thread.sleep(2000);
    }
	
	@Test(priority = 2)
	public void testDescription() throws InterruptedException {
        // 📌 Đọc danh sách mong đợi từ file JSON
		List<String> expectedOptions = readExpectedOptionsFromJson("Description.json");

        // Tìm tất cả các mục con trong dropdown
        List<WebElement> title = driver.findElements(By.xpath("/html/body/div[3]/main/div/div[2]/div[1]/header/div/p"));
        
        System.out.println("Nội dung mô tả của mục Minecraft:");
        
        List<String> actualOptions = new ArrayList<>();
        
        // Lặp qua danh sách các mục và lấy text
        for (WebElement item : title) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String text = (String) js.executeScript("return arguments[0].textContent;", item);
            actualOptions.add(text.trim()); // Thêm vào danh sách thực tế
            System.out.println("- " + text.trim());
        }

        // So sánh danh sách thực tế với mong đợi
        if (actualOptions.equals(expectedOptions)) {
            System.out.println("✅ Kết quả đúng: Nội dung mô tả khớp với mong đợi!");
        } else {
            System.out.println("❌ Kết quả sai! Nội dung mô tả thực tế không khớp với mong đợi.");
            System.out.println("Expected: " + expectedOptions);
            System.out.println("Actual: " + actualOptions);
        }

        Thread.sleep(2000);
    }

	@Test(priority = 3)
	public void testContent() throws InterruptedException {
        // 📌 Đọc danh sách mong đợi từ file JSON
		List<String> expectedOptions = readExpectedOptionsFromJson("Content.json");

        // Tìm tất cả các mục con trong dropdown
        List<WebElement> title = driver.findElements(By.xpath("/html/body/div[3]/main/div/div[2]/div[1]/article[1]/div/p"));
        
        System.out.println("Nội dung trong How to Add a Modpack to your Minecraft Server:");
        
        List<String> actualOptions = new ArrayList<>();
        
        // Lặp qua danh sách các mục và lấy text
        for (WebElement item : title) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String text = (String) js.executeScript("return arguments[0].textContent;", item);
            actualOptions.add(text.trim()); // Thêm vào danh sách thực tế
            System.out.println("- " + text.trim());
        }

        // So sánh danh sách thực tế với mong đợi
        if (actualOptions.equals(expectedOptions)) {
            System.out.println("✅ Kết quả đúng: Nội dung khớp với mong đợi!");
        } else {
            System.out.println("❌ Kết quả sai! Nội dung thực tế không khớp với mong đợi.");
            System.out.println("Expected: " + expectedOptions);
            System.out.println("Actual: " + actualOptions);
        }

        Thread.sleep(2000);
    }
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}

