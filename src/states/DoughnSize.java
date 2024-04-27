package states;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import localisation.LocalisedButton;
import localisation.LocalisedLabel;
import localisation.ThemeRoundPanel;
import main.Settings;
import objects.CartItem;
import objects.PriorityIngredient;
import raven.glasspanepopup.GlassPanePopup;

@SuppressWarnings("serial")
public class DoughnSize extends JPanel {
	private CartItem cartitem;
	private int bordersize;
	private JPanel sizepanelscroll;
	private JLabel price;
	private JScrollBar verticalScrollBar;
	private Color bglight, bgdark, borderlight, borderdark;
	private LocalisedButton btnAtgriezties;

	public DoughnSize(CartItem cartitem, int bordersize, Color bglight, Color bgdark, Color borderlight, Color borderdark) {
		this.cartitem = cartitem;
		this.bglight = bglight;
		this.bgdark = bgdark;
		this.borderlight = borderlight;
		this.borderdark = borderdark;
		this.bordersize = bordersize;
		verticalScrollBar = new JScrollBar(JScrollBar.VERTICAL);
        verticalScrollBar.setPreferredSize(new Dimension(0, 0));
		setLayout(null);
		setOpaque(false);
		setPreferredSize(new Dimension(480, 200));
		LocalisedLabel lblNewLabel = new LocalisedLabel(cartitem.getPizzaObj().getNickname());
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(10, 7, 460, 21);
		add(lblNewLabel);
		
		LocalisedLabel lblSize = new LocalisedLabel("size.text");
		lblSize.setHorizontalAlignment(SwingConstants.LEFT);
		lblSize.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSize.setBounds(10, 39, 113, 21);
		add(lblSize);
		
		LocalisedLabel lblDoughType = new LocalisedLabel("dough.text");
		lblDoughType.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDoughType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDoughType.setBounds(357, 39, 113, 21);
		add(lblDoughType);
		
		JPanel doughpanel = new ThemeRoundPanel(20, new Color(10,10,10, 10), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200,200,200));
		doughpanel.setLayout(null);
		
		sizepanelscroll = new JPanel(new FlowLayout(FlowLayout.LEFT));	
		sizepanelscroll.setOpaque(false);
		sizepanelscroll.setBackground(new Color(0,0,0,0));
		sizepanelscroll.setPreferredSize(new Dimension(0, 0));
		
		JScrollPane doughPanelScr = new JScrollPane(sizepanelscroll);
		doughPanelScr.setBorder(BorderFactory.createEmptyBorder());
		doughPanelScr.setOpaque(false);
		doughPanelScr.getViewport().setOpaque(false);              
        doughPanelScr.setVerticalScrollBar(verticalScrollBar);
        doughPanelScr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        doughPanelScr.setBounds(0, 0, 221, 70);
        
        doughpanel.add(doughPanelScr);
		doughpanel.setBounds(10, 65, 221, 70);
		add(doughpanel);
		
		JPanel panel_1 = new ThemeRoundPanel(20, new Color(10,10,10, 10), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200,200,200));
		panel_1.setBounds(249, 65, 221, 70);
		add(panel_1);
		
		LocalisedButton btnNewButton = new LocalisedButton("back.text", this::back);
		btnNewButton.setBounds(10, 9, 89, 23);
		add(btnNewButton);
		
		btnAtgriezties = new LocalisedButton("addorder.text", this::addToCart);
		btnAtgriezties.setBounds(160, 146, 159, 43);
		add(btnAtgriezties);
		
		price = new JLabel();
		price.setHorizontalAlignment(SwingConstants.CENTER);
		price.setFont(new Font("Tahoma", Font.PLAIN, 17));
		price.setBounds(10, 25, 460, 21);
		add(price);
		
		addIngredients();
		info();
	}
	@Override
    protected void paintComponent(Graphics g) {
		Color[] theme = theme();
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setColor(theme[0]);
        g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, bordersize, bordersize);
        Shape shape = new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, bordersize, bordersize);
        super.paintComponent(g2d);
        g2d.setColor(theme[1]);
        g2d.draw(shape);

        g2d.dispose();
    }
	
	private Color[] theme() {
		Color[] array = new Color[2];
		switch(Settings.currentSettings.get("theme")) {
			case "light":
				array[0] = bglight;
				array[1] = borderlight;
				break;
			case "dark":
				array[0] = bgdark;
				array[1] = borderdark;
				break;
		}
		return array;
	}
	private void addIngredients() {
		sizepanelscroll.add(new PriorityIngredient("20 cm", "size", "20 cm", 20, new Color(100, 100, 100, 50), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200, 200, 200), true, () -> setSize(sizepanelscroll, "20 cm")));
		sizepanelscroll.add(new PriorityIngredient("30 cm", "size", "30 cm", 20, new Color(100, 100, 100, 50), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200, 200, 200), false, () -> setSize(sizepanelscroll, "30 cm")));
		sizepanelscroll.add(new PriorityIngredient("60 cm", "size", "60 cm", 20, new Color(100, 100, 100, 50), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200, 200, 200), false, () -> setSize(sizepanelscroll, "60 cm")));
	}
	private void addToCart() {
		if (Settings.lang.containsKey(cartitem.getPizzaObj().getNickname()))
			System.out.println("[CART] Forwarded x"+cartitem.getAmount()+" "+Settings.lang.get(cartitem.getPizzaObj().getNickname())+" to cart!");
		else
			System.out.println("[CART] Forwarded x"+cartitem.getAmount()+" "+cartitem.getPizzaObj().getNickname()+" to cart!");
		GlassPanePopup.closePopupLast();
	}
	private void back() {
		GlassPanePopup.closePopupLast();
	}
	private void setSize(JPanel parent, String exception) {
		for (Component child : parent.getComponents()) {
			if (child instanceof PriorityIngredient && !child.getName().equals(exception)) {
				PriorityIngredient temp = (PriorityIngredient)child;
				temp.enableIngredient(false);
			} else {
					cartitem.getPizzaObj().setDoughSize(Integer.parseInt(child.getName().substring(0, 2)));			
			}
		}
		info();
	}
	private void info() {
		DecimalFormat df = new DecimalFormat("0.00");
		price.setText("€"+df.format(cartitem.getPizzaObj().getPrice()*(double)cartitem.getAmount()));
	}
}
