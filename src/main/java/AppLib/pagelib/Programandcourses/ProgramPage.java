package AppLib.pagelib.Programandcourses;

import org.openqa.selenium.By;

import AppLib.ApiLib.pojolib.programpojo;
import CoreLib.SeleniumUtil.SeleniumActions;

public class ProgramPage extends programpojo {

	static By searchGradeProgram = By.xpath("//input[@name='programNameOrCode']");

	static By programmodule = By.xpath("//span[text() = 'Programs']/ancestor::a");

	public static void searchCreatedProgram() throws Exception {

		SeleniumActions.clickOnElementAndType(searchGradeProgram, getProgramename(), "Search Grade or program");
		Thread.sleep(2000);

	}

	public static void clickProgramModule() throws InterruptedException {
		SeleniumActions.iClickElementByLocator(programmodule, "Program module");
	}

}
