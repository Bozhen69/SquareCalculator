public class Circle extends AbstractShape {

    public Circle(String color, double diameter) {
        super(color);
        this.diameter = diameter;
    }

    private double diameter;



    public double getArea() {
        return 0.25*Math.PI*Math.pow(diameter,2.0);
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }


    @Override
    public boolean equals(Object obj) {
        if(obj==this)
            return true;
        else if(obj==null)
            return false;
        else if (getClass() != obj.getClass())
            return false;
        else{
            Circle circle = (Circle)obj;
            return circle.diameter == this.diameter && circle.color.equals(this.color);
        }
    }
}
