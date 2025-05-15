package AppLib.pagelib;

import org.openqa.selenium.By;

import CoreLib.SeleniumUtil.SeleniumActions;

public class HomePage {
	static By Programandcourses = By.xpath("//span[text() = 'Programs & Courses']/ancestor::a");

	public static void clickProgramAndCourses() throws InterruptedException {
		SeleniumActions.iClickElementByLocator(Programandcourses, "Program and courses module");
	}
}
