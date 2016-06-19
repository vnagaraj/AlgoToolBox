package week5;

import java.util.*;

class EditDistance {
  public static int EditDistance(String s, String t) {
      int[][] distance = distanceArray(s, t);
      return distance[distance.length-1][distance[0].length-1];
  }

  public static String[] OutputAlignment(String s, String t){
      String result1 = "";
      String result2 = "";
      int penalty_gap = 1;
      int[][] distance = distanceArray(s, t);
      int m = s.length();
      int n = t.length();
      int i = m;
      int j = n;
      while(i>=0 && j>=0){
         if (i == 0 & j == 0){
             break;
         }
         if (distance[i][j]== distance[i-1][j] + penalty_gap){
                  result1 = s.charAt(i-1) + result1;
                  result2 = "-" + result2;
                  i = i-1;
        } else if (distance[i][j]== distance[i][j-1] + penalty_gap){
                  result1 = "-" + result1;
                  result2 = t.charAt(j-1) + result2;
                  j = j -1;
        }else{
                  result1 = s.charAt(i-1) + result1;
                  result2 = t.charAt(j-1) + result2;
                  i = i-1;
                  j = j-1;

        }
      }

      return new String[]{result1, result2};
  }

  private static int[][] distanceArray(String s, String t){
      int m = s.length();
      int n = t.length();
      int penalty_gap = 1;
      int penalty_mismatch = 1;
      int[][] distance = new int[m+1][n+1];
      for (int i = 0; i < m+1; i++) {
          for (int j = 0; j < n+1; j++) {
              //base cases when i & j == 0
              if (i == 0) {
                  distance[i][j] = j * penalty_gap;
              } else if (j == 0) {
                  distance[i][j] = i * penalty_gap;
              } else if (s.charAt(i-1) == t.charAt(j-1)) {
                  distance[i][j] = min(distance[i - 1][j - 1], distance[i - 1][j] + penalty_gap, distance[i][j - 1] + penalty_gap);
              } else {
                  distance[i][j] = min(distance[i - 1][j - 1] + penalty_mismatch, distance[i - 1][j] + penalty_gap, distance[i][j - 1] + penalty_gap);
              }
          }
      }
      return distance;
  }



  private static int min(int x, int y, int z){
        if (x <=y && x <= z){
            return x;
        } else if (y <=x && y <=z){
            return y;
        }else{
            return z;
        }

    }


  public static void main(String args[]) {
    Scanner scan = new Scanner(System.in);

    String s = scan.next();
    String t = scan.next();

    System.out.println(EditDistance(s, t));
    String[] outputAlignment = OutputAlignment(s, t);
    for (String st: outputAlignment){
        System.out.println(st);
    }
  }

}
