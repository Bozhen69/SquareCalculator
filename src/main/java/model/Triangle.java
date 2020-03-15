package model;

public class Triangle extends AbstractShape {
    private double[] side;

    public Triangle(String color, double[] side) {
        super(color);
        this.side = side;
    }

    public double getArea() {
        double square=0;
        double semiPerimeter = getPerimeter()/2;
        square = semiPerimeter*((semiPerimeter-side[0])*(semiPerimeter-side[1])*(semiPerimeter-side[2]));
        return Math.sqrt(square);
    }

    public double getPerimeter(){
        double perimeter = 0;
        for(double s: side)
            perimeter += s;
        return perimeter;
    }

    public double[] getSide() {
        return side;
    }

    public void setSide(double[] side) {
        this.side = side;
    }

    public boolean equals(Object obj) {
        if(obj==this)
            return true;
        else if(obj==null)
            return false;
        else if (getClass() != obj.getClass())
            return false;
        else{
            Triangle rectangle = (Triangle) obj;
            for(int i=0;i<side.length;i++){
                if(side[i]!= rectangle.side[i])
                    return false;
            }
            return rectangle.color.equals(this.color);
        }
    }
}
