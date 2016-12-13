package ru.stqa.addressbook;

/**
 * Created by user on 13.12.2016.
 */
public class ContactData {
  private final String firstName;
  private final String lastName;
  private final String phoneNumber;
  private final String emailAddress;

  public ContactData(String firstName, String lastName, String phoneNumber, String emailAddress) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.emailAddress = emailAddress;
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
}

