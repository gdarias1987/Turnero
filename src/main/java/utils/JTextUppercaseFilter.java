package utils;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class JTextUppercaseFilter  extends DocumentFilter {
    @Override
    public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr)
            throws BadLocationException {
        fb.insertString(offset, text.toUpperCase(), attr);
    }

    @Override
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {
        fb.replace(offset, length, text.toUpperCase(), attrs);
    }
}
