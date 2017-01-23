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

            .withFirstName("Piotr")
            .withMiddleName("Miroslaw")
            .withLastName("Piotrowski")
            .withNickname("siwy")
            .withCompany("Comarch")
            .withTitle("Pan")
            .withAddress1("ul. Glogera, 31-222 Kraków")
            .withHomePhone("123456789")
            .withMobilePhone("500500500")
            .withWorkPhone("12400400400")
            .withFax("900900900")
            .withEmail1("piotr@piotr.com")
            .withEmail2("piotr2@piotr.com")
            .withEmail3("piotr3@piotr.com")
            .withHomePage("www.piotr.com")
            .withBday("10")
            .withBmonth("Maj")
            .withByear("2000")
            .withAday("20")
            .withAmonth("Luty")
            .withAyear("1900")
            .withAddress2("Wlokietka 135/100 30-100 Kraków")
            .withPrivatephone("8989898989")
            .withNotes("none")
            .withGroup("test1");

    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}