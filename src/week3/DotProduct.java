package week3;

import java.math.BigInteger;
import java.util.*;

public class DotProduct {
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextInt();
        }
        System.out.println(minDotProduct(a, b));
    }
}

