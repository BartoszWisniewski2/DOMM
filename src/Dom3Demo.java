import java.io.FileOutputStream;

// rejestr do tworzenia implementacji DOM
import org.w3c.dom.bootstrap.DOMImplementationRegistry;

// Implementacja DOM Level 3 Load & Save
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSParser; // Do serializacji (zapisywania) dokumentow
import org.w3c.dom.ls.LSSerializer;
import org.w3c.dom.ls.LSOutput;

// Konfigurator i obsluga bledow
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMError;
import org.w3c.dom.DOMErrorHandler;

// Do pracy z dokumentem
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Dom3Demo {
	public static Document document;

	public static void main(String[] args) {

		/*if (args.length == 0) {
			printUsage();
			System.exit(1);
		}*/

		try {
			/*
			 * ustawienie systemowej wlasnosci (moze byc dokonane w innym
			 * miejscu, pliku konfiguracyjnym w systemie itp.) konkretna
			 * implementacja DOM
			 */
			System.setProperty(DOMImplementationRegistry.PROPERTY,
					"org.apache.xerces.dom.DOMXSImplementationSourceImpl");
			DOMImplementationRegistry registry = DOMImplementationRegistry
					.newInstance();

			// pozyskanie implementacji Load & Save DOM Level 3 z rejestru
			DOMImplementationLS impl = (DOMImplementationLS) registry
					.getDOMImplementation("LS");

			// stworzenie DOMBuilder
			LSParser builder = impl.createLSParser(
					DOMImplementationLS.MODE_SYNCHRONOUS, null);

			// pozyskanie konfiguratora - koniecznie zajrzec do dokumentacji co
			// mozna poustawiac
			DOMConfiguration config = builder.getDomConfig();

			// stworzenie DOMErrorHandler i zarejestrowanie w konfiguratorze
			DOMErrorHandler errorHandler = getErrorHandler();
			config.setParameter("error-handler", errorHandler);

			// set validation feature
			config.setParameter("validate", Boolean.TRUE);

			// set schema language
			config.setParameter("schema-type",
					"http://www.w3.org/2001/XMLSchema");

			// set schema location
			config.setParameter("schema-location", "ceny.xsd");

			System.out.println("Parsowanie " + "ceny.xml" + "...");
			// sparsowanie dokumentu i pozyskanie "document" do dalszej pracy
			document = builder.parseURI("ceny.xml");

			// praca z dokumentem, modyfikacja zawartosci etc... np.
			// tutaj dodanie nowego pracownika poprzez skopiowanie innego
			int najmniejsza=999999999;
			int x=document.getElementsByTagName("produkt").getLength();
			for(int j=0;j<x;j++) {
			Node staff = document.getElementsByTagName("produkt").item(j);
			NodeList list = staff.getChildNodes();

			for (int i = 0; i < list.getLength(); i++) {
				
	                   Node node = list.item(i);

			   // get the salary element, and update the value
			   if ("cena".equals(node.getNodeName())) {
				int k=Integer.valueOf(node.getTextContent());
				if(najmniejsza>k) {
					najmniejsza=k;
				}
				node.setTextContent(String.valueOf((k+1000)));
			   }
			}
			}
			System.out.println(najmniejsza);
			// pozyskanie serializatora
			LSSerializer domWriter = impl.createLSSerializer();
			// pobranie konfiguratora dla serializatora
			config = domWriter.getDomConfig();
			config.setParameter("xml-declaration", Boolean.TRUE);

			// pozyskanie i konfiguracja Wyjscia
			LSOutput dOut = impl.createLSOutput();
			dOut.setEncoding("latin2");
			dOut.setByteStream(new FileOutputStream("new_" + "cenyn.xml"));

			System.out.println("Serializing document... ");
			domWriter.write(document, dOut);

			// Wyjscie na ekran
			// dOut.setByteStream(System.out);
			// domWriter.writeNode(System.out, document);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static void printUsage() {

		System.err.println("usage: java Dom3Demo uri");
		System.err.println();
		System.err
				.println("NOTE: You can only validate DOM tree against XML Schemas.");

	}

	// obsluga bledow za pomoca anonimowej klasy wewnetrznej implementujacej
	// DOMErrorHandler
	// por. SAX ErrorHandler
	public static DOMErrorHandler getErrorHandler() {
		return new DOMErrorHandler() {
			public boolean handleError(DOMError error) {
				short severity = error.getSeverity();
				if (severity == error.SEVERITY_ERROR) {
					System.out.println("[dom3-error]: " + error.getMessage());
				}
				if (severity == error.SEVERITY_WARNING) {
					System.out.println("[dom3-warning]: " + error.getMessage());
				}
				if (severity == error.SEVERITY_FATAL_ERROR) {
					System.out.println("[dom3-fatal-error]: "
							+ error.getMessage());
				}
				return true;
			}
		};
	}

}
