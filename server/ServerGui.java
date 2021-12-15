
package server;
 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
 
public class ServerGui extends JFrame implements ActionListener {
 
    private static final long serialVersionUID = 1L;
    private JTextArea jta = new JTextArea(40, 25);
    private JTextField jtf = new JTextField(25);
    private ServerBack sb = new ServerBack();
    private JButton transBtn=new JButton("전송");
    private Font ft = new Font("Gowun Batang Regular", Font.BOLD, 12);
    private LineBorder border=new LineBorder(Color.white,1,true);
    private LineBorder stopBd=new LineBorder(Color.lightGray,2,true);
    private LineBorder border2=new LineBorder(Color.decode("#39796b"),3,true);
   
 
    public ServerGui() throws IOException {
    	
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	setVisible(true);
    	setResizable(false);
    	setSize(405,619);
    	setTitle("서버");
 
    	setLayout(null);
        jta.setBounds(1,1,390,550);
        jta.setBackground(Color.decode("#81c784"));
        jta.setFont(ft);
        jta.setBorder(border2);
       
        jtf.setBounds(1,552,330,30);
        jtf.setFont(ft);
        jtf.addActionListener(this);
        transBtn.setBounds(330, 551, 60, 30);
        transBtn.addActionListener(this);
        transBtn.setBackground(Color.decode("#fbc02d"));
        transBtn.setFont(ft);
        transBtn.setBorder(border);
       
        
        add(jta);
        add(jtf);
        add(transBtn);
      
 
        sb.setGui(this);
        sb.setting();
    }
 
    public static void main(String[] args) throws IOException {
        new ServerGui();
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
    	
        String msg = "서버 : " + jtf.getText() + "\n";
        System.out.print(msg);
        sb.sendMessage(msg);
        jtf.setText("");
    	
    	
    }
 
    public void appendMsg(String msg) {
        jta.append(msg);
    }
 
}

