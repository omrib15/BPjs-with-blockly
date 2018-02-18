package com.iot_proj.iot_proj.gui;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import com.iot_proj.iot_proj.blocklyeditor.BlocklyRunner;

import il.ac.bgu.cs.bp.bpjs.execution.BProgramRunner;
import il.ac.bgu.cs.bp.bpjs.execution.listeners.BProgramRunnerListener;
import il.ac.bgu.cs.bp.bpjs.execution.listeners.InMemoryEventLoggingListener;
import il.ac.bgu.cs.bp.bpjs.execution.listeners.PrintBProgramRunnerListener;
import il.ac.bgu.cs.bp.bpjs.model.BProgram;
import il.ac.bgu.cs.bp.bpjs.model.SingleResourceBProgram;


public class MainFrameFuncs {
	private PrintStream logStream;
	private Thread currentRunnerThread;
	private BlocklyRunner blocklyRunner;
	
	public MainFrameFuncs(JTextArea logTextArea) {
		this.logStream = new PrintStream(new CustomOutStream(logTextArea), true);
		this.blocklyRunner = new BlocklyRunner();
	}
	
	public void runBprog(String path) throws InterruptedException{
		
		BProgram bprog = new SingleResourceBProgram(path);
		//allow external events
		bprog.setDaemonMode(true);
		
		BProgramRunner runner = new BProgramRunner(bprog);
		
		//my custom listener that prints logs to the appropriate text area
		CustomBProgramRunnerListener eventLogger = new CustomBProgramRunnerListener(logStream);
		
		runner.addListener(eventLogger);
		
		//set the thread that starts the BProgramRunner
		currentRunnerThread = new Thread(){
			public void run(){
				try {
					runner.start();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		//start the thread
		currentRunnerThread.start();
		
               
	}
	
	
	public PrintStream getLogStream() {
		return logStream;
	}

	public void setLogStream(PrintStream logStream) {
		this.logStream = logStream;
	}
	
	public void openBlockly(){
		try {
			this.blocklyRunner.run();
		} catch (IOException e) {
			e.printStackTrace();
		}
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

