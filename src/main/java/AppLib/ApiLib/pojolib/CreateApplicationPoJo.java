package AppLib.ApiLib.pojolib;

import CoreLib.VariableUtil.GlobalVariable;

public class CreateApplicationPoJo extends GlobalVariable{
	

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


}
