package ru.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class ContactDetailedTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
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
  public void testContactDetails(){
    app.goTo().homePage();
    //ContactData contact = app.db().contacts().iterator().next();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromDetailsForm = app.contact().infoFromDetailsForm(contact);
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contactInfoFromDetailsForm.getAlldetails(), equalTo(mergeAll(contactInfoFromEditForm)));
  }

  private String mergeAll(ContactData contact) {
    return Arrays.asList(contact.getFirstName() + " " + contact.getMiddlename() + " " + contact.getLastName(),
            contact.getNickname(), contact.getTitle(), contact.getCompany(), contact.getAddress1() + "\n",
            "H: " + contact.getHomePhone(), "M: " + contact.getMobilePhone(), "W: " + contact.getWorkPhone(), "F: " + contact.getFax() + "\n",
            contact.getEmail1(), contact.getEmail2(), contact.getEmail3(), "Homepage:", contact.getHomepage(),
            "\n", contact.getAddress2() + "\n", "P: " + contact.getPrivatephone() + "\n", contact.getNotes()+ "\n\n",
            "Członek grupy: "+ contact.getGroups())
            .stream().filter((s) -> ! s.equals("")).collect(Collectors.joining("\n"));
  }
}