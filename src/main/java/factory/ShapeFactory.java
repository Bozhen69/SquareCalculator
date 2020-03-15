package factory;

import enums.TypeShapeElement;
import enums.XmlParameters;
import model.*;

import java.util.Arrays;
import java.util.Map;


public class ShapeFactory {

    public static AbstractShape newInstance(TypeShapeElement shapeElement, Map<XmlParameters, String> parameters) {
        double[] params = new double[]{};
        if(parameters.getOrDefault(XmlParameters.SIDE,null)!=null) {
             params = Arrays.stream(parameters.get(XmlParameters.SIDE).split(";"))
                    .mapToDouble(Double::valueOf)
                    .toArray();
        }
        switch (shapeElement) {
            case CIRCLE:
                double diameter = Double.valueOf(parameters.get(XmlParameters.DIAMETER));
                return new Circle(parameters.get(XmlParameters.COLOR), diameter);
            case SQUARE:
                return new Square(parameters.get(XmlParameters.COLOR),params[0]);
            case TRIANGLE:
                return new Triangle(parameters.get(XmlParameters.COLOR),params);
            case RECTANGLE:
                return new Rectangle(parameters.get(XmlParameters.COLOR),params);
        }
        return null;
    }

}
