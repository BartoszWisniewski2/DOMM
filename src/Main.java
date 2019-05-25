
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public class Main extends Application {
	@Override
	public void start(Stage stage) {
		WarzywniakDOM wd = new WarzywniakDOM();

		GridPane okno = new GridPane();

		// vboxy i scrollpane
		ScrollPane scroll0 = new ScrollPane();
		scroll0.setMinWidth(200);
		VBox box0 = new VBox();
		box0.setMinWidth(200);
		VBox box1 = new VBox();
		box1.setMinWidth(200);
		VBox box2 = new VBox();
		box2.setMinWidth(200);
		VBox box3 = new VBox();
		box3.setMinWidth(200);
		HBox hBox0 = new HBox();
		hBox0.setMinWidth(900);

		//
		Label scrollLabel = new Label("Lista produktów");

		// vbox1
		Label addLabel = new Label("Dodaj produkt");
		TextField addNazwa = new TextField();
		TextField addCena = new TextField();
		TextField addWaga = new TextField();
		ChoiceBox addRodzaj = new ChoiceBox();
		TextField addWaluta = new TextField();
		ChoiceBox addJednostka = new ChoiceBox();
		TextField addOpakowanie = new TextField();

		Button addZatwierdz = new Button("ZatwierdŸ");

		addNazwa.setPromptText("nazwa");
		addCena.setPromptText("cena");
		addWaga.setPromptText("waga");
		addRodzaj.getItems().addAll("owoc", "warzywo");
		addWaluta.setPromptText("waluta");
		addJednostka.getItems().addAll("kg", "gr", "szt");
		addOpakowanie.setPromptText("rodzaj opakowania");
		addLabel.setMinWidth(200);
		addRodzaj.setMinWidth(200);
		addNazwa.setMinWidth(200);
		addCena.setMinWidth(200);
		addWaluta.setMinWidth(200);
		addWaga.setMinWidth(200);
		addJednostka.setMinWidth(200);
		addOpakowanie.setMinWidth(200);
		addZatwierdz.setMinWidth(200);

		addZatwierdz.setOnAction(e -> {
			if (addRodzaj.getValue().equals("owoc")) {
				wd.Dodaj(addNazwa.getText(), addCena.getText(), addWaga.getText(), "ow", addWaluta.getText(),
						String.valueOf(addJednostka.getValue()), addOpakowanie.getText());
			} else {
				wd.Dodaj(addNazwa.getText(), addCena.getText(), addWaga.getText(), "wa", addWaluta.getText(),
						String.valueOf(addJednostka.getValue()), addOpakowanie.getText());
			}
			wd.Print(box0);
			
			
			addRodzaj.setValue(null);
			addNazwa.clear();
			addCena.clear();
			addWaluta.clear();
			addWaga.clear();
			addJednostka.setValue(null);
			addOpakowanie.clear();


		});

		// vbox2
		Label edLabel = new Label("Aktualizuj produkt");
		TextField edNazwaStara = new TextField();
		TextField edNazwa = new TextField();
		TextField edCena = new TextField();
		TextField edWaga = new TextField();
		ChoiceBox edRodzaj = new ChoiceBox();
		TextField edWaluta = new TextField();
		ChoiceBox edJednostka = new ChoiceBox();
		TextField edOpakowanie = new TextField();

		Button edZatwierdz = new Button("ZatwierdŸ");

		edNazwaStara.setPromptText("stara nazwa");
		edNazwa.setPromptText("nazwa");
		edCena.setPromptText("cena");
		edWaga.setPromptText("waga");
		edRodzaj.getItems().addAll("owoc", "warzywo");
		edWaluta.setPromptText("waluta");
		edJednostka.getItems().addAll("kg", "gr", "szt");
		edOpakowanie.setPromptText("rodzaj opakowania");
		edNazwaStara.setMinWidth(200);
		edLabel.setMinWidth(200);
		edRodzaj.setMinWidth(200);
		edNazwa.setMinWidth(200);
		edCena.setMinWidth(200);
		edWaluta.setMinWidth(200);
		edWaga.setMinWidth(200);
		edJednostka.setMinWidth(200);
		edOpakowanie.setMinWidth(200);
		edZatwierdz.setMinWidth(200);

		edZatwierdz.setOnAction(e -> {
			if (edRodzaj.getValue().equals("owoc")) {
				wd.Update(edNazwaStara.getText(), "ow", edNazwa.getText(), edCena.getText(), edWaluta.getText(),
						edWaga.getText(), String.valueOf(edJednostka.getValue()), edOpakowanie.getText());

			} else {
				wd.Update(edNazwaStara.getText(), "wa", edNazwa.getText(), edCena.getText(), edWaluta.getText(),
						edWaga.getText(), String.valueOf(edJednostka.getValue()), edOpakowanie.getText());

			}
			wd.Print(box0);
			edNazwaStara.clear();
			edRodzaj.setValue(null);
			edNazwa.clear();
			edCena.clear();
			edWaluta.clear();
			edWaga.clear();
			edJednostka.setValue(null);
			edOpakowanie.clear();

		});

		// vbox3
		Label usLabel = new Label("Usuñ produkt");
		TextField usNazwa = new TextField();

		Button usZatwierdz = new Button("ZatwierdŸ");

		usNazwa.setPromptText("nazwa");

		usNazwa.setMinWidth(200);
		usZatwierdz.setMinWidth(200);

		usZatwierdz.setOnAction(e -> {
				wd.Delete(usNazwa.getText());
			wd.Print(box0);
			usNazwa.clear();

		});

		scroll0.setContent(box0);
		box0.getChildren().addAll(scrollLabel);
		wd.Print(box0);

		// dodanie paneli 0-3 do okna
		okno.add(hBox0, 0, 0, 1, 4);
		hBox0.getChildren().addAll(scroll0, box1, box2, box3);
		hBox0.setSpacing(5);

		box1.getChildren().addAll(addLabel, addRodzaj, addNazwa, addCena, addWaluta, addWaga, addJednostka,
				addOpakowanie, addZatwierdz);
		box1.setSpacing(5);

		box2.getChildren().addAll(edLabel, edNazwaStara, edRodzaj, edNazwa, edCena, edWaluta, edWaga, edJednostka,
				edOpakowanie, edZatwierdz);
		box2.setSpacing(5);

		box3.getChildren().addAll(usLabel, usNazwa, usZatwierdz);
		box3.setSpacing(5);

		// wd.Dodaj("Kie³ki", "10", "10", "wo", "pln", "gr", "luzem");
		// wd.Delete("Kie³ki");
		wd.Update("Kie³ki", "wa", "Kie³eczki", "20", "eur", "4", "kg", "karton");

		Scene scena = new Scene(okno, 840, 600);
		stage.setScene(scena);
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
