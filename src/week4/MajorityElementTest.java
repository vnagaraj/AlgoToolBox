package week4;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by VGN on 5/24/16.
 */
public class MajorityElementTest {

    public static void main(String[] args){
        verify(9);
    }

    private static void verify(int size){
        while (true){
            int a[] = generateRandomArray(size);
            int[] b = new int[a.length];
            int[]c = new int[a.length];
            for (int i=0; i < b.length; i++){
                b[i] = a[i];
                c[i] = a[i];
            }
            int val = getMajorityElementBruteForce(a);
            int val2 = getMajorityElement(b);
            if (val != val2){
                System.out.println("ERROR: Failure to validate");
                System.out.println("hashTable " +val);
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

    private static int[] generateRandomArray(int size){
        Random rn = new Random();
        int[] a = new int[size];
        for (int i = 0; i < size; i++) {
            int value = (rn.nextInt(10));
            //int value = rn.nextInt(10);
            a[i] = value;
        }
        return a;
    }

    private static int getMajorityElementBruteForce(int[] a) {
        int countMajority = a.length/2 + 1;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i =0; i< a.length; i++){
            if (map.containsKey(a[i])){
                Integer val = map.get(a[i]);
                map.put(a[i], val+1);
            }else{
                map.put(a[i], 1);
            }
        }
        for (int i = 0; i< a.length; i++ ){
            if (map.get(a[i]) >= countMajority){
                return 1;
            }
        }
        return 0;
    }

    private static int getMajorityElement(int[] a) {
        int[] aux = new int[a.length];
        boolean found = getMajorityElement(a, 0, a.length-1 , aux);
        if (found){
            return 1;
        }
        return 0;
    }

    private static boolean getMajorityElement(int[] a, int left, int right, int[] aux) {
        if (left >= right) {
            return true;
        }
        int middle = (right - left)/2 + left;
        boolean first = getMajorityElement(a, left, middle, aux);
        boolean second = getMajorityElement(a, middle+1, right, aux);
        return merge(a, left, right, aux, first, second);
    }

    private static boolean merge(int[] a, int left, int right, int[] aux, boolean first, boolean second) {
        //make a copy
        for (int i = left; i <= right; i++) {
            aux[i] = a[i];
        }
        int firstMajor = -1;
        int secondMajor = -1;
        int countfirstMajor = 0;
        int countsecondMajor = 0;
        int middle = (right - left) / 2 + left;
        if (first) {
            int median = (middle - left) / 2 + left;
            firstMajor = a[median];
        }
        if (second) {
            int median = (right - (middle + 1)) / 2 + middle + 1;
            secondMajor = a[median];
        }
        int i = left;
        int j = middle + 1;
        for (int index = left; index <= right; index++) {
            int value = -1;
            if (i > middle) {
                value = aux[j++];
            } else if (j > right) {
                value = aux[i++];
            } else if (aux[i] <= aux[j]) {
                value = aux[i++];
            } else {
                value = aux[j++];
            }
            if (value == firstMajor && first) {
                countfirstMajor += 1;
            }
            if (value == secondMajor && second) {
                countsecondMajor += 1;
            }
            a[index] = value;

        }
        int arrayLength = right - left + 1;

        int countMajority = arrayLength / 2 + 1;
        if (countfirstMajor >= countMajority || countsecondMajor >= countMajority) {
            return true;
        }
        return false;
    }
}
