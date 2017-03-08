package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.mantis.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    if(! app.contact().isThereAContact()) {
      app.goTo().contactPage();
      app.contact().create(new ContactData().withFirstName(app.getData().getProperty("precondition.firstName")).withMiddleName(app.getData().getProperty("precondition.middleName"))
              .withLastName(app.getData().getProperty("precondition.lastName")).withNickname(app.getData().getProperty("precondition.nickName")).withTitle(app.getData().getProperty("precondition.title")).withCompany(app.getData().getProperty("precondition.company"))
              .withAddress1(app.getData().getProperty("precondition.address1")).withHomePhone(app.getData().getProperty("precondition.homePhone")).withMobilePhone(app.getData().getProperty("precondition.mobilePhone"))
              .withWorkPhone(app.getData().getProperty("precondition.workPhone")).withFax(app.getData().getProperty("precondition.fax")).withEmail1(app.getData().getProperty("precondition.email1")).withEmail2(app.getData().getProperty("precondition.email2"))
              .withEmail3(app.getData().getProperty("precondition.email3")).withHomePage(app.getData().getProperty("precondition.homePage")).withAddress2(app.getData().getProperty("precondition.address2"))
              .withPrivatephone(app.getData().getProperty("precondition.privatePhone")).withNotes(app.getData().getProperty("precondition.notes")).withGroup(null));

      app.contact().create(new ContactData().withFirstName(app.getData().getProperty("precondition.firstName")).withLastName(app.getData().getProperty("precondition.lastName"))
              .withPhoneNumber(app.getData().getProperty("precondition.privatePhone")).withEmailAddress(app.getData().getProperty("precondition.email")).withGroup(app.getData().getProperty("precondition.group")));
    }
  }

  @Test
  public void testContactPhones(){
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(), contact.getPrivatephone())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone){
    return phone.replaceAll("\\s", "").replaceAll("[-()]","");
  }
}