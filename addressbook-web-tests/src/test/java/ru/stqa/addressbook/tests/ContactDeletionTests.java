package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by user on 14.12.2016.
 */
public class ContactDeletionTests extends TestBase {
  @Test (enabled = false)
  public void testContactDeletion() {
    if(! app.getContactHelper().isThereAContact()) {
      app.goTo().gotoContactPage();
      app.getContactHelper().createContact(new ContactData("Alan", "Alan", "111111111", "alan@alan.com", "test1"));
    }
    app.goTo().gotoToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContacts();
    app.goTo().gotoToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();

    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(0);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(before, after);
  }
}
