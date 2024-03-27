package github;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class HowToCaptureSingledata {
	
	@Test
	public void CreateRepo() {
		HashMap rb=new HashMap();
		rb.put("name", "Virat");
		rb.put("description", "cricketer");
		rb.put("private", true);
		
		String repoName=RestAssured.given().contentType(ContentType.JSON)
		.body(rb).log().all()
		.header("Authorization","Bearer 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")
		.when().post("https://api.github.com/user/repos").jsonPath().get("name");
		
		System.out.println(repoName);
		
	}
	
	@Test
	public void deleteRepo() {
		
		RestAssured.given().header("Authorization","Bearer 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")
		.when().delete("https://api.github.com/repos/Rushii22/Virat")
		.then().assertThat().statusCode(204);
		
		
		
	}
	
	
	
	

}
