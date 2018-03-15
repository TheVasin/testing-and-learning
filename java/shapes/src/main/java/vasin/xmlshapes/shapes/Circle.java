/**
 * Class representing circle.
 * 
 * 2015, Alexander Vasin <alexander.v.vasin@gmail.com>
 */

package vasin.xmlshapes.shapes;

public class Circle extends Shape{
    private final float diameter;
    
    public Circle(long id, String color, float diameter) {
        super(id, color);
        this.diameter = diameter;
    }

    @Override
    public float calculateArea() {
        float r = diameter/2;
        return (float) Math.PI * r * r;
    }

}
