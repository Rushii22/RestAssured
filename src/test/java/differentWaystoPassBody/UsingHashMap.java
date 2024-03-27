package differentWaystoPassBody;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@Test
public class UsingHashMap {
	
	public void createUser() {
		
		HashMap cb=new HashMap();
		cb.put("name", "aman");
		cb.put("job", "ceo");
		
		RestAssured.given().contentType(ContentType.JSON)
		.body(cb).log().all()
		.when().post("https://reqres.in/api/users/2")
		.then().assertThat().statusCode(201).log().all();
	}
	

}
