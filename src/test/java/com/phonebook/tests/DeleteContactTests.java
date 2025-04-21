package com.phonebook.tests;

import com.phonebook.data.ContactData;
import com.phonebook.data.UserData;
import com.phonebook.models.Contact;
import com.phonebook.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteContactTests extends TestBase{
//    precondition
//    login


    @BeforeMethod
    public void preCondition(){
//        Login
        if(!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignButton();
        }
        app.getUser().clickOnLoginLink();
        app.getUser().fillRegistLoginForm(new User().setEmail(UserData.EMAIL).setPassword(UserData.PASSWORD));
        app.getUser().clickLoginButton();

//        Add contact
        app.getContact().clickAddLink();
        app.getContact().fillContactForm(new Contact().setName(ContactData.NAME)
                                    .setLastName(ContactData.LASTNAME)
                                    .setPhone(ContactData.PHONE)
                                    .setEmail(ContactData.EMAIL)
                                    .setAddress(ContactData.ADDRESS)
                                    .setDescription(ContactData.DESCRIPTION));
        app.getContact().clickSaveButton();
    }

    @Test

    public void deleteContactTest(){
        int sizeBefore = app.getContact().sizeofContacts();
        
//            click on the card
        app.getContact().deleteContact();
//            verify contact is deleted(by size)
        app.getContact().pause(2000);
        int sizeAfter = app.getContact().sizeofContacts();
        Assert.assertEquals(sizeAfter, sizeBefore-1);
        
    }


}
