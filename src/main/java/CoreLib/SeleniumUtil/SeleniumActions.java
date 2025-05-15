package CoreLib.SeleniumUtil;

import static org.testng.Assert.assertEquals;

import org.apache.log4j.Logger;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Keyboard;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import io.appium.java_client.AppiumDriver;
//import io.appium.java_client.PerformsTouchActions;
//import io.appium.java_client.TouchAction;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.interactions.internal.MouseAction;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.javafaker.Faker;

import CoreLib.DriverUtil.DriverUtils;
import CoreLib.Loggerutil.LoggerHelper;
import variableutility.GlobalVariable;


/**
 * 
 * @author Harsh Gujrati Do not Modify this action methods . If any change
 *         required, please contact me at harsh.gujrati@serosoft.in
 *
 */

public class SeleniumActions extends GlobalVariable {

	public static WebDriver driver =  DriverUtils.getDriver();;

	
	public static WebElement getElement(By elementloactor) {
		iLogMessage("Waiting for element to be visible");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(elementloactor));
		return ele;
	}

	public static void clickOnElementAndType(By elementlocator, String value, String nameofelement) throws Exception {
		if (driver != null) {
			iImplicitlywait(30, nameofelement);
			WebElement ele = getElement(elementlocator);
			ele.clear();
			iClick(ele, nameofelement);
			ele.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			ele.sendKeys(Keys.chord(Keys.DELETE));
			ele.sendKeys(value);
			iLogMessage("Clicked on" + nameofelement);
		} else {
			throw new Exception("Element not present");
		}
	}

	public static void isDisplayed(By elementlocator, String elementName) throws Exception {
		WebElement element = getElementIfPresent(elementlocator);
		if (element.isDisplayed()) {
			// iScrollToTheElement(element, elementName);
			iLogMessage(elementName + " is displayed....");
		} else {
			throw new Exception("Element not displayed");

		}
	}

	public static void isDisplayedElement(WebElement element, String elementName) throws Exception {
		if (element.isDisplayed()) {
			iLogMessage(elementName + " is displayed....");
			try {
				if (element.isDisplayed()) {
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid green;');", element);
					Thread.sleep(1000);
				}
			} catch (Exception e) {
				iLogErrorMessage("element caanot be located");
			}
		} else {
			throw new Exception("Element not displayed");

		}
	}

	public void iVerifySelected(By elementlocator, String elementName) throws Exception {
		iImplicitlywait(30, elementName);
		WebElement ele = getElement(elementlocator);
		if (ele.isSelected()) {
			System.out.println("The element is selected " + elementName);
		} else {
			throw new Exception("Element not selected");
		}
	}

	public static void iVerifyPartialText(String actualText, String expectedText) throws Exception {
		if (actualText.contains(expectedText)) {
			System.out.println("The expected text contains the actual: " + expectedText);
		} else {
			throw new Exception("Text is not matching");
		}

	}

	public static String iGetText(WebElement ele, String elementname) {
		iLogMessage("Getting the text from" + elementname);
		return ele.getText();
	}

	public static String iGetAttributeValue(By locator, String attributename, String elename) {
		iLogMessage("Getting the text from" + elename);
		WebElement ele = getElement(locator);
		return ele.getAttribute(attributename);
	}

	public static void iVerifyPartialElementText(By elementlocator, String expectedText, String elementName) {
		try {
			WebElement ele = getElement(elementlocator);
			if (iGetText(ele, elementName).contains(expectedText)) {
				System.out.println("The actual text contain the partial element text: " + expectedText);
			} else {
				iLogMessage("The actual text does not contain the partial element text: " + expectedText);
			}
		} catch (Exception e) {
			iLogErrorMessage("The actual text does not contain the partial element text: " + expectedText);
		}
	}

	public static void iVerifyExactElementText(By elementlocator, String expectedText, String elementName) {
		WebElement ele = getElement(elementlocator);
		assertEquals(expectedText, ele.getText());
	}

	public static void iImplicitlywait(long sec, String elementName) {
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));
//			driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
			System.out.println("Wait for " + sec + " sec to visible " + elementName);
		} catch (Exception e) {
			System.out.println(elementName + " is not displayed ");
		}
	}

	public static void iClickJSE(WebElement ele, String elementName) {
		iImplicitlywait(30, elementName);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", ele);
		iLogMessage("i Click on the element " + elementName);

	}

	public static void iClick(WebElement ele, String elementName) throws InterruptedException {
		Thread.sleep(2000);
		iImplicitlywait(30, elementName);
		ele.click();
		iLogMessage("i Click on the element " + elementName);
	}

	public static void allowPermissionss(String alertnamebutton) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
			wait.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().accept();
		} catch (Exception e) {
			iLogMessage("Alert not present");
		}
	}

	public static void iLogMessage(String message) {
		Logger logger = LoggerHelper.getLogger(SeleniumActions.class);
		logger.info(message);
	}

	public static void iLogErrorMessage(String message) {
		Logger logger = LoggerHelper.getLogger(SeleniumActions.class);
		logger.error(message);
	}

	public static void iClickBack(int times) throws InterruptedException {
		Thread.sleep(3000);
		for (int i = 1; i <= times; i++) {
			driver.navigate().back();
		}
		iLogMessage("Navigated back");
	}

	
	public static void iScrollToTheElementByLocator(By loc, String nameofelement) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = getElement(loc);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		iLogMessage("scrolled till the " + nameofelement);

	}

	public static void iScrollToTheElement(WebElement ele, String nameofelement) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
		iLogMessage("scrolled till the " + nameofelement);
	}

	public static void clickForcefully(WebElement ele, String name) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", ele);
	}

	public void handleAlert(String decision) {
		if (decision.equals("accept"))
			driver.switchTo().alert().accept();
		else
			driver.switchTo().alert().dismiss();
	}

	public static void hoverOverElement(WebElement element, String name) {
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		iLogMessage("Hover to the " + name);
	}

	public static void scrollPage(String to) throws Exception {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		if (to.equals("end"))
			executor.executeScript(
					"window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");
		else if (to.equals("top"))
			executor.executeScript(
					"window.scrollTo(Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight),0);");
		else
			throw new Exception("Exception : Invalid Direction (only scroll \"top\" or \"end\")");
	}

	public void switchToNewWindow() {
		String old_win = driver.getWindowHandle();
		for (String winHandle : driver.getWindowHandles())
			old_win = winHandle;
		driver.switchTo().window(old_win);
	}

	/**
	 * Method to switch to old window
	 */
	public void switchToOldWindow(String windowhandle) {
		driver.switchTo().window(windowhandle);
	}

	/**
	 * Method to switch to window by title
	 *
	 * @param windowTitle : String : Name of window title to switch
	 */
	public void switchToWindowByTitle(String windowTitle) throws Exception {
		// System.out.println("++"+windowTitle+"++");
		String old_win = driver.getWindowHandle();
		boolean winFound = false;
		for (String winHandle : driver.getWindowHandles()) {
			String str = driver.switchTo().window(winHandle).getTitle();
			// System.out.println("**"+str+"**");
			if (str.equals(windowTitle)) {
				winFound = true;
				break;
			}
		}
		if (!winFound)
			throw new Exception("Window having title " + windowTitle + " not found");
	}

	/**
	 * Method to close new window
	 */
	public void closeNewWindow() {
		driver.close();
	}

	/**
	 * Method to switch frame using web element frame
	 *
	 * @param accessType : String : Locator type (index, id, name, class, xpath,
	 *                   css)
	 * @param accessName : String : Locator value
	 */

	/**
	 * method to switch to default content
	 */
	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}

	public static String schoolCodeGenrator() {
		String chars = "abcdefghijklmnopqrstuvwzyz0123456789";
		String school = "SCH";
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		}
		return school + sb.toString();
	}

	public static String randomNameGenerator() {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		}
		return "Automation name" + sb.toString();

	}

	public static String randomEmailGenerator() {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		}
		return "Automationtest@" + sb.toString() + ".com";

	}

	public static String randomSchoolName() {
		String chars = "0123456789ABCDEFGHijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 9; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		}
		return "Automation public school Indore" + sb.toString();

	}

	public static void iClickElementByLocator(By loc, String elementName) throws InterruptedException {
		Thread.sleep(1000);
		iImplicitlywait(25, elementName);
		WebElement ele = getElement(loc);
		try {
			ele.click();
		} catch (Exception e) {
			SeleniumActions.iClickJSE(ele, elementName);
		}
		iLogMessage("i Click on the element " + elementName);
	}

	public static void selectOptionFromDropdown(By loc, int optionnumber) throws InterruptedException {
		Thread.sleep(1000);
		WebElement ele = getElement(loc);
		iClick(ele, "Dropdown");
		for (int i = 1; i < optionnumber; i++) {
			ele.sendKeys(Keys.chord(Keys.DOWN));
		}
		Thread.sleep(1000);
		ele.sendKeys(Keys.chord(Keys.TAB));
	}

	public static void selectionOptionFromDropdownAfterEntering(By loc, String optionname) throws InterruptedException {
		WebElement ele = getElement(loc);
		ele.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		ele.sendKeys(Keys.chord(Keys.DELETE));
		ele.sendKeys(optionname);
		Thread.sleep(2000);
		ele.sendKeys(Keys.chord(Keys.ENTER));
		Thread.sleep(2000);
		ele.sendKeys(Keys.chord(Keys.TAB));
	}

	public static void refresh() {
		driver.navigate().refresh();
	}

	public static void selectLastRow(By Locator) throws InterruptedException {
		iImplicitlywait(30, "List");
		Thread.sleep(4000);
		List<WebElement> pending = driver.findElements(Locator);
		int size = pending.size();
		Thread.sleep(2000);
		pending.get(size - 1).click();
	}

	public static List<WebElement> getElementList(By elementloactor) {
		iLogMessage("Waiting for elements to be visible");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		List<WebElement> ListOfElement = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(elementloactor));
		return ListOfElement;
	}

	public static void iClickEnter(By locator) throws InterruptedException {
		Thread.sleep(3000);
		WebElement ele = getElement(locator);
		ele.sendKeys(Keys.chord(Keys.ENTER));
	}

	public static WebElement getElementIfPresent(By elementloactor) {
		iLogMessage("Waiting for element to be Present");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
		WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(elementloactor));
		return ele;
	}

	public static void iFocusOnElementAndClcik(By loc, String nameofelement) throws InterruptedException {
		WebElement ele = getElement(loc);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("ele.focus();");
		iClickJSE(ele, "clicked on " + nameofelement);
	}

	public static void iVerifyExactText(String expectedText, By loc, String nameofelement) throws Exception {
		WebElement ele = getElement(loc);
		String actualtext = ele.getText();
		System.out.println("*************" + expectedText);
		System.out.println("***********" + actualtext);
		if (actualtext.trim().equalsIgnoreCase(expectedText.trim())) {
			System.out.println("The expected text contains the actual: " + expectedText);
		} else {
			throw new Exception("Text is not matching");
		}

	}

	public static void iVerifyExactTextElement(String expectedText, WebElement ele, String nameofelement)
			throws Exception {
		String actualtext = ele.getText();
		if (actualtext.equalsIgnoreCase(expectedText)) {
			System.out.println("The expected text contains the actual: " + expectedText);
		} else {
			throw new Exception("Text is not matching");
		}

	}

	public static void iVerifyPartialText(String expectedText, WebElement ele, String nameofelement) throws Exception {
		String actualtext = ele.getText();
		if (actualtext.contains(expectedText)) {
			System.out.println("The expected text contains the actual: " + expectedText);
		} else {
			throw new Exception("Text is not matching");
		}

	}

	public static String iGetTextByLoctor(By locator, String elementname) {
		iLogMessage("Getting the text from" + elementname);
		WebElement ele = getElement(locator);
		return ele.getText();
	}

	public static void iScrollDownCoordinates(int x, int y) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(" + x + ", " + y + ");");
	}

	public static WebElement getElementIfClickable(By elementloactor) {
		iLogMessage("Waiting for element to be Clickable");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(elementloactor));
		return ele;
	}

	public static String GradeProgramCodeGenerator() {
		String chars = "abcdefghijklmnopqrstuvwzyz0123456789";
		String school = "GradePrgCode";
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		}
		return school + sb.toString();
	}



	public static String randomNumberGenerator(int size) {
		String chars = "1234567890";
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		}
		return sb.toString();

	}

	public static String randromPrgOrGradeNameIntakeGenerator() {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		}
		return "GradeorProCode " + sb.toString();

	}

	public static String randromFeeplanNameGenerator() {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		}
		return "FeePlanName " + sb.toString();

	}

	public static String randromExamFeePlanNameGenerator() {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		}
		return "ExamFeePlan " + sb.toString();

	}

	public static String randromPrgFeeplanNameGenerator() {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		}
		return "ProgramFeePlanName " + sb.toString();

	}

	public static String randromProorGradePeriodNameGenerator() {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		}
		return "PeriodName " + sb.toString();

	}

	public static String getTodayDate(String year) {
		DateTimeFormatter td = DateTimeFormatter.ofPattern("dd");
		System.out.println("************************************************" + td);
		DateTimeFormatter tdm = DateTimeFormatter.ofPattern("dd/MM");
		LocalDate now = LocalDate.now();
		String date = (td.format(now) + "/" + year + "");
		return date;
	}

	public static void iValidateElementPresent(By loc) {
		try {
			WebElement alertmessage = SeleniumActions.getElement(loc);
		} catch (Exception e) {
			SeleniumActions.iLogMessage("No such alert found!!");
		}

	}

	public static String randromClassesNameGeneretor() {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		}
		return "Class " + sb.toString();

	}

	public static String randromSelectionProcessName() {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 12; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		}
		return "Automation " + sb.toString();

	}

	public static String randromSelectApplicantFirstName() {
		Faker faker = new Faker();
		String name = faker.name().fullName();
		String firstName = faker.name().firstName();
		return firstName;

	}

	public static String randromSelectApplicantLasttName() {
		Faker faker = new Faker();
		String lastName = faker.name().lastName();
		String chars = "abcdefghijklmnopqrstuvwxyz";
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 5; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		}
		return lastName + sb.toString();

	}
	

	public static String randromSelectApplicantLasttNameNew() {
		Faker faker = new Faker();
		String lastName = faker.name().lastName();
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 5; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		}
		return lastName + sb.toString();

	}


	public static void moveDownUntilElementFoundInList(By loclist, By inp, String value) throws InterruptedException {
		List<WebElement> elemList = getElementList(loclist);
		for (WebElement ele : elemList) {
			System.out.println("******" + ele.getText());
			if (ele.getText().contains(value)) {
				iScrollToTheElement(ele, "ele");
				iClick(ele, "ele");
			} else {
				WebElement in = getElement(inp);
				in.sendKeys(Keys.chord(Keys.ARROW_DOWN));
			}
		}

	}

	public static String randomSubjectCourseGeneration() {
		Faker faker = new Faker();
		String courname = faker.educator().course();
		String coursename = courname.replaceAll("[^A-Za-z0-9 ]", "").trim();
		String chars = "abcdefghijklmnopqrstuvwxyz";
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		}
		return coursename + sb.toString();

	}

	public static String randomSubjectCourseCodeGeneration() {
		String chars = "abcdefghijklmnopqrstuvwxyz";
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		}
		return "Coursecode " + sb.toString();

	}

	public static String randomAssessmentNameGeneration() {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		}
		return "Assmnt Method " + sb.toString();

	}

	public static String randomEventNameGeneration() {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		}
		return "Assessment Event " + sb.toString();

	}

	public static String randomSectionNameGeneration() {
		Faker faker = new Faker();

		String course = faker.educator().campus().replace("Campus", "").replaceAll("[^A-Za-z0-9]", "");
		String chars = "abcdefghijklmnopqrstuvwxyz";
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		}
		return ((course + sb.toString()).replace(" ", ""));

	}

	public static String randomAssessmentSchemeNameGeneration() {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		}
		return "Assessment Scheme " + sb.toString();

	}

	public static String AssessmentSchemeRuleNameGeneration() {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		}
		return "AssmntRule " + sb.toString();

	}

	public static String AssessmentTypeRuleNameGeneration() {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		}
		return "AssmntTypeRule " + sb.toString();

	}

	public static String AssessmentSubTypeRuleNameGeneration() {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		}
		return "AsmntSubTypeRule " + sb.toString();

	}

	public static String AssessmentMethodRuleNameGeneration() {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		}
		return "AssmntMthdRule " + sb.toString();

	}

	public static void VerifyBrokenLinksandPrint() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<WebElement> allURLs = driver.findElements(By.tagName("a"));
		System.out.println("The number of URLs on page is " + allURLs.size());
		System.out.println("***********************************************");
		System.out.println();
		System.out.println("The all URLs on the page is:");
		for (int i = 0; i < allURLs.size(); i++) {

			WebElement url = allURLs.get(i);
			String urlname = url.getAttribute("href");
			// System.out.println(urlname);
			verifyLinks(urlname);

		}
	}

	public static void verifyLinks(String linkUrl) {
		try {
			URL url = new URL(linkUrl);

			// Now we will be creating url connection and getting the response code
			HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();
			httpURLConnect.setConnectTimeout(5000);
			httpURLConnect.connect();
			if (httpURLConnect.getResponseCode() >= 400) {
				System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage() + "is a broken link");
			}

			// Fetching and Printing the response code obtained
			else {
				System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage());
			}
		} catch (Exception e) {
		}
	}

	public static void clickDownKey(By loc) throws InterruptedException {
		WebElement ele = getElement(loc);
		ele.sendKeys(Keys.chord(Keys.ARROW_DOWN));
		System.out.println("Clicked down key");
		Thread.sleep(2000);

	}

	public static String revalCodeGeneration() {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		}
		return "revCod " + sb.toString();

	}

	public static String revalNameGeneration() {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		}
		return "revalName " + sb.toString();
	}

	public static void iClickJSEByLocator(By loc, String elementName) {
		WebElement ele = SeleniumActions.getElement(loc);
		iImplicitlywait(30, elementName);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", ele);
		iLogMessage("i Click on the element " + elementName);

	}

	public static String paymentGrpCode() {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		}
		return "PaymentGrpCode " + sb.toString();

	}

	public static String paymentGrpName() {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		}
		return "PaymentGrp " + sb.toString();

	}

	public static String courseFeeDefNameGenerator() {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		}
		return "CourseFeeDef " + sb.toString();

	}

	public static String markSheetJobName() {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		}
		return "Auto " + sb.toString();

	}

	public static void getcoordinatesandclick(By loc, String name) {

		WebElement element = SeleniumActions.getElement(loc);
		Actions actions = new Actions(driver);

		int getX = element.getLocation().getX();
		System.out.println("X coordinate: " + getX);

		int getY = element.getLocation().getY();
		System.out.println("X coordinate: " + getY);

		actions.moveToElement(element, getX / 2, getY / 2).click();
		actions.build().perform();

//	    JavascriptExecutor executor = (JavascriptExecutor) driver;
//        executor.executeScript("window.scroll(" + getX + ", " + getY + ");");
//        executor.executeScript("arguments[0].click();", element);

	}

	public static String fatherFirstName() {
		Faker indfaker = new Faker(new Locale("en-IND"));
		String firstname = String.format(indfaker.address().firstName());
//		System.out.println(String.format("City Names in India : %s", indfaker.address().city()));
		System.out.println(firstname);
		return firstname;
	}

	public static String fatherLastName() {
		Faker indfaker = new Faker(new Locale("en-IND"));
		String lastname = String.format(indfaker.address().lastName());
		System.out.println(lastname);
		return lastname;
	}

	public static String fatherEmailidName() {
		Faker indfaker = new Faker(new Locale("en-IND"));
		String emailid = String.format(indfaker.internet().emailAddress());
		System.out.println(emailid);
		return emailid;
	}

	public static String randomRoomTypeCreater() {
		Faker faker = new Faker();
		String roomname = "Room";
		String chars = "abcdefghijklmnopqrstuvwxyz";
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 5; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		}
		return roomname + sb.toString();

	}

	public static String examCenterCode() {
		Faker indfaker = new Faker(new Locale("en-IND"));
		String centercode = "Center " + indfaker.regexify("[a-zA-Z]{4}");
		System.out.println(centercode);
		return centercode;
	}

	public static String CityName() {
		Faker indfaker = new Faker(new Locale("en-IND"));
		String cityname = String.format(indfaker.address().city());
		System.out.println(cityname);
		return cityname;
	}

	public static String remarkCode() {
		Faker indfaker = new Faker(new Locale("en-IND"));
		String remarkcode = "Remark " + indfaker.regexify("[a-zA-Z]{4}");
		System.out.println(remarkcode);
		return remarkcode;
	}

	public static String generateAssessmentGroupName() {
		Faker indfaker = new Faker(new Locale("en-IND"));
		String assesgroupname = "AssessmentGroup " + indfaker.regexify("[a-zA-Z]{5}");
		System.out.println(assesgroupname);
		return assesgroupname;
	}

	public static String generateTopicName() {
		Faker indfaker = new Faker(new Locale("en-IND"));
		String topic = "Topic " + indfaker.regexify("[a-zA-Z]{5}");
		System.out.println(topic);
		return topic;
	}

	public static void iRightClick(By locator, String elementname) {
		WebElement ele = getElement(locator);
		Actions actions = new Actions(driver);
		actions.contextClick(ele).perform();
	}

	public static String generateHallticketNumber() {

		Calendar cal = Calendar.getInstance();
		int vlaue = cal.get(Calendar.YEAR);
		String year = Integer.toString(vlaue).substring(2, 4);
		Faker faker = new Faker();
		String hallticketnum = year + faker.regexify("[A-Z]{2}" + "[0-9]{1}" + "[A-Z]{1}" + "[0-9]{4}");
		return hallticketnum;

	}

	public static String unitTitlename() {
		Faker indfaker = new Faker(new Locale("en-IND"));
		String topic = "LPD_" + indfaker.regexify("[A-Z]{4}");
		System.out.println(topic);
		return topic;
	}

	public static String topicCount() {
		Faker indfaker = new Faker(new Locale("en-IND"));		
		String topic = indfaker.regexify("[1-9]{1}");
		System.out.println(topic);
		return topic;
	}
	
	public static void iVerifyExactTextValues(String expectedText, String actualtext) throws Exception {		
		System.out.println("*************" + expectedText);
		System.out.println("***********" + actualtext);
		if (actualtext.trim().equalsIgnoreCase(expectedText.trim())) {
			System.out.println("Expected and Actual Text are mathched.");
		} else {
			throw new Exception("Text is not matching");
		}

	}
	
	public static void iVerifyPartialExactTextValues(String expectedText, String actualtext) throws Exception {		
		System.out.println("*************" + expectedText);
		System.out.println("***********" + actualtext);
		int n = expectedText.length();
		if (actualtext.trim().contains(expectedText.trim())) {
			System.out.println("Expected and Actual Text are mathched.");
		} else {
			throw new Exception("Text is not matching");
		}
	}
	
	public static void iClickEnterWithOutLocator() throws InterruptedException {
		Thread.sleep(2000);
		try {
			Actions actions = new Actions(driver);
			actions.sendKeys(Keys.ENTER).build().perform();			
		} catch (Exception e) {
			((WebElement) driver).sendKeys(Keys.chord(Keys.ENTER));
		}		
	}
	
	public static String previousdayattendance(String days)throws Exception{
		int day = Integer.parseInt(days);
		LocalDate todayDate = LocalDate.now();
	     LocalDate previousDate = todayDate.minusDays(day);
	     
	     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	     String date = previousDate.format(formatter);
	     System.out.println("Previous Attendace date is : "+date);
	     return date;
	}

	public static String assignmentSubmissiondate(String days)throws Exception{
		int day = Integer.parseInt(days);
		LocalDate todayDate = LocalDate.now();
	     LocalDate previousDate = todayDate.plusDays(day);
	     
	     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	     String date = previousDate.format(formatter);
	     System.out.println("Previous Attendace date is : "+date);
	     return date;
	}
	public static String assignmentName() {
		
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 5; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		}
		return "Assignment " + sb.toString();
	}

	
	
	 private static int extractYear(String str) {
	        return Integer.parseInt(str.replaceAll("[^0-9]", "").substring(0, 4));
	    }

	  private static double extractVersion(String str) {
	        return Double.parseDouble(str.replaceAll("[^0-9.]", "").substring(4));
	    }
	  
	  
	  public static List<String> sort(List<String> list){
		  
		  list.sort(Comparator.comparing(SeleniumActions::extractYear).thenComparing(SeleniumActions::extractVersion));  
		
	      return list;
		  
	  }

	}


