package ru.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactEditionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){

    if(app.db().contacts().size() == 0){
      app.goTo().contactPage();
      app.contact().create(new ContactData().withFirstName(app.getData().getProperty("precondition.firstName")).withMiddleName(app.getData().getProperty("precondition.middleName"))
              .withLastName(app.getData().getProperty("precondition.lastName")).withNickname(app.getData().getProperty("precondition.nickName")).withTitle(app.getData().getProperty("precondition.title")).withCompany(app.getData().getProperty("precondition.company"))
              .withAddress1(app.getData().getProperty("precondition.address1")).withHomePhone(app.getData().getProperty("precondition.homePhone")).withMobilePhone(app.getData().getProperty("precondition.mobilePhone"))
              .withWorkPhone(app.getData().getProperty("precondition.workPhone")).withFax(app.getData().getProperty("precondition.fax")).withEmail1(app.getData().getProperty("precondition.email1")).withEmail2(app.getData().getProperty("precondition.email2"))
              .withEmail3(app.getData().getProperty("precondition.email3")).withHomePage(app.getData().getProperty("precondition.homePage")).withAddress2(app.getData().getProperty("precondition.address2"))
              .withPrivatephone(app.getData().getProperty("precondition.privatePhone")).withNotes(app.getData().getProperty("precondition.notes")).withGroup(app.getData().getProperty("precondition.group"))
              .withPhoto(new File(".\\src\\test\\resources\\hqdefault.jpg")));
    }
  }

  @Test
  public void testContactEdition() {
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId())
            .withFirstName(app.getData().getProperty("contact.firstName")).withMiddleName(app.getData().getProperty("contact.middleNam"))
            .withLastName(app.getData().getProperty("contact.lastName")).withNickname(app.getData().getProperty("contact.nickName")).withTitle(app.getData().getProperty("contact.title")).withCompany(app.getData().getProperty("contact.company"))
            .withAddress1(app.getData().getProperty("contact.address1")).withHomePhone(app.getData().getProperty("contact.homePhone")).withMobilePhone(app.getData().getProperty("contact.mobilePhone"))
            .withWorkPhone(app.getData().getProperty("contact.workPhone")).withFax(app.getData().getProperty("contact.fax")).withEmail1(app.getData().getProperty("contact.email1")).withEmail2(app.getData().getProperty("contact.email2"))
            .withEmail3(app.getData().getProperty("contact.email3")).withHomePage(app.getData().getProperty("contact.homePage")).withAddress2(app.getData().getProperty("contact.address2"))
            .withPrivatephone(app.getData().getProperty("contact.privatePhone")).withNotes(app.getData().getProperty("contact.notes")).withGroup(app.getData().getProperty("contact.group"))
            .withPhoto(new File(".\\src\\test\\resources\\hqdefault.jpg"));

    app.contact().modify(contact);

    assertEquals(app.contact().count(), before.size() );
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }
}