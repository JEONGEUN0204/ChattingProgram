package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class ClientBack {
	
	private Socket socket;
	private ClientGui gui;
	private String msg;
	private String name;
	private DataOutputStream outstream;
	private DataInputStream instream;
	
	public final void setGui(ClientGui gui) {
		this.gui=gui;
	}
	
	public void connect() {
		try {
			socket=new Socket("192.168.35.171",7777);//192.168.35.171 221.139.111.148
			System.out.println("���� �����.");
			
			outstream=new DataOutputStream(socket.getOutputStream());
			instream=new DataInputStream(socket.getInputStream());

			outstream.writeUTF(name);
			System.out.println("Ŭ���̾�Ʈ:�޼��� ���ۿϷ�");
			while(instream!=null) {
				
				msg=instream.readUTF();
					gui.appendMsg(msg);
							
			}
		}catch(IOException e) {
			if(!socket.isClosed()) {
				stopConnect();
				System.out.println("���� ���� ����");
				
			}
		}
	}

	public static void main(String[] args)  {
		ClientBack cb=new ClientBack();
		cb.connect();
	}
	
	
	public void sendMessage(String msg2) {
		try {
			outstream.writeUTF(msg2);
		}catch(IOException e) {
			
			System.out.println("�޼����� ������ �� �����ϴ�.");
		}
	}
	
	public void setName(String name) {
		this.name=name;
	}

	public void stopConnect() {
		try {
			if(socket !=null&&!socket.isClosed()) {
				socket.close();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
