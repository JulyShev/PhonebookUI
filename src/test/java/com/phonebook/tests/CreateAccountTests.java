package com.phonebook.tests;

import com.phonebook.data.UserData;
import com.phonebook.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateAccountTests extends TestBase{

    SoftAssert softAssert = new SoftAssert();
@BeforeMethod
public void  ensurePrecondition(){
    if(!app.getUser().isLoginLinkPresent()){
        app.getUser().clickOnSignButton();
    }
}
    @Test(enabled = false)
    public void newRegistrationPositiveTest() {
        int i = (int)((System.currentTimeMillis()/1000)%3600);
        app.getUser().clickOnLoginLink();
        app.getUser().fillRegistLoginForm(new User().setEmail(UserData.EMAIL).setPassword(UserData.PASSWORD));
        app.getUser().clickRegistrationButton();
        Assert.assertTrue(app.getUser().isSignOutButtonPresent());

    }

    @Test
    public void existedUserRegistrationNegativeTest(){
        app.getUser().clickOnLoginLink();
        app.getUser().fillRegistLoginForm(new User().setEmail(UserData.EMAIL).setPassword(UserData.PASSWORD));
        app.getUser().clickRegistrationButton();
        softAssert.assertTrue(app.getUser().isAlertDisplayed());
        softAssert.assertTrue(app.getUser().isErroeMessagePresent());
        softAssert.assertAll();
    }

}
