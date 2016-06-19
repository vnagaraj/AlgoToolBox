package week2;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class FibonacciHuge {
    private static BigInteger getFibonacciHuge(long n, long m) {
        if (n <= 1){
            return BigInteger.valueOf(n);
        }
        //BigInteger[] fib = new BigInteger[(int)n+1];
        ArrayList<BigInteger> fib = new ArrayList<BigInteger>();
        //fib[0] = BigInteger.valueOf(0);
        //fib[1] = BigInteger.valueOf(1);
        fib.add(BigInteger.valueOf(0));
        fib.add(BigInteger.valueOf(1));
        BigInteger prev = BigInteger.valueOf(0);
        BigInteger next = BigInteger.valueOf(1);

        BigInteger mbig = BigInteger.valueOf(m);
        BigInteger nbig = BigInteger.valueOf(n);
        int seqIndex = 0;
        int seqlength = 2;
        int r = 0;
        for (int i =2; i < n+1; i++){

            BigInteger current = prev.add(next);
            BigInteger  curseq = current.mod(mbig);
            fib.add(curseq);
            //fib[i] = curseq;
            if (seqlength == seqIndex){
                break;
            }
            if (!curseq.equals(fib.get(seqIndex))){
              seqIndex = 0;
              seqlength = i;
            }else{
                seqIndex +=1;
            }
            prev = next;
            next = current;
            r = i;
        }
        System.out.println("iterations " + r);
        System.out.println("Sequence length " + (seqlength+1));
        BigInteger remainder = nbig.mod(BigInteger.valueOf(seqlength+1));
        return  fib.get(remainder.intValue());
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long m = scanner.nextLong();
        long startTime = System.currentTimeMillis();
        System.out.println(getFibonacciHuge(n, m));
        System.out.println(System.currentTimeMillis() - startTime);
    }
}

