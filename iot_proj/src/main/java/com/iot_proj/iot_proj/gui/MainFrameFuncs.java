package com.iot_proj.iot_proj.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextArea;

import com.iot_proj.iot_proj.blocklyeditor.BlocklyRunner;
import com.iot_proj.iot_proj.custom.CustomBProgramRunnerListener;
import com.iot_proj.iot_proj.custom.EventOutStream;

import il.ac.bgu.cs.bp.bpjs.execution.BProgramRunner;
import il.ac.bgu.cs.bp.bpjs.execution.listeners.BProgramRunnerListener;
import il.ac.bgu.cs.bp.bpjs.execution.listeners.InMemoryEventLoggingListener;
import il.ac.bgu.cs.bp.bpjs.execution.listeners.PrintBProgramRunnerListener;
import il.ac.bgu.cs.bp.bpjs.model.BEvent;
import il.ac.bgu.cs.bp.bpjs.model.BProgram;
import il.ac.bgu.cs.bp.bpjs.model.SingleResourceBProgram;


public class MainFrameFuncs {
	
	//-----------------Instance Members Begin-----------------------
	
	private PrintStream logStream;
	private Thread currentRunnerThread;
	private BlocklyRunner blocklyRunner;
	private PrintStream oldStream = System.out;
	private DefaultListModel<String> eventsModel;
	private BProgram currBProgram;
	private JTextArea logTextArea;
	
	//-----------------Instance Members End-----------------------
	
	//----------------Constructors Begin--------------------------
	
	public MainFrameFuncs(JTextArea logTextArea, DefaultListModel<String> eventsModel) {
		this.logStream = new PrintStream(new EventOutStream(logTextArea, eventsModel));
		this.blocklyRunner = new BlocklyRunner();
		this.eventsModel = eventsModel;
		this.logTextArea = logTextArea;
		
		
	}
	
	//----------------Constructors End--------------------------
	
	
	
	//----------------------MainFrame helper functions Begin -----------------------
	
	public void runBprog(String path) throws InterruptedException{
		
		//clear the display for the new running program
		clearEventsAndLog();
		
		BProgram bprog = new SingleResourceBProgram(path);
		setCurrBProgram(bprog);
		
		//allow external events
		bprog.setDaemonMode(true);
		
		BProgramRunner runner = new BProgramRunner(bprog);
		
		System.setOut(logStream);
		//my custom listener that prints logs to the appropriate text area
		CustomBProgramRunnerListener eventLogger = new CustomBProgramRunnerListener(eventsModel);
	
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
	
	
	public boolean isProgRunning(){
		return (currentRunnerThread != null );
	}
	
	public void openBlockly(){
		try {
			this.blocklyRunner.run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void clearEventsAndLog(){
		eventsModel.removeAllElements();
		logTextArea.setText("");
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
    
    public void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }
    
  //----------------------MainFrame helper functions End -----------------------
    
    
    //---------------------Setters and getters Begin--------------------

	public BProgram getCurrBProgram() {
		return currBProgram;
	}

	public void setCurrBProgram(BProgram currBProgram) {
		this.currBProgram = currBProgram;
	}
	
	public PrintStream getLogStream() {
		return logStream;
	}

	public void setLogStream(PrintStream logStream) {
		this.logStream = logStream;
	}
	
	
	//---------------------Setters and getters End--------------------
	

}

