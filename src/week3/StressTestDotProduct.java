package week3;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by VGN on 5/21/16.
 */
public class StressTestDotProduct {
    public static void main(String[] args){
        verify(3);
    }

    private static void verify(int size){
        while (true){
            int a[] = generateRandomArray(size);
            int b[] = generateRandomArray(size);
            long minDotProd = minDotProduct(a, b);
            long minDotProdBruteForce = minDotProductBruteForce(a, b);
            if (minDotProd != minDotProdBruteForce){
                System.out.println("ERROR: Failure to validate");
                System.out.println("minProd " + minDotProd);
                System.out.println("minProdBrute " + minDotProdBruteForce);
                System.out.println("a values" );
                for (int i=0; i< a.length; i++){
                    System.out.println(a[i]);
                }
                System.out.println("b values");
                for (int i=0; i< b.length; i++){
                    System.out.println(b[i]);
                }
                break;
            }
            System.out.println("Validated");
        }
    }

    private static int[] generateRandomArray(int size){
        Random rn = new Random();
        int[] a = new int[size];
        for (int i = 0; i < size; i++) {
           int value = (rn.nextInt(65536)-32768);
            //int value = rn.nextInt(10);
            a[i] = value;
        }
        return a;
    }

    private static long minDotProduct(int[] a, int[] b) {
        assert a.length == b.length; // length of a and b are same
        BigInteger result = new BigInteger(String.valueOf(0));
        Arrays.sort(a);
        Arrays.sort(b);
        int length = a.length;
        int i =0; //pointer to array b
        int j = length-1; //pointer to array a
        while (true){
            if (i == length || j < 0){
                break;
            }
            BigInteger bBig = new BigInteger(String.valueOf(b[i++]));
            BigInteger aBig = new BigInteger(String.valueOf(a[j--]));
            BigInteger val = bBig.multiply(aBig);
            result = result.add(val);
        }
        return result.longValue();
    }

    private static long minDotProductBruteForce(int[] a, int[] b) {
        ArrayList<ArrayList<Integer>> arrays = permute(b);
        assert a.length == b.length; // length of a and b are same
        long result = Integer.MAX_VALUE;
        int n = a.length;
        for (int array =0; array < arrays.size(); array++){
            long result_array = 0;
            ArrayList<Integer> arrayList = arrays.get(array);
            for (int i=0; i< n; i++){
                    result_array += a[i] * arrayList.get(i);
            }
            if (result > result_array){
                result = result_array;
            }
        }
        return result;
    }

    public static  ArrayList<ArrayList<Integer>>  permute(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        //start from an empty list
        result.add(new ArrayList<Integer>());

        for (int i = 0; i < num.length; i++) {
            //list of list in current iteration of the array num
            ArrayList<ArrayList<Integer>> current = new ArrayList<ArrayList<Integer>>();

            for (ArrayList<Integer> l : result) {
                // # of locations to insert is largest index + 1
                for (int j = 0; j < l.size()+1; j++) {
                    // + add num[i] to different locations
                    l.add(j, num[i]);

                    ArrayList<Integer> temp = new ArrayList<Integer>(l);
                    current.add(temp);

                    //System.out.println(temp);

                    // - remove num[i] add
                    l.remove(j);
                }
            }

            result = new ArrayList<ArrayList<Integer>>(current);
        }

        return result;
    }
}
