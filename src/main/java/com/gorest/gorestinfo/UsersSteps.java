package com.gorest.gorestinfo;

import com.gorest.constants.EndPoints;
import com.gorest.model.UsersPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class UsersSteps {
    @Step("Creating user with name : {0}, email : {1}, gender {2}, status : {3}")
    public ValidatableResponse CreateUser(String name, String email, String gender, String status) {
        UsersPojo userPojo = UsersPojo.getUserPojo(name, email, gender, status);
        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215")
                .body(userPojo)
                .when()
                .post(EndPoints.CREATE_USER)
                .then();

    }

    @Step("Getting user information by userId:{0}")

    public HashMap<String,Object> getUserInfoById(int userId){
      HashMap<String,Object> userMap = SerenityRest.given().log().all()

              .header("Content-Type", "application/json")
              .header("Authorization", "Bearer 61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215")
              .pathParam("userID", userId)
              .when()
              .get(EndPoints.GET_USER_BY_ID)
              .then().statusCode(200)
              .extract()
              .path("");
        return userMap;

    }
    @Step("Updating user with userId : {0},name : {1}, email : {2}, gender {3}, status : {4}")
    public ValidatableResponse updateUser(int userId,String name,String email, String gender, String status){

        UsersPojo userPojo = UsersPojo.getUserPojo(name,email,gender,status);

       return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215")
                .pathParam("userID",userId)
                .body(userPojo)
                .when()
                .put(EndPoints.UPDATE_USER_BY_ID)

                .then();
    }
    @Step("Deleting user information with userId : {0}")
    public ValidatableResponse deleteUser(int userId){
       return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215")
                .pathParam("userID",userId)
                .when()
                .delete(EndPoints.DELETE_USER_BY_ID)
                .then();
    }
    @Step("Getting user by Id ;{0}")
    public ValidatableResponse getUserById(int userId){
       return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Access", "application/json")
                .header("Authorization", "Bearer 61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215")
               .pathParam("userID",userId)
                .when()
                .get(EndPoints.GET_SINGLE_USER_BY_ID)
                .then();
    }
}



