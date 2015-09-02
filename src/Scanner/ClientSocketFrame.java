package Scanner;

import java.awt.*;

import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class ClientSocketFrame extends JFrame  { // ������̳�JFrame��
	public JLabel label;
	public JPanel panel;
    public PrintWriter writer; // ����PrintWriter�����
    public BufferedReader reader; // ����BufferedReader����
    public Socket socket; // ����Socket����
    public JTextArea ta_info = new JTextArea(); // ����JtextArea����
    public JTextField tf_send = new JTextField(); // ����JtextField����
    public JTextField ip_input=new JTextField(10);
    public JButton b1=new JButton("����");
    public JLabel l1=new JLabel("���������ip��");
    public String s;
    public JPanel p1=new JPanel();
    public JMenuBar bar=new JMenuBar();
    public JMenu m1=new JMenu("Edit");
    public JMenuItem i1=new JMenuItem("exit");
    
   // private JButton a=new JButton("ok");
    
    public ClientSocketFrame() { // ���췽��
        super(); // ���ø���Ĺ��췽��
        setTitle("�ͻ��˳���");
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
    
    public void connect() { // �����׽��ַ���
        ta_info.append("��������......\n"); // �ı�������Ϣ��Ϣ
        SCanner.success=true;
        try { // ��׽�쳣
            socket = new Socket(s, 135); // ʵ����Socket����
            while (true) {
                writer = new PrintWriter(socket.getOutputStream(), true);
                reader = new BufferedReader(new InputStreamReader(socket
                        .getInputStream())); // ʵ����BufferedReader����
                ta_info.append("������ӡ�\n"); // �ı�������ʾ��Ϣ
                getClientInfo();
            }
        } catch (Exception e) {
            //e.printStackTrace(); // ����쳣��Ϣ
        	JOptionPane.showMessageDialog(this, "invalid ip address");
        	SCanner.success=false;
            
        }
    }
    
    /*public static void main(String[] args) { // ������
        ClientSocketFrame clien = new ClientSocketFrame(); // ������������
        clien.setVisible(true); // ��������ʾ
        clien.connect(); // �������ӷ���
    }*/
    
    public void getClientInfo() {
        try {
            while (true) { // ����׽���������״̬
                if (reader != null) {
                    String line = reader.readLine();
                    if (line != null)
                        ta_info.append("���յ����������͵���Ϣ��" + line + "\n"); // ��ÿͻ�����Ϣ
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    
                    reader.close();// �ر���
                    
                }
                if (socket != null) {
                    socket.close(); // �ر��׽���
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
            tf_send.addActionListener(new ActionListener() { // ���¼�
                        public void actionPerformed(ActionEvent e) {
                            writer.println(tf_send.getText()); // ���ı�������Ϣд����
                            ta_info.append("�ͻ��˷��͵���Ϣ�ǣ�" + tf_send.getText()
                                    + "\n"); // ���ı�������Ϣ��ʾ���ı�����
                            tf_send.setText(""); // ���ı������
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
            label.setText("�ͻ��˷��͵���Ϣ��");
        }
        return label;
    }

	
	
}
