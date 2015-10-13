/**
 * Class representing square.
 * 
 * 2015, Alexander Vasin <alexander.v.vasin@gmail.com>
 */

package vasin.xmlshapes.shapes;

public class Square extends Shape {
    private float side;
    
    public Square(long id, String color, float side) {
        super(id, color);
        this.side = side;
    }

    @Override
    public float calculateArea() {
        return side * side;
    }
    
    public float getSide() {
        return side;
    }

}
