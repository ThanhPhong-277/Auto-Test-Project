package tc_1_15;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;

public class TC_1_15 {
    WebDriver driver;
    
    @BeforeClass
    public void setup() {
    	System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
		options.addArguments("user-data-dir=C:\\Users\\ROG\\AppData\\Local\\Microsoft\\Edge\\User Data\\Profile 2");
		options.addArguments("profile-directory=Default");
 		driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/Account/Login");
    }
    
    @Test
    public void loginTest() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        
    	// Nhấn nút đăng nhập chính
        WebElement loginButton = driver.findElement(By.id("OpenIdConnect"));
        loginButton.click();
        
        // Xem học kỳ
    	WebElement listButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[1]/div[2]/ul/li[2]/a/span")));
    	listButton.click();
    	
    	//Khóa người dùng 1
    	WebElement khoaNguoiDung1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/table/tbody/tr[1]/td[8]/div/input")));
    	khoaNguoiDung1.click();
    	System.out.println("Đã khóa người dùng 1");
    	Thread.sleep(1000);
    	
    	//Khóa người dùng 2
    	WebElement khoaNguoiDung2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/table/tbody/tr[2]/td[8]/div/input")));
    	khoaNguoiDung2.click();
    	System.out.println("Đã khóa người dùng 2");
    	Thread.sleep(1000);
    	
    	//Khóa người dùng 3
    	WebElement khoaNguoiDung3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/table/tbody/tr[3]/td[8]/div/input")));
    	khoaNguoiDung3.click();
    	System.out.println("Đã khóa người dùng 3");
    	Thread.sleep(1000);
    }
    	
    
    @AfterClass
    public void afterClass() {
        if (driver != null) {
        	driver.quit();
        }
    }
}