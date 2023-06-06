package com.gorest.gorestinfo;

import com.gorest.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;
@Concurrent(threads = "4x")
@UseTestDataFrom("src/test/java/resources/testdata/userinfo.csv")

@RunWith(SerenityParameterizedRunner.class)

public class UsersDataDrivenTesting extends TestBase {
    static String name;
    static String email;
    static String gender;
    static String status;
    @Steps
    UsersSteps usersSteps;
    @Title("Data driven test for adding multiple students to the application")
    @Test
    public void createMultipleUser(){
        usersSteps.CreateUser(name,email,gender,status).statusCode(201);
    }
}
