package Scanner;

import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class TCPThread extends Thread 
{    
public static InetAddress hostAddress;
public static int MIN_port;
public static int MAX_port;
public static int flag=1;
private int threadnum;    
public TCPThread(String name,int threadnum) 
{        
super(name);        
this.threadnum = threadnum;    
}    
public void run() 
{
int i; 
if(flag==1) 
{if(SubmitAction.flag1==0){ flag=0;SubmitAction.flag1=1;}
}     
Socket theTCPsocket;
for (i = MIN_port+threadnum; i <=MAX_port; i += Integer.parseInt(ThreadScan.maxThread.getText())) 
{
ThreadScan.status.setText("Scanning status: scanning "+i+" Port");
//System.out.print(i+" ");
try
{
theTCPsocket=new Socket(hostAddress,i);
theTCPsocket.close();
ThreadScan.Result.append(" "+i);
switch(i)
{case 7:
ThreadScan.Result.append("(ECHO)");
break;

case 21:
ThreadScan.Result.append("(FTP)");
break;
case 23:
ThreadScan.Result.append("(TELNET)");
break;
case 25:
ThreadScan.Result.append("(SMTP)");
break;
case 53:
ThreadScan.Result.append("(DNS)");
break; 
case 69:
ThreadScan.Result.append("(TFTP)");
break;
case 79:
ThreadScan.Result.append("(Finger)");
break;
case 80:
ThreadScan.Result.append("(HTTP)");
break;
case 110:
ThreadScan.Result.append("(POP)");
break;
case 135:
ThreadScan.Result.append("(RPC)");
break;
case 139:
ThreadScan.Result.append("(netBIOS)");
break;
case 388:
ThreadScan.Result.append("(LDM)");
break;
case 1433:
ThreadScan.Result.append("(MSSQL)");
break;
case 3389:
ThreadScan.Result.append("Terminal Services");
break;
}
ThreadScan.Result.append(",");
}
catch (IOException e)
{}
}
if (i>MAX_port&&threadnum==Integer.parseInt(ThreadScan.maxThread.getText())-1&&flag==0)
{flag=1;
ThreadScan.Result.append("\n"+"Scanning completed...");
ThreadScan.status.setText("Scanning status: Completed!");

ThreadScan.status.setEnabled(false);

}
}





}