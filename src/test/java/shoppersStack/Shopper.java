package shoppersStack;

import org.testng.Assert;
import org.testng.annotations.Test;

import POJO.AddAddress;
import POJO.AddReview;
import POJO.OrderAdd;
import POJO.PlaceOrderData;
import POJO.UpdateProductInCart;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class Shopper {
	String baseURL="https://www.shoppersstack.com/shopping";
	int shopperId;
	String token;
	int productId;
	int itemId;
	int addressId; 
	int orderId;
	int reviewId;
	
	@Test(priority = 1)
	public void ShopperLogin() {
		
		POJO.ShopperLogin s1=new POJO.ShopperLogin();
		s1.setEmail("rushiimarathe22@gmail.com");
		s1.setPassword("Marathe@22");
		s1.setRole("SHOPPER");
		
		Response loginResp = RestAssured.given().contentType(ContentType.JSON).body(s1)
		.when().post(baseURL+"/users/login");
		
		 shopperId=loginResp.jsonPath().get("data.userId");
		 token=loginResp.jsonPath().get("data.jwtToken");
		
		System.out.println(shopperId);
		System.out.println(token);
		
		Assert.assertEquals(loginResp.getStatusCode(), 200);
		String email=loginResp.getBody().jsonPath().get("data.email");
		
		Assert.assertEquals(email, "rushiimarathe22@gmail.com");
		
	}
	
	@Test
	 public void viewProducts() {
		  productId = RestAssured.given()
		 .when().get(baseURL+"/products/alpha").jsonPath().get("data[0].productId");
		
		  System.out.println(productId);
		  
	}
	
/*	@Test(priority = 2)
	public void AddProductToWishlist() {
		POJO.AddProductToWishlist add=new POJO.AddProductToWishlist();
		add.setProductId(productId);
		add.setQuantity(0);
		
		RestAssured.given().log().all().body(add).contentType(ContentType.JSON)
		.header("Authorization","Bearer "+token).pathParam("shopperId", shopperId)
		.when().post(baseURL+"/shoppers/{shopperId}/wishlist")
		.then().assertThat().statusCode(201).log().all();
	}
	*/
	
	@Test(priority = 3)
	public void AddProductToCart() {
		
		POJO.AddProductToCart add=new POJO.AddProductToCart();
		add.setProductId(productId);
		add.setQuantity(1);
		
		 itemId=RestAssured.given().body(add).contentType(ContentType.JSON)
		.pathParam("shopperId", shopperId).header("Authorization","Bearer "+token)
		.when().post(baseURL+"/shoppers/{shopperId}/carts").jsonPath().get("data.itemId");
		
		System.out.println(itemId);
	}
	
	@Test(priority = 4)
	public void getProductFromCart() {
		
		  RestAssured.given().contentType(ContentType.JSON)
		 .pathParam("shopperId", shopperId).header("Authorization","Bearer "+token)
		 .when().get(baseURL+"/shoppers/{shopperId}/carts")
		 .then().assertThat().statusCode(200);
					
	}
	
	@Test(priority = 5)
	public void updateProductIncart() {
		UpdateProductInCart up=new UpdateProductInCart();
		up.setProduct(productId);
		up.setQuantity(5);
	
		  RestAssured.given().body(up).contentType(ContentType.JSON)
		 .pathParam("shopperId", shopperId).pathParam("itemId", itemId)
		 .header("Authorization","Bearer "+token)
		 .when().put(baseURL+"/shoppers/{shopperId}/carts/{itemId}");			
		
	}
	
	//@Test(priority = 6)
/*	public void deleteProductFromCart() {

		  RestAssured.given().contentType(ContentType.JSON)
		 .pathParam("shopperId", shopperId).pathParam("productId", productId)
		 .header("Authorization","Bearer "+token)
		 .when().delete(baseURL+"/shoppers/{shopperId}/carts/{productId}");
				
	}
	*/
	
	@Test(priority = 6)
	public void addAddress() {
		AddAddress add=new AddAddress();
		add.setBuildingInfo("Ground floor");
		add.setCity("Banglore");
		add.setCountry("India");
		add.setLandmark("Near BMW Shoeroom");
		add.setName("Mannat");
		add.setPhone("8308766869");
		add.setPincode("560010");
		add.setState("Karnatka");
		add.setStreetInfo("cross road");
		add.setType("Home");
		
		addressId = RestAssured.given().body(add).contentType(ContentType.JSON)
		.pathParam("shopperId", shopperId).header("Authorization","Bearer "+token)
		.when().post(baseURL+"/shoppers/{shopperId}/address").jsonPath().get("data.addressId");
	
		System.out.println(addressId);
				
	}
	//Place order
	@Test(priority = 7)
	public void placeOrder() {
		
		OrderAdd add=new OrderAdd();
		add.setAddressId(addressId);
		
		PlaceOrderData po=new PlaceOrderData();
		po.setAddress(add);;
		po.setPaymentMode("COD");
		
		orderId=RestAssured.given().log().all().body(po).contentType(ContentType.JSON)
		.pathParam("shopperId", shopperId).header("Authorization","Bearer "+token)
		.when().post(baseURL+"/shoppers/{shopperId}/orders").jsonPath().get("data.orderId");
		
		System.out.println(orderId);
		
	}
	
	@Test(priority = 8)
	public void updateorderStatus() {
		
		RestAssured.given().log().all().header("Authorization","Bearer "+token)
		.pathParam("shopperId", shopperId).pathParam("orderId", orderId)
		.queryParam("status", "DELIVERED")
		.when().patch(baseURL+"/shoppers/{shopperId}/orders/{orderId}")
		.then().assertThat().statusCode(200).log().all();
	}
	
	//add review
	@Test(priority = 9)
	public void addReview() {
		
		AddReview ar=new AddReview();
		ar.setDescription("Good camera");
		ar.setHeading("Nice product");
		ar.setRating(4);
		ar.setShopperId(shopperId);
		ar.setShopperName("Rushii");
		
		reviewId=RestAssured.given().body(ar).contentType(ContentType.JSON)
		.log().all().header("Authorization","Bearer "+token)
		.queryParam("productId", productId)
		.when().post(baseURL+"/reviews").jsonPath().get("data.reviewId");
		
		System.out.println(reviewId);
		
	}
	
	//delete review
	@Test(priority = 10)
	public void deleteReview() {
		
		RestAssured.given().contentType(ContentType.JSON)
		.log().all().header("Authorization","Bearer "+token)
		.queryParam("productId", productId)
		.pathParam("reviewId", reviewId)
		.when().delete(baseURL+"/reviews/{reviewId} ")
		.then().assertThat().statusCode(200);
		
	}
	
	//delete Address
	@Test(priority = 11)
	public void deleteAddress() {
		RestAssured.given().log().all().header("Authorization","Bearer "+token)
		.pathParam("shopperId", shopperId).pathParam("addressId", addressId)
		.when().delete(baseURL+"/shoppers/{shopperId}/address/{addressId}")
		.then().assertThat().statusCode(204);
		
	}
	
	
	
	
	
	
	
	
}
