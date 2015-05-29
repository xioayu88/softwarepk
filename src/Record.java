import com.niit.jdbc.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.util.*;
import java.awt.event.*;

/**
 * ���һ����¼
 * 
 * @author Administrator
 * 
 */
public class Record {
	private JFrame jf = new JFrame("��Ӽ�¼");
	private JMenuBar bar = new JMenuBar();
	private JMenu fileMenu = new JMenu("�ļ�(F)");
	private JMenuItem newItem = new JMenuItem("�½�����(N)");
	private JMenuItem modifyItem = new JMenuItem("�޸�����(M)");
	private JMenuItem deleteItem = new JMenuItem("ɾ������(D)");
	private JMenuItem queryItem = new JMenuItem("��ѯ(Q)");
	private JLabel dataLabel = new JLabel("��ѡ������:");
	private JLabel yearLabel = new JLabel("��");
	private JLabel monthLabel = new JLabel("��");
	private JLabel dayLabel = new JLabel("��");
	private JTextArea descArea = new JTextArea(10, 20);
	private JScrollPane descScroll = new JScrollPane(descArea);
	private JLabel mountLabel = new JLabel("��������:");
	private JTextField amountFld = new JTextField(18);
	private JLabel typeLabel = new JLabel("��ѡ�����ͣ�");
	private JTextArea remarkArea = new JTextArea(2, 20);
	private Vector types;
	private JButton commitBtn = new JButton("�ύ");
	private JComboBox yearChooser;
	private JComboBox monthChooser;
	private JComboBox dayChooser;
	private JComboBox typeChooser;
	private int id;
	private String year;
	private String month;
	private String day;
	private String description;
	private String amount;
	private String type;
	private String remark;
	public ArrayList<String> list = new LoginModule().list;

	/**
	 * �޲���������
	 */
	public Record() {

	}

	/**
	 * �в���������
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @param description
	 * @param amount
	 * @param type
	 * @param remark
	 */
	public Record(int id, String year, String month, String day,
			String description, String amount, String type, String remark) {
		this.id = id;
		this.year = year;
		this.month = month;
		this.day = day;
		this.description = description;
		this.amount = amount;
		this.type = type;
		this.remark = remark;
	}

	public String getName() {
		return list.get(0);
	}

	public String getPass() {
		return list.get(1);
	}

	public void init() {

		fileMenu.setMnemonic(KeyEvent.VK_F);
		newItem.setAccelerator(KeyStroke
				.getKeyStroke('N', InputEvent.CTRL_MASK));
		newItem.setMnemonic(KeyEvent.VK_N);
		modifyItem.setAccelerator(KeyStroke.getKeyStroke('M',
				InputEvent.CTRL_MASK));
		modifyItem.setMnemonic(KeyEvent.VK_M);
		deleteItem.setAccelerator(KeyStroke.getKeyStroke('D',
				InputEvent.CTRL_MASK));
		deleteItem.setMnemonic(KeyEvent.VK_D);
		queryItem.setAccelerator(KeyStroke.getKeyStroke('Q',
				InputEvent.CTRL_MASK));
		queryItem.setMnemonic(KeyEvent.VK_Q);
		newItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String yourType = JOptionPane.showInputDialog(jf, "����������������ͣ�");
				if (types.contains(yourType)) {
					JOptionPane.showMessageDialog(jf, "������������Ѿ����ڣ���ȷ��");
				} else {
					new TypeDao().insert(yourType);
				}
			}
		});

		modifyItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ChooserType().init();
			}
		});

		deleteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DelType().init();
			}
		});

		fileMenu.add(newItem);
		fileMenu.add(modifyItem);
		fileMenu.add(deleteItem);
		fileMenu.add(queryItem);
		fileMenu.add(queryItem);
		bar.add(fileMenu);

		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - 400) / 2;
		int y = (d.height - 600) / 2;
		jf.setBounds(x, y, 400, 600);
		String[] years = new String[100];
		for (int i = 0; i < 100; i++) {
			years[i] = (i + 2010) + "";
		}
		yearChooser = new JComboBox(years);
		String[] days = new String[31];
		for (int j = 0; j < 30; j++) {
			days[j] = (j + 1) + "";
		}
		dayChooser = new JComboBox(days);
		String[] months = new String[12];
		for (int k = 0; k < 12; k++) {
			months[k] = (k + 1) + "";
		}
		monthChooser = new JComboBox(months);
		JPanel topPanel = new JPanel();
		topPanel.add(dataLabel);
		topPanel.add(yearChooser);
		topPanel.add(yearLabel);
		topPanel.add(monthChooser);
		topPanel.add(monthLabel);
		topPanel.add(dayChooser);
		topPanel.add(dayLabel);
		descScroll.setBorder(new TitledBorder(new EtchedBorder(), "����֧������",
				TitledBorder.TOP, TitledBorder.CENTER));
		types = new TypeDao().getAll();
		typeChooser = new JComboBox(types);
		JPanel centerPanel = new JPanel();
		centerPanel.add(mountLabel);
		centerPanel.add(amountFld);
		JPanel cenPanel = new JPanel();
		cenPanel.add(typeLabel);
		cenPanel.add(typeChooser);
		JPanel bottomPanel = new JPanel();
		bottomPanel.add(commitBtn);
		Box box = Box.createVerticalBox();
		box.add(topPanel);
		box.add(descScroll);
		box.add(centerPanel);
		box.add(cenPanel);
		remarkArea.setBorder(new TitledBorder(new EtchedBorder(), "��ע",
				TitledBorder.TOP, TitledBorder.CENTER));
		box.add(remarkArea);
		box.add(commitBtn);
		jf.setJMenuBar(bar);
		jf.add(box);
		jf.setResizable(false);
		jf.setVisible(true);
	}

	public void build() {
		commitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = getName();
				String pass = getPass();
				String year = getYear();
				String month = getMonth();
				String day = getDay();
				String date = year + "-" + month + "-" + day;
				String description = getDescription();
				int amount = getAmount();
				String type = getType();
				String remark = getRemark();
				int affect = new ItemDao().add(user, pass, amount, date, type,
						description, remark);
				if (affect == 1) {
					JOptionPane.showMessageDialog(jf, "�ύ�ɹ�");
					jf.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(jf, "�ύʧ�ܣ��������ύ");
				}
			}
		});
	}

	public void edit() {
		yearChooser.setSelectedItem(year);
		monthChooser.setSelectedItem(month);
		dayChooser.setSelectedItem(day);
		descArea.setText(description);
		amountFld.setText(amount);
		typeChooser.setSelectedItem(type);
		remarkArea.setText(remark);
		commitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String year = getYear();
				String month = getMonth();
				String day = getDay();
				String date = year + "-" + month + "-" + day;
				String description = getDescription();
				int amount = getAmount();
				String type = getType();
				String remark = getRemark();
				int affect = new ItemDao().modify(date, description, amount,
						type, remark, id);
				if (affect == 1) {
					JOptionPane.showMessageDialog(jf, "�ύ�ɹ�");
					jf.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(jf, "�ύʧ�ܣ��������ύ");
				}
			}
		});
	}

	public String getYear() {
		return yearChooser.getSelectedItem().toString();
	}

	public String getMonth() {
		return monthChooser.getSelectedItem().toString();
	}

	public String getDay() {
		return dayChooser.getSelectedItem().toString();
	}

	public String getDescription() {
		return descArea.getText();
	}

	public int getAmount() {
		return Integer.parseInt(amountFld.getText());
	}

	public String getType() {
		return typeChooser.getSelectedItem().toString();
	}

	public String getRemark() {
		return remarkArea.getText();
	}

	/**
	 * �޸����͵��ڲ��࣬�������͵��޸�
	 * 
	 * @author Administrator
	 * 
	 */
	class ChooserType {
		private JFrame jf = new JFrame("�޸�����");
		private JComboBox typeChooser;
		private JTextField inputFld = new JTextField();
		private JButton confirmBtn = new JButton("ȷ��");
		private Vector types;

		public void init() {
			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (d.width - 200) / 2;
			int y = (d.height - 200) / 2;
			jf.setBounds(x, y, 200, 200);
			types = new TypeDao().getAll();
			typeChooser = new JComboBox(types);
			typeChooser.setBorder(new TitledBorder(new EtchedBorder(),
					"��ѡ��Ҫ�޸ĵ�����"));
			inputFld.setBorder(new TitledBorder(new EtchedBorder(), "�����������͵�����"));
			Box box = Box.createVerticalBox();
			box.add(typeChooser);
			box.add(inputFld);
			JPanel panel = new JPanel();
			panel.add(confirmBtn);
			box.add(panel);
			jf.add(box);
			jf.setResizable(false);
			jf.setVisible(true);

			confirmBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String oldType = typeChooser.getSelectedItem().toString();
					String newType = inputFld.getText();
					if (types.contains(newType)) {
						JOptionPane.showMessageDialog(jf, "������������Ѿ����ڣ���ȷ��");
					} else {
						Vector v = new Vector();
						v.add("salary");
						v.add("clothes");
						v.add("food");
						if (v.contains(oldType)) {
							JOptionPane.showMessageDialog(jf, oldType
									+ "ΪϵͳĬ�����ͣ����������");
						} else {
							new TypeDao().modify(newType, oldType);
							JOptionPane.showMessageDialog(jf, "�������ѱ�����");
							jf.setVisible(false);
						}
					}
				}
			});
		}
	}

	/**
	 * ɾ�����͵��ڲ��࣬�������͵�ɾ��
	 */
	class DelType {
		private JFrame jf = new JFrame("ɾ������");
		private Vector types;
		private JComboBox typeChooser;
		private JButton confirmBtn = new JButton("ȷ��");

		public void init() {
			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (d.width - 200) / 2;
			int y = (d.height - 150) / 2;
			jf.setBounds(x, y, 200, 150);
			types = new TypeDao().getAll();
			typeChooser = new JComboBox(types);
			typeChooser.setBorder(new TitledBorder(new EtchedBorder(),
					"��ѡ����Ҫɾ��������"));
			JPanel panel = new JPanel();
			panel.add(confirmBtn);
			Box box = Box.createVerticalBox();
			box.add(typeChooser);
			box.add(panel);
			jf.add(box);
			jf.setResizable(false);
			jf.setVisible(true);

			confirmBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String type = typeChooser.getSelectedItem().toString();
					if (new TypeDao().isVisiable(type)) {
						JOptionPane.showMessageDialog(jf, "�������м�¼,ϵͳ������ɾ��");
					} else {
						Vector v = new Vector();
						v.add("salary");
						v.add("clothes");
						v.add("food");
						if (v.contains(type)) {
							JOptionPane.showMessageDialog(jf, type
									+ "ΪϵͳĬ�����ͣ�������ɾ��");
						} else {
							new TypeDao().delete(type);
							JOptionPane.showMessageDialog(jf, "�������ѱ�ɾ��");
							jf.setVisible(false);
						}
					}
				}
			});
		}
	}
}
