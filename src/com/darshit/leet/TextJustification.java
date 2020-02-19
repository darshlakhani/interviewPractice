package com.darshit.leet;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {

    public static void main(String arg[]) {
        //["This", "is", "an", "example", "of", "text", "justification."]
        //16
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        System.out.println(justifyTextNotEfficient(words, 16));
        System.out.println(" DP Solution: ");
        textJustifyDP(words, 16);
    }

    public static List<String> justifyTextNotEfficient(String[] words, int maxWidth) {
        int lineLength = 0;
        List<String> line = new ArrayList<>();
        List<String> results = new ArrayList<>();

        for (int i=0; i<words.length; i++) {
            String word = words[i];
            if (!line.isEmpty()) {
                lineLength += 1; // adding space after word
            }
            if ((lineLength + word.length()) <= maxWidth) {
                lineLength += word.length();
                line.add(word);
            } else {
                results.add(justifyText(line, maxWidth-lineLength+1, false));
                lineLength = 0;
                line = new ArrayList<>();
                i--;
            }
        }
        results.add(justifyText(line, maxWidth-lineLength, true));
        return results;
    }

    public static String justifyText(List<String> line, int remainderLength, boolean isLast) {

        int spaceAdd = line.size() <= 1 ? 0 : remainderLength/(line.size()-1); // line size -1 cause last word does not have space.
        int extraSpace = line.size() <=1 ? 0 : remainderLength%(line.size()-1);
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<line.size(); i++) {
            sb.append(line.get(i));
            if (i != line.size()-1) {
                sb.append(" ");
                if (!isLast) {
                    for (int k = 0; k < spaceAdd; k++) {
                        sb.append(" ");
                    }
                    if (extraSpace > 0) {
                        sb.append(" ");
                        extraSpace--;
                    }
                }
            }
        }
        if (isLast || line.size() == 1) {
            for (int i=0; i<remainderLength; i++) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public static void textJustifyDP(String[] words, int maxWidth) {
        // calculate cost, formula - (no. of space req) ^2.
        int[][] cost = new int[words.length][words.length];
//        for (int i=0; i<words.length; i++) {
//            for (int j=0; j<words.length; j++) {
//                cost[i][j] = -1;
//            }
//        }
        for (int i=0; i<words.length; i++) {
            cost[i][i] = maxWidth - words[i].length();
            for (int j=i+1; j<words.length; j++) {
                cost[i][j] = cost[i][j-1] - words[j].length() - 1; // 1 is for space after the word.
            }
        }

        for (int i=0; i<words.length; i++) {
            for (int j=i; j<words.length; j++) {
                if (cost[i][j] < 0) {
                    cost[i][j] = Integer.MAX_VALUE; // infinity;
                } else {
                    cost[i][j] = (int)Math.pow(cost[i][j], 2);
                }
            }
        }

        int minCost[] = new int[words.length];
        int result[] = new int[words.length];

        for (int i=words.length-1; i>=0; i--) {
            minCost[i] = cost[i][words.length-1];
            result[i] = words.length;
            for (int j= words.length-1; j> i ; j--) {
                if (cost[i][j-1] == Integer.MAX_VALUE) {
                    continue;
                }
                if (minCost[i] > minCost[j] + cost[i][j-1]) {
                    minCost[i] = minCost[j] + cost[i][j-1];
                    result[i] = j;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int j;
        int i = 0;
        List<String> justified = new ArrayList<>();
        do {
            j = result[i];
            for (int k=i ; k<j; k++) {
                sb.append(words[k] + " ");
            }
            sb.append("\n");
            i=j;
        } while (j < words.length);
        System.out.println(sb.toString());
    }
}
