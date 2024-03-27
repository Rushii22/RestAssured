package github;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class HowToCaptureMultipleData {
	
	@Test
	public void CreateRepo() {
		HashMap rb=new HashMap();
		rb.put("name", "Virat");
		rb.put("description", "cricketer");
		rb.put("private", true);
		
		 Response createrepoResp = RestAssured.given().contentType(ContentType.JSON)
		.body(rb).log().all()
		.header("Authorization","Bearer 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")
		.when().post("https://api.github.com/user/repos");
		
		System.out.println(createrepoResp.getStatusCode());
		System.out.println(createrepoResp.jsonPath().get("name"));
		System.out.println(createrepoResp.jsonPath().get("description"));
		System.out.println(createrepoResp.jsonPath().get("private"));
		System.out.println(createrepoResp.jsonPath().get("owner.login"));

	Assert.assertEquals(createrepoResp.getStatusCode(), 201);
	
	
	}
	
	@Test
	public void deleteRepo() {
		
		RestAssured.given().header("Authorization","Bearer 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")
		.when().delete("https://api.github.com/repos/Rushii22/Virat")
		.then().assertThat().statusCode(204);
		
		
		
	}

}
