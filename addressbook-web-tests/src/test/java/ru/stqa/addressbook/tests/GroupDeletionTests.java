package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by user on 14.12.2016.
 */
public class GroupDeletionTests extends TestBase{
  @Test
  public void testGroupDeletion() {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
  }
}
