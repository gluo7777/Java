import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

public class Sorting{

    public static String debug = "n/a";

    public static void main(String[] args) {
        int [] nums = {-5,3,-7,10,-2,8,0,-7,8};
        // insertionSort(nums);
        // debug = "bucketSort";
        // nums = new int []{5,3,7,10,2,8,0,7,8};
        // bucketSort(nums, 4);
        quickSort(nums);
        System.out.println(Arrays.toString(nums));
        int [] A = {1,2,3,4,5,6,0,0,0,0,0,0,0,0,0};
        int endA = 5;
        int [] B = {-5,-4,0,1,3};
        sortBIntoA(A, B, endA);
        System.out.println("Sorted A: " + Arrays.toString(A));
    }

    /**
     * Insertion Sort
     * Time: O(n^2)
     * Space: O(1)
     */
    static void insertionSort(int [] arr){
        for (int i = 1; i < arr.length; i++)
            for (int j = i; j >= 1 && arr[j-1] > arr[j]; j--)
                swap(arr, j, j-1);
    }

    static void swap(int [] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Bubble Sort
     * Time: O(n + k) where n = input size and k = number of buckets
     * Worst: O(n^2) if all elements sorted into one bucket and sorted with insertion sort
     * Best: O(n) if n == k and all elements sorted into different buckets.
     * Space: O(n * k)
     */
     static void bucketSort(int [] arr, int k){
        if(arr == null || arr.length == 0) return;
        // find maximum value in arr O(n)
        int max = Integer.MIN_VALUE;
        for (int var : arr) max = Math.max(max, var);
        // create k buckets (lists) of ceiling(arr.length / k) size each or simply use a list
        ArrayList<Integer> [] buckets = new ArrayList[k];
        // sort into buckets
        for (int var : arr) {
            int index = (int) Math.floor( 1.0 * var/max * (k - 1) );
            buckets[index] = buckets[index] == null ? new ArrayList<Integer>() : buckets[index];
            buckets[index].add(var);
        }
        // apply insertion sort or w/e sorting on each bucket
        int i = 0;
        for (ArrayList<Integer> bucket : buckets) {
            if(bucket != null) {
                if("bucketSort".equals(debug)) System.out.println("bucket: " + bucket.toString());
                Collections.sort(bucket);
                // insert back into array
                for (Integer var : bucket) arr[i++] = var;
            }
        }
     }

     /**
      * Time:
      * Worst: O(n^2) when all elements moved to one side of pivot
      * Average: O(nlogn)
      */
     static void quickSort(int [] arr){ if(arr != null) quickSort(arr, 0, arr.length-1); }
     static void quickSort(int [] arr, int l, int r){ 
        if(l < r){
            int pivot = partition(arr, l, r);
            quickSort(arr, l, pivot - 1);
            quickSort(arr, pivot + 1, r);
        }
     }

     /**
      * O(l-r+1)
      * different ways to calculate pivot e.g. last element
      * after partitioning, pivot will be moved into correct position
      */
     static int partition(int [] arr, int l, int r){
        // calculate pivot and move it to last place
        int p = getPivotIndex(arr, l, r), pivot = arr[p];
        swap(arr, p, r);
        // use left pointer to track last swapped element
        int left = l - 1; // start at -1 so it'll move into 0
        for (int i = l; i <= r - 1; i++) {
            if(arr[i] <= pivot){
                swap(arr, ++left, i);
            }
        }
        // move pivot to correct position
        // such that all elements left of pivot < pivot and right > pivot
        swap(arr, ++left, r);
        return left;
     }

     static int getPivotIndex(int [] arr, int l, int r){
         return r;
     }

     static void sortBIntoA(int [] A, int [] B, int endA){
        int n = endA + B.length;
        if(A.length < n) throw new IllegalArgumentException("A does not contain enough space to hold B");
        int i=endA, j=B.length-1;
        while (i >= 0 && j >= 0){
            if(A[i] > B[j]) A[n--] = A[i--];
            else A[n--] = B[j--];
        }
        // no need to move remaining A elements, since they’ll be in the right position
        while (j >= 0) A[n--] = B[j--];        
     }
}