package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;

public class DDTests {
	@Test(priority=1,dataProvider="Data",dataProviderClass=DataProviders.class)
	public void testPostUser(String userID, String userName, String fname, String lname, String userEmail, String pwd, String ph) {
		
		User userPayload=new User();
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstname(fname);
		userPayload.setLastname(lname);
		userPayload.setEmail(userEmail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		Response response = UserEndPoints.createUser(userPayload);
		Assert.assertEquals(response.getStatusCode(),200);
		
	}
	
	@Test(priority=2,dataProvider="userName",dataProviderClass=DataProviders.class)
	public void testDeleteUserByName(String userName) {
		Response response = UserEndPoints.deleteuser(userName);
		
		
	}
	

}

