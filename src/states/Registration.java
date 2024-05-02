package states;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import localisation.LocalisedButton;
import localisation.LocalisedLabel;
import localisation.ThemeRoundPanel;
import objects.LabelStatus;
import raven.glasspanepopup.GlassPanePopup;

@SuppressWarnings("serial")
public class Registration extends JPanel {
	private JTextField nicknme;
	private JTextField lgn;
	private JPasswordField pswd;
	private DocumentListener documentListener;
	private LabelStatus sts;
	
	public Registration() {
		setPreferredSize(new Dimension(265, 374));
		setLayout(null);
		setOpaque(false);
		
		JPanel pnl = new ThemeRoundPanel(20, new Color(255, 255, 255, 250), new Color(70,70,70, 250), new Color(0,0,0), new Color(200, 200, 200));
		pnl.setLayout(null);
		pnl.setBounds(0,0,265,374);
		add(pnl);
		
		JLabel lblNewLabel = new LocalisedLabel("registration.text");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 32, 245, 37);
		pnl.add(lblNewLabel);
		
		nicknme = new JTextField();
		nicknme.addKeyListener(new java.awt.event.KeyAdapter() {
		    public void keyTyped(java.awt.event.KeyEvent evt) {
		        if(nicknme.getText().length() >= 20&&!(evt.getKeyChar()==KeyEvent.VK_DELETE||evt.getKeyChar()==KeyEvent.VK_BACK_SPACE)) {
		            evt.consume();
		         }
		     }
		});
		nicknme.setBounds(10, 117, 245, 34);
		pnl.add(nicknme);
		nicknme.setColumns(10);
		
		JLabel lblNickname = new LocalisedLabel("nickname.text");
		lblNickname.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNickname.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNickname.setBounds(10, 80, 130, 37);
		pnl.add(lblNickname);
		
		lgn = new JTextField();
		lgn.addKeyListener(new java.awt.event.KeyAdapter() {
		    public void keyTyped(java.awt.event.KeyEvent evt) {
		        if(lgn.getText().length() >= 20&&!(evt.getKeyChar()==KeyEvent.VK_DELETE||evt.getKeyChar()==KeyEvent.VK_BACK_SPACE)) {
		            evt.consume();
		         }
		     }
		});
		lgn.setColumns(10);
		lgn.setBounds(10, 177, 245, 34);
		pnl.add(lgn);
		
		LocalisedLabel lblLogin = new LocalisedLabel("login.text");
		lblLogin.setVerticalAlignment(SwingConstants.BOTTOM);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLogin.setBounds(10, 140, 130, 37);
		pnl.add(lblLogin);
		
		JLabel lblNickname_1_1 = new LocalisedLabel("password.text");
		lblNickname_1_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNickname_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNickname_1_1.setBounds(10, 200, 130, 37);
		pnl.add(lblNickname_1_1);
		
		pswd = new JPasswordField();
		pswd.setColumns(10);
		pswd.setBounds(10, 237, 245, 34);
		pnl.add(pswd);
		
		LocalisedButton btnNewButton = new LocalisedButton("registration.text", this::registrationInitiate);
		btnNewButton.setBounds(10, 320, 163, 43);
		pnl.add(btnNewButton);
		
		LocalisedButton btnNewButton_1 = new LocalisedButton("exit.text", this::windowDie);
		btnNewButton_1.setBounds(190, 320, 65, 43);
		pnl.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(113, 271, 99, 23);
		pnl.add(lblNewLabel_1);
		
		sts = new LabelStatus(lblNewLabel_1);
		sts.setBounds(10, 276, 99, 14);
		pnl.add(sts);
		initPasswordField(pswd);
	}
	private void windowDie() {
		GlassPanePopup.closePopupLast();
	}
	private void registrationInitiate() {
		LoginWindow.timerThing(1000, lgn.getText(), new String(pswd.getPassword()), nicknme.getText());
		GlassPanePopup.closePopupLast();
	}
	public void initPasswordField(JPasswordField txt) {
        if (documentListener == null) {
            documentListener = new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    sts.checkPassword(String.valueOf(txt.getPassword()));
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                	sts.checkPassword(String.valueOf(txt.getPassword()));
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                	sts.checkPassword(String.valueOf(txt.getPassword()));
                }
            };
        }
        if (pswd != null) {
        	pswd.getDocument().removeDocumentListener(documentListener);
        }
        txt.getDocument().addDocumentListener(documentListener);
        pswd = txt;
    }

}
