package ru.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactEditionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    if(! app.contact().isThereAContact()) {
      app.goTo().contactPage();
<<<<<<< HEAD
      new ContactData().withFirstName("Precondition").withMiddleName("Precondition")
              .withLastName("Kowalski").withNickname("nick moj").withTitle("Pan moj").withCompany("Microsoft")
              .withAddress1("Al. Mickiewicza 20-123 Kraków").withHomePhone("123456789").withMobilePhone("500500500")
              .withWorkPhone("129009001").withFax("123456789").withEmail("per1@pre.com").withEmail2("pre2@re.com")
              .withEmail3("pre3@pre.com").withHomePage("www.precondition.com").withAddress2("Address 2")
              .withPrivatephone("9876544321").withNotes("no notes").withGroup("test1");
=======
      app.contact().create(new ContactData().withFirstName("Precondition").withLastName("Precondition")
              .withPhoneNumber("111111111").withEmailAddress("precondition@precondition.com").withGroup("test1"));
>>>>>>> origin/master
    }
  }

  @Test
  public void testContactEdition() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
<<<<<<< HEAD
            .withId(modifiedContact.getId())
            .withFirstName("Precondition").withMiddleName("Precondition")
            .withLastName("Kowalski").withNickname("nick moj").withTitle("Pan moj").withCompany("Microsoft")
            .withAddress1("Al. Mickiewicza 20-123 Kraków").withHomePhone("123456789").withMobilePhone("500500500")
            .withWorkPhone("129009001").withFax("123456789").withEmail("per1@pre.com").withEmail2("pre2@re.com")
            .withEmail3("pre3@pre.com").withHomePage("www.precondition.com").withAddress2("Address 2")
            .withPrivatephone("9876544321").withNotes("no notes").withGroup("test1");
=======
            .withId(modifiedContact.getId()).withFirstName("Modified")
            .withLastName("Modified").withPhoneNumber("999999999").withEmailAddress("modified@gmail.com").withGroup(null);
>>>>>>> origin/master
    app.contact().modify(contact);

    assertEquals(app.contact().count(), before.size() );
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }
}