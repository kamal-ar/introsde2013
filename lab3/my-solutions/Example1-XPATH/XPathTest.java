import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XPathTest {

	  public static void main(String[] args)  throws ParserConfigurationException, SAXException,
	          											IOException, XPathExpressionException {

	    DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
	    domFactory.setNamespaceAware(true);
	    DocumentBuilder builder = domFactory.newDocumentBuilder();
	    System.out.println("Loading books.xml...");
	    Document doc = builder.parse("books.xml");

	    XPathFactory factory = XPathFactory.newInstance();
	    XPath xpath = factory.newXPath();
	    System.out.println("Reading list of titles...");
	    System.out.println("(using xpath = /bookstore/book/title/text() | /bookstore/book/author/text()");
	    XPathExpression expr = xpath.compile("/bookstore/book/title/text() | /bookstore/book/author/text()");

	    Object result = expr.evaluate(doc, XPathConstants.NODESET);
	    NodeList nodes = (NodeList) result;
	    for (int i = 0; i < nodes.getLength(); i++) {
	        System.out.println(nodes.item(i).getNodeValue());
	    }

/*            System.out.println("Reading list of authors...");
            System.out.println("(using xpath = /bookstore/book/author/text()");
            XPathExpression expr2 = xpath.compile("/bookstore/book/author/text()");

            Object result2 = expr2.evaluate(doc, XPathConstants.NODESET);
            NodeList nodes2 = (NodeList) result2;
            for (int i = 0; i < nodes2.getLength(); i++) {
                System.out.println(nodes2.item(i).getNodeValue());
            }
*/
	  }
}
