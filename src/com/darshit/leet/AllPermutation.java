package com.darshit.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AllPermutation {
    public static void main (String arg[]) {
        int[] numbersDistinct = {1,2,3};
        findAllPermutation(numbersDistinct);

        int[] numbers = {-1, 2, 3, 1, 1};
        findAllUniquePermutations(numbers);
    }

    public static void findAllPermutation(int[] numbers) {
        List<List<Integer>> result = new ArrayList<>();
        permuteArray(numbers, 0, result);
        System.out.println("Permutation of all numbers: ");
        System.out.println(result);
    }

    public static void permuteArray(int[] numbers, int index, List<List<Integer>> result) {
        if (index >= numbers.length) {
            result.add(Arrays.stream(numbers).boxed().collect(Collectors.toList()));
        }

        for (int i=index; i<numbers.length; i++) {
            swap(numbers, i, index);
            permuteArray(numbers, index+1, result);
            swap(numbers, i, index);
        }
    }

    public static void findAllUniquePermutations(int[] numbers) {
        List<List<Integer>> results = new ArrayList<>();
        uniquePermute(numbers, 0, results);
        System.out.println("Unique permute without distinct numbers: ");
        System.out.println(results);
    }

    public static void uniquePermute(int[] number, int index, List<List<Integer>> results) {
        if (index >= number.length) {
            results.add(Arrays.stream(number).boxed().collect(Collectors.toList()));
        }
        boolean visited[] = new boolean[number.length];
        boolean negVisited[] = new boolean[number.length];

        for (int i=index; i<number.length; i++) {
            if (number[i] < 0 ) {
                if (!negVisited[number[i]*-1]) {
                    swap(number, i, index);
                    uniquePermute(number, index + 1, results);
                    swap(number, i, index);
                }
                negVisited[number[i]*-1] = true;
            }
            if (number[i] >= 0) {
                if (!visited[number[i]]) {
                    swap(number, i, index);
                    uniquePermute(number, index + 1, results);
                    swap(number, i, index);
                }
                visited[number[i]] = true;
            }
        }
    }

    public static void swap (int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }
}
