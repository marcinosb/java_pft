package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by user on 13.12.2016.
 */
public class NavigationHelper extends BaseHelper {

  public NavigationHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void gotoGroupPage() {
    click(By.linkText("grupy"));
  }

  public void gotoContactPage() {
    click(By.linkText("nowy wpis"));
  }

  public void gotoToHomePage() {
    click(By.linkText("home page"));
  }
}
