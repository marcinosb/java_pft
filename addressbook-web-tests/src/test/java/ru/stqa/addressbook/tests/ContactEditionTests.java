package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

/**
 * Created by user on 14.12.2016.
 */
public class ContactEditionTests extends TestBase{
  @Test
  public void testContactEdition() {
    if(! app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoContactPage();
      app.getContactHelper().createContact(new ContactData("Jan", "Jan", "111111111", "jan@jan.com", "test1"), true);
    }
    app.getNavigationHelper().gotoToHomePage();
    app.getContactHelper().editContact();
    app.getContactHelper().fillContactForm(new ContactData("Jan", "Nowy", "999999999", "jan@gmail.com", null),false);
    app.getContactHelper().submitContactEdition();
    app.getNavigationHelper().gotoToHomePage();
  }
}
