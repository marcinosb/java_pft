package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by user on 10.12.2016.
 */
public class PointTests {
  @Test
  public void testDistance1(){
    Point p1 = new Point(2,5);
    Point p2 = new Point(5,9);

    Assert.assertEquals(p1.distance(p2), 5.0);
  }

  @Test
  public void testDistance2(){
    Point p1 = new Point(1,1);
    Point p2 = new Point(1,1);

    Assert.assertEquals(p1.distance(p2), 0.0);
  }
}