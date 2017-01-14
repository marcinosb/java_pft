package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by user on 13.12.2016.
 */
public class ContactCreationTests extends TestBase {

  @Test (enabled = false)
  public void testContactCreation() {
    app.getNavigationHelper().gotoToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getNavigationHelper().gotoContactPage();
    ContactData contact = new ContactData("Andrzej", "Andrzej", "123456789", "marcinosb@gmail.com", "test1");
    app.getContactHelper().fillContactForm(contact, true);
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().gotoToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();

    Assert.assertEquals(after.size(), before.size() + 1  );

    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);

    for(ContactData c : before){
      System.out.println(c.getId() + " " + c.getFirstName() + " " + c.getLastName());
    }

    for(ContactData c : after){
      System.out.println(c.getId() + " " + c.getFirstName() + " " + c.getLastName());
    }

    Assert.assertEquals(before, after);
  }
}


