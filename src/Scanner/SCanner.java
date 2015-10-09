package Scanner;

public class SCanner {
	static boolean socketflag=false;
    static boolean ipflag=false;
    static boolean success=true;
    static boolean serverflag=false;
    static boolean serverclose=false;
    
	public static void main(String[] args) {  
		new ThreadScan();
		
		// create the socket GUI
		ClientSocketFrame clien = new ClientSocketFrame();
 		clien.setVisible(false);
 		ServerSocketFrame frame= new ServerSocketFrame();
 		frame.setVisible(false);
     	while(true){
		if(socketflag==true){
	    clien.setVisible(true); // 将窗体显示
	    if(ipflag==true&&success==true) {
	    	clien.connect();
	    }
	    if(success==false) {
	    	clien.setVisible(false);socketflag=false;success=true;ipflag=false;}
	    }
		if(serverflag==true){
			frame.setVisible(true);
	       if(serverclose==false) {
	    	   frame.getserver();
	       } // 调用方法
	       if(serverclose==true) {
	    	   frame.setVisible(false);serverclose=false;serverflag=false;
	       }
		}
		
		}
	}
}