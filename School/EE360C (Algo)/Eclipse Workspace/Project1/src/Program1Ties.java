/*
 * Name: 
 * EID:
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Your solution goes in this class.
 * 
 * Please do not modify the other files we have provided for you, as we will use
 * our own versions of those files when grading your project. You are
 * responsible for ensuring that your solution works with the original version
 * of all the other files we have provided for you.
 * 
 * That said, please feel free to add additional files and classes to your
 * solution, as you see fit. We will use ALL of your additional files when
 * grading your solution.
 */
public class Program1Ties extends AbstractProgram1 {
    /**
     * Determines whether a candidate Matching represents a solution to the
     * Stable Marriage problem. Study the description of a Matching in the
     * project documentation to help you with this.
     */
    public boolean isStableMatching(Matching marriage) {
    	// Enforce assumptions about a perfect matching
    	if (marriage == null)
    		return false;
    	if (marriage.getWorkerMatching() == null)
    		return false;
    	int workerCount = marriage.getWorkerCount();
    	int jobCount = marriage.getJobCount();
    	int workerMatchSize = marriage.getWorkerMatching().size();
    	if (workerCount != jobCount) // # jobs = # workers
    		return false;
    	if (workerCount != workerMatchSize) // is perfect matching
    		return false;
    	
    	// Cache relevant info
    	ArrayList<Integer> workerMatching = marriage.getWorkerMatching();
    	ArrayList<ArrayList<Integer>> workersPrefs = marriage.getWorkerPreference();
    	int n = jobCount;

    	// Create a look up table for the jobs' preferences so that they are arranged by worker number, not preference
    	int[][] jobsPrefsLookup = createJobsPrefsLookup(marriage.getJobPreference(), n);
    	
    	// Create a jobMatching that holds the matched pairs indexed by job. This also
    	// allows the function to be O(n^2), but only takes O(n) time
    	int[] jobMatching = new int[n];
    	for (int worker = 0; worker < n; worker++) {
    		int job = workerMatching.get(worker);
    		if (job == -1) // not a valid matching
    			return false;
    		jobMatching[job] = worker;
    	}

    	// Check for instabilities   O(n^2)
    	for (int worker = 0; worker < n; worker++) { // for every worker
    		int matchedJob = workerMatching.get(worker);
    		ArrayList<Integer> workersJobPrefs = workersPrefs.get(worker);
    		for (int preferredJobIndex = 0; matchedJob != workersJobPrefs.get(preferredJobIndex); preferredJobIndex++) { // for every job the worker prefers over his current matched job
    			int preferredJob = workersJobPrefs.get(preferredJobIndex);
    			int jobsCurrentWorker = jobMatching[preferredJob];
    			int jobsCurrentWorkerRanking = jobsPrefsLookup[preferredJob][jobsCurrentWorker];
    			int jobsThisWorkerRanking = jobsPrefsLookup[preferredJob][worker];
    			if (jobsCurrentWorkerRanking > jobsThisWorkerRanking) { // if that job prefers the worker over her current worker
    				return false; // is an unstable matching
    			}
    		}
    	}
    	
        return true; // is a stable matching
    }

    
    
    /**
     * Determines a solution to the Stable Marriage problem from the given input
     * set. Study the project description to understand the variables which
     * represent the input to your solution.
     * 
     * @return A stable Matching.
     */
    public Matching stableHiringGaleShapley(Matching marriage) {
    	// Enforce assumptions about a perfect matching
    	if (marriage == null)
    		return marriage;
    	int workerCount = marriage.getWorkerCount();
    	int jobCount = marriage.getJobCount();
    	if (workerCount != jobCount) // # jobs = # workers
    		return marriage;
    	
    	// Cache relevant info
    	ArrayList<ArrayList<Integer>> workersPrefs = marriage.getWorkerPreference();
    	int n = marriage.getJobCount();
    	
    	// Create an array to hold the locations that each worker is at on their preference lists
    	int[] workersCurrPrefIndex = new int[n]; // automatically initted to 0's
    	
    	// Create arrays to hold matchings, indexed by both worker and job
    	ArrayList<Integer> workerMatching = new ArrayList<Integer>(n);
    	ArrayList<Integer> jobMatching = new ArrayList<Integer>(n);
    	for (int i = 0; i < n; i++) { // init to null matchings
    		workerMatching.add(-1);
    		jobMatching.add(-1);
    	}

    	// Create a look up table for the jobs' preferences so that they are arranged by worker number, not preference
    	int[][] jobsPrefsLookup = createJobsPrefsLookup(marriage.getJobPreference(), n);

    	// Create queue of workers
    	LinkedList<Integer> workersQ = new LinkedList<Integer>();
    	for (int worker = 0; worker < n; worker++) { // populate
    		workersQ.add(worker);
    	}
    	
    	// Gale-Shapley algorithm:
    	
    	// while queue isn't empty
    	while (!workersQ.isEmpty()) {
    		int worker = workersQ.remove();
    		// while worker isn't matched
    		int job = -1;
    		while (job == -1) {
    			// apply to highest ranked job on worker's list that he hasn't applied to yet
    			int jobApp = workersPrefs.get(worker).get(workersCurrPrefIndex[worker]);
    			// if job isn't taken
    			if (jobMatching.get(jobApp) == -1) {
    				// match worker and job
    				job = jobApp;
    			} else {// else if job is taken (matched)
    				// if the job prefers this worker over its current worker
    				int jobsCurrentWorker = jobMatching.get(jobApp);
        			int jobsCurrentWorkerRanking = jobsPrefsLookup[jobApp][jobsCurrentWorker];
        			int jobsThisWorkerRanking = jobsPrefsLookup[jobApp][worker];
    				if (jobsThisWorkerRanking < jobsCurrentWorkerRanking) { // lower rank means more preferred
    					// match them
    					job = jobApp;
    					// add ex-worker to queue
    					workersQ.add(jobsCurrentWorker);
    				}
    			}
    			workersCurrPrefIndex[worker]++;
    		}
    		// put matching into ArrayList structures
    		workerMatching.set(worker, job);
    		jobMatching.set(job, worker);
    	}
    	
    	return new Matching(marriage, workerMatching);
    }

    
    
	// Create a look up table for the jobs' preferences so that they are arranged
	// by worker number, and store the preference value. This operation is O(n^2) and
	// allows the above functions to be O(n^2).
	int[][] createJobsPrefsLookup(ArrayList<ArrayList<Integer>> jobsPrefs, int n) {
    	int[][] jobsPrefsLookup = new int[n][n];
    	// init to null matchings
    	for (int j = 0; j < n; j++)
    		for (int w = 0; w < n; w++)
    			jobsPrefsLookup[j][w] = -1;
    	// populate lookup table
    	for (int job = 0; job < n; job++) {
    		ArrayList<Integer> jobsWorkerPrefs = jobsPrefs.get(job);
    		for (int wPref = 0; wPref < n; wPref++) {
    			int worker = jobsWorkerPrefs.get(wPref);
    			jobsPrefsLookup[job][worker] = wPref;
    		}
    	}
    	return jobsPrefsLookup;
	}
	
	
	
	// Prints out n vs. time (in ns) for 8 inputs to Gale-Shapley and 4 inputs
	// to brute force
	public static void printPlotData() {
		
		// Brute force
		int[] n = {4, 6, 8, 10};
		long[] time = new long[4];
		Program1Ties program = new Program1Ties();
		Matching problem;
		long startTime;
		try {
			problem = Driver.parseMatchingProblem("src/student_inputs/small_inputs/4.in");
			// run a couple times to get cache optimizing out of the way
	        program.stableMarriageBruteForce(problem);
	        program.stableMarriageBruteForce(problem);
			startTime = System.nanoTime();
	        program.stableMarriageBruteForce(problem);
	        time[0] = System.nanoTime() - startTime;

			problem = Driver.parseMatchingProblem("src/student_inputs/small_inputs/6.in");
			// run a couple times to get cache optimizing out of the way
	        program.stableMarriageBruteForce(problem);
	        program.stableMarriageBruteForce(problem);
			startTime = System.nanoTime();
	        program.stableMarriageBruteForce(problem);
	        time[1] = System.nanoTime() - startTime;

			problem = Driver.parseMatchingProblem("src/student_inputs/small_inputs/8.in");
			// run a couple times to get cache optimizing out of the way
	        program.stableMarriageBruteForce(problem);
	        program.stableMarriageBruteForce(problem);
			startTime = System.nanoTime();
	        program.stableMarriageBruteForce(problem);
	        time[2] = System.nanoTime() - startTime;

			problem = Driver.parseMatchingProblem("src/student_inputs/small_inputs/10.in");
			// run a couple times to get cache optimizing out of the way
	        program.stableMarriageBruteForce(problem);
	        program.stableMarriageBruteForce(problem);
			startTime = System.nanoTime();
	        program.stableMarriageBruteForce(problem);
	        time[3] = System.nanoTime() - startTime;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Gale-Shapley
		int[] n2 = {4, 6, 8, 10, 160, 320, 640, 1280};
		long[] time2 = new long[8];
		try {
			problem = Driver.parseMatchingProblem("src/student_inputs/small_inputs/4.in");
			// run a couple times to get cache optimizing out of the way
	        program.stableHiringGaleShapley(problem);
	        program.stableHiringGaleShapley(problem);
			startTime = System.nanoTime();
	        program.stableHiringGaleShapley(problem);
	        time2[0] = System.nanoTime() - startTime;

			problem = Driver.parseMatchingProblem("src/student_inputs/small_inputs/6.in");
			// run a couple times to get cache optimizing out of the way
	        program.stableHiringGaleShapley(problem);
	        program.stableHiringGaleShapley(problem);
			startTime = System.nanoTime();
	        program.stableHiringGaleShapley(problem);
	        time2[1] = System.nanoTime() - startTime;

			problem = Driver.parseMatchingProblem("src/student_inputs/small_inputs/8.in");
			// run a couple times to get cache optimizing out of the way
	        program.stableHiringGaleShapley(problem);
	        program.stableHiringGaleShapley(problem);
			startTime = System.nanoTime();
	        program.stableHiringGaleShapley(problem);
	        time2[2] = System.nanoTime() - startTime;

			problem = Driver.parseMatchingProblem("src/student_inputs/small_inputs/10.in");
			// run a couple times to get cache optimizing out of the way
	        program.stableHiringGaleShapley(problem);
	        program.stableHiringGaleShapley(problem);
			startTime = System.nanoTime();
	        program.stableHiringGaleShapley(problem);
	        time2[3] = System.nanoTime() - startTime;

			problem = Driver.parseMatchingProblem("src/student_inputs/large_inputs/160.in");
			// run a couple times to get cache optimizing out of the way
	        program.stableHiringGaleShapley(problem);
	        program.stableHiringGaleShapley(problem);
			startTime = System.nanoTime();
	        program.stableHiringGaleShapley(problem);
	        time2[4] = System.nanoTime() - startTime;

			problem = Driver.parseMatchingProblem("src/student_inputs/large_inputs/320.in");
			// run a couple times to get cache optimizing out of the way
	        program.stableHiringGaleShapley(problem);
	        program.stableHiringGaleShapley(problem);
			startTime = System.nanoTime();
	        program.stableHiringGaleShapley(problem);
	        time2[5] = System.nanoTime() - startTime;

			problem = Driver.parseMatchingProblem("src/student_inputs/large_inputs/640.in");
			// run a couple times to get cache optimizing out of the way
	        program.stableHiringGaleShapley(problem);
	        program.stableHiringGaleShapley(problem);
			startTime = System.nanoTime();
	        program.stableHiringGaleShapley(problem);
	        time2[6] = System.nanoTime() - startTime;

			problem = Driver.parseMatchingProblem("src/student_inputs/large_inputs/1280.in");
			// run a couple times to get cache optimizing out of the way
	        program.stableHiringGaleShapley(problem);
	        program.stableHiringGaleShapley(problem);
			startTime = System.nanoTime();
	        program.stableHiringGaleShapley(problem);
	        time2[7] = System.nanoTime() - startTime;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// print
		System.out.println("Brute Force:");
		System.out.println("n: "+Arrays.toString(n));
		System.out.println("time: "+Arrays.toString(time));
		System.out.println("\n\nGale-Shapley:");
		System.out.println("n: "+Arrays.toString(n2));
		System.out.println("time: "+Arrays.toString(time2));
	}
	
	
	
}
