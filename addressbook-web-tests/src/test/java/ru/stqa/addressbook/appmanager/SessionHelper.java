package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by user on 13.12.2016.
 */
public class SessionHelper extends BaseHelper {

  public SessionHelper(WebDriver wd) {
    super(wd);
  }

  public void login(String username, String password) {
   type(By.name("pass"), password);
   type(By.name("user"), username);
   click(By.xpath("//form[@id='LoginForm']/input[3]"));
  }
}
