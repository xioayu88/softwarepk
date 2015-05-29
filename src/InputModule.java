import com.niit.jdbc.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class InputModule {
	private JFrame jf = new JFrame("�������˻�");
	private JLabel nameLabel = new JLabel("�������û�������");
	private JLabel passLabel = new JLabel("�������û����룺");
	private JLabel wordLabel = new JLabel("������һ�����룺"); // �û������ǩ
	private JTextField nameFld = new JTextField(20);
	private JPasswordField passFld = new JPasswordField(20);
	private JPasswordField wordFld = new JPasswordField(20);
	private JButton handleBtn = new JButton("����");

	public void init() { // ʹJFrame����Ļ������ʾ
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); // ��ȡ��Ļ�Ĵ�С
		int x = (int) ((d.getWidth() - 400) / 2); // ��ȡJFrame������x����
		int y = (int) ((d.getHeight() - 200) / 2); // ��ȡJFrame������y����
		JPanel topPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel cenPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		topPanel.add(nameLabel);
		topPanel.add(nameFld);
		centerPanel.add(passLabel);
		centerPanel.add(passFld);
		cenPanel.add(wordLabel);
		cenPanel.add(wordFld);
		bottomPanel.add(handleBtn);
		Box box = Box.createVerticalBox();
		box.add(topPanel);
		box.add(centerPanel);
		box.add(cenPanel);
		box.add(bottomPanel);
		jf.setBounds(x, y, 400, 200);
		jf.add(box);
		jf.setVisible(true);
		jf.setResizable(false);

		handleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = nameFld.getText();
				String pass = passFld.getText();
				String word = wordFld.getText();
				if (pass.equals(word)) {
					if (new UserDao().addUserCheck(username)) {
						JOptionPane.showMessageDialog(jf, "�û����Ѿ����ڣ�����������");
						nameFld.setText("");
						passFld.setText("");
						wordFld.setText("");
					} else {
						if (new UserDao().addUser(username, pass) == 1) {
							JOptionPane.showMessageDialog(jf, "�˻������ɹ�");
						}
						jf.setVisible(false);
					}
				} else {
					JOptionPane.showMessageDialog(jf, "���벻һ�£���������������");
					passFld.setText("");
					wordFld.setText("");
				}
			}
		});
	}
}
