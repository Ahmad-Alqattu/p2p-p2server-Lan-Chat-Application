package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class MainController implements Initializable {
	static String name;
	private String destip;
	static int sourcePort;
	private int destPort;
	private static Hashtable<String, InetSocketAddress> userList;
	
    @FXML
    private Label userName;
	@FXML
	private Pane ChatPane;

	public Pane getChatPane() {
		return ChatPane;
	}
    @FXML
    private AnchorPane right;
	public void setChatPane(Pane chatPane) {
		ChatPane = chatPane;
	}

	@FXML
	private TextArea TextArea;

	//@FXML   	
	//private  ComboBox<String> clientList;

	private static ComboBox<String> clientList= new ComboBox<String>();
	
	ChatClient ch;

	@FXML
	void clear(ActionEvent event) {
		ChatClient.px=3;
		ChatPane.getChildren().clear();
		ChatPane.setMinHeight(758);
	}

	@FXML
	void send(ActionEvent event) throws IOException {
//System.out.println("ad "+name);

		InetSocketAddress address = new InetSocketAddress(destip, destPort);
		String msg = name + " >  " + TextArea.getText();
		Label l = new Label("you >  " + TextArea.getText());
		l.setStyle( 
				"-fx-background-color: #417AF5; -fx-text-fill: black;-fx-font-size: 18px; -fx-background-radius:13;-fx-border-width:.5; -fx-border-radius:15;-fx-border-color:#00ADB5");
		l.setLayoutY(ChatClient.px);
		l.setPadding(new Insets(1, 8, 1, 8));
		ChatPane.getChildren().add(l);



		l.setLayoutX(ChatPane.getWidth()*.85 - (msg.length() * 6));
		if (ChatPane.getHeight()<(ChatClient.px+20))
			ChatPane.setPrefHeight(ChatClient.px+50);
		ChatClient.px += 40;
		ch.SendTo(address, msg);
		System.out.println(msg);
		TextArea.clear();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		clientList.setLayoutX(13);
		clientList.setLayoutY(228);
		clientList.setPromptText(" select one");
		clientList.setPrefWidth(150);
		clientList.setPrefHeight(35);
		clientList.setVisibleRowCount(12);
		right.getChildren().add(clientList);
		clientList.setOnAction(e ->{

			destip =userList.get(clientList.getValue()).getAddress().getHostAddress() ;
			destPort=userList.get(clientList.getValue().toString()).getPort() ;

		});
	}

	public void initial() throws InterruptedException {

		userName.setText(name);
		ch = new ChatClient();
		ch.SetObject(this.ChatPane);
		try {
			ch.bind(sourcePort);
			
			Timer timer = new Timer();
			timer.schedule(new ClientServer(ch.getObject()), 0, 5000);

		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ch.start();
//	
	}
    @FXML
    void clientList(ActionEvent event) {
		destip =userList.get(clientList.getValue().toString()).getAddress().toString() ;
		destPort=userList.get(clientList.getValue().toString()).getPort() ;

    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSourcePort() {
		return sourcePort;
	}

	public void setSourcePort(int sourcePort) {
		this.sourcePort = sourcePort;
	}

	public Hashtable<String, InetSocketAddress> getUserList() {
		return userList;
	}

	public static void setUserList(Hashtable<String, InetSocketAddress> userList) {
		MainController.userList =new Hashtable<String, InetSocketAddress>(userList);

	}

	public static void  update() {
		clientList.getItems().clear();
		clientList.getSelectionModel().clearSelection();
		   for (String name : userList.keySet()) {     
			   clientList.getItems().add(name);
		   }
	}
	
}
