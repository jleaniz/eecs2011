/**
 * EECS 2011 - Assignment 1]
 * Implementation of the java List interface as per
 * the instructor specifications
 * 
 * @author Juan Leaniz Pittamiglio
 * @version 1.00, 11 Jan 2019
 */

import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.Test;

class ListTester {

	private int[] N = { 10, 100, 1000 };
	private long[] runningTimes = new long[14];

	/*
	 * Test the add() method by inserting elements at the begging of the lists
	 */
	void testAddBeginning(int n) {
		long start = 0, end = 0, diff = 0;
		FileList<Integer> filelist = new FileList<Integer>("AddBegin.list");
		ArrayList<Integer> arraylist = new ArrayList<Integer>();

		// test arraylist
		start = System.currentTimeMillis();
		for (int j = 0; j < n; j++) {
			arraylist.add(0, j);
		}
		end = System.currentTimeMillis();
		diff = end - start;
		runningTimes[0] = diff;

		// test filelist
		start = System.currentTimeMillis();
		for (int j = 0; j < n; j++) {
			filelist.add(0, j);
		}
		end = System.currentTimeMillis();
		diff = end - start;
		runningTimes[7] = diff;

		filelist.clear();

	}

	/*
	 * Test the add() method by inserting elements at the end of the lists
	 */
	void testAddEnd(int n) {
		long start = 0, end = 0, diff = 0;
		FileList<Integer> filelist = new FileList<Integer>("AddEnd.list");
		ArrayList<Integer> arraylist = new ArrayList<Integer>();

		// test arraylist
		start = System.currentTimeMillis();
		for (int j = 0; j < n; j++) {
			arraylist.add(arraylist.size(), j);
		}
		end = System.currentTimeMillis();
		diff = end - start;
		runningTimes[1] = diff;

		// test filelist
		start = System.currentTimeMillis();
		for (int j = 0; j < n; j++) {
			filelist.add(filelist.size(), j);
		}
		end = System.currentTimeMillis();
		diff = end - start;
		runningTimes[8] = diff;

		filelist.clear();

	}

	/*
	 * Test the add() method by inserting elements at random positions of the lists
	 */
	void testAddRnd(int n) {
		long start = 0, end = 0, diff = 0;
		FileList<Integer> filelist = new FileList<Integer>("AddRnd.list");
		ArrayList<Integer> arraylist = new ArrayList<Integer>();

		// test arraylist
		start = System.currentTimeMillis();
		for (int j = 0; j < n; j++) {
			if (arraylist.size() > 0)
				arraylist.add(new Random().nextInt(arraylist.size()), j);
			else
				arraylist.add(0, j);
		}
		end = System.currentTimeMillis();
		diff = end - start;
		runningTimes[2] = diff;

		// test filelist
		start = System.currentTimeMillis();
		for (int j = 0; j < n; j++) {
			if (filelist.size() > 0)
				filelist.add(new Random().nextInt(filelist.size()), j);
			else
				filelist.add(0, j);
		}
		end = System.currentTimeMillis();
		diff = end - start;
		runningTimes[9] = diff;

		filelist.clear();

	}

	/*
	 * Test the remove() method by inserting elements at the begging of the lists
	 */
	void testRemoveBginning(int n) {
		long start = 0, end = 0, diff = 0;
		FileList<Integer> filelist = new FileList<Integer>("RemoveBeg.list");
		ArrayList<Integer> arraylist = new ArrayList<Integer>();

		// test arraylist
		// -------------------------------
		for (int j = 0; j < n; j++) {
			arraylist.add(j);
		}
		start = System.currentTimeMillis();
		for (int j = 0; j < n; j++) {
			arraylist.remove(0);
		}
		end = System.currentTimeMillis();
		diff = end - start;
		runningTimes[3] = diff;
		// --------------------------------

		// test filelist
		// --------------------------------
		for (int j = 0; j < n; j++) {
			filelist.add(j);
		}
		start = System.currentTimeMillis();
		for (int j = 0; j < n; j++) {
			filelist.remove(0);
		}
		end = System.currentTimeMillis();
		diff = end - start;
		runningTimes[10] = diff;
		// --------------------------------
		filelist.clear();

	}

	/*
	 * Test the remove() method by inserting elements at random positions of the
	 * lists
	 */
	void testRemoveEnd(int n) {
		long start = 0, end = 0, diff = 0;
		FileList<Integer> filelist = new FileList<Integer>("RemoveEnd.list");
		ArrayList<Integer> arraylist = new ArrayList<Integer>();

		// test arraylist
		// -------------------------------
		for (int j = 0; j < n; j++) {
			arraylist.add(j);
		}
		start = System.currentTimeMillis();
		for (int j = 0; j < n; j++) {
			if (arraylist.size() > 1)
				arraylist.remove(arraylist.size() - 1);
			else
				arraylist.remove(0);
		}
		end = System.currentTimeMillis();
		diff = end - start;
		runningTimes[4] = diff;
		// --------------------------------

		// test filelist
		// --------------------------------
		for (int j = 0; j < n; j++) {
			filelist.add(j);
		}
		start = System.currentTimeMillis();
		for (int j = 0; j < n; j++) {
			if (filelist.size() > 1)
				filelist.remove(filelist.size() - 1);
			else
				filelist.remove(0);
		}
		end = System.currentTimeMillis();
		diff = end - start;
		runningTimes[11] = diff;
		// --------------------------------

		filelist.clear();
	}

	/*
	 * Test the remove() method by inserting elements at random positions of the
	 * lists
	 */
	void testRemoveRnd(int n) {
		long start = 0, end = 0, diff = 0;
		FileList<Integer> filelist = new FileList<Integer>("RemoveRnd.list");
		ArrayList<Integer> arraylist = new ArrayList<Integer>();

		// test arraylist
		// -------------------------------
		for (int j = 0; j < n; j++) {
			arraylist.add(j);
		}
		start = System.currentTimeMillis();
		for (int j = 0; j < n; j++) {
			if (arraylist.size() > 1)
				arraylist.remove(new Random().nextInt(arraylist.size() - 1));
			else
				arraylist.remove(0);
		}
		end = System.currentTimeMillis();
		diff = end - start;
		runningTimes[5] = diff;
		// --------------------------------

		// test filelist
		// --------------------------------
		for (int j = 0; j < n; j++) {
			filelist.add(j);
		}
		start = System.currentTimeMillis();
		for (int j = 0; j < n; j++) {
			int rnd = new Random().nextInt(filelist.size());
			// System.out.println("Rand: " + rnd);
			filelist.remove(rnd);
		}
		end = System.currentTimeMillis();
		diff = end - start;
		runningTimes[12] = diff;
		// --------------------------------
		filelist.clear();

	}

	/*
	 * Test the remove() method by inserting elements at random positions of the
	 * lists
	 */
	void testRemoveByValue(int n) {
		long start = 0, end = 0, diff = 0;
		FileList<Integer> filelist = new FileList<Integer>("RemoveValue.list");
		ArrayList<Integer> arraylist = new ArrayList<Integer>();

		// test arraylist
		// -------------------------------
		for (int j = 0; j < n; j++) {
			arraylist.add(j);
		}
		start = System.currentTimeMillis();
		for (int j = 0; j < n; j++) {
			if (arraylist.size() > 1)
				arraylist.remove(new Random().nextInt(arraylist.size()));
			else
				arraylist.remove(0);
		}
		end = System.currentTimeMillis();
		diff = end - start;
		runningTimes[6] = diff;
		// --------------------------------

		// test filelist
		// --------------------------------
		for (int j = 0; j <= n; j++) {
			filelist.add(j);
		}
		start = System.currentTimeMillis();
		for (int j = 0; j < n; j++) {
			if (filelist.size() > 1)
				filelist.remove(new Random().nextInt(filelist.size()));
			else
				filelist.remove(0);
		}
		end = System.currentTimeMillis();
		diff = end - start;
		runningTimes[13] = diff;
		// --------------------------------
		filelist.clear();

	}

	@Test
	void testAll() {
		for (int i = 0; i < N.length; i++) {
			System.out
					.println("N = " + N[i] + "| ms to \tIns. start \tend	rnd;\tRmv. start \tend \trnd \tRmv. by value");
			testAddBeginning(N[i]);
			testAddRnd(N[i]);
			testAddEnd(N[i]);
			testRemoveBginning(N[i]);
			testRemoveRnd(N[i]);
			testRemoveEnd(N[i]);
			testRemoveByValue(N[i]);
			System.out.println("ArrayList \t" + '\t' + runningTimes[0] + '\t' + runningTimes[1] + '\t' + runningTimes[2]
					+ '\t' + runningTimes[3] + "\t\t" + runningTimes[4] + '\t' + runningTimes[5] + '\t'
					+ runningTimes[6]);
			System.out.println("FileList \t" + '\t' + runningTimes[7] + '\t' + runningTimes[8] + '\t' + runningTimes[9]
					+ '\t' + runningTimes[10] + "\t\t" + runningTimes[11] + '\t' + runningTimes[12] + '\t'
					+ runningTimes[13] + '\t');
		}

	}
}
