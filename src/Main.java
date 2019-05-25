
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
		WarzywniakDOM wd = new WarzywniakDOM();
		wd.Print();
		//wd.Dodaj("Kie³ki", "10", "10");
		wd.Delete("Kie³ki");
	}

	public static void main(String[] args) {
		launch(args);
	}
}

