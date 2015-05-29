import com.niit.jdbc.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * 登陆模块
 * 
 * @author Administrator
 * 
 */
public class LoginModule {

	private JFrame jf = new JFrame("登陆"); // 创建登陆的主窗体
	private JLabel nameLabel = new JLabel("用户姓名："); // 用户姓名标签
	private JLabel passLabel = new JLabel("用户密码："); // 用户密码标签
	private JTextField nameFld = new JTextField(20); // 用户姓名输入文本框
	private JPasswordField passFld = new JPasswordField(20); // 用户密码输入文本框
	private JRadioButton adminBtn = new JRadioButton("管理员"); // 管理员单选按钮
	private JRadioButton userBtn = new JRadioButton("用户", true); // 用户单选按钮
	private ButtonGroup group = new ButtonGroup(); // 按钮组
	private JButton loginBtn = new JButton("登陆"); // 登陆按钮
	private static String name;
	private static String pass;
	public static ArrayList<String> list = new ArrayList<String>();

	public String getName() {
		return nameFld.getText();
	}

	public String getPass() {
		return passFld.getText();
	}

	// 初始化框架
	public void init()

	{ // 使JFrame在屏幕中央显示
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); // 获取屏幕的大小
		int x = (int) ((d.getWidth() - 400) / 2); // 获取JFrame框起点的x坐标
		int y = (int) ((d.getHeight() - 200) / 2); // 获取JFrame框起点的y坐标
		group.add(adminBtn); // 为group添加adminBtn按钮
		group.add(userBtn);
		JPanel topPanel = new JPanel(); // 创建四个JPanel面板
		JPanel centerPanel = new JPanel();
		JPanel cenPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		topPanel.add(nameLabel); // 最顶层的面板添加nameLabel和nameFld
		topPanel.add(nameFld);
		centerPanel.add(passLabel); // 第二个面板添加passLabel和passFld
		centerPanel.add(passFld);
		cenPanel.add(adminBtn); // 第三个面板添加adminBtn和userBtn
		cenPanel.add(userBtn);
		bottomPanel.add(loginBtn); // 最底层的面板添加loginBtn
		Box box = Box.createVerticalBox(); // 创建一个垂直排列的Box
		box.add(topPanel); // 将四个面板依次添加到Box中
		box.add(centerPanel);
		box.add(cenPanel);
		box.add(bottomPanel);
		jf.add(box);
		jf.setBounds(x, y, 400, 200); // 设置窗口的位置
		jf.setVisible(true); // 设置窗口可见
		jf.setResizable(false); // 设置窗口大小不可改变
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name = getName(); // 获取nameFld中的内容
				pass = getPass(); // 获取passFld中的内容
				list.add(name);
				list.add(pass);
				if (adminBtn.isSelected()) // 如果是以admin身份登陆
				{ // 如果姓名和密码正确
					if (new UserDao().loginCheck_NAP(name, pass)) {
						new UserManager().init(); // 进入admin窗口
						jf.setVisible(false); // 登陆窗口消失
					} else // 如果姓名或密码错误
					{ // 弹出提示对话框
						JOptionPane.showMessageDialog(jf, "用户名或密码输入错误，请重新输入");
						nameFld.setText(""); // 姓名输入栏清空
						passFld.setText(""); // 密码输入栏清空
					}
				} else if (userBtn.isSelected()) // 如果是以user身份登陆
				{
					if (new UserDao().loginCheck_admin(name, pass)) {
						if (new UserDao().loginCheck_stop(name)) {
							JOptionPane.showMessageDialog(jf, "该账号已被管理员禁用");
						} else {
							new UserFrame().init();
							jf.setVisible(false);
						}
					} else {
						JOptionPane.showMessageDialog(jf, "用户名或密码输入错误，请重新输入");
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
