package localisation;

import javax.swing.*;
import javax.swing.text.*;

@SuppressWarnings("serial")
public class LatvianNumberTextField extends JTextField {
    public LatvianNumberTextField() {
        super();
        ((AbstractDocument) this.getDocument()).setDocumentFilter(new DocumentFilter() {
            private final String regex = "^2\\d{0,7}$";

            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                String newStr = fb.getDocument().getText(0, fb.getDocument().getLength()) + string;
                if (newStr.matches(regex)) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                String newStr = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
                if (newStr.matches(regex)) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
    }
}



