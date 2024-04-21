package states;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import main.Global;
import main.JSON;
import main.Settings;

public class TestingWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField loginfield, passwordfield;
	private JLabel bg, passwordtitle, logintitle, themeSwitch;
	private JButton btnRegister, btnLogin;
	private JLabel languageSwitch;
	private JComboBox<String> comboBox;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		JSON.setupFiles(Global.persistenceLocation, Global.settings, Global.database);
		Settings.currentSettings = JSON.jsonToHashMap(Global.settings, "CURRENT SETTINGS");
		Settings.lang = JSON.jsonToHashMap(new File(System.getProperty("user.dir")+Global.fileSeparator+"locales"+Global.fileSeparator+Settings.currentSettings.get("lang")+".json"), "LANGUAGE");
		Global.reloadLAF();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestingWindow frame = new TestingWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TestingWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(null);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		setContentPane(contentPane);
		JPanel loginpanel = new JPanel();
		loginpanel.setBounds(0, 0, 784, 561);
		loginpanel.setBackground(new Color(255, 255, 255));
		contentPane.add(loginpanel);
		loginpanel.setLayout(null);
		
		themeSwitch = new JLabel();
		themeSwitch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
            	changeTheme();     
            }
        });
		
		comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               updateLanguage((String)comboBox.getSelectedItem());
            }
        });
		try {
            InputStream inputStream = TestingWindow.class.getClassLoader().getResourceAsStream("fonts/font.ttf");
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (Exception e) {
            e.printStackTrace();
        }
		comboBox.setBounds(664, 456, 110, 34);
		loginpanel.add(comboBox);
		
		languageSwitch = new JLabel();
		languageSwitch.setIcon(getLanguageIcon());
		languageSwitch.setHorizontalAlignment(SwingConstants.CENTER);
		languageSwitch.setBounds(664, 500, 50, 50);
		loginpanel.add(languageSwitch);
		
		themeSwitch.setHorizontalAlignment(SwingConstants.CENTER);
		themeSwitch.setBounds(724, 500, 50, 50);
		themeSwitch.setIcon(getThemeIcon());
		loginpanel.add(themeSwitch);
		
		loginfield = new JTextField();
		loginfield.setBounds(302, 311, 180, 44);
		loginpanel.add(loginfield);
		loginfield.setColumns(10);
		
		passwordfield = new JTextField();
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
		
		JLabel infolabel = new JLabel("Viens vai vairāki ievades lauki ir tukši!");
		infolabel.setHorizontalAlignment(SwingConstants.CENTER);
		infolabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		infolabel.setBounds(260, 511, 264, 39);
		loginpanel.add(infolabel);
		
		setupLanguageSelector();
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
					comboBox.addItem(Global.removeExtension(f.getName()));
				}
			}
		}
    	comboBox.setSelectedItem(Settings.currentSettings.get("lang"));
    }
}
