package ru.stqa.pft.mantis.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table( name = "addressbook")
public class ContactData {
  @XStreamOmitField
  @Id
  @Column (name = "id")
  private int id = Integer.MAX_VALUE;
  @Expose
  @Column (name = "firstname")
  private String firstName;
  @Expose
  @Column (name = "lastname")
  private String lastName;
  @Expose
  @Transient
  private String emailAddress;
  //@Expose
  //@Transient
  //private String group;
  @Expose
  @Column (name = "home")
  @Type(type = "text")
  private String homePhone;
  @Expose
  @Column (name = "mobile")
  @Type(type = "text")
  private String mobilePhone;
  @Expose
  @Column (name = "work")
  @Type(type = "text")
  private String workPhone;
  @Expose
  @Column (name = "email")
  @Type(type = "text")
  private String email1;
  @Expose
  @Column (name = "email2")
  @Type(type = "text")
  private String email2;
  @Expose
  @Column (name = "email3")
  @Type(type = "text")
  private String email3;
  @XStreamOmitField
  @Transient
  private String allPhones;
  @XStreamOmitField
  @Transient
  private String allEmails;
  @Expose
  @Column (name = "address")
  @Type(type = "text")
  private String address1;
  @Expose
  @Column (name = "address2")
  @Type(type = "text")
  private String address2;
  @Expose
  @Column (name = "middlename")
  private String middlename;
  @Expose
  @Column (name = "nickname")
  private String nickname;
  @Expose
  @Column (name = "company")
  private String company;
  @Expose
  @Column (name = "title")
  private String title;
  @Expose
  @Column (name = "fax")
  @Type(type = "text")
  private String fax;
  @Expose
  @Column (name = "homepage")
  @Type(type = "text")
  private String homepage;
  @Expose
  @Column (name = "phone2")
  @Type(type = "text")
  private String privatephone;
  @Expose
  @Column (name = "bday")
  @Type(type = "byte")
  private byte bday;
  @Expose
  @Column (name = "bmonth")
  private String bmonth;
  @Expose
  @Column (name = "byear")
  private String byear;
  @Expose
  @Column (name = "aday")
  @Type(type = "byte")
  private byte aday;
  @Expose
  @Column (name = "amonth")
  private String amonth;
  @Expose
  @Column (name = "ayear")
  private String ayear;
  @Expose
  @Column (name = "notes")
  @Type(type = "text")
  private String notes;
  @XStreamOmitField
  @Transient
  private String alldetails;
  @Expose
  @Column (name = "photo")
  @Type(type = "text")
  private String photo;

  @JoinTable (name = "address_in_groups",
          joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  @ManyToMany (fetch = FetchType.EAGER)
  private Set<GroupData> groups = new HashSet<GroupData>();

  public Groups getGroups() {
    return new Groups(groups);
  }

  public ContactData withGroups(String group) {
    this.group = group;
    return this;
  }





  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }

  public File getPhoto() {
    return new File(photo);
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public String getAlldetails() {
    return alldetails;
  }

  public ContactData withAllDetails(String alldetails) {
    this.alldetails = alldetails;
    return this;
  }

  public String getNotes() {
    return notes;
  }

  public ContactData withNotes(String notes) {
    this.notes = notes;
    return this;
  }

  public String getMiddlename() {
    return middlename;
  }

  public ContactData withMiddleName(String middlename) {
    this.middlename = middlename;
    return this;
  }

  public String getNickname() {
    return nickname;
  }

  public ContactData withNickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

  public String getCompany() {
    return company;
  }

  public ContactData withCompany(String company) {
    this.company = company;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public ContactData withTitle(String title) {
    this.title = title;
    return this;
  }

  public String getFax() {
    return fax;
  }

  public ContactData withFax(String fax) {
    this.fax = fax;
    return this;
  }

  public String getHomepage() {
    return homepage;
  }

  public ContactData withHomePage(String homepage) {
    this.homepage = homepage;
    return this;
  }

  public String getPrivatephone() {
    return privatephone;
  }

  public ContactData withPrivatephone(String privatephone) {
    this.privatephone = privatephone;
    return this;
  }

  public String getBday() {
    return Byte.toString(bday);
  }

  public ContactData withBday(String bday) {
    this.bday = bday.getBytes()[0];
    return this;
  }

  public String getBmonth() {
    return bmonth;
  }

  public ContactData withBmonth(String bmonth) {
    this.bmonth = bmonth;
    return this;
  }

  public String getByear() {
    return byear;
  }

  public ContactData withByear(String byear) {
    this.byear = byear;
    return this;
  }

  public String getAday() {
    return Byte.toString(aday);
  }

  public ContactData withAday(String aday) {
    this.aday = aday.getBytes()[0];
    return this;
  }

  public String getAmonth() {
    return amonth;
  }

  public ContactData withAmonth(String amonth) {
    this.amonth = amonth;
    return this;
  }

  public String getAyear() {
    return ayear;
  }

  public ContactData withAyear(String ayear) {
    this.ayear = ayear;
    return this;
  }

  public String getAddress1() {
    return address1;
  }

  public ContactData withAddress1(String address1) {
    this.address1 = address1;
    return this;
  }

  public String getAddress2() {
    return address2;
  }

  public ContactData withAddress2(String address2) {
    this.address2 = address2;
    return this;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public String getEmail1() {
    return email1;
  }

  public ContactData withEmail1(String email) {
    this.email1 = email;
    return this;
  }

  public String getEmail2() {
    return email2;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public String getEmail3() {
    return email3;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public int getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  //public String getGroup() {
  //  return group;
  //}

  public ContactData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ContactData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ContactData withPhoneNumber(String homephone) {
    this.homePhone = homephone;
    return this;
  }

  public ContactData withEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
    return this;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
    if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
    if (homePhone != null ? !homePhone.equals(that.homePhone) : that.homePhone != null) return false;
    if (mobilePhone != null ? !mobilePhone.equals(that.mobilePhone) : that.mobilePhone != null) return false;
    if (workPhone != null ? !workPhone.equals(that.workPhone) : that.workPhone != null) return false;
    if (email1 != null ? !email1.equals(that.email1) : that.email1 != null) return false;
    if (email2 != null ? !email2.equals(that.email2) : that.email2 != null) return false;
    if (email3 != null ? !email3.equals(that.email3) : that.email3 != null) return false;
    if (address1 != null ? !address1.equals(that.address1) : that.address1 != null) return false;
    if (address2 != null ? !address2.equals(that.address2) : that.address2 != null) return false;
    if (middlename != null ? !middlename.equals(that.middlename) : that.middlename != null) return false;
    if (nickname != null ? !nickname.equals(that.nickname) : that.nickname != null) return false;
    if (company != null ? !company.equals(that.company) : that.company != null) return false;
    if (title != null ? !title.equals(that.title) : that.title != null) return false;
    if (fax != null ? !fax.equals(that.fax) : that.fax != null) return false;
    if (homepage != null ? !homepage.equals(that.homepage) : that.homepage != null) return false;
    return privatephone != null ? privatephone.equals(that.privatephone) : that.privatephone == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    result = 31 * result + (homePhone != null ? homePhone.hashCode() : 0);
    result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
    result = 31 * result + (workPhone != null ? workPhone.hashCode() : 0);
    result = 31 * result + (email1 != null ? email1.hashCode() : 0);
    result = 31 * result + (email2 != null ? email2.hashCode() : 0);
    result = 31 * result + (email3 != null ? email3.hashCode() : 0);
    result = 31 * result + (address1 != null ? address1.hashCode() : 0);
    result = 31 * result + (address2 != null ? address2.hashCode() : 0);
    result = 31 * result + (middlename != null ? middlename.hashCode() : 0);
    result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
    result = 31 * result + (company != null ? company.hashCode() : 0);
    result = 31 * result + (title != null ? title.hashCode() : 0);
    result = 31 * result + (fax != null ? fax.hashCode() : 0);
    result = 31 * result + (homepage != null ? homepage.hashCode() : 0);
    result = 31 * result + (privatephone != null ? privatephone.hashCode() : 0);
    return result;
  }

  public ContactData inGroup(GroupData group) {
    groups.add(group);
    return this;

  }
}