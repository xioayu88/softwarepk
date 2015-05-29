import com.niit.jdbc.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * �޸Ĺ���Ա���û���������
 * 
 * @author Administrator
 * 
 */
public class AdminModule {
	JFrame jf = new JFrame("�޸Ĺ���Ա�˺ź�����");
	private JLabel nameLabel = new JLabel("�������µ��˺ţ�");
	private JLabel oldpassLabel = new JLabel("������ɵ����룺");
	private JLabel newpassLabel = new JLabel("�������µ����룺");
	private JLabel wordLabel = new JLabel("������һ�����룺"); // �û������ǩ
	private JTextField nameFld = new JTextField(20);
	private JPasswordField oldpassFld = new JPasswordField(20);
	private JPasswordField newpassFld = new JPasswordField(20);
	private JPasswordField wordFld = new JPasswordField(20);
	private JButton handleBtn = new JButton("�ύ");

	public void init() { // ʹJFrame����Ļ������ʾ
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); // ��ȡ��Ļ�Ĵ�С
		int x = (int) ((d.getWidth() - 400) / 2); // ��ȡJFrame������x����
		int y = (int) ((d.getHeight() - 300) / 2); // ��ȡJFrame������y����
		JPanel topPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel cenPanel = new JPanel();
		JPanel terPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		topPanel.add(nameLabel);
		topPanel.add(nameFld);
		centerPanel.add(oldpassLabel);
		centerPanel.add(oldpassFld);
		terPanel.add(newpassLabel);
		terPanel.add(newpassFld);
		cenPanel.add(wordLabel);
		cenPanel.add(wordFld);
		bottomPanel.add(handleBtn);
		Box box = Box.createVerticalBox();
		box.add(topPanel);
		box.add(centerPanel);
		box.add(terPanel);
		box.add(cenPanel);
		box.add(bottomPanel);
		jf.setBounds(x, y, 400, 300);
		jf.add(box);
		jf.setVisible(true);
		jf.setResizable(false);

		handleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = nameFld.getText();
				String password = oldpassFld.getText();
				String pass = newpassFld.getText();
				String word = wordFld.getText();
				if (new UserDao().Check_NAP(password)) {
					if (pass.equals(word)) {
						new UserDao().insert_NAP(username, pass, password);
						JOptionPane.showMessageDialog(jf, "�޸ĳɹ�");
						jf.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(jf, "���벻һ�£���������������");
						newpassFld.setText("");
						wordFld.setText("");

					}
				} else {
					JOptionPane.showMessageDialog(jf, "�����벻��ȷ����������������");
					oldpassFld.setText("");
				}

			}
		});
	}

}
