package Scanner;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;


class dialogclass extends JFrame
{private JTextArea text=new JTextArea("CONTEXT:",20,30);
 private JDialog fh=new JDialog (this,"Help");
 private String s1="The software is for scanning open ports of a given IP or hostname!";
 private String s2="It also support searching online hosts in the LAN!";
private char ch='\012';
 private String sb;
 public dialogclass()
	{   fh.setSize(400,200);
		fh.setLocation(350,300);
		
		text.setBackground(Color.gray);
	 text.setForeground(Color.yellow);
	 text.setEditable(false);
	 fh.add(text);
	 s1=s1.concat(Character.toString(ch));
	 s1=s1.concat(s2);
	 sb=text.getText().concat(Character.toString(ch));
	 sb=sb.concat(s1);
	 text.setText(sb);
	 fh.setSize(350,100);
	 fh.setVisible(true);
		fh.addWindowListener 
(new WindowAdapter () 
{ 
public void windowClosing (WindowEvent e) 
{ 
fh.setVisible(false);
} 
}); 
}
}