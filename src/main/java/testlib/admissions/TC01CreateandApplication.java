package testlib.admissions;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import AppLib.ApiLib.payloadutils.CreateApplicationPayload;
import AppLib.ApiLib.payloadutils.PayloadProgramModule;
import AppLib.ApiLib.pojolib.CreateApplicationPoJo;
import AppLib.ApiLib.sepcbuilderutil.Academiaspecs;
import CoreLib.JsonUtil.JsonConversionUtilities;
import CoreLib.VariableUtil.GlobalVariable;
import CoreLib.javautilities.BaseClass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class TC01CreateandApplication extends CreateApplicationPoJo {
	
	public static String applicantPersonId;
	
	@Test(testName = "Valiadte the Application status")
	public void ValidateApplicationStatus() throws Exception {
		setAccessToken(BaseClass.getAccessToken());
		RestAssured.basePath = "/rest/application/checkDuplicateApplicationOnErp";
		RestAssured.useRelaxedHTTPSValidation();
		String response = given().log().all().spec(Academiaspecs.academiaspecbuilder()).contentType(ContentType.JSON)
				.accept(ContentType.JSON).body(CreateApplicationPayload.CheckDuplicateApplicationpayloaddata()).log().all().when().post()
				.then().statusCode(200).extract().response().asString();
		
		boolean value = JsonConversionUtilities.getRawToJsonDataBoolean(response, "isDuplicateApplication");
		if (value==false) {
		    System.out.println("This is a not a duplicate application.");
		} else {
		    System.out.println("This is a duplicate application.");
		}
	}

	@Test(testName = "Create the Application", dependsOnMethods = "ValidateApplicationStatus")
	public void creatNewApplication() throws Exception {
		setAccessToken(BaseClass.getAccessToken());
		RestAssured.basePath = "/rest/application/createApplication";
		RestAssured.useRelaxedHTTPSValidation();
		String response = given().log().all().spec(Academiaspecs.academiaspecbuilder()).contentType(ContentType.JSON)
				.accept(ContentType.JSON).body(CreateApplicationPayload.CreaApplicatetionpayloaddata()).log().all().when().post()
				.then().statusCode(200).extract().response().asString();
//		GlobalVariable.applicantAdmissionId = JsonConversionUtilities.getRawToJsonData(response, "formToken");
		GlobalVariable.applicationId = JsonConversionUtilities.getRawToJsonData(response, "applicationId");
		applicantPersonId = JsonConversionUtilities.getRawToJsonData(response, "personId");
		
//		System.out.println(applicantAdmissionId);
		System.out.println(applicationId);
		System.out.println(applicantPersonId);
		}
	
	@Test(testName = "Generate the Application Id", dependsOnMethods = "creatNewApplication")
	public void GenerateApplicationId() throws Exception {
		setAccessToken(BaseClass.getAccessToken());
		RestAssured.basePath = "/rest/application/generateApplicationID";
		RestAssured.useRelaxedHTTPSValidation();
		String response = given().log().all().spec(Academiaspecs.academiaspecbuilder()).contentType(ContentType.JSON)
				.accept(ContentType.JSON).queryParam("applicationId", applicationId)
				.when().post()
				.then().statusCode(204).extract().response().asString();
			}
	
	@Test(testName = "Submit the Application form", dependsOnMethods = "GenerateApplicationId")
	public void SubmitApplicationForm() throws Exception {
		setAccessToken(BaseClass.getAccessToken());
		RestAssured.basePath = "/rest/application/submitForm";
		RestAssured.useRelaxedHTTPSValidation();
		String response = given().log().all().spec(Academiaspecs.academiaspecbuilder()).contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(CreateApplicationPayload.submitformdata()).log().all()
				.when().post()
				.then().statusCode(204).extract().response().asString();
			}
	
	@Test(testName = "Search the Application Data", dependsOnMethods = "SubmitApplicationForm")
	public void ValidateApplicationData() throws Exception {
		setAccessToken(BaseClass.getAccessToken());
		RestAssured.basePath = "/rest/applicationProgram/applicationsToApprove";
		RestAssured.useRelaxedHTTPSValidation();
		String response = given().log().all().spec(Academiaspecs.academiaspecbuilder()).contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.queryParam("applicationId", applicationId)
				.queryParam("page", "1")
				.queryParam("start", "0")
				.queryParam("limit", "1")
				.queryParam("_dc", "1747381155892")
				.when().get()
				.then().statusCode(200).log().all().extract().response().asString();
			}
	
	@Test(testName = "Approve the Application", dependsOnMethods = "ValidateApplicationData")
	public void ApproveApplicationForm() throws Exception {
		setAccessToken(BaseClass.getAccessToken());
		RestAssured.basePath = "/rest/applicationProgram/approveOrRejectOrWithdrawnApplication";
		RestAssured.useRelaxedHTTPSValidation();
		String response = given().log().all().spec(Academiaspecs.academiaspecbuilder()).contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(CreateApplicationPayload.approveApplicationPayload()).log().all()
				.when().post()
				.then().statusCode(204).log().all().extract().response().asString();
		
		System.out.println(response);
			}
	
}
