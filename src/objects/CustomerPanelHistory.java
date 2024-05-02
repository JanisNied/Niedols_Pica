package objects;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import localisation.LocalisedButton;
import localisation.LocalisedLabelAttributed;
import localisation.ThemeRoundPanel;
import main.Global;
import raven.glasspanepopup.DefaultOption;
import raven.glasspanepopup.GlassPanePopup;
import raven.glasspanepopup.Option;
import states.Receipt;

@SuppressWarnings("serial")
public class CustomerPanelHistory extends JPanel {
	private Customer customer;
	public CustomerPanelHistory(Customer customer, int cornerRadius, Color bglight, Color bgdark, Color borderlight, Color borderdark) {
		this.customer = customer;
		setPreferredSize(new Dimension(238, 100));
		setLayout(null);
		setOpaque(false);
		
		
		LocalisedLabelAttributed lblNewLabel = new LocalisedLabelAttributed("ordertitle.text"," #"+customer.getOrderNum()+": ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 11, 210, 21);
		add(lblNewLabel);
		
		LocalisedButton btnNewButton = new LocalisedButton("receipt.text", this::receipt);
		btnNewButton.setBounds(10, 59, 102, 29);
		add(btnNewButton);
		
		
		LocalisedButton btnNewButton_1 = new LocalisedButton("infobutton.text", this::info);
		btnNewButton_1.setBounds(126, 58, 102, 30);
		add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel(customer.getDate()+", "+customer.getTime());
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(20, 37, 208, 21);
		add(lblNewLabel_1);
		
		JPanel panel = new ThemeRoundPanel(cornerRadius, bglight, bgdark, borderlight, borderdark);
		panel.setBounds(0, 0, 238, 100);
		add(panel);
		
	}
	private void receipt() {
		Receipt receipt = new Receipt(customer);
		receipt.setVisible(true);
		new Sound(Global.sounds.get("pickuppaper"), 1f, false).play();
		Global.centerFrameOnScreen(receipt);
	}
	private void info() {
		Option options = new DefaultOption() {
		    @Override
		    public float opacity() {
		        return 0.5f;
		    }
		    @Override
		    public boolean closeWhenClickOutside() {
		        return false;
		    }

		};
		GlassPanePopup.showPopup(new InfoPanel(customer,20, new Color(200, 200, 200, 250), new Color(70,70,70, 250), new Color(0,0,0), new Color(200, 200, 200)),options);
	}
}
