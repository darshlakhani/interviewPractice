package com.darshit.leet;

import com.darshit.leet.model.Graph;

import java.util.ArrayList;
import java.util.List;

public class CyclicDAG {

    public static void main(String arg[]) {
        Graph g1 = new Graph();
        g1.setValue(5);

        Graph g2 = new Graph();
        g2.setValue(6);

        Graph g3 = new Graph();
        g3.setValue(7);

        Graph g4 = new Graph();
        g4.setValue(8);


        g1.getAdj().add(g2);
        g1.getAdj().add(g3);

        g2.getAdj().add(g3);

        g3.getAdj().add(g1);
        g3.getAdj().add(g4);

        g4.getAdj().add(g4);

        List<Graph> visited = new ArrayList<>();

        System.out.println("Is cycle: " + isCycle(g1, visited));
    }

    public static boolean isCycle(Graph p, List<Graph> visited) {
        if (p == null) {
            return false;
        }

        if (visited.contains(p)) {
            return true;
        }
        visited.add(p);
        for (Graph g : p.getAdj()) {
            if (isCycle(g, visited)) {
                return true;
            }
        }

        return false;
    }

}
