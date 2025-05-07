package tc_1_21;

import org.testng.annotations.Test;

import ch.qos.logback.core.util.Duration;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC_1_21 {
	WebDriver driver;
    WebDriverWait wait;
    
    public void loginBtn() throws InterruptedException {
    	//Nhấn nút đăng nhập chính
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("OpenIdConnect")));
        loginButton.click();
        Thread.sleep(1000);
        
        // nhấn vào thanh thời khóa biểu
        WebElement thoiKhoaBieu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[1]/div[2]/ul/li[4]/a/span")));
        thoiKhoaBieu.click();
    	Thread.sleep(1000);
    	// trong list chọn IMPORT TKB
    	WebElement importsTKB = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[1]/div[2]/ul/li[4]/ul/li[1]/a/span")));
    	importsTKB.click();
    }
    
    public void robotImport() throws InterruptedException, AWTException {
    	// ấn vào ô "Kéo thả hoặc nhấn chọn để upload".
    	driver.findElement(By.id("dpz-single-file")).click();
    	driver.switchTo().activeElement();
    	
    	StringSelection ss = new StringSelection("D:\\CNTT UIS-ThoiKhoaBieu_TieuChuan_Mau.xlsx");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		Thread.sleep(3000);
	try {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		System.out.println("Import TKB lên trang thành công!");
	} catch (Exception e) {
		System.out.println("Import TKB lên trang thất bại: " + e.getMessage());
	}
    }

    public void openComboBoxHocKy(String textbox1) throws InterruptedException {
        // Nhấp vào dropdown để mở danh sách
        WebElement dropdown1 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".select2-selection--single")));
        dropdown1.click();
        Thread.sleep(1000);

        // Chờ danh sách hiện ra và chọn giá trị phù hợp
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" + textbox1 + "']")));
        option.click();
        Thread.sleep(2000);
    }
    
    public void openComboNganh(String majorName) throws InterruptedException {
        // Nhấp vào dropdown để mở danh sách chọn
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("select2-major-container")));
        dropdown.click();
        Thread.sleep(1000); // Đợi Select2 mở danh sách

        // Chờ danh sách hiển thị và chọn mục mong muốn
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//ul[@id='select2-major-results']/li[text()='" + majorName + "']")
        ));

        // Click vào tùy chọn mong muốn
        option.click();
        Thread.sleep(1000);
    }


    
    public void submitButton() throws InterruptedException {
    	
    	WebElement clickButton1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div[1]/div/div/div[2]/form/div[3]/button")));
    	Thread.sleep(3000);
    	clickButton1.click();
    }
    
    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:\\Users\\ROG\\AppData\\Local\\Microsoft\\Edge\\User Data\\Profile 2");
        options.addArguments("profile-directory=Default");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 120);
        driver.manage().window().maximize();
        driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/Account/Login");
    }
    
    @Test
    public void loginTest() throws InterruptedException, AWTException{
    	loginBtn();
    	//tc3.30
    	System.out.println("- TC_3_30: Test Import TKB Có ấn nút Cập nhật");
    	openComboBoxHocKy("999");
    	Thread.sleep(1000);
    	openComboNganh("Công Nghệ Thông Tin 28");
    	Thread.sleep(1000);
    	robotImport();
    	Thread.sleep(3000);
    	submitButton();
    	WebElement CapNhat = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/div[6]/button[1]")));
    	CapNhat.click();
    	Thread.sleep(3000);
    try {
    	WebElement xacNhanCapNhat = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/div[6]/button[1]")));
    	xacNhanCapNhat.click();
    	System.out.println("Cập nhật thành công.");
    } catch (Exception e) {
    	System.out.println("Cập nhật thất bại: " + e.getMessage());
    }
    	Thread.sleep(3000);
    	WebElement closeThongBao1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/div[6]/button[1]")));
    	closeThongBao1.click();
    	Thread.sleep(6000);
    	WebElement importsTKB = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[1]/div[2]/ul/li[4]/ul/li[1]/a/span")));
    	importsTKB.click();
    	Thread.sleep(3000);
    	
    	//tc3.31
    	System.out.println("- TC_3_31: Test Import TKB Có ấn nút Thay thế");
    	openComboBoxHocKy("999");
    	openComboNganh("Công Nghệ Thông Tin 28");
    	robotImport();
    	Thread.sleep(3000);
    	submitButton();
    	WebElement ThayThe = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/div[6]/button[2]")));
    	ThayThe.click();
    	Thread.sleep(3000);
    try {
    	WebElement xacNhanThayThe = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/div[6]/button[1]")));
    	xacNhanThayThe.click();
    	System.out.println("Thay thế thành công.");
    } catch (Exception e) {
    	System.out.println("Thay thế thất bại: " + e.getMessage());
    }
    	Thread.sleep(3000);
    	WebElement closeThongBao2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/div[6]/button[1]")));
    	closeThongBao2.click();
    	Thread.sleep(6000);
    	WebElement importsTKb = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[1]/div[2]/ul/li[4]/ul/li[1]/a/span")));
    	importsTKb.click();
    	Thread.sleep(3000);
    	
    	//tc3.32
    	System.out.println("- TC_3_32: Test Import TKB Có ấn nút Hủy");
    	openComboBoxHocKy("999");
    	openComboNganh("Công Nghệ Thông Tin 28");
    	robotImport();
    	Thread.sleep(3000);
    	submitButton();
    try {
    	WebElement Huy = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/div[6]/button[3]")));
    	Huy.click();
    	System.out.println("Hủy thành công.");
    } catch (Exception e) {
    	System.out.println("Hủy thất bại: " + e.getMessage());
    }
    	Thread.sleep(3000);
    }
    
    @AfterClass
    public void afterClass() {
        if (driver != null) {
            driver.quit();
        }
    }
}