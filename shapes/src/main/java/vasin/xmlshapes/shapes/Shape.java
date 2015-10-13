/**
 * Abstract class for all of the shapes. Provides methods for printing object information
 * and abstract method for calculating area of the shape.
 * 
 * 2015, Alexander Vasin <alexander.v.vasin@gmail.com>
 */

package vasin.xmlshapes.shapes;

import java.util.Locale;

public abstract class Shape {
	private final String color;
	private final long id;
	private float area;
	private String printInfo;
	
	public Shape(long id, String color) { 
		this.color = color;
		this.id = id;
		this.area = Float.NEGATIVE_INFINITY;
		this.printInfo = "";
	}
	
	public String getColor() {
		return color;
	}
	
	public long getID() {
		return id;
	}
	
	public float getArea() {
		if (area == Float.NEGATIVE_INFINITY) { // calculate area if it was not previously caclulated
			area = calculateArea();
		}
		return area;
	}
	
	public String toString() {
		if (printInfo.isEmpty()) {
			printInfo = String.format(Locale.ENGLISH, "%d: %s - %.2f", id, color, getArea());
		}
		return printInfo;
	}
	
	protected abstract float calculateArea(); // should be implemented for every subclass
}
