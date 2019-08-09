import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class XMLParser {
    public static void main(String[] args) {
        parseDoc(args[0]);
    }
    public static void parseDoc(String fileName){
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser;
        BlockingQueue<AbstractShape> shapes = new LinkedBlockingQueue<>();
        Thread thOutput = new Thread(()->{
            try {
                while (!Thread.interrupted()) {
                    AbstractShape shape = shapes.take();
                    if(shape.getColor().equals(ShapeParser.END_QUEUE))
                        break;
                    System.out.println(shape.toString());
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        try {
            parser = factory.newSAXParser();
            ShapeParser sp = new ShapeParser(shapes);
            thOutput.start();
            parser.parse(fileName,sp);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            thOutput.interrupt();
        }
    }
}
