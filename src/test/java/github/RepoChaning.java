package github;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;

public class RepoChaning {
	String baseURL="https://api.github.com";
	String token="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";
	
	
	@Test(priority = 1)
	public void CreateRepo() {
		HashMap cb=new HashMap();
		cb.put("name", "SRK");
		cb.put("description", "Actor");
		cb.put("private", true);
		
		RestAssured.given().contentType(ContentType.JSON).body(cb)
		.header("Authorization","Bearer "+token)
		.when().post(baseURL+"/user/repos")
		.then().assertThat().statusCode(201).body("name", equalTo("SRK"))
		.body("description", equalTo("Actor")).body("private", equalTo(true));
	
}
	
	//Path Parameter
	@Test(priority = 2)
	public void getRepo() {

		RestAssured.given().log().all().header("Authorization","Bearer "+token)
		.pathParam("owner", "Rushii22")
		.pathParam("repo", "SRK")
		.when().get(baseURL+"/repos/{owner}/{repo}")
		.then().assertThat().statusCode(200).body("name", equalTo("SRK"))
		.body("description", equalTo("Actor")).body("private", equalTo(true));
		
	
		
	}
	@Test(priority = 3)
	public void updateRepo() {
		HashMap cb=new HashMap();
		cb.put("name", "SRK");
		cb.put("description", "Producer");
		cb.put("private", false);
		
		
		RestAssured.given().contentType(ContentType.JSON).body(cb)
		.pathParam("owner", "Rushii22")
		.pathParam("repo", "SRK")
		.header("Authorization","Bearer "+token)
		.when().patch(baseURL+"/repos/{owner}/{repo}")
		.then().assertThat().statusCode(200).body("name", equalTo("SRK"))
		.body("description", equalTo("Producer")).body("private", equalTo(false));
		
		
	}
	@Test(priority = 4)
	public void deleteRepo() {
		RestAssured.given().header("Authorization","Bearer "+token)
		.pathParam("owner", "Rushii22")
		.pathParam("repo", "SRK")
		.when().delete(baseURL+"/repos/{owner}/{repo}")
		.then().assertThat().statusCode(204);
		
		
	}
	
}