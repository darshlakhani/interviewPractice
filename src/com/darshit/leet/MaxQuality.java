package com.darshit.leet;

import javafx.util.Pair;

import java.util.*;

/*
Task Description


When building a computing cluster consisting of several machines, we consider two parameters of the machines to be the most important: speed and reliability. We define the quality of a computing cluster as the sum of its machines' speeds multiplied by the minimum reliability of its machines.
 
You are presented with several available machines with various parameters, and you want your computing cluster to contain a certain maximum number of machines. Based on this data, you must determine the maximum quality of cluster you can achieve.
 
For example, let's say that the number of available machines, n, is 5. These machines have the following parameters: speed = [4, 3, 15, 5, 6], reliability = [7, 6, 1, 2, 8]. The maximum number of machines you want to have, maxMachines, is 3.
 
The best quality of a cluster can be achieved by selecting the first, second, and fifth machine. By doing so, the quality of a cluster will be (4 + 3 + 6) * min(7, 6, 8) = 13 * 6 = 78. This is the highest quality that can be achieved by selecting at most 3 machines from the available ones, so the answer is 78.
 
Function Description
Complete the function maxClusterQuality in the editor below. The function must return an integer denoting the maximum quality of a computing cluster that can be built.
 
maxClusterQuality has the following parameter(s):
    speed: an integer array of size n, such that speed[i] is the speed of the ith machine
    reliability: an integer array of size n, such that reliability[i] is the reliability of the ith machine
    maxMachines: an integer denoting the maximum number of machines you want in a cluster
 
Constraints
* 1≤ n ≤105 
* 1≤ speed[i] ≤105 
* 1≤ reliability[i] ≤105 
* 1≤ maxMachines ≤ n 
 
Input Format For Custom Testing
Sample Case 0
Sample Input For Custom Testing
5
12
112
100
13
55
5
31
4
100
55
50
3
Sample Output
10000
Explanation
There are 5 machines available. Their speeds are 12, 112, 100, 13, and 55 respectively, while their reliabilities are 31, 4, 100, 55, and 50 respectively. The maximum number of machines allowed in a cluster is 3.
 
The best quality of a cluster can be achieved by selecting only the third machine. By doing so, the quality of a cluster will be 100 * 100 = 10000. This is the highest quality that can be achieved by selecting at most 3 machines from the available ones, so the answer is 10000.
 */
public class MaxQuality {
    public static void main(String arg[]) {
        int[] speed = {4, 3, 15, 5, 6};
        int[] rel = {7, 6, 1, 2, 8};
        List<Integer> s = new ArrayList<>(Arrays.asList(50,100,20));
        List<Integer> r = new ArrayList<>(Arrays.asList(50,100,20));
        System.out.println(findMaxQuality(s, r, 3));
    }

    public static int findMaxQuality(List<Integer> speed, List<Integer> reliablilty, int maxMachine) {
        List<Pair<Integer, Integer>> pairList = new ArrayList<>();
        for (int i=0; i<reliablilty.size(); i++) {
            Pair<Integer, Integer> p = new Pair<>(reliablilty.get(i), speed.get(i));
            pairList.add(p);
        }

        Comparator<Pair<Integer, Integer>> comp = new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
                return p2.getKey() - p1.getKey();
            }
        };

        Collections.sort(pairList, comp);
        int machineCount = 0;

        Queue<Pair<Integer,Integer>> pQ = new PriorityQueue<>((p1,p2) -> {
            int q1 = (int)p1.getValue()*(int)p1.getKey();
            int q2 = (int)p2.getValue()*(int)p2.getKey();
            return q2-q1;
        });
        for (Pair p : pairList) {
            if (pQ.isEmpty()) {
                pQ.offer(p);
                machineCount = 1;
            } else {
                int pQuality = (int)p.getKey() * (int)p.getValue();
                Pair<Integer, Integer> fromQ = pQ.peek();
                int qQuality = (int)fromQ.getKey() * (int)fromQ.getValue();
                if (pQuality > qQuality) {
                    pQ.offer(p);
                    machineCount = 1;
                } else {
                    int combineSpeed = (int)p.getValue() + (int)fromQ.getValue();
                    int minRel = Math.min((int)p.getKey(), fromQ.getKey());
                    int combQ = combineSpeed * minRel;

                    if (combQ > qQuality && machineCount <=maxMachine) {
                        Pair<Integer, Integer> nP = new Pair<>(minRel, combineSpeed);
                        pQ.offer(nP);
                        machineCount++;
                    }
                }
            }
        }
        Pair<Integer, Integer> r = pQ.poll();
        return r.getValue()*r.getKey();
    }
}
