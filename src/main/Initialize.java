package main;
import java.awt.EventQueue;

import states.LoginWindow;
import states.WindowHandler;

public class Initialize {
	public static void main(String[] args) {
		Global.setup();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Global.frame = new WindowHandler(new LoginWindow());	
					Global.frame.setVisible(true);	
				} catch (Exception e) {
					System.out.println("[FRAME] Frame failed to load! Stack Trace: ");
					e.printStackTrace();
				}
			}
		});
	}
}
