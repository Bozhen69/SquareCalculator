package model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class AbstractShape {
    private static long count;

    private final long id=count++;

    protected String color;

    public static void restart() {
        count=0;
    }

    public AbstractShape(String color) {
        this.color = color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public abstract double getArea();

    @Override
    public String toString() {
        double area = new BigDecimal(getArea()).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return String.format("%d: %s - %.2f",id,color,area);
    }
}
