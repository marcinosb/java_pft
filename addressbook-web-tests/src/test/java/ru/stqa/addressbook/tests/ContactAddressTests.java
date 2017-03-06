package ru.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if(! app.contact().isThereAContact()) {

      app.goTo().contactPage();
      app.contact().create(new ContactData().withFirstName(app.getData().getProperty("precondition.firstName")).withMiddleName(app.getData().getProperty("precondition.middleName"))
              .withLastName(app.getData().getProperty("precondition.lastName")).withNickname(app.getData().getProperty("precondition.nickName")).withTitle(app.getData().getProperty("precondition.title")).withCompany(app.getData().getProperty("precondition.company"))
              .withAddress1(app.getData().getProperty("precondition.address1")).withHomePhone(app.getData().getProperty("precondition.homePhone")).withMobilePhone(app.getData().getProperty("precondition.mobilePhone"))
              .withWorkPhone(app.getData().getProperty("precondition.workPhone")).withFax(app.getData().getProperty("precondition.fax")).withEmail1(app.getData().getProperty("precondition.email1")).withEmail2(app.getData().getProperty("precondition.email2"))
              .withEmail3(app.getData().getProperty("precondition.email3")).withHomePage(app.getData().getProperty("precondition.homePage")).withAddress2(app.getData().getProperty("precondition.address2"))
              .withPrivatephone(app.getData().getProperty("precondition.privatePhone")).withNotes(app.getData().getProperty("precondition.notes")).withGroup(app.getData().getProperty("precondition.group")));
    }
  }
  @Test
  public void testContactAddress(){
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(cleaned(contact.getAddress1()), equalTo(mergeAddress(contactInfoFromEditForm)));
  }

  private String mergeAddress(ContactData contact) {
    return cleaned(contact.getAddress1());
  }

  public static String cleaned(String address){
    return address.replaceAll("\\s+", " ");
  }
}