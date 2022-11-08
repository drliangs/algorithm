package com.example.suanfa.bilbil;


import java.util.Arrays;
import java.util.Random;

//希尔排序
public class ShellQuickSort {

    public static int[] sortArray(int[] arr) {
        if (arr == null || arr.length == 0) return arr;
        int len = arr.length;
        int gap = len >>> 2, currentValue;
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                currentValue = arr[i];
                int prevIndex = i - gap;
                while (prevIndex >= 0 && arr[prevIndex] > currentValue) {
                    arr[gap + prevIndex] = arr[prevIndex];
                    prevIndex -= gap;
                }
                arr[gap + prevIndex] = currentValue;
            }
            gap >>>= 2;
        }
        return arr;
    }

    public static void main(String[] args) {
        Random random = new Random();
        int[] arr = new int[100000];
        int[] jdkarr = new int[100000];
        for (int i = 0; i < arr.length; i++) {
            int date = random.nextInt(100000);
            arr[i] = date;
            jdkarr[i] = date;
        }

        sortArray(arr);
        Arrays.sort(jdkarr);

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != jdkarr[i]) {
                System.out.println("fuck");
                System.out.println(arr[i] + "zzzzzzz" + jdkarr[i]);
                break;

            }
        }
    }
}
