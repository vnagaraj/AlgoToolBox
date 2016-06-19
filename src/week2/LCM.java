package week2;

import java.util.*;

public class LCM {
  private static long lcm(int a, int b) {
    long a_long = a;
    long b_long = b;
    long result = a_long * b_long;
    return result/gcd(a,b);
  }

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

    System.out.println(lcm(a, b));
  }
}
