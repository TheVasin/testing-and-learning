/**
 * Class representing triangle.
 * 
 * 2015, Alexander Vasin <alexander.v.vasin@gmail.com>
 */

package vasin.xmlshapes.shapes;

public class Triangle extends Shape {
    private final float sideA, sideB, sideC;
    
    public Triangle(long id, String color, float sideA, float sideB, float sideC) {
        super(id, color);
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    @Override
    public float calculateArea() {
        float p = (sideA + sideB + sideC)/2;
        float area = p * (p - sideA) * (p - sideB) * (p - sideC);
        return (float) Math.sqrt(area);
    }
    
}
