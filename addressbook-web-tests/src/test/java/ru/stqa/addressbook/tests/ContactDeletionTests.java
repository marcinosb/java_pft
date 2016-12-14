package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by user on 14.12.2016.
 */
public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion() {
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContacts();
  }
}
