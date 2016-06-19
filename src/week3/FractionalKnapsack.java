package week3;

import java.util.Arrays;
import java.util.Scanner;

public class FractionalKnapsack {
    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double value = 0;
        Item[] items = getItems(values, weights);
        Arrays.sort(items);
        for (int i= items.length-1; i >=0 ; i--){
            if (capacity == 0){
                return value;
            }
            Item item = items[i];
            int extractedCap = min(capacity, item.weight);
            capacity -= extractedCap;
            double valuePerWeight = item.value/item.weight;
            double addedValue = extractedCap * valuePerWeight;
            value += addedValue;
        }
        return value;
    }

    private static int min(int value1, int value2){
        if (value1 <= value2){
            return value1;
        }
        return value2;
    }

    private static class Item implements Comparable<Item>{
        int weight;
        double value;

        Item(int weight, int value){
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Item o) {
            double thisValuePerWeight= (double) value/weight;
            double thatValuePerWeight = (double) o.value/o.weight;
            if (thisValuePerWeight < thatValuePerWeight){
                return -1;
            }
            if (thisValuePerWeight > thatValuePerWeight){
                return 1;
            }
            return 0;
        }
    }

    private static Item[] getItems(int[] values, int[] weights){
        Item[] items = new Item[values.length];
        for (int i=0; i < items.length; i++){
            items[i] = new Item(weights[i], values[i]);
        }
        return items;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(getOptimalValue(capacity, values, weights));
    }
} 
