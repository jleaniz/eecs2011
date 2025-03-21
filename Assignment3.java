
public class Assignment3 {

    public void sort(int[] a)
    {
        int n = a.length;
 
      	// Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
          heapify(a, n, i);
        }
 
		// Heap sort
        for (int i=n-1; i>=0; i--)
        {
            int temp = a[0];
            a[0] = a[i];
            a[i] = temp;
 
          	// Heapify root element
            heapify(a, i, 0);
        }
    }
 
    // To heapify a subtree rooted with node i which is 
    // an index in arr[]. n is size of heap 
    private void heapify(int arr[], int n, int i) 
    { 
        int largest = i;  // Initialize largest as root 
        int l = 2*i + 1;  // left = 2*i + 1 
        int r = 2*i + 2;  // right = 2*i + 2 
  
        // If left child is larger than root 
        if (l < n && arr[l] > arr[largest]) 
            largest = l; 
  
        // If right child is larger than largest so far 
        if (r < n && arr[r] > arr[largest]) 
            largest = r; 
  
        // If largest is not root 
        if (largest != i) 
        { 
            int swap = arr[i]; 
            arr[i] = arr[largest]; 
            arr[largest] = swap; 
  
            // Recursively heapify the affected sub-tree 
            heapify(arr, n, largest); 
        } 
    } 

}
