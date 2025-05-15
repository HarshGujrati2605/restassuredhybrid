package AppLib.ApiLib.sepcbuilderutil;

import AppLib.ApiLib.pojolib.AlumniPojo;
import CoreLib.javautilities.BaseClass;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class Academiaspecs extends AlumniPojo {
	
	public static String token = getAccessToken();

	public static RequestSpecification academiaspecbuilder() throws Exception {

		RequestSpecification req = new RequestSpecBuilder().setBaseUri(BaseClass.getUrl())
				.addHeader("Content-Type", "application/json").addHeader("Authorization", "Bearer " + token + "")
				.build();
		return req;
	}

	public static RequestSpecification academiaspecbuildertexttype() throws Exception {

		RequestSpecification req = new RequestSpecBuilder().setBaseUri(BaseClass.getUrl())
				.addHeader("Content-Type", "text/plain")
				.addHeader("Authorization", "Bearer "+token+"").build();
		return req;
	}
	
	
}
