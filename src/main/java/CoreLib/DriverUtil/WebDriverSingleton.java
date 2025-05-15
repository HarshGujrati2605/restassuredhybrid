package CoreLib.DriverUtil;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import CoreLib.Excelutil.ExcelDataSupplier;

public class WebDriverSingleton {
	
	public static WebDriver driver;
	
	@BeforeClass
	public static WebDriver setupDriver() throws IOException {
		System.out.println("*****"+ "Befor class is executing");
		if (driver == null) {
			DriverUtils.driverInitialization();
			driver = DriverUtils.getDriver();
			
		     }
		     return driver;
		
		
	}

}
