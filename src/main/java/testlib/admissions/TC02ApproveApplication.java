package testlib.admissions;

import org.testng.annotations.Test;

import AppLib.ApiLib.pojolib.CreateApplicationPoJo;
import AppLib.pagelib.HomePage;
import AppLib.pagelib.admissions.normaladmission.ApproveAppication;

public class TC02ApproveApplication extends CreateApplicationPoJo {
	
		
	 @Test(testName = "Sreach Applicant and Approve")
		public void searchApplicationId() throws Exception {
			HomePage.clickAdmissioms();
			ApproveAppication.clickApplicationModule();
			ApproveAppication.SearchApplicationIdandApprove();			
		}
	 @Test(testName = "SelectionStage", dependsOnMethods = "searchApplicationId")
		public void selectionStage() throws Exception {
			HomePage.clickAdmissioms();
			ApproveAppication.SelectionStages();
			ApproveAppication.approveApplicantWritten();
			ApproveAppication.approveApplicantInterview();
						
		}
	
	
}
