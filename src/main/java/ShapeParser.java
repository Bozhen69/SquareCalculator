import enums.TypeShapeElement;
import enums.XmlParameters;
import factory.ShapeFactory;
import model.AbstractShape;
import model.Circle;
import org.apache.commons.lang3.EnumUtils;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

import java.util.HashMap;
import java.util.concurrent.BlockingQueue;

public class ShapeParser extends DefaultHandler {

    public static final String END_QUEUE = "END_THIS_QUEUE";

    private BlockingQueue<AbstractShape> shapes;

    public ShapeParser(BlockingQueue<AbstractShape> shapes) {
        this.shapes = shapes;
    }

    private HashMap<XmlParameters,String> parameters = new HashMap<>();

    private TypeShapeElement currentShapeType;

    private XmlParameters currentParameter;

    @Override
    public void startDocument() {
        System.out.println("document start");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if(EnumUtils.isValidEnumIgnoreCase(TypeShapeElement.class,qName)){
            currentShapeType =  EnumUtils.getEnumIgnoreCase(TypeShapeElement.class,qName);
        }else if(EnumUtils.isValidEnumIgnoreCase(XmlParameters.class,qName)){
            currentParameter = EnumUtils.getEnumIgnoreCase(XmlParameters.class,qName);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String information = new String(ch, start, length);
        information = information.replace("\n", "").trim();
        if(currentParameter!=null && !information.equals("")) {
            parameters.merge(currentParameter,information,(v1,v2)-> v1+";"+v2);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if(EnumUtils.isValidEnumIgnoreCase(TypeShapeElement.class,qName)){
            shapes.add(ShapeFactory.newInstance(currentShapeType,parameters));
            parameters.clear();
            currentShapeType=null;
        }
        currentParameter=null;
    }

    @Override
    public void endDocument() {
        shapes.add(new Circle(END_QUEUE,0.0));
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        throw e;
    }
}
