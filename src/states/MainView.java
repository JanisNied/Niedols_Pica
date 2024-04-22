package states;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import org.jdesktop.animation.timing.interpolation.Interpolator;

import animation.CustomInterpolator;
import main.Global;
import main.JSON;
import main.Settings;

public class MainView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, transitionpanel;
	private JLabel welcomelabel;

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
					MainView frame = new MainView();
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
	public MainView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(getBGPanelColor());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        transitionpanel = new JPanel();
        transitionpanel.setBounds(0, 0, 784, 561);
        contentPane.add(transitionpanel);
        transitionpanel.setBackground(getPanelColor());
        transitionpanel.setLayout(null);
        
        welcomelabel = new JLabel("{Welcome.text}, {user}!");
        welcomelabel.setFont(new Font("Tahoma", Font.PLAIN, 33));
        welcomelabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomelabel.setBounds(182, 230, 420, 100);
        transitionpanel.add(welcomelabel);

        JPanel mainpanel = new JPanel();
        mainpanel.setBounds(5, 5, 774, 551);
        mainpanel.setBackground(getBGPanelColor());
        contentPane.add(mainpanel);
		mainpanel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBounds(0, 0, 774, 551);
		mainpanel.add(tabbedPane);
		
		JPanel presetpizza = new JPanel();
		tabbedPane.addTab("Gatavas Picas", null, presetpizza, null);
		
		JPanel custompizza = new JPanel();
		tabbedPane.addTab("Pielāgota Pica", null, custompizza, null);
		
		JPanel cart = new JPanel();
		tabbedPane.addTab("Grozs", null, cart, null);
		
		JPanel bakery = new JPanel();
		tabbedPane.addTab("Virtuve", null, bakery, null);
			TimingTargetAdapter timingTarget = new TimingTargetAdapter() {
	            @Override
	            public void timingEvent(float fraction) {
	                int newY = (int) (transitionpanel.getY() + (-600 - transitionpanel.getY()) * fraction);
	                transitionpanel.setBounds(0, newY, 784, 561);
	            }
	        };
	        Animator animator = new Animator(10000, timingTarget);
	        animator.setEndBehavior(Animator.EndBehavior.HOLD);
	        Interpolator interpolator = new CustomInterpolator();
	        animator.setInterpolator(interpolator);
	        animator.start();
	}
	private Color getPanelColor() {
		Color cl = null;
		switch(Settings.currentSettings.get("theme")) {
			case "light":
				cl = Color.WHITE;
				break;
			case "dark":
				cl = new Color(72, 72, 72);
				break;
		}
		return cl;
	}
	private Color getBGPanelColor() {
		switch(Settings.currentSettings.get("theme")) {
		case "light":
			return new Color(225, 225, 225);
		default:
			return new Color(35, 35, 35);
		}
	}
}
