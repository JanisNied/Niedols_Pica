package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import classes.BoxItem;
import main.Global;
import main.JSON;
import main.Settings;

public class LoginWindow extends JPanel {
	private static final long serialVersionUID = -8071787828056377082L;
	private JTextField loginfield;
	private JPasswordField passwordfield;
	private JLabel bg, passwordtitle, logintitle, themeSwitch, languageSwitch;
	private JButton btnRegister, btnLogin;
	private JComboBox<BoxItem> comboBox;
	private boolean valid = false;
	
	public LoginWindow() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		JPanel loginpanel = new JPanel();
		loginpanel.setBounds(0, 0, 784, 561);
		loginpanel.setBackground(new Color(255, 255, 255));
		add(loginpanel);
		loginpanel.setLayout(null);
		
		themeSwitch = new JLabel();
		themeSwitch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
            	changeTheme();     
            }
        });
		themeSwitch.setHorizontalAlignment(SwingConstants.CENTER);
		themeSwitch.setBounds(724, 500, 50, 50);
		themeSwitch.setIcon(getThemeIcon());
		loginpanel.add(themeSwitch);
		
		languageSwitch = new JLabel();
		languageSwitch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
            	comboBox.setVisible(!comboBox.isVisible());
            }
        });
		languageSwitch.setIcon(getLanguageIcon());
		languageSwitch.setHorizontalAlignment(SwingConstants.CENTER);
		languageSwitch.setBounds(664, 500, 50, 50);
		loginpanel.add(languageSwitch);
		
		comboBox = new JComboBox<BoxItem>();
		comboBox.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 11));
		((JLabel)comboBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (valid) {
	            	BoxItem item = (BoxItem)comboBox.getSelectedItem();
	            	updateLanguage(item.getValue());
	            	comboBox.setVisible(false);
            	}
            }
        });
		comboBox.setBounds(664, 456, 110, 34);
		comboBox.setVisible(false);
		loginpanel.add(comboBox);
		
		loginfield = new JTextField();
		loginfield.setBounds(302, 311, 180, 44);
		loginpanel.add(loginfield);
		loginfield.setColumns(10);
		
		passwordfield = new JPasswordField();
		passwordfield.setColumns(10);
		passwordfield.setBounds(302, 391, 180, 44);
		loginpanel.add(passwordfield);
		
		logintitle = new JLabel(Settings.lang.get("login.text"));
		logintitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		logintitle.setVerticalAlignment(SwingConstants.BOTTOM);
		logintitle.setBounds(302, 286, 180, 22);
		loginpanel.add(logintitle);
		
		JLabel logoimage = new JLabel("");
		logoimage.setIcon(new ImageIcon(new ImageIcon(LoginWindow.class.getResource("/img/pizzalogo.png")).getImage().getScaledInstance(230, 230, java.awt.Image.SCALE_SMOOTH)));
		logoimage.setHorizontalAlignment(SwingConstants.CENTER);
		logoimage.setBounds(218, 0, 348, 325);
		loginpanel.add(logoimage);
		
		passwordtitle = new JLabel(Settings.lang.get("password.text"));
		passwordtitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordtitle.setVerticalAlignment(SwingConstants.BOTTOM);
		passwordtitle.setBounds(302, 366, 137, 22);
		loginpanel.add(passwordtitle);
		
		btnRegister = new JButton(Settings.lang.get("register.text"));
		btnRegister.setBounds(398, 456, 84, 44);
		loginpanel.add(btnRegister);
		
		btnLogin = new JButton(Settings.lang.get("loggingin.text"));
		btnLogin.setBounds(302, 456, 84, 44);
		loginpanel.add(btnLogin);
		
		bg = new JLabel("");
		bg.setBounds(0, 0, 784, 600);
		loginpanel.add(bg);
		bg.setIcon(new ImageIcon(LoginWindow.class.getResource("/img/"+Settings.currentSettings.get("theme")+".png")));
		bg.setHorizontalAlignment(SwingConstants.CENTER);
		
		setupLanguageSelector();
		valid = true;
	}
	private void changeTheme() {
		Global.themeSwitcher();
		Global.reloadLAF();
		themeSwitch.setIcon(getThemeIcon());
		languageSwitch.setIcon(getLanguageIcon());
		bg.setIcon(new ImageIcon(LoginWindow.class.getResource("/img/"+Settings.currentSettings.get("theme")+".png")));
	}
    private void updateLanguage(String lang) {
    	Global.changeLanguage(lang);
        logintitle.setText(Settings.lang.get("login.text"));
        passwordtitle.setText(Settings.lang.get("password.text"));
        btnRegister.setText(Settings.lang.get("register.text"));
        btnLogin.setText(Settings.lang.get("loggingin.text"));
    }
    private ImageIcon getThemeIcon() {
    	ImageIcon theme = new ImageIcon(new ImageIcon(LoginWindow.class.getResource("/themeicons/"+Settings.currentSettings.get("theme")+".png")).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
    	return theme;
    }
    private ImageIcon getLanguageIcon() {
    	ImageIcon theme = new ImageIcon(new ImageIcon(LoginWindow.class.getResource("/img/"+Settings.currentSettings.get("theme")+"themeglobe.png")).getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH));
    	return theme;
    }
    private void setupLanguageSelector() {
    	File locales = new File(System.getProperty("user.dir")+Global.fileSeparator+"locales");
    	for(File f : locales.listFiles()){
			if(f.isFile()){
				if (f.getName().endsWith(".json")) {
					HashMap<String, String> temporary = JSON.jsonToHashMap(f, "LOCALE ADDITION");
					String noextension = Global.removeExtension(f.getName());
					BoxItem item = new BoxItem(noextension, temporary.get("language"));
					comboBox.addItem(item);
					if (noextension.equalsIgnoreCase(Settings.currentSettings.get("lang")))
						comboBox.setSelectedItem(item);
				}
			}
		}
    }
}
