package Scanner;
import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class Ipchange  implements ActionListener
{  private JFrame  Ipc=new JFrame("Hostname Resolve");
   private JButton Ipb=new JButton("analyze");
   private JLabel  Ipl=new JLabel("Hostname:");
   private JTextField Ipt=new JTextField("www.sina.com",20);
   private JLabel  Ipl1=new JLabel("IP:");
   private JTextField Ipt1=new JTextField(28);
   
   private JPanel p1=new JPanel();
   private JPanel p2=new JPanel();
   private JPanel p3=new JPanel();
   private String address;
  
	public void actionPerformed (ActionEvent a) 
	{
		InetAddress ipneed=null;
		Ipc.setLayout(new GridLayout(3,1));
	//Ipc.setSize(500,200);

    Ipc.setSize(500,125);
	Ipc.setLocation(350,300);
	//Ipb.add(Ipb);
    Ipc.add(p1);
    Ipc.add(p2);
    Ipc.add(p3);
    p1.setLayout(new FlowLayout(FlowLayout.LEFT));
    p1.add(Ipl);
    p1.add(Ipt);
    p2.setLayout(new FlowLayout(FlowLayout.LEFT));
    p2.add(Ipl1);
    p2.add(Ipt1);
    p3.setLayout(new FlowLayout(FlowLayout.CENTER));
    p3.add(Ipb);
    Ipb.addActionListener(this);
    Ipc.setVisible(true);
   			Ipc.addWindowListener 
(new WindowAdapter () 
{ 
public void windowClosing (WindowEvent e) 

{ 
Ipc.setVisible(false);
} 

}); 

 	if(a.getActionCommand().equals("analyze"))	
 	
 	{  try{
 	
 		ipneed=InetAddress.getByName(Ipt.getText().trim());
 		//System.out.println("stupid");
 	
        //System.out.println(ipneed);
 		String address=ipneed.getHostAddress();
 		Ipt1.setText(address);
 		Ipt1.validate();
 		}
 		catch(UnknownHostException e){
 			JOptionPane.showMessageDialog(Ipc, "error");
 		}
 		
 		}
 	
 	
 	

 
		}
	
	}