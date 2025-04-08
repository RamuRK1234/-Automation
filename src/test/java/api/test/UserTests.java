package api.test;

import static org.testng.Assert.assertEquals;
import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;


import api.endpoints.UserEndPoints;
import api.payload.User;

public class UserTests {
	Faker faker;
	User userpayload;
	
	@BeforeClass
	public void setUpdata() {
		faker=new Faker();
		userpayload=new User();
		
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstname(faker.name().firstName());
		userpayload.setLastname(faker.name().lastName());
		userpayload.setPassword(faker.internet().password(5,10));
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPhone(faker.phoneNumber().cellPhone());
	}
	@Test(priority=1)
	public void testpostuser() {
	Response response = UserEndPoints.CreateUser(userpayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
		
	}
	@Test(priority=2)
	public void testgetuser() {
		Response response = UserEndPoints.ReadUser(this.userpayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
	}
	@Test(priority=3)
	public void testupdateUserByName() {
		
		userpayload.setFirstname(faker.name().firstName());
		userpayload.setLastname(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		Response response = UserEndPoints.UpdateUser(this.userpayload.getUsername(),userpayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		
		Response responseAfterupdate = UserEndPoints.ReadUser(this.userpayload.getUsername());
		Assert.assertEquals(responseAfterupdate.getStatusCode(),200);
	}
	@Test(priority=4)
	public void deleteuserByName() {
		Response response = UserEndPoints.deleteuser(this.userpayload.getUsername());
		Assert.assertEquals(response.getStatusCode(),200);
	}

}
