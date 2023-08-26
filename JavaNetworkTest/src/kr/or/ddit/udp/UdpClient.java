package kr.or.ddit.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UdpClient {
	
	private DatagramSocket ds;
	private DatagramPacket dp;
	
	private byte[] msg;
	
	public UdpClient() {
		try {
			msg = new byte[100];
			
			ds = new DatagramSocket();
			
		}catch(SocketException ex) {
			ex.printStackTrace();
		}
	}
	
	//시작메서드
	public void start() {
		try {
			
			InetAddress serverAddr = InetAddress.getByName("192.168.145.41");
			dp = new DatagramPacket(msg, 1, serverAddr, 8888);
			ds.send(dp);
			
			dp = new DatagramPacket(msg,  msg.length);
			ds.receive(dp);
			
			System.out.println("현재 서버 시간 => " + new String (dp.getData()));
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		new UdpClient().start();
	}
}
