package com.iot_proj.iot_proj.gui;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JTextArea;

public class CustomOutStream extends OutputStream {
	private JTextArea textArea;
	
	
	
    public CustomOutStream(JTextArea textArea) {
        this.textArea = textArea;
    }
    
    
	@Override
	public void write(int b) throws IOException {
		// redirects data to the text area
        textArea.append(String.valueOf((char)b));
        // scrolls the text area to the end of data
        textArea.setCaretPosition(textArea.getDocument().getLength());
	}

}
