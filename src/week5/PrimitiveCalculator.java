package week5;

import java.util.Scanner;

/**
 * Created by vnagarajan on 5/31/16.
 */
public class PrimitiveCalculator {

    public static int minOperations(int input){
        return computeValueArray(input)[input];
    }

    public static int[] reconstructSoln(int input){
        int[] val = computeValueArray(input);
        int[] soln = new int[input];
        int counter = soln.length-1;
        int value = input;
        int index = counter+1;
        while (value >= 1){
            soln[counter] = value;
            if (val[index] == val[index-1] + 1){
                value = value -1;
                index = index -1;
            } else if (value%2 == 0 && val[index] == val[index/2] + 1 ){
                value = value/2;
                index = index/2;
            }
            else {
                value = value/3;
                index = index/3;
            }
            counter --;
        }
        return soln;
    }
    private static int[] computeValueArray(int input){
        int[] val = new int[input+1];
        val[0] = 0;
        val[1] = 0;
        for (int i=2; i < val.length; i++){
            int val_add = val[i-1] + 1;
            int val_mul_two = -1;
            int val_mul_three = -1;
            if ( i %2 == 0){
                val_mul_two = val[i/2] + 1;
            }
            if (i % 3 == 0){
                val_mul_three = val[i/3] + 1;
            }
            val[i] = get_min(val_add, val_mul_two, val_mul_three);
        }
        return val;
    }

    private static int get_min(int value1, int value2, int value3){
        if (value2 == -1 && value3 == -1){
            return value1;
        }
        if (value2 == -1){
            if (value1 <= value3){
                return value1;
            }
            return value3;
        }
        if (value3 == -1){
            if (value1 <= value2){
                return value1;
            }
            return value2;
        }
        if (value1 <= value2 && value1 <= value3){
            return value1;
        }
        if (value2 <= value1 && value2 <= value3){
            return value2;
        }
        return value3;
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        System.out.println(PrimitiveCalculator.minOperations(input));
        int[] soln = PrimitiveCalculator.reconstructSoln(input);
        for (int i =0; i < soln.length; i++){
            if (soln[i] != 0){
                System.out.print(soln[i] + " ");
            }
        }
    }

}