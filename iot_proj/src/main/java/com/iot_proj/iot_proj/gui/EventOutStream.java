package com.iot_proj.iot_proj.gui;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JList;
import javax.swing.JTextArea;

public class EventOutStream extends OutputStream {
	private JTextArea textArea;
	private JList eventsList;
	private String accStr;
	private final String found = "[BP][Info] found:";
	private boolean begin;
	
    public EventOutStream(JTextArea textArea, JList<String> eventsList) {
        this.textArea = textArea;
        this.eventsList = eventsList;
        this.accStr = "" ;
        this.begin = false;
    }
    
    
	@Override
	public void write(int b) throws IOException {
		String ch = String.valueOf((char)b);
		
		if(ch.equals("[")){
			begin = true;
		}
		
		//print ch as usual
		if(!begin){
			// redirects data to the text area
			textArea.append(ch);
        	// scrolls the text area to the end of data
        	textArea.setCaretPosition(textArea.getDocument().getLength());
		}
		
        checkForEvents(ch);
	}
	
	private void checkForEvents(String ch){
		
		if(begin){
			accStr += ch;
			
			if(accStr.length() > found.length()){
				if(accStr.contains(found)){
					if(ch.equals("\n")){
						textArea.append("fucking hell!: " + accStr.substring(found.length()));
						begin = false;
						accStr = "";
					} 
					
				}
				
				//the accumulated string isn't the "found" message 
				else{
					textArea.append(accStr);
					begin = false;
					accStr = "" ;
				}
			}
			
			//the accumulated string isn't the "found" message
			else if(!found.contains(accStr)){
				textArea.append(accStr);
				accStr = "";
				begin = false;
			}
			
		}
	}
	
	

}
