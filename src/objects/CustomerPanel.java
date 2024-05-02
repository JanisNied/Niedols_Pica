package objects;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import localisation.LocalisedButton;
import localisation.LocalisedLabelAttributed;
import localisation.ThemeRoundPanel;
import main.Global;
import raven.glasspanepopup.DefaultOption;
import raven.glasspanepopup.GlassPanePopup;
import raven.glasspanepopup.Option;
import states.MainView;
import states.Receipt;
import states.Stove;

@SuppressWarnings("serial")
public class CustomerPanel extends JPanel {
	private Customer customer;
	public CustomerPanel(Customer customer, int cornerRadius, Color bglight, Color bgdark, Color borderlight, Color borderdark) {
		this.customer = customer;
		setPreferredSize(new Dimension(230, 100));
		setLayout(null);
		setOpaque(false);
		
		
		LocalisedLabelAttributed lblNewLabel = new LocalisedLabelAttributed("ordertitle.text"," #"+customer.getOrderNum()+": ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 11, 210, 21);
		add(lblNewLabel);
		
		LocalisedButton btnNewButton = new LocalisedButton("receipt.text", this::receipt);
		btnNewButton.setBounds(131, 34, 89, 23);
		add(btnNewButton);
		
		LocalisedButton btnCooking = new LocalisedButton("cooking.text", this::plc);
		btnCooking.setBounds(131, 59, 89, 23);
		add(btnCooking);
		
		LocalisedButton btnNewButton_1 = new LocalisedButton("infobutton.text", this::info);
		btnNewButton_1.setBounds(10, 36, 111, 46);
		add(btnNewButton_1);
		
		JPanel panel = new ThemeRoundPanel(cornerRadius, bglight, bgdark, borderlight, borderdark);
		panel.setBounds(0, 0, 230, 100);
		add(panel);
		
	}
	private void plc() {
		if (MainView.cooking.size() < 3) {
			MainView.frncscroll.add(new Stove(customer, 20, new Color(50, 50, 50, 10), new Color(200, 200, 200, 10), new Color(0,0,0), new Color(200,200,200)));
			MainView.frncscroll.repaint();
			MainView.frncscroll.revalidate();
			MainView.cooking.add(customer);
			MainView.updateCustomers();
			
		}
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
