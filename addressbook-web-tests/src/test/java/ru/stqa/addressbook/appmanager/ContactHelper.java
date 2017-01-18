package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends BaseHelper {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("home"), contactData.getPhoneNumber());
    type(By.name("email"), contactData.getEmailAddress());

    if(creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    }
    else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void selectContact() {
    click(By.xpath("//input[@name='selected[]']"));
  }

  private WebElement selectContactById(int id) {
    WebElement checkBox = wd.findElement(By.cssSelector("input[value='"+id+"']"));
    checkBox.click();
    return checkBox;
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//input[@value='Usuń']"));
    wd.switchTo().alert().accept();
  }

  public void editContact() {
    click(By.cssSelector("img[alt='Edytuj']"));
  }

  public void submitContactEdition() {
    click(By.xpath("//input[@type='submit']"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//input[@name='selected[]']"));
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=entry"));
    for(WebElement element : elements){
      String firstName = element.findElement(By.cssSelector(":nth-child(2)")).getText();
      String lastName = element.findElement(By.cssSelector(":nth-child(3)")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName));
    }
    return contacts;
  }

  public void create(ContactData contact) {
    fillContactForm(contact, true);
    submitContactCreation();
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId()).findElement(By.xpath("../../td[8]/a")).click();
    fillContactForm(contact,false);
    submitContactEdition();
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContacts();
    returnToHomePage();

  }

  public void returnToHomePage() {
    if(isElementPresent(By.id("maintable"))) {
      return;
    }
    click(By.linkText("strona główna"));
  }
}