import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TestSuiteTies {

	@Test
	public void runMainSmallInputs() { // alternate Gale-Shapley and brute force
		try {
			System.out.println("TESTING ----------------------------------    4.in");
			Driver.main(new String[]{"-b", "src/student_inputs/small_inputs/4.in"});
			Driver.main(new String[]{"-g", "src/student_inputs/small_inputs/4.in"});

			System.out.println("TESTING ----------------------------------    6.in");
			Driver.main(new String[]{"-b", "src/student_inputs/small_inputs/6.in"});
			Driver.main(new String[]{"-g", "src/student_inputs/small_inputs/6.in"});

			System.out.println("TESTING ----------------------------------    8.in");
			Driver.main(new String[]{"-b", "src/student_inputs/small_inputs/8.in"});
			Driver.main(new String[]{"-g", "src/student_inputs/small_inputs/8.in"});

			System.out.println("TESTING ----------------------------------   10.in");
			Driver.main(new String[]{"-b", "src/student_inputs/small_inputs/10.in"});
			Driver.main(new String[]{"-g", "src/student_inputs/small_inputs/10.in"});
		} catch (Exception e) {
			e.printStackTrace();
			fail("Encountered exception: "+e.getMessage());
		}
	}

	@Test
	public void runMainLargeInputs() { // Gale-Shapley only
		try {
			System.out.println("TESTING ----------------------------------  160.in");
			Driver.main(new String[]{"-g", "src/student_inputs/large_inputs/160.in"});
			
			System.out.println("TESTING ----------------------------------  320.in");
			Driver.main(new String[]{"-g", "src/student_inputs/large_inputs/320.in"});
			
			System.out.println("TESTING ----------------------------------  640.in");
			Driver.main(new String[]{"-g", "src/student_inputs/large_inputs/640.in"});
			
			System.out.println("TESTING ---------------------------------- 1280.in");
			Driver.main(new String[]{"-g", "src/student_inputs/large_inputs/1280.in"});
		} catch (Exception e) {
			e.printStackTrace();
			fail("Encountered exception: "+e.getMessage());
		}
	}
	
	@Test
	public void testBF() {
		Program1 program = new Program1();
		Matching unMatched;
		Matching BFMatching;
		
		try {
			unMatched = Driver.parseMatchingProblem("src/student_inputs/small_inputs/4.in");
	        BFMatching = program.stableMarriageBruteForce(unMatched);
	        assertTrue(program.isStableMatching(BFMatching));
	        
			unMatched = Driver.parseMatchingProblem("src/student_inputs/small_inputs/6.in");
	        BFMatching = program.stableMarriageBruteForce(unMatched);
	        assertTrue(program.isStableMatching(BFMatching));
	        
			unMatched = Driver.parseMatchingProblem("src/student_inputs/small_inputs/8.in");
	        BFMatching = program.stableMarriageBruteForce(unMatched);
	        assertTrue(program.isStableMatching(BFMatching));
	        
			unMatched = Driver.parseMatchingProblem("src/student_inputs/small_inputs/10.in");
	        BFMatching = program.stableMarriageBruteForce(unMatched);
	        assertTrue(program.isStableMatching(BFMatching));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Encountered exception: "+e.getMessage());
		}
	}
	
	@Test
	public void testGS() {
		Program1 program = new Program1();
		Matching unMatched;
		Matching BFMatching;
		
		try {
			unMatched = Driver.parseMatchingProblem("src/student_inputs/small_inputs/4.in");
	        BFMatching = program.stableHiringGaleShapley(unMatched);
	        assertTrue(program.isStableMatching(BFMatching));
	        
			unMatched = Driver.parseMatchingProblem("src/student_inputs/small_inputs/6.in");
	        BFMatching = program.stableHiringGaleShapley(unMatched);
	        assertTrue(program.isStableMatching(BFMatching));
	        
			unMatched = Driver.parseMatchingProblem("src/student_inputs/small_inputs/8.in");
	        BFMatching = program.stableHiringGaleShapley(unMatched);
	        assertTrue(program.isStableMatching(BFMatching));
	        
			unMatched = Driver.parseMatchingProblem("src/student_inputs/small_inputs/10.in");
	        BFMatching = program.stableHiringGaleShapley(unMatched);
	        assertTrue(program.isStableMatching(BFMatching));
	        
			unMatched = Driver.parseMatchingProblem("src/student_inputs/large_inputs/160.in");
	        BFMatching = program.stableHiringGaleShapley(unMatched);
	        assertTrue(program.isStableMatching(BFMatching));
	        
			unMatched = Driver.parseMatchingProblem("src/student_inputs/large_inputs/320.in");
	        BFMatching = program.stableHiringGaleShapley(unMatched);
	        assertTrue(program.isStableMatching(BFMatching));
	        
			unMatched = Driver.parseMatchingProblem("src/student_inputs/large_inputs/640.in");
	        BFMatching = program.stableHiringGaleShapley(unMatched);
	        assertTrue(program.isStableMatching(BFMatching));
	        
			unMatched = Driver.parseMatchingProblem("src/student_inputs/large_inputs/1280.in");
	        BFMatching = program.stableHiringGaleShapley(unMatched);
	        assertTrue(program.isStableMatching(BFMatching));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Encountered exception: "+e.getMessage());
		}
	}
	
	@Test
	public void testGSEqualsBF() {
		Program1 program = new Program1();
		Matching unMatched;
		Matching BFMatching;
		Matching GSMatching;
		
		try {
			unMatched = Driver.parseMatchingProblem("src/student_inputs/small_inputs/4.in");
	        BFMatching = program.stableMarriageBruteForce(unMatched);
	        GSMatching = program.stableHiringGaleShapley(unMatched);
	        assertArrayEquals(BFMatching.getWorkerMatching().toArray(new Integer[]{}), GSMatching.getWorkerMatching().toArray(new Integer[]{}));
	        
			unMatched = Driver.parseMatchingProblem("src/student_inputs/small_inputs/6.in");
	        BFMatching = program.stableMarriageBruteForce(unMatched);
	        GSMatching = program.stableHiringGaleShapley(unMatched);
	        assertArrayEquals(BFMatching.getWorkerMatching().toArray(new Integer[]{}), GSMatching.getWorkerMatching().toArray(new Integer[]{}));
	        
			unMatched = Driver.parseMatchingProblem("src/student_inputs/small_inputs/8.in");
	        BFMatching = program.stableMarriageBruteForce(unMatched);
	        GSMatching = program.stableHiringGaleShapley(unMatched);
	        assertArrayEquals(BFMatching.getWorkerMatching().toArray(new Integer[]{}), GSMatching.getWorkerMatching().toArray(new Integer[]{}));
	        
			unMatched = Driver.parseMatchingProblem("src/student_inputs/small_inputs/10.in");
	        BFMatching = program.stableMarriageBruteForce(unMatched);
	        GSMatching = program.stableHiringGaleShapley(unMatched);
	        assertArrayEquals(BFMatching.getWorkerMatching().toArray(new Integer[]{}), GSMatching.getWorkerMatching().toArray(new Integer[]{}));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Encountered exception: "+e.getMessage());
		}
	}
	
	@Test
	public void testIsStableMatching() {
		final int n = 8;
		
		ArrayList<Integer> answer = new ArrayList<Integer>();
		for (int i = 0; i < n; i++)
			answer.add(i);
		
        ArrayList<ArrayList<Integer>> jobPref = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> workerPref = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < n; i++) {
        	jobPref.add(new ArrayList<Integer>());
        	workerPref.add(new ArrayList<Integer>());
        	for (int j = 0; j < n; j++) {
        		jobPref.get(i).add(j);
        		workerPref.get(i).add(j);
        	}
        }
		Matching m = new Matching(n, n, jobPref, workerPref, null, null);

        Permutation p = new Permutation(n, n);
        Program1 program = new Program1();

        Matching matching;
        while ((matching = p.getNextMatching(m)) != null) {
        	if (matching.getWorkerMatching().equals(answer)) {
                assertTrue(program.isStableMatching(matching));
        	} else {
                assertFalse(program.isStableMatching(matching));
        	}
        }
	}
	
	@Test
	public void testHardWorkersMatchedToFullTimeJobs() {
		Program1 program = new Program1();
		Matching unMatched;
		Matching matching;
		
		try {
			unMatched = Driver.parseMatchingProblem("src/student_inputs/small_inputs/4.in");
	        matching = program.stableHiringGaleShapley(unMatched);
	        testHardWorkersMatchedToFullTimeJobs(matching,"GS 4");
	        
			unMatched = Driver.parseMatchingProblem("src/student_inputs/small_inputs/6.in");
	        matching = program.stableHiringGaleShapley(unMatched);
	        testHardWorkersMatchedToFullTimeJobs(matching,"GS 6");
	        
			unMatched = Driver.parseMatchingProblem("src/student_inputs/small_inputs/8.in");
	        matching = program.stableHiringGaleShapley(unMatched);
	        testHardWorkersMatchedToFullTimeJobs(matching,"GS 8");
	        
			unMatched = Driver.parseMatchingProblem("src/student_inputs/small_inputs/10.in");
	        matching = program.stableHiringGaleShapley(unMatched);
	        testHardWorkersMatchedToFullTimeJobs(matching,"GS 10");
	        
			unMatched = Driver.parseMatchingProblem("src/student_inputs/large_inputs/160.in");
	        matching = program.stableHiringGaleShapley(unMatched);
	        testHardWorkersMatchedToFullTimeJobs(matching,"GS 160");
	        
			unMatched = Driver.parseMatchingProblem("src/student_inputs/large_inputs/320.in");
	        matching = program.stableHiringGaleShapley(unMatched);
	        testHardWorkersMatchedToFullTimeJobs(matching,"GS 320");
	        
			unMatched = Driver.parseMatchingProblem("src/student_inputs/large_inputs/640.in");
	        matching = program.stableHiringGaleShapley(unMatched);
	        testHardWorkersMatchedToFullTimeJobs(matching,"GS 640");
	        
			unMatched = Driver.parseMatchingProblem("src/student_inputs/large_inputs/1280.in");
	        matching = program.stableHiringGaleShapley(unMatched);
	        testHardWorkersMatchedToFullTimeJobs(matching,"GS 1280");

			unMatched = Driver.parseMatchingProblem("src/student_inputs/small_inputs/4.in");
			matching = program.stableMarriageBruteForce(unMatched);
	        testHardWorkersMatchedToFullTimeJobs(matching,"BF 4");
	        
			unMatched = Driver.parseMatchingProblem("src/student_inputs/small_inputs/6.in");
			matching = program.stableMarriageBruteForce(unMatched);
	        testHardWorkersMatchedToFullTimeJobs(matching,"BF 6");
	        
			unMatched = Driver.parseMatchingProblem("src/student_inputs/small_inputs/8.in");
			matching = program.stableMarriageBruteForce(unMatched);
	        testHardWorkersMatchedToFullTimeJobs(matching,"BF 8");
	        
			unMatched = Driver.parseMatchingProblem("src/student_inputs/small_inputs/10.in");
			matching = program.stableMarriageBruteForce(unMatched);
	        testHardWorkersMatchedToFullTimeJobs(matching,"BF 10");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Encountered exception: "+e.getMessage());
		}
	}
	
	private void testHardWorkersMatchedToFullTimeJobs(Matching m, String msg) {
		int fullTimeCnt = 0;
		int hardWorkerCnt = 0;
		for (int i = 0; i < m.getJobCount(); i++)
			if (m.getJobFulltime().get(i))
				fullTimeCnt++;
		for (int i = 0; i < m.getWorkerCount(); i++)
			if (m.getWorkerHardworking().get(i))
				hardWorkerCnt++;
//		System.out.println(msg+"  "+fullTimeCnt+" "+hardWorkerCnt);
		
		int mismatches = 0;
		for (int w = 0; w < m.getWorkerCount(); w++) {
			int j = m.getWorkerMatching().get(w);
			if (m.getJobFulltime().get(j) != m.getWorkerHardworking().get(w))
//				fail(msg+" worker "+w+" "+m.getWorkerHardworking().get(w)+" incorrectly matched with job "+j+" "+m.getJobFulltime().get(j));
				mismatches++;
		}
		assertTrue(mismatches == Math.abs(fullTimeCnt-hardWorkerCnt));
	}
	
	@Test
	public void testGS2() {
		int n = 1000;
		
		ArrayList<ArrayList<Integer>> jobsPrefs = new ArrayList<ArrayList<Integer>>(n);
		ArrayList<ArrayList<Integer>> workersPrefs = new ArrayList<ArrayList<Integer>>(n);
		for (int i = 0; i < n; i++) {
			jobsPrefs.add(new ArrayList<Integer>(n));
			workersPrefs.add(new ArrayList<Integer>(n));
			for (int j = 0; j < n; j++) {
				workersPrefs.get(i).add(j);
				jobsPrefs.get(i).add(n-1-j);
			}
		}
		
		ArrayList<Integer> answer = new ArrayList<Integer>();
		for (int i = 0; i < n; i++)
			answer.add(n-1-i);
		
		Matching m = new Matching(n,n,jobsPrefs,workersPrefs,null,null);
		Program1 p = new Program1();
		m = p.stableHiringGaleShapley(m);
		assertArrayEquals(m.getWorkerMatching().toArray(new Integer[]{}), answer.toArray(new Integer[]{}));
	}
	
	@Test
	public void testGS3() {
		int n = 1000;
		
		ArrayList<ArrayList<Integer>> jobsPrefs = new ArrayList<ArrayList<Integer>>(n);
		ArrayList<ArrayList<Integer>> workersPrefs = new ArrayList<ArrayList<Integer>>(n);
		for (int i = 0; i < n; i++) {
			jobsPrefs.add(new ArrayList<Integer>(n));
			workersPrefs.add(new ArrayList<Integer>(n));
			for (int j = 0; j < n; j++) {
				workersPrefs.get(i).add(n-1-j);
				jobsPrefs.get(i).add(n-1-j);
			}
		}
		
		ArrayList<Integer> answer = new ArrayList<Integer>();
		for (int i = 0; i < n; i++)
			answer.add(i);
		
		Matching m = new Matching(n,n,jobsPrefs,workersPrefs,null,null);
		Program1 p = new Program1();
		m = p.stableHiringGaleShapley(m);
		assertArrayEquals(m.getWorkerMatching().toArray(new Integer[]{}), answer.toArray(new Integer[]{}));
	}

	
	
	
	
	
	
	
	
	public static void main(String args[]) {
		Program1.printPlotData();
	}
}
