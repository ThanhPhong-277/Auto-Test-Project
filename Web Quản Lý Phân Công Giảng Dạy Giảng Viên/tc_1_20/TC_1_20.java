package tc_1_20;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

import java.util.ArrayList;
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

@SuppressWarnings("unused")
public class TC_1_20 {
	WebDriver driver;
    WebDriverWait wait;    
    
    public void openComboBoxLengthTerm(String length) throws InterruptedException{
    	try {
    	WebElement dropdown = driver.findElement(By.name("tblMajor_length"));    
    	dropdown.click();
    	Thread.sleep(1000);
    	Select select = new Select(dropdown);
    	select.selectByVisibleText(length);
    	dropdown.click();
    		System.out.println("✅ Sử dụng bộ lọc số dòng thành công ✅");
    	}catch(Exception e) {
    		System.out.println("❌ Sử dụng bộ lọc số dòng thất bại: " + e.getMessage());
    	}
    	Thread.sleep(2000);
    }
    
    public void loginBtn() {
    	//Nhấn nút đăng nhập chính
        WebElement loginButton = driver.findElement(By.id("OpenIdConnect"));
        loginButton.click();
    }
    
    public void search(String input, List<String> expectedResults) throws InterruptedException {
    	
    	// Nhập từ khóa tìm kiếm
    	//Ấn vào học kỳ và ngành
    	WebElement listButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, '/Phancong02/Term') and span[text()='Học kỳ và ngành']]")));
    	listButton.click();
    	Thread.sleep(1000);
    	
    	//Xem danh sách ngành
    	WebElement switchbutton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/ul/li[2]/a")));
    	switchbutton.click();
    	Thread.sleep(1000);
    	
        WebElement searchBox = driver.findElement(By.cssSelector("#tblMajor_filter input"));
        searchBox.clear();
        searchBox.sendKeys(input);
        Thread.sleep(2000);

        // Lấy dữ liệu bảng
        List<WebElement> noResultElements = driver.findElements(By.cssSelector("td.dataTables_empty"));
        List<String> actualResults = new ArrayList<>();

        if (!noResultElements.isEmpty()) {
            String message = noResultElements.get(0).getText();
            System.out.println("⚠️ Nội dung thông báo: " + message);
            actualResults.add(message);
        } else {
            // Lấy dữ liệu bảng
            WebElement table = driver.findElement(By.id("tblMajor"));
            List<WebElement> rows = table.findElements(By.tagName("tr"));

            if (rows.size() > 1) {
                WebElement firstRow = rows.get(1);
                List<WebElement> columns = firstRow.findElements(By.tagName("td"));
                
                if (columns.size() > 1) {
                    String maNganh = columns.get(1).getText();
                    actualResults.add(maNganh);
                    
                    // So sánh kết quả mong đợi với kết quả thực tế
                    if (expectedResults.equals(actualResults)) {
                    	System.out.println("✅ Kết quả giống nhau: Mong đợi." + expectedResults + " | Thực tế: " + actualResults);
                    } else {
                        System.out.println("❌ Kết quả không khớp: " + expectedResults + " | Thực tế: " + actualResults);
                    }
                }
            }
        }
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
    	Thread.sleep(2000);

    	//tc3.23
    	System.out.println("- Nhập mỗi số:");
    	search("0000000000001", Arrays.asList("0000000000001"));
    	
//    	//tc3.24
//    	System.out.println("- Nhập mỗi chữ:");
//    	search("ABC", Arrays.asList("ABC"));
//    	
//    	//tc3.25
//    	System.out.println("- Nhập mỗi ký tự đặc biệt:");
//    	search("!@#^&&*()_+", Arrays.asList("!@#^&&*()_+"));
//    	
//    	//tc3.26
//    	System.out.println("- Nhập cả chữ, số, ký tự đặc biệt:");
//    	search("asd234@@!$%%", Arrays.asList("asd234@@!$%%"));
//    	
//    	//tc3.27
//    	System.out.println("- Test nhập mỗi chữ kèm theo bộ lọc Hiển thị 25 dòng:");
//    	search("Kiểm Thử Tự Động", Arrays.asList("Kiểm Thử Tự Động"));
//    	openComboBoxLengthTerm("25");
//    	
//    	//tc3.28
//    	System.out.println("- Test nhập mỗi chữ kèm theo bộ lọc Hiển thị 50 dòng:");
//    	search("Kiểm Thử Tự Động", Arrays.asList("Kiểm Thử Tự Động"));
//    	openComboBoxLengthTerm("50");
    	
    	//tc3.29
    	System.out.println("- Test nhập mỗi chữ kèm theo bộ lọc Hiển thị tất cả dòng:");
    	search("Kiểm Thử Tự Động", Arrays.asList("Kiểm Thử Tự Động"));
    	openComboBoxLengthTerm("tất cả");
    }
    
    @AfterClass
    public void afterClass() {
        if (driver != null) {
            driver.quit();
        }
    }
}