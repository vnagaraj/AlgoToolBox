package week2;

import java.util.Scanner;

public class Fib {
  private static long calc_fib(int n) {
    if (n <= 1)
      return n;
    int prev = 0;
    int next = 1;
    int current = -1;
    for (int i=2; i< n+1; i++){
       current = next + prev;
       prev = next;
       next = current;

    }
    return current;
  }

  public static void main(String args[]) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();

    System.out.println(calc_fib(n));
  }
}
