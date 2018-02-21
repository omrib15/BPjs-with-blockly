package com.iot_proj.iot_proj.custom;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextArea;

public class EventOutStream extends OutputStream {
	private JTextArea textArea;
	private String accStr;
	private final String found = "[BP][Info] EVENT_DETECTED:";
	private boolean begin;
	private DefaultListModel<String> eventsModel;
	
    public EventOutStream(JTextArea textArea, DefaultListModel<String> eventsModel) {
        this.textArea = textArea;
        this.accStr = "" ;
        this.begin = false;
        this.eventsModel = eventsModel;
        
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
						//add the found event to the list of events
						String eventname = accStr.substring(found.length()+1,accStr.length()-2);
						
						//check if the event is already on the list
						if(!eventsModel.contains(eventname)){
							eventsModel.addElement(accStr.substring(found.length()+1,accStr.length()-2));
						}
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
