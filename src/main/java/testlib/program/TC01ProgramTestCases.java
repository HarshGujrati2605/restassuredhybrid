package testlib.program;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import AppLib.ApiLib.payloadutils.PayloadProgramModule;
import AppLib.ApiLib.pojolib.programpojo;
import AppLib.ApiLib.sepcbuilderutil.Academiaspecs;
import CoreLib.DriverUtil.DriverUtils;
import CoreLib.DriverUtil.WebDriverSingleton;
import CoreLib.JsonUtil.JsonConversionUtilities;
import CoreLib.SeleniumUtil.SeleniumActions;
import CoreLib.javautilities.BaseClass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class TC01ProgramTestCases extends programpojo {

	@Test(priority = 1, testName = "Create Course")
	public void creatNewCourse() throws Exception {
		setAccessToken(BaseClass.getAccessToken());
		RestAssured.basePath = "/rest/course/create";
		RestAssured.useRelaxedHTTPSValidation();
		String response = given().log().all().spec(Academiaspecs.academiaspecbuilder()).contentType(ContentType.JSON)
				.accept(ContentType.JSON).body(PayloadProgramModule.CreateCoursePayload()).log().all().when().post()
				.then().statusCode(200).extract().response().asString();
		System.out.println("Cousre Id : " + response.trim());
		setCourseIdrecived(Integer.parseInt(response));

	}

	@Test(testName = "Program creation", dependsOnMethods = "creatNewCourse")
	public void creatNewProgram() throws Exception {
		RestAssured.basePath = "/rest/program/create";
		RestAssured.useRelaxedHTTPSValidation();
		String response = given().log().all().spec(Academiaspecs.academiaspecbuilder()).contentType(ContentType.JSON)
				.accept(ContentType.JSON).body(PayloadProgramModule.createProgramPayload()).when().post().then().log()
				.all().statusCode(200).extract().response().asString();

		setProgramidreceived(response);
		System.out.println(getProgramidreceived());

	}

	@Test(testName = "Program search and validation", dependsOnMethods = "creatNewProgram")
	public void programSearch() throws Exception {

		RestAssured.basePath = "/rest/program/findById";
		RestAssured.useRelaxedHTTPSValidation();
		String response = given().log().all().spec(Academiaspecs.academiaspecbuilder())
				.queryParam("_dc", "1747125904944").queryParam("id", getProgramidreceived())
				.contentType(ContentType.JSON).accept(ContentType.JSON).when().get().then().log().all().statusCode(200)
				.extract().response().asString();

		assertEquals(JsonConversionUtilities.getRawToJsonData(response, "programName"), getProgramename());

	}

	@Test(testName = "Batch creation", dependsOnMethods = "programSearch")
	public void creatNewProgramBatch() throws Exception {

		RestAssured.basePath = "/rest/batch/create";
		RestAssured.useRelaxedHTTPSValidation();
		String response = given().log().all().spec(Academiaspecs.academiaspecbuilder()).contentType(ContentType.JSON)
				.accept(ContentType.JSON).body(PayloadProgramModule.createProgramBatchPayload()).when().post().then()
				.log().all().statusCode(200).extract().response().asString();

		setBatchidreceived(response);
	}

	@Test(testName = "Seat creation", dependsOnMethods = "creatNewProgramBatch")
	public void creatSeatcType() throws Exception {

		RestAssured.basePath = "/rest/programBatchSeatConfiguration/create";
		RestAssured.useRelaxedHTTPSValidation();
		String response = given().log().all().spec(Academiaspecs.academiaspecbuilder()).contentType(ContentType.JSON)
				.accept(ContentType.JSON).body(PayloadProgramModule.createSeatPayload()).when().post().then().log()
				.all().statusCode(200).extract().response().asString();

	}

	@Test(testName = "Period creation", dependsOnMethods = "creatSeatcType")
	public void creatPeriod() throws Exception {

		RestAssured.basePath = "/rest/programBatchPeriodConfiguration/create";
		RestAssured.useRelaxedHTTPSValidation();
		String response = given().log().all().spec(Academiaspecs.academiaspecbuilder()).contentType(ContentType.JSON)
				.accept(ContentType.JSON).body(PayloadProgramModule.createPeriodPayload()).when().post().then().log()
				.all().statusCode(200).extract().response().asString();

		setPeriodidreceived(response);

	}

	@Test(testName = "Course creation in batch", dependsOnMethods = "creatPeriod")
	public void courseCreationwithBatch() throws Exception {

		RestAssured.basePath = "/rest/programBatchCourse/create";
		RestAssured.useRelaxedHTTPSValidation();
		String response = given().log().all().spec(Academiaspecs.academiaspecbuilder()).contentType(ContentType.JSON)
				.accept(ContentType.JSON).body(PayloadProgramModule.payloadCourseMapping()).when().post().then().log()
				.all().statusCode(200).extract().response().asString();
		
		setBatchcourseidreceived(response);

	}
	
	
//	@Test(testName = "Course creation in batch", dependsOnMethods = "courseCreationwithBatch")
//	public void courseMappingwithBatch() throws Exception {
//
//		RestAssured.basePath = "rest/programCoursePeriod/createUpdateProgramCoursePeriod";
//		RestAssured.useRelaxedHTTPSValidation();
//		String response = given().log().all().spec(newsandfeedspecs.academiaspecbuilder()).contentType(ContentType.JSON)
//				.accept(ContentType.JSON).body(PayloadProgramModule.payloadCourseMappingwithPeriod()).when().post().then().log()
//				.all().statusCode(200).extract().response().asString();
//	
//	}
	
	
	@Test(testName = "Login")
    public void setup() throws Exception {
	
		WebDriverSingleton.setupDriver().get(BaseClass.getUrl());
		WebDriverSingleton.setupDriver().manage().window().maximize();
		
		
		
			
	}
	
	
	
	
	
	
	

}
