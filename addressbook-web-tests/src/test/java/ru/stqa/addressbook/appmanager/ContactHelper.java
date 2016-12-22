package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.addressbook.model.ContactData;

/**
 * Created by user on 13.12.2016.
 */
public class ContactHelper extends BaseHelper {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("home"), contactData.getPhoneNumber());
    type(By.name("email"), contactData.getEmailAddress());
  }

  public void selectContact() {
    click(By.xpath("//input[@name='selected[]']"));

  }

  public void deleteSelectedContacts() {
    //Remove first entry from the top
    click(By.xpath("//input[@value='Usuń']"));
    wd.switchTo().alert().accept();
  }

  public void editContact() {
    click(By.cssSelector("img[alt='Edytuj']"));
  }

  public void submitContactEdition() {
    click(By.xpath("//input[@type='submit']"));
  }


}
