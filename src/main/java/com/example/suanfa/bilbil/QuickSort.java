package com.example.suanfa.bilbil;

import java.util.Arrays;

import java.util.Random;

public class QuickSort {





    public int[] sortArray(int[] num) {
        return sort(num, 0, num.length - 1);
    }

    private int[] sort(int[] num, int start, int end) {
        if (num.length < 1 || start < 0 || end >= num.length || start > end) {
            return null;
        }
        int zoneIndex = partition(num, start, end);
        if (zoneIndex > start) sort(num, start, zoneIndex - 1);
        if (zoneIndex < end) sort(num, zoneIndex + 1, end);
        return num;
    }

    private int partition(int[] num, int start, int end) {
        if (start == end) {
            return start;
        }
        int pivot = (int) (start + Math.random() * (end - start + 1));
        int zoneIndex = start - 1;
        swap(num, pivot, end);
        for (int i = start; i <= end; i++) {
            if (num[i] <= num[end]) {
                zoneIndex++;
                if (i > zoneIndex) {
                    swap(num, i, zoneIndex);
                }
            }
        }
        return zoneIndex;
    }

    private void swap(int[] num, int pivot, int end) {
        int temp = num[pivot];
        num[pivot] = num[end];
        num[end] = temp;
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
        QuickSort q = new QuickSort();
        q.sortArray(arr);
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
