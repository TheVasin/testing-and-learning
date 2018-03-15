/**
 * Class representing rectangle.
 * 
 * 2015, Alexander Vasin <alexander.v.vasin@gmail.com>
 */

package vasin.xmlshapes.shapes;

public class Rectangle extends Shape {
    private final float sideA, sideB;
    
    public Rectangle(long id, String color, float sideA, float sideB) {
        super(id, color);
        this.sideA = sideA;
        this.sideB = sideB;
    }

    @Override
    public float calculateArea() {
        return sideA * sideB;
    }

}
