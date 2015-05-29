import com.niit.jdbc.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * 修改管理员的用户名和密码
 * 
 * @author Administrator
 * 
 */
public class AdminModule {
	JFrame jf = new JFrame("修改管理员账号和密码");
	private JLabel nameLabel = new JLabel("请输入新的账号：");
	private JLabel oldpassLabel = new JLabel("请输入旧的密码：");
	private JLabel newpassLabel = new JLabel("请输入新的密码：");
	private JLabel wordLabel = new JLabel("再输入一遍密码："); // 用户密码标签
	private JTextField nameFld = new JTextField(20);
	private JPasswordField oldpassFld = new JPasswordField(20);
	private JPasswordField newpassFld = new JPasswordField(20);
	private JPasswordField wordFld = new JPasswordField(20);
	private JButton handleBtn = new JButton("提交");

	public void init() { // 使JFrame在屏幕中央显示
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); // 获取屏幕的大小
		int x = (int) ((d.getWidth() - 400) / 2); // 获取JFrame框起点的x坐标
		int y = (int) ((d.getHeight() - 300) / 2); // 获取JFrame框起点的y坐标
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
						JOptionPane.showMessageDialog(jf, "修改成功");
						jf.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(jf, "密码不一致，请重新输入密码");
						newpassFld.setText("");
						wordFld.setText("");

					}
				} else {
					JOptionPane.showMessageDialog(jf, "旧密码不正确，请重新输入密码");
					oldpassFld.setText("");
				}

			}
		});
	}

}
