package com.iot_proj.iot_proj.gui;

import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import il.ac.bgu.cs.bp.bpjs.execution.BProgramRunner;
import il.ac.bgu.cs.bp.bpjs.execution.listeners.BProgramRunnerListener;
import il.ac.bgu.cs.bp.bpjs.execution.listeners.InMemoryEventLoggingListener;
import il.ac.bgu.cs.bp.bpjs.execution.listeners.PrintBProgramRunnerListener;
import il.ac.bgu.cs.bp.bpjs.model.BProgram;
import il.ac.bgu.cs.bp.bpjs.model.SingleResourceBProgram;


public class MainFrameFuncs {
	private PrintStream logStream;
	
	public MainFrameFuncs(JTextArea logTextArea) {
		this.logStream = new PrintStream(new CustomOutStream(logTextArea)); 
	}
	
	public void runBprog(String path) throws InterruptedException{
		//running 
		BProgram bprog = new SingleResourceBProgram(path);
		BProgramRunner runner = new BProgramRunner(bprog);
		runner.addListener(new PrintBProgramRunnerListener());
		CustomBProgramRunnerListener eventLogger = new CustomBProgramRunnerListener(logStream);
		runner.addListener(eventLogger);
      
			runner.start();
                        
               
	}
	
	
	public PrintStream getLogStream() {
		return logStream;
	}

	public void setLogStream(PrintStream logStream) {
		this.logStream = logStream;
	}

		//shows the main frame
        public static void showFrame(JFrame frame){
    	java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frame.setVisible(true);
            }
        });
    }

}

