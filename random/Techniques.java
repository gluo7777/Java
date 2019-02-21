import java.util.Arrays;

public class Techniques{
    public static void main(String[] args) {
        int [] nums = {300,6,-200,5,234,900,-500,300,421};
        int k = 3;
        System.out.printf("Largest sum of %d consecutive elements in %s is %d", k, Arrays.toString(nums), largestSumOfKConsecutiveElements(nums, k));
    }

    /**
     * Sliding window
     * Can be used for problems that require evaluating a contiguous subset of elements (e.g. substring)
     */
    static int largestSumOfKConsecutiveElements(int [] nums, int k){
        if(k > nums.length) throw new IllegalArgumentException("k cannot be larger than number of nums elements.");
        // calculate initial window pane size
        int max = 0;
        for (int i = 0; i < k; i++) {
            max += nums[i];
        }
        // slide pane across window
        int windowSum = max;
        for (int i = k; i < nums.length; i++) {
            windowSum -= nums[i-k]; // remove at left end
            windowSum += nums[i]; // add to right end
            max = Math.max(max, windowSum);
        }
        return max;
    }
}