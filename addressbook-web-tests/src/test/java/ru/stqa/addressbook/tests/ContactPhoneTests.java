package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import ru.stqa.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    if(! app.contact().isThereAContact()) {
      app.goTo().contactPage();
<<<<<<< HEAD
      app.contact().create(new ContactData().withFirstName("Precondition").withMiddleName("Precondition")
              .withLastName("Kowalski").withNickname("nick moj").withTitle("Pan moj").withCompany("Microsoft")
              .withAddress1("Al. Mickiewicza 20-123 Kraków").withHomePhone("123456789").withMobilePhone("500500500")
              .withWorkPhone("129009001").withFax("123456789").withEmail("per1@pre.com").withEmail2("pre2@re.com")
              .withEmail3("pre3@pre.com").withHomePage("www.precondition.com").withAddress2("Address 2")
              .withPrivatephone("9876544321").withNotes("no notes").withGroup(null));
=======
      app.contact().create(new ContactData().withFirstName("Precondition").withLastName("Precondition")
              .withPhoneNumber("111111111").withEmailAddress("precondition@precondition.com").withGroup("test1"));
>>>>>>> origin/master
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
<<<<<<< HEAD
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(), contact.getPrivatephone())
=======
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
>>>>>>> origin/master
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone){
    return phone.replaceAll("\\s", "").replaceAll("[-()]","");
  }
}