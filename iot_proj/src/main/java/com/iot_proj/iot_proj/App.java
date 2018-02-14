package com.iot_proj.iot_proj;

import com.iot_proj.iot_proj.gui.MainFrame;

public class App 
{
	//the app's entry point
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        showMainFrame();
    }
    
    //shows the main frame
    public static void showMainFrame(){
    	java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
}
