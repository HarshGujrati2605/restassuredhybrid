package CoreLib.DriverUtil;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import CoreLib.SeleniumUtil.SeleniumActions;
import CoreLib.VariableUtil.GlobalVariable;
import CoreLib.javautilities.BaseClass;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author Harsh Gujrati Do not Modify any functions . If required to ad more
 *         choices or integration , contact harsh.gujrati@serosoft.in
 *
 */
public class DriverUtils extends GlobalVariable {
	public static String choice;
	
	private static WebDriver driver;

	public static void setDriver(WebDriver driver) {
		DriverUtils.driver = driver;
	}
	
	public static WebDriver getDriver() {
		return driver;
	}

	@SuppressWarnings("rawtypes")
	public static void driverInitialization() throws IOException {

		choice = BaseClass.getCloudDeviceChoice();
		switch (choice.toLowerCase()) {
		case "localdevice":
			try {
				boolean headless = System.getProperty("headless", "false").equals("true");
				final ChromeOptions chromeOptions = new ChromeOptions();
				if (headless) {
					chromeOptions.addArguments("--headless");
				}
				chromeOptions.addArguments("window-size=1920,1080");
				chromeOptions.addArguments("-incognito");
				chromeOptions.addArguments("start-maximized");
				chromeOptions.addArguments("disable-infobars");
				chromeOptions.addArguments("--disable-extensions");
				chromeOptions.addArguments("--disable-gpu");
				chromeOptions.addArguments("--disable-dev-shm-usage");
				chromeOptions.addArguments("--no-sandbox");
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(chromeOptions);
				setDriver(driver);
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("Driver initialization error");
			}

			break;

		default:
			try {
				String headless = BaseClass.getHeadlessChoice();
				final ChromeOptions chromeOptions = new ChromeOptions();
				if (headless.equals("true")) {
					chromeOptions.addArguments("--headless");
				}
				chromeOptions.addArguments("window-size=1920,1080");
				chromeOptions.addArguments("-incognito");
				chromeOptions.addArguments("start-maximized");
				chromeOptions.addArguments("disable-infobars");
				chromeOptions.addArguments("--disable-extensions");
				chromeOptions.addArguments("--disable-gpu");
				chromeOptions.addArguments("--disable-dev-shm-usage");
				chromeOptions.addArguments("--no-sandbox");
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(chromeOptions);
				setDriver(driver);
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("Driver initialization error");
			}
			break;
		}
	}



	
}
