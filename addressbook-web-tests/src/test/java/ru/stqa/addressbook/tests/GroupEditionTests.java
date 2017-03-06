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
    app.goTo().groupPage();
    if(app.group().all().size() == 0){
      app.group().create(new GroupData().withName(app.getData().getProperty("group.name")));
    }
  }

  @Test
  public void testGroupEdition() {
    Groups before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData()
            .withId(modifiedGroup.getId()).withName(app.getData().getProperty("group.name"))
            .withFooter(app.getData().getProperty("group.footer")).withHeader(app.getData().getProperty("group.header"));
    app.group().modify(group);
    assertEquals(app.group().count(), before.size() );
    Groups after = app.group().all();
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
  }
}