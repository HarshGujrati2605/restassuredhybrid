package CoreLib.Locatorutil;

import org.openqa.selenium.By;

import CoreLib.SeleniumUtil.SeleniumActions;

public class CommonLocatos {

	public static void clickSubModule(String submodulename) throws InterruptedException {

		SeleniumActions.iClickElementByLocator(By.xpath("//span[text() = '" + submodulename + "']/ancestor::a"),
				submodulename);

	}

}
