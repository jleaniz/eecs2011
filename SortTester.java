import java.util.Arrays;
import java.util.Random;

public class SortTester {

	private static int[] N = { 10, 100, 1000, 10000, 100000, 1000000, 10000000 };
	
    private static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i < n; ++i)
            System.out.print(arr[i]+" ");
        System.out.println();
    }
	
	public static void main(String[] args) {

		long start = 0, end = 0, diff = 0;
        Assignment3 hs = new Assignment3();
        
        // loop for each element of N
		for (int i = 0; i < N.length; i++) {
			// create two new arrays with N[i] elements
			int arr[] = new int[N[i]];
			int qs_arr[] = new int[N[i]];

			// fill the array with random integers < N[i]
			for (int j = 0; j < N[i]; j++) {
				int rnd = new Random().nextInt(arr.length);
				arr[j] = rnd;
			}
			System.arraycopy(arr, 0, qs_arr, 0, arr.length);
			
			// test heapsort
			// -------------------------------
			System.out.println("n = " + N[i] + ":");
			start = System.currentTimeMillis();
			hs.sort(arr);
			end = System.currentTimeMillis();
			printArray(arr);
			diff = end - start;
			System.out.println("heapsort: " + diff + "ms");
			// --------------------------------

			// test quicksort
			// -------------------------------
			start = System.currentTimeMillis();
			Arrays.sort(qs_arr);
			end = System.currentTimeMillis();
			diff = end - start;
			System.out.println("quicksort: " + diff + "ms");
			// --------------------------------
		}
	}
}
