import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OneButton extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		Scene scene = new Scene(makeVBox());
		stage.setScene(scene);
		stage.show();
	}

	private int i;

	private VBox makeVBox() {
		Label label = new Label("Never Pressed");
		Button button = new Button("Press me!");
		button.setOnAction(event -> label
				.setText("Pressed " + (++i) + " times"));
		VBox vbox = new VBox(20, button, label);
		vbox.setPrefWidth(200);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(20));
		return vbox;
	}
}