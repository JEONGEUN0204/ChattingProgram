package client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class ClientGui extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private ClientBack cb=new ClientBack();
	private JTextArea jta = new JTextArea(40, 25);
	private JTextArea jta2 = new JTextArea(40, 25);
	private JTextField jtf = new JTextField(25);
	private JButton transBtn=new JButton("전송");
	private JButton stopBtn=new JButton("채팅 종료");
	private Font ft = new Font("Gowun Batang Regular", Font.BOLD, 12);
	private LineBorder border=new LineBorder(Color.white,1,true);
	private MatteBorder borderLeft=new MatteBorder(3,3,3,0,Color.decode("#ffab91"));
	private MatteBorder borderRight=new MatteBorder(3,0,3,3,Color.decode("#ffab91"));
	private LineBorder stopBd=new LineBorder(Color.lightGray,2,true);
	public static String name;
	private boolean left=false;
	private boolean right=false;
	private int leftCount=0;
	private int rightCount=0;

	
    public ClientGui(){
    	
    	
    	setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    	setBounds(500,0,405,648);
    	setTitle("클라이언트");
    	setResizable(false);
    	setVisible(true);
       
    	setLayout(null);
        jta.setBounds(1,1,195,549);
        jta.setBackground(Color.decode("#ffddc1"));
        jta.setFont(ft);
        jta.setBorder(borderLeft);
        
        setLayout(null);
        jta2.setBounds(196,1,195,549);
        jta2.setBackground(Color.decode("#ffddc1"));
        jta2.setFont(ft);
        jta2.setBorder(borderRight);
    	alignRight();
      
       
        jtf.setBounds(1,552,330,30);
        jtf.setFont(ft);
        jtf.addActionListener(this);
        transBtn.setBounds(330, 551, 60, 30);
        transBtn.addActionListener(this);
        transBtn.setBackground(Color.decode("#fbc02d"));
        transBtn.setFont(ft);
        transBtn.setBorder(border);
        stopBtn.setBounds(1,582,389,29);
        stopBtn.addActionListener(this);
        stopBtn.setBackground(Color.white);
        stopBtn.setFont(ft);
        stopBtn.setBorder(stopBd);
        
        add(jta);
        
        add(jta2);
        add(jtf);
        add(transBtn);
        add(stopBtn);
               
        service();

    }
    
    public static void main(String[] args) {
    	
		new ClientGui();   	
    	
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==transBtn||e.getSource()==jtf) {
		String msg = name + " : " + jtf.getText() + "\n";
		cb.sendMessage(msg);
		jtf.setText("");
		}
		else if(e.getSource()==stopBtn) {
			
			cb.stopConnect();
			dispose();
			
		}
		

	}
	
	
	public void appendMsg(String msg) {
		StringTokenizer st= new StringTokenizer(msg," ");
		if(st.nextToken().equals(name)) {
			if(left) {
				for(int i=0;i<leftCount;i++) {
					jta2.append("\n");
				}
				jta2.append(msg);
			}
			else {
				jta2.append(msg);
			}
			right=true;
			rightCount++;
			leftCount=0;
	
		}
		else {
			if(right) {
				for(int i=0;i<rightCount;i++) {
					jta.append("\n");
				}
				jta.append(msg);
			}
			else {
				jta.append(msg);
			}
			left=true;
			rightCount=0;
			leftCount++;
		}
		
		
	}
	
	public void service()  {
		name=JOptionPane.showInputDialog(this,"닉네임을 입력하세요.","닉네임 설정",JOptionPane.INFORMATION_MESSAGE);
		if(name == null || name.length()==0){
			name="guest";
		}
		 cb.setGui(this);
	     cb.setName(name);
	     cb.connect();
	}
	
	public void alignRight() {
		jta2.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

	}
	


}
