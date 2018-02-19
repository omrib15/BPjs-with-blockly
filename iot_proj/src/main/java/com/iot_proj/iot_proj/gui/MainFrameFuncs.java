package com.iot_proj.iot_proj.gui;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextArea;

import com.iot_proj.iot_proj.blocklyeditor.BlocklyRunner;

import il.ac.bgu.cs.bp.bpjs.execution.BProgramRunner;
import il.ac.bgu.cs.bp.bpjs.execution.listeners.BProgramRunnerListener;
import il.ac.bgu.cs.bp.bpjs.execution.listeners.InMemoryEventLoggingListener;
import il.ac.bgu.cs.bp.bpjs.execution.listeners.PrintBProgramRunnerListener;
import il.ac.bgu.cs.bp.bpjs.model.BEvent;
import il.ac.bgu.cs.bp.bpjs.model.BProgram;
import il.ac.bgu.cs.bp.bpjs.model.SingleResourceBProgram;


public class MainFrameFuncs {
	private PrintStream logStream;
	private Thread currentRunnerThread;
	private BlocklyRunner blocklyRunner;
	private PrintStream oldStream = System.out;
	private DefaultListModel<String> eventsModel;
	private BProgram currBProgram;
	
	public MainFrameFuncs(JTextArea logTextArea, DefaultListModel<String> eventsModel) {
		this.logStream = new PrintStream(new EventOutStream(logTextArea, eventsModel));
		this.blocklyRunner = new BlocklyRunner();
		this.eventsModel = eventsModel;
		
		
	}
	
	public void runBprog(String path) throws InterruptedException{
		
		
		BProgram bprog = new SingleResourceBProgram(path);
		setCurrBProgram(bprog);
		
		//allow external events
		bprog.setDaemonMode(true);
		
		BProgramRunner runner = new BProgramRunner(bprog);
		
		System.setOut(logStream);
		//my custom listener that prints logs to the appropriate text area
		CustomBProgramRunnerListener eventLogger = new CustomBProgramRunnerListener();
	
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
	
	
	public void enqueueExternalEvent(String eventName){
		currBProgram.enqueueExternalEvent(new BEvent(eventName));
	}

		//shows the main frame
    public static void showFrame(JFrame frame){
    	java.awt.EventQueue.invokeLater(new Runnable() {
    		public void run() {
    			frame.setVisible(true);
    		}
    	});
    }

	public BProgram getCurrBProgram() {
		return currBProgram;
	}

	public void setCurrBProgram(BProgram currBProgram) {
		this.currBProgram = currBProgram;
	}

}

