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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import db.Database;
import localisation.LocalisedLabel;
import localisation.LocalisedLabelAttributed;
import localisation.ThemeIcon;
import localisation.ThemeRoundPanel;
import main.Global;
import main.JSON;
import main.Settings;
import objects.BoxItem;
import ui.DraggablePanel;

public class LanyardBadge extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel themeSwitch, username, username_1, nametitle, lblOrdersTaken;
	private ThemeRoundPanel profilepicture;
	private JLabel workerid;
	private JPanel panel;
	private JLabel languageSwitch;
	private JComboBox<BoxItem> comboBox;
	private boolean valid = false;

	public LanyardBadge() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 419, 265);
		setUndecorated(true);
		setBackground(new Color(0,0,0,0));
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(0,0,0,0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JPanel personalinfo = new ThemeRoundPanel(20, new Color(255, 255, 255, 250), new Color(70,70,70, 250), new Color(0,0,0), new Color(200, 200, 200));
		personalinfo.setBackground(new Color(0,0,0,0));
		personalinfo.setBounds(0, 0, 419, 265);
		contentPane.add(personalinfo);
		personalinfo.setLayout(null);
		
		themeSwitch = new ThemeIcon(new ImageIcon(new ImageIcon(LoginWindow.class.getResource("/themeicons/light.png")).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)), new ImageIcon(new ImageIcon(LoginWindow.class.getResource("/themeicons/dark.png")).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
		themeSwitch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
            	changeTheme();     
            }
        });
		themeSwitch.setHorizontalAlignment(SwingConstants.CENTER);
		themeSwitch.setBounds(359, 204, 50, 50);
		personalinfo.add(themeSwitch);
		
		profilepicture = new ThemeRoundPanel(20, new Color(255, 255, 255, 250), new Color(70,70,70, 250), new Color(0,0,0), new Color(200, 200, 200));
		profilepicture.setBounds(10, 61, 105, 98);
		personalinfo.add(profilepicture);
		
		nametitle = new LocalisedLabelAttributed("name.text", ": ");
		nametitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		nametitle.setBounds(125, 60, 176, 34);
		personalinfo.add(nametitle);
		
		username = new JLabel(Database.getDisplayName(Global.database, Global.user));
		username.setFont(new Font("Tahoma", Font.PLAIN, 14));
		username.setBounds(135, 81, 194, 34);
		personalinfo.add(username);
		
		username_1 = new JLabel(Integer.toString(Database.getOrders(Global.database, Global.user)));
		username_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		username_1.setBounds(135, 136, 194, 34);
		personalinfo.add(username_1);
		
		lblOrdersTaken = new LocalisedLabelAttributed("orderstaken.text", ": ");
		lblOrdersTaken.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblOrdersTaken.setBounds(125, 115, 250, 34);
		personalinfo.add(lblOrdersTaken);
		
		workerid = new LocalisedLabel("workerid.text");
		workerid.setHorizontalAlignment(SwingConstants.CENTER);
		workerid.setFont(new Font("Tahoma", Font.BOLD, 20));
		workerid.setBounds(10, 11, 399, 34);
		personalinfo.add(workerid);
		
		panel = new ThemeRoundPanel(20, new Color(70,70,70, 250),new Color(255, 255, 255, 250), new Color(0,0,0), new Color(200, 200, 200));
		panel.setBounds(10, 44, 399, 6);
		personalinfo.add(panel);
		
		languageSwitch = new ThemeIcon(new ImageIcon(new ImageIcon(LoginWindow.class.getResource("/img/lightthemeglobe.png")).getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH)), new ImageIcon(new ImageIcon(LoginWindow.class.getResource("/img/darkthemeglobe.png")).getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH)));
		languageSwitch.setHorizontalAlignment(SwingConstants.CENTER);
		languageSwitch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
            	comboBox.setVisible(!comboBox.isVisible());
            }
        });
		languageSwitch.setBounds(299, 204, 50, 50);
		personalinfo.add(languageSwitch);
		
		comboBox = new JComboBox<BoxItem>();
		comboBox.setVisible(false);
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
		comboBox.setBounds(299, 171, 110, 22);
		personalinfo.add(comboBox);
		
		DraggablePanel dragpanel = new DraggablePanel();
		dragpanel.setBounds(0, 0, 419, 265);
		contentPane.add(dragpanel);
		setupLanguageSelector();
		valid = true;
	}
	private void changeTheme() {
		Global.themeSwitcher();
		Global.reloadLAF();		
	}
	private void updateLanguage(String lang) {
    	Global.changeLanguage(lang);
    	if (Global.frame != null)
    		Global.frame.repaint();
        repaint();
        MainView.updateDesc();
        MainView.updateCart();
    }
    private void setupLanguageSelector() {
    	File locales = new File(Global.persistenceLocation+Global.fileSeparator+Global.fileSeparator+"locales");
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
