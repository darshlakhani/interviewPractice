package com.darshit.leet;

import com.darshit.leet.model.Graph;

import java.util.*;

public class TopologicalSort {

    public static void main (String arg[]) {
        Graph g0 = new Graph();
        g0.setValue(0);

        Graph g1 = new Graph();
        g1.setValue(1);

        Graph g2 = new Graph();
        g2.setValue(2);

        Graph g3 = new Graph();
        g3.setValue(3);

        Graph g4 = new Graph();
        g4.setValue(4);

        Graph g5 = new Graph();
        g5.setValue(5);

        g5.getAdj().add(g0);
        g5.getAdj().add(g2);

        g4.getAdj().add(g0);
        g4.getAdj().add(g1);

        g2.getAdj().add(g3);

        g3.getAdj().add(g1);
        List<Graph> nodeList = new ArrayList<>(Arrays.asList(g0,g1,g2,g3,g4,g5));
        topologicalSort(nodeList);
    }

    public static void topologicalSort(List<Graph> graphNodes) {
        List<Integer> visited = new ArrayList<>();
        Stack<Integer> ans = new Stack<>();

        for (Graph g : graphNodes) {
            if(!visited.contains(g.getValue())) {
                topSortHelper(g, visited, ans);
            }
        }

        while (!ans.isEmpty()) {
            System.out.print(ans.pop() + " ");
        }

    }

    public static void topSortHelper(Graph node, List<Integer> visited, Stack<Integer> ans) {
        visited.add(node.getValue());

        for(Graph adj : node.getAdj()) {
            if (!visited.contains(adj.getValue())) {
                topSortHelper(adj, visited, ans);
            }
        }
        ans.push(node.getValue());
    }
}
