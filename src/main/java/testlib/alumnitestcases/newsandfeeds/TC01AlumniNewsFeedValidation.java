package testlib.alumnitestcases.newsandfeeds;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;

import org.testng.annotations.Test;

import AppLib.ApiLib.pojolib.AlumniPojo;
import AppLib.ApiLib.sepcbuilderutil.Academiaspecs;
import CoreLib.JsonUtil.JsonConversionUtilities;
import CoreLib.javautilities.BaseClass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloadutilitis.PayloadAlumniNews;
import variableutility.GlobalVariable;

public class TC01AlumniNewsFeedValidation extends AlumniPojo{

	@Test(testName = "Alumni news feed creation")
	public void postalumninewsandfeeds() throws Exception {
		setAccessToken(BaseClass.getAccessToken());
		RestAssured.basePath = "/rest/alumniNewsFeeds/saveNewsAndFeeds";
		RestAssured.useRelaxedHTTPSValidation();
		String response = given().log().all().spec(Academiaspecs.academiaspecbuilder()).contentType(ContentType.JSON)
				.accept(ContentType.JSON).body(PayloadAlumniNews.alumninewspayload()).when().post().then().log().all()
				.statusCode(200).extract().response().asString();

	}
	

	@Test(testName = "Search Created Alumni news feed", dependsOnMethods = "postalumninewsandfeeds")
	public void searchalumninewsandfeeds() throws Exception {
		RestAssured.basePath = "/rest/alumniNewsFeeds/gridData";
		RestAssured.useRelaxedHTTPSValidation();
		Response response = given().log().all().spec(Academiaspecs.academiaspecbuilder())
				.queryParam("_dc", "1747030864292").queryParam("newsCode", "")
				.queryParam("newsTitle", "" + GlobalVariable.alumninewsfeedname + "").queryParam("status", "")
				.queryParam("startDate", "").queryParam("endDate", "").queryParam("page", 1).queryParam("start", 0)
				.queryParam("limit", 10).accept(ContentType.JSON).when().get().then().log().all().statusCode(200)
				.extract().response();
		//GlobalVariable.alumninewsid =JsonConversionUtilities.getRawToJsonDataInt(response.asString(), "rows[0].id") ;
	    setAlumninewsid(JsonConversionUtilities.getRawToJsonDataInt(response.asString(), "rows[0].id"));
	
	}

	@Test(testName = "Created Alumni news feed validations", dependsOnMethods = "searchalumninewsandfeeds")
	public void validatealumninewsandfeeds() throws Exception {
		RestAssured.basePath = "/rest/alumniNewsFeeds/newsFeedsDataById";
		RestAssured.useRelaxedHTTPSValidation();
		Response response = given().log().all().spec(Academiaspecs.academiaspecbuilder())
				.queryParam("_dc", "1747030864292").queryParam("_dc", "1741347827707")
				.queryParam("newsId", GlobalVariable.alumninewsid).accept(ContentType.JSON).when().get().then().log()
				.all().statusCode(200).extract().response();

		String title = JsonConversionUtilities.getRawToJsonData(response.asString(), "title");
		System.out.println(title);
		String status = JsonConversionUtilities.getRawToJsonData(response.asString(), "status");
		System.out.println(status);
		//GlobalVariable.alumninewscode = JsonConversionUtilities.getRawToJsonData(response.asString(), "code");
		setAlumninewscode( JsonConversionUtilities.getRawToJsonData(response.asString(), "code"));	
		System.out.println(getAlumninewscode());
		assertEquals(title, getAlumninewsfeedname());
		assertEquals(status, "UNPUBLISHED");

	}
	
	
	@Test(testName = "Alumni news feed editing" , dependsOnMethods = "validatealumninewsandfeeds")
	public void editalumninewsandfeeds() throws Exception {

		RestAssured.basePath = "/rest/alumniNewsFeeds/saveNewsAndFeeds"; 
		RestAssured.useRelaxedHTTPSValidation();
		String response = given().log().all().spec(Academiaspecs.academiaspecbuilder()).contentType(ContentType.JSON)
				.accept(ContentType.JSON).body(PayloadAlumniNews.alumninewspayloadEdit()).when().post().then().log().all()
				.statusCode(200).extract().response().asString();
		
	}
	
	@Test(testName = "Alumni news feed Deleting" , dependsOnMethods = "editalumninewsandfeeds" , enabled = false)
	public void deletealumninewsandfeeds() throws Exception {

		RestAssured.basePath = "/rest/alumniNewsFeeds/newsFeedDelete"; 
		RestAssured.useRelaxedHTTPSValidation();
		String response = given().log().all().spec(Academiaspecs.academiaspecbuildertexttype())
				.body(PayloadAlumniNews.alumninewspayloadDelete())
				.when().post().then().log().all()
				.statusCode(200).extract().response().asString();
		assertEquals(response.trim(), "Success");
		
	}

}
