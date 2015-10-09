package Scanner;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

class ThreadScan implements ActionListener {
	
	// implements the ActionListener interface
	// Define all the GUI objects
	// Port scan GUI
	public static JFrame main = new JFrame("TCP Port Host Scanner");
	
	//menu
	public JMenuBar mb = new JMenuBar(); // menu bar above
	public JMenu m1 = new JMenu("Start");
	public JMenu m3 = new JMenu("Socket");
	public JMenu m2 = new JMenu("About");
	public JMenuItem i1 = new JMenuItem("Scan open port");
	public JMenuItem i2 = new JMenuItem("Scan online host");
	public JMenuItem ipch = new JMenuItem("Hostname Resolve");
	public JMenuItem ipscan = new JMenuItem("Get URL content length");
	public JMenuItem com = new JMenuItem("Socket Client");
	public JMenuItem com2 = new JMenuItem("Socket Server");
	public JMenuItem i3 = new JMenuItem("Exit");
	public JMenuItem i4 = new JMenuItem("Help");
	public JMenuItem i5 = new JMenuItem("Statement");
	
	public static JTextArea Result = new JTextArea("", 8, 50);
	public static JTextField hostname = new JTextField("localhost", 10);
	public static JTextField minPort = new JTextField("0", 3);
	public static JTextField maxPort = new JTextField("1000", 3);
	public static JTextField maxThread = new JTextField("100", 3);
	public static JDialog DLGError = new JDialog(main, "Error!");
	public static Label DLGINFO = new Label("");
	public static Label status = new Label("Scanning Status: Not Started");
	
	public static JPanel mPanel = new JPanel(); // panel for port scan

	// IPSCAN, define all the labels and buttons
	public static JTextArea Result1 = new JTextArea("", 8, 50);
	public static JLabel staipl = new JLabel("IP start at:");
	public static JTextField startip = new JTextField("192.168.1.1", 10);
	public static JLabel endipl = new JLabel("IP end at:");
	public static JTextField endip = new JTextField("192.168.1.254", 10);
	public static JTextField ipthread = new JTextField("20", 3);
	public static JLabel iprst = new JLabel("Host scanning result:                                            ");
	public static JButton strbutton = new JButton("Start");
	public static JButton canbutton = new JButton("Cancel");
	
	public static JPanel ippanel = new JPanel(); // panel for IP scan
	
	public static JLabel TNUM1 = new JLabel("Threads Number:");
	public static JLabel status1 = new JLabel("Scanning status: not started");
	
	// constructor for the GUI and listener
	public ThreadScan() {
		main.setSize(600, 600);
		main.setLocation(400, 250);
		main.setResizable(false);
		DLGError.setSize(400, 200);
		DLGError.setLocation(400, 250);

		JScrollPane sp = new JScrollPane(Result1); // result scroll plane for IP, since long result

		main.setJMenuBar(mb); //put the mb into the main frame
		
		// Set border for all the menu component
		mb.setBorder(BorderFactory.createRaisedBevelBorder());
		m1.setBorder(BorderFactory.createRaisedBevelBorder());
		m2.setBorder(BorderFactory.createRaisedBevelBorder());
		m3.setBorder(BorderFactory.createRaisedBevelBorder());
		
		mb.add(m1);
		mb.add(m3);
		mb.add(m2);
		
		m1.add(i1);
		m1.addSeparator(); // separate line
		// i1.addActionListener(this);
		m1.add(i2);
		m1.addSeparator();
		// i2.addActionListener(this);//WAITING FOR CHANGE
		m1.add(ipch);
		m1.addSeparator();
		m1.add(ipscan);
		m1.addSeparator();
		m1.add(i3);
		i3.addActionListener(this);
		m2.add(i4);
		i4.addActionListener(this);
		m2.addSeparator();
		m2.add(i5);
		i5.addActionListener(this);
		
		m3.add(com);
		m3.addSeparator();
		m3.add(com2);

		JLabel H = new JLabel("IP address or hostname");
		JLabel P1 = new JLabel("Port range:");
		JLabel P2 = new JLabel("~");
		JLabel TNUM = new JLabel("Threads number:");
		JLabel RST = new JLabel("Port scanning result:                                       ");

		Result.setLineWrap(true);  // lien an wrap
		Result.setEditable(false); // can not edit result
		Result1.setLineWrap(true);
		Result1.setEditable(false);
		JButton OK = new JButton("Start");
		JButton Submit = new JButton("Start");
		JButton Cancel = new JButton("Cancel");
		Container dPanel = DLGError.getContentPane();

		main.setLayout(new GridLayout(2, 1));
		main.add(mPanel);
		main.add(ippanel);
		mPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		dPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		ippanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		mPanel.add(H);
		mPanel.add(hostname);
		mPanel.add(P1);
		mPanel.add(minPort);
		mPanel.add(P2);
		mPanel.add(maxPort);
		mPanel.add(TNUM);
		mPanel.add(maxThread);
		mPanel.add(RST);

		// Result.setBackground(Color.white);
		// Result.setForeground(Color.black);
		mPanel.add(Result);
		mPanel.add(Submit);
		mPanel.add(Cancel);
		mPanel.add(status);
		// mPanel.add(con);
		//
		// mPanel.add(ippanel);
		// ippanel.add(mes1);
		Result1.setBackground(Color.white);
		Result1.setForeground(Color.black);
		ippanel.add(staipl);
		ippanel.add(startip);
		ippanel.add(endipl);
		ippanel.add(endip);
		ippanel.add(TNUM1);
		ippanel.add(ipthread);
		ippanel.add(iprst);
		ippanel.add(sp);
		// Result1.add(sp);

		ippanel.add(strbutton);
		ippanel.add(canbutton);
		ippanel.add(status1);
		
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dPanel.add(DLGINFO);
		dPanel.add(OK);
		main.setVisible(true); // show the GUI
		
		// specify all the ActionListener
		Submit.addActionListener(new SubmitAction()); // listen and execute the submit action
		i1.addActionListener(new SubmitAction()); // equals to submit port scan action
		i2.addActionListener(new ipAction()); // IP scan action
		strbutton.addActionListener(new ipAction());
		Cancel.addActionListener(new CancleAction());
		canbutton.addActionListener(new CancleAction());
		OK.addActionListener(new OKAction());
		ipch.addActionListener(new Ipchange()); // DNS service
		ipscan.addActionListener(new InternetSizeFrame()); // Size Service
		
		// socket
		com.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SCanner.socketflag = true;
			}

		});
		
		com2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SCanner.serverflag = true;
			}

		});
	}
	
	// the ThreadScan should implement the ActionListener
	public void actionPerformed(ActionEvent a) {
		if (a.getActionCommand().equals("Exit")) {
			main.dispose();
		}

		if (a.getActionCommand().equals("Help")) {
			new dialogclass();
		}

		if (a.getActionCommand().equals("Statement")) {
			new claimclass();
		}
	}
}