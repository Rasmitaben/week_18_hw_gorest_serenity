package com.gorest.gorestinfo;

import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)

public class UsersCRUDTest extends TestBase {
    //static String token = PropertyReader.getInstance().getProperty("token");
    static String name = "Zara" + TestUtils.getRandomValue();
    static String email = "Zara" + TestUtils.getRandomValue() + "@gmail.com";
    static String gender = "Female";
    static String status = "active";
    static int userId;
    @Steps
    UsersSteps usersSteps;
    @Title("This will create new user")
    @Test
    public void test001(){
        ValidatableResponse response = usersSteps.CreateUser(name,email,gender,status);
        response.log().all().statusCode(201);
        userId = response.log().all().extract().path("id");
        System.out.println(userId);
    }

    @Title("Verify that user is added ")
    @Test
    public void test002() {
      HashMap<String,Object> userMap = usersSteps.getUserInfoById(userId);
        Assert.assertThat(userMap,hasValue(userId));
        System.out.println(userMap);
    }
    @Title("Update the user information and verify the updated information")
    @Test
    public void test003(){
     name = name + "_updated";
     usersSteps.updateUser(userId,name,email,gender,status).statusCode(200);
     HashMap<String ,Object> userMap = usersSteps.getUserInfoById(userId);
     Assert.assertThat(userMap,hasValue(userId));
    }
    @Title("Deleting user information with ID and verify that user is deleted ")
    @Test
    public void test004(){
        usersSteps.deleteUser(userId).statusCode(204);
        usersSteps.getUserById(userId).statusCode(404);
    }

}
