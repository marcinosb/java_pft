package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by user on 13.12.2016.
 */
public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoContactPage();

    app.getContactHelper().fillContactForm(new ContactData("Marcin", "Bugajski", "123456789", "marcinosb@gmail.com", "test1"), true);
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().gotoToHomePage();
  }
}


