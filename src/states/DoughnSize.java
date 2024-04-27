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
import javax.swing.ImageIcon;
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
import objects.IngredientHolder;
import objects.PriorityIngredient;
import raven.glasspanepopup.GlassPanePopup;

@SuppressWarnings("serial")
public class DoughnSize extends JPanel {
	private CartItem cartitem;
	private int bordersize;
	private JPanel sizepanelscroll, doughpanelscroll;
	private JLabel price, lblAddS, lblAddD;
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
		
		lblAddS = new JLabel("");
		lblAddS.setHorizontalAlignment(SwingConstants.LEFT);
		lblAddS.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddS.setBounds(10, 150, 113, 21);
		add(lblAddS);
		
		lblAddD = new JLabel();
		lblAddD.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAddD.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddD.setBounds(357, 150, 113, 21);
		add(lblAddD);
		
		LocalisedLabel lblDoughType = new LocalisedLabel("dough.text");
		lblDoughType.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDoughType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDoughType.setBounds(357, 39, 113, 21);
		add(lblDoughType);
		
		JPanel sizepanel = new ThemeRoundPanel(20, new Color(10,10,10, 10), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200,200,200));
		sizepanel.setLayout(null);
		
		sizepanelscroll = new JPanel(new FlowLayout(FlowLayout.LEFT));	
		sizepanelscroll.setOpaque(false);
		sizepanelscroll.setBackground(new Color(0,0,0,0));
		sizepanelscroll.setPreferredSize(new Dimension(0, 0));
		
		JScrollPane sizepanelScr = new JScrollPane(sizepanelscroll);
		sizepanelScr.setBorder(BorderFactory.createEmptyBorder());
		sizepanelScr.setOpaque(false);
		sizepanelScr.getViewport().setOpaque(false);              
		sizepanelScr.setVerticalScrollBar(verticalScrollBar);
		sizepanelScr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sizepanelScr.setBounds(0, 0, 221, 70);
        
        sizepanel.add(sizepanelScr);
        sizepanel.setBounds(10, 65, 221, 70);
		add(sizepanel);
		
		JPanel doughpanel = new ThemeRoundPanel(20, new Color(10,10,10, 10), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200,200,200));
		doughpanel.setBounds(249, 65, 221, 70);
		add(doughpanel);
		doughpanel.setLayout(null);
		
		doughpanelscroll = new JPanel(new FlowLayout(FlowLayout.LEFT));	
		doughpanelscroll.setOpaque(false);
		doughpanelscroll.setBackground(new Color(0,0,0,0));
		doughpanelscroll.setPreferredSize(new Dimension(0, 0));
		
		JScrollPane doughPanelScr = new JScrollPane(doughpanelscroll);
		doughPanelScr.setBorder(BorderFactory.createEmptyBorder());
		doughPanelScr.setOpaque(false);
		doughPanelScr.getViewport().setOpaque(false);              
        doughPanelScr.setVerticalScrollBar(verticalScrollBar);
        doughPanelScr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        doughPanelScr.setBounds(0, 0, 221, 70);
        
        doughpanel.add(doughPanelScr);
		
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
		sizepanelscroll.add(new PriorityIngredient("20 cm", "size", new ImageIcon(LoginWindow.class.getResource("/img/dough20.png")), 20, new Color(100, 100, 100, 50), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200, 200, 200), true, () -> setSize(sizepanelscroll, "20 cm"), () -> editLabel(lblAddS, "* 20 cm"), () -> editLabel(lblAddS, "")));
		sizepanelscroll.add(new PriorityIngredient("30 cm", "size", new ImageIcon(LoginWindow.class.getResource("/img/dough30.png")), 20, new Color(100, 100, 100, 50), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200, 200, 200), false, () -> setSize(sizepanelscroll, "30 cm"), () -> editLabel(lblAddS, "* 30 cm"), () -> editLabel(lblAddS, "")));
		sizepanelscroll.add(new PriorityIngredient("60 cm", "size", new ImageIcon(LoginWindow.class.getResource("/img/dough60.png")), 20, new Color(100, 100, 100, 50), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200, 200, 200), false, () -> setSize(sizepanelscroll, "60 cm"), () -> editLabel(lblAddS, "* 60 cm"), () -> editLabel(lblAddS, "")));
		doughpanelscroll.add(new PriorityIngredient("thick", "dough", new ImageIcon(LoginWindow.class.getResource("/img/dough.png")), 20, new Color(100, 100, 100, 50), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200, 200, 200), false, () -> setDoughType(doughpanelscroll, new IngredientHolder("dough", "thick.text", "thick", "dough"), "thick"), () -> editLabel(lblAddD, Settings.lang.get("thick.text")+" "+Settings.lang.get("dough.text")+" *"), () -> editLabel(lblAddD, "")));
		doughpanelscroll.add(new PriorityIngredient("regular", "dough", new ImageIcon(LoginWindow.class.getResource("/img/dough.png")), 20, new Color(100, 100, 100, 50), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200, 200, 200), false, () -> setDoughType(doughpanelscroll, new IngredientHolder("dough", "regular.text", "regular", "dough"), "regular"), () -> editLabel(lblAddD, Settings.lang.get("regular.text")+" "+Settings.lang.get("dough.text")+" *"), () -> editLabel(lblAddD, "")));
		doughpanelscroll.add(new PriorityIngredient("thin", "dough", new ImageIcon(LoginWindow.class.getResource("/img/dough.png")), 20, new Color(100, 100, 100, 50), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200, 200, 200), true, () -> setDoughType(doughpanelscroll, new IngredientHolder("dough", "thin.text", "thin", "dough"), "thin"), () -> editLabel(lblAddD, Settings.lang.get("thin.text")+" "+Settings.lang.get("dough.text")+" *"), () -> editLabel(lblAddD, "")));
		for (Component i : sizepanelscroll.getComponents()) {
			if (i instanceof PriorityIngredient) {
				PriorityIngredient temp = (PriorityIngredient)i;
				temp.setAction(true);
			}
		}
		for (Component i : doughpanelscroll.getComponents()) {
			if (i instanceof PriorityIngredient) {
				PriorityIngredient temp = (PriorityIngredient)i;
				temp.setAction(true);
			}
		}
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
	private void setDoughType(JPanel parent, IngredientHolder dough, String exception) {
		for (Component child : parent.getComponents()) {
			if (child instanceof PriorityIngredient && !child.getName().equals(exception)) {
				PriorityIngredient temp = (PriorityIngredient)child;
				temp.enableIngredient(false);
			} else {
					cartitem.getPizzaObj().setDoughType(dough);		
			}
		}
		info();
	}
	private void editLabel(JLabel label, String text) {
		label.setText(text);
	}
	private void info() {
		DecimalFormat df = new DecimalFormat("0.00");
		price.setText("€"+df.format(cartitem.getPizzaObj().getPrice()*(double)cartitem.getAmount()));
	}
}
