package com.phonebook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomepageTests extends TestBase{
    @BeforeMethod
    public void ensurePrecondition(){
        if(!app.getHomePage().isHomeComponentPresent()){
            app.getHomePage().clickOnHomeLink();
        }
    }
    @Test
    public void isHomeComponentPresentTest(){
//        driver.findElements(By.cssSelector("div:nth-child(2)>div>div>h1"));
//        System.out.println("Home Component "+isHomeComponentPresent());
        Assert.assertTrue(app.getHomePage().isHomeComponentPresent());
//        Assert.assertTrue(isElementPresent("div:nth-child(2)>div>div>h1"));
    }


}
