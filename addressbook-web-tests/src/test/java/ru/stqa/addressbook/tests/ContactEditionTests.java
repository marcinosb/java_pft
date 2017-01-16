package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactEditionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    if(! app.contact().isThereAContact()) {
      app.goTo().contactPage();
      app.contact().create(new ContactData("Alan", "Alan", "111111111", "alan@alan.com", "test1"));
    }
  }

  @Test
  public void testContactEdition() {

    app.goTo().homePage();
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData(before.get(0).getId(), "Modified", "Modified", "999999999", "modified@gmail.com", null);
    app.contact().modify(contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() );
    before.remove(0);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
