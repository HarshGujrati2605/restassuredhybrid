package AppLib.pagelib;

import org.openqa.selenium.By;

import CoreLib.SeleniumUtil.SeleniumActions;

public class HomePage {
	static By Programandcourses = By.xpath("//span[text() = 'Programs & Courses']/ancestor::a");
	static By examinations = By.xpath("//span[text() = 'Examinations']/ancestor::a");
	static By admissions = By.xpath("//span[text() = 'Admissions']/ancestor::a");

	public static void clickProgramAndCourses() throws InterruptedException {
		SeleniumActions.iClickElementByLocator(Programandcourses, "Program and courses module");
	}
	
	public static void clickExaminations() throws InterruptedException {
		SeleniumActions.iClickElementByLocator(examinations, "Examination Module");
	}
	
	public static void clickAdmissioms() throws InterruptedException {
		SeleniumActions.iClickElementByLocator(admissions, "Admission Module");
	}
}
