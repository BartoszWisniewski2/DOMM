
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
	public void Dodaj(String nazwa1, String cena1, String waga1) {
		try {
			Document d = DOM.getDocument("warzywniak.xml");
			Element warzywniak = d.getDocumentElement();
			NodeList nl = warzywniak.getElementsByTagName("produkty");
			Element produkty = (Element)nl.item(0);
			//Create student tag
			Element produkt = d.createElement("produkt");
			//nazwa 
			Element nazwa = d.createElement("nazwa");
			nazwa.appendChild(d.createTextNode(nazwa1));
			produkt.appendChild(nazwa);
			//nazwisko
			Element cena = d.createElement("cena");
			cena.appendChild(d.createTextNode(cena1));
			produkt.appendChild(cena);
			//nrTelefonu
			Element waga = d.createElement("waga");
			waga.appendChild(d.createTextNode(waga1));
			produkt.appendChild(waga);
			
			produkty.appendChild(produkt);
			//Write to file
			DOM.saveXMLContent(d, "warzywniak.xml");
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void Delete(String nazwa) {
		try {
			Document d = DOM.getDocument("warzywniak.xml");
			NodeList nl = d.getElementsByTagName("produkt");
			for(int i=0; i<nl.getLength(); i++)
			{
				Element eprodukt = (Element)nl.item(i);
				if(eprodukt.getElementsByTagName("nazwa").item(0)
						.getTextContent().contentEquals(nazwa))
				{
					eprodukt.getParentNode().removeChild(eprodukt);
				}
			}
			//Write to file
			DOM.saveXMLContent(d, "warzywniak.xml");
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}