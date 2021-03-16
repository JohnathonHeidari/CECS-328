package com.company;

import java.util.ArrayList;

public class Node {
    // instance variables
    static int i = 1;
    int distance, data;
    String color;
    Node parent;
    int start = 0, end = 0;

    private ArrayList<Node> adj = new ArrayList<>();

    // constructor
    public Node(int data) {
        this.data = data;
        this.parent = null;
    }
    // getters
    public int getDistance() {
        return distance;
    }

    public ArrayList<Node> getAdj() {
        return adj;
    }

    // accessor
    public void setDistance(int distance) {
        this.distance = distance;
    }

    // method
    public void adj(Node node){
        this.adj.add(node);
    }


    @Override
    public String toString() {

        switch (data){
            case 0:
                return "a";
            case 1:
                return "b";
            case 2:
                return "c";
            case 3:
                return "d";
            case 4:
                return "e";
            case 5:
                return "f";
            case 6:
                return "g";
            case 7:
                return "h";
            case 8:
                return "i";
            case 9:
                return "j";
            case 10:
                return "k";
            case 11:
                return "l";
            case 12:
                return "m";
            default:
                return Integer.toString(data); // error
        }
    }
}
