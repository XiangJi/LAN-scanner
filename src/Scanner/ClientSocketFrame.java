package Scanner;

import java.awt.*;

import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class ClientSocketFrame extends JFrame  { // 创建类继承JFrame类
	public JLabel label;
	public JPanel panel;
    public PrintWriter writer; // 声明PrintWriter类对象
    public BufferedReader reader; // 声明BufferedReader对象
    public Socket socket; // 声明Socket对象
    public JTextArea ta_info = new JTextArea(); // 创建JtextArea对象
    public JTextField tf_send = new JTextField(); // 创建JtextField对象
    public JTextField ip_input=new JTextField(10);
    public JButton b1=new JButton("连接");
    public JLabel l1=new JLabel("输入服务器ip：");
    public String s;
    public JPanel p1=new JPanel();
    public JMenuBar bar=new JMenuBar();
    public JMenu m1=new JMenu("Edit");
    public JMenuItem i1=new JMenuItem("exit");
    
   // private JButton a=new JButton("ok");
    
    public ClientSocketFrame() { // 构造方法
        super(); // 调用父类的构造方法
        setTitle("客户端程序");
        setJMenuBar(bar);
        bar.add(m1);
        m1.add(i1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 361, 257);
        final JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        scrollPane.setViewportView(ta_info);
        getContentPane().add(getPanel(), BorderLayout.NORTH);
        FlowLayout f=new FlowLayout();
        f.setHgap(20);
        p1.setLayout(f);
        p1.add(l1);p1.add(ip_input);p1.add(b1);
        add(p1,BorderLayout.AFTER_LAST_LINE);
        b1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				s=ip_input.getText().trim();
				SCanner.ipflag=true;
			}
        	
        });
        i1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SCanner.success=false;
				
			}
        	
        });
      
    }
    
    public void connect() { // 连接套接字方法
        ta_info.append("尝试连接......\n"); // 文本域中信息信息
        SCanner.success=true;
        try { // 捕捉异常
            socket = new Socket(s, 135); // 实例化Socket对象
            while (true) {
                writer = new PrintWriter(socket.getOutputStream(), true);
                reader = new BufferedReader(new InputStreamReader(socket
                        .getInputStream())); // 实例化BufferedReader对象
                ta_info.append("完成连接。\n"); // 文本域中提示信息
                getClientInfo();
            }
        } catch (Exception e) {
            //e.printStackTrace(); // 输出异常信息
        	JOptionPane.showMessageDialog(this, "invalid ip address");
        	SCanner.success=false;
            
        }
    }
    
    /*public static void main(String[] args) { // 主方法
        ClientSocketFrame clien = new ClientSocketFrame(); // 创建本例对象
        clien.setVisible(true); // 将窗体显示
        clien.connect(); // 调用连接方法
    }*/
    
    public void getClientInfo() {
        try {
            while (true) { // 如果套接字是连接状态
                if (reader != null) {
                    String line = reader.readLine();
                    if (line != null)
                        ta_info.append("接收到服务器发送的信息：" + line + "\n"); // 获得客户端信息
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    
                    reader.close();// 关闭流
                    
                }
                if (socket != null) {
                    socket.close(); // 关闭套接字
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * @return
     */
    public JPanel getPanel() {
        if (panel == null) {
            panel = new JPanel();
            panel.add(getLabel());
            tf_send.setPreferredSize(new Dimension(200, 25));
            panel.add(tf_send);
            tf_send.addActionListener(new ActionListener() { // 绑定事件
                        public void actionPerformed(ActionEvent e) {
                            writer.println(tf_send.getText()); // 将文本框中信息写入流
                            ta_info.append("客户端发送的信息是：" + tf_send.getText()
                                    + "\n"); // 将文本框中信息显示在文本域中
                            tf_send.setText(""); // 将文本框清空
                        }
                    });
        }
        return panel;
    }
    
    /**
     * @return
     */
    public JLabel getLabel() {
        if (label == null) {
            label = new JLabel();
            label.setText("客户端发送的信息：");
        }
        return label;
    }

	
	
}
