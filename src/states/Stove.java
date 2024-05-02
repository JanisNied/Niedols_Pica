package states;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.Timer;

import db.Database;
import localisation.LocalisedLabel;
import localisation.ThemeIcon;
import localisation.ThemeRoundPanel;
import main.Global;
import main.Settings;
import objects.CartItem;
import objects.Customer;

public class Stove extends JPanel{

	private static final long serialVersionUID = 1L;
	private Customer c;
	private int max, count = 0;
	private Timer timer;
	
	public Stove(Customer c, int bordersize, Color bglight, Color bgdark, Color borderlight, Color borderdark) {
		this.c = c;
		setPreferredSize(new Dimension(240, 315));
		setLayout(null);
		setOpaque(false);
		
		JLabel lblNewLabel = new ThemeIcon(new ImageIcon(new ImageIcon(LoginWindow.class.getResource("/img/whitethemefurnace.png")).getImage().getScaledInstance(188, 188, java.awt.Image.SCALE_SMOOTH)), new ImageIcon(new ImageIcon(LoginWindow.class.getResource("/img/blackthemefurnace.png")).getImage().getScaledInstance(188,188, java.awt.Image.SCALE_SMOOTH)));  
		lblNewLabel.setIcon(new ImageIcon(Stove.class.getResource("/img/blackthemefurnace.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(26, 63, 188, 188);
		add(lblNewLabel);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(10, 266, 220, 21);
		add(progressBar);
		
		LocalisedLabel lblNewLabel_1 = new LocalisedLabel("timeleft.text");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 290, 141, 20);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("00:00");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(66, 290, 164, 20);
		add(lblNewLabel_1_1);
		
		@SuppressWarnings("serial")
		JLabel lblNewLabel_2 = new JLabel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				setText(Settings.lang.get("ordertitle.text")+" #"+c.getOrderNum()+"; x"+countPizzas()+" "+Settings.lang.get("pizzasleft.text"));
			}
		};
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(10, 11, 220, 41);
		add(lblNewLabel_2);
		
		JPanel bg = new ThemeRoundPanel(20, new Color(50, 50, 50, 10), new Color(200, 200, 200, 10), new Color(0,0,0), new Color(200,200,200));
		bg.setBounds(0,0,240,315);
		add(bg);
		max = 10 * countPizzas();
		progressBar.setMaximum(max);
		
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (count < max) {
                            count++;
                            progressBar.setValue(count);
                            lblNewLabel_1_1.setText(getFormattedTime(max - count));
                        } else {
                            timer.stop();
                            MainView.frncscroll.remove(Stove.this);
                    		MainView.frncscroll.repaint();
                    		MainView.frncscroll.revalidate();
                    		c.setOrderComplete(true);
                    		Database.overrideCustomer(Global.database, c);
                    		MainView.cooking.remove(c);
                    		MainView.updateCustomers();
                    		Database.updateOrders(Global.database, Global.user, Database.getOrders(Global.database, Global.user)+1);
                        }
                    }
                });
                timer.start();
                return null;
            }
        };

        worker.execute();
	}
	private String getFormattedTime(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%02d:%02d", minutes, remainingSeconds);
    }
	private int countPizzas() {
		int count = 0;
		for (CartItem i : c.getCart()) {
			count += i.getAmount();
		}
		return count;
	}
}
