package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

import application.view.MainViewController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {

	private MyCustomProps props;
	private final String propertiesFilePath = System.getProperty("user.home") + "/.m120_uifz726/"
			+ getClass().getSimpleName();

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("view/MainView.fxml"));
			Parent root = loader.load();

			// hole MainViewController - Instanz anhand fx:controlller-Attribut
			MainViewController mainController = loader.getController();
			// übergebe aktuelle Stage an MainViewController
			mainController.setPrimaryStage(primaryStage);
			props = new MyCustomProps(propertiesFilePath);
			mainController.setProperties(props);

			try {
				props.loadProperties();
				double width = Double.parseDouble(props.getProperty("stageWidth", "600"));
				double height = Double.parseDouble(props.getProperty("stageHeight", "400"));
				double xPos = Double.parseDouble(props.getProperty("xPos", "200"));
				double yPos = Double.parseDouble(props.getProperty("yPos", "200"));
				primaryStage.setX(xPos);
				primaryStage.setY(yPos);
				primaryStage.setWidth(width);
				primaryStage.setHeight(height);
				System.out.println("done reading app properties");

			} catch (Exception e) {
				System.out.println("couln't read properties"+e.getMessage());
			}
			Scene scene = new Scene(root);
			primaryStage.setTitle("JavaFX Hibernation based Administration tool");
			primaryStage.setScene(scene);
			primaryStage.show();

			// adding proper close and cleanup
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent event) {
					System.out.println("closing down from Main()");
					mainController.closeAction();
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
