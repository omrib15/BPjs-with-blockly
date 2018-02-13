package com.iot_proj.iot_proj.gui;

import il.ac.bgu.cs.bp.bpjs.execution.BProgramRunner;
import il.ac.bgu.cs.bp.bpjs.execution.listeners.InMemoryEventLoggingListener;
import il.ac.bgu.cs.bp.bpjs.execution.listeners.PrintBProgramRunnerListener;
import il.ac.bgu.cs.bp.bpjs.model.BProgram;
import il.ac.bgu.cs.bp.bpjs.model.SingleResourceBProgram;


public class MainFrameFuncs {
	
	public void runBprog(String path) throws InterruptedException{
		//running 
		BProgram bprog = new SingleResourceBProgram("our_resources/examples/"+path);
		BProgramRunner runner = new BProgramRunner(bprog);
		runner.addListener(new PrintBProgramRunnerListener());
		InMemoryEventLoggingListener eventLogger = new InMemoryEventLoggingListener();
		runner.addListener(eventLogger);

			runner.start();
	}

}
