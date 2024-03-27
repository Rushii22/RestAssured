package differentWaystoPassBody;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

public class Pet {
	 
	String baseURL="https://petstore.swagger.io/v2";
	long petID;
	
	//post
	@Test(priority = 1)
	public void createPet() {
		HashMap categ=new HashMap();
		categ.put("name", "dog");
		
		HashMap cb=new HashMap();
		cb.put("category", categ);
		cb.put("name", "scooby");
		cb.put("status", "available");
		
		String createBodyResp = RestAssured.given().contentType(ContentType.JSON)
		.body(cb).log().all()
		.when().post(baseURL+"/pet")
		.then().assertThat().statusCode(200).log().all()
		.extract().response().asString();
		
		System.out.println(createBodyResp);
		
		JsonPath js=new JsonPath(createBodyResp);
		petID=js.get("id");
		System.out.println(petID);
		//System.out.println(js.get("id"));

	//	String name = js.get("category.name");
	//	System.out.println(name);
			
	}
	
	//getPet
	@Test(priority = 2)
	public void getPet() {
		
		RestAssured.given().log().all()
		.when().get(baseURL+"/pet/"+petID)
		.then().assertThat().statusCode(200).log().all();
		
	}

	//updatePet
	@Test(priority = 3)
	public void updatePet() {
		HashMap categ=new HashMap();
		categ.put("name", "dog");
		
		HashMap ub=new HashMap();
		ub.put("category", categ);
		ub.put("name", "Leo");
		ub.put("status", "unavailable");
		ub.put("id", petID);
		

		RestAssured.given().contentType(ContentType.JSON).body(ub).log().all()
		.when().put(baseURL+"/pet")
		.then().assertThat().statusCode(200).log().all();
		
		
	}
	
	//deletePet
	@Test(priority = 4)
	public void deletePet() {
		RestAssured.given().log().all()
		.when().delete(baseURL+"/pet/"+petID)
		.then().assertThat().statusCode(200).log().all();
		
	}


}
