package com.iot_proj.iot_proj;

import java.util.stream.Collectors;

import org.mozilla.javascript.NativeArray;

import il.ac.bgu.cs.bp.bpjs.bprogram.runtimeengine.BProgram;
import il.ac.bgu.cs.bp.bpjs.bprogram.runtimeengine.BProgramRunner;
import il.ac.bgu.cs.bp.bpjs.bprogram.runtimeengine.SingleResourceBProgram;
import il.ac.bgu.cs.bp.bpjs.bprogram.runtimeengine.listeners.InMemoryEventLoggingListener;
import il.ac.bgu.cs.bp.bpjs.bprogram.runtimeengine.listeners.PrintBProgramRunnerListener;
import il.ac.bgu.cs.bp.bpjs.events.BEvent;
import il.ac.bgu.cs.bp.bpjs.search.ForgetfulVisitedNodeStore;
import il.ac.bgu.cs.bp.bpjs.search.Node;
import il.ac.bgu.cs.bp.bpjs.verification.DfsBProgramVerifier;
import il.ac.bgu.cs.bp.bpjs.verification.VerificationResult;
import il.ac.bgu.cs.bp.bpjs.verification.listeners.BriefPrintDfsVerifierListener;
import il.ac.bgu.cs.bp.bpjs.analysis.Requirements;


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
		
			System.out.println(e.getMessage());
		

		Maze maze = new Maze();
		maze.run();
		maze.verify();
	}

	public class Maze{
		String implementation = "maze-positive.js"; 
		//        String implementation = "MazesNegative.js";
		final BEvent targetFoundEvent = BEvent.named("targetFound");

		// change commented line below to solve a different maze.
		String mazeName = "complex";


		public void run() throws InterruptedException {
			SingleResourceBProgram bprog = prepareProgram();
			BProgramRunner rnr = new BProgramRunner(bprog);
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
				vfr.setVisitedNodeStore(new ForgetfulVisitedNodeStore());

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

