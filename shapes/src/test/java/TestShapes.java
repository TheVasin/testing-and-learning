/**
 * This class checks that shapes gives correct area.
 * This file was taken as example:
 *     <shapes>
 *         <triangle>
 *           <color>red</color>
 *           <side>18</side>
 *           <side>30</side>
 *           <side>24</side>
 *         </triangle>
 *         <circle>
 *           <color>orange</color>
 *           <diameter>20</diameter>
 *         </circle>
 *         <square>
 *           <color>yellow</color>
 *           <side>4</side>
 *         </square>
 *         <rectangle>
 *           <color>black</color>
 *           <side>3</side>
 *           <side>2</side>
 *         </rectangle>
 *     </shapes>
 * 
 * 2015, Alexander Vasin <alexander.v.vasin@gmail.com>
 */

import static org.junit.Assert.*;

import org.junit.Test;

import vasin.xmlshapes.shapes.Circle;
import vasin.xmlshapes.shapes.Rectangle;
import vasin.xmlshapes.shapes.Square;
import vasin.xmlshapes.shapes.Triangle;

public class TestShapes {
    private final float DELTA = 0.001f;
    @Test
    public void testShapes() {
        Triangle t = new Triangle(1, "red", 18, 30, 24);
        Circle c = new Circle(2, "orange", 20);
        Square s = new Square(3, "yellow", 4);
        Rectangle r = new Rectangle(4, "black", 3, 2);
        
        assertEquals(t.getArea(), 216.00f, DELTA);
        assertEquals(c.getArea(), 314.1592654f, DELTA);
        assertEquals(s.getArea(), 16.00f, DELTA);
        assertEquals(r.getArea(), 6.00f, DELTA);
    }
}
