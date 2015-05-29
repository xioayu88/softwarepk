import com.niit.jdbc.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;

/**
 * 用户管理模块
 * 
 * @author Administrator
 * 
 */
public class UserManager {
	private JFrame jf = new JFrame("用户管理"); // 创建用户管理的主框体
	private JMenuBar mBar = new JMenuBar();
	private JMenu nameMenu = new JMenu("修改");
	private JMenuItem nameItem = new JMenuItem("修改账号和密码");
	private JToolBar bar = new JToolBar(); // 创建工具条
	private JTable tab;
	private DefaultTableModel mod;
	private JScrollPane scroll;
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	Action newAction = new AbstractAction("新建") // 创建”新建“Action
	{
		public void actionPerformed(ActionEvent e) {
			new InputModule().init();
		}
	};
	Action enableAction = new AbstractAction("启用账户") // 创建”启用账户“Action
	{
		public void actionPerformed(ActionEvent e) {
			startAccount();
		}
	};
	Action disableAction = new AbstractAction("禁用账户") // 创建”禁用账户“Action
	{
		public void actionPerformed(ActionEvent e) {
			stopAccount();
		}
	};
	Action queryAction = new AbstractAction("查询") // 创建”查询“Action
	{
		public void actionPerformed(ActionEvent e) {
			queryAccount();
		}
	};

	public void init() {
		// 使JFrame在屏幕中央显示
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); // 获取屏幕的大小
		int x = (int) ((d.getWidth() - 400) / 2); // 获取JFrame框起点的x坐标
		int y = (int) ((d.getHeight() - 600) / 2); // 获取JFrame框起点的y坐标
		jf.setBounds(x, y, 400, 600);
		nameMenu.add(nameItem);
		mBar.add(nameMenu);
		bar.add(newAction); // 在工具条上添加Action按钮
		bar.addSeparator();
		bar.add(enableAction);
		bar.addSeparator();
		bar.add(disableAction);
		bar.addSeparator();
		bar.add(queryAction);
		jf.add(bar, BorderLayout.NORTH);
		jf.setJMenuBar(mBar);
		jf.setResizable(false);
		jf.setVisible(true);
		nameItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminModule().init();
			}
		});
	}

	/**
	 * 禁用账户的方法
	 */
	public void stopAccount() {
		String username = JOptionPane.showInputDialog(jf, "请输入用户名");
		if (username != null) {
			if (new UserDao().nameCheck_admin(username)) {
				if (new UserDao().loginCheck_stop(username)) {
					JOptionPane.showMessageDialog(jf, "该账户已经被禁用");
				} else {
					if (new UserDao().nameInsert_stop(username) == 1) {
						JOptionPane.showMessageDialog(jf, "该帐户被成功禁用");
					}
				}

			} else {
				JOptionPane.showMessageDialog(jf, "您输入的帐户不存在");
			}

		}
	}

	/**
	 * 启用账户的方法
	 */
	public void startAccount() {
		String username = JOptionPane.showInputDialog(jf, "请输入用户名");
		if (username != null) {
			if (new UserDao().nameCheck_admin(username)) {
				if (new UserDao().loginCheck_stop(username)) {
					new UserDao().nameDelete_stop(username);
					JOptionPane.showMessageDialog(jf, "该帐户成功解禁");
				} else {
					JOptionPane.showMessageDialog(jf, "该账户没有被禁用");
				}

			} else {
				JOptionPane.showMessageDialog(jf, "您输入的帐户不存在");
			}

		}
	}

	/**
	 * 查询账户的方法
	 */
	public void queryAccount() {
		String username = JOptionPane.showInputDialog(jf, "请输入用户名");
		if (username != null) {
			String name = "%" + username + "%";
			if (scrollPane != null) {
				jf.remove(scrollPane);
			}

			Vector[] tab = new UserDao().Query_user(name);
			Vector names = tab[0];
			Vector data = tab[1];
			model = new DefaultTableModel(data, names);
			table = new JTable(model);
			scrollPane = new JScrollPane(table);
			jf.add(scrollPane, BorderLayout.CENTER);
			jf.validate();

		}
	}
}
