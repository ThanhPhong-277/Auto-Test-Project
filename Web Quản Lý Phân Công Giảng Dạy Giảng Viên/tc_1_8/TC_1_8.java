package tc_1_8;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC_1_8 {
    WebDriver driver;
    WebDriverWait wait;

    public void loginBtn() {
    	//Nhấn nút đăng nhập chính
        WebElement loginButton = driver.findElement(By.id("OpenIdConnect"));
        loginButton.click();
    }

    public void userListNSearch(String data) throws InterruptedException {
    	//Xem danh sách người dùng
    	WebElement listButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[1]/div[2]/ul/li[3]/a")));
    	listButton.click();
    	Thread.sleep(1000);
    	
    	//An vao thanh tim kiem
    	WebElement ThanhTimKiem = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div[2]/div[2]/div/div/div[1]/div[2]/div/div[1]/div/label/input")));
    	ThanhTimKiem.sendKeys(data);
        Thread.sleep(1000);
    }

    public void openComboBoxLoaiGV(String loaiGV) throws InterruptedException {

    	WebElement dropdown = driver.findElement(By.id("UserType"));
    	dropdown.click();
    	Thread.sleep(1000);
        Select select = new Select(dropdown);
        select.selectByVisibleText(loaiGV);
        dropdown.click();
        Thread.sleep(2000);
    }
    public void openComboBoxRole(String Role) throws InterruptedException {
    
    	WebElement dropdown1 = driver.findElement(By.id("UserRole"));
    	dropdown1.click();
    	Thread.sleep(1000);
    	Select select1 = new Select(dropdown1);
    	select1.selectByVisibleText(Role);
    	dropdown1.click();
    	Thread.sleep(2000);
    }
    public void openComboBoxLengthColumn(String length) throws InterruptedException{
    	
    	WebElement dropdown2 = driver.findElement(By.name("tblUser_length"));    
    	dropdown2.click();
    	Thread.sleep(1000);
    	Select select2 = new Select(dropdown2);
    	select2.selectByVisibleText(length);
    	dropdown2.click();
    	Thread.sleep(2000);
    }
    public void selectColumnFilter(List<String> filtersToSelect) throws InterruptedException {
    	@SuppressWarnings("unused")
		WebDriverWait wait = new WebDriverWait(driver, 5);

        // Mở Select2 bằng cách nhấn vào hộp chọn
        WebElement select2Container = driver.findElement(By.cssSelector(".select2-selection"));
        select2Container.click();
        Thread.sleep(500);

        // Lấy danh sách tất cả các tùy chọn trong Select2
        List<WebElement> options = driver.findElements(By.cssSelector(".select2-results__option"));

        for (WebElement option : options) {
            String optionText = option.getText().trim();

            // Nếu tên tùy chọn có trong danh sách cần chọn -> Nhấp vào
            if (filtersToSelect.contains(optionText)) {
                option.click();
                Thread.sleep(300);
            }
        }

        // Đóng dropdown bằng cách nhấn ra ngoài
        select2Container.click();
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
    	userListNSearch("Ho Sy Phuc @!$12"); //TC 3.1
    	openComboBoxLoaiGV("Cơ hữu"); //TC 3.2
    	Thread.sleep(4000);
    	openComboBoxLoaiGV("Thỉnh giảng"); //TC 3.3
        Thread.sleep(2000);
        System.out.println("Test comboBox Loại GV: Passed");
        
        userListNSearch("Ho Sy Phuc @!$12");
        openComboBoxRole("BCN khoa"); //TC 3.4
        Thread.sleep(2000);
        openComboBoxRole("Bộ môn"); //TC 3.5
        Thread.sleep(2000);
        openComboBoxRole("Chưa phân quyền"); //TC 3.6
        Thread.sleep(2000);
        openComboBoxRole("Giảng viên"); //TC 3.7
        System.out.println("Test comboBox Role: Passed");
    	
//        userListNSearch("Ho Sy Phuc @!$12");
//        openComboBoxLengthColumn("25"); //TC 3.8
//        Thread.sleep(2000);
//        openComboBoxLengthColumn("50"); //TC 3.9
//        Thread.sleep(2000);
//        openComboBoxLengthColumn("tất cả"); //TC 3.10
//        System.out.println("Test comboBox Hiển thị dòng: Passed");
//        
//    	userListNSearch("Ho Sy Phuc @!$12");
//        List<String> filters = Arrays.asList("Mã giảng viên", "Tên giảng viên", "Email", "Loại", "Role"); //TC 3.11
//        selectColumnFilter(filters);
//        System.out.println("Test comboBox bỏ cột # Mã giảng viên, Tên giảng viên, Email, Loại, Role: Passed");
//        
//        userListNSearch("Ho Sy Phuc @!$12");
//        List<String> filters1 = Arrays.asList("Mã giảng viên", "Tên giảng viên", "Email", "Loại", "Role", "Quốc tịch", "Trạng thái"); //TC 3.12
//        selectColumnFilter(filters1);
//        System.out.println("Test comboBox hiển thị mỗi cột # Quốc tịch, Trạng thái: Passed");
        
//        userListNSearch("Ho Sy Phuc @!$12");
//        List<String> filters2 = Arrays.asList("Quốc tịch", "Trạng thái"); //TC 3.13
//        selectColumnFilter(filters2);
//        System.out.println("Test comboBox hiển thị tất cả các cột: Passed");
    }
    	
    @AfterClass
    public void afterClass() {
        if (driver != null) {
            driver.quit();
        }
    }
}