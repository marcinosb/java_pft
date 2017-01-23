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
      app.contact().create(new ContactData().withFirstName("Precondition").withMiddleName("Precondition")
              .withLastName("Kowalski").withNickname("nick moj").withTitle("Pan moj").withCompany("Microsoft")
              .withAddress1("Al. Mickiewicza 20-123 Kraków").withHomePhone("123456789").withMobilePhone("500500500")
              .withWorkPhone("129009001").withFax("123456789").withEmail("per1@pre.com").withEmail2("pre2@re.com")
              .withEmail3("pre3@pre.com").withHomePage("www.precondition.com").withAddress2("Address 2")
              .withPrivatephone("9876544321").withNotes("no notes").withGroup("test1"));
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