package week4;

import java.util.Random;

/**
 * Created by VGN on 5/25/16.
 */
public class StressTestInversions {
    public static void main(String[] args){
        verify(10000);
    }
    private static void verify(int size){
        while (true){
            Long a[] = generateRandomArray(size);
            Long[] b = new Long[a.length];
            Long[]c = new Long[a.length];
            for (int i=0; i < b.length; i++){
                b[i] = a[i];
                c[i] = a[i];
            }
            long val = Inversions.getNumberOfInversionsBruteForce(a);
            long val2 = Inversions.getNumberOfInversions(b);
            if (val != val2){
                System.out.println("ERROR: Failure to validate");
                System.out.println("Brute Force " +val);
                System.out.println("Divide&Conquer " + val2);
                System.out.println("array values" );
                for (int i=0; i< a.length; i++){
                    System.out.println(c[i]);
                }
                break;
            }
            System.out.println("Validated");
        }
    }

    private static Long[] generateRandomArray(int size){
        Random rn = new Random();
        Long[] a = new Long[size];
        for (int i = 0; i < size; i++) {
            long value = (rn.nextLong());
            //int value = rn.nextInt(10);
            a[i] = value;
        }
        return a;
    }
}
