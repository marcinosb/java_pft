package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by user on 13.12.2016.
 */
public class NavigationHelper extends BaseHelper {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void gotoGroupPage() {
    if( isElementPresent(By.tagName("h1"))
            && wd.findElement(By.tagName("h1")).getText().equals("Grupy")
            && isElementPresent(By.name("new"))) {
     return;
    }
    click(By.linkText("grupy"));
  }

  public void gotoContactPage() {
    if( isElementPresent(By.tagName("h1"))
      && wd.findElement(By.tagName("h1")).getText().equals("Edytuj / dodaj wpis do książki adresowej")) {
      return;
    }
    click(By.linkText("nowy wpis"));
  }

  public void gotoToHomePage() {
    if(isElementPresent(By.id("maintable"))) {
      return;
    }
    click(By.linkText("home page"));
  }


}


