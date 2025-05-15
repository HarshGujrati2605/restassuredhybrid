package testlib.admissions;

import java.io.IOException;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import AppLib.pagelib.LoginPage;
import CoreLib.DriverUtil.WebDriverSingleton;
import CoreLib.Excelutil.ExcelDataSupplier;
import CoreLib.javautilities.BaseClass;

public class TC01Login {
	
	@BeforeSuite()
	public void setup() throws IOException {
		System.out.println("*****"+ "Befor suite is executing");
		ExcelDataSupplier excelDataSupplier = new ExcelDataSupplier();
		excelDataSupplier.readDataFromExcel();
		WebDriverSingleton.setupDriver().get(BaseClass.getUrl());
		WebDriverSingleton.setupDriver().manage().window().maximize();
		
	}
	
	@Test(testName = "Login test", dataProviderClass = ExcelDataSupplier.class, dataProvider = "readDataFromExcel")

	public void validateLogin(String instanceurl, String username, String password) throws Exception {
        System.out.println("In test login");
		if (instanceurl.contains("https://automation.academiaerp.com/")) {
			LoginPage.enterCredentials(username, password);
		}

	}

}
