package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.PageUtility;

public class Login {
	WebDriver driver;

//	 @FindBy(xpath = "//span")
//	 WebElement textvalue;

	@FindBy(xpath = "//input[@id=\"loginform-username\"] ")
	WebElement username;

	@FindBy(xpath = "//input[@id=\"loginform-password\"] ")
	WebElement password;

	@FindBy(xpath = "//button[@name=\"login-button\"] ")
	WebElement loginButton;

	public Login(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public HomePage LogIn(String uname, String pwd) {

		username.sendKeys(uname);
		password.sendKeys(pwd);
		 loginButton.click();
		return new HomePage(driver);

	}

	//
//	public String readActualText() {
//		System.out.println("ccccccccccccccce");
//		//System.out.println( dashboardtext.getText());
//		return textvalue.getText();
//
//	}
//	

//	public void IsLoaded() {
//	System.out.println("Page title");
//	String expectedtitle = "Sign In";
//	String actualtitle =PageUtility.GetTitle(driver);
//	System.out.println("Actual title is" +PageUtility.GetTitle(driver) );
//	
//	assertEquals(actualtitle, expectedtitle, "Not logged in ...");
//}
//

}
