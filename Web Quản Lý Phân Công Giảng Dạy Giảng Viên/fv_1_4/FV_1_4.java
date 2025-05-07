package fv_1_4;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class FV_1_4 {
    WebDriver driver;
    
    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:\\Users\\ROG\\AppData\\Local\\Microsoft\\Edge\\User Data\\Profile 2");
        options.addArguments("profile-directory=Profile 1");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/Account/Login");
    }
    
    @Test
    public void loginTest() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        
    	// Nhấn nút đăng nhập chính
        WebElement loginButton = driver.findElement(By.id("OpenIdConnect"));
        loginButton.click();
        
        //Xem danh sách người dùng
    	WebElement listButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[1]/div[2]/ul/li[3]/a")));
    	listButton.click();
        
        // Gọi phương thức lấy thông tin trang sau khi đã đăng nhập và mở danh sách
        extractPageInfo();
    }
    
    public void extractPageInfo() {
        // Lấy và in tiêu đề trang
        WebElement pageTitle = driver.findElement(By.xpath("//h4[contains(text(),'Quản lý người dùng')]"));
        if (pageTitle != null) {
            System.out.println("Tiêu đề trang: " + pageTitle.getText());
        } else {
            System.out.println("Không tìm thấy tiêu đề trang");
        }
        
        // Lấy và in các giá trị trong dropdown Loại giảng viên
        WebDriverWait wait = new WebDriverWait(driver, 15);
        if (driver.findElements(By.id("UserType")).size() > 0) {
            WebElement userTypeElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("UserType")));
            Select userTypeDropdown = new Select(userTypeElement);
            List<WebElement> userTypeOptions = userTypeDropdown.getOptions();
            System.out.println("Danh sách Loại giảng viên:");
            for (WebElement option : userTypeOptions) {
                System.out.println("- " + option.getText().trim());
            }
        } else {
            System.out.println("Không tìm thấy dropdown Loại giảng viên");
        }
        
        // Lấy và in các giá trị trong dropdown Vai trò người dùng
        if (driver.findElements(By.id("UserRole")).size() > 0) {
            WebElement userRoleElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("UserRole")));
            Select userRoleDropdown = new Select(userRoleElement);
            List<WebElement> userRoleOptions = userRoleDropdown.getOptions();
            System.out.println("Danh sách Vai trò người dùng:");
            for (WebElement option : userRoleOptions) {
                System.out.println("- " + option.getText().trim());
            }
        } else {
            System.out.println("Không tìm thấy dropdown Vai trò người dùng");
        }
        
        // Lấy toàn bộ văn bản trên trang
        String pageText = driver.findElement(By.tagName("body")).getText();
        System.out.println("Toàn bộ nội dung trang:");
        System.out.println(pageText);
    }
    
    @AfterClass
    public void afterClass() {
        if (driver != null) {
            driver.quit();
        }
    }
}