package differentWaystoPassBody;

import org.testng.annotations.Test;

import POJO.createUser;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class UsingPOJO {
	
	@Test
	public void createuser() {
		
		
		createUser cu=new createUser();
		cu.setName("SRK");
		cu.setJob("actor");
		
		RestAssured.given().contentType(ContentType.JSON)
		.body(cu).log().all()
		.when().post("https://reqres.in/api/users")
		.then().assertThat().statusCode(201).log().all();
	}
		


}
