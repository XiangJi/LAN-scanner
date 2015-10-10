package Scanner;

import java.net.InetAddress;
import java.io.IOException;


class ipThread extends Thread {
	public static String ipbase;
	public static int last1;
	public static int last2;
	public static int threadip;
	private int threadnum;
	public static int flag3 = 1;
	public static String hostname;
	public static String macname;
	public static String HostName;

	// gain active hosts in subnet, their MAC address and hostname

	/*
	 * for(i=0;i<ipth;i++) { new ipThread("IP" + i,i).start(); }
	 */
	public ipThread(String tname, int tnum) {
		super(tname);
		threadnum = tnum;
	}

	// InetAddress.getByName(ThreadScan.hostname.getText());
	public void run() {
		int i;
		if (flag3 == 1) {
			if (ipAction.flag2 == 0) {
				flag3 = 0;
				ipAction.flag2 = 1;
			}
		}

		String scanningip;

		for (i = last1 + threadnum; i <= last2; i += threadip) {
			scanningip = ipbase.concat(Integer.toString(i));
			ThreadScan.status1.setText("Scanning status: scanning hosts"
					+ scanningip);

			try {
				if (InetAddress.getByName(scanningip).isReachable(2000)) {
					InetAddress hostAddress = InetAddress.getByName(scanningip);
					String hostname = hostAddress.getHostName();
					String HostName = ipAction.getHostName(scanningip);
					String macname = ipAction.getMacAddressIP(scanningip);
					ThreadScan.Result1.append(scanningip + ":" + "Online"
							+ "      Hostname: " + hostname + "(" + HostName + ")"  + "  MAC address:"
							+ macname + '\n');
				}
			} catch (IOException e) {
				continue;
			}

		}

		if (i > last2 && threadnum == threadip - 1 && flag3 == 0) {
			flag3 = 1;
			ThreadScan.Result1.append("Scanning Completed..." + "\n");
			ThreadScan.status1.setText("Scanning status: Completed!");

			ThreadScan.status1.setEnabled(false);
		}

	}

}
