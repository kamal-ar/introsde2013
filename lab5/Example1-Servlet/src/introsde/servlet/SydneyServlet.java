package introsde.servlet;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.bind.*;

import oracle.jrockit.jfr.settings.JSONElement;

import org.w3c.dom.*;
import org.w3c.dom.Element;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Iterator;


/**
 * Servlet implementation class SydneyServlet
 */
@WebServlet("/SydneyServlet")
public class SydneyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String [] places = {
		"Harbour Bridge", 
		"Circular Quay", 
		"Taronga Zoo", 
		"Manly Beach", 
		"Darling Harbour", 
		"Coogee Beach", 
		"University of New South Wales"
	};
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SydneyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(
			HttpServletRequest request, 
			HttpServletResponse response
			) throws ServletException, IOException {
		
		// GET http://starbucks/order/json"
		
		if ("/json".equals(request.getPathInfo())) {
			jsonReply(response);
		} else if ("/csv".equals(request.getPathInfo())) {
			csvReply(response);
		} else {
			try {
				try {
					xmlReply(response);
				} catch (TransformerFactoryConfigurationError
						| TransformerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void xmlReply(HttpServletResponse response) 
			throws ParserConfigurationException, JAXBException, 
			IOException, TransformerFactoryConfigurationError, TransformerException {
		response.setContentType("text/xml");
		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		//creating a new instance of a DOM to build a DOM tree.
		Document doc = docBuilder.newDocument();
		
		Element root = doc.createElement("cityplaces");
		doc.appendChild(root);

		Element city = doc.createElement("city");
		city.setTextContent("Sydney");
		root.appendChild(city);
		
		Element list = doc.createElement("places");
		for (String placeName : places) {
			Element place = doc.createElement("place");
			place.setTextContent(placeName);
			
			list.appendChild(place);
		}
		root.appendChild(list);
		
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		
		// create string from xml tree
		StringWriter sw = new StringWriter();
		StreamResult result = new StreamResult(sw);
		DOMSource source = new DOMSource(doc);
		transformer.transform(source, result);
		String xmlString = sw.toString();
		
		response.getWriter().write(xmlString);
	}

	private void csvReply(HttpServletResponse response) {
		response.setContentType("text/csv");
		try {
			Writer writer = response.getWriter();
			writer.write("City.place\n");
			
			for (String place: places) {
				writer.write("Sydney,");
				writer.write(place+"\n");
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void jsonReply(HttpServletResponse response) {
		// TODO
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}