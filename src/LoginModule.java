import com.niit.jdbc.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * ��½ģ��
 * 
 * @author Administrator
 * 
 */
public class LoginModule {

	private JFrame jf = new JFrame("��½"); // ������½��������
	private JLabel nameLabel = new JLabel("�û�������"); // �û�������ǩ
	private JLabel passLabel = new JLabel("�û����룺"); // �û������ǩ
	private JTextField nameFld = new JTextField(20); // �û����������ı���
	private JPasswordField passFld = new JPasswordField(20); // �û����������ı���
	private JRadioButton adminBtn = new JRadioButton("����Ա"); // ����Ա��ѡ��ť
	private JRadioButton userBtn = new JRadioButton("�û�", true); // �û���ѡ��ť
	private ButtonGroup group = new ButtonGroup(); // ��ť��
	private JButton loginBtn = new JButton("��½"); // ��½��ť
	private static String name;
	private static String pass;
	public static ArrayList<String> list = new ArrayList<String>();

	public String getName() {
		return nameFld.getText();
	}

	public String getPass() {
		return passFld.getText();
	}

	// ��ʼ�����
	public void init()

	{ // ʹJFrame����Ļ������ʾ
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); // ��ȡ��Ļ�Ĵ�С
		int x = (int) ((d.getWidth() - 400) / 2); // ��ȡJFrame������x����
		int y = (int) ((d.getHeight() - 200) / 2); // ��ȡJFrame������y����
		group.add(adminBtn); // Ϊgroup���adminBtn��ť
		group.add(userBtn);
		JPanel topPanel = new JPanel(); // �����ĸ�JPanel���
		JPanel centerPanel = new JPanel();
		JPanel cenPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		topPanel.add(nameLabel); // ����������nameLabel��nameFld
		topPanel.add(nameFld);
		centerPanel.add(passLabel); // �ڶ���������passLabel��passFld
		centerPanel.add(passFld);
		cenPanel.add(adminBtn); // ������������adminBtn��userBtn
		cenPanel.add(userBtn);
		bottomPanel.add(loginBtn); // ��ײ��������loginBtn
		Box box = Box.createVerticalBox(); // ����һ����ֱ���е�Box
		box.add(topPanel); // ���ĸ����������ӵ�Box��
		box.add(centerPanel);
		box.add(cenPanel);
		box.add(bottomPanel);
		jf.add(box);
		jf.setBounds(x, y, 400, 200); // ���ô��ڵ�λ��
		jf.setVisible(true); // ���ô��ڿɼ�
		jf.setResizable(false); // ���ô��ڴ�С���ɸı�
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name = getName(); // ��ȡnameFld�е�����
				pass = getPass(); // ��ȡpassFld�е�����
				list.add(name);
				list.add(pass);
				if (adminBtn.isSelected()) // �������admin��ݵ�½
				{ // ���������������ȷ
					if (new UserDao().loginCheck_NAP(name, pass)) {
						new UserManager().init(); // ����admin����
						jf.setVisible(false); // ��½������ʧ
					} else // ����������������
					{ // ������ʾ�Ի���
						JOptionPane.showMessageDialog(jf, "�û��������������������������");
						nameFld.setText(""); // �������������
						passFld.setText(""); // �������������
					}
				} else if (userBtn.isSelected()) // �������user��ݵ�½
				{
					if (new UserDao().loginCheck_admin(name, pass)) {
						if (new UserDao().loginCheck_stop(name)) {
							JOptionPane.showMessageDialog(jf, "���˺��ѱ�����Ա����");
						} else {
							new UserFrame().init();
							jf.setVisible(false);
						}
					} else {
						JOptionPane.showMessageDialog(jf, "�û��������������������������");
						nameFld.setText("");
						passFld.setText("");
					}
				}
			}
		});
	}

	public static void main(String[] args) {
		new CreateTableDao().init();
		new LoginModule().init();
	}
}
