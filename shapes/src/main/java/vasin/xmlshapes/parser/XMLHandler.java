/**
 * This class handles events while parsing XML file, produce objects and puts them into queue.
 * 
 * 2015, Alexander Vasin <alexander.v.vasin@gmail.com>
 */

package vasin.xmlshapes.parser;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import vasin.xmlshapes.Errors;
import vasin.xmlshapes.queue.EndOfQueue;
import vasin.xmlshapes.shapes.Circle;
import vasin.xmlshapes.shapes.Rectangle;
import vasin.xmlshapes.shapes.Shape;
import vasin.xmlshapes.shapes.Square;
import vasin.xmlshapes.shapes.Triangle;

class XMLHandler extends DefaultHandler {
	private StringBuilder buffer;			// collects characters inside an element
	private String color;					// stores color of an element
	private long counter;					// counter to provide id for an every object
	private ArrayList<Float> sides;			// list of sides
	private BlockingQueue<Object> output;	// where to put new objects
	
	public XMLHandler(BlockingQueue<Object> output) {
		super();
		this.output = output;
	}
	
	public void startDocument() {
		// every time new document started
		sides = new ArrayList<Float>(4); // There should not occur shape with more that 4 sides
		counter = 1;
		buffer = new StringBuilder();
	}
	
	public void startElement (String uri, String name, String qName, Attributes attrs) {
		// when new element started
		resetBuffer();
	}
	
	public void endElement (String uri, String name, String qName) {
		// when element has ended we need to save these elements: shapes, color, side or diameter
		Object newObject = findObject(name); // check if there is an end of some shape
		if (newObject != null ) { // parsed the end of the new object. Need to put it into queue
			put(newObject);
			counter++;
			resetSides();
			resetColor();
			return;
		}

		if (Elements.COLOR.equalsIgnoreCase(name)) { // if that was color object, remember the color
			color = readString();
		}
		
		if (Elements.SIDE.equalsIgnoreCase(name) ||
				Elements.DIAMETER.equalsIgnoreCase(name)) { // if that was side or diameter, add it to list
			sides.add(readFloat());
		}		
	}
	
	public void characters (char[] ch, int start, int length) {
		collect(ch, start, length); // collect characters to get data from them later
	}
	
	public void endDocument() {
		put(new EndOfQueue()); // when document has ended put signal for printer to finish waiting for new objects
	}
	
	private void collect(char[] ch, int start, int length) {
		buffer.append(ch, start, length);
	}
	
	private void resetBuffer() {
		buffer.setLength(0);
	}
	
	private void resetSides() {
		sides.clear();
	}
	
	private void resetColor() {
		color = "";
	}
	
	private String readString() {
		// collect data from buffer
		buffer.trimToSize();
		return buffer.toString();
	}
	
	private float readFloat() {
		// collect float number from buffer
		return Float.parseFloat(readString());
	}
	
	private Object findObject(String name) {
		// if name is corresponding to a shape, create shape
		Shape newObject = null;
		if (Elements.TRIANGLE.equalsIgnoreCase(name)) {
			newObject = new Triangle(counter, color, sides.get(0), sides.get(1), sides.get(2));
		}
		if (Elements.RECTANGLE.equalsIgnoreCase(name)) {
			newObject = new Rectangle(counter, color, sides.get(0), sides.get(1));
		}
		if (Elements.CIRCLE.equalsIgnoreCase(name)) {
			newObject = new Circle(counter, color, sides.get(0));
		}
		if (Elements.SQUARE.equalsIgnoreCase(name)) {
			newObject = new Square(counter, color, sides.get(0));
		}
		return newObject;
	}
	
	private void put(Object o) {
		// put new object into queue or exit if interrupted
		try {
			output.put(o);
		} catch (InterruptedException e) {
			interrupted();
		}
	}
	
	private void interrupted() {
		Errors.printErrorAndExit(Errors.MESSAGE_INTERRUPTED, Errors.ERROR_INTERRUPTED);
	}
}
