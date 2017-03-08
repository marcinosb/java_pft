package ru.stqa.pft.mantis.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.ContactData;
import ru.stqa.pft.mantis.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if(app.db().contacts().size() == 0){
      app.goTo().contactPage();
      app.contact().create(new ContactData().withFirstName(app.getData().getProperty("precondition.firstName")).withMiddleName(app.getData().getProperty("precondition.middleName"))
              .withLastName(app.getData().getProperty("precondition.lastName")).withNickname(app.getData().getProperty("precondition.nickName")).withTitle(app.getData().getProperty("precondition.title")).withCompany(app.getData().getProperty("precondition.company"))
              .withAddress1(app.getData().getProperty("precondition.address1")).withHomePhone(app.getData().getProperty("precondition.homePhone")).withMobilePhone(app.getData().getProperty("precondition.mobilePhone"))
              .withWorkPhone(app.getData().getProperty("precondition.workPhone")).withFax(app.getData().getProperty("precondition.fax")).withEmail1(app.getData().getProperty("precondition.email1")).withEmail2(app.getData().getProperty("precondition.email2"))
              .withEmail3(app.getData().getProperty("precondition.email3")).withHomePage(app.getData().getProperty("precondition.homePage")).withAddress2(app.getData().getProperty("precondition.address2"))
              .withPrivatephone(app.getData().getProperty("precondition.privatePhone")).withNotes(app.getData().getProperty("precondition.notes"))/*.withGroup(app.getData().getProperty("precondition.group"))*/
              .withPhoto(new File(".\\src\\test\\resources\\hqdefault.jpg")));
    }
  }

  @Test
  public void testContactDeletion() {
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    //Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    assertEquals(app.contact().count(), before.size() - 1 );
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deletedContact)));
  }
}