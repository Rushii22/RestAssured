package github;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class Repos {
	
	@Test
	public void CreateRepo() {
		
		HashMap rb = new HashMap();
		rb.put("name", "Virat");
		rb.put("description","cricketer");
		rb.put("privet", true);
		
		RestAssured.given().contentType(ContentType.JSON)
		.body(rb).log().all().header("Authorization","Bearer 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")
		.when().post("https://api.github.com/user/repos")
		.then().assertThat().statusCode(201).log().all();
	
	
	}

}
