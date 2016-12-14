package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;

/**
 * Created by user on 14.12.2016.
 */
public class GroupEditionTests extends TestBase{
  @Test
  public void testGroupEdition() {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().editSelectedGroups();
    app.getGroupHelper().fillGroupForm(new GroupData("nowy1", "nowy2", "nowy3"));
    app.getGroupHelper().submitGroupEdition();
    app.getGroupHelper().returnToGroupPage();
  }
}
