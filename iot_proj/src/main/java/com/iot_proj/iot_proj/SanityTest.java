package com.iot_proj.iot_proj;

import il.ac.bgu.cs.bp.bpjs.bprogram.runtimeengine.BProgramRunner;
import il.ac.bgu.cs.bp.bpjs.bprogram.runtimeengine.BProgram;
import il.ac.bgu.cs.bp.bpjs.bprogram.runtimeengine.SingleResourceBProgram;
import il.ac.bgu.cs.bp.bpjs.events.BEvent;
import il.ac.bgu.cs.bp.bpjs.bprogram.runtimeengine.listeners.InMemoryEventLoggingListener;
import il.ac.bgu.cs.bp.bpjs.bprogram.runtimeengine.listeners.PrintBProgramRunnerListener;
import il.ac.bgu.cs.bp.bpjs.eventselection.PrioritizedBSyncEventSelectionStrategy;
import il.ac.bgu.cs.bp.bpjs.verification.DfsBProgramVerifier;
import il.ac.bgu.cs.bp.bpjs.verification.VerificationResult;
import il.ac.bgu.cs.bp.bpjs.verification.VerificationResult;


public class SanityTest 
{
    public static void main( String[] args )
    {
		BProgram bprog = new SingleResourceBProgram("our_resources/examples/hello-world-seq.js");
//		bprog.setEventSelectionStrategy(new PrioritizedBSyncEventSelectionStrategy());
		BProgramRunner runner = new BProgramRunner(bprog);
		runner.addListener(new PrintBProgramRunnerListener());
		InMemoryEventLoggingListener eventLogger = new InMemoryEventLoggingListener();
		runner.addListener(eventLogger);
		try{
			runner.start();
			}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		
		}
}

