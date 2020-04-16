package scripts;

import java.io.File;
import java.io.FileNotFoundException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utilities.GenericUtility;
import utilities.NotepadUtility;

public class TestHelper {

	WebDriver driver;

	@BeforeMethod
	public void launchBrowser() throws FileNotFoundException {

		System.setProperty("webdriver.chrome.driver", "C:\\Driver\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		String path = "D:\\Testingg\\EclipseWorkSpace\\qabible-erptest\\src\\test\\resources\\qaenvironment";
		String url = NotepadUtility.ReadNotepad(path);
		driver.get(url);

	}

//	@AfterMethod
//	public void closeBrowser()
//{
//		driver.quit();
//	}
//  
// 
	
	@AfterMethod
    public void tearDown (ITestResult result)
    {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                TakesScreenshot ts = (TakesScreenshot) driver;
                File source = ts.getScreenshotAs(OutputType.FILE);
                GenericUtility.copyFileUsingStream(source, new File("./target/" + result.getName() + ".png"));
                System.out.println("Screenshot taken");
            } catch (Exception e) {
                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        }
        closeBrowser();
    }

    public void closeBrowser() {
        driver.quit();
    }
	
	
	

}
