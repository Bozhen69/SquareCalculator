import model.AbstractShape;
import org.xml.sax.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class XMLParser {
    public static void main(String[] args) {
            parseDoc(args[0],"shapes.xsd");
    }

    public static void parseDoc(String fileName, String xsd) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(false);
        factory.setNamespaceAware(true);

        SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        SAXParser parser = null;
        try {
            factory.setSchema(schemaFactory.newSchema(new Source[] {new StreamSource( xsd )}));
            parser = factory.newSAXParser();
        }

        catch (SAXException | ParserConfigurationException se) {
            System.out.println("SCHEMA : " + se.getMessage());
            return;
        }

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
            System.out.println("FATAL ERROR in xml file");
            e.printStackTrace();
            thOutput.interrupt();
        }

    }
}
