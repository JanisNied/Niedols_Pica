package states;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import localisation.LocalisedLabel;
import main.Global;
import main.Settings;
import objects.CartItem;
import objects.Customer;
import objects.Sound;
import ui.DraggablePanel;

public class Receipt extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea area;
	private Customer customer;
	
	public Receipt(Customer customer) {
		this.customer = customer;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 320, 633);
		setUndecorated(true);
		setBackground(new Color(0,0,0,0));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setOpaque(false);
		contentPane.setBackground(new Color(0,0,0,0));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBackground(new Color(0,0,0,0));
		panel.setBounds(8, 19, 304, 594);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1_2 = new JLabel("Paldies!");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_2.setBounds(17, 493, 269, 32);
		panel.add(lblNewLabel_1_2);
		
		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setBackground(Color.BLACK);
		panel_1_1_1.setBounds(17, 479, 269, 3);
		panel.add(panel_1_1_1);
		
		
		JButton btnNewButton = new JButton("X");
		btnNewButton.addActionListener(e -> event());
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 7));
		btnNewButton.setBounds(240, 36, 46, 23);
		panel.add(btnNewButton);
		
		
		area = new JTextArea();
		area.setOpaque(false);
		area.getCaret().setVisible(false);
		area.setFocusable(false);
		area.setBackground(new Color(0,0,0,0));
		area.validate();
		area.setWrapStyleWord(true);
		area.setFont(new Font("Tahoma", Font.PLAIN, 12));
		area.setLineWrap(true);
		area.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(area);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBounds(22, 88, 259, 280);
		panel.add(scrollPane);
		
		DraggablePanel drag = new DraggablePanel();
		drag.setBounds(10, 22, 284, 550);
		panel.add(drag);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("€"+MainView.df.format(customer.getDeliveryFee()));
		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1_2.setBounds(157, 393, 129, 32);
		panel.add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("€"+MainView.df.format(customer.getFull()));
		lblNewLabel_1_1_2_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1_2_1.setBounds(156, 436, 129, 32);
		panel.add(lblNewLabel_1_1_2_1);
		
		LocalisedLabel lblNewLabel_1_1_1 = new LocalisedLabel("totalcost.text");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1_1.setBounds(17, 436, 129, 32);
		panel.add(lblNewLabel_1_1_1);
		
		LocalisedLabel lblNewLabel_1_1 = new LocalisedLabel("orderingcost.text");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(17, 393, 129, 32);
		panel.add(lblNewLabel_1_1);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(Color.BLACK);
		panel_1_1.setBounds(17, 379, 269, 3);
		panel.add(panel_1_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setBounds(17, 68, 269, 3);
		panel.add(panel_1);
		
		LocalisedLabel lblNewLabel_1 = new LocalisedLabel("receipt.text");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(87, 36, 129, 32);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel(new ImageIcon(new ImageIcon(Receipt.class.getResource("/img/receipt.png")).getImage().getScaledInstance(530, 594, java.awt.Image.SCALE_SMOOTH)));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 304, 594);
		panel.add(lblNewLabel);
		addPizzas();
		centerOnX();
	}
	private void event() {
		new Sound(Global.sounds.get("trash"), 1f, false).play();
		dispose();
	}
	private void addPizzas() {
		for (CartItem i : customer.getCart()) {
			area.append(Settings.lang.get(i.getPizzaObj().getNickname())+" - x"+i.getAmount()+" - €"+MainView.df.format(i.getFinalPrice())+"\n");
			area.append(i.getPizzaObj().description(true)+"\n\n");
		}
	}
	private void centerOnX() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - this.getWidth()) / 2;
        this.setLocation(x, 5000);
    }
}
