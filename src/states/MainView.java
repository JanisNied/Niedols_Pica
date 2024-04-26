package states;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import localisation.ThemePanel;
import localisation.ThemeRoundPanel;
import main.Global;
import ui.RoundPanel;

public class MainView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel presetpizzascroll;
	private JLabel welcomelabel;
	private ThemePanel transitionpanel;
	private int size = 240;
	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Global.setup();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
//		contentPane = new ThemePanel(new Color(225, 225, 225), new Color(35, 35, 35));
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane = new JPanel();
		contentPane.setBackground(new Color(225, 225, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
//        transitionpanel = new ThemePanel(Color.WHITE, new Color(72, 72, 72));
//        transitionpanel.setBounds(0, 0, 784, 561);
//        contentPane.add(transitionpanel);
//        transitionpanel.setLayout(null);
//        
//        welcomelabel = new JLabel("{Welcome.text}, {user}!");
//        welcomelabel.setFont(new Font("Tahoma", Font.PLAIN, 33));
//        welcomelabel.setHorizontalAlignment(SwingConstants.CENTER);
//        welcomelabel.setBounds(182, 230, 420, 100);
//        transitionpanel.add(welcomelabel);

//        ThemePanel mainpanel = new ThemePanel(new Color(225, 225, 225), new Color(35, 35, 35));
        JPanel mainpanel = new JPanel();
        mainpanel.setBounds(5, 5, 774, 551);
        contentPane.add(mainpanel);
		mainpanel.setLayout(null);
		
//		MainViewTabbedPane tabbedPane = new MainViewTabbedPane(4, new String[]{"premade.text", "custom.text", "kitchen.text", "cart.text"});
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
		tabbedPane.setTabPlacement(JTabbedPane.BOTTOM);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBounds(0, 0, 774, 551);
		mainpanel.add(tabbedPane);
		// PRESET PIZZA TAB
		
		JPanel presetpizza = new JPanel();
		tabbedPane.addTab("Gatavas Picas", null, presetpizza, null);
		presetpizza.setLayout(null);
		
		RoundPanel presetpizzascrollpanel = new RoundPanel(20, new Color(0,0,0,0), new Color(0,0,0,0));
		presetpizzascrollpanel.setBounds(10, 11, 749, 501);
		presetpizzascrollpanel.setLayout(null);
		presetpizza.add(presetpizzascrollpanel);
		
		presetpizzascroll = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		presetpizzascroll.setOpaque(false);
		presetpizzascroll.setBackground(new Color(0,0,0,0));
		presetpizzascroll.setPreferredSize(new Dimension(0, 100));
		
		JScrollPane scrollPane = new JScrollPane(presetpizzascroll);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        
		JScrollBar verticalScrollBar = new JScrollBar(JScrollBar.VERTICAL);
        verticalScrollBar.setPreferredSize(new Dimension(0, 0));
        scrollPane.setVerticalScrollBar(verticalScrollBar);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(7, 11, 749, 501);
        
		presetpizzascrollpanel.add(scrollPane);
		
		// END OF PRESET PIZZA TAB
		
		// START OF CUSTOM PIZZA TAB
		JPanel custompizza = new JPanel();
		tabbedPane.addTab("Pielāgota Pica", null, custompizza, null);
		custompizza.setLayout(null);
		
		ThemeRoundPanel pizzaimg = new ThemeRoundPanel(20, new Color(50, 50, 50, 20), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200,200,200));
		pizzaimg.setBounds(10, 11, 250, 250);
		custompizza.add(pizzaimg);
		pizzaimg.setLayout(null);
		
		JLabel bbq = new JLabel("");
        bbq.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/bbq.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        bbq.setHorizontalAlignment(SwingConstants.CENTER);
        bbq.setBounds(0, 0, 250, 250);
        pizzaimg.add(bbq);
        
        JLabel chicken = new JLabel("");
        chicken.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/chicken.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        chicken.setHorizontalAlignment(SwingConstants.CENTER);
        chicken.setBounds(0, 0, 250, 250);
        pizzaimg.add(chicken);
        
        JLabel ham = new JLabel("");
        ham.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/ham.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        ham.setHorizontalAlignment(SwingConstants.CENTER);
        ham.setBounds(0, 0, 250, 250);
        pizzaimg.add(ham);
        
        JLabel salami = new JLabel("");
        salami.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/salami.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        salami.setHorizontalAlignment(SwingConstants.CENTER);
        salami.setBounds(0, 0, 250, 250);
        pizzaimg.add(salami);
        
        JLabel jalapeno = new JLabel("");
        jalapeno.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/jalapeno.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        jalapeno.setHorizontalAlignment(SwingConstants.CENTER);
        jalapeno.setBounds(0, 0, 250, 250);
        pizzaimg.add(jalapeno);
        
        JLabel mushroom = new JLabel("");
        mushroom.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/mushroom.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        mushroom.setHorizontalAlignment(SwingConstants.CENTER);
        mushroom.setBounds(0, 0, 250, 250);
        pizzaimg.add(mushroom);
        
        JLabel pineapple = new JLabel("");
        pineapple.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/pineapple.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        pineapple.setHorizontalAlignment(SwingConstants.CENTER);
        pineapple.setBounds(0, 0, 250, 250);
        pizzaimg.add(pineapple);
        
        JLabel pickle = new JLabel("");
        pickle.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/pickle.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        pickle.setHorizontalAlignment(SwingConstants.CENTER);
        pickle.setBounds(0, 0, 250, 250);
        pizzaimg.add(pickle);
        
        JLabel tomato = new JLabel("");
        tomato.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/tomato.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        tomato.setHorizontalAlignment(SwingConstants.CENTER);
        tomato.setBounds(0, 0, 250, 250);
        pizzaimg.add(tomato);
        
        JLabel onion = new JLabel("");
        onion.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/onion.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        onion.setHorizontalAlignment(SwingConstants.CENTER);
        onion.setBounds(0, 0, 250, 250);
        pizzaimg.add(onion);
        
        JLabel ketchup = new JLabel("");
        ketchup.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/ketchup.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        ketchup.setHorizontalAlignment(SwingConstants.CENTER);
        ketchup.setBounds(0, 0, 250, 250);
        pizzaimg.add(ketchup);
        
        JLabel mozzarella = new JLabel("");
        mozzarella.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/mozzarellacheese.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        mozzarella.setHorizontalAlignment(SwingConstants.CENTER);
        mozzarella.setBounds(0, 0, 250, 250);
        pizzaimg.add(mozzarella);
        
        JLabel bluecheese = new JLabel("");
        bluecheese.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/bluecheese.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        bluecheese.setHorizontalAlignment(SwingConstants.CENTER);
        bluecheese.setBounds(0, 0, 250, 250);
        pizzaimg.add(bluecheese);
        
        JLabel normalcheese = new JLabel("");
        normalcheese.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/normalcheese.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        normalcheese.setHorizontalAlignment(SwingConstants.CENTER);
        normalcheese.setBounds(0, 0, 250, 250);
        pizzaimg.add(normalcheese);
        
        JLabel dough = new JLabel(""); 
        dough.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/dough.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        dough.setHorizontalAlignment(SwingConstants.CENTER);
        dough.setBounds(0, 0, 250, 250);
        pizzaimg.add(dough);
        
		JTextArea desc = new JTextArea();
		desc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		desc.setBounds(10, 272, 250, 193);
		custompizza.add(desc);
		
		JButton btnNewButton = new JButton("Add to Order");
		btnNewButton.setBounds(86, 476, 174, 36);
		custompizza.add(btnNewButton);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(10, 476, 66, 36);
		custompizza.add(spinner);
		
		
		
		JPanel cart = new JPanel();
		tabbedPane.addTab("Grozs", null, cart, null);
		
		JPanel bakery = new JPanel();
		tabbedPane.addTab("Virtuve", null, bakery, null);
//		TimingTargetAdapter timingTarget = new TimingTargetAdapter() {
//			@Override
//	        public void timingEvent(float fraction) {
//				int newY = (int) (transitionpanel.getY() + (-600 - transitionpanel.getY()) * fraction);
//				transitionpanel.setBounds(0, newY, 784, 561);
//			}
//		};
//		Animator animator = new Animator(6000, timingTarget);
//		animator.setEndBehavior(Animator.EndBehavior.HOLD);
//		Interpolator interpolator = new EaseInQuad();
//		animator.setInterpolator(interpolator);
//		animator.start();
	}
	private void addPizza() {
		presetpizzascroll.add(new PizzaPanel(20, new Color(50, 50, 50, 50), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200, 200, 200)));
	}
	private void changeTheme() {
		Global.themeSwitcher();
		Global.reloadLAF();		
		repaint();
	}
}
