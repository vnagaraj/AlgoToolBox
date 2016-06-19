package week5;
import java.util.HashMap;
import java.util.Scanner;
import java.io.FileInputStream;

/**
 * Created by VGN on 6/3/16.
 */
public class KnapsackStanford {
    private int[] items = null;
    private int[] weights = null;
    private int W = 0;

    KnapsackStanford(int[] items, int[] weights, int W){
        this.items = items;
        this.weights = weights;
        this.W = W;
    }

    public int run(){
        int N = items.length;
        int[][] values = new int[N+1][W+1];
        //initialization step
        for (int w =0; w < W+1; w++){
            values[0][w] =0;
        }
        for (int i=1; i < N+1; i++){
            for (int w =0; w < W+1; w++){
                values[i][w] = values[i-1][w]; //picking i-1th item
                if (w > weights[i-1]){
                    int valIthItem = values[i-1][w-weights[i-1]] + items[i-1];
                    if (valIthItem > values[i][w]){
                        values[i][w] = valIthItem;
                    }
                }

            }
        }
        return values[N][W];
    }

    public int optimal_run(){
        int N = items.length;
        int[] values1 = new int [W+1];
        int[] values2 = new int [W+1];
        int[] current = values1;
        int[] prev = values2;
        for (int i=1; i < N+1; i++){
            for (int w =0; w < W+1; w++){
                current[w] = prev[w];
                if (w >= weights[i-1]){
                    int valIthItem = prev[w-weights[i-1]] + items[i-1];
                    if (valIthItem > current[w]){
                        current[w] = valIthItem;

                    }
                }

            }
            if (current == values1){
                current = values2;
                prev = values1;
            } else {
                current = values1;
                prev = values2;
            }
        }
        return prev[W];
    }



    public int run_recursive(){
        int N = items.length;
        HashMap<KnapsackInstance, Integer> map = new HashMap<KnapsackInstance, Integer>();
        Integer[][] values = new Integer[N+1][W+1];
        int val = recursive_sol(N, W, map);
        return val;
    }

    private static class KnapsackInstance {
        Integer item;
        Integer weight;

        KnapsackInstance(int item, int weight){
            this.item = item;
            this.weight = weight;
        }

        public boolean equals(Object o){
            if (this  == o){
                return true;
            }
            if (this.getClass() != o.getClass()){
                return false;
            }
            KnapsackInstance that = (KnapsackInstance) o;
            if (this.item == that.item && this.weight == that.weight){
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 7 * hash + this.item.hashCode();
            hash = 7 * hash + this.weight.hashCode();
            return hash;
        }

        public String toString(){
            return "Items " + this.item + "Weight " + this.weight;
        }
    }

    private int recursive_sol(int item, int weight, HashMap<KnapsackInstance, Integer> map){
        KnapsackInstance instance = new KnapsackInstance(item, weight);
        //base case
        if (item == 0){
            map.put(instance, 0);
            //values[item][weight] = 0;
            return 0;
        }
        int value1 = -1;
        int value2 = -1;

        KnapsackInstance instance1 = new KnapsackInstance(item-1, weight);
        if (map.containsKey(instance1)){
            value1= map.get(instance1);
        } else {
            value1 = recursive_sol(item - 1, weight, map);
        }
        if (weights[item-1] > weight){
            map.put(instance, value1);
            //values[item][weight] = value1;
            return value1;
        }
        KnapsackInstance instance2 = new KnapsackInstance(item-1, weight - weights[item-1]);
        if (map.containsKey(instance2)){
           value2 = map.get(instance2) + items[item-1];
        } else {
            value2 = recursive_sol(item - 1, weight - weights[item - 1], map) + items[item - 1];
        }
        if (value1 > value2){
            map.put(instance, value1);
            //values[item][weight] = value1;
            return value1;
        }
        map.put(instance, value2);
        //values[item][weight] = value2;
        return value2;
    }

    public static KnapsackStanford readFile(String fileName) {
        Scanner reader = null;
        try {
            reader = new Scanner(new FileInputStream(fileName));
        } catch (Exception exception) {
            System.err.println("File not found");
        }
        int W = -1;
        int itemCount = -1;
        int[] items = null;
        int[] weights = null;
        int counter = 0;
        if (reader != null) {
            while (reader.hasNext()) {
                if (W == -1) {
                    W = reader.nextInt();
                }
                if (itemCount == -1) {
                    itemCount = reader.nextInt();
                    items = new int[itemCount];
                    weights = new int[itemCount];
                }
                int value = reader.nextInt();
                items[counter] = value;
                int weight = reader.nextInt();
                weights[counter++] = weight;
            }

        }
        return new KnapsackStanford(items, weights, W);
    }
}
