import model.*;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.Assert.*;

public class XMLParserTest {

    @Test
    public void parseDoc() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser;
        BlockingQueue<AbstractShape> shapes = new LinkedBlockingQueue<>();
        try {
            parser = factory.newSAXParser();
            ShapeParser sp = new ShapeParser(shapes);
            parser.parse("src/test/123.xml",sp);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BlockingQueue<AbstractShape> shapes1 = new LinkedBlockingQueue<>();
        shapes1.add(new Triangle("red",new double[]{1.5,1.5,1.5}));
        shapes1.add(new Square("green",5.0));
        shapes1.add(new Circle("orange",1.5));
        shapes1.add(new Rectangle("blue",new double[]{1.0,2.0}));
        shapes1.add(new Circle(ShapeParser.END_QUEUE,-5.0));
        for(int i=0;i<shapes1.size();i++){
            try {
                assertEquals(shapes1.take(),shapes.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}