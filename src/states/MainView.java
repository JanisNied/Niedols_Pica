package states;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
import localisation.LocalisedLabel;
import localisation.MainViewTabbedPane;
import localisation.ThemePanel;
import localisation.ThemeRoundPanel;
import main.Global;
import main.Settings;
import objects.CartItem;
import objects.IngredientHolder;
import objects.IngredientPanel;
import objects.Pizza;
import raven.glasspanepopup.GlassPanePopup;
import ui.RoundPanel;

public class MainView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel presetpizzascroll, sizepanel, sizepanelScroll,infopanel, doughpanel, doughpanelscroll, saucepanelscroll, cheesepanelscroll, meatpanelscroll, additivepanelscroll;
	private static JPanel cartpanelscroll;
	private JLabel welcomelabel, prdctprc, imglbl, prdctlbl;
	private ThemePanel transitionpanel;
	private JButton orderbutton;
	private int size = 240;
	private JPanel contentPane;
	private JScrollBar verticalScrollBar;
	private IngredientPanel twenty, thirty, sixty;
	private JSpinner spinner;
	private JTextArea desc;
	public static JScrollPane cartscr;
	private ThemeRoundPanel pizzaimg;
	public static ArrayList<CartItem> cart = new ArrayList<CartItem>();
	// Custom pizza
	private Pizza custom = new Pizza(20, "custompizza.text", new IngredientHolder("dough", "thin.text", "thin", "dough"));
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Global.setup();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
					GlassPanePopup.install(frame);
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
		setResizable(false);
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
		
		MainViewTabbedPane tabbedPane = new MainViewTabbedPane(4, new String[]{"premade.text", "custom.text", "cart.text", "kitchen.text"});
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
		
		pizzaimg = new ThemeRoundPanel(20, new Color(50, 50, 50, 20), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200,200,200));
		pizzaimg.setBounds(10, 11, 250, 250);
		custompizza.add(pizzaimg);
		pizzaimg.setLayout(null);
      
		JLabel bbq = new JLabel("");
        bbq.setName("bbqsauce");
        bbq.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/bbq.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        bbq.setHorizontalAlignment(SwingConstants.CENTER);
        bbq.setBounds(0, 0, 250, 250);
        pizzaimg.add(bbq);
        
        JLabel chicken = new JLabel("");
        chicken.setName("chicken");
        chicken.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/chicken.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        chicken.setHorizontalAlignment(SwingConstants.CENTER);
        chicken.setBounds(0, 0, 250, 250);
        pizzaimg.add(chicken);
        
        JLabel ham = new JLabel("");
        ham.setName("ham");
        ham.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/ham.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        ham.setHorizontalAlignment(SwingConstants.CENTER);
        ham.setBounds(0, 0, 250, 250);
        pizzaimg.add(ham);
        
        JLabel salami = new JLabel("");
        salami.setName("salami");
        salami.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/salami.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        salami.setHorizontalAlignment(SwingConstants.CENTER);
        salami.setBounds(0, 0, 250, 250);
        pizzaimg.add(salami);
        
        JLabel jalapeno = new JLabel("");
        jalapeno.setName("jalapeno");
        jalapeno.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/jalapeno.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        jalapeno.setHorizontalAlignment(SwingConstants.CENTER);
        jalapeno.setBounds(0, 0, 250, 250);
        pizzaimg.add(jalapeno);
        
        JLabel mushroom = new JLabel("");
        mushroom.setName("mushroom");
        mushroom.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/mushroom.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        mushroom.setHorizontalAlignment(SwingConstants.CENTER);
        mushroom.setBounds(0, 0, 250, 250);
        pizzaimg.add(mushroom);
        
        JLabel pineapple = new JLabel("");
        pineapple.setName("pineapple");
        pineapple.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/pineapple.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        pineapple.setHorizontalAlignment(SwingConstants.CENTER);
        pineapple.setBounds(0, 0, 250, 250);
        pizzaimg.add(pineapple);
        
        JLabel pickle = new JLabel("");
        pickle.setName("pickles");
        pickle.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/pickle.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        pickle.setHorizontalAlignment(SwingConstants.CENTER);
        pickle.setBounds(0, 0, 250, 250);
        pizzaimg.add(pickle);
        
        JLabel tomato = new JLabel("");
        tomato.setName("tomato");
        tomato.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/tomato.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        tomato.setHorizontalAlignment(SwingConstants.CENTER);
        tomato.setBounds(0, 0, 250, 250);
        pizzaimg.add(tomato);
        
        JLabel onion = new JLabel("");
        onion.setName("onion");
        onion.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/onion.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        onion.setHorizontalAlignment(SwingConstants.CENTER);
        onion.setBounds(0, 0, 250, 250);
        pizzaimg.add(onion);   
        
        JLabel mozzarella = new JLabel("");
        mozzarella.setName("mozzarella");
        mozzarella.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/mozzarellacheese.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        mozzarella.setHorizontalAlignment(SwingConstants.CENTER);
        mozzarella.setBounds(0, 0, 250, 250);
        pizzaimg.add(mozzarella);
        
        JLabel bluecheese = new JLabel("");
        bluecheese.setName("bluecheese");
        bluecheese.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/bluecheese.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        bluecheese.setHorizontalAlignment(SwingConstants.CENTER);
        bluecheese.setBounds(0, 0, 250, 250);
        pizzaimg.add(bluecheese);
        
        JLabel normalcheese = new JLabel("");
        normalcheese.setName("normal");
        normalcheese.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/normalcheese.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        normalcheese.setHorizontalAlignment(SwingConstants.CENTER);
        normalcheese.setBounds(0, 0, 250, 250);
        pizzaimg.add(normalcheese);
        
        JLabel ketchup = new JLabel("");
        ketchup.setName("tomatosauce");
        ketchup.setIcon(new ImageIcon(new ImageIcon(PizzaPanel.class.getResource("/img/layers/ketchup.png")).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH)));
        ketchup.setHorizontalAlignment(SwingConstants.CENTER);
        ketchup.setBounds(0, 0, 250, 250);
        pizzaimg.add(ketchup);
        
        JLabel dough = new JLabel("");
        dough.setName("dough");
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
		desc.setText(custom.description(true));
		custompizza.add(desc);
		
		orderbutton = new JButton("Add to Order - $99.99");
		orderbutton.addActionListener(e -> sendToCart(custom, (Integer)spinner.getValue()));
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
		LocalisedLabel sizelbl = new LocalisedLabel("size.text");
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
        
        
        LocalisedLabel lblMkla = new LocalisedLabel("dough.text");
		lblMkla.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMkla.setBounds(270, 128, 119, 36);
		custompizza.add(lblMkla);
		// DOUGHPANEL END
		
		
		LocalisedLabel lblMrce = new LocalisedLabel("sauce.text");
		lblMrce.setHorizontalAlignment(SwingConstants.TRAILING);
		lblMrce.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMrce.setBounds(640, 11, 119, 36);
		custompizza.add(lblMrce);
		// SAUCE PANEL
		JPanel saucepanel = new ThemeRoundPanel(20, new Color(10,10,10, 10), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200,200,200));
		saucepanel.setBounds(538, 41, 221, 70);
		custompizza.add(saucepanel);
		
		saucepanel.setLayout(null);
		
		saucepanelscroll = new JPanel(new FlowLayout(FlowLayout.RIGHT));	
		saucepanelscroll.setOpaque(false);
		saucepanelscroll.setBackground(new Color(0,0,0,0));
		saucepanelscroll.setPreferredSize(new Dimension(0, 0));
		
		JScrollPane saucepanelscr = new JScrollPane(saucepanelscroll);
		saucepanelscr.setBorder(BorderFactory.createEmptyBorder());
		saucepanelscr.setOpaque(false);
		saucepanelscr.getViewport().setOpaque(false);              
		saucepanelscr.setVerticalScrollBar(verticalScrollBar);
		saucepanelscr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		saucepanelscr.setBounds(0, 0, 221, 70);
        
		saucepanel.add(saucepanelscr);
		// SAUCE PANEL END
		
		// CHEESE PANEL START
		JPanel cheesepanel = new ThemeRoundPanel(20, new Color(10,10,10, 10), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200,200,200));
		cheesepanel.setBounds(538, 158, 221, 70);
		custompizza.add(cheesepanel);
		
		cheesepanel.setLayout(null);
		
		cheesepanelscroll = new JPanel(new FlowLayout(FlowLayout.RIGHT));	
		cheesepanelscroll.setOpaque(false);
		cheesepanelscroll.setBackground(new Color(0,0,0,0));
		cheesepanelscroll.setPreferredSize(new Dimension(0, 0));
		
		JScrollPane cheesepanelscr = new JScrollPane(cheesepanelscroll);
		cheesepanelscr.setBorder(BorderFactory.createEmptyBorder());
		cheesepanelscr.setOpaque(false);
		cheesepanelscr.getViewport().setOpaque(false);              
		cheesepanelscr.setVerticalScrollBar(verticalScrollBar);
		cheesepanelscr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		cheesepanelscr.setBounds(0, 0, 221, 70);
        
		cheesepanel.add(cheesepanelscr);
		
		LocalisedLabel lblSiers = new LocalisedLabel("cheese.text");
		lblSiers.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSiers.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSiers.setBounds(640, 128, 119, 36);
		custompizza.add(lblSiers);
		
		// CHEESE PANEL END
		
		// MEAT PANEL START
		JPanel meatpanel = new ThemeRoundPanel(20, new Color(10,10,10, 10), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200,200,200));
		meatpanel.setBounds(538, 269, 221, 70);
		custompizza.add(meatpanel);
		meatpanel.setLayout(null);
		
		meatpanelscroll = new JPanel(new FlowLayout(FlowLayout.RIGHT));	
		meatpanelscroll.setOpaque(false);
		meatpanelscroll.setBackground(new Color(0,0,0,0));
		meatpanelscroll.setPreferredSize(new Dimension(0, 0));
		
		JScrollPane meatpanelscr = new JScrollPane(meatpanelscroll);
		meatpanelscr.setBorder(BorderFactory.createEmptyBorder());
		meatpanelscr.setOpaque(false);
		meatpanelscr.getViewport().setOpaque(false);              
		meatpanelscr.setVerticalScrollBar(verticalScrollBar);
		meatpanelscr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		meatpanelscr.setBounds(0, 0, 221, 70);
        
		meatpanel.add(meatpanelscr);
		
		LocalisedLabel lblMeat = new LocalisedLabel("meat.text");
		lblMeat.setHorizontalAlignment(SwingConstants.TRAILING);
		lblMeat.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMeat.setBounds(640, 239, 119, 36);
		custompizza.add(lblMeat);
		// MEAT PANEL END
		
		// ADDITIVE PANEL START
		JPanel additivepanel = new ThemeRoundPanel(20, new Color(10,10,10, 10), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200,200,200));
		additivepanel.setBounds(538, 380, 221, 131);
		custompizza.add(additivepanel);
		additivepanel.setLayout(null);
		
		additivepanelscroll = new JPanel(new FlowLayout(FlowLayout.RIGHT));	
		additivepanelscroll.setOpaque(false);
		additivepanelscroll.setBackground(new Color(0,0,0,0));
		additivepanelscroll.setPreferredSize(new Dimension(0, 0));
		
		JScrollPane additivescr = new JScrollPane(additivepanelscroll);
		additivescr.setBorder(BorderFactory.createEmptyBorder());
		additivescr.setOpaque(false);
		additivescr.getViewport().setOpaque(false);              
		additivescr.setVerticalScrollBar(verticalScrollBar);
		additivescr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		additivescr.setBounds(0, 0, 221, 131);
        
		additivepanel.add(additivescr);
		
		LocalisedLabel lblMrce_1_1_1 = new LocalisedLabel("additives.text");
		lblMrce_1_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblMrce_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMrce_1_1_1.setBounds(640, 350, 119, 36);
		custompizza.add(lblMrce_1_1_1);
		// ADDITIVE PANEL END
		// INFO PANEL
		infopanel = new JPanel();
		infopanel.setBounds(270, 269, 221, 181);
		custompizza.add(infopanel);
		infopanel.setLayout(null);
		
		prdctlbl = new JLabel("LabelForProduct");
		prdctlbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		prdctlbl.setHorizontalAlignment(SwingConstants.CENTER);
		prdctlbl.setBounds(10, 11, 201, 20);
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
		// END OF INFO PANEL
		// END OF CUSTOM
		// START OF CART
		JPanel cart = new JPanel();
		tabbedPane.addTab("Grozs", null, cart, null);
		cart.setLayout(null);
		
		JPanel panel_1 = new ThemeRoundPanel(20, new Color(50, 50, 50, 20), new Color(200, 200, 200, 30), new Color(0,0,0), new Color(200,200,200));
		panel_1.setBounds(10, 47, 260, 465);
		cart.add(panel_1);
		panel_1.setLayout(null);
		
		cartpanelscroll = new JPanel(new FlowLayout(FlowLayout.LEFT));	
		cartpanelscroll.setOpaque(false);
		cartpanelscroll.setBackground(new Color(0,0,0,0));
		cartpanelscroll.setPreferredSize(new Dimension(235, 500));
		
		cartscr = new JScrollPane(cartpanelscroll);
		cartscr.setBorder(BorderFactory.createEmptyBorder());
		cartscr.setOpaque(false);
		cartscr.getViewport().setOpaque(false);              
		cartscr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		cartscr.setBounds(0, 5, 255, 450);
		panel_1.add(cartscr);
		
		LocalisedLabel grozslbl = new LocalisedLabel("cart.text");
		grozslbl.setHorizontalAlignment(SwingConstants.CENTER);
		grozslbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		grozslbl.setBounds(10, 11, 260, 34);
		cart.add(grozslbl);
		// END OF CART
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
		updateImage();
		updateCart();
	}
	private void addPizza() {
		Pizza cheesePizza = new Pizza(20, "cheesepizza.text", new IngredientHolder("dough", "thin.text", "thin", "dough"));
		cheesePizza.addIngredient(new IngredientHolder("sauce", "tomatosauce.text", "tomatosauce", "tomatosauce"));
		cheesePizza.addIngredient(new IngredientHolder("cheese", "bluecheese.text", "bluecheese", "bluecheese"));
		cheesePizza.addIngredient(new IngredientHolder("cheese", "mozzarella.text", "mozzarella", "mozzarella"));
		cheesePizza.addIngredient(new IngredientHolder("cheese", "normal.text", "normal", "normal"));
		PizzaPanel cheesepizzapnl = new PizzaPanel(cheesePizza, 20, new Color(50, 50, 50, 50), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200, 200, 200));
		cheesepizzapnl.update();
		
		Pizza pineapple = new Pizza(20, "pineapplepizza.text", new IngredientHolder("dough", "thin.text", "thin", "dough"));
		pineapple.addIngredient(new IngredientHolder("sauce", "tomatosauce.text", "tomatosauce", "tomatosauce"));
		pineapple.addIngredient(new IngredientHolder("cheese", "mozzarella.text", "mozzarella", "mozzarella"));
		pineapple.addIngredient(new IngredientHolder("veggie", "pineapple.text", "pineapple", "pineapple"));
		pineapple.addIngredient(new IngredientHolder("meat", "ham.text", "ham", "ham"));
		PizzaPanel pineapplepnl = new PizzaPanel(pineapple, 20, new Color(50, 50, 50, 50), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200, 200, 200));
		pineapplepnl.update();
		
		Pizza shroom = new Pizza(20, "shroompizza.text", new IngredientHolder("dough", "thin.text", "thin", "dough"));
		shroom.addIngredient(new IngredientHolder("sauce", "tomatosauce.text", "tomatosauce", "tomatosauce"));
		shroom.addIngredient(new IngredientHolder("cheese", "mozzarella.text", "mozzarella", "mozzarella"));
		shroom.addIngredient(new IngredientHolder("veggie", "mushroom.text", "mushroom", "mushroom"));
		shroom.addIngredient(new IngredientHolder("meat", "ham.text", "ham", "ham"));
		PizzaPanel shroompnl = new PizzaPanel(shroom, 20, new Color(50, 50, 50, 50), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200, 200, 200));
		shroompnl.update();
		
		Pizza meatlovers = new Pizza(20, "meatlovers.text", new IngredientHolder("dough", "thin.text", "thin", "dough"));
		meatlovers.addIngredient(new IngredientHolder("sauce", "tomatosauce.text", "tomatosauce", "tomatosauce"));
		meatlovers.addIngredient(new IngredientHolder("cheese", "mozzarella.text", "mozzarella", "mozzarella"));
		meatlovers.addIngredient(new IngredientHolder("meat", "ham.text", "ham", "ham"));
		meatlovers.addIngredient(new IngredientHolder("meat", "chicken.text", "chicken", "chicken"));
		meatlovers.addIngredient(new IngredientHolder("meat", "salami.text", "salami", "salami"));
		PizzaPanel meatloverspnl = new PizzaPanel(meatlovers, 20, new Color(50, 50, 50, 50), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200, 200, 200));
		meatloverspnl.update();
		
		presetpizzascroll.add(cheesepizzapnl);
		presetpizzascroll.add(pineapplepnl);
		presetpizzascroll.add(shroompnl);
		presetpizzascroll.add(meatloverspnl);
	}
	private void addIngredients() {
		infopanel.setVisible(false);
		twenty = new IngredientPanel("20 cm", "size", new ImageIcon(LoginWindow.class.getResource("/img/dough20.png")), 20, new Color(100, 100, 100, 50), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200, 200, 200), true, () -> setSize(sizepanelScroll, "20 cm"), () -> info("20 cm", null, new ImageIcon(LoginWindow.class.getResource("/img/dough20.png")), Settings.prices.get("20")), this::infoOff);
		thirty = new IngredientPanel("30 cm", "size", new ImageIcon(LoginWindow.class.getResource("/img/dough30.png")), 20, new Color(100, 100, 100, 50), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200, 200, 200), false, () -> setSize(sizepanelScroll, "30 cm"), () -> info("30 cm", null, new ImageIcon(LoginWindow.class.getResource("/img/dough30.png")), Settings.prices.get("30")), this::infoOff);
		sixty = new IngredientPanel("60 cm", "size", new ImageIcon(LoginWindow.class.getResource("/img/dough60.png")), 20, new Color(100, 100, 100, 50), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200, 200, 200), false, () -> setSize(sizepanelScroll, "60 cm"), () -> info("60 cm", null, new ImageIcon(LoginWindow.class.getResource("/img/dough60.png")), Settings.prices.get("60")), this::infoOff);
		sizepanelScroll.add(twenty);
		sizepanelScroll.add(thirty);
		sizepanelScroll.add(sixty);
		doughpanelscroll.add(new IngredientPanel("thin", "dough", new ImageIcon(LoginWindow.class.getResource("/img/dough.png")), 20, new Color(100, 100, 100, 50), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200, 200, 200), true, () -> setDoughType(doughpanelscroll, new IngredientHolder("dough", "thin.text", "thin", "dough"), "thin"), () -> info(Settings.lang.get("thin.text")+" "+Settings.lang.get("dough.text"), null, new ImageIcon(LoginWindow.class.getResource("/img/dough.png")), Settings.prices.get("thin")), this::infoOff));
		doughpanelscroll.add(new IngredientPanel("regular", "dough", new ImageIcon(LoginWindow.class.getResource("/img/dough.png")), 20, new Color(100, 100, 100, 50), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200, 200, 200), false, () -> setDoughType(doughpanelscroll, new IngredientHolder("dough", "regular.text", "regular", "dough"), "regular"), () -> info(Settings.lang.get("regular.text")+" "+Settings.lang.get("dough.text"), null, new ImageIcon(LoginWindow.class.getResource("/img/dough.png")), Settings.prices.get("regular")), this::infoOff));
		doughpanelscroll.add(new IngredientPanel("thick", "dough", new ImageIcon(LoginWindow.class.getResource("/img/dough.png")), 20, new Color(100, 100, 100, 50), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200, 200, 200), false, () -> setDoughType(doughpanelscroll, new IngredientHolder("dough", "thick.text", "thick", "dough"), "thick"), () -> info(Settings.lang.get("thick.text")+" "+Settings.lang.get("dough.text"), null, new ImageIcon(LoginWindow.class.getResource("/img/dough.png")), Settings.prices.get("thick")), this::infoOff));
		
		saucepanelscroll.add(new IngredientPanel("tomatosauce", "sauce", new ImageIcon(LoginWindow.class.getResource("/img/tomatosauce.png")), 20, new Color(100, 100, 100, 50), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200, 200, 200), false, () -> info(Settings.lang.get("tomatosauce.text"), null, new ImageIcon(LoginWindow.class.getResource("/img/tomatosauce.png")), Settings.prices.get("tomatosauce")), this::infoOff, () -> addIngredientToPizza("sauce", "tomatosauce.text", "tomatosauce", "tomatosauce"), () -> removeIngredientFromPizza("sauce", "tomatosauce.text", "tomatosauce", "tomatosauce")));
		saucepanelscroll.add(new IngredientPanel("bbqsauce", "sauce", new ImageIcon(LoginWindow.class.getResource("/img/bbqsauce.png")), 20, new Color(100, 100, 100, 50), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200, 200, 200), false, () -> info(Settings.lang.get("bbqsauce.text"), null, new ImageIcon(LoginWindow.class.getResource("/img/bbqsauce.png")), Settings.prices.get("bbqsauce")), this::infoOff, () -> addIngredientToPizza("sauce", "bbqsauce.text", "bbqsauce", "bbqsauce"), () -> removeIngredientFromPizza("sauce", "bbqsauce.text", "bbqsauce", "bbqsauce")));
	
		cheesepanelscroll.add(new IngredientPanel("mozzarella", "cheese", new ImageIcon(LoginWindow.class.getResource("/img/mozzarella.png")), 20, new Color(100, 100, 100, 50), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200, 200, 200), false, () -> info(Settings.lang.get("mozzarella.text"), null, new ImageIcon(LoginWindow.class.getResource("/img/mozzarella.png")), Settings.prices.get("mozzarella")), this::infoOff, () -> addIngredientToPizza("cheese", "mozzarella.text", "mozzarella", "mozzarella"), () -> removeIngredientFromPizza("cheese", "mozzarella.text", "mozzarella", "mozzarella")));
		cheesepanelscroll.add(new IngredientPanel("bluecheese", "cheese", new ImageIcon(LoginWindow.class.getResource("/img/bluecheese.png")), 20, new Color(100, 100, 100, 50), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200, 200, 200), false, () -> info(Settings.lang.get("bluecheese.text"), null, new ImageIcon(LoginWindow.class.getResource("/img/bluecheese.png")), Settings.prices.get("bluecheese")), this::infoOff, () -> addIngredientToPizza("cheese", "bluecheese.text", "bluecheese", "bluecheese"), () -> removeIngredientFromPizza("cheese", "bluecheese.text", "bluecheese", "bluecheese")));	
		cheesepanelscroll.add(new IngredientPanel("normal", "sauce", new ImageIcon(LoginWindow.class.getResource("/img/normal.png")), 20, new Color(100, 100, 100, 50), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200, 200, 200), false, () -> info(Settings.lang.get("normal.text"), null, new ImageIcon(LoginWindow.class.getResource("/img/normal.png")), Settings.prices.get("normal")), this::infoOff, () -> addIngredientToPizza("cheese", "normal.text", "normal", "normal"), () -> removeIngredientFromPizza("cheese", "normal.text", "normal", "normal")));
		
		meatpanelscroll.add(new IngredientPanel("ham", "meat", new ImageIcon(LoginWindow.class.getResource("/img/ham.png")), 20, new Color(100, 100, 100, 50), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200, 200, 200), false, () -> info(Settings.lang.get("ham.text"), null, new ImageIcon(LoginWindow.class.getResource("/img/ham.png")), Settings.prices.get("ham")), this::infoOff, () -> addIngredientToPizza("meat", "ham.text", "ham", "ham"), () -> removeIngredientFromPizza("meat", "ham.text", "ham", "ham")));
		meatpanelscroll.add(new IngredientPanel("salami", "meat", new ImageIcon(LoginWindow.class.getResource("/img/salami.png")), 20, new Color(100, 100, 100, 50), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200, 200, 200), false, () -> info(Settings.lang.get("salami.text"), null, new ImageIcon(LoginWindow.class.getResource("/img/salami.png")), Settings.prices.get("salami")), this::infoOff, () -> addIngredientToPizza("meat", "salami.text", "salami", "salami"), () -> removeIngredientFromPizza("meat", "salami.text", "salami", "salami")));
		meatpanelscroll.add(new IngredientPanel("chicken", "meat", new ImageIcon(LoginWindow.class.getResource("/img/chicken.png")), 20, new Color(100, 100, 100, 50), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200, 200, 200), false, () -> info(Settings.lang.get("chicken.text"), null, new ImageIcon(LoginWindow.class.getResource("/img/chicken.png")), Settings.prices.get("chicken")), this::infoOff, () -> addIngredientToPizza("meat", "chicken.text", "chicken", "chicken"), () -> removeIngredientFromPizza("meat", "chicken.text", "chicken", "chicken")));
		
		additivepanelscroll.add(new IngredientPanel("mushroom", "veggies", new ImageIcon(LoginWindow.class.getResource("/img/mushroom.png")), 20, new Color(100, 100, 100, 50), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200, 200, 200), false, () -> info(Settings.lang.get("mushroom.text"), null, new ImageIcon(LoginWindow.class.getResource("/img/mushroom.png")), Settings.prices.get("mushroom")), this::infoOff, () -> addIngredientToPizza("veggies", "mushroom.text", "mushroom", "mushroom"), () -> removeIngredientFromPizza("veggies", "mushroom.text", "mushroom", "mushroom")));
		additivepanelscroll.add(new IngredientPanel("pineapple", "veggies", new ImageIcon(LoginWindow.class.getResource("/img/pineapple.png")), 20, new Color(100, 100, 100, 50), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200, 200, 200), false, () -> info(Settings.lang.get("pineapple.text"), null, new ImageIcon(LoginWindow.class.getResource("/img/pineapple.png")), Settings.prices.get("pineapple")), this::infoOff, () -> addIngredientToPizza("veggies", "pineapple.text", "pineapple", "pineapple"), () -> removeIngredientFromPizza("veggies", "pineapple.text", "pineapple", "pineapple")));
		additivepanelscroll.add(new IngredientPanel("tomato", "veggies", new ImageIcon(LoginWindow.class.getResource("/img/tomato.png")), 20, new Color(100, 100, 100, 50), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200, 200, 200), false, () -> info(Settings.lang.get("tomato.text"), null, new ImageIcon(LoginWindow.class.getResource("/img/tomato.png")), Settings.prices.get("tomato")), this::infoOff, () -> addIngredientToPizza("veggies", "tomato.text", "tomato", "tomato"), () -> removeIngredientFromPizza("veggies", "tomato.text", "tomato", "tomato")));
		additivepanelscroll.add(new IngredientPanel("onion", "veggies", new ImageIcon(LoginWindow.class.getResource("/img/onion.png")), 20, new Color(100, 100, 100, 50), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200, 200, 200), false, () -> info(Settings.lang.get("onion.text"), null, new ImageIcon(LoginWindow.class.getResource("/img/onion.png")), Settings.prices.get("onion")), this::infoOff, () -> addIngredientToPizza("veggies", "onion.text", "onion", "onion"), () -> removeIngredientFromPizza("veggies", "onion.text", "onion", "onion")));
		additivepanelscroll.add(new IngredientPanel("pickles", "veggies", new ImageIcon(LoginWindow.class.getResource("/img/pickles.png")), 20, new Color(100, 100, 100, 50), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200, 200, 200), false, () -> info(Settings.lang.get("pickles.text"), null, new ImageIcon(LoginWindow.class.getResource("/img/pickles.png")), Settings.prices.get("pickles")), this::infoOff, () -> addIngredientToPizza("veggies", "pickles.text", "pickles", "pickles"), () -> removeIngredientFromPizza("veggies", "pickles.text", "pickles", "pickles")));
		additivepanelscroll.add(new IngredientPanel("jalapeno", "veggies", new ImageIcon(LoginWindow.class.getResource("/img/jalapeno.png")), 20, new Color(100, 100, 100, 50), new Color(200, 200, 200, 50), new Color(0,0,0), new Color(200, 200, 200), false, () -> info(Settings.lang.get("jalapeno.text"), null, new ImageIcon(LoginWindow.class.getResource("/img/jalapeno.png")), Settings.prices.get("jalapeno")), this::infoOff, () -> addIngredientToPizza("veggies", "jalapeno.text", "jalapeno", "jalapeno"), () -> removeIngredientFromPizza("veggies", "jalapeno.text", "jalapeno", "jalapeno")));
		
	}
	private void addIngredientToPizza(String type, String locale, String name, String identifier) {
		custom.addIngredient(new IngredientHolder(type, locale, name, identifier));
		updateDesc();
	}
	private void removeIngredientFromPizza(String type, String locale, String name, String identifier) {
		custom.removeIngredient(new IngredientHolder(type, locale, name, identifier));
		updateDesc();
	}
	private void updateImage() {
		Component[] components = pizzaimg.getComponents();
		ArrayList<String> items = new ArrayList<String>();
		for (IngredientHolder i : custom.ingredients()) {
			items.add(i.getIdentifier());
		}
		for (Component c : components) {
			if (items.contains(c.getName()) || c.getName().equals("dough")) {
				c.setVisible(true);
			} else
				c.setVisible(false);
		}
	}
	private void setSize(JPanel parent, String exception) {
		for (Component child : parent.getComponents()) {
			if (child instanceof IngredientPanel && !child.getName().equals(exception)) {
				IngredientPanel temp = (IngredientPanel)child;
				temp.enableIngredient(false);
			} else {
					custom.setDoughSize(Integer.parseInt(child.getName().substring(0, 2)));			
			}
		}
		updateDesc();
	}
	private void setDoughType(JPanel parent, IngredientHolder dough, String exception) {
		for (Component child : parent.getComponents()) {
			if (child instanceof IngredientPanel && !child.getName().equals(exception)) {
				IngredientPanel temp = (IngredientPanel)child;
				temp.enableIngredient(false);
			} else {
					custom.setDoughType(dough);		
			}
		}
		updateDesc();
	}
	private void updateDesc() {
		Integer val = (Integer) spinner.getValue();
		double spinnerValdb = val.doubleValue();
		DecimalFormat df = new DecimalFormat("0.00");
		desc.setText(custom.description(true));
		orderbutton.setText(Settings.lang.get("addorder.text")+" - €"+df.format(custom.getPrice() * spinnerValdb));
		updateImage();
	}
	private void info(String title, String text, ImageIcon img, double price) {
		DecimalFormat df = new DecimalFormat("0.00");
		infopanel.setVisible(true);
		prdctlbl.setText(title);
		if (text != null) {
			imglbl.setIcon(null);
			imglbl.setText(text);
		} else {
			imglbl.setText("");
			imglbl.setIcon(new ImageIcon(img.getImage().getScaledInstance(90, 90, java.awt.Image.SCALE_SMOOTH)));
		}
		prdctprc.setText("€"+df.format(price));
	}
	private void infoOff() {
		infopanel.setVisible(false);
	}
	
	public static void updateCart() {
		cartpanelscroll.removeAll();
		int newY = 500;
		for (CartItem i : cart) {
			CartPanel panel = new CartPanel(i, 20, new Color(50, 50, 50, 5), new Color(200, 200, 200, 5), new Color(0,0,0), new Color(200, 200, 200));
			panel.update();
			cartpanelscroll.add(panel);
			newY += 150;
		}
		cartpanelscroll.setPreferredSize(new Dimension(235, newY));
		cartscr.repaint();
		cartscr.revalidate();
	}
	private void sendToCart(Pizza p, int amount) {
		DateTimeFormatter timeformat = DateTimeFormatter.ofPattern("HH:mm"); 
		DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
		LocalDateTime now = LocalDateTime.now();   
		CartItem cartitem = new CartItem(amount, new Pizza(p), dateformat.format(now), timeformat.format(now));
		if (MainView.cart.contains(cartitem)) {
			MainView.cart.get(MainView.cart.indexOf(cartitem)).setAmount(MainView.cart.get(MainView.cart.indexOf(cartitem)).getAmount()+cartitem.getAmount());
		} else {
			MainView.cart.add(cartitem);
		}
		updateCart();
	}
}
