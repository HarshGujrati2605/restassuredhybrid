package testlib.program;

import org.testng.annotations.Test;

import AppLib.pagelib.HomePage;
import AppLib.pagelib.Programandcourses.ProgramPage;

public class TC03SeachProgramAndParameterized {
	
    @Test(testName = "search program")
	public void searchProgram() throws Exception {
		HomePage.clickProgramAndCourses();
		ProgramPage.clickProgramModule();
		ProgramPage.searchCreatedProgram();
	}
}
