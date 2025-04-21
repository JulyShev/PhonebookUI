package com.phonebook.tests;

import com.phonebook.data.ContactData;
import com.phonebook.data.UserData;
import com.phonebook.models.Contact;
import com.phonebook.models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class AddContacts extends TestBase{

    @BeforeMethod
    public void preconditions(){
        if(!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignButton();
        }
        app.getUser().clickOnLoginLink();
        app.getUser().fillRegistLoginForm(new User().setEmail(UserData.EMAIL).setPassword(UserData.PASSWORD));
        app.getUser().clickLoginButton();
    }
@Test
    public void addContactPositiveTest(){
        app.getContact().clickAddLink();
        app.getContact().fillContactForm(new Contact().setName(ContactData.NAME)
                                    .setLastName(ContactData.LASTNAME)
                                    .setPhone(ContactData.PHONE)
                                    .setEmail(ContactData.EMAIL)
                                    .setAddress(ContactData.ADDRESS)
                                    .setDescription(ContactData.DESCRIPTION));
        app.getContact().clickSaveButton();
        //    verify contact add
        Assert.assertTrue(app.getContact().isContactAdd(ContactData.NAME));

}
@DataProvider
public Iterator <Object[]> addNewContact(){
    List <Object[]> list = new ArrayList<>();
    list.add(new Object[]{"Oli", "Koen", "1234545678","Oli@gmail.com", "Rehovot", "Director"});
    list.add(new Object[]{"Oli", "Koen", "123454567099","Oli@gmail.com", "Rehovot", "Director"});
    list.add(new Object[]{"Oli", "Koen", "12345456789223","Oli@gmail.com", "Rehovot", "Director"});
        return list.iterator();
}
@Test(dataProvider = "addNewContact")
    public void addContactPositiveFromDataProviderTest(String name, String lastName, String phone,
                                                       String email, String address, String description) throws IOException {
    app.getContact().clickAddLink();
    app.getContact().fillContactForm(new Contact()
            .setName(name)
            .setLastName(lastName)
            .setPhone(phone)
            .setEmail(email)
            .setAddress(address)
            .setDescription(description));
    app.getContact().clickSaveButton();
    //    verify contact add
    Assert.assertTrue(app.getContact().isContactAdd(name));
}

@DataProvider
        public Iterator<Object[]>addNewContactWithCsv() throws IOException {
    List<Object[]>list= new ArrayList<>();
        BufferedReader reader = new BufferedReader( new FileReader(new File("src/test/resources/contact.csv")));
        String line = reader.readLine();
        while(line != null) {

            String[] split = line.split(",");

            list.add(new Object[]{new Contact().setName(split[0])
                                                .setLastName(split[1])
                                                .setPhone(split[2])
                                                .setEmail(split[3])
                                                .setAddress(split[4])
                                                .setDescription(split[5])});
            line = reader.readLine();
        }
    return list.iterator();
    }

@Test(dataProvider = "addNewContactWithCsv")
    public void addContactPositiveFromDataProviderWithCsvFile(Contact contact){
        app.getContact().clickAddLink();
        app.getContact().fillContactForm(contact);
        app.getContact().clickSaveButton();
        //    verify contact add
        Assert.assertTrue(app.getContact().isContactAdd(contact.getName()));

}

    @AfterMethod
    public void postCondition(){
        app.getContact().deleteContact();
    }

}
