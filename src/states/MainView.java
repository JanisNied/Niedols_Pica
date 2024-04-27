package states;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.DecimalFormat;

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
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import org.jdesktop.animation.timing.interpolation.Interpolator;

import animation.EaseInQuad;
import localisation.MainViewTabbedPane;
import localisation.ThemePanel;
import localisation.ThemeRoundPanel;
import main.Global;
import main.Settings;
import objects.IngredientHolder;
import objects.Pizza;
import objects.PriorityIngredient;
import ui.RoundPanel;

public class MainView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel presetpizzascroll, sizepanel, sizepanelScroll,infopanel, doughpanel, doughpanelscroll;
	private JLabel welcomelabel, prdctprc, imglbl, prdctlbl;
	private ThemePanel transitionpanel;
	private JButton orderbutton;
	private int size = 240;
	private JPanel contentPane;
	private JScrollBar verticalScrollBar;
	private PriorityIngredient twenty, thirty, sixty;
	private JSpinner spinner;
	private JTextArea desc;
	// Custom pizza
	private Pizza custom = new Pizza(20, new IngredientHolder("dough", "thin.text", "thin"));
	
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
		contentPane = new ThemePanel(new Color(225, 225, 225), new Color(35, 35, 35));
		contentPane.setBackground(new Color(225, 225, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        transitionpanel = new ThemePanel(Color.WHITE, new Color(72, 72, 72));
        transitionpanel.setBounds(0, 0, 784, 561);
        contentPane.add(transitionpanel);
        transitionpanel.setLayout(null);
        
        welcomelabel = new JLabel("{Welcome.text}, {user}!");
        welcomelabel.setFont(new Font("Tahoma", Font.PLAIN, 33));
        welcomelabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomelabel.setBounds(182, 230, 420, 100);
        transitionpanel.add(welcomelabel);

        ThemePanel mainpanel = new ThemePanel(new Color(225, 225, 225), new Color(35, 35, 35));
        mainpanel.setBounds(5, 5, 774, 551);
        contentPane.add(mainpanel);
		mainpanel.setLayout(null);
		
		MainViewTabbedPane tabbedPane = new MainViewTabbedPane(4, new String[]{"premade.text", "custom.text", "kitchen.text", "cart.text"});
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
		
		JScrollPane scrollPanePreset = new JScrollPane(presetpizzascroll);
		scrollPanePreset.setBorder(BorderFactory.createEmptyBorder());
		scrollPanePreset.setOpaque(false);
		scrollPanePreset.getViewport().setOpaque(false);
        
		verticalScrollBar = new JScrollBar(JScrollBar.VERTICAL);
        verticalScrollBar.setPreferredSize(new Dimension(0, 0));
        
        scrollPanePreset.setVerticalScrollBar(verticalScrollBar);
        scrollPanePreset.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanePreset.setBounds(7, 11, 749, 501);
        
		presetpizzascrollpanel.add(scrollPanePreset);
		
		// END OF PRESET PIZZA TAB
		
		// START OF CUSTOM PIZZA TAB
		JPanel custompizza = new JPanel();
		tabbedPane.addTab("Pielāgota Pica", null, custompizza, null);
		custompizza.setLayout(null);
		
		ThemeRoundPanel pizzaimg = new ThemeRoundPanel(20, new Color(50, 50, 50, 20), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200,200,200));
		pizzaimg.setBounds(10, 11, 250, 250);
		custompizza.add(pizzaimg);
		pizzaimg.setLayout(null);
      
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
        
        JLabel bbq = new JLabel("");
        bbq.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/bbq.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        bbq.setHorizontalAlignment(SwingConstants.CENTER);
        bbq.setBounds(0, 0, 250, 250);
        pizzaimg.add(bbq);
        
        JLabel ketchup = new JLabel("");
        ketchup.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/ketchup.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        ketchup.setHorizontalAlignment(SwingConstants.CENTER);
        ketchup.setBounds(0, 0, 250, 250);
        pizzaimg.add(ketchup);
        
        JLabel dough = new JLabel(""); 
        dough.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/dough.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        dough.setHorizontalAlignment(SwingConstants.CENTER);
        dough.setBounds(0, 0, 250, 250);
        pizzaimg.add(dough);
        
        
        JPanel descPanel = new ThemeRoundPanel(20, new Color(10,10,10, 10), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200,200,200));
        descPanel.setBounds(10, 272, 250, 193);
        custompizza.add(descPanel);
        
		desc = new JTextArea();
		desc.setEditable(false);
		desc.setOpaque(false);
		desc.setWrapStyleWord(true);
		desc.setLineWrap(true);
		desc.setFocusable(false);
		desc.setBackground(new Color(0,0,0,0));
		desc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		desc.setBounds(10, 272, 250, 193);
		desc.setText(custom.description());
		custompizza.add(desc);
		
		orderbutton = new JButton("Add to Order - $99.99");
		orderbutton.setBounds(86, 476, 174, 36);
		custompizza.add(orderbutton);
		
		spinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
		spinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                updateDesc();
            }
        });
        ((JSpinner.DefaultEditor)spinner.getEditor()).getTextField().setEditable(false);
		spinner.setBounds(10, 476, 66, 36);
		custompizza.add(spinner);
		
		// SIZE PANEL
		JLabel sizelbl = new JLabel("Izmērs");
		sizelbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		sizelbl.setBounds(270, 11, 119, 36);
		custompizza.add(sizelbl);
		
		verticalScrollBar = new JScrollBar(JScrollBar.VERTICAL);
        verticalScrollBar.setPreferredSize(new Dimension(0, 0));
        
		sizepanel = new ThemeRoundPanel(20, new Color(10,10,10, 10), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200,200,200));
		sizepanel.setBounds(270, 41, 221, 70);
		sizepanel.setLayout(null);
		
		custompizza.add(sizepanel);
		
		sizepanelScroll = new JPanel(new FlowLayout(FlowLayout.LEFT));	
		sizepanelScroll.setOpaque(false);
		sizepanelScroll.setBackground(new Color(0,0,0,0));
		sizepanelScroll.setPreferredSize(new Dimension(0, 0));
		
		JScrollPane scrollPaneSize = new JScrollPane(sizepanelScroll);
		scrollPaneSize.setBorder(BorderFactory.createEmptyBorder());
		scrollPaneSize.setOpaque(false);
		scrollPaneSize.getViewport().setOpaque(false);
        
        scrollPaneSize.setVerticalScrollBar(verticalScrollBar);
        scrollPaneSize.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneSize.setBounds(0, 0, 221, 70);
        
        sizepanel.add(scrollPaneSize);
        // SIZE PANEL END
        
		// DOUGH PANEL
		doughpanel = new ThemeRoundPanel(20, new Color(10,10,10, 10), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200,200,200));
		doughpanel.setBounds(270, 158, 221, 70);
		custompizza.add(doughpanel);
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
        
        
        JLabel lblMkla = new JLabel("Mīkla");
		lblMkla.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMkla.setBounds(270, 128, 119, 36);
		custompizza.add(lblMkla);
		// DOUGHPANEL END
		JLabel lblMrce = new JLabel("Mērce");
		lblMrce.setHorizontalAlignment(SwingConstants.TRAILING);
		lblMrce.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMrce.setBounds(640, 11, 119, 36);
		custompizza.add(lblMrce);
		
		JPanel saucepanel = new JPanel();
		saucepanel.setBounds(538, 41, 221, 70);
		custompizza.add(saucepanel);
		
		JPanel cheesepanel = new JPanel();
		cheesepanel.setBounds(538, 158, 221, 70);
		custompizza.add(cheesepanel);
		
		JLabel lblSiers = new JLabel("Siers");
		lblSiers.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSiers.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSiers.setBounds(640, 128, 119, 36);
		custompizza.add(lblSiers);
		
		JPanel meatpanel = new JPanel();
		meatpanel.setBounds(538, 269, 221, 70);
		custompizza.add(meatpanel);
		
		JLabel lblMeat = new JLabel("Gaļa");
		lblMeat.setHorizontalAlignment(SwingConstants.TRAILING);
		lblMeat.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMeat.setBounds(640, 239, 119, 36);
		custompizza.add(lblMeat);
		
		JPanel additivepanel = new JPanel();
		additivepanel.setBounds(538, 380, 221, 70);
		custompizza.add(additivepanel);
		
		JLabel lblMrce_1_1_1 = new JLabel("Piedevas");
		lblMrce_1_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblMrce_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMrce_1_1_1.setBounds(640, 350, 119, 36);
		custompizza.add(lblMrce_1_1_1);
		
		// INFO PANEL
		infopanel = new JPanel();
		infopanel.setBounds(270, 269, 221, 181);
		custompizza.add(infopanel);
		infopanel.setLayout(null);
		
		prdctlbl = new JLabel("LabelForProduct");
		prdctlbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		prdctlbl.setHorizontalAlignment(SwingConstants.CENTER);
		prdctlbl.setBounds(10, 11, 201, 14);
		infopanel.add(prdctlbl);
		
		JPanel img = new ThemeRoundPanel(20, new Color(10,10,10, 10), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200,200,200));
		img.setBounds(60, 40, 100, 100);
		infopanel.add(img);
		img.setLayout(null);
		
		imglbl = new JLabel("");
		imglbl.setHorizontalAlignment(SwingConstants.CENTER);
		imglbl.setBounds(0, 0, 100, 100);
		img.add(imglbl);
		
		prdctprc = new JLabel("$99.99");
		prdctprc.setHorizontalAlignment(SwingConstants.CENTER);
		prdctprc.setFont(new Font("Tahoma", Font.BOLD, 14));
		prdctprc.setBounds(10, 156, 201, 14);
		infopanel.add(prdctprc);
		
		// END OF CUSTOM
		
		JPanel cart = new JPanel();
		tabbedPane.addTab("Grozs", null, cart, null);
		cart.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 47, 245, 465);
		cart.add(panel_1);
		
		JLabel grozslbl = new JLabel("Grozs");
		grozslbl.setHorizontalAlignment(SwingConstants.CENTER);
		grozslbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		grozslbl.setBounds(10, 11, 245, 34);
		cart.add(grozslbl);
		
		JPanel bakery = new JPanel();
		tabbedPane.addTab("Virtuve", null, bakery, null);
		TimingTargetAdapter timingTarget = new TimingTargetAdapter() {
			@Override
	        public void timingEvent(float fraction) {
				int newY = (int) (transitionpanel.getY() + (-600 - transitionpanel.getY()) * fraction);
				transitionpanel.setBounds(0, newY, 784, 561);
			}
		};
		Animator animator = new Animator(4000, timingTarget);
		animator.setEndBehavior(Animator.EndBehavior.HOLD);
		Interpolator interpolator = new EaseInQuad();
		animator.setInterpolator(interpolator);
		animator.start();
		addPizza();
		addIngredients();
		updateDesc();
	}
	private void addPizza() {
		presetpizzascroll.add(new PizzaPanel(20, new Color(50, 50, 50, 50), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200, 200, 200)));
	}
	private void addIngredients() {
		infopanel.setVisible(false);
		twenty = new PriorityIngredient("20 cm", "size", "20 cm", 20, new Color(100, 100, 100, 50), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200, 200, 200), true, () -> disableOtherDough(sizepanelScroll, "20 cm"), () -> info("20 cm", "20 cm", null, Settings.prices.get("20")), this::infoOff);
		thirty = new PriorityIngredient("30 cm", "size", "30 cm", 20, new Color(100, 100, 100, 50), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200, 200, 200), false, () -> disableOtherDough(sizepanelScroll, "30 cm"), () -> info("30 cm", "30 cm", null, Settings.prices.get("30")), this::infoOff);
		sixty = new PriorityIngredient("60 cm", "size", "60 cm", 20, new Color(100, 100, 100, 50), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200, 200, 200), false, () -> disableOtherDough(sizepanelScroll, "60 cm"), () -> info("60 cm", "60 cm", null, Settings.prices.get("60")), this::infoOff);
		sizepanelScroll.add(twenty);
		sizepanelScroll.add(thirty);
		sizepanelScroll.add(sixty);
		doughpanelscroll.add(new PizzaPanel(20, new Color(50, 50, 50, 50), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200, 200, 200)));
	
	}
	private void disableOtherDough(JPanel parent, String exception) {
		for (Component child : parent.getComponents()) {
			if (child instanceof PriorityIngredient && !child.getName().equals(exception)) {
				PriorityIngredient temp = (PriorityIngredient)child;
				temp.enableIngredient(false);
			} else {
				custom.setDoughSize(Integer.parseInt(child.getName().substring(0, 2)));
			}
		}
		updateDesc();
	}
	private void updateDesc() {
		Integer val = (Integer) spinner.getValue();
		double spinnerValdb = val.doubleValue();
		DecimalFormat df = new DecimalFormat("0.00");
		desc.setText(custom.description());
		orderbutton.setText(Settings.lang.get("addorder.text")+" - €"+df.format(custom.getPrice() * spinnerValdb));
	}
	private void info(String title, String text, ImageIcon img, double price) {
		DecimalFormat df = new DecimalFormat("0.00");
		infopanel.setVisible(true);
		prdctlbl.setText(title);
		if (text != null) {
			imglbl.setText(text);
		} else {
			imglbl.setText("");
			imglbl.setIcon(img);
		}
		prdctprc.setText("€"+df.format(price));
	}
	private void infoOff() {
		infopanel.setVisible(false);
	}
}
