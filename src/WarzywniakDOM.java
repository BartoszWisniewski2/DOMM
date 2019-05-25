
import org.w3c.dom.*;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class WarzywniakDOM {

	public void Print() {
		try {
			Document d = DOM.getDocument("warzywniak.xml");
			NodeList nl = d.getElementsByTagName("produkt");
			for (int i = 0; i < nl.getLength(); i++) {
				Element epracownik = (Element) nl.item(i);
				System.out.println(epracownik.getAttributes().item(0).getNodeValue());
				System.out.println("nazwa: " + epracownik.getElementsByTagName("nazwa").item(0).getTextContent());
				System.out.println("cena: " + epracownik.getElementsByTagName("cena").item(0).getTextContent() + " "
						+ epracownik.getElementsByTagName("cena").item(0).getAttributes().item(0).getNodeValue());
				System.out.println("ilosc w opakowaniu: "
						+ epracownik.getElementsByTagName("waga").item(0).getTextContent() + " "
						+ epracownik.getElementsByTagName("waga").item(0).getAttributes().item(0).getNodeValue() + " "
						+ epracownik.getElementsByTagName("waga").item(0).getAttributes().item(1).getNodeValue());

				System.out.println();
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			System.out.println("blad");
		}
	}

	public void Dodaj(String nazwa1, String cena1, String waga1, String rodzaj1, String waluta1, String jednostka1,
			String opakowanie1) {
		try {
			Document d = DOM.getDocument("warzywniak.xml");
			Element warzywniak = d.getDocumentElement();
			NodeList nl = warzywniak.getElementsByTagName("produkty");
			Element produkty = (Element) nl.item(0);
			// Create student tag
			Element produkt = d.createElement("produkt");
			produkt.setAttribute("rodzaj", rodzaj1);
			// nazwa
			Element nazwa = d.createElement("nazwa");
			nazwa.appendChild(d.createTextNode(nazwa1));
			produkt.appendChild(nazwa);
			// nazwisko
			Element cena = d.createElement("cena");
			cena.appendChild(d.createTextNode(cena1));
			cena.setAttribute("waluta", waluta1);
			produkt.appendChild(cena);
			// nrTelefonu
			Element waga = d.createElement("waga");
			waga.appendChild(d.createTextNode(waga1));
			waga.setAttribute("jednostka", jednostka1);
			waga.setAttribute("opakowanie", opakowanie1);
			produkt.appendChild(waga);

			produkty.appendChild(produkt);
			// Write to file
			DOM.saveXMLContent(d, "warzywniak.xml");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void Delete(String nazwa) {
		try {
			Document d = DOM.getDocument("warzywniak.xml");
			NodeList nl = d.getElementsByTagName("produkt");
			for (int i = 0; i < nl.getLength(); i++) {
				Element eprodukt = (Element) nl.item(i);
				if (eprodukt.getElementsByTagName("nazwa").item(0).getTextContent().contentEquals(nazwa)) {
					eprodukt.getParentNode().removeChild(eprodukt);
				}
			}
			// Write to file
			DOM.saveXMLContent(d, "warzywniak.xml");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	public void Update(String nazwaStara, String rodzaj1, String nazwa1, String cena1, String waluta1, String waga1, String jednostka1, String opakowanie1) {
		try {
			Document d = DOM.getDocument("warzywniak.xml");
			NodeList nl = d.getElementsByTagName("produkt");
			for(int i=0; i<nl.getLength(); i++)
			{
				Element epracownik = (Element) nl.item(i);
				if(epracownik.getElementsByTagName("nazwa").item(0)
						.getTextContent().contentEquals(nazwaStara))
				{
						epracownik.getAttributes().item(0).setNodeValue(rodzaj1);
						epracownik.getElementsByTagName("nazwa").item(0).setTextContent(nazwa1);
						epracownik.getElementsByTagName("cena").item(0).setTextContent(cena1);
						epracownik.getElementsByTagName("cena").item(0).getAttributes().item(0).setNodeValue(waluta1);
						epracownik.getElementsByTagName("waga").item(0).setTextContent(waga1);
						epracownik.getElementsByTagName("waga").item(0).getAttributes().item(0).setNodeValue(jednostka1); 
						epracownik.getElementsByTagName("waga").item(0).getAttributes().item(1).setNodeValue(opakowanie1);
				}
			}
			//Write to file
			DOM.saveXMLContent(d, "warzywniak.xml");
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}