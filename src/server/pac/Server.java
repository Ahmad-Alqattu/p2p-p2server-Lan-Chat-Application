package server.pac;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

public class Server {

	private DatagramSocket dgs;
	 
	private Hashtable<String, userDat> userlest = new Hashtable<String, userDat>();
	DatagramPacket dgp;
	// private String name;

	public Server(DatagramSocket dgs) {
		super();
		this.dgs = dgs;

	}

	public void reseiveThenSend() throws IOException {
		while (true) {
			byte buffer[] = new byte[512];
			DatagramPacket dgp = new DatagramPacket(buffer, buffer.length);
			dgs.receive(dgp);
			String user = new String(buffer,0, dgp.getLength());

			if (userlest.containsKey(user)) {
				userlest.get(user).setD(LocalDateTime.now());

			} else {
				userDat userData = new userDat(dgp.getAddress(), dgp.getPort(), user, LocalDateTime.now());
				userlest.put(user, userData);
				
				for (userDat data : userlest.values()) {
					int port = data.getPort();
					InetAddress Address = data.getS();

					for (String name : userlest.keySet()) {
						buffer = ("u/:" + name + ":" + userlest.get(name).getS() + ":" + userlest.get(name).getPort())
								.getBytes();
						dgp = new DatagramPacket(buffer, buffer.length, Address, port);
						dgs.send(dgp);
					}

					buffer = ("f/:").getBytes();
					dgp = new DatagramPacket(buffer, buffer.length, Address, port);
					dgs.send(dgp);

				}

			}
			LocalDateTime l = LocalDateTime.now();
			l = l.minusSeconds(5);
			for (String nams : userlest.keySet()) {
				if (userlest.get(nams).getD().isBefore(l)) {

					userlest.remove(nams);
					for (userDat data : userlest.values()) {
						int port = data.getPort();
						InetAddress Address = data.getS();

						for (String name : userlest.keySet()) {

							buffer = ("u/:" + name + ":" + userlest.get(name).getS() + ":"
									+ userlest.get(name).getPort()).getBytes();
							dgp = new DatagramPacket(buffer, buffer.length, Address, port);
							dgs.send(dgp);

						}

						buffer = ("f/:").getBytes();
						dgp = new DatagramPacket(buffer, buffer.length, Address, port);
						dgs.send(dgp);
					}
					break;

				}

			}
		}
	}

	public static void main(String[] args) throws IOException {
		DatagramSocket datagramSocket = new DatagramSocket(1234);
		Server server = new Server(datagramSocket);
		server.reseiveThenSend();

	}

}
