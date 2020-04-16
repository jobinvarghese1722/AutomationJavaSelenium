package scripts;

import static org.testng.AssertJUnit.assertEquals;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.Branches;
import pages.CompanyProfile;
import pages.HomePage;
import pages.Login;
import utilities.NotepadUtility;
import utilities.PageUtility;

public class Regression extends TestHelper {
	Login login;
	HomePage homePage;
	CompanyProfile companyProfile;
	Branches branches;

	
	
	@Test
	public void VerifyValidLogin() {

		String exptdashtext = "ERP | Dashboards";
		String actdashtext;

		login = new Login(driver);
		homePage = login.LogIn("jobin", "pass123");
		actdashtext = homePage.getWelcomeText();
		Assert.assertEquals(actdashtext, exptdashtext, "Dashboard not loaded.. ...");
		System.out.println("Dashboard loaded!!");

	}

	
	// @Test
	public void VerifyCompanyDropdownOptionsDisplayed() {
		String exptcompanyprofiletext = "Company Profile";
		String actcompanyprofiletext;
		String exptbranchtext = "Branches";
		String actbranchtext;

		login = new Login(driver);
		homePage = login.LogIn("jobin", "pass123");
		homePage.ClickonCompanyIcon();
		actcompanyprofiletext = homePage.getCompanyProfileTextFromDropdown();
		Assert.assertEquals(actcompanyprofiletext, exptcompanyprofiletext,
				"'Company Profile' text IS NOT verified in dropdown!!");
		actbranchtext = homePage.getBranchTextFromDropdown();
		Assert.assertEquals(actbranchtext, exptbranchtext, "'Branches' text IS NOT verified in dropdown!!");

	}

	
	// @Test
	public void VerifyCompanyProfilePageIsLoaded() {

		String expectedcompanytext = "Company";
		List<String> expcompanyprofilepath = new ArrayList<String>();
		// Instantiating list using Collections.addAll()
		Collections.addAll(expcompanyprofilepath, "Home", "Company");

		login = new Login(driver);
		homePage = login.LogIn("jobin", "pass123");
		homePage.ClickonCompanyIcon();
		companyProfile = homePage.ClickOnCompanyProfileLink();
		String actualcompanytext = companyProfile.getCompanyPageWelcomeText();
		Assert.assertEquals(actualcompanytext, expectedcompanytext, "Company Profile Loaded not loaded.. ...");
		// System.out.println("List : " +expcompanyprofilepath.get(0));
		String[] actcompanypath = companyProfile.getCompanyProfilePath();
		for (int i = 0; i < expcompanyprofilepath.size(); i++) {
			Assert.assertEquals(actcompanypath[i], expcompanyprofilepath.get(i),
					"Error in Loading Company Profile Page");

		}
		System.out.println("Company Profile Loaded!!");
	}
	

	// @Test
	public void VerifyBranchesPageIsLoaded() {

		String expectedbranchtext = "Create Branch";
		List<String> expbranchpath = new ArrayList<String>();

		// Instantiating list using Collections.addAll()
		Collections.addAll(expbranchpath, "Home", "Branches", "Create Branch");
		login = new Login(driver);
		homePage = login.LogIn("jobin", "pass123");
		homePage.ClickonCompanyIcon();
		branches = homePage.ClickOnBranchesLink();
		String actualbranchtext = branches.getBranchPageWelcomeText();
		Assert.assertEquals(actualbranchtext, expectedbranchtext, "Branch Page is NOT  loaded.. ...");
		String[] actbranchpath = branches.getBranchPath();
		for (int i = 0; i < expbranchpath.size(); i++) {
			Assert.assertEquals(actbranchpath[i], expbranchpath.get(i), "Error in Loading Branch Page");

		}
		System.out.println("Branch Page is  Loaded!!");
	}
	
	
	// @Test
	public void VerfyValdtnMsgeForCompanyName() {
		String exptectedvalidtnmesge = "Company Name cannot be blank.";
		String actualvalidtnmesge;

		login = new Login(driver);
		homePage = login.LogIn("jobin", "pass123");
		homePage.ClickonCompanyIcon();
		companyProfile = homePage.ClickOnCompanyProfileLink();
		actualvalidtnmesge = companyProfile.getCompanyNameValidationMessage();
		Assert.assertEquals(actualvalidtnmesge, exptectedvalidtnmesge, "Proper validation is missing");
		System.out.println("Validation for Mandatory field is done successfully!!");
	}
	
	
	// @Test
	public void VerifyCreationofNewCompany() {
		String companyname = "Huwaeiii";
		String companyemail = "test@gmail.com";
		String companyaddress = "Wuhang, Effel Towers, China";

		List<String> expcompanyid = new ArrayList<String>();
		// Instantiating list using Collections.addAll()
		Collections.addAll(expcompanyid, "Huwaeiii");
		login = new Login(driver);
		homePage = login.LogIn("jobin", "pass123");
		homePage.ClickonCompanyIcon();
		companyProfile = homePage.ClickOnCompanyProfileLink();
		companyProfile.enterNewCompanyDetails(companyname, companyemail, companyaddress);
		branches = companyProfile.clickOnBranchTab();
		String[] actualcompanyid = branches.getCompanyid();
		for (int i = 0; i < actualcompanyid.length; i++) {
			Assert.assertEquals(actualcompanyid[i], expcompanyid.get(0), "Error in Creating new Company");
		}
		System.out.println("New  Company is Created Successfully!!");

	}



	// @Test
	public void CheckTableheaderOfBranchPage() {
		String expectedtableheader[] = { "#", "Company ID", "Branch Name", "Status" };
		login = new Login(driver);

		homePage = login.LogIn("jobin", "pass123");
		homePage.ClickonCompanyIcon();
		branches = homePage.ClickOnBranchesLink();
		String actualtableheaders[] = branches.gettableheaders();

		for (int i = 0; i < actualtableheaders.length - 1; i++) {
			Assert.assertEquals(actualtableheaders[i], expectedtableheader[i], "Error in table header fields");

		}

//		for(int i=0;i<tablehead.length;i++) {
//			System.out.println("value is " + tablehead[i]);
//		}

	}

	
	//@Test
	public void VerifyEditBranchesPageIsLoaded() {
		String expectedupdateformfields[] = { "Company ID", "Branch Name", "Email", "Phone", "Mobile", "Gst No:",
				"Address1", "Address2", "State", "Pincode" };
		login = new Login(driver);

		homePage = login.LogIn("jobin", "pass123");
		homePage.ClickonCompanyIcon();
		branches = homePage.ClickOnBranchesLink();
		branches.clickOnupdateBranchDetails();

		String actualupdateformfields[] = branches.getUpdateBarnchFormFields();
		for (int i = 0; i < expectedupdateformfields.length; i++) {
			Assert.assertEquals(actualupdateformfields[i], expectedupdateformfields[i],
					"Error in loading update branch page");

		}

	}
	
	//@Test
	
	public void VerifyCoresspondingBranchNameLoaded() {
		
		login = new Login(driver);

		homePage = login.LogIn("jobin", "pass123");
		homePage.ClickonCompanyIcon();
		branches = homePage.ClickOnBranchesLink();
		branches.clickOnupdateBranchDetails();
		String expectedbranchname=branches. getExpectedCoresspondingBranchNameHeader();
		String actualbranchname=branches. getActualCoresspondingBranchNameHeader();
		 Assert.assertEquals(actualbranchname,expectedbranchname,"Error in validating corresponding Branch name");
		
	}
	
	
	//@Test
	
	public void VerifyUpdateBranchPage() {
		
		login = new Login(driver);

		homePage = login.LogIn("jobin", "pass123");
		homePage.ClickonCompanyIcon();
		branches = homePage.ClickOnBranchesLink();
		branches.clickOnupdateBranchDetails();
		branches.enterUpdatedDetails("Tvmm","abc@gmail.com","0471256412","9945213871","1452","Asiatic Building","Technopark","Kerala","65124").ClickonSaveButtoninUpdateBranchPage();
		List<String> testing =branches.getUpdatedBranchDetails();
		
		
		//System.out.println(testing);
		
		for (int i = 0; i < testing.size(); i++) {
			System.out.println("hello world");
			System.out.println("value is "+ testing.get(i));
		}
		
	}
	
	
	

}
