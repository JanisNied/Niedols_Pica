package states;

import javax.swing.JFrame;
import javax.swing.JPanel;
import main.Global;

public class WindowHandler extends JFrame {
	private static final long serialVersionUID = -4846037374513777426L;
	public WindowHandler(JPanel contentPane) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setContentPane(contentPane);
		setResizable(false);
		Global.reloadLAF();
	}
}
