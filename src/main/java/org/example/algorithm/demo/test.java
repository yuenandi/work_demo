package org.example.algorithm.demo;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class test {

    public static void main(String[] args) {
        Sort sort = new Sort();
//        int[] arr = {4, 3, 2, 8, 6, 9};
//        int[] arr = new int[10];
//        for (int i = 0; i < 10; i++) {
//            arr[i] = new Random().nextInt(40) + 1;
//        }
        Set<Integer> set =new HashSet<Integer>();
        while (set.size()<10){
            int num = new Random().nextInt(40) + 1;
            set.add(num);
        }
        Integer[] integers = set.toArray(new Integer[set.size()]);
        int[] arr = Arrays.stream(integers).mapToInt(Integer::valueOf).toArray();
//        sort.bubbleSort(arr);
//        sort.quickSort(arr, 0, arr.length - 1);
//        sort.insertSort(arr);
//        sort.shellSort(arr);
//        sort.selectSort(arr);
        sort.heapSort(arr);
        for (int v : arr) {
            System.out.printf("%d ", v);
        }
    }
}
