package Scanner;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


/*
 * Help window
 */
class dialogclass extends JFrame {
	private JTextArea text = new JTextArea("CONTEXT:", 20, 50);
	private JDialog fh = new JDialog(this, "Help");
	private String s1 = "The tool is for scanning open ports of a given IP or hostname!";
	private String s2 = "It also support searching online hosts in the LAN!";
	private char ch = '\n';
	private String sb;

	public dialogclass() {
		fh.setLocation(350, 200);

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
			public void windowClosing(WindowEvent e) {
				fh.setVisible(false);
			}
		});
	}
}