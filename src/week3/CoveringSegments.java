package week3;

import java.util.*;

public class CoveringSegments {

    private static ArrayList<Integer> optimalPoints(Segment[] segments) {
        ArrayList<Integer> points = new ArrayList<Integer>();
        int pointCounter = 0;
        //sort by right end point

        Arrays.sort(segments);
        int i = 0; //pointer to segments array
        int j = 1; //second pointer to segments array
        while (j <= segments.length-1){
            Segment first = segments[i];
            Segment second = segments[j];
            if (first.end < second.start){ //no overlap
                points.add(first.end);
                i = j;
            }
            j++;
        }
        points.add(segments[i].end);

        return points;
    }

    private static class Segment implements Comparable<Segment> {
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Segment o) {
            if (this.end < o.end){
                return -1;
            }
            if (this.end > o.end){
                return 1;
            }
            //ends are equal at this stage
            if (this.start < o.start){
                return -1;
            }
            if (this.start > o.start){
                return 1;
            }
            return 0;
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }
        ArrayList<Integer> points = optimalPoints(segments);
        System.out.println(points.size());
        for (int point : points) {
            System.out.print(point + " ");
        }
    }
}
 
