package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

/**
 * Created by user on 14.12.2016.
 */
public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion() {
    if(! app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoContactPage();
      app.getContactHelper().createContact(new ContactData("Alan", "Alan", "111111111", "alan@alan.com", "test1"), true);
    }
    app.getNavigationHelper().gotoToHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContacts();
  }
}
