package ru.stqa.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by user on 13.12.2016.
 */
public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {

    gotoContactPage();
    fillContactForm(new ContactData("Marcin", "Bugajski", "123456789", "marcinosb@gmail.com"));
    submitContactCreation();
    returnToHomePage();
  }

}
