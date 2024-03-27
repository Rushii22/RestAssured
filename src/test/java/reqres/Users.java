package reqres;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class Users {
	
	String baseURL="https://reqres.in";
	
	@Test
	public void creatUser() {
		//given()-Input
		//when()-HTTP method along with URL
		//then()-Validation
		RestAssured.given().body("{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"leader\"\r\n"
				+ "}").contentType(ContentType.JSON)
		.when().post(baseURL+"/api/users")
		.then().assertThat().statusCode(201).log().all();
		
	}
	
	@Test
	public void getsingleUser() {
		//given()-Input
		//when()-HTTP method along with URL
		//then()-Validation
		RestAssured.given()
		.when().get(baseURL+"/api/users/2")
		.then().assertThat().statusCode(200).log().all();
		
	}
		
	@Test
	public void getListUser() {
		//given()-Input
		//when()-HTTP method along with URL
		//then()-Validation
		RestAssured.given()
		.when().get(baseURL+"/api/users?page=2")
		.then().assertThat().statusCode(200).log().all();
		
	}
		
	@Test
	public void getsingleusernotfound() {
		//given()-Input
		//when()-HTTP method along with URL
		//then()-Validation
		RestAssured.given()
		.when().get(baseURL+"/api/users/23")
		.then().assertThat().statusCode(404).log().all();
		
	}
	
	@Test
	public void getListRESOURCE() {
		//given()-Input
		//when()-HTTP method along with URL
		//then()-Validation
		RestAssured.given()
		.when().get(baseURL+"/api/unknown")
		.then().assertThat().statusCode(200).log().all();
		
	}
	
	@Test
	public void getsingleRESOURCE() {
		//given()-Input
		//when()-HTTP method along with URL
		//then()-Validation
		RestAssured.given()
		.when().get(baseURL+"/api/unknown/2")
		.then().assertThat().statusCode(200).log().all();
		
	}
	
	@Test
	public void getsingleRESOURCENotFound() {
		//given()-Input
		//when()-HTTP method along with URL
		//then()-Validation
		RestAssured.given()
		.when().get(baseURL+"/api/unknown/23")
		.then().assertThat().statusCode(404).log().all();
		
	}
	

	@Test
	public void Update() {
		//given()-Input
		//when()-HTTP method along with URL
		//then()-Validation
		RestAssured.given().body("{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"zion resident\"\r\n"
				+ "}").contentType(ContentType.JSON)
		.when().put(baseURL+"/api/users/2")
		.then().assertThat().statusCode(200).log().all();
		
		
	}

	@Test
	public void Update1() {
		//given()-Input
		//when()-HTTP method along with URL
		//then()-Validation
		RestAssured.given().body("{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"zion resident\"\r\n"
				+ "}").contentType(ContentType.JSON)
		.when().patch(baseURL+"/api/users/2")
		.then().assertThat().statusCode(200).log().all();
		
	}
	
	@Test
	public void Delete() {
		//given()-Input
		//when()-HTTP method along with URL
		//then()-Validation
		RestAssured.given()
		.when().delete(baseURL+"/api/users/2")
		.then().assertThat().statusCode(204).log().all();
		
	}

	@Test
	public void REGISTER_SUCCESSFUL() {
		//given()-Input
		//when()-HTTP method along with URL
		//then()-Validation
		RestAssured.given().body("{\r\n"
				+ "    \"email\": \"eve.holt@reqres.in\",\r\n"
				+ "    \"password\": \"pistol\"\r\n"
				+ "}").contentType(ContentType.JSON)
		.when().post(baseURL+"/api/register")
		.then().assertThat().statusCode(200).log().all();
		
	}
	
	@Test
	public void REGISTER_UNSUCCESSFUL() {
		//given()-Input
		//when()-HTTP method along with URL
		//then()-Validation
		RestAssured.given().body("{\r\n"
				+ "    \"email\": \"sydney@fife\"\r\n"
				+ "}").contentType(ContentType.JSON)
		.when().post(baseURL+"/api/register")
		.then().assertThat().statusCode(400).log().all();
		
	}
	

	@Test
	public void Login_SUCCESSFUL() {
		//given()-Input
		//when()-HTTP method along with URL
		//then()-Validation
		RestAssured.given().body("{\r\n"
				+ "    \"email\": \"eve.holt@reqres.in\",\r\n"
				+ "    \"password\": \"cityslicka\"\r\n"
				+ "}").contentType(ContentType.JSON)
		.when().post(baseURL+"/api/login")
		.then().assertThat().statusCode(200).log().all();
		
	}
	

	@Test
	public void Login_UNSUCCESSFUL() {
		//given()-Input
		//when()-HTTP method along with URL
		//then()-Validation
		RestAssured.given().body("{\r\n"
				+ "    \"email\": \"peter@klaven\"\r\n"
				+ "}").contentType(ContentType.JSON)
		.when().post(baseURL+"/api/login")
		.then().assertThat().statusCode(400).log().all();
		
	}
	

	@Test
	public void get_DELAYED_RESPONSE() {
		//given()-Input
		//when()-HTTP method along with URL
		//then()-Validation
		RestAssured.given()
		.when().get(baseURL+"/api/users?delay=3")
		.then().assertThat().statusCode(200).log().all();
		
	}
}
