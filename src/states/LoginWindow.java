package states;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import main.Global;
import main.Settings;

public class LoginWindow extends JPanel {
	private static final long serialVersionUID = -8071787828056377082L;
	private JTextField loginfield;
	private JTextField passwordfield;
	private JLabel bg;

	public LoginWindow() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		JPanel loginpanel = new JPanel();
		loginpanel.setBounds(0, 0, 784, 561);
		loginpanel.setBackground(new Color(255, 255, 255));
		add(loginpanel);
		loginpanel.setLayout(null);
		
		loginfield = new JTextField();
		loginfield.setBounds(302, 311, 180, 44);
		loginpanel.add(loginfield);
		loginfield.setColumns(10);
		
		passwordfield = new JTextField();
		passwordfield.setColumns(10);
		passwordfield.setBounds(302, 391, 180, 44);
		loginpanel.add(passwordfield);
		
		JLabel logintitle = new JLabel(Settings.lang.get("login.text"));
		logintitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		logintitle.setVerticalAlignment(SwingConstants.BOTTOM);
		logintitle.setBounds(302, 286, 180, 22);
		loginpanel.add(logintitle);
		
		JLabel logoimage = new JLabel("");
		logoimage.setIcon(new ImageIcon(new ImageIcon(LoginWindow.class.getResource("/img/pizzalogo.png")).getImage().getScaledInstance(230, 230, java.awt.Image.SCALE_SMOOTH)));
		logoimage.setHorizontalAlignment(SwingConstants.CENTER);
		logoimage.setBounds(218, 0, 348, 325);
		loginpanel.add(logoimage);
		
		JLabel passwordtitle = new JLabel(Settings.lang.get("password.text"));
		passwordtitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordtitle.setVerticalAlignment(SwingConstants.BOTTOM);
		passwordtitle.setBounds(302, 366, 137, 22);
		loginpanel.add(passwordtitle);
		
		JButton btnRegister = new JButton(Settings.lang.get("register.text"));
		btnRegister.setBounds(398, 456, 84, 44);
		loginpanel.add(btnRegister);
		
		JButton btnLogin = new JButton(Settings.lang.get("loggingin.text"));
		btnLogin.addActionListener(e -> changeTheme());
		btnLogin.setBounds(302, 456, 84, 44);
		loginpanel.add(btnLogin);
		
		bg = new JLabel("");
		bg.setBounds(0, 0, 784, 575);
		loginpanel.add(bg);
		bg.setIcon(new ImageIcon(LoginWindow.class.getResource("/img/"+Settings.currentSettings.get("theme")+".png")));
		bg.setHorizontalAlignment(SwingConstants.CENTER);
	}
	void changeTheme() {
		Settings.currentSettings.put("theme", "dark");
		System.out.println(Settings.currentSettings);
		Global.frame.reloadLAF();
		bg.setIcon(new ImageIcon(LoginWindow.class.getResource("/img/"+Settings.currentSettings.get("theme")+".png")));
	}
}
