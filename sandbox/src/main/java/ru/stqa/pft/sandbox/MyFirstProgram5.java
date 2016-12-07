package ru.stqa.pft.sandbox;

public class MyFirstProgram5 {
	public static void main(String[] args){
		System.out.println("Hello World !");

		Point p1 = new Point(2,5);
		Point p2 = new Point(5,9);

		System.out.println("Distance between 2 points is: " + distance(p1,p2));
		System.out.println("Distance between 2 points is: " + p2.distance(p1));
	}

	public static double distance(Point p1, Point p2){
		return Math.sqrt( (p1.x - p2.x)*(p1.x - p2.x) + (p1.y - p2.y)*(p1.y - p2.y) );
	}
}