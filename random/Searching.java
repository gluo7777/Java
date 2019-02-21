import java.util.Arrays;
import java.util.PriorityQueue;

public class Searching{
    public static void main(String[] args) {
        int [] nums = {-5,3,-7,10,-2,8,0,-7,8};
        int target = -7;
        Arrays.sort(nums);
        System.out.printf("Searching for %d in %s = %d\n", target, Arrays.toString(nums), binarySearch(nums, target));
        int k = 7;
        System.out.printf("%dth largest element in %s = %d\n", k, Arrays.toString(nums), kthLargestElement(nums, k));
        System.out.printf("Smallest element index in %s = %d\n", Arrays.toString(nums), searchSmallestElementOfRotatedSortedArray(nums));
        int [] rotatedSortedArray = {0, 3, 8, 8, 10, -7, -7, -5, -2};
        System.out.printf("Smallest element index in %s = %d\n", Arrays.toString(rotatedSortedArray), searchSmallestElementOfRotatedSortedArray(rotatedSortedArray));
    }

    /**
     * Binary Search can be implemented with/out recursion.
     * This approach is non-recursive.
     */
    static int binarySearch(int [] arr, int key){
        int l = 0, r = arr.length - 1;
        while(l <= r){
            int m = (r - l) / 2 + l;
            if(arr[m] == key) return m;
            else if(arr[m] < key) l = m + 1;
            else r = m - 1;
        }
        return -1;
    }

    /**
     * Using priority queue
     */
    static int kthLargestElement(int [] arr, int k){
        if(k > arr.length) throw new IllegalArgumentException("k cannot be larger than number of array elements.");
        PriorityQueue<Integer> max_heap = new PriorityQueue<Integer>(arr.length, (a,b) -> b - a);
        for (int var : arr) {
            max_heap.offer(var);
        }
        for (int i = 0; i < k - 1; i++) {
            max_heap.poll();
        }
        return max_heap.poll();
    }

    // use binary search
    static int searchSmallestElementOfRotatedSortedArray(int [] arr){
        // lo == hi when we've found the smallest element
        int lo = 0, hi = arr.length - 1;
        while(lo < hi){
            int mid = (hi - lo) / 2 + lo;
            if(arr[mid] > arr[hi]) lo = mid + 1;    // shift left when right side is in sorted order
            else hi = mid;                      // shift right when otherwise
        }
        return hi >= 0 ? hi : -1;
    }
}