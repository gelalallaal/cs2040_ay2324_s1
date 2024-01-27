import java.io.*;
import java.util.*;

class SortingDemo {
  private static void swap(int a[], int i, int j) { // swap array elements i and j
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }


  private static void bubbleSort(int a[], int N) { // the standard version
    for (; N > 0; --N) // N iterations
      for (int i = 0; i < N-1; ++i) // except the last, O(N)
        if (a[i] > a[i+1]) // not in non-decreasing order
          swap(a, i, i+1); // swap in O(1)
  }


  private static void merge(int a[], int low, int mid, int high) {
    // subarray1 = a[low..mid], subarray2 = a[mid+1..high], both sorted
    int N = high-low+1;
    int[] b = new int[N]; // discuss: why do we need a temporary array b?
    int left = low, right = mid+1, bIdx = 0;
    while (left <= mid && right <= high) // the merging
      b[bIdx++] = (a[left] <= a[right]) ? a[left++] : a[right++];
    while (left <= mid) b[bIdx++] = a[left++]; // leftover, if any
    while (right <= high) b[bIdx++] = a[right++]; // leftover, if any
    for (int k = 0; k < N; ++k) a[low+k] = b[k]; // copy back
  }

  private static void mergeSort(int a[], int low, int high) {
    // the array to be sorted is a[low..high]
    if (low < high) { // base case: low >= high (0 or 1 item)
      int mid = (low+high) / 2; 
      mergeSort(a, low  , mid ); // divide into two halves
      mergeSort(a, mid+1, high); // then recursively sort them
      merge(a, low, mid, high); // conquer: the merge routine
    }
  }


  private static int partition(int a[], int i, int j) {
    // ================== the only addition for Randomized Quick Sort
    Random rand = new Random();
    int r = i + rand.nextInt(j-i+1); // a random index between [i..j]
    swap(a, i, r); // tada
    // ==================
    int p = a[i]; // p is the pivot
    int m = i; // S1 and S2 are initially empty
    for (int k = i+1; k <= j; ++k) { // explore the unknown region
      if ((a[k] < p) || ((a[k] == p) && (rand.nextInt(2) == 0))) { // case 2 (PATCHED solution to avoid TLE O(N^2) on input array with identical values)
        ++m;
        swap(a, k, m); // manual swap function
      } // notice that we do nothing in case 1: a[k] > p
    }
    swap(a, i, m); // final step, swap pivot with a[m]
    return m; // return the index of pivot, to be used by Quick Sort
  }

  private static void quickSort(int a[], int low, int high) {
    if (low < high) {
      int pivotIdx = partition(a, low, high); // O(N)
      // a[low..high] ~> a[low..pivotIdxâ€“1], pivot, a[pivotIdx+1..high]
      quickSort(a, low, pivotIdx-1); // recursively sort left subarray
      // a[pivotIdx] = pivot is already sorted after partition
      quickSort(a, pivotIdx+1, high); // then sort right subarray
    }
  }
}
