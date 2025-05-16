package AppLib.ApiLib.payloadutils;

import AppLib.ApiLib.pojolib.CreateApplicationPoJo;
import CoreLib.VariableUtil.GlobalVariable;
import CoreLib.stringutil.StringUtils;

public class CreateApplicationPayload extends CreateApplicationPoJo {
	
		public static String CheckDuplicateApplicationpayloaddata() {
			
			setLastname(StringUtils.randomApplicantLastname());
			
		return "{\r\n"
				+ "    \"studentFirstName\": \"Applicant\",\r\n"
				+ "    \"studentMiddleName\": \"\",\r\n"
				+ "    \"studentLastName\": \""+getLastname()+"\",\r\n"
				+ "    \"mobileCountryCode\": \"+91\",\r\n"
				+ "    \"mobileNo\": \"9987456525\",\r\n"
				+ "    \"email\": \"test@gmail.com\",\r\n"
				+ "    \"dateOfBirth\": \"2025-05-15\",\r\n"
				+ "    \"programBatchSeatTypeIdString\": \"1802\"\r\n"
				+ "}";
	}
public static String CreaApplicatetionpayloaddata() {
			
		String date = StringUtils.todayDate();	
			
		return "{\r\n"
				+ "    \"application\": {\r\n"
				+ "        \"id\": \"\",\r\n"
				+ "        \"saleRefType\": \"\",\r\n"
				+ "        \"saleRefId\": \"\",\r\n"
				+ "        \"submissionDate\": \""+date+"\",\r\n"
				+ "        \"person\": {\r\n"
				+ "            \"id\": \"\",\r\n"
				+ "            \"version\": \"\",\r\n"
				+ "            \"salutation\": {\r\n"
				+ "                \"id\": 1\r\n"
				+ "            },\r\n"
				+ "            \"firstName\": \"Applicant\",\r\n"
				+ "            \"middleName\": \"\",\r\n"
				+ "            \"lastName\": \""+getLastname()+"\",\r\n"
				+ "            \"gender\": \"\",\r\n"
				+ "            \"birthDate\": \"2025-05-15\",\r\n"
				+ "            \"isEmployeeOfOrganization\": \"\",\r\n"
				+ "            \"emailId\": \"test@gmail.com\",\r\n"
				+ "            \"mobileNumber\": \"9987456525\",\r\n"
				+ "            \"mobileCountryCode\": \"+91\",\r\n"
				+ "            \"phoneNo\": \"\",\r\n"
				+ "            \"phoneAreaCode\": \"\",\r\n"
				+ "            \"phoneCountryCode\": \"\",\r\n"
				+ "            \"employeeId\": null,\r\n"
				+ "            \"status\": \"ACTIVE\",\r\n"
				+ "            \"shortName\": \"Applicant "+getLastname()+"\",\r\n"
				+ "            \"printName\": \"Applicant "+getLastname()+"\",\r\n"
				+ "            \"genderCSM\": {\r\n"
				+ "                \"id\": 1\r\n"
				+ "            },\r\n"
				+ "            \"externalSysRefOne\": \"\",\r\n"
				+ "            \"externalSysRefTwo\": \"\",\r\n"
				+ "            \"externalSysRefThree\": \"\",\r\n"
				+ "            \"firstNameOtherLanguage\": \"\",\r\n"
				+ "            \"middleNameOtherLanguage\": \"\",\r\n"
				+ "            \"lastNameOtherLanguage\": \"\",\r\n"
				+ "            \"shortNameOtherLanguage\": \"\",\r\n"
				+ "            \"printNameOtherLanguage\": \"\"\r\n"
				+ "        },\r\n"
				+ "        \"version\": \"\",\r\n"
				+ "        \"applicationFormId\": \"\",\r\n"
				+ "        \"token\": null,\r\n"
				+ "        \"currentStatus\": \"FORM_SALE_TOKEN_GENERATED\",\r\n"
				+ "        \"code\": \"\",\r\n"
				+ "        \"existingStudentId\": \"\",\r\n"
				+ "        \"enquiryStudentDetailId\": \"\",\r\n"
				+ "        \"applicationOwner\": {\r\n"
				+ "            \"id\": 1\r\n"
				+ "        }\r\n"
				+ "    },\r\n"
				+ "    \"programs\": [\r\n"
				+ "        {\r\n"
				+ "            \"version\": \"\",\r\n"
				+ "            \"progBatchSeatConfigId\": 1802,\r\n"
				+ "            \"programId\": 176,\r\n"
				+ "            \"currentStatus\": \"ACTIVE\",\r\n"
				+ "            \"priority\": 1,\r\n"
				+ "            \"academyLocationId\": 1,\r\n"
				+ "            \"distancetoTravel\": 0,\r\n"
				+ "            \"programGroupId\": 1,\r\n"
				+ "            \"whetherHostelApplicable\": false,\r\n"
				+ "            \"whetherTransportApplicable\": false\r\n"
				+ "        }\r\n"
				+ "    ],\r\n"
				+ "    \"applicantPersonRelationships\": [],\r\n"
				+ "    \"whehterDirectSubmission\": false\r\n"
				+ "}";
	}

	public static String submitformdata() {
		return "applicationId="+GlobalVariable.applicationId+"";
	}
	
	public static String approveApplicationPayload() {
		return "{\r\n"
				+ "    \"currentStatus\": \"APPROVED\",\r\n"
				+ "    \"applicationId\": "+GlobalVariable.applicationId+",\r\n"
				+ "    \"allSectionsVerified\": null,\r\n"
				+ "    \"allDocumentsVerified\": null,\r\n"
				+ "    \"reasonId\": 1,\r\n"
				+ "    \"remark\": null,\r\n"
				+ "    \"applicationProgramIds\": [\r\n"
				+ "        56741\r\n"
				+ "    ]\r\n"
				+ "}";
	}


}
