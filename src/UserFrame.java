import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * 用户主界面
 * 
 * @author Administrator
 * 
 */
public class UserFrame {
	private JFrame jf = new JFrame("我的记账本");
	private JToolBar bar = new JToolBar();
	Action newAction = new AbstractAction("新建记录") {
		public void actionPerformed(ActionEvent e) {
			Record build = new Record();
			build.init();
			build.build();
		}
	};
	Action modifyAction = new AbstractAction("修改记录") {
		public void actionPerformed(ActionEvent e) {
			Modify que = new Modify();
			que.init();
			que.que();
		}
	};
	Action deleteAction = new AbstractAction("删除记录") {
		public void actionPerformed(ActionEvent e) {
			Modify del = new Modify();
			del.init();
			del.del();
		}
	};
	Action queryAction = new AbstractAction("查询记录") {
		public void actionPerformed(ActionEvent e) {
			new Query().init();
		}
	};

	public void init() {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - 400) / 2;
		int y = (d.height - 600) / 2;
		jf.setBounds(x, y, 400, 600);
		bar.add(newAction);
		bar.addSeparator();
		bar.add(modifyAction);
		bar.addSeparator();
		bar.add(deleteAction);
		bar.addSeparator();
		bar.add(queryAction);
		bar.addSeparator();
		jf.add(bar, BorderLayout.NORTH);
		jf.setVisible(true);

	}
}
