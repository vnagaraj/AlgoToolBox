package week5;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by VGN on 6/3/16.
 */
public class KnapsackStanfordTest {


    @Test
    public void test1(){
      KnapsackStanford knapsack = KnapsackStanford.readFile("knapsack_1.txt");
      assertTrue(knapsack.run() == 8);
      System.out.println(knapsack.optimal_run());
      assertTrue(knapsack.optimal_run() == 8);
    }

    @Test
    public void test2(){
        KnapsackStanford knapsack = KnapsackStanford.readFile("knapsack.txt");
        System.out.println(knapsack.optimal_run());
        long startTime = System.currentTimeMillis();
        assertTrue(knapsack.optimal_run() == 2493893);
        System.out.println("Duration " +(System.currentTimeMillis() - startTime));
        startTime = System.currentTimeMillis();
        assertTrue((knapsack.run() == 2493893));
        System.out.println("Duration " +(System.currentTimeMillis() - startTime));
    }

    @Test
    public void test3(){
        KnapsackStanford knapsack = KnapsackStanford.readFile("knapsack_big.txt");
        System.out.println(knapsack.optimal_run());
        assertTrue(knapsack.run() == 4243395);
    }

    @Test
    public void test4(){
        int[] items = new int[] {1,4, 8};
        KnapsackStanford knapsackStanford = new KnapsackStanford(items, items, 10);
        assertTrue(knapsackStanford.optimal_run() == 9);
        assertTrue(knapsackStanford.run_recursive() == 9);


    }
}
