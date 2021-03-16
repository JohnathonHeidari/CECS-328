package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class Node {
    // instance variables
    int x, y;
    double cost = Double.POSITIVE_INFINITY;
    Node parent;

    HashMap<Node,Double> adj = new HashMap<>();

    // constructor
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.parent = null;
    }

    public HashMap<Node, Double> getAdj() {
        return adj;
    }

    // method
    public void adj(Node node, double weight){
        this.adj.put(node, weight);
    }

    @Override
    public String toString() {
        return x + "" + y;
    }
}
