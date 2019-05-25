

import java.io.StringWriter;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public class DOM{
	public static Document getDocument(String path) {
		Document document = null;
		//System.out.println("tu1");
		try {

			//System.out.println("tu2");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			document = db.parse(path);
		}catch (Exception ex) {
			document = null;

			//System.out.println("tu3");
		}
		return document;
	}
	
	public static String getXML(Document document) {
		String result ="";
		try {
			TransformerFactory tff = TransformerFactory.newInstance();
			Transformer tf = tff.newTransformer();
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			StringWriter sw = new StringWriter();
			StreamResult sr = new StreamResult(sw);
			DOMSource source = new DOMSource(document);
			tf.transform(source, sr);
			result = sw.toString();
		}catch (Exception ex) {
			result = "";
		}
		return result;
	}
	
	public static void saveXMLContent(Document document, String path) {
		try {
			TransformerFactory tff = TransformerFactory.newInstance();
			Transformer tf = tff.newTransformer();
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource ds = new DOMSource(document);
			StreamResult sr = new StreamResult(path);
			tf.transform(ds, sr);
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
}