package AppLib.pagelib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import CoreLib.SeleniumUtil.SeleniumActions;

public class LoginPage {
	static By AdminTab = By.xpath("//span[text() = 'Administrator']/..");
	static By Username = By.xpath("//input[@name = 'email']");
	static By Password = By.xpath("//input[@name = 'password']");
	static By SigninButton = By.xpath("//input[@name = 'signin']");

	public static void enterCredentials(String username, String password) throws Exception {
		WebElement admintab = SeleniumActions.getElement(AdminTab);
		SeleniumActions.iClick(admintab, "Admin Tab");
		SeleniumActions.clickOnElementAndType(Username, username, "Username field");
		SeleniumActions.clickOnElementAndType(Password, password, "Password field");
		SeleniumActions.iClickElementByLocator(SigninButton, "Login button");

	}

};
