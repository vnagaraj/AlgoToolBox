package week4;
import java.util.Scanner;

public class Inversions {

    public static long getNumberOfInversions(Comparable[] a) {
        Comparable[] b = new Comparable[a.length];
        return count_sort(a, b, 0, a.length-1);
    }

    public static long getNumberOfInversionsBruteForce(Comparable[] a){
        long count = 0;
        for (int i=0; i < a.length; i++){
            for(int j=i+1; j < a.length; j++){
                if (a[i].compareTo(a[j]) >0){
                    count++;
                }
            }
        }
        return count;
    }


    private static long count_sort(Comparable[] a, Comparable[] b, int left, int right) {
        if (left >= right) {
            return 0;
        }
        int mid = (right - left) / 2 + left;
        long count_left = count_sort(a, b, left, mid);
        long count_right = count_sort(a, b, mid + 1, right);
        long count_merge = count_merge(a, b, left, right);
        return count_left + count_right + count_merge;

    }

    private static long count_merge(Comparable[] a, Comparable[] b, int left, int right) {
        int count = 0;
        for (int i = left; i <= right; i++) {
            b[i] = a[i];
        }
        int mid = (right - left) / 2 + left;
        int i = left;
        int j = mid + 1;
        for (int index = left; index <= right; index++) {
            if (i > mid) {
                a[index] = b[j++];
            } else if (j > right) {
                a[index] = b[i++];
            }  else if (b[i].compareTo(b[j])>0) {
                long rem_elements = mid - i + 1;
                count += rem_elements;
                a[index] = b[j++];
            }else  {
                a[index] = b[i++];
            }
        }
        return count;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Comparable[] a = new Comparable[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

       // int[] a = new int[]{4, 80, 70, 23, 9, 60, 68, 27, 66, 78, 12, 40, 52, 53, 44, 8, 49, 28, 18, 46, 21, 39, 51, 7, 87, 99, 69, 62, 84, 6, 79, 67, 14, 98, 83, 0, 96, 5, 82, 10, 26, 48, 3, 2, 15, 92, 11, 55, 63, 97, 43, 45, 81, 42, 95, 20, 25, 74, 24, 72, 91, 35, 86, 19, 75, 58, 71, 47, 76, 59, 64, 93, 17, 50, 56, 94, 90, 89, 32, 37, 34, 65, 1, 73, 41, 36, 57, 77, 30, 22, 13, 29, 38, 16, 88, 61, 31, 85, 33, 54};
        //System.out.println(getNumberOfInversionsBruteForce(a));
        System.out.println(getNumberOfInversions(a));
    }
}
