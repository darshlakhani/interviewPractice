package com.darshit.leet.model;

import java.util.ArrayList;
import java.util.List;


public class Graph {
    int value;
    List<Graph> adj;

    public Graph() {
        this.adj = new ArrayList<>();
    }

    public List<Graph> getAdj() {
        return adj;
    }

    public void setAdj(List<Graph> adj) {
        this.adj = adj;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
