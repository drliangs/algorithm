package com.example.suanfa.bilbil;

public class InsertionSort {
    public int[] sortArray(int[] numbers) {
        if (numbers == null || numbers.length == 0) return numbers;
        for (int i = 0; i < numbers.length - 1; i++) {
            int preIndex = i;
            int currentValue = numbers[preIndex + 1];

            while (preIndex >= 0 && currentValue < numbers[preIndex]) {
                numbers[preIndex + 1] = numbers[preIndex];
                preIndex--;
            }
            numbers[preIndex + 1] = currentValue;

        }
        return numbers;

    }

}
