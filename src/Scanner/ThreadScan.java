package Scanner;
import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

 class ThreadScan implements ActionListener
{
public static JFrame main=new JFrame("TCP Port Host Scanner");
public static JTextArea Result=new JTextArea("",5,53);
public static JTextField hostname=new JTextField("localhost",7);
public static JTextField minPort=new JTextField("0",3);
public  static JTextField maxPort=new JTextField("1000",3);
public static  JTextField maxThread=new JTextField("100",3);
public  static  JDialog DLGError=new JDialog(main,"Error!");
public static  Label DLGINFO=new Label("");
public static Label status=new Label("Scanning Status: Not Started");
 public  JMenuBar mb=new JMenuBar();//
 public  JMenu m1=new JMenu("Start");
 public  JMenu m2=new JMenu("About");
 public JMenu m3=new JMenu("Socket");
 public JMenuItem i1=new JMenuItem("Scan open port");
 public  JMenuItem i2=new JMenuItem("Scan online host");
 public JMenuItem  ipch=new JMenuItem("Hostname Resolve");
 public JMenuItem ipscan=new JMenuItem("网络资源大小获取");
 public JMenuItem com=new JMenuItem("Socket Client");
 public JMenuItem com2=new JMenuItem("Socket Server");
 public  JMenuItem i3=new JMenuItem("Exit");
 public JMenuItem i4=new JMenuItem("Help");
 public JMenuItem i5=new JMenuItem("Statement");
 //
 public static JPanel mPanel=new JPanel();


//IPSCAN 
public static JTextArea Result1=new JTextArea("",5,53);
public static JLabel staipl=new JLabel("IP start at:");
public static JTextField startip=new JTextField("192.168.1.1",10);
public static JLabel endipl=new JLabel("IP end at:");
public static JTextField endip=new JTextField("192.168.1.254",10);
public static JTextField ipthread=new JTextField("20",4);
public static JLabel  iprst=new JLabel("Host scanning result:                                            ");
public static JButton strbutton=new JButton("Start");
public static JButton canbutton=new JButton("Cancel");
public static JPanel ippanel =new JPanel();
public static JLabel TNUM1=new JLabel("Threads Number:");
public static JLabel status1=new JLabel("Scanning status: not started");
//public static JLabel mes1=new JLabel("下面是主机扫描界面:      ");
//


 public ThreadScan ()
{
main.setSize(550,450);
main.setLocation(300,300);
main.setResizable(false);
DLGError.setSize(300,100);
DLGError.setLocation(400,400);

JScrollPane sp =new JScrollPane(Result1);

main.setJMenuBar(mb);//

mb.setBorder(BorderFactory.createRaisedBevelBorder());
 
  m1.setBorder(BorderFactory.createRaisedBevelBorder());
  m2.setBorder(BorderFactory.createRaisedBevelBorder());
  m3.setBorder(BorderFactory.createRaisedBevelBorder());
mb.add(m1);
mb.add(m3);
mb.add(m2);
m1.add(i1);
m1.addSeparator(); 
//i1.addActionListener(this);
m1.add(i2);
m1.addSeparator();
//i2.addActionListener(this);//WAITING FOR CHANGE
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

JPanel mainPanel = new JPanel();
JLabel H=new JLabel("IP address or hostname");
JLabel P1=new JLabel("Port range:");
JLabel P2=new JLabel("~");
JLabel TNUM=new JLabel("Threads number:");
JLabel RST=new JLabel("Port scanning result:                                       ");
//JLabel con=new JLabel("                                                                                                              ");
Result.setLineWrap(true);
Result.setEditable(false);
Result1.setLineWrap(true);
Result1.setEditable(false);
JButton OK = new JButton("Start");
JButton Submit = new JButton("Start");
JButton Cancel = new JButton("Cancel");
//Container mPanel = main.getContentPane();
Container dPanel = DLGError.getContentPane();
//
main.setLayout(new GridLayout(2,1));
main.add(mPanel);
main.add(ippanel);
//mPanel.setBackground(Color.white);
//ippanel.setBackground(Color.white);
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

//Result.setBackground(Color.white);
//Result.setForeground(Color.black);
mPanel.add(Result);
mPanel.add(Submit);
mPanel.add(Cancel);
mPanel.add(status);
//mPanel.add(con);
//
//mPanel.add(ippanel);
//ippanel.add(mes1);
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
//Result1.add(sp);

ippanel.add(strbutton);
ippanel.add(canbutton);
ippanel.add(status1);
//
main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  dPanel.add(DLGINFO);
  dPanel.add(OK);
    main.setVisible(true);
    Submit.addActionListener(new SubmitAction());
    i1.addActionListener(new SubmitAction());
    i2.addActionListener(new ipAction());
    strbutton.addActionListener(new ipAction());
    Cancel.addActionListener(new CancleAction());
    canbutton.addActionListener(new CancleAction());
    OK.addActionListener(new OKAction());
    ipch.addActionListener(new Ipchange());
    ipscan.addActionListener(new InternetSizeFrame());
    com.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			SCanner.socketflag=true;
		}
    	
    });
    com2.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			SCanner.serverflag=true;
		}
    	
    });
   }
   
    public void actionPerformed(ActionEvent a)
   {
   	if(a.getActionCommand().equals("Exit"))	
   {main.dispose();
    }
    
    if(a.getActionCommand().equals("Help"))
    {
    	new dialogclass();}
    
   
    if(a.getActionCommand().equals("Statement"))
    {new claimclass(); }
  //  if(a.getSource()==com);
 //   {SCanner.socketflag=true;}
   }	
}