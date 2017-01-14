package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by user on 14.12.2016.
 */
public class ContactEditionTests extends TestBase{
  @Test (enabled = false)
  public void testContactEdition() {
    if(! app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoContactPage();
      app.getContactHelper().createContact(new ContactData("Jan", "Jan", "111111111", "jan@jan.com", "test1"));
    }
    app.getNavigationHelper().gotoToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData(before.get(0).getId(), "Modified", "Modified", "999999999", "modified@gmail.com", null);
    app.getContactHelper().editContact();
    app.getContactHelper().fillContactForm(contact,false);
    app.getContactHelper().submitContactEdition();
    app.getNavigationHelper().gotoToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();

    Assert.assertEquals(after.size(), before.size() );

    before.remove(0);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
