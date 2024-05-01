package objects;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.json.JSONException;
import org.json.JSONObject;
import org.jxmapviewer.viewer.GeoPosition;

import com.github.kevinsawicki.http.HttpRequest;

import localisation.LocalisedButton;
import localisation.LocalisedLabel;
import localisation.ThemeRoundPanel;
import main.Settings;
import raven.glasspanepopup.GlassPanePopup;

@SuppressWarnings("serial")
public class InfoPanel extends JPanel {
	public InfoPanel(Customer customer, int bordersize, Color bglight, Color bgdark, Color borderlight, Color borderdark) {
		Object[] column = {Settings.lang.get("data.text"), Settings.lang.get("value.text")};
		Object[][] data = null;
		if (customer.getAddress() == null && !customer.getTypeofdelivery().equals("home")) {
						data = new Object[][]{
						   {Settings.lang.get("name.text"), customer.getName()}, 
	    				   {Settings.lang.get("surname.text"), customer.getSurname()}, 
	    				   {Settings.lang.get("phone.text"), customer.getNumber()},
	    				   {Settings.lang.get("paymenttype.text"), Settings.lang.get(customer.getTypeofpayment()+"payment.text")},
	    				   {Settings.lang.get("deliverytype.text"), Settings.lang.get(customer.getTypeofdelivery()+"delivery.text")}};
		}
		else {
			data = new Object[][]{
				   {Settings.lang.get("name.text"), customer.getName()}, 
				   {Settings.lang.get("surname.text"), customer.getSurname()}, 
				   {Settings.lang.get("phone.text"), customer.getNumber()},
				   {Settings.lang.get("paymenttype.text"), Settings.lang.get(customer.getTypeofpayment()+"payment.text")},
				   {Settings.lang.get("deliverytype.text"), Settings.lang.get(customer.getTypeofdelivery()+"delivery.text")},
				   {Settings.lang.get("addresstitle.text"), getAddress(customer.getAddress())},
				   {Settings.lang.get("additionaltitle.text"), customer.getExtraInfo()}
				   };
		}
		DefaultTableModel model = new DefaultTableModel(data, column) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        };
//        BoxItem[] choices = {new BoxItem("card", Settings.lang.get("cardpayment.text")), new BoxItem("money", Settings.lang.get("moneypayment.text"))};
	    JTable customertable = new JTable(model);
	    customertable.setDefaultRenderer(Object.class, new TooltipCellRenderer());
	      
		setLayout(null);
		setPreferredSize(new Dimension(450, 235));
		setOpaque(false);
		
		ThemeRoundPanel panel = new ThemeRoundPanel(bordersize, bglight, bgdark, borderlight, borderdark);
		panel.setBounds(0, 0, 450, 235);
		add(panel);
		panel.setLayout(null);
		
		LocalisedButton btnNewButton = new LocalisedButton("back.text", this::buttonAction);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.setBounds(10, 11, 85, 23);
		panel.add(btnNewButton);
		
		LocalisedLabel lblNewLabel = new LocalisedLabel("orderinfo.text");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 45, 200, 29);
		panel.add(lblNewLabel);
		
		// set properties
		ThemeRoundPanel outline = new ThemeRoundPanel(bordersize, new Color(0,0,0,0), new Color(0,0,0,0), borderlight, borderdark);
		outline.setBounds(10, 72, 430, 150);
		panel.add(outline);
		
		JScrollPane scrollPane = new JScrollPane(customertable);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBounds(10, 72, 430, 150);
		panel.add(scrollPane);
	}
	private void buttonAction() {
		GlassPanePopup.closePopupLast();
	}
	public String getAddress(GeoPosition pos) {
        String body = HttpRequest.get("https://nominatim.openstreetmap.org/reverse?lat=" + pos.getLatitude() + "&lon=" + pos.getLongitude() + "&format=json").body();
        JSONObject json = new JSONObject(body);
        try {
        	return json.getString("display_name");
        } catch(JSONException e) {}
        return "Unknown Location.";
    }
	class TooltipCellRenderer extends DefaultTableCellRenderer {
	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	        if (component instanceof JComponent) {
	            JComponent jComponent = (JComponent) component;
	            jComponent.setToolTipText(value != null ? value.toString() : null);
	        }
	        return component;
	    }
}
}
