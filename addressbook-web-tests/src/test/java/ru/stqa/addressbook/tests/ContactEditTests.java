package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

/**
 * Created by user on 14.12.2016.
 */
public class ContactEditTests extends TestBase{
  @Test
  public void testContactEdition() {
    app.getContactHelper().editContact();
    app.getContactHelper().fillContactForm(new ContactData("Jan", "Kwiatkowski", "999999999", "jan@gmail.com", null),false);
    app.getContactHelper().submitContactEdition();
    app.getNavigationHelper().gotoToHomePage();
  }
}
