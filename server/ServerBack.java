package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;




public class ServerBack {
	    private ServerSocket serverSocket;
	    private Socket socket;
	    private ServerGui gui;
	    private String msg;
	 
	    
	    private Map<String, DataOutputStream> clientsMap = new HashMap<String, DataOutputStream>();
	 
	    public final void setGui(ServerGui gui) {
	        this.gui = gui;
	    }
	 
	    public void setting() throws IOException {
	        Collections.synchronizedMap(clientsMap); 
	        serverSocket = new ServerSocket(7777);
	        while (true) {
	            
	            System.out.println("서버 대기중...");
	            socket = serverSocket.accept();
	            System.out.println(socket.getInetAddress() + "에서 접속했습니다.");
	          
	            Receiver receiver = new Receiver(socket);
	            receiver.start();
	        }
	    }
	 
	    public static void main(String[] args) throws IOException {
	        ServerBack sb = new ServerBack();
	        sb.setting();
	    }
	 
	   
	    public void addClient(String name, DataOutputStream outstream) throws IOException {
	        sendMessage(name + "님이 접속하셨습니다.\n");
	        clientsMap.put(name, outstream);
	    }
	 
	    public void removeClient(String name) {
	        sendMessage(name + "님이 나가셨습니다.\n");
	        clientsMap.remove(name);
	    }
	 
	   
	    public void sendMessage(String msg) {
	        Iterator<String> it = clientsMap.keySet().iterator();
	        String key = "";
	        while (it.hasNext()) {
	            key =it.next();
	            try {
	                clientsMap.get(key).writeUTF(msg);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	 
	    
	    class Receiver extends Thread {
	        private DataInputStream instream;
	        private DataOutputStream outstream;
	        private String name;
	 
	        public Receiver(Socket socket) throws IOException {
	            outstream = new DataOutputStream(socket.getOutputStream());
	            instream = new DataInputStream(socket.getInputStream());
	            name = instream.readUTF();
	            addClient(name, outstream);
	        }
	 
	        public void run() {
	            try {
	                while (instream != null) {
	                    msg = instream.readUTF();
	                    sendMessage(msg); 
	                    gui.appendMsg(msg);
	                }
	            } catch (IOException e) {
	      
	                removeClient(name);
	            }
	        }
	    }
	    
	   
	    	
	    


}
