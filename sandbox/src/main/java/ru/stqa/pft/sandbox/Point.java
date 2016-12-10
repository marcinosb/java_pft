package ru.stqa.pft.sandbox;

/**
 * Created by user on 07.12.2016.
 */
public class Point {
  int x;
  int y;

  Point(int x, int y){
    this.x = x;
    this.y = y;
  }
  public double distance(Point p){
    return Math.sqrt( (this.x - p.x)*(this.x - p.x) + (this.y - p.y)*(this.y - p.y) );
  }
}