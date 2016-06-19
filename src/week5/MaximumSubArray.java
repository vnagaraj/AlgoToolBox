package week5;

/**
 * Created by VGN on 6/1/16.
 */
public class MaximumSubArray {
    public static int maxSubArray(int[] nums) {
        int[] vals = new int[nums.length];
        vals[0] = nums[0]; //base case
        for (int i=1; i < nums.length; i++){
            vals[i] = vals[i-1];
            int value = vals[i] + nums[i];
            if (value > vals[i]){
                vals[i] = value;
            }
        }
        return vals[nums.length-1];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(nums));
    }
}
