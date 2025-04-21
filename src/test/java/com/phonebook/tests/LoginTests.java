package com.phonebook.tests;

import com.phonebook.data.UserData;
import com.phonebook.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignButton();
        }
    }
        @Test(priority = 1)
        public void loginPositiveTests () {
        logger.info("Login with data "+ UserData.EMAIL+"*********"+UserData.PASSWORD);
            app.getUser().clickOnLoginLink();
            app.getUser().fillRegistLoginForm(new User().setEmail(UserData.EMAIL).setPassword(UserData.PASSWORD));
            app.getUser().clickLoginButton();
            Assert.assertTrue(app.getUser().isSignOutButtonPresent());
        }
        @Test(priority = 2)
        public void loginNegativeWithoutEmailTests () {
            app.getUser().clickOnLoginLink();
            app.getUser().fillRegistLoginForm(new User().setPassword(UserData.PASSWORD));
            app.getUser().clickLoginButton();
            Assert.assertTrue(app.getUser().isAlertDisplayed());
        }

}
