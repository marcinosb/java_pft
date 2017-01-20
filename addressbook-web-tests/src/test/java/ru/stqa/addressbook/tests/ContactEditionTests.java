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
      app.contact().create(new ContactData().withFirstName("Precondition").withLastName("Precondition")
              .withPhoneNumber("111111111").withEmailAddress("precondition@precondition.com").withGroup("test1"));
    }
  }

  @Test
  public void testContactEdition() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstName("Modified")
            .withLastName("Modified").withPhoneNumber("999999999").withEmailAddress("modified@gmail.com").withGroup(null);
    app.contact().modify(contact);

    assertEquals(app.contact().count(), before.size() );
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }
}