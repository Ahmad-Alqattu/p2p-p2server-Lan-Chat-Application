package application;

import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.*;

public class LoginWindow extends Application {
	private static Stage stg;

	@FXML
	private Button Go;

	@FXML
	private TextField Name;
    @FXML
    private Button def;

    @FXML
    private TextField port;

	@FXML
	void Go(ActionEvent event) {
		doLogin();
	}
	@FXML
	void def(ActionEvent event) {
		port.setText("9000");
	}

	private void doLogin() {
		String name = Name.getText();
		try {
		int port=Integer.parseInt( this.port.getText());
		// String password = passwordField.getText();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("mainWindow.fxml"));
		Parent root = null;
		root = loader.load();
		Scene scene = new Scene(root);
		getStg().setScene(scene);
		getStg().show();
		getStg().setOnCloseRequest((EventHandler<WindowEvent>) new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent event) {
		        try {
					stop();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		});
		((MainController)loader.getController()).setName(name);
		((MainController)loader.getController()).setSourcePort(port);

		((MainController)loader.getController()).initial();
		//changeScene("mainWindow.fxml");
	    changeSize(755, 680);
		} catch (Exception e1) {
			Alert a = new Alert(null);
			a.setAlertType(AlertType.ERROR);
			a.setContentText("Invalid login/password.");
			a.show();		
 		}
		// show error message

	}


	public void start(Stage primaryStage) {
		try {



			setStg(primaryStage);
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("LogIn.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);

			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void changeScene(String fxml) throws IOException {
		Parent pane = FXMLLoader.load(getClass().getResource(fxml));
		getStg().getScene().setRoot(pane);
	}

	public void changeSize(int w, int h) throws IOException {
		getStg().setWidth(w);
		getStg().setHeight(h);
	}

public static void main(String[] args) {
	launch(args);
}
public static Stage getStg() {
	return stg;
}
public static void setStg(Stage stg) {
	LoginWindow.stg = stg;
}


}