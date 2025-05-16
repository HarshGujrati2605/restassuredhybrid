package AppLib.pagelib.Programandcourses;

import org.openqa.selenium.By;



import AppLib.ApiLib.pojolib.programpojo;
import CoreLib.SeleniumUtil.SeleniumActions;
import CoreLib.javautilities.BaseClass;



public class ManageCourseSectionPage extends programpojo {
	By AcdemicYearArrow = By.xpath("//input[@name = 'programWiseAcademicTerm']/../../td[2]");
	By marksArrow = By.xpath("//input[@name = 'programWiseCourseMarkGrade']/../../td[2]");
	By mngsecprgnametxt = By.xpath("//div[@name = 'programWiseProgram']/parent::td/../td[3]");
	By coursedepartmenttxt = By.xpath("//input[@name='department']");
	By searchClass = By.xpath(
			"//body/div[contains(@class, 'contentPanel')]/div[contains(@class, 'x-panel-body')]//div[not(contains(@style,'display: none')) and contains(@class,'x-panel customSearchPanel x-box-item x-panel-default')]//span[text() = 'Search']/..");
		
	public void iChoosetheSectionType(String sectiontype, String academicterm) throws Exception {
		setSectiontype(sectiontype);
		setAcademicterm(academicterm);
		if (sectiontype.equalsIgnoreCase("Open Sections")) {
			SeleniumActions
					.iClickElementByLocator(
							By.xpath("//table[contains(@data-xpath,'sectionTypeRadio')]//label[contains(text(),'"
									+ sectiontype + "')]/parent::td/input"),
							"I choose the Section type as " + sectiontype);
		} else if (sectiontype.equalsIgnoreCase("Program-Wise Sections")) {
			SeleniumActions
					.iClickElementByLocator(
							By.xpath("//table[contains(@data-xpath,'sectionTypeRadio')]//label[contains(text(),'"
									+ sectiontype + "')]/parent::td/input"),
							"I choose the Section type as " + sectiontype);
		} else {
			SeleniumActions.iLogMessage("I choose the Section type as " + sectiontype);
		}
			
		Thread.sleep(1000);
		if (getSectiontype().equalsIgnoreCase("Program-Wise Sections")) {

			try {
				SeleniumActions.iClickElementByLocator(AcdemicYearArrow, "Academic term dropdown");

				Thread.sleep(1000);
				SeleniumActions.iClickElementByLocator(By.xpath("//div[text() = ' " + getAcademicterm() + " ']"),
						"year is selected " + getAcademicterm() + "");

			} catch (Exception e) {
				SeleniumActions.clickOnElementAndType(By.xpath("//input[@name = 'programWiseAcademicTerm']"), getAcademicterm(),
						"Academic term dropdown");
			}
			SeleniumActions.iClickElementByLocator(marksArrow, "marks scheme arrow");
			Thread.sleep(1000);

			SeleniumActions.iClickElementByLocator(By.xpath("//div[text() = ' " + getMarktype() + " ']"),
					"Mark Type is selected as " + getMarktype() + "");

			SeleniumActions.iClickElementByLocator(mngsecprgnametxt, "Grade prg name Arrow");
			Thread.sleep(3000);

			SeleniumActions.iClickElementByLocator(searchClass, "Search button");
		}
		else if (getSectiontype().equalsIgnoreCase("Open Sections")) {
			SeleniumActions.clickOnElementAndType(coursedepartmenttxt, getCoursedepartment(),
					"I enter the course department name as " + getCoursedepartment());
			Thread.sleep(1000);
			SeleniumActions.iClickEnter(coursedepartmenttxt);
			SeleniumActions.iClickElementByLocator(By.xpath("//div[@name='courseSectionId']/../../td[3]"),
					"Clicking Course name dropdown");
			Thread.sleep(1000);
			SeleniumActions.iClickElementByLocator(
					By.xpath("//div[contains(text(),'" + getCoursename() + "')]/preceding-sibling::div"),
					"Selecting Course name in dropdown");
			Thread.sleep(1000);
			SeleniumActions.iClickElementByLocator(By.xpath(
					"//table[contains(@data-xpath,'opensectionsearch')]//label[contains(text(),'Courses Without Section')]/preceding-sibling::input"),
					"I select the Course with out section check box");
			Thread.sleep(1000);
			SeleniumActions.iClickElementByLocator(searchClass, "Search button");
		} else {
			SeleniumActions.iLogMessage("Section Type selected as " + getSectiontype());
		}

	}

	
	

}
