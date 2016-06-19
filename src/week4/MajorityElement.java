package week4;

import java.util.*;
import java.io.*;

public class MajorityElement {
    private static int getMajorityElement(int[] a, int left, int right) {
        if (a.length == 0){
            return -1;
        }
        //write your code here
        int[] aux = new int[a.length];
        boolean found = getMajorityElement(a, left, right-1 , aux);
        if (found){
            return 1;
        }
        return -1;
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

    private static boolean merge(int[] a, int left, int right, int[] aux, boolean first, boolean second){
        //make a copy
        for(int i =left; i <= right; i++){
            aux[i] = a[i];
        }
        int firstMajor = -1;
        int secondMajor = -1;
        int countfirstMajor = 0;
        int countsecondMajor = 0;
        int middle = (right - left)/2 + left;
        if (first){
            int median = (middle - left)/2 + left;
            firstMajor = a[median];
        }
        if (second){
            int median = (right - (middle+1))/2 + middle+1;
            secondMajor = a[median];
        }
        int i = left;
        int j = middle+1;
        for (int index=left; index <=right; index++){
            int value = -1;
            if (i > middle){
                value = aux[j++];
            }else if ( j> right){
                value = aux[i++];
            }else if (aux[i] <= aux[j]){
                value = aux[i++];
            } else{
                value = aux[j++];
            }
            if (value == firstMajor && first){
                countfirstMajor += 1;
            }
            if (value == secondMajor && second){
                countsecondMajor += 1;
            }
            a[index] = value;

        }
        int arrayLength = right - left +1;

        int countMajority = arrayLength/2 + 1;
        if (countfirstMajor >= countMajority  || countsecondMajor >= countMajority){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        if (getMajorityElement(a, 0, a.length) != -1) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

