package week5;

import java.util.Scanner;

public class PlacingParentheses {
    private static long getMaximValue(String exp) {
      long[] digits = digits(exp);
      char[] operands =  operands(exp);
      return parentheses(digits, operands);
    }

    private static long parentheses(long[] digits, char[] operands){
        int n = digits.length;
        long[][] min = new long[n][n];
        long[][] max = new long[n][n];
        for(int i= 0; i < n; i++){
            //base case when sub expression involves 1 digit
            min[i][i] = digits[i];
            max[i][i] = digits[i];
        }
        for (int s = 1; s<= n-1; s++){
            for (int i = 1; i <= n-s; i++){
                int j = i + s;
                long[] result = minAndMax(i-1, j-1, min, max, digits, operands);
                min[i-1][j-1] = result[0];
                max[i-1][j-1] = result[1];
            }
        }
        return max[0][n-1];
    }

    private static long[] minAndMax(int i, int j, long[][] min, long[][] max, long[] digits, char[] operands){
        long minimum = Long.MAX_VALUE;
        long maximum = Long.MIN_VALUE;
        for (int k =i; k <= j-1; k++){
            char operand = operands[k];
            long a = eval(max[i][k], max[k+1][j], operand);
            long b = eval(max[i][k], min[k+1][j], operand);
            long c = eval(min[i][k], max[k+1][j], operand);
            long d = eval(min[i][k], min[k+1][j], operand);
            minimum = getMin(minimum, a, b, c, d);
            maximum = getMax(maximum, a, b, c, d);
        }
        return new long[]{minimum, maximum};
    }

    private static long getMin(long min, long a, long b, long c , long d){
        if (a <= min && a <=b && a <= c && a <=d ){
            return a;
        }
        if (b <= min && b <=a && b <= c && b <=d ){
            return b;
        }

        if (c <= min && c <=a && c <= b && c <=d ){
            return c;
        }

        if (d <= min && d <=a && d <= b && d <=c ){
            return b;
        }
        return min;
    }

    private static long getMax(long max, long a, long b, long c , long d){
        if (a >= max && a >=b && a >= c && a >=d ){
            return a;
        }
        if (b >= max && b >=a && b >= c && b >=d ){
            return b;
        }

        if (c >= max && c >=a && c >= b && c >=d ){
            return c;
        }

        if (d >= max && d >=a && d >= b && d >=c ){
            return b;
        }
        return max;
    }

    private static char[] operands(String exp){
        char[] operands = new char[exp.length()/2];
        int counter =0;
        for (int i = 1; i < exp.length(); i= i+2){
            operands[counter++] = exp.charAt(i);
        }
        return operands;
    }

    private static long[] digits(String exp){
        long[] digits = new long[(exp.length()/2) +1];
        int counter =0;
        for (int i = 0; i < exp.length(); i= i+2){
            String s = String.valueOf(exp.charAt(i));
            digits[counter++] = Integer.parseInt(s);
        }
        return digits;
    }

    private static long eval(long a, long b, char op) {
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        } else if (op == '*') {
            return a * b;
        } else {
            assert false;
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.next();
        System.out.println(getMaximValue(exp));
    }
}

