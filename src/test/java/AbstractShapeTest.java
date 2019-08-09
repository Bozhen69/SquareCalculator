import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AbstractShapeTest {

    @Test
    public void toString_Test() {
        List<AbstractShape> testShape = new ArrayList<>();
        testShape.add(new Circle("circle",2.0));
        testShape.add(new Triangle("triangle",new double[]{1,2,2}));
        testShape.add(new Square("square",2));
        testShape.add(new Rectangle("rectangle",2.0,3.0));
        List<String> result = new ArrayList<>();
        result.add("0: circle - 3,14");
        result.add("1: triangle - 0,97");
        result.add("2: square - 4,00");
        result.add("3: rectangle - 6,00");
        for(int i=0;i<testShape.size();i++){
            Assert.assertEquals(testShape.get(i).toString(),result.get(i));
        }
    }

}