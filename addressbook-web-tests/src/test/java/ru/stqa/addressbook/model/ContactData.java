package ru.stqa.addressbook.model;

/**
 * Created by user on 13.12.2016.
 */
public class ContactData {
  private final String firstName;
  private final String lastName;
  private final String phoneNumber;
  private final String emailAddress;
  private String group;

  public ContactData(String firstName, String lastName, String phoneNumber, String emailAddress, String group) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.emailAddress = emailAddress;
    this.group = group;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public String getGroup() {
    return group;
  }
}

