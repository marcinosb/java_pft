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
    click(By.xpath("//input[@name='submit']"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("company"), contactData.getCompany());
    type(By.name("title"), contactData.getTitle());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("email"), contactData.getEmailAddress());
    type(By.name("address"), contactData.getAddress1());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("fax"), contactData.getFax());
    type(By.name("email"), contactData.getEmail1());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    type(By.name("homepage"), contactData.getHomepage());
    type(By.name("address2"), contactData.getAddress2());
    type(By.name("phone2"), contactData.getPrivatephone());
    type(By.name("notes"), contactData.getNotes());
    attach(By.name("photo"), contactData.getPhoto());

    if(creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    }
    else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  private WebElement selectContactById(int id) {
    WebElement checkBox = wd.findElement(By.cssSelector("input[value='"+id+"']"));
    checkBox.click();
    return checkBox;
  }

  public void deleteSelectedContacts() {
    //click(By.xpath("//input[@value='Usuń']"));
    click(By.xpath("//input[@onclick='DeleteSel()']"));
    wd.switchTo().alert().accept();
  }

   public void submitContactEdition() {
    click(By.xpath("//input[@type='submit']"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//input[@name='selected[]']"));
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if(contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=entry"));
    for(WebElement element : elements){
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String lastName = element.findElement(By.cssSelector(":nth-child(2)")).getText();
      String firstName = element.findElement(By.cssSelector(":nth-child(3)")).getText();

      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));

      String allPhones = cells.get(5).getText();
      String allEmails = cells.get(4).getText();
      String address = cells.get(3).getText();

      contactCache.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
              .withAllPhones(allPhones).withAllEmails(allEmails).withAddress1(address));
    }
    return new Contacts(contactCache);
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public void create(ContactData contact) {
    fillContactForm(contact, true);
    submitContactCreation();
    contactCache = null;
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId()).findElement(By.xpath("../../td[8]/a")).click();
    fillContactForm(contact,false);
    submitContactEdition();
    contactCache = null;
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContacts();
    contactCache = null;
    returnToHomePage();
  }

  public void returnToHomePage() {
    if(isElementPresent(By.id("maintable"))) {
      return;
    }
    //click(By.linkText("strona główna"));
    click(By.xpath("//a[@href='./']"));
  }

  public ContactData infoFromEditForm(ContactData contact) {
    selectContactById(contact.getId()).findElement(By.xpath("../../td[8]/a")).click();
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String middlename = wd.findElement(By.name("middlename")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String nickname = wd.findElement(By.name("nickname")).getAttribute("value");
    String company = wd.findElement(By.name("company")).getAttribute("value");
    String title = wd.findElement(By.name("title")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String fax = wd.findElement(By.name("fax")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String homepage = wd.findElement(By.name("homepage")).getAttribute("value");
    String address1 = wd.findElement(By.name("address")).getText();
    String address2 = wd.findElement(By.name("address2")).getText();
    String privatephone = wd.findElement(By.name("phone2")).getAttribute("value");
    String notes = wd.findElement(By.name("notes")).getText();
    String bday = wd.findElement(By.xpath("//select[@name='bday']//option[@selected='selected']")).getAttribute("value");
    String bmonth = wd.findElement(By.xpath("//select[@name='bmonth']//option[@selected='selected']")).getText();
    String byear = wd.findElement(By.xpath("//input[@class='byear']")).getAttribute("value");
    String aday = wd.findElement(By.xpath("//select[@name='aday']//option[@selected='selected']")).getAttribute("value");
    String amonth = wd.findElement(By.xpath("//select[@name='amonth']//option[@selected='selected']")).getText();
    String ayear = wd.findElement(By.xpath("//input[@name='ayear']")).getAttribute("value");

    wd.navigate().back();
    return new ContactData()
                    .withId(contact.getId())
                    .withFirstName(firstname)
                    .withMiddleName(middlename)
                    .withLastName(lastname)
                    .withNickname(nickname)
                    .withCompany(company)
                    .withTitle(title)
                    .withHomePhone(home)
                    .withMobilePhone(mobile)
                    .withWorkPhone(work)
                    .withFax(fax)
                    .withEmail1(email)
                    .withEmail2(email2)
                    .withEmail3(email3)
                    .withHomePage(homepage)
                    .withAddress1(address1)
                    .withAddress2(address2)
                    .withPrivatephone(privatephone)
                    .withNotes(notes)
                    .withBday(bday)
                    .withBmonth(bmonth)
                    .withByear(byear)
                    .withAday(aday)
                    .withAmonth(amonth)
                    .withAyear(ayear)
            //workaround
                    .withGroup("test1");

  }

  public ContactData infoFromDetailsForm(ContactData contact) {
    selectContactById(contact.getId()).findElement(By.xpath("../../td[7]/a")).click();
    String all = wd.findElement(By.xpath("//div[@id='content']")).getText();
    wd.navigate().back();
    return new ContactData().withAllDetails(all);
  }
}