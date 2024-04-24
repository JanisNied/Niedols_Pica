package states;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class WindowHandler extends JFrame {
	private static final long serialVersionUID = -4846037374513777426L;
	public WindowHandler(JPanel contentPane) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setContentPane(contentPane);
		setResizable(false);
		getContentPane().setLayout(null);
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.print(e);
			}
		});
	}
	
	public void replaceContentPane(JPanel contentPane) {
		getContentPane().removeAll();
		setContentPane(contentPane);
		repaint();
		revalidate();
	}
}
