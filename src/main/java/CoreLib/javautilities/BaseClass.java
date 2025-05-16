package CoreLib.javautilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import CoreLib.DriverUtil.WebDriverSingleton;
import CoreLib.VariableUtil.GlobalVariable;

public class BaseClass extends WebDriverSingleton{
	

	public static void captureScreenshot(String screenshotName) {

		try {
			TakesScreenshot ts = (TakesScreenshot) driver;

			File source = ts.getScreenshotAs(OutputType.FILE);

			FileHandler.copy(source, new File("./Screenshots/" + screenshotName + ".png"));

			System.out.println("Screenshot taken");
		} catch (Exception e) {

			System.out.println("Exception while taking screenshot " + e.getMessage());

		}
	}

	public static String getUrl() throws IOException {
		Properties properties = new Properties();
		FileInputStream configfile = new FileInputStream(".\\src\\main\\resources\\Properties\\config.properties");
		properties.load(configfile);
		String url = properties.getProperty("url");
		return url;
	}

	public static String getHeadlessChoice() throws IOException {
		Properties properties = new Properties();
		FileInputStream configfile = new FileInputStream(".\\src\\main\\resources\\Properties\\config.properties");
		properties.load(configfile);
		String response = properties.getProperty("headless");
		return response;

	}

	public static String getCloudDeviceChoice() throws IOException {
		Properties properties = new Properties();
		FileInputStream configfile = new FileInputStream(".\\src\\main\\resources\\Properties\\config.properties");
		properties.load(configfile);
		String response = properties.getProperty("Device");
		return response;

	}
	
	public static String getAccessToken() throws Exception {
		Properties properties = new Properties();
		FileInputStream configfile = new FileInputStream(".\\src\\main\\resources\\Properties\\config.properties");
		properties.load(configfile);
		String token = properties.getProperty("access_token");
		System.out.println("********************"+ token);
		return token;

	}
	
	

}
