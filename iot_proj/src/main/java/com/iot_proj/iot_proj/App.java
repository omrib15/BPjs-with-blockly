package com.iot_proj.iot_proj;

import com.iot_proj.iot_proj.gui.MainFrame;


public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        showMainFrame();
    }
    
    public static void showMainFrame(){
    	java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
}
