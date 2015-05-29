import com.niit.jdbc.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class InputModule {
	private JFrame jf = new JFrame("创建新账户");
	private JLabel nameLabel = new JLabel("请输入用户姓名：");
	private JLabel passLabel = new JLabel("请输入用户密码：");
	private JLabel wordLabel = new JLabel("再输入一遍密码："); // 用户密码标签
	private JTextField nameFld = new JTextField(20);
	private JPasswordField passFld = new JPasswordField(20);
	private JPasswordField wordFld = new JPasswordField(20);
	private JButton handleBtn = new JButton("创建");

	public void init() { // 使JFrame在屏幕中央显示
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); // 获取屏幕的大小
		int x = (int) ((d.getWidth() - 400) / 2); // 获取JFrame框起点的x坐标
		int y = (int) ((d.getHeight() - 200) / 2); // 获取JFrame框起点的y坐标
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
						JOptionPane.showMessageDialog(jf, "用户名已经存在，请重新输入");
						nameFld.setText("");
						passFld.setText("");
						wordFld.setText("");
					} else {
						if (new UserDao().addUser(username, pass) == 1) {
							JOptionPane.showMessageDialog(jf, "账户创建成功");
						}
						jf.setVisible(false);
					}
				} else {
					JOptionPane.showMessageDialog(jf, "密码不一致，请重新输入密码");
					passFld.setText("");
					wordFld.setText("");
				}
			}
		});
	}
}
