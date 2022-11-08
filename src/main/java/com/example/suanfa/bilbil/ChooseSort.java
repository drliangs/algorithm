package com.example.suanfa.bilbil;

public class ChooseSort {
    public int[] sortArray(int[] numbs) {
        if (numbs == null || numbs.length == 0) return numbs;
        for (int i = 0; i < numbs.length; i++) {
            int minxIndex = i;
            for (int j = i; j < numbs.length; j++) {
                if (numbs[j] < numbs[minxIndex]) {
                    minxIndex = j;
                }
            }
            int temp = numbs[minxIndex];
            numbs[minxIndex] = numbs[i];
            numbs[i] = temp;
        }
        return numbs;
    }
}
