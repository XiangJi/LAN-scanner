package Scanner;
import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class SubmitAction implements ActionListener
{
 public static int flag1=0;	
public void actionPerformed (ActionEvent a) 
{
int minPort;
int maxPort;
int maxThread;

try
{
minPort=Integer.parseInt(ThreadScan.minPort.getText());
maxPort=Integer.parseInt(ThreadScan.maxPort.getText());
maxThread=Integer.parseInt(ThreadScan.maxThread.getText());
}
catch(NumberFormatException e)
{
ThreadScan.DLGINFO.setText("错误的端口号或线程数!端口号和线程数必须为整数!");
ThreadScan.DLGError.setVisible(true);
ThreadScan.status.setText("Scanning status: Not started");
return;
}
try
{
TCPThread.hostAddress=InetAddress.getByName(ThreadScan.hostname.getText());
}
catch(UnknownHostException e)
{
ThreadScan.DLGINFO.setText("            错误的IP地址/域名或地址不可达!            ");
ThreadScan.DLGError.setVisible(true);
ThreadScan.status.setText("Scanning status: Not started");
return;
}
if(minPort<0 || minPort>65535 || minPort>maxPort)
{
ThreadScan.DLGINFO.setText("最小端口必须是0-65535并且小于最大端口的整数!");
ThreadScan.DLGError.setVisible(true);
return;
}
else TCPThread.MIN_port=minPort;
if(maxPort<0 || maxPort>65535 || maxPort<minPort)
{
ThreadScan.DLGINFO.setText("最大端口必须是0-65535并且大于最小端口的整数!");
ThreadScan.DLGError.setVisible(true);
ThreadScan.status.setText("Scanning status: Not started");
return;
}
else TCPThread.MAX_port=maxPort;
if(maxThread<1 || maxThread>200)
{
ThreadScan.DLGINFO.setText("                    线程数为1-200的整数!                    ");
ThreadScan.DLGError.setVisible(true);
ThreadScan.status.setText("Scanning status: Not started");
return;
}
ThreadScan.status.setEnabled(true);
ThreadScan.Result.setText("");
ThreadScan.Result.append("Scanning "+ThreadScan.hostname.getText()+" Number of threads "+ThreadScan.maxThread.getText()+"\n");
ThreadScan.Result.append("Open ports: ");
for(int i=0;i<maxThread;i++)
{
new TCPThread("T" + i,i).start();
//System.out.print(i+" ");
}
flag1=0;
}
}