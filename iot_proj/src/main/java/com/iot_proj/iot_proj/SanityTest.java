package com.iot_proj.iot_proj;

import java.util.stream.Collectors;

import org.mozilla.javascript.NativeArray;

import il.ac.bgu.cs.bp.bpjs.model.BProgram;
import il.ac.bgu.cs.bp.bpjs.execution.BProgramRunner;
import il.ac.bgu.cs.bp.bpjs.model.SingleResourceBProgram;
import il.ac.bgu.cs.bp.bpjs.execution.listeners.InMemoryEventLoggingListener;
import il.ac.bgu.cs.bp.bpjs.execution.listeners.PrintBProgramRunnerListener;
import il.ac.bgu.cs.bp.bpjs.model.BEvent;
import il.ac.bgu.cs.bp.bpjs.analysis.ForgetfulVisitedStateStore;
import il.ac.bgu.cs.bp.bpjs.analysis.Node;
import il.ac.bgu.cs.bp.bpjs.analysis.Requirements;
import il.ac.bgu.cs.bp.bpjs.analysis.DfsBProgramVerifier;
import il.ac.bgu.cs.bp.bpjs.analysis.VerificationResult;
import il.ac.bgu.cs.bp.bpjs.analysis.listeners.BriefPrintDfsVerifierListener;


public class SanityTest 
{
	public static void main( String[] args ) throws InterruptedException
	{
		BProgram bprog = new SingleResourceBProgram("our_resources/examples/hello-world-seq.js");
		//		bprog.setEventSelectionStrategy(new PrioritizedBSyncEventSelectionStrategy());
		BProgramRunner runner = new BProgramRunner(bprog);
		runner.addListener(new PrintBProgramRunnerListener());
		InMemoryEventLoggingListener eventLogger = new InMemoryEventLoggingListener();
		runner.addListener(eventLogger);

			runner.start();
		//#TODO get a fair selection strategy, daemon=true and then enqueueExternalEvent. i dont think we need to add Listeners to the
		//BProgram, logs are enough - but either way there are some examples (i saw some in the Deeper into BPjs slides)
		

		Maze maze = new Maze();
//		maze.run();
//		maze.verify();
	}

	public static class Maze{
		String implementation = "maze-positive.js"; 
		//        String implementation = "MazesNegative.js";
		final BEvent targetFoundEvent = BEvent.named("targetFound");

		// change commented line below to solve a different maze.
		String mazeName = "complex";


		public void run() throws InterruptedException {
			il.ac.bgu.cs.bp.bpjs.model.SingleResourceBProgram bprog = prepareProgram();
			il.ac.bgu.cs.bp.bpjs.execution.BProgramRunner rnr = new BProgramRunner(bprog);
			rnr.addListener(new PrintBProgramRunnerListener());
			rnr.start();
			printMaze(getMaze(bprog));

		}

		public void verify() throws InterruptedException {
			SingleResourceBProgram bprog = prepareProgram();
			bprog.appendSource( Requirements.eventNotSelected(targetFoundEvent.getName()) );

			try {
				DfsBProgramVerifier vfr = new DfsBProgramVerifier();

				vfr.setProgressListener(new BriefPrintDfsVerifierListener());
				vfr.setIterationCountGap(10);
				//                vfr.setVisitedNodeStore(new BProgramStateVisitedNodeStore(true));
				vfr.setVisitedNodeStore(new ForgetfulVisitedStateStore());

				vfr.setDetectDeadlocks(false); // prevent from detecting cases where we must hit a wall.
				final VerificationResult res = vfr.verify(bprog);

				char[][] maze = getMaze(bprog);
				printMaze(maze);
				if (res.isCounterExampleFound()) {
					System.out.println("Found a counterexample");
					for (Node nd : res.getCounterExampleTrace()) {
						System.out.println(" " + nd.getLastEvent());
						if (nd.getLastEvent() != null) {
							String name = nd.getLastEvent().getName();
							if (name.startsWith("Enter")) {
								String loc = name.split("\\(")[1].replace(")", "").trim();
								String coord[] = loc.split(",");
								int col = Integer.parseInt(coord[0]);
								int row = Integer.parseInt(coord[1]);
								maze[row][col] = '•';
							}
						}
					}
					printMaze(maze);

				} else {
					System.out.println("No counterexample found.");
				}
				System.out.printf("Scanned %,d states\n", res.getScannedStatesCount());
				System.out.printf("Time: %,d milliseconds\n", res.getTimeMillies());

			} catch (Exception ex) {
				ex.printStackTrace(System.out);
			}
		}

		private SingleResourceBProgram prepareProgram() {
			// Create a program
			final SingleResourceBProgram bprog = new SingleResourceBProgram(implementation);
			bprog.putInGlobalScope("MAZE_NAME", mazeName);
			bprog.putInGlobalScope("TARGET_FOUND_EVENT", targetFoundEvent);
			return bprog;
		}

		char[][] getMaze(BProgram bprog) {
			NativeArray jsMaze = bprog.getFromGlobalScope(mazeName, NativeArray.class).get();
			char maze[][] = new char[(int) jsMaze.getLength()][];
			for (int i = 0; i < jsMaze.getLength(); i++) {
				maze[i] = jsMaze.get(i).toString().toCharArray();
			}

			return maze;
		}

		private static void printMaze(char[][] maze) {
			String sep = new String(maze[0]).chars().mapToObj(c -> "-").collect(Collectors.joining("+", "+", "+"));
			for (char[] row : maze) {
				System.out.println(sep);
				System.out.println(
						new String(row).chars()
						.mapToObj(c -> new String(new char[]{(char) c}))
						.collect(Collectors.joining("|", "|", "|"))
						);
			}
			System.out.println(sep);
		}
	}
}

