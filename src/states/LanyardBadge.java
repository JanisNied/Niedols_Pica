package states;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

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
		
		JPanel profilepicture = new RoundPanel(20, getScrollBG(), getScrollBorder());
		profilepicture.setBounds(66, 47, 105, 98);
		personalinfo.add(profilepicture);
		
		JLabel nametitle = new JLabel("Name:");
		nametitle.setForeground(new Color(255, 255, 255));
		nametitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		nametitle.setBounds(181, 46, 176, 34);
		personalinfo.add(nametitle);
		
		JLabel username = new JLabel("12345678912345678912");
		username.setForeground(new Color(255, 255, 255));
		username.setFont(new Font("Tahoma", Font.PLAIN, 14));
		username.setBounds(191, 67, 194, 34);
		personalinfo.add(username);
		
		JLabel username_1 = new JLabel("0");
		username_1.setForeground(Color.WHITE);
		username_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		username_1.setBounds(191, 122, 194, 34);
		personalinfo.add(username_1);
		
		JLabel lblOrdersTaken = new JLabel("Orders Taken: ");
		lblOrdersTaken.setForeground(Color.WHITE);
		lblOrdersTaken.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblOrdersTaken.setBounds(181, 101, 176, 34);
		personalinfo.add(lblOrdersTaken);
		
		DraggablePanel dragpanel = new DraggablePanel();
		dragpanel.setBounds(107, 242, 419, 265);
		contentPane.add(dragpanel);
		
		JLabel lanyard = new JLabel("");
		lanyard.setBounds(5, 5, 1000, 1000);
		lanyard.setIcon(new ImageIcon(LanyardBadge.class.getResource("/img/lanyardasset.png")));
		lanyard.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lanyard);
	}
	private Color getScrollBG() {
		switch(Settings.currentSettings.get("theme")) {
		case "light":
			return new Color(200, 200, 200, 50);
		default:
			return new Color(50, 50, 50, 50);
		}
	}
	private Color getScrollBorder() {
		switch(Settings.currentSettings.get("theme")) {
		case "light":
			return new Color(0,0,0);
		default:
			return new Color(200, 200, 200);
		}
	}
}
