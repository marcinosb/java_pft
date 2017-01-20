package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;
        import ru.stqa.addressbook.model.ContactData;
        import ru.stqa.addressbook.model.Contacts;

        import static org.hamcrest.CoreMatchers.equalTo;
        import static org.hamcrest.junit.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.goTo().contactPage();
    ContactData contact = new ContactData()
            .withFirstName("Piotr").withLastName("Piotr").withPhoneNumber("123456789")
            .withEmailAddress("marcinosb@gmail.com").withGroup("test1").withAddress1("Lokietka 135/11 \n 31-263 Krakow")
            .withHomePhone("123456789").withWorkPhone("123123123").withMobilePhone("500500500");
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}