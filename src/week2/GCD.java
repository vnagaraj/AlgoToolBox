package week2;

import java.util.*;

public class GCD {
  private static int gcd(int a, int b) {
     if (b == 0){
         return a; //base case
     }
     int rem = a%b;
     return gcd(b, rem);
  }

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    int b = scanner.nextInt();

    System.out.println(gcd(a, b));
  }
}
