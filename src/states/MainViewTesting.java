package states;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import localisation.MainViewTabbedPane;
import localisation.ThemePanel;
import localisation.ThemeRoundPanel;
import main.Global;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class MainViewTesting extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Global.setup();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainViewTesting frame = new MainViewTesting();
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
	public MainViewTesting() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		setContentPane(contentPane);
		
        JPanel mainpanel = new JPanel();
        mainpanel.setBounds(5, 5, 774, 551);
        contentPane.add(mainpanel);
		mainpanel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setTabPlacement(JTabbedPane.BOTTOM);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBounds(0, 0, 774, 551);
		mainpanel.add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		tabbedPane.addTab("New tab", null, panel_1, null);
		
		JPanel bgforenterfield = new JPanel();
		bgforenterfield.setLayout(null);
		bgforenterfield.setBackground(new Color(0, 64, 0));
		bgforenterfield.setBounds(280, 47, 479, 465);
		panel_1.add(bgforenterfield);
		
		JButton delivery = new JButton("");
		delivery.setIcon(new ImageIcon(MainViewTesting.class.getResource("/img/chicken.png")));
		delivery.setBounds(173, 11, 60, 60);
		bgforenterfield.add(delivery);
		
		JButton pickup = new JButton("");
		pickup.setBounds(246, 11, 60, 60);
		bgforenterfield.add(pickup);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 91, 191, 288);
		bgforenterfield.add(panel_2);
		panel_2.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 36, 171, 28);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JLabel nm = new JLabel("Vārds");
		nm.setBounds(10, 11, 114, 14);
		panel_2.add(nm);
		nm.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel surnm = new JLabel("Uzvards");
		surnm.setBounds(10, 75, 114, 14);
		panel_2.add(surnm);
		surnm.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 96, 171, 28);
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(10, 156, 171, 28);
		panel_2.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel mobile = new JLabel("Tālrunis");
		mobile.setBounds(10, 135, 171, 14);
		panel_2.add(mobile);
		mobile.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton pickup_1 = new JButton("");
		pickup_1.setBounds(10, 195, 81, 81);
		panel_2.add(pickup_1);
		
		JButton pickup_1_1 = new JButton("");
		pickup_1_1.setBounds(101, 195, 81, 81);
		panel_2.add(pickup_1_1);
		
		JPanel mapdata = new JPanel();
		mapdata.setLayout(null);
		mapdata.setBounds(211, 91, 258, 322);
		bgforenterfield.add(mapdata);
		
		JLabel lblAdresesLauks = new JLabel("Adreses Lauks");
		lblAdresesLauks.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdresesLauks.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAdresesLauks.setBounds(10, 11, 238, 14);
		mapdata.add(lblAdresesLauks);
		
		JPanel map = new JPanel();
		map.setBounds(10, 36, 238, 276);
		mapdata.add(map);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 390, 191, 64);
		bgforenterfield.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblPiegde = new JLabel("Piegāde:");
		lblPiegde.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPiegde.setBounds(10, 11, 171, 19);
		panel_3.add(lblPiegde);
		
		JLabel lblKop = new JLabel("Kopā: ");
		lblKop.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblKop.setBounds(10, 34, 171, 19);
		panel_3.add(lblKop);
		
		JButton pickup_1_1_1 = new JButton("Pasūtīt");
		pickup_1_1_1.setBounds(211, 390, 119, 65);
		bgforenterfield.add(pickup_1_1_1);
		pickup_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton pickup_1_1_1_1 = new JButton("Tirīt Grozu");
		pickup_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pickup_1_1_1_1.setBounds(350, 425, 119, 30);
		bgforenterfield.add(pickup_1_1_1_1);
		
		JPanel buttonpanel = new JPanel();
		buttonpanel.setBounds(173, 11, 60, 60);
		bgforenterfield.add(buttonpanel);
		
		JLabel data = new JLabel("Cilvēka Dati");
		data.setFont(new Font("Tahoma", Font.BOLD, 19));
		data.setHorizontalAlignment(SwingConstants.CENTER);
		data.setBounds(280, 11, 479, 23);
		panel_1.add(data);
	}
}
