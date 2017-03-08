package ru.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupEditionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    if(app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName(app.getData().getProperty("group.name")));
    }
  }

  @Test
  public void testGroupEdition() {
    Groups before = app.db().groups();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData()
            .withId(modifiedGroup.getId()).withName(app.getData().getProperty("group.name"))
            .withFooter(app.getData().getProperty("group.footer")).withHeader(app.getData().getProperty("group.header"));
    app.goTo().groupPage();
    app.group().modify(group);
    assertEquals(app.group().count(), before.size() );
    Groups after = app.db().groups();
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    verifyGroupListInUI();
  }
}