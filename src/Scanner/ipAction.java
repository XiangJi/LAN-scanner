package Scanner;


import java.io.*;

import java.awt.event.*;

class ipAction implements ActionListener {
	public static int flag2 = 0;

	public void actionPerformed(ActionEvent a) {
		String ipstart;
		String ipend;
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
		while (i < 3) {
			if (strs[j] == '.')
				i++;
			j++;
		}
		ipThread.ipbase = ipstart.substring(0, j);
		ipThread.last1 = Integer.parseInt(ipstart.substring(j));
		ipThread.last2 = Integer.parseInt(ipend.substring(j));
		ipThread.threadip = ipth;
		// System.out.println(ipThread.threadip);

		// Create i thread;
		for (i = 0; i < ipth; i++) {
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
					if (str.indexOf("MAC Address") > 1) {
						macAddress = str.substring(
								str.indexOf("MAC Address") + 13, str.length());
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
					if (str.indexOf("UNIQUE") > 1) {
						HostName = str.substring(str.indexOf("UNIQUE") - 21,
								str.indexOf("UNIQUE") - 6);
						break;
					}
				}
			}
		} catch (IOException ex) {
		}
		return HostName;
	}
}
