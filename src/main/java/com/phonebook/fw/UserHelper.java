package com.phonebook.fw;

import com.phonebook.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserHelper extends BaseHelper {

    public UserHelper(WebDriver driver) {
        super(driver);
    }

    public void clickRegistrationButton() {
         click(By.name("registration"));
     }

    public void fillRegistLoginForm(User user) {
         type(By.name("email"), user.getEmail());
         type(By.name("password"), user.getPassword());
     }

    public void clickOnLoginLink() {
         click(By.cssSelector("[href='/login']"));
     }

    public boolean isSignOutButtonPresent() {
         return isElementPresent(By.xpath("//button[.='Sign Out']"));
     }

    public boolean isErroeMessagePresent() {
         return isElementPresent(By.cssSelector(".login_login__3EHKB>div"));
     }

    public void clickLoginButton() {
         click(By.name("login"));
     }

    public boolean isLoginLinkPresent() {
        return isElementPresent(By.cssSelector("[href='/login']"));
    }

    public void clickOnSignButton() {
        click(By.xpath("//button[.='Sign Out']"));
    }
}
