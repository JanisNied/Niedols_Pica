package states;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import localisation.LocalisedButton;
import localisation.ThemeRoundPanel;
import raven.glasspanepopup.GlassPanePopup;

@SuppressWarnings("serial")
public class History extends JPanel {
	public JPanel scroll;
	public History() {
		setPreferredSize(new Dimension(300 ,450));
		setLayout(null);
		setOpaque(false);
		
		JPanel bg = new ThemeRoundPanel(20, new Color(255, 255, 255, 250), new Color(70,70,70, 250), new Color(0,0,0), new Color(200, 200, 200));
		bg.setBounds(0,0,300,450);
		bg.setLayout(null);
		add(bg);
		
		JLabel lblNewLabel = new JLabel("Vēsture");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(12, 11, 275, 28);
		bg.add(lblNewLabel);
		
		JPanel panel = new ThemeRoundPanel(20, new Color(255, 255, 255, 250), new Color(70,70,70, 250), new Color(0,0,0), new Color(200, 200, 200));
		panel.setBounds(15, 50, 270, 358);
		panel.setLayout(null);
		bg.add(panel);
		
		LocalisedButton btnNewButton = new LocalisedButton("exit.text",this::blowUp);
		btnNewButton.setBounds(105, 419, 89, 23);
		bg.add(btnNewButton);
		
		
		scroll = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		scroll.setOpaque(false);
		scroll.setBackground(new Color(0,0,0,0));
		scroll.setPreferredSize(new Dimension(0, 1000));
		
		JScrollPane scrollPanePreset = new JScrollPane(scroll);
		scrollPanePreset.setBorder(BorderFactory.createEmptyBorder());
		scrollPanePreset.setOpaque(false);
		scrollPanePreset.getViewport().setOpaque(false);
        scrollPanePreset.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanePreset.setBounds(0,0, 270, 358);
        
		panel.add(scrollPanePreset);
	}
	private void blowUp() {
		GlassPanePopup.closePopupLast();
	}
}
