package Scanner;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

class claimclass extends JFrame {
	private JTextArea text = new JTextArea("CONTEXT:", 20, 100);
	private JDialog fh = new JDialog(this, "Copyright Statment");
	private String s1 = "Program designed and implemented by Xiang Ji and Zhen Fan!";
	private String s2 = "Please feel free to use this program for learning purpose";
	private char ch = '\012';
	private String sb;

	public claimclass() {
		fh.setLocation(350, 300);

		text.setBackground(Color.darkGray);
		text.setForeground(Color.orange);
		text.setEditable(false);
		fh.add(text);
		s1 = s1.concat(Character.toString(ch));
		s1 = s1.concat(s2);
		sb = text.getText().concat(Character.toString(ch));
		sb = sb.concat(s1);
		text.setText(sb);
		fh.setSize(500, 100);
		fh.setVisible(true);
		fh.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)

			{
				fh.setVisible(false);
			}

		});
	}
}