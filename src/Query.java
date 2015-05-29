import com.niit.jdbc.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * 
 * @author Administrator
 * 
 */
public class Query {
	private JFrame jf = new JFrame("查询记录");
	private JLabel dataLabel = new JLabel("请选择日期:");
	private JLabel yearLabel = new JLabel("年");
	private JLabel monthLabel = new JLabel("月");
	private JLabel dayLabel = new JLabel("日");
	private JComboBox yearChooser;
	private JComboBox monthChooser;
	private JComboBox dayChooser;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	private Vector data, columnNames;
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
		jf.add(topPanel, BorderLayout.NORTH);
		jf.setVisible(true);
		jf.setResizable(false);
		yearChooser.addActionListener(new ExceListener_year());
		monthChooser.addActionListener(new ExceListener_month());
		dayChooser.addActionListener(new ExceListener_day());

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
			Vector[] tab = new Vector[2];
			tab = new ItemDao().query_data(d1, user);
			columnNames = tab[0];
			data = tab[1];
			model = new DefaultTableModel(data, columnNames);
			JTable table = new JTable(model);
			scrollPane = new JScrollPane(table);
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
			Vector[] tab = new Vector[2];
			tab = new ItemDao().query_data(d1, user);
			columnNames = tab[0];
			data = tab[1];
			model = new DefaultTableModel(data, columnNames);
			JTable table = new JTable(model);
			scrollPane = new JScrollPane(table);
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
			Vector[] tab = new Vector[2];
			tab = new ItemDao().query_data(d1, user);
			columnNames = tab[0];
			data = tab[1];
			model = new DefaultTableModel(data, columnNames);
			JTable table = new JTable(model);
			scrollPane = new JScrollPane(table);
			jf.add(scrollPane, BorderLayout.CENTER);
			jf.validate();
		}
	}

}
