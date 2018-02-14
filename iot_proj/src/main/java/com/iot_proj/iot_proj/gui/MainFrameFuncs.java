package com.iot_proj.iot_proj.gui;

import il.ac.bgu.cs.bp.bpjs.execution.BProgramRunner;
import il.ac.bgu.cs.bp.bpjs.execution.listeners.InMemoryEventLoggingListener;
import il.ac.bgu.cs.bp.bpjs.execution.listeners.PrintBProgramRunnerListener;
import il.ac.bgu.cs.bp.bpjs.model.BProgram;
import il.ac.bgu.cs.bp.bpjs.model.SingleResourceBProgram;
import javax.swing.JTextArea;

public class MainFrameFuncs {
	
	public void runBprog(String path) throws InterruptedException{
		//running 
		BProgram bprog = new SingleResourceBProgram(path);
		BProgramRunner runner = new BProgramRunner(bprog);
		runner.addListener(new PrintBProgramRunnerListener());
		InMemoryEventLoggingListener eventLogger = new InMemoryEventLoggingListener();
		runner.addListener(eventLogger);
                
			runner.start();
                        
               
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

