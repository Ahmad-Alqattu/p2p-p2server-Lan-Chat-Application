package application;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Timer;
import java.util.TimerTask;


public class ChatClient implements Runnable{

	private DatagramSocket DatagramSocket;
	private boolean running;
	Pane ChatPane;
	public static int px = 3;
	private Hashtable<String, InetSocketAddress> userList ;

	public void bind(int port) throws SocketException {
		System.out.println("bind at "+port);
		DatagramSocket = new DatagramSocket(port);
	}

	public void start() throws InterruptedException {
		Thread thread = new Thread(this);

		thread.start();
	}

	public void stop() {
		running = false;
		DatagramSocket.close();

	}

	public void SendTo(InetSocketAddress address, String msg) throws IOException {
		byte[] buffer = msg.getBytes();
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

		packet.setSocketAddress(address);
		DatagramSocket.send(packet);

	}

	public void SetObject(Pane ChatPane) {
		this.ChatPane = ChatPane;

	}
	
	public DatagramSocket getObject() {
		return this.DatagramSocket;

	}

	@Override
	public void run() {

		byte[] buffer = new byte[1024];
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		running = true;
		 userList=new Hashtable<String, InetSocketAddress>();
		while (running) {
			try {
				DatagramSocket.receive(packet);
				String msg = new String(buffer, 0, packet.getLength());
				String str[] = msg.split(":");
				if (str[0].equals("u/")) {
					InetSocketAddress Address = new InetSocketAddress(str[2].replaceFirst("/",""), Integer.parseInt(str[3]));
					userList.put(str[1], Address);
				} else if (str[0].equals("f/")) {
					Platform.runLater(() -> {
						MainController.setUserList(userList);
						MainController.update();
						userList.clear();
					});			
				} else {
					Label l = new Label(msg);
					l.setStyle( 
							"-fx-background-color: #90DEF5; -fx-text-fill: black;-fx-font-size: 18px; -fx-background-radius:13;-fx-border-width:.5; -fx-border-radius:15;-fx-border-color:#00ADB5");
					l.setLayoutY(ChatClient.px);
					l.setPadding(new Insets(1, 8, 1, 8));
					if (ChatPane.getHeight()<(px+20))
						ChatPane.setPrefHeight(px+50);
					px += 40;
					Platform.runLater(() -> {
						ChatPane.getChildren().add(l);
					});
				}

	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
		}
	}

}