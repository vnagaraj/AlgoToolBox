package week2;

import java.util.*;

public class FibonacciLastDigit {
    private static int getFibonacciLastDigit(int n) {
        if (n <=1){
            return n;
        }
        int prev = 0;
        int next = 1;
        int current = -1;
        for (int i=2; i < n+1; i++){
            current  = (prev + next)%10;
            prev = next;
            next = current;
        }
        return current;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int c = getFibonacciLastDigit(n);
        System.out.println(c);
    }
}

