package ru.stqa.pft.mantis.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.mantis.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;
  @Parameter(names = "-f",description = "Target file")
  public String file;
  @Parameter(names = "-d",description = "Data format")
  public String format;

  public static void main( String [] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try{
      jCommander.parse(args);
    }
    catch(ParameterException ex){
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if(format.equals("csv")){
      saveAsCSV(contacts, new File(file));
    }else if (format.equals("xml")){
      saveAsXML(contacts, new File(file));
    } else if (format.equals("json")) {
      saveAsJSON(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format...");
    }
  }

  private void saveAsJSON(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    try( Writer writer = new FileWriter(file)) {
      writer.write(json);
    }
  }

  private void saveAsXML(List<ContactData> contacts, File file) throws IOException {
    XStream xStream = new XStream();
    xStream.processAnnotations(ContactData.class);
    String xml = xStream.toXML(contacts);
    try( Writer writer = new FileWriter(file)) {
      writer.write(xml);
    }
  }

  private void saveAsCSV(List<ContactData> contacts, File file) throws IOException {
    try (Writer writer = new FileWriter(file)) {
      for (ContactData contact : contacts) {
        writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;\n"
                , contact.getFirstName()
                , contact.getMiddlename()
                , contact.getLastName()
                , contact.getNickname()
                , contact.getCompany()
                , contact.getTitle()
                , contact.getAddress1()
                , contact.getHomePhone()
                , contact.getMobilePhone()
                , contact.getWorkPhone()
                , contact.getFax()
                , contact.getEmail1()
                , contact.getEmail2()
                , contact.getEmail3()
                , contact.getHomepage()
                , contact.getBday()
                , contact.getBmonth()
                , contact.getByear()
                , contact.getAday()
                , contact.getAmonth()
                , contact.getAyear()
                , contact.getAddress2()
                , contact.getPrivatephone()
                , contact.getNotes()
                , contact.getGroup()
                , contact.getPhoto()));
      }
    }
  }

  private List<ContactData> generateContacts(int count) {
    File photo = new File("./src/test/resources/hqdefault.jpg");
    List<ContactData> contacts = new ArrayList<ContactData>();
    for(int i = 0; i < count; i++){
      contacts.add(new ContactData()
              .withFirstName("Piotr "+ i)
              .withMiddleName("Miroslaw "+ i)
              .withLastName("Piotrowski " + i)
              .withNickname("siwy " + i)
              .withCompany("Comarch " + i)
              .withTitle("Pan " + i)
              .withAddress1("ul. Glogera, 31-222 Kraków " + i)
              .withHomePhone("123456789"+ i)
              .withMobilePhone("500500500"+ i)
              .withWorkPhone("12400400400"+ i)
              .withFax("900900900"+ i)
              .withEmail1("piotr"+i+"@piotr.com")
              .withEmail2("piotr2"+i+"@piotr.com")
              .withEmail3("piotr3"+i+"@piotr.com")
              .withHomePage("www.piotr"+i+".com")
              .withBday("10")
              .withBmonth("Maj")
              .withByear("2000")
              .withAday("20")
              .withAmonth("Luty")
              .withAyear("1900")
              .withAddress2("Wlokietka 135/100 30-100 Kraków")
              .withPrivatephone("8989898989"+i)
              .withNotes("none"+i)
              .withGroup("test")
              .withPhoto(photo));
    }
    return contacts;
  }
}
