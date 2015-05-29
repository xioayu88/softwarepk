import com.niit.jdbc.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.*;

public class DeleteModule {
	private JFrame jf = new JFrame("��ϸ�嵥");
	private JTextArea dateArea = new JTextArea(1, 20);
	private JTextArea descArea = new JTextArea(10, 20);
	private JTextArea amountArea = new JTextArea(1, 20);
	private JTextArea typeArea = new JTextArea(1, 20);
	private JTextArea remarkArea = new JTextArea(10, 20);
	private JButton button = new JButton("ɾ�������¼");
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

		dateArea.setBorder(new TitledBorder(new EtchedBorder(), "��¼������"));
		descArea.setBorder(new TitledBorder(new EtchedBorder(), "����֧�ļ�����"));
		amountArea.setBorder(new TitledBorder(new EtchedBorder(), "��֧�Ľ��"));
		typeArea.setBorder(new TitledBorder(new EtchedBorder(), "��֧������"));
		remarkArea.setBorder(new TitledBorder(new EtchedBorder(), "��ע"));

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
				int option = JOptionPane.showConfirmDialog(jf, "��ȷ��Ҫɾ�������¼��");
				if (option == JOptionPane.YES_OPTION) {
					int id = getId();
					new ItemDao().delete(id);
					JOptionPane.showMessageDialog(jf, "�����¼�ѱ�ɾ��");
					jf.setVisible(false);
				}
			}
		});
	}
}
