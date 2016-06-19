package week3; /**
 * Created by vnagarajan on 5/19/16.
 */
import java.util.Scanner;

public class Change {
    private static int getChange(int n) {
        int[] denominations = new int[]{10,5,1};
        int min_coins = 0;
        int current_val = 0;
        while(current_val <  n){
            int index = 0;
            while (current_val + denominations[index] > n){
                index += 1;
            }
            current_val += denominations[index];
            min_coins +=1;
        }
        return min_coins;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(getChange(n));

    }
}