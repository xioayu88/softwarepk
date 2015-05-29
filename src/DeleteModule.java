import com.niit.jdbc.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.*;

public class DeleteModule {
	private JFrame jf = new JFrame("详细清单");
	private JTextArea dateArea = new JTextArea(1, 20);
	private JTextArea descArea = new JTextArea(10, 20);
	private JTextArea amountArea = new JTextArea(1, 20);
	private JTextArea typeArea = new JTextArea(1, 20);
	private JTextArea remarkArea = new JTextArea(10, 20);
	private JButton button = new JButton("删除此项记录");
	private int id;
	private String date;
	private String desc;
	private String amount;
	private String type;
	private String remark;

	public DeleteModule(int id, String date, String desc, String amount,
			String type, String remark) {
		this.id = id;
		this.date = date;
		this.desc = desc;
		this.amount = amount;
		this.type = type;
		this.remark = remark;
	}

	public int getId() {
		return id;
	}

	public void init() {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - 300) / 2;
		int y = (d.height - 500) / 2;
		jf.setBounds(x, y, 300, 500);
		dateArea.setText(date);
		descArea.setText(desc);
		amountArea.setText(amount);
		typeArea.setText(type);
		remarkArea.setText(remark);

		dateArea.setBorder(new TitledBorder(new EtchedBorder(), "记录的日期"));
		descArea.setBorder(new TitledBorder(new EtchedBorder(), "对收支的简单描述"));
		amountArea.setBorder(new TitledBorder(new EtchedBorder(), "收支的金额"));
		typeArea.setBorder(new TitledBorder(new EtchedBorder(), "收支的类型"));
		remarkArea.setBorder(new TitledBorder(new EtchedBorder(), "备注"));

		dateArea.setEditable(false);
		descArea.setEditable(false);
		amountArea.setEditable(false);
		typeArea.setEditable(false);
		remarkArea.setEditable(false);

		Box box = Box.createVerticalBox();
		box.add(dateArea);
		box.add(descArea);
		box.add(amountArea);
		box.add(typeArea);
		box.add(remarkArea);
		JPanel panel = new JPanel();
		panel.add(button);
		box.add(panel);
		jf.add(box);
		jf.setVisible(true);
		jf.setResizable(false);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(jf, "您确定要删除该项纪录吗？");
				if (option == JOptionPane.YES_OPTION) {
					int id = getId();
					new ItemDao().delete(id);
					JOptionPane.showMessageDialog(jf, "该项纪录已被删除");
					jf.setVisible(false);
				}
			}
		});
	}
}
