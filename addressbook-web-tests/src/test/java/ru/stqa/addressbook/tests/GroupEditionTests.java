package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;

/**
 * Created by user on 14.12.2016.
 */
public class GroupEditionTests extends TestBase{
  @Test
  public void testGroupEdition() {
    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupHelper().getGroupCount();
    if(! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().editSelectedGroups();
    app.getGroupHelper().fillGroupForm(new GroupData("nowy2", "test2", "test3"));
    app.getGroupHelper().submitGroupEdition();
    app.getGroupHelper().returnToGroupPage();
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before );
  }
}
