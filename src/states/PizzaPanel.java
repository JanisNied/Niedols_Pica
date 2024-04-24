package states;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import ui.RoundPanel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class PizzaPanel extends RoundPanel {

	private static final long serialVersionUID = 1L;
	private int size = 130;
	/**
	 * Create the panel.
	 */
	public PizzaPanel(int bordersize, Color bg, Color border) {
		super(bordersize, bg, border);
//		setBackground(new Color(255, 255, 255));
        setLayout(null);
        setPreferredSize(new Dimension(360, 155));
        
        RoundPanel pizza = new RoundPanel(20, new Color(0,0,0,0), new Color(0,0,0,0));
        pizza.setBounds(10, 11, 133, 133);
        add(pizza);
        pizza.setLayout(null);
        
        JLabel bbq = new JLabel("");
        bbq.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/bbq.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        bbq.setHorizontalAlignment(SwingConstants.CENTER);
        bbq.setBounds(0, 0, 133, 133);
        pizza.add(bbq);
        
        JLabel chicken = new JLabel("");
        chicken.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/chicken.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        chicken.setHorizontalAlignment(SwingConstants.CENTER);
        chicken.setBounds(0, 0, 133, 133);
        pizza.add(chicken);
        
        JLabel ham = new JLabel("");
        ham.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/ham.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        ham.setHorizontalAlignment(SwingConstants.CENTER);
        ham.setBounds(0, 0, 133, 133);
        pizza.add(ham);
        
        JLabel salami = new JLabel("");
        salami.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/salami.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        salami.setHorizontalAlignment(SwingConstants.CENTER);
        salami.setBounds(0, 0, 133, 133);
        pizza.add(salami);
        
        JLabel jalapeno = new JLabel("");
        jalapeno.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/jalapeno.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        jalapeno.setHorizontalAlignment(SwingConstants.CENTER);
        jalapeno.setBounds(0, 0, 133, 133);
        pizza.add(jalapeno);
        
        JLabel mushroom = new JLabel("");
        mushroom.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/mushroom.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        mushroom.setHorizontalAlignment(SwingConstants.CENTER);
        mushroom.setBounds(0, 0, 133, 133);
        pizza.add(mushroom);
        
        JLabel pineapple = new JLabel("");
        pineapple.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/pineapple.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        pineapple.setHorizontalAlignment(SwingConstants.CENTER);
        pineapple.setBounds(0, 0, 133, 133);
        pizza.add(pineapple);
        
        JLabel pickle = new JLabel("");
        pickle.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/pickle.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        pickle.setHorizontalAlignment(SwingConstants.CENTER);
        pickle.setBounds(0, 0, 133, 133);
        pizza.add(pickle);
        
        JLabel tomato = new JLabel("");
        tomato.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/tomato.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        tomato.setHorizontalAlignment(SwingConstants.CENTER);
        tomato.setBounds(0, 0, 133, 133);
        pizza.add(tomato);
        
        JLabel onion = new JLabel("");
        onion.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/onion.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        onion.setHorizontalAlignment(SwingConstants.CENTER);
        onion.setBounds(0, 0, 133, 133);
        pizza.add(onion);
        
        JLabel ketchup = new JLabel("");
        ketchup.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/ketchup.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        ketchup.setHorizontalAlignment(SwingConstants.CENTER);
        ketchup.setBounds(0, 0, 133, 133);
        pizza.add(ketchup);
        
        JLabel mozzarella = new JLabel("");
        mozzarella.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/mozzarellacheese.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        mozzarella.setHorizontalAlignment(SwingConstants.CENTER);
        mozzarella.setBounds(0, 0, 133, 133);
        pizza.add(mozzarella);
        
        JLabel bluecheese = new JLabel("");
        bluecheese.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/bluecheese.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        bluecheese.setHorizontalAlignment(SwingConstants.CENTER);
        bluecheese.setBounds(0, 0, 133, 133);
        pizza.add(bluecheese);
        
        JLabel normalcheese = new JLabel("");
        normalcheese.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/normalcheese.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        normalcheese.setHorizontalAlignment(SwingConstants.CENTER);
        normalcheese.setBounds(0, 0, 133, 133);
        pizza.add(normalcheese);
        
        JLabel dough = new JLabel("");
        
        dough.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/dough.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        dough.setHorizontalAlignment(SwingConstants.CENTER);
        dough.setBounds(0, 0, 133, 133);
        pizza.add(dough);
        
        JLabel pizzatitle = new JLabel("Cheese Pizza");
        pizzatitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
        pizzatitle.setBounds(153, 11, 197, 30);
        add(pizzatitle);
        
        JTextArea desc = new JTextArea();
        desc.setBackground(new Color(0,0,0,0));
        desc.setWrapStyleWord(true);
        desc.setFont(new Font("Tahoma", Font.PLAIN, 13));
        desc.setLineWrap(true);
        desc.setText("Ingredients: Dough, Cheese: [Normal, Mozzarella, Blue], Ham, Ketchup, Onion");
        desc.setEditable(false);
        desc.setBounds(153, 42, 197, 52);
        add(desc);
        
        JButton addtocart = new JButton("Add to Order");
        addtocart.setBounds(261, 105, 89, 39);
        add(addtocart);
	}
}
