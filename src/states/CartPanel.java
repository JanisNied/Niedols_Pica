package states;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import org.jdesktop.animation.timing.interpolation.Interpolator;

import animation.EaseInQuad;
import localisation.LocalisedLabel;
import localisation.ThemeRoundPanel;
import main.Settings;
import objects.CartItem;
import objects.IngredientHolder;
import ui.RoundPanel;

public class CartPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private int bordersize, size = 80;
	private Color bglight, bgdark, borderlight, borderdark;
	private RoundPanel pizza;
	private CartItem item;
//	private DecimalFormat df;
	private JTextArea desc, descpizza;
	private boolean isButtonPressed = false, isAnimatorRunning = false;
	private Animator animator;
	private JButton btnNewButton;
	private JPanel panel;
	private JLabel price;
	
	public CartPanel(CartItem item, int bordersize, Color bglight, Color bgdark, Color borderlight, Color borderdark) {
		this.bglight = bglight;
		this.bgdark = bgdark;
		this.borderlight = borderlight;
		this.borderdark = borderdark;
		this.bordersize = bordersize;
		this.item = item;
		
        
		TimingTargetAdapter timingTarget = new TimingTargetAdapter() {
		    @Override
		    public void timingEvent(float fraction) {
		        int targetHeight = isButtonPressed ? 193 : 100; 
		        int newY = (int) (CartPanel.this.getPreferredSize().getHeight() + (targetHeight - CartPanel.this.getPreferredSize().getHeight()) * fraction);
		        panel.setBounds(0,0,235,newY);
		        btnNewButton.setBounds(185, newY-20, 47, 15);
		        CartPanel.this.setPreferredSize(new Dimension(235, newY));
		        CartPanel.this.revalidate();
		    }
		};

		animator = new Animator(1000, timingTarget);
		animator.setEndBehavior(Animator.EndBehavior.HOLD);
		Interpolator interpolator = new EaseInQuad();
		animator.setInterpolator(interpolator);
		
		animator.addTarget(new TimingTargetAdapter() {
		    @Override
		    public void end() {
		        isAnimatorRunning = false; 
		    }
		});
	
        setLayout(null);
        setPreferredSize(new Dimension(235, 100));
        setOpaque(false);
        
        pizza = new RoundPanel(20, new Color(0,0,0,0), new Color(0,0,0,0));
        pizza.setBounds(0, 0, 100, 100);
        add(pizza);
        pizza.setLayout(null);
        
        JLabel bbq = new JLabel("");
        bbq.setName("bbqsauce");
        bbq.setIcon(new ImageIcon(new ImageIcon(CartPanel.class.getResource("/img/layers/bbq.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        bbq.setHorizontalAlignment(SwingConstants.CENTER);
        bbq.setBounds(0, 0, 100, 100);
        pizza.add(bbq);
        
        JLabel chicken = new JLabel("");
        chicken.setName("chicken");
        chicken.setIcon(new ImageIcon(new ImageIcon(CartPanel.class.getResource("/img/layers/chicken.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        chicken.setHorizontalAlignment(SwingConstants.CENTER);
        chicken.setBounds(0, 0, 100, 100);
        pizza.add(chicken);
        
        JLabel ham = new JLabel("");
        ham.setName("ham");
        ham.setIcon(new ImageIcon(new ImageIcon(CartPanel.class.getResource("/img/layers/ham.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        ham.setHorizontalAlignment(SwingConstants.CENTER);
        ham.setBounds(0, 0, 100, 100);
        pizza.add(ham);
        
        JLabel salami = new JLabel("");
        salami.setName("salami");
        salami.setIcon(new ImageIcon(new ImageIcon(CartPanel.class.getResource("/img/layers/salami.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        salami.setHorizontalAlignment(SwingConstants.CENTER);
        salami.setBounds(0, 0, 100, 100);
        pizza.add(salami);
        
        JLabel jalapeno = new JLabel("");
        jalapeno.setName("jalapeno");
        jalapeno.setIcon(new ImageIcon(new ImageIcon(CartPanel.class.getResource("/img/layers/jalapeno.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        jalapeno.setHorizontalAlignment(SwingConstants.CENTER);
        jalapeno.setBounds(0, 0, 100, 100);
        pizza.add(jalapeno);
        
        JLabel mushroom = new JLabel("");
        mushroom.setName("mushroom");
        mushroom.setIcon(new ImageIcon(new ImageIcon(CartPanel.class.getResource("/img/layers/mushroom.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        mushroom.setHorizontalAlignment(SwingConstants.CENTER);
        mushroom.setBounds(0, 0, 100, 100);
        pizza.add(mushroom);
        
        JLabel pineapple = new JLabel("");
        pineapple.setName("pineapple");
        pineapple.setIcon(new ImageIcon(new ImageIcon(CartPanel.class.getResource("/img/layers/pineapple.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        pineapple.setHorizontalAlignment(SwingConstants.CENTER);
        pineapple.setBounds(0, 0, 100, 100);
        pizza.add(pineapple);
        
        JLabel pickle = new JLabel("");
        pickle.setName("pickles");
        pickle.setIcon(new ImageIcon(new ImageIcon(CartPanel.class.getResource("/img/layers/pickle.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        pickle.setHorizontalAlignment(SwingConstants.CENTER);
        pickle.setBounds(0, 0, 100, 100);
        pizza.add(pickle);
        
        JLabel tomato = new JLabel("");
        tomato.setName("tomato");
        tomato.setIcon(new ImageIcon(new ImageIcon(CartPanel.class.getResource("/img/layers/tomato.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        tomato.setHorizontalAlignment(SwingConstants.CENTER);
        tomato.setBounds(0, 0, 100, 100);
        pizza.add(tomato);
        
        JLabel onion = new JLabel("");
        onion.setName("onion");
        onion.setIcon(new ImageIcon(new ImageIcon(CartPanel.class.getResource("/img/layers/onion.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        onion.setHorizontalAlignment(SwingConstants.CENTER);
        onion.setBounds(0, 0, 100, 100);
        pizza.add(onion);
           
        JLabel mozzarella = new JLabel("");
        mozzarella.setName("mozzarella");
        mozzarella.setIcon(new ImageIcon(new ImageIcon(CartPanel.class.getResource("/img/layers/mozzarellacheese.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        mozzarella.setHorizontalAlignment(SwingConstants.CENTER);
        mozzarella.setBounds(0, 0, 100, 100);
        pizza.add(mozzarella);
        
        JLabel bluecheese = new JLabel("");
        bluecheese.setName("bluecheese");
        bluecheese.setIcon(new ImageIcon(new ImageIcon(CartPanel.class.getResource("/img/layers/bluecheese.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        bluecheese.setHorizontalAlignment(SwingConstants.CENTER);
        bluecheese.setBounds(0, 0, 100, 100);
        pizza.add(bluecheese);
        
        JLabel normalcheese = new JLabel("");
        normalcheese.setName("normal");
        normalcheese.setIcon(new ImageIcon(new ImageIcon(CartPanel.class.getResource("/img/layers/normalcheese.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        normalcheese.setHorizontalAlignment(SwingConstants.CENTER);
        normalcheese.setBounds(0, 0, 100, 100);
        pizza.add(normalcheese);
        
        JLabel ketchup = new JLabel("");
        ketchup.setName("tomatosauce");
        ketchup.setIcon(new ImageIcon(new ImageIcon(CartPanel.class.getResource("/img/layers/ketchup.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        ketchup.setHorizontalAlignment(SwingConstants.CENTER);
        ketchup.setBounds(0, 0, 100, 100);
        pizza.add(ketchup);
        
        JLabel dough = new JLabel("");
        dough.setName("dough");
        dough.setIcon(new ImageIcon(new ImageIcon(CartPanel.class.getResource("/img/layers/dough.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        dough.setHorizontalAlignment(SwingConstants.CENTER);
        dough.setBounds(0, 0, 100, 100);
        pizza.add(dough);
        
        LocalisedLabel pizzatitle = new LocalisedLabel(item.getPizzaObj().getNickname());
        pizzatitle.setFont(new Font("Tahoma", Font.BOLD, 12));
        pizzatitle.setBounds(105, 0, 123, 30);
        
        desc = new JTextArea();
        desc.setOpaque(false);
        desc.getCaret().setVisible(false);
        desc.setFocusable(false);
        desc.setBackground(new Color(0,0,0,0));
        desc.validate();
        desc.setWrapStyleWord(true);
        desc.setFont(new Font("Tahoma", Font.PLAIN, 12));
        desc.setLineWrap(true);
        desc.setText(Settings.lang.get("placedorder.text")+" "+item.getDate()+" "+item.getTime()+"\n"+Settings.lang.get("amount.text")+" "+item.getAmount());
        desc.setEditable(false);
        desc.setBounds(105, 26, 123, 49);
        add(desc);   
        add(pizzatitle); 
        
   
        
        btnNewButton = new JButton("V");
        btnNewButton.addActionListener(e -> button(btnNewButton));
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 8));
        btnNewButton.setBounds(185, 80, 47, 15);
        add(btnNewButton);
        
        descpizza = new JTextArea();
        descpizza.setOpaque(false);
        descpizza.getCaret().setVisible(false);
        descpizza.setFocusable(false);
        descpizza.setBackground(new Color(0,0,0,0));
        descpizza.validate();
        descpizza.setWrapStyleWord(true);
        descpizza.setFont(new Font("Tahoma", Font.PLAIN, 12));
        descpizza.setLineWrap(true);
        descpizza.setText(item.getPizzaObj().description(true));
        descpizza.setEditable(false);
        descpizza.setBounds(10, 100, 218, 59);
        add(descpizza);
        
        JScrollPane scrollPane = new JScrollPane(descpizza);
        scrollPane.setBounds(10, 100, 218, 59);
        add(scrollPane);
        
        panel = new ThemeRoundPanel(this.bordersize, this.bglight, this.bgdark, this.borderlight, this.borderdark);
        panel.setOpaque(false);
        panel.setBounds(0, 0, 235, 100);     
        add(panel);
        
        price = new JLabel("Cena: eur19.99");
        price.setFont(new Font("Tahoma", Font.BOLD, 12));
        price.setBounds(10, 170, 113, 21);
        add(price);
        
        JButton btnX = new JButton("X");
        btnX.addActionListener(e -> removal());
        btnX.setForeground(new Color(255, 0, 0));
        btnX.setFont(new Font("Tahoma", Font.PLAIN, 8));
        btnX.setBounds(124, 173, 47, 15);
        add(btnX);
        updateDesc();
	}
	private void removal() {
		MainView.cart.remove(item);
		MainView.updateCart();
	}
	public void updateDesc() {
		desc.setText(Settings.lang.get("placedorder.text")+" "+item.getDate()+" "+item.getTime()+"\n"+Settings.lang.get("amount.text")+" "+item.getAmount());  
		descpizza.setText(item.getPizzaObj().description(true));
		DecimalFormat df = new DecimalFormat("0.00");
		price.setText(Settings.lang.get("price.text")+": €"+df.format(item.getPizzaObj().getPrice()*(double)item.getAmount()));
	}
	private void button(JButton button) {
		if (!isAnimatorRunning) {
			isButtonPressed = !isButtonPressed;
			if (isButtonPressed) {
				button.setText("^");
				isAnimatorRunning = true;
				animator.start();
			} else {
				button.setText("V");
				isAnimatorRunning = true;
				animator.start();
			}
		}
	}
	public void update() {
		Component[] components = pizza.getComponents();
		ArrayList<String> items = new ArrayList<String>();
		for (IngredientHolder i : item.getPizzaObj().ingredients()) {
			items.add(i.getIdentifier());
		}
		for (Component c : components) {
			if (items.contains(c.getName()) || c.getName().equals("dough")) {
				c.setVisible(true);
			} else
				c.setVisible(false);
		}
	}
}
