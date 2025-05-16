package AppLib.ApiLib.pojolib;

import CoreLib.VariableUtil.GlobalVariable;

public class CreateApplicationPoJo extends GlobalVariable{
	
	public static String getApplicantAdmissionId() {
		return applicantAdmissionId;
	}

	public static void setApplicantAdmissionId(String applicantAdmissionId) {
		GlobalVariable.applicantAdmissionId = applicantAdmissionId;
	}

	public static String getApplicationId() {
		return applicationId;
	}

	public static void setApplicationId(String applicationId) {
		GlobalVariable.applicationId = applicationId;
	}

	public static String getLastname() {
		return applicantlastname;
	}

	public static void setLastname(String applicantlastname) {
		GlobalVariable.applicantlastname = applicantlastname;
	}
	
	public static String getAccessToken() {
		return accessToken;
	}

	public static void setAccessToken(String accessToken) {
		GlobalVariable.accessToken = accessToken;
	}
	
	public static String getAcademyLocation() {
		return academyLocation;
	}

	public static void setAcademyLocation(String academyLocation) {
		GlobalVariable.academyLocation = academyLocation;
	}


}
