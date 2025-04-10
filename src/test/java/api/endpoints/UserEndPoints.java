package api.endpoints;
import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import io.restassured.http.ContentType;

import api.payload.User;

public class UserEndPoints {
	
	public static Response createUser(User payload) {
		 
		Response response= given()
		    .contentType(ContentType.JSON)
		     .accept(ContentType.JSON)
		.body(payload)
		.when()
		.post(Routes.post_url);
		return response;
		
	}
	public static Response ReadUser(String username) {
		 
		Response response=given()
		                  .pathParam("username",username)
		.when()
		.get(Routes.get_url);
		return response;
		
	}
	public static Response UpdateUser(String username,User payload) {
		 
		Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.pathParam("username",username)
		.when()
		.put(Routes.update_url);
		return response;
		
	}
	
	public static Response deleteuser(String username) {
		 
		Response response=given()
		                  .pathParam("username",username)
		.when()
		.delete(Routes.delete_url);
		return response;
		
	}
	
	

}
