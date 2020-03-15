package model;

public class Square extends AbstractQuadrangle {

    public Square(String color, double side) {
        super(color, side, side);
    }

    public double getArea() {
        return Math.pow(height,2.0);
    }

    public boolean equals(Object obj) {
        if(obj==this)
            return true;
        else if(obj==null)
            return false;
        else if (getClass() != obj.getClass())
            return false;
        else{
            Square rectangle = (Square) obj;
            return rectangle.height == this.height && rectangle.color.equals(this.color) && rectangle.weight==this.weight;
        }
    }
}
