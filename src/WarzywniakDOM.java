
import org.w3c.dom.*;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;



public class WarzywniakDOM {

	public void Print() {
		try {
			Document d = DOM.getDocument("warzywniak.xml");
			NodeList nl = d.getElementsByTagName("produkt");
			for(int i=0; i<nl.getLength(); i++)
			{
				Element epracownik = (Element)nl.item(i);
				System.out.println("nazwa: " + epracownik.getElementsByTagName("nazwa").item(0).getTextContent());
				System.out.println("cena: " + epracownik.getElementsByTagName("cena").item(0).getTextContent());
				System.out.println("ilosc w opakowaniu: " + epracownik.getElementsByTagName("waga").item(0).getTextContent());
				System.out.println(); 
			}
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
			System.out.println("blad");
		}
	}
}