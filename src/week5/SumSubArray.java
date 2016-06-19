package week5;

/**
 * Created by VGN on 6/3/16.
 */
public class SumSubArray {

    public void findContSum(int[] input, int targetSum){
        boolean[][] vals = new boolean[input.length+1][targetSum];
        for (int j =1; j < vals[0].length; j++){
                vals[0][j] = false;
        }
        for (int j =0; j < vals.length; j++){
            vals[j][0] = true;
        }

    }
}
