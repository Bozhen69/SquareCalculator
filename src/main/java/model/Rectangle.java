package model;

public class Rectangle extends AbstractQuadrangle {

    public Rectangle(String color, double[] sides) {
        super(color, sides[0], sides[1]);
    }

    public double getArea() {
        return height*weight;
    }

    public boolean equals(Object obj) {
        if(obj==this)
            return true;
        else if(obj==null)
            return false;
        else if (getClass() != obj.getClass())
            return false;
        else{
            Rectangle rectangle = (Rectangle) obj;
            return rectangle.height == this.height && rectangle.color.equals(this.color) && rectangle.weight==this.weight;
        }
    }
}
