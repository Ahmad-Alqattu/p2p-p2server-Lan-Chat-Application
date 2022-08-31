package server.pac;

import java.net.InetAddress;
import java.net.SocketAddress;
import java.time.LocalDateTime;
import java.util.Date;

public class userDat {

	private  InetAddress s;
	private int port;
	private String id;
	private LocalDateTime d;
	public userDat(InetAddress s, int port, String id, LocalDateTime d) {
		super();
		this.s = s;
		this.port = port;
		this.id = id;
		this.d = d;
	}
	public InetAddress getS() {
		return s;
	}
	public void setS(InetAddress s) {
		this.s = s;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public LocalDateTime getD() {
		return d;
	}
	public void setD(LocalDateTime d) {
		this.d = d;
	}
	
	

	
	
}
