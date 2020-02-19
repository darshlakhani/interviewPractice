package com.darshit.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaximumGuest {

    public static void main(String[] args) {
	// write your code here
        int[] entry = {1,2,9,5,5};
        int[] exit = {4,5,12,9,12};

        int time = getMaximumGuestTime(entry, exit);
        System.out.println("Time at which maximum guest are present: " + time);


    }

    public static int getMaximumGuestTime(int[] entry, int[] exit) {
        Arrays.sort(entry);
        Arrays.sort(exit);

        int maxGuest = 0;
        int currentGuest = 0;
        int time = 0;

        int i=0, j=0;

        while(i < entry.length && j<exit.length) {
            if (entry[i] < exit[j]) {
                currentGuest++;
                i++;
                if (currentGuest > maxGuest) {
                    maxGuest = currentGuest;
                    time = entry[i];
                }
            } else {
                currentGuest--;
                j++;
            }
        }
        return time;
    }
}
