package differentWaystoPassBody;


import java.io.File;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;


public class UsingExtFile {
	

	public void createUser() {
		File f = new File("./src/test/resources/body") ;
		
		RestAssured.given().contentType(ContentType.JSON)
		.body(f).log().all()
		.when().post("https://reqres.in/api/users")
		.then().assertThat().statusCode(201).log().all();
	}

}
