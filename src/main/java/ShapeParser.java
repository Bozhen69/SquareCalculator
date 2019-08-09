import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ShapeParser extends DefaultHandler {

    public static final String END_QUEUE = "END_THIS_QUEUE";

    private BlockingQueue<AbstractShape> shapes = new LinkedBlockingQueue<>();

    public ShapeParser(BlockingQueue<AbstractShape> shapes) {
        this.shapes = shapes;
    }

    private String typeElement = "";
    LinkedList<String> parameters = new LinkedList<>();
    @Override
    public void startDocument() {
        System.out.println("document start");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if(qName.equals("circle")){
            typeElement = "circle";
        }
        else if(qName.equals("triangle")){
            typeElement="triangle";
        }
        else if(qName.equals("square") || qName.equals("rectangle")){
            typeElement="quadrangle";
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String information = new String(ch, start, length);
        information = information.replace("\n", "").trim();
        if(typeElement.equals("circle")&& !information.equals("")){
            parameters.add(information);
        }
        else if(typeElement.equals("triangle") && !information.equals("")){
            parameters.add(information);
        }
        else if(!information.equals("") && typeElement.equals("quadrangle")){
            parameters.add(information);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if(qName.equals("circle")){
            shapes.add(new Circle(parameters.get(0),Double.parseDouble(parameters.get(1))));
            parameters.clear();
        }
        else if(qName.equals("triangle")){
            double[] sides = parameters.subList(1,4).stream().mapToDouble(s->Double.parseDouble(s)).toArray();;
            shapes.add(new Triangle(parameters.get(0),sides));
            parameters.clear();
        }
        else if(qName.equals("square") || qName.equals("rectangle")){
            if(parameters.size()==3){
                double[] p = parameters.subList(1,3).stream().mapToDouble(s->Double.parseDouble(s)).toArray();
                shapes.add(new Rectangle(parameters.get(0),p[0],p[1]));
            }else{
                shapes.add(new Square(parameters.get(0),Double.parseDouble(parameters.get(1))));
            }
            parameters.clear();
        }
    }

    @Override
    public void endDocument() {
        shapes.add(new Circle(END_QUEUE,-5.0));
    }
}
