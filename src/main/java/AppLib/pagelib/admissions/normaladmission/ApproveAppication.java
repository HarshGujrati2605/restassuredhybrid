package AppLib.pagelib.admissions.normaladmission;

import org.openqa.selenium.By;

import AppLib.ApiLib.pojolib.CreateApplicationPoJo;
import CoreLib.Locatorutil.CommonLocatos;
import CoreLib.SeleniumUtil.SeleniumActions;
import CoreLib.VariableUtil.GlobalVariable;

public class ApproveAppication extends CreateApplicationPoJo {
	
	static String programGroup = "Under Graduate";
	static String programname = "Automation test1";
	static String programBatch = "DEPT16/Automation test1/1";
	static String programseattype = "Reserved Seat";
	static String writtenstage = "Automation Written";
	static String interviewstage = "Automation Interview";
	
	
	static By searchapplicationname = By.xpath("//input[@name='APPICANT_PRINT_NAME']");
	static By applicationmodule = By.xpath("//span[text() = 'Applications']/ancestor::a");
	static By selectionstagemodule = By.xpath("//span[text() = 'Selection Stage']/ancestor::a");
	static By selectapplicant = By.xpath("//div[contains(text(),'"+getLastname()+"')]/ancestor::tr/td[1]/div/div");
	static By aplicationApproveButton = By.xpath("//span[text() = 'Approve Application']/ancestor::a | //*[contains(text() , 'Accept Application')]");
	static By applicantProgram = By.xpath("//body//div[contains(@class,'x-window') and contains(@style,'opacity: 1')]//div[text() = 'Automation test1']/..");
	static By approvalReasonArrow = By.xpath("//input[@name= 'reasonId']/../../td[2]");
	static By closeonetab = By.xpath("//a[contains(@data-xpath, 'tab') and contains(@class, 'closable')]//span[text()='Applications']/ancestor::span/following-sibling::span");
	static By academylocationArrow = By.xpath("//input[@name= 'ACADEMY_LOCATION_ID']/../../td[2]");
	static By programgrouparrow = By.xpath("//input[@name= 'pgmGroup']/../../td[2]");
	static By prgGrpArrow = By.xpath("//input[@name= 'PROGRAM_GROUP']/../../td[2]");
	static By stageDropdownArrow = By.xpath("//input[@name = 'PROGRAM_SELECTION_PROCESS_STAGE_ID'  and not(contains(@class , 'hidden'))]/../../td[2]");
	static By evealuateApplicantButton = By.xpath("//body/div[contains(@class, 'contentPanel')]/div[contains(@class, 'x-panel-body')]/div[not(contains(@style,'display: none')) and contains(@class,x-panel)]//div[contains(@contains,x-tabpanel-child)]//div[contains(id,tabpanel)]/div[not(contains(@style,'display: none')) and contains(@class, 'x-tabpanel-child')]//span[text() = 'Evaluate Applicant' or text() = 'Accept Applicant']/..");
	static By moreFilters = By.xpath("//body/div[contains(@class, 'contentPanel')]/div[contains(@class, 'x-panel-body')]/div[not(contains(@style,'display: none')) and contains(@class,x-panel)]//div[contains(@contains,x-tabpanel-child)]//div[contains(id,tabpanel)]/div[not(contains(@style,'display: none')) and contains(@class, 'x-tabpanel-child')]//div[text() = 'More Filters']/..");
	static By statusStage = By.xpath("//input[@name = 'STAGE_STATUS']/../../td[2]");
	static By promoToNextStgBtn = By.xpath("//body/div[contains(@class, 'contentPanel')]/div[contains(@class, 'x-panel-body')]/div[not(contains(@style,'display: none')) and contains(@class,x-panel)]//div[contains(@contains,x-tabpanel-child)]//div[contains(id,tabpanel)]/div[not(contains(@style,'display: none')) and contains(@class, 'x-tabpanel-child')]//span[text() = 'Promote To Next Stage']/..");
	static By seatTypeArrow = By.xpath("//body/div[contains(@class, 'contentPanel')]/div[contains(@class, 'x-panel-body')]/div[not(contains(@style,'display: none')) and contains(@class,x-panel)]//input[@name= 'SEAT_TYPE']/../../td[2]");
	static By admissionid = By.xpath("//body/div[contains(@class, 'contentPanel')]/div[contains(@class, 'x-panel-body')]/div[not(contains(@style,'display: none')) and contains(@class,x-panel)]//tr[contains(@role,'row')]/td[2]/div");
	static By evelutionappllicant = By.xpath("//a[text()='"+getApplicantAdmissionId()+"']/ancestor::tr/td[1]/div/div");
	static By aplicantadmisnId = By.xpath("(//div[contains(@data-xpath,'applicantProfileTemplate')]//span)[1]");
	
	
	public static void clickApplicationModule() throws Exception {
		setAcademyLocation("DXT-Dehradun");
		
		SeleniumActions.iClickElementByLocator(applicationmodule, "Application Sub module");
				
	}
	public static void SearchApplicationIdandApprove() throws Exception {
		SeleniumActions.clickOnElementAndType(searchapplicationname, getLastname(), "Select Application Id");
		SeleniumActions.iClickEnter(searchapplicationname);		
		CommonLocatos.iClickButtonOfChoice("Search");
		SeleniumActions.iClickElementByLocator(selectapplicant, "Select the applicant");
		CommonLocatos.iClickActionButton("Edit");		
		SeleniumActions.iClickElementByLocator(aplicationApproveButton, "Click Approve application button");		
		SeleniumActions.iClickElementByLocator(applicantProgram,"Program is selected");		
		SeleniumActions.iClickElementByLocator(approvalReasonArrow, "Approval reason dripdown arrow");
		SeleniumActions.iClickElementByLocator(By.xpath("//div[text()= ' Eligible ']"),"Aprroval reason is selected");		
		CommonLocatos.iClickSaveButton("window");		
		Thread.sleep(5000);		
		GlobalVariable.applicantAdmissionId = CommonLocatos.iGetTextByLoctor(aplicantadmisnId, "Admission Id");
		setApplicantAdmissionId(GlobalVariable.applicantAdmissionId);
		System.out.println("Applicant Admission Id : "+getApplicantAdmissionId());		
		if(applicantAdmissionId.equals(null)|| applicantAdmissionId==null){
			GlobalVariable.applicantAdmissionId = CommonLocatos.iGetTextByLoctor(admissionid, "Admission Id");
		}		
		CommonLocatos.closealltabs();
		
	}
	
	public static void SelectionStages() throws Exception {
		SeleniumActions.iClickElementByLocator(selectionstagemodule, "Selectionstage Sub module");
	}
		
		public static void approveApplicantWritten() throws Exception {
			SeleniumActions.iClickElementByLocator(academylocationArrow, "Click Academy Location Arrow");
			Thread.sleep(2000);
			SeleniumActions.iClickElementByLocator(
					By.xpath("//div[contains(text(),'"+getAcademyLocation()+"')]"),
					"Select Assgin Calender Arrow");
			SeleniumActions.iClickElementByLocator(prgGrpArrow, "Click Program group Arrow");
			Thread.sleep(2000);
			SeleniumActions.iClickElementByLocator(By.xpath("//div[contains(text(),'" + programGroup + "')]"),
					"Select Program Group Name");
			SeleniumActions.clickOnElementAndType(By.xpath("//input[@name= 'PROGRAM_ID']"), programname,
					"Select Program Name");
			SeleniumActions.iClickEnter(By.xpath("//input[@name= 'PROGRAM_ID']"));
			SeleniumActions.iClickElementByLocator(By.xpath("//input[@name= 'BATCH_ID']/../../td[2]"),
					"Arrow Batch dropdown");
			Thread.sleep(2000);
			SeleniumActions.iClickElementByLocator(
					By.xpath("//div[text()=' " + programBatch + " ' and contains(@class, 'list')]"),
					"Value for batch is " + programBatch + "");
			SeleniumActions.iClickElementByLocator(seatTypeArrow, "seat type arrow");
			Thread.sleep(2000);
			SeleniumActions.iClickElementByLocator(
					By.xpath("//div[text() = ' " + programseattype + " ' and contains(@class, 'list')]"),
					"Selected seat type " + programseattype);
			SeleniumActions.iClickElementByLocator(stageDropdownArrow, "Selection stage arrow");
			Thread.sleep(2000);
			SeleniumActions.iClickElementByLocator(
					By.xpath("//div[text() = ' " + writtenstage + " ' and contains(@class, 'list')]"),
					writtenstage);
			CommonLocatos.iClickButtonOfChoice("Search");
			SeleniumActions.iClickElementByLocator(By.xpath("//a[text()='"+getApplicantAdmissionId()+"']/ancestor::tr/td[1]/div/div"), "Select Evalution Applicant");
			SeleniumActions.iClickElementByLocator(evealuateApplicantButton, "Evaluate applicant button");
			CommonLocatos.iClickYes();
			SeleniumActions.iClickElementByLocator(moreFilters, "More filters");
			Thread.sleep(2000);
			SeleniumActions.iClickElementByLocator(statusStage, "stage status stage");
			SeleniumActions.iClickElementByLocator(
					By.xpath("//div[text() = ' Ready to Promote ' or text() = ' Ready for Academic Approval ']"),
					"Ready to promote");
			CommonLocatos.iClickButtonOfChoice("Search");
			SeleniumActions.iClickElementByLocator(By.xpath("//a[text()='"+getApplicantAdmissionId()+"']/ancestor::tr/td[1]/div/div"), "Select Evalution Applicant");
			SeleniumActions.iClickElementByLocator(promoToNextStgBtn, "Promote to next stage");
			CommonLocatos.iClickYes();
		}

		public static void approveApplicantInterview() throws Exception {
			SeleniumActions.iClickElementByLocator(academylocationArrow, "Click Academy Location Arrow");
			Thread.sleep(2000);
			SeleniumActions.iClickElementByLocator(
					By.xpath("//div[contains(text(),'" + getAcademyLocation() + "')]"),
					"Select Assgin Calender Arrow");
			Thread.sleep(2000);
			SeleniumActions.iClickElementByLocator(prgGrpArrow, "Click Program group Arrow");
			Thread.sleep(2000);
			SeleniumActions.iClickElementByLocator(By.xpath("//div[contains(text(),'" + programGroup + "')]"),
					"Select Program Group Name");
			SeleniumActions.clickOnElementAndType(By.xpath("//input[@name= 'PROGRAM_ID']"), programname,
					"Select Program Name");
			SeleniumActions.iClickEnter(By.xpath("//input[@name= 'PROGRAM_ID']"));
			SeleniumActions.iClickElementByLocator(By.xpath("//input[@name= 'BATCH_ID']/../../td[2]"),
					"Arrow Batch dropdown");
			SeleniumActions.iClickElementByLocator(
					By.xpath("//div[text()=' " + programBatch + " ' and contains(@class, 'list')]"),
					"Value for batch is " + programBatch + "");
			SeleniumActions.iClickElementByLocator(seatTypeArrow, "seat type arrow");
			Thread.sleep(2000);
			SeleniumActions.iClickElementByLocator(
					By.xpath("//div[text() = ' " + programseattype + " ' and contains(@class, 'list')]"),
					"Selected seat type " + programseattype);
			SeleniumActions.iClickElementByLocator(stageDropdownArrow, "Selection stage arrow");
			Thread.sleep(2000);
			SeleniumActions.iClickElementByLocator(
					By.xpath("//div[text() = ' " + interviewstage + " ' and contains(@class, 'list')]"),
					interviewstage);
			CommonLocatos.iClickButtonOfChoice("Search");
			SeleniumActions.iClickElementByLocator(By.xpath("//a[text()='"+getApplicantAdmissionId()+"']/ancestor::tr/td[1]/div/div"), "Select Evalution Applicant");			Thread.sleep(2000);
			SeleniumActions.iClickElementByLocator(evealuateApplicantButton, "Evaluate applicant button");
			CommonLocatos.iClickYes();
			Thread.sleep(3000);
//			try {
//				SeleniumActions.iClickElementByLocator(moreFilters, "More filters");
//				Thread.sleep(2000);
//				SeleniumActions.iClickElementByLocator(statusStage, "stage status stage");
//			} catch (Exception e) {
//				SeleniumActions.iClickElementByLocator(moreFilters, "More filters");
//				Thread.sleep(2000);
//				SeleniumActions.iClickElementByLocator(statusStage, "stage status stage");
//			}			
			SeleniumActions.iClickElementByLocator(By.xpath("//div[text() = ' Ready to Promote ']"), "Ready to promote");
			CommonLocatos.iClickButtonOfChoice("Search");
			Thread.sleep(2000);
			SeleniumActions.iClickElementByLocator(By.xpath("//a[text()='"+getApplicantAdmissionId()+"']/ancestor::tr/td[1]/div/div"), "Select Evalution Applicant");
			SeleniumActions.iClickElementByLocator(promoToNextStgBtn, "Promote to next stage");
			CommonLocatos.iClickYes();
		}

		
	
	
	

	
	

}
