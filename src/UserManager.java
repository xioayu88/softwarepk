import com.niit.jdbc.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;

/**
 * �û�����ģ��
 * 
 * @author Administrator
 * 
 */
public class UserManager {
	private JFrame jf = new JFrame("�û�����"); // �����û������������
	private JMenuBar mBar = new JMenuBar();
	private JMenu nameMenu = new JMenu("�޸�");
	private JMenuItem nameItem = new JMenuItem("�޸��˺ź�����");
	private JToolBar bar = new JToolBar(); // ����������
	private JTable tab;
	private DefaultTableModel mod;
	private JScrollPane scroll;
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	Action newAction = new AbstractAction("�½�") // �������½���Action
	{
		public void actionPerformed(ActionEvent e) {
			new InputModule().init();
		}
	};
	Action enableAction = new AbstractAction("�����˻�") // �����������˻���Action
	{
		public void actionPerformed(ActionEvent e) {
			startAccount();
		}
	};
	Action disableAction = new AbstractAction("�����˻�") // �����������˻���Action
	{
		public void actionPerformed(ActionEvent e) {
			stopAccount();
		}
	};
	Action queryAction = new AbstractAction("��ѯ") // ��������ѯ��Action
	{
		public void actionPerformed(ActionEvent e) {
			queryAccount();
		}
	};

	public void init() {
		// ʹJFrame����Ļ������ʾ
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); // ��ȡ��Ļ�Ĵ�С
		int x = (int) ((d.getWidth() - 400) / 2); // ��ȡJFrame������x����
		int y = (int) ((d.getHeight() - 600) / 2); // ��ȡJFrame������y����
		jf.setBounds(x, y, 400, 600);
		nameMenu.add(nameItem);
		mBar.add(nameMenu);
		bar.add(newAction); // �ڹ����������Action��ť
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
	 * �����˻��ķ���
	 */
	public void stopAccount() {
		String username = JOptionPane.showInputDialog(jf, "�������û���");
		if (username != null) {
			if (new UserDao().nameCheck_admin(username)) {
				if (new UserDao().loginCheck_stop(username)) {
					JOptionPane.showMessageDialog(jf, "���˻��Ѿ�������");
				} else {
					if (new UserDao().nameInsert_stop(username) == 1) {
						JOptionPane.showMessageDialog(jf, "���ʻ����ɹ�����");
					}
				}

			} else {
				JOptionPane.showMessageDialog(jf, "��������ʻ�������");
			}

		}
	}

	/**
	 * �����˻��ķ���
	 */
	public void startAccount() {
		String username = JOptionPane.showInputDialog(jf, "�������û���");
		if (username != null) {
			if (new UserDao().nameCheck_admin(username)) {
				if (new UserDao().loginCheck_stop(username)) {
					new UserDao().nameDelete_stop(username);
					JOptionPane.showMessageDialog(jf, "���ʻ��ɹ����");
				} else {
					JOptionPane.showMessageDialog(jf, "���˻�û�б�����");
				}

			} else {
				JOptionPane.showMessageDialog(jf, "��������ʻ�������");
			}

		}
	}

	/**
	 * ��ѯ�˻��ķ���
	 */
	public void queryAccount() {
		String username = JOptionPane.showInputDialog(jf, "�������û���");
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
