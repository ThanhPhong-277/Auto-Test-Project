package tc_1_14;

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
//import org.testng.Assert;

public class TC_1_14 {
    WebDriver driver;
    WebDriverWait wait;
    
    public void termNSearch(String term) throws InterruptedException {
    	//Xem danh sách học kỳ
    	WebElement listButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[1]/div[2]/ul/li[2]/a")));
    	listButton.click();
    	Thread.sleep(1000);
    	
    	//An vao thanh tim kiem
    	WebElement ThanhTimKiem = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/div[1]/div[2]/div/div[1]/div/label/input")));
    	ThanhTimKiem.sendKeys(term);
        Thread.sleep(1000); 
    }
    
    public void openComboBoxLengthTerm(String length) throws InterruptedException{
    	WebElement dropdown = driver.findElement(By.name("tblTerm_length"));    
    	dropdown.click();
    	Thread.sleep(1000);
    	Select select = new Select(dropdown);
    	select.selectByVisibleText(length);
    	dropdown.click();
    	Thread.sleep(2000);
    }
    
    public void loginBtn() {
    	//Nhấn nút đăng nhập chính
        WebElement loginButton = driver.findElement(By.id("OpenIdConnect"));
        loginButton.click();
    }
    
    @BeforeClass
    public void setup() {
    	System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
		options.addArguments("user-data-dir=C:\\Users\\ROG\\AppData\\Local\\Microsoft\\Edge\\User Data\\Profile 2");
		options.addArguments("profile-directory=Default");
 		driver = new ChromeDriver(options);
 		wait = new WebDriverWait(driver, 60);
        driver.manage().window().maximize();
        driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/Account/Login");
    }

    @Test
    public void loginTest() throws InterruptedException {
    	loginBtn();
    	termNSearch("999"); //tc3.15
    	System.out.println("Nhập số học kỳ: Passed");
//    	termNSearch("11/6/2024"); //tc3.16
//    	System.out.println("Nhập ngày tháng năm: Passed");
//    	termNSearch("ascv"); //tc3.17
//    	System.out.println("Nhập mỗi chữ: Passed");
//    	termNSearch("ascv12345"); //tc3.18
//    	System.out.println("Nhập cả chữ và số: Passed");
//    	
//    	termNSearch("999"); //tc3.19
//    	openComboBoxLengthTerm("25");
//    	System.out.println("Test comboBox Hiển thị 25 dòng: Passed");
//    	Thread.sleep(1000);
//    	openComboBoxLengthTerm("50"); //tc3.20
//    	System.out.println("Test comboBox Hiển thị 50 dòng: Passed");
//    	Thread.sleep(1000);
    	openComboBoxLengthTerm("tất cả"); //tc3.21
    	System.out.println("Test comboBox Hiển thị tất cả dòng: Passed");
    }
    
    @AfterClass
    public void afterClass() {
        if (driver != null) {
            driver.quit();
        }
    }
}