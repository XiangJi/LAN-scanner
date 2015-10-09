package Scanner;

import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ipAction implements ActionListener {
	public static int flag2 = 0;

	public void actionPerformed(ActionEvent a) {
		String ipstart;
		String ipend;
		String substr;
		char[] strs;
		// 本程序只能扫描一个D段.
		int ipth, i, j;
		ipstart = ThreadScan.startip.getText().trim();
		ipend = ThreadScan.endip.getText().trim();
		ipth = Integer.parseInt(ThreadScan.ipthread.getText());
		ThreadScan.Result1.append("\nOnline hosts:\n");
		ThreadScan.status1.setEnabled(true);

		strs = ipstart.toCharArray();
		i = 0;
		j = 0;
		while (i != 3) {
			if (strs[j] == '.')
				i++;
			j++;
		}
		ipThread.ipbase = ipstart.substring(0, j);
		ipThread.last1 = Integer.parseInt(ipstart.substring(j));
		ipThread.last2 = Integer.parseInt(ipend.substring(j));
		ipThread.threadip = ipth;
		// System.out.println(ipThread.threadip);

		for (i = 0; i < ipth; i++)
		// Create i thread;

		{
			new ipThread("IP" + i, i).start();

		}
		flag2 = 0;
	}

	public static String getMacAddressIP(String remotePcIP) {
		String str = "";
		String macAddress = "";
		try {
			Process pp = Runtime.getRuntime().exec("nbtstat -A " + remotePcIP);
			InputStreamReader ir = new InputStreamReader(pp.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			for (int i = 1; i < 100; i++) {
				str = input.readLine();
				if (str != null) {
					if (str.indexOf("MAC address") > 1) {
						macAddress = str.substring(
								str.indexOf("MAC address") + 8, str.length());
						break;
					}
				}
			}
		} catch (IOException ex) {
		}
		return macAddress;
	}

	public static String getHostName(String remotePcIP) {
		String str = "";
		String HostName = "";
		try {
			Process pp = Runtime.getRuntime().exec("nbtstat -A " + remotePcIP);
			InputStreamReader ir = new InputStreamReader(pp.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			for (int i = 1; i < 100; i++) {
				str = input.readLine();
				if (str != null) {
					if (str.indexOf("Only") > 1) {
						HostName = str.substring(str.indexOf("Only") - 21,
								str.indexOf("Only") - 6);
						break;
					}
				}
			}
		} catch (IOException ex) {
		}
		return HostName;
	}

}
