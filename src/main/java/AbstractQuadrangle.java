public abstract class AbstractQuadrangle extends AbstractShape {
    protected double height;
    protected double weight;

    public AbstractQuadrangle(String color, double height, double weight) {
        super(color);
        this.height = height;
        this.weight = weight;
    }

    public abstract double getArea();
}
