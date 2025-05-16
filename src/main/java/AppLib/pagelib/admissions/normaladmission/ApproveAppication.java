package AppLib.pagelib.admissions.normaladmission;

import org.openqa.selenium.By;

import AppLib.ApiLib.pojolib.CreateApplicationPoJo;
import CoreLib.Locatorutil.CommonLocatos;
import CoreLib.SeleniumUtil.SeleniumActions;

public class ApproveAppication extends CreateApplicationPoJo {
	
	static By searchapplicationname = By.xpath("//input[@name='APPICANT_PRINT_NAME']");

	static By applicationmodule = By.xpath("//span[text() = 'Applications']/ancestor::a");
	
	public static void clickApplicationModule() throws Exception {
		SeleniumActions.iClickElementByLocator(applicationmodule, "Program module");
				
	}
	public static void SearchApplicationId() throws Exception {
		SeleniumActions.clickOnElementAndType(searchapplicationname, getLastname(), "Select Application Id");
		SeleniumActions.iClickEnter(searchapplicationname);
		
		CommonLocatos.iClickButtonOfChoice("Search");
		
	}

	
	

}
