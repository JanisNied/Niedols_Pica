package states;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import main.Global;
import main.Settings;
import ui.DraggablePanel;
import ui.RoundPanel;

public class LanyardBadge extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel themeSwitch, lanyard, username, username_1, nametitle, lblOrdersTaken;
	private RoundPanel profilepicture;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Global.setup();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LanyardBadge frame = new LanyardBadge();
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
	public LanyardBadge() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 1000);
		setUndecorated(true);
		setBackground(new Color(0,0,0,0));
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(0,0,0,0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel personalinfo = new JPanel();
		personalinfo.setBackground(new Color(0,0,0,0));
		personalinfo.setBounds(107, 242, 419, 265);
		contentPane.add(personalinfo);
		personalinfo.setLayout(null);
		
		themeSwitch = new JLabel();
		themeSwitch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
            	changeTheme();     
            }
        });
		themeSwitch.setHorizontalAlignment(SwingConstants.CENTER);
		themeSwitch.setBounds(300, 167, 50, 50);
		themeSwitch.setIcon(getThemeIcon());
		personalinfo.add(themeSwitch);
		
		profilepicture = new RoundPanel(20, getScrollBG(), getScrollBorder());
		profilepicture.setBounds(66, 47, 105, 98);
		personalinfo.add(profilepicture);
		
		nametitle = new JLabel("Name:");
		nametitle.setForeground(getText());
		nametitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		nametitle.setBounds(181, 46, 176, 34);
		personalinfo.add(nametitle);
		
		username = new JLabel("12345678912345678912");
		username.setForeground(getText());
		username.setFont(new Font("Tahoma", Font.PLAIN, 14));
		username.setBounds(191, 67, 194, 34);
		personalinfo.add(username);
		
		username_1 = new JLabel("0");
		username_1.setForeground(getText());
		username_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		username_1.setBounds(191, 122, 194, 34);
		personalinfo.add(username_1);
		
		lblOrdersTaken = new JLabel("Orders Taken: ");
		lblOrdersTaken.setForeground(getText());
		lblOrdersTaken.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblOrdersTaken.setBounds(181, 101, 176, 34);
		personalinfo.add(lblOrdersTaken);
		
		DraggablePanel dragpanel = new DraggablePanel();
		dragpanel.setBounds(107, 242, 419, 265);
		contentPane.add(dragpanel);
		
		lanyard = new JLabel("");
		lanyard.setBounds(5, 5, 1000, 1000);
		lanyard.setIcon(new ImageIcon(LanyardBadge.class.getResource("/img/lanyardasset"+Settings.currentSettings.get("theme")+".png")));
		lanyard.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lanyard);
	}
	 private ImageIcon getThemeIcon() {
	    	ImageIcon theme = new ImageIcon(new ImageIcon(LoginWindow.class.getResource("/themeicons/"+Settings.currentSettings.get("theme")+".png")).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
	    	return theme;
	}
	private Color getScrollBG() {
		switch(Settings.currentSettings.get("theme")) {
		case "light":
			return new Color(50, 50, 50, 50);
		default:
			return new Color(200, 200, 200, 50);
		}
	}
	private Color getScrollBorder() {
		switch(Settings.currentSettings.get("theme")) {
		case "light":
			return new Color(0, 0, 0);
		default:
			return new Color(200, 200, 200);
		}
	}
	private void changeTheme() {
		Global.themeSwitcher();
		Global.reloadLAF();		
		themeSwitch.setIcon(getThemeIcon());
		profilepicture.setBackground(getScrollBG());
		profilepicture.setBorderColor(getScrollBorder());
		nametitle.setForeground(getText());
		username.setForeground(getText());
		username_1.setForeground(getText());
		lblOrdersTaken.setForeground(getText());
		lanyard.setIcon(new ImageIcon(LanyardBadge.class.getResource("/img/lanyardasset"+Settings.currentSettings.get("theme")+".png")));
	}
	private Color getText() {
		switch(Settings.currentSettings.get("theme")) {
		case "light":
			return Color.BLACK;
		default:
			return Color.WHITE;
		}
	}
}
