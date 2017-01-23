package ru.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class ContactDetailedTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if(! app.contact().isThereAContact()) {
      app.goTo().contactPage();
      app.contact().create(new ContactData().withFirstName("Precondition").withMiddleName("Precondition")
              .withLastName("Kowalski").withNickname("nick moj").withTitle("Pan moj").withCompany("Microsoft")
              .withAddress1("Al. Mickiewicza 20-123 Kraków").withHomePhone("123456789").withMobilePhone("500500500")
              .withWorkPhone("129009001").withFax("123456789").withEmail("per1@pre.com").withEmail2("pre2@re.com")
              .withEmail3("pre3@pre.com").withHomePage("www.precondition.com").withAddress2("Address 2")
              .withPrivatephone("9876544321").withNotes("no notes").withGroup("test1"));
    }
  }
  @Test
  public void testContactEmails(){
    app.goTo().homePage();
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
            "Członek grupy: "+ contact.getGroup())
            .stream().filter((s) -> ! s.equals("")).collect(Collectors.joining("\n"));
  }
}
