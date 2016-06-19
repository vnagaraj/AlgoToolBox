package week4;

import java.util.Scanner;
import java.util.Arrays;

public class PointsAndSegments {

    private static int[] fastCountSegments2(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        Pair[] pairs = new Pair[starts.length+ends.length+points.length];
        for(int i=0; i< starts.length; i++){
            pairs[i] = new Pair(starts[i], 'l');
        }
        int counter = 0;
        for(int i=starts.length; i< starts.length+ ends.length; i++){
            pairs[i] = new Pair(ends[counter++], 'r');
        }
        counter = 0;
        for(int i=starts.length+ ends.length; i< starts.length+ ends.length+points.length; i++){
            pairs[i] = new Pair(points[counter], 'p',counter++);
        }
        Arrays.sort(pairs);
        int segment_count = 0;
        int point_count = 0;
        for (int i=0; i< pairs.length ;i++){
            Pair pair = pairs[i];
            if (pair.ch == 'p'){
                cnt[pair.index] = segment_count;
                point_count++;
            } else if (pair.ch == 'l'){
                segment_count++;
            } else{
                segment_count --;
            }
            if (point_count == cnt.length){
                break;
            }
        }
        return cnt;
    }



    private static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        Segment[] segments = new Segment[starts.length];
        for (int i=0; i< starts.length; i++){
            segments[i] = new Segment(starts[i], ends[i]);
        }
        Arrays.sort(segments);
        for (int i=0; i< points.length ;i++){
            cnt[i] = binarySearch(segments, 0, segments.length-1, points[i]);
        }
        return cnt;
    }

    private static int binarySearch(Segment[] segments, int low, int high, int key){
        if (low > high){
            return 0;
        }
        int mid = (high-low)/2 + low;
        Segment midSegment = segments[mid];
        int start = midSegment.start;
        int end = midSegment.end;
        if (key>= start && key <= end ){
            //value found
            return 1 + binarySearch(segments, low, mid-1, key) + binarySearch(segments, mid+1, high, key);
        }else if (key < start){
            //search in 1st half
            return binarySearch(segments, low, mid-1, key);
        } else{
            return binarySearch(segments, mid+1, high, key);
        }
    }

    private static int[] naiveCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < starts.length; j++) {
                if (starts[j] <= points[i] && points[i] <= ends[j]) {
                    cnt[i]++;
                }
            }
        }
        return cnt;
    }

    private static class Segment implements Comparable<Segment>{
        int start;
        int end;

        Segment(int start, int end){
            this.start = start;
            this.end = end;
        }

        public int compareTo(Segment o){
            if (this.start < o.start){
                return -1;
            }
            if (this.start > o.start){
                return 1;
            }
            if (this.end < o.end){
                return -1;
            }
            if (this.end > o.end){
                return 1;
            }
            return 0;
        }
    }

    private static class Pair implements Comparable<Pair>{
        int start;
        char ch;
        int index = -1; //applicable to only points to keep track location in initial index

        Pair(int start, char ch){
            this.start = start;
            this.ch = ch;
        }

        Pair(int start, char ch, int index){
            this(start,ch);
            this.index = index;
        }

        public int compareTo(Pair o){
            if (this.start < o.start){
                return -1;
            }
            if (this.start > o.start){
                return 1;
            }
            if (this.ch < o.ch){
                return -1;
            }
            if (this.ch > o.ch){
                return 1;
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[] starts = new int[n];
        int[] ends = new int[n];
        int[] points = new int[m];
        for (int i = 0; i < n; i++) {
            starts[i] = scanner.nextInt();
            ends[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        //use fastCountSegments
        //int[] cnt = naiveCountSegments(starts, ends, points);
        int[] cnt = fastCountSegments2(starts, ends, points);
        for (int x : cnt) {
            System.out.print(x + " ");
        }
    }
}