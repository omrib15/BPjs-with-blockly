package com.iot_proj.iot_proj;

import com.iot_proj.iot_proj.gui.MainFrame;
import com.iot_proj.iot_proj.gui.MainFrameFuncs;
import com.iot_proj.iot_proj.blocklyeditor.BlocklyRunner;;

public class App 
{
	//the app's entry point
    public static void main( String[] args )
    {
    	MainFrame mainFrame = new MainFrame();
    
        MainFrameFuncs.showFrame(mainFrame);
        
        
    }
    
    
}
