package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  private final Properties properties;
  private final Properties data;
  WebDriver wd;
  private NavigationHelper navigationHelper ;
  private GroupHelper groupHelper;
  private SessionHelper sessionHelper;
  private ContactHelper contactHelper;
  private String browser;
  private DbHelper dbHelper;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
    data = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    String targetData = System.getProperty("data", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    data.load(new FileReader(new File(String.format("src/test/resources/%s.data", targetData))));

    dbHelper = new DbHelper();

    if(browser.equals(BrowserType.FIREFOX)){
      wd = new FirefoxDriver();
    } else if(browser.equals(BrowserType.CHROME)){
      wd = new ChromeDriver();
    } else if(browser.equals(BrowserType.IE)){
      wd = new InternetExplorerDriver();
    }

    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    wd.get(properties.getProperty("web.baseURL"));
    groupHelper = new GroupHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    contactHelper = new ContactHelper(wd);
    sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));

  }

  public void stop() {
    wd.quit();
  }

  public GroupHelper group() {
    return groupHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }

  public ContactHelper contact() {
    return contactHelper;
  }

  public Properties getData() {
    return data;
  }

  public DbHelper db() {
    return dbHelper;
  }
}
