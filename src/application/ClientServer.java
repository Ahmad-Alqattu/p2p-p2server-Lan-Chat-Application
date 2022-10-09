package application;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.control.Label;

public class ClientServer extends TimerTask {
	private byte[] buffer;
	private DatagramSocket datagramSocket;

	public ClientServer(DatagramSocket object) {
		datagramSocket = object;
	}

	public void run() {
		try {

			InetSocketAddress address = new InetSocketAddress("192.168.0.107", 1234);

			buffer = (MainController.name).getBytes();
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address);

			datagramSocket.send(packet);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
