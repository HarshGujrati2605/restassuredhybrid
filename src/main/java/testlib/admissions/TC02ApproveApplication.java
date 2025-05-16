package testlib.admissions;

import org.testng.annotations.Test;

import AppLib.ApiLib.pojolib.CreateApplicationPoJo;
import AppLib.pagelib.HomePage;
import AppLib.pagelib.Programandcourses.ProgramPage;

public class TC02ApproveApplication extends CreateApplicationPoJo {
	
		
	 @Test(testName = "search program")
		public void searchApplicationId() throws Exception {
			HomePage.clickAdmissioms();
			ProgramPage.clickProgramModule();
			ProgramPage.searchCreatedProgram();
		}
	
}
