package CoreLib.Locatorutil;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import CoreLib.DriverUtil.DriverUtils;
import CoreLib.SeleniumUtil.SeleniumActions;
import CoreLib.VariableUtil.GlobalVariable;

public class CommonLocatos extends SeleniumActions {	
	
	static By SaveButton = By.xpath(
			"//body/div[contains(@class, 'contentPanel')]/div[contains(@class, 'x-panel-body')]/div[not(contains(@style,'display: none')) and contains(@class,x-panel)]//span[text() = 'Save']/..");
	static By SaveButtonNested = By.xpath(
			"//body/div[contains(@class, 'contentPanel')]/div[contains(@class, 'x-panel-body')]/div[not(contains(@style,'display: none')) and contains(@class,x-panel)]//div[contains(@contains,x-tabpanel-child)]//div[contains(id,tabpanel)]/div[not(contains(@style,'display: none')) and contains(@class, 'x-tabpanel-child')]//span[text() = 'Save']/..");
	static By SaveButtonFloated = By.xpath(
			"//body//div[contains(@class,'x-window') and contains(@style,'opacity: 1')]//span[text()= 'Save']/..");
	static By SubmitButtonFloated = By.xpath(
			"//body//div[contains(@class,'x-window') and contains(@style,'opacity: 1')]//span[text()= 'Submit']/..");

	static By MoreActionsButton = By.xpath(
			"//body/div[contains(@class, 'contentPanel')]/div[contains(@class, 'x-panel-body')]/div[not(contains(@style,'display: none')) and contains(@class,x-panel)]//span[text()= 'More Actions']/..");

	static By MoreActionButtonNested = By.xpath(
			"//body/div[contains(@class, 'contentPanel')]/div[contains(@class, 'x-panel-body')]/div[not(contains(@style,'display: none')) and contains(@class,x-panel)]//div[contains(@contains,x-tabpanel-child)]//div[contains(id,tabpanel)]/div[not(contains(@style,'display: none')) and contains(@class, 'x-tabpanel-child')]//span[text()= 'More Actions']/..");

	static By TodayButton = By.xpath(
			"//div[not(contains(@style,'display: none'))]/div[contains(@role,'grid')]//span[text() = 'Today' and contains(@class , 'btn')]/..");

	static By YesButton = By.xpath("//span[text() = 'Yes']/..");

	static By SuccessMessage = By.xpath("//div[contains(text() , 'successfully') or contains(text() , 'Success') or contains(text() , 'SUCCESS') ]/..");

	static By searchButton = By.xpath(
			"//body/div[contains(@class, 'contentPanel')]/div[contains(@class, 'x-panel-body')]/div[not(contains(@style,'display: none')) and contains(@class,x-panel)]//span[contains(text(),'Search')]/ancestor::a[contains(@class, 'Submit')]");

	static By searchButtonNested = By.xpath(
			"//body/div[contains(@class, 'contentPanel')]/div[contains(@class, 'x-panel-body')]/div[not(contains(@style,'display: none')) and contains(@class,x-panel)]//div[contains(@contains,x-tabpanel-child)]//div[contains(id,tabpanel)]/div[not(contains(@style,'display: none')) and contains(@class, 'x-tabpanel-child')]//span[contains(text(),'Search')]/ancestor::a[contains(@class, 'Submit')]");

	static By submitButton = By.xpath(
			"//body/div[contains(@class, 'contentPanel')]/div[contains(@class, 'x-panel-body')]/div[not(contains(@style,'display: none')) and contains(@class,x-panel)]//span[text()= 'Submit']/../..");

	static By continueManuallyButton = By.xpath("//span[text()='Continue Manually']/..");
	static By profilePic = By.xpath("//a[contains(@class , 'headerUserinfo')]");
	static By singOutBtn = By.xpath("//span[contains(text() ,'Sign Out')]/..");
	static By changeal = By.xpath("//span[text() = 'Change Academy Location']/parent::a");

	static By nextmontharrow = By.xpath(
			"//div[not(contains(@style,'display: none'))]/div[contains(@role,'grid')]//a[contains(@title , 'Next Month')]");
	static By prevmontharrow = By.xpath(
			"//div[not(contains(@style,'display: none'))]/div[contains(@role,'grid')]//a[contains(@title , 'Previous Month')]");
	
	static By day = By.xpath("//div[not(contains(@style,'display: none'))]/div[contains(@role,'grid')]//a[contains(text(), '"
			+ GlobalVariable.Dayfordatepicker + "')]/..");

	static By alchangearrow = By.xpath(
			"//body//div[contains(@class,'x-window') and contains(@style,'opacity: 1')]//label[text() = 'Academy Location:']/ancestor::tr/td[contains(@class , 'item')]/table//td[2]");
	static By firsttab = By.xpath("(//a[contains(@data-xpath, 'tab') and contains(@class, 'closable')])[1]");
	static By closealltabs = By.xpath(
			"//div[not(contains(@class , 'disabled')) and @data-xpath = 'menu_menucheckitem']//span[text() = 'Close All Tabs']");
	static By closeonetab = By.xpath(
			"//div[not(contains(@class , 'disabled')) and @data-xpath = 'menu_menucheckitem']//span[text() = 'Close Tab']");

	public static void clickSubModule(String submodulename) throws InterruptedException {

		iClickElementByLocator(By.xpath("//span[text() = '" + submodulename + "']/ancestor::a"),
				submodulename);

	}
	
	public static void iClickOnoption(String option) throws InterruptedException {
		iImplicitlywait(30, option);
		WebElement ele = DriverUtils.getDriver().findElement(By.xpath(
				"//body/div[contains(@class, 'contentPanel')]/div[contains(@class, 'x-panel-body')]/div[not(contains(@style,'display: none')) and contains(@class,x-panel)]//div[contains(@class, 'x-panel x-grid-with-row-lines x-panel-default x-grid')]//a[contains(@class,'x-btn') and contains(@role,'button') and contains(@data-qtip,'"
						+ option + "')]"));
		Thread.sleep(2000);
		iClick(ele, option);
	}

	public void iClickModule(String option) throws InterruptedException {

		if (option.equalsIgnoreCase("Students List")) {

			WebElement ele = driver.findElement(
					By.xpath("//span[text() = '" + option + "']/ancestor::a[@data-qtip = '" + option + "']"));
			Thread.sleep(3000);
			try {
				iClick(ele, option);
			} catch (Exception e) {
				iClickJSE(ele, option);
			}
		} else {

			WebElement ele = driver.findElement(By.xpath("//span[text() = '" + option + "']/ancestor::a"));
			Thread.sleep(3000);
			try {
				iClick(ele, option);
			} catch (Exception e) {
				iClickJSE(ele, option);
			}
		}
	}

	public static void iClickActionButton(String option) throws InterruptedException {
		try {
			System.out.println("Inside BTN conditon");
//			WebElement button = getElement(By.xpath(
//					"//body/div[contains(@class, 'contentPanel')]/div[contains(@class, 'x-panel-body')]/div[not(contains(@style,'display: none')) and contains(@class,x-panel)]//div[contains(@class, 'x-panel x-grid-with-row-lines x-panel-default x-grid')]//a[contains(@class,'x-btn') and contains(@role,'button') and contains(@data-qtip,'"
//							+ option + "')]|//a[contains(@data-xpath,'Btn" + option + "')]"));
			WebElement button = getElement(By.xpath(
					"//body/div[contains(@class, 'contentPanel')]/div[contains(@class, 'x-panel-body')]/div[not(contains(@style,'display: none')) and contains(@class,x-panel)]//a[contains(@class,'x-btn') and contains(@role,'button') and contains(@data-xpath ,'Btn"
							+ option + "')  or  contains(@data-xpath ,'btn" + option + "') or contains(@data-qtip ,'"
							+ option + "') ]"));
			iClick(button, "Click on " + option + " button");

		} catch (Exception e) {
			WebElement button = getElement(By.xpath(
					"//body/div[contains(@class, 'contentPanel')]/div[contains(@class, 'x-panel-body')]/div[not(contains(@style,'display: none')) and contains(@class,x-panel)]//div[contains(@class, 'x-panel x-grid-with-row-lines x-panel-default x-grid')]//a[contains(@class,'x-btn') and contains(@role,'button') and contains(@data-qtip,'"
							+ option + "')]"));
			iClick(button, "Click on " + option + " button");
		}
	}

	public void iClickActionButtonInsideNestedTabs(String option) throws InterruptedException {
		WebElement button = getElement(By.xpath(
				"//body/div[contains(@class, 'contentPanel')]/div[contains(@class, 'x-panel-body')]/div[not(contains(@style,'display: none')) and contains(@class,x-panel)]//div[contains(@contains,x-tabpanel-child)]//div[contains(id,tabpanel)]/div[not(contains(@style,'display: none')) and contains(@class, 'x-tabpanel-child')]//a[@data-qtip='"
						+ option + "']"));
		iClick(button, option);
	}

	public static void iClickSaveButton(String type) throws InterruptedException {
		WebElement button;
		switch (type) {
		case "window":
			button = getElement(SaveButtonFloated);
			iClick(button, type);

			break;
		case "submit":
			button = getElement(SubmitButtonFloated);
			iClick(button, type);

			break;
		case "nested":
			button = getElement(SaveButtonNested);
			try {
				iClick(button, type);
			} catch (Exception e) {
				iClickJSE(button, type);
			}

			break;
		default:
			button = getElement(SaveButton);
			iClick(button, type);
			break;
		}
	}

	public static void iClickButtonOfChoice(String ButtonName) throws InterruptedException {
		WebElement button;

		switch (ButtonName) {
		case "Today":
			button = getElement(TodayButton);
			iClick(button, ButtonName);

			break;
		case "More Action":
			button = getElement(MoreActionsButton);
			iClick(button, ButtonName);

			break;

		case "Submit nested":
			button = getElement(By.xpath(
					"//body/div[contains(@class, 'contentPanel')]/div[contains(@class, 'x-panel-body')]/div[not(contains(@style,'display: none')) and contains(@class,x-panel)]//div[contains(@contains,x-tabpanel-child)]//div[contains(id,tabpanel)]/div[not(contains(@style,'display: none')) and contains(@class, 'x-tabpanel-child')]//span[text() = 'Submit']/../.."));
			iClick(button, ButtonName);
			break;
		case "Next":
			button = getElement(By.xpath(
					"//body/div[contains(@class, 'contentPanel')]/div[contains(@class, 'x-panel-body')]/div[not(contains(@style,'display: none')) and contains(@class,x-panel)]//div[contains(@contains,x-tabpanel-child)]//div[contains(id,tabpanel)]/div[not(contains(@style,'display: none')) and contains(@class, 'x-tabpanel-child')]//span[text() = 'Next']/../.."));
			iClick(button, ButtonName);
			break;

		case "Search":

			button = getElement(searchButton);
			iClick(button, ButtonName);
			break;

		case "SearchNested":

			button = getElement(searchButtonNested);
			iClick(button, ButtonName);
			break;

		case "Submit":

			button = getElement(submitButton);
			iClick(button, ButtonName);
			break;

		case "Manually":

			button = getElement(continueManuallyButton);
			iClick(button, ButtonName);
			break;

		case "Next Month":

			button = getElement(nextmontharrow);
			iClick(button, ButtonName);
			break;
		
		case "Prev Month":

			button = getElement(prevmontharrow);
			iClick(button, ButtonName);
			break;

		case "Day":

			button = getElement(day);
			iClick(button, ButtonName);
			break;

		case "Submit window":
			button = getElement(By.xpath(
					"//body//div[contains(@class,'x-window') and contains(@style,'opacity: 1')]//span[text() = 'Submit']/../.."));
			iClick(button, ButtonName);
			break;

		case "Save window":
			button = getElement(By.xpath(
					"//body//div[contains(@class,'x-window') and contains(@style,'opacity: 1')]//span[text() = 'Save']/../.."));
			iClick(button, ButtonName);
			break;

		case "More Action nested":
			button = getElement(MoreActionButtonNested);
			iClick(button, ButtonName);
			break;

		default:
			System.out.print("Wrong choice , please try again!!!");

		}

	}

	public void iSelectRow(String number, String option) throws InterruptedException {
		WebElement row;

		switch (option) {
		case "nested":
			Thread.sleep(6000);
			row = getElement(By.xpath(
					"//body/div[contains(@class, 'contentPanel')]/div[contains(@class, 'x-panel-body')]/div[not(contains(@style,'display: none')) and contains(@class,x-panel)]//div[contains(@contains,x-tabpanel-child)]//div[contains(id,tabpanel)]/div[not(contains(@style,'display: none')) and contains(@class, 'x-tabpanel-child')]//tr[contains(@role,'row')]["
							+ number + "]"));
			iClick(row, number);
			break;
		default:
			Thread.sleep(6000);
			row = getElement(By.xpath(
					"//body/div[contains(@class, 'contentPanel')]/div[contains(@class, 'x-panel-body')]/div[not(contains(@style,'display: none')) and contains(@class,x-panel)]//tr[contains(@role,'row')]["
							+ number + "]"));
			iClick(row, "row");
			break;

		}

	}

	public static void iClickYes() throws InterruptedException {

		WebElement yesbutton = getElement(YesButton);
		iClick(yesbutton, "Yes button clicked");
	}

	public void iValidateAlert() {
		try {
			WebElement alertmessage = getElement(SuccessMessage);
//			highlightElementGreen(alertmessage, "Successful alert is present");
		} catch (Exception e) {
			iLogMessage("No such alert found!!");
		}

	}

	public void iSelectColumn(String rownumber, String column, String option) throws InterruptedException {
		WebElement row;
		switch (option) {
		case "nested":
			Thread.sleep(3000);
			row = getElement(By.xpath(
					"//body/div[contains(@class, 'contentPanel')]/div[contains(@class, 'x-panel-body')]/div[not(contains(@style,'display: none')) and contains(@class,x-panel)]//div[contains(@contains,x-tabpanel-child)]//div[contains(id,tabpanel)]/div[not(contains(@style,'display: none')) and contains(@class, 'x-tabpanel-child')]//tr[contains(@role,'row')]["
							+ rownumber + "]/td[" + column + "]/div"));
			iClick(row, rownumber);
			break;
		default:
			Thread.sleep(3000);
			row = getElement(By.xpath(
					"//body/div[contains(@class, 'contentPanel')]/div[contains(@class, 'x-panel-body')]/div[not(contains(@style,'display: none')) and contains(@class,x-panel)]//tr[contains(@role,'row')]["
							+ rownumber + "]/td[" + column + "]/div"));
			iClick(row, rownumber);
			break;

		}
		Thread.sleep(3000);

	}

	public String iSelectColumnAndGetText(String rownumber, String column, String option) throws InterruptedException {
		WebElement row;
		String text;
		switch (option) {
		case "nested":
			row = getElement(By.xpath(
					"//body/div[contains(@class, 'contentPanel')]/div[contains(@class, 'x-panel-body')]/div[not(contains(@style,'display: none')) and contains(@class,x-panel)]//div[contains(@contains,x-tabpanel-child)]//div[contains(id,tabpanel)]/div[not(contains(@style,'display: none')) and contains(@class, 'x-tabpanel-child')]//tr[contains(@role,'row')]["
							+ rownumber + "]/td[" + column + "]/div"));
			text = row.getText();
			break;
		default:
			row = getElement(By.xpath(
					"//body/div[contains(@class, 'contentPanel')]/div[contains(@class, 'x-panel-body')]/div[not(contains(@style,'display: none')) and contains(@class,x-panel)]//tr[contains(@role,'row')]["
							+ rownumber + "]/td[" + column + "]/div"));
			text = row.getText();
			break;
		}
		return text;
	}

	public void iClickLogOutButton() throws InterruptedException {
		iClickElementByLocator(profilePic, "Profile picture");
		Thread.sleep(2000);
		iClickElementByLocator(singOutBtn, "Sign out button");
		Thread.sleep(2000);
		try {
			iClickElementByLocator(By.xpath("//span[text()='OK']"), "I click ok button ");
		} catch (Exception e) {
//		System.out.println("Session not expired");
			iLogMessage("Session not expired");
		}

	}

	public void iRestartSession() throws Exception {
		Thread.sleep(3000);
		iClickElementByLocator(By.xpath("//span[text()='OK']"), "I click ok button ");

	}

	public void iClickOk() throws InterruptedException {
		iClickJSEByLocator(By.xpath(
				"//body//div[contains(@class,'x-window') and contains(@style,'opacity: 1')]//span[text() = 'Ok']/.."),
				"ok button");
	}

//	public void iChangeAcademyLocation(String Academylocation) throws InterruptedException {
//		GlobalVariable.exchangeacademylocation = Academylocation.trim();
//		
//		iClickElementByLocator(profilePic, "Profile picture");
//		Thread.sleep(2000);
//		iClickElementByLocator(changeal, "Change AL");
//		Thread.sleep(1000);
//		iClickElementByLocator(alchangearrow, "AL change arrow");
//		Thread.sleep(3000);
//		iClickElementByLocator(By.xpath("//div[text() = ' " + Academylocation + " ']"), Academylocation);
//		Thread.sleep(1000);
//		new CommonLocatorPage().iClickSaveButton("window");
//
//	}

	public static void closealltabs() throws InterruptedException {
		// iClickJSEByLocator(firsttab, "first tab");

		try {
			try {
			List<WebElement> closebtns = getElementList(By.xpath(
					"//a[contains(@data-xpath, 'tab') and contains(@class, 'closable')]/span[contains(@class ,'close')]"));
			for (WebElement close : closebtns) {

				iClickJSE(close, "close button");
				Thread.sleep(1000);
			}
			}catch (Exception exception) {
				iLogMessage("All tabs are closed already");
			}
		} catch (Exception e) {
			getcoordinatesandclick(firsttab, "first tab");
			Thread.sleep(1000);
			iRightClick(firsttab, "first tab");
			Thread.sleep(1000);
			try {
				iClickJSEByLocator(closealltabs, "close all open tabs");
			} catch (Exception exception) {
				iClickJSEByLocator(closeonetab, "close one tab");

			}
		}

	}
	
	public void iCloseTab(String tabname) throws InterruptedException {
		Thread.sleep(1000);
		iClickJSEByLocator(By.xpath("//span[text() = '"+tabname+"' and contains(@class , 'tab')] /ancestor::a/span[contains(@class , 'close')]"), tabname);
	}
}
