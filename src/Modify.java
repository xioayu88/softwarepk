import com.niit.jdbc.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * 修改记录
 * 
 * @author Administrator
 * 
 */
public class Modify {
	private JFrame jf = new JFrame("记录");
	private JLabel dataLabel = new JLabel("请选择日期:");
	private JLabel yearLabel = new JLabel("年");
	private JLabel monthLabel = new JLabel("月");
	private JLabel dayLabel = new JLabel("日");
	private JComboBox yearChooser;
	private JComboBox monthChooser;
	private JComboBox dayChooser;
	private JList idList;
	private JScrollPane scrollPane;
	private JButton button = new JButton("查看清单");
	public ArrayList<String> list = new LoginModule().list;

	public String getName() {
		return list.get(0);
	}

	public void init() {
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
		JPanel bottom = new JPanel();
		bottom.add(button);
		jf.add(bottom, BorderLayout.SOUTH);
		jf.add(topPanel, BorderLayout.NORTH);
		jf.setResizable(false);
		jf.setVisible(true);

		yearChooser.addActionListener(new ExceListener_year());
		monthChooser.addActionListener(new ExceListener_month());
		dayChooser.addActionListener(new ExceListener_day());
	}

	public void del() {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currentid = (String) idList.getSelectedValue();
				String number = currentid.split("：")[1];
				int id = Integer.parseInt(number);
				Vector v = new ItemDao().query(id);
				String date = v.get(0).toString();
				String desc = v.get(1).toString();
				String amount = v.get(2).toString();
				String type = v.get(3).toString();
				String remark = v.get(4).toString();
				new DeleteModule(id, date, desc, amount, type, remark).init();
			}
		});
	}

	public void que() {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currentid = (String) idList.getSelectedValue();
				String number = currentid.split("：")[1];
				int id = Integer.parseInt(number);
				Vector v = new ItemDao().query(id);
				String date = v.get(0).toString();
				String desc = v.get(1).toString();
				String amount = v.get(2).toString();
				String type = v.get(3).toString();
				String remark = v.get(4).toString();
				new ModifyModule(id, date, desc, amount, type, remark).init();
			}
		});
	}

	class ExceListener_year implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String year = yearChooser.getSelectedItem().toString();
			String month = monthChooser.getSelectedItem().toString();
			String day = dayChooser.getSelectedItem().toString();
			String d1 = year + "%";
			String user = getName();
			if (scrollPane != null) {
				jf.remove(scrollPane);
			}
			Vector data = new Vector();
			data = new ItemDao().query_id(d1, user);
			idList = new JList(data);
			scrollPane = new JScrollPane(idList);
			jf.add(scrollPane, BorderLayout.CENTER);
			jf.validate();
		}
	}

	class ExceListener_month implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String year = yearChooser.getSelectedItem().toString();
			String month = monthChooser.getSelectedItem().toString();
			String day = dayChooser.getSelectedItem().toString();
			String d1 = year + "-" + month + "%";
			String user = getName();
			if (scrollPane != null) {
				jf.remove(scrollPane);
			}
			Vector data = new Vector();
			data = new ItemDao().query_id(d1, user);
			idList = new JList(data);
			scrollPane = new JScrollPane(idList);
			jf.add(scrollPane, BorderLayout.CENTER);
			jf.validate();
		}
	}

	class ExceListener_day implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String year = yearChooser.getSelectedItem().toString();
			String month = monthChooser.getSelectedItem().toString();
			String day = dayChooser.getSelectedItem().toString();
			String d1 = year + "-" + month + "-" + day;
			String user = getName();
			if (scrollPane != null) {
				jf.remove(scrollPane);
			}
			Vector data = new Vector();
			data = new ItemDao().query_id(d1, user);
			idList = new JList(data);
			scrollPane = new JScrollPane(idList);
			jf.add(scrollPane, BorderLayout.CENTER);
			jf.validate();
		}
	}

}
