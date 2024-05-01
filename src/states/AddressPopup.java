package states;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.json.JSONException;
import org.json.JSONObject;
import org.jxmapviewer.viewer.GeoPosition;

import com.github.kevinsawicki.http.HttpRequest;

import localisation.LocalisedButton;
import localisation.LocalisedLabel;
import localisation.ThemeRoundPanel;
import objects.Customer;
import raven.glasspanepopup.GlassPanePopup;

@SuppressWarnings("serial")
public class AddressPopup extends JPanel {
	private JTextArea additional;
	public AddressPopup(boolean pizzeria, Customer customer, int bordersize, Color bglight, Color bgdark, Color borderlight, Color borderdark, GeoPosition pizzeriaBuilding) {
		setLayout(null);
		if (pizzeria)
			setPreferredSize(new Dimension(450, 150));
		else
			setPreferredSize(new Dimension(450, 300));
		setOpaque(false);
		
		ThemeRoundPanel panel = new ThemeRoundPanel(bordersize, bglight, bgdark, borderlight, borderdark);
		if (pizzeria)
			panel.setBounds(0, 0, 450, 150);
		else
			panel.setBounds(0, 0, 450, 300);
		add(panel);
		panel.setLayout(null);
		
		LocalisedButton btnNewButton = new LocalisedButton("back.text", this::buttonAction);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.setBounds(10, 11, 85, 23);
		panel.add(btnNewButton);
		
		LocalisedLabel lblNewLabel;
		if (pizzeria)
			lblNewLabel = new LocalisedLabel("pizzeria.text"); 
		else
			lblNewLabel = new LocalisedLabel("personaladdress.text"); 
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 45, 200, 29);
		panel.add(lblNewLabel);
		
		JTextArea address = new JTextArea();
		address.setOpaque(false);
		address.getCaret().setVisible(false);
		address.setFocusable(false);
		address.setBackground(new Color(0,0,0,0));
		address.validate();
		address.setWrapStyleWord(true);
		address.setFont(new Font("Tahoma", Font.PLAIN, 12));
		address.setLineWrap(true);
		address.setEditable(false);
		if (pizzeria)
			address.setText(getLocation(pizzeriaBuilding));
		else
			address.setText(getLocation(customer.getAddress()));	
		
		ThemeRoundPanel outline1 = new ThemeRoundPanel(bordersize, new Color(0,0,0,0), new Color(0,0,0,0), borderlight, borderdark);
		outline1.setBounds(10, 72, 430, 64);
		panel.add(outline1);
		
		JScrollPane scrollPane = new JScrollPane(address);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBounds(10, 72, 430, 64);
		panel.add(scrollPane);
		
		additional = new JTextArea();
		additional.setOpaque(false);
		additional.setBackground(new Color(0,0,0,0));
		additional.setWrapStyleWord(true);
		additional.setFont(new Font("Tahoma", Font.PLAIN, 12));
		additional.setLineWrap(true);
		
		ThemeRoundPanel outline2 = new ThemeRoundPanel(bordersize, new Color(0,0,0,0), new Color(0,0,0,0), borderlight, borderdark);
		outline2.setBounds(10, 174, 430, 115);
		panel.add(outline2);
		
		JScrollPane scrollPane_1 = new JScrollPane(additional);
		scrollPane_1.getViewport().setOpaque(false);
		scrollPane_1.setBounds(10, 174, 430, 115);
		panel.add(scrollPane_1);
		
		LocalisedLabel lblPapildusInfonav = new LocalisedLabel("additional.text"); 
		lblPapildusInfonav.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPapildusInfonav.setBounds(10, 147, 245, 29);
		panel.add(lblPapildusInfonav);
		
	}
	public void buttonAction() {
		MainView.customer.setExtraInfo(additional.getText());
		GlassPanePopup.closePopupLast();
	}
    public String getLocation(GeoPosition pos) {
        String body = HttpRequest.get("https://nominatim.openstreetmap.org/reverse?lat=" + pos.getLatitude() + "&lon=" + pos.getLongitude() + "&format=json").body();
        JSONObject json = new JSONObject(body);
        try {
        	return json.getString("display_name");
        } catch(JSONException e) {}
        return "Unknown Location.";
    }
}
