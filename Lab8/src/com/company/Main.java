package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        System.out.println("G1");
        problemOne(userInput);
        System.out.println("\n------------");
        System.out.println("G2");
        problemTwo(userInput);
        System.out.println("\n------------"); // random graph
        System.out.println("Random Graph");
        randomGraph(userInput);
    }

    private static void problemOne(Scanner userInput) {
        // creating vertices
        Node a = new Node(0); Node b = new Node(1); Node c = new Node(2);
        Node d = new Node(3); Node e = new Node(4); Node f = new Node(5);
        Node g = new Node(6);
        ArrayList<Node> vertices = new ArrayList<>();
        vertices.add(a); vertices.add(b); vertices.add(c); vertices.add(d); vertices.add(e);
        vertices.add(f); vertices.add(g);
        // assigning adj
        a.adj(b); a.adj(c); a.adj(d);
        b.adj(d);
        c.adj(d);
        d.adj(e);
        e.adj(g);
        f.adj(e);
        DFS(vertices);
    }

    private static void problemTwo(Scanner userInput) {
        // creating vertices
        Node a = new Node(0); Node b = new Node(1); Node c = new Node(2);
        Node d = new Node(3); Node e = new Node(4); Node f = new Node(5);
        ArrayList<Node> vertices = new ArrayList<>();
        vertices.add(a); vertices.add(b); vertices.add(c); vertices.add(d); vertices.add(e);
        vertices.add(f);
        // assigning adj
        a.adj(b); a.adj(c);
        b.adj(c); b.adj(d); b.adj(e);
        c.adj(e);
        d.adj(f);
        e.adj(b); e.adj(d);
        f.adj(e);
        // resetting the timer
        Node.i = 1;
        DFS(vertices);
    }

    private static void randomGraph(Scanner userInput) {
        ArrayList<Node> vertices = generateRandomGraph(userInput);
        System.out.println("List of nodes: " + vertices);
        for (Node v: vertices) {
            System.out.println("v: " + v + " adj: " + v.getAdj());
        }
        Node.i = 1; // resetting timer
        DFS(vertices);
    }

    private static void DFS(ArrayList<Node> v) {
        Stack<Node> u = new Stack<>();
        for (int i = 0; i < v.size(); i++) { // checking if a vertices has null parent for new origin
            if(v.get(i).parent == null){
                v.get(i).distance = -1;
                DFS_visit(v.get(i), u);
            }
        }
        while (u.size() > 0){ // reverse the topological order
            Node curr = u.peek();
            System.out.format("%s timer:%d/%d   ", curr,curr.start,curr.end); // display the start and finish
            u.pop();
        }
    }

    private static void DFS_visit(Node v, Stack<Node> u) {


        v.start = Node.i++; // start the timer
        ArrayList<Node> adj = v.getAdj();
        // checking the adj vertices
        for (int i = 0; i < adj.size(); i++) {
            // determine if the adj have been explore
            if(adj.get(i).parent == null && adj.get(i).distance != -1){
                adj.get(i).parent = v; // set pointer
                adj.get(i).start = v.start;
                DFS_visit(adj.get(i), u);
            }
            else if(adj.get(i).parent != null && adj.get(i).end == 0){
                System.out.format("Cycle detected, topological sort is impossible\n");
                break;
            }
        }


        v.end = Node.i++; // end timer
        u.add(v);
    }

    private static ArrayList<Node> generateRandomGraph(Scanner userInput) {
        int order = 0, size = 0;
        Random random = new Random();
        System.out.print("Please enter a order: ");
        order = userInput.nextInt(); // number of nodes
        System.out.print("Please enter a size: ");
        size = userInput.nextInt(); //  number of edges
        ArrayList<Node> vertices = new ArrayList<>();

        // assign nodes
        for (int i = 0; i < order; i++) {
            vertices.add(new Node(i)); // adding nodes
        }
        // assign adj
        int i = 0;
        Node curr;
        while (i < size) {
            int randNode = random.nextInt(order), edge = random.nextInt(order);
            curr = vertices.get(randNode); // grab random node
            // check if node already has adj or it self
            if(randNode != edge && !curr.getAdj().contains(vertices.get(edge))){
                curr.adj(vertices.get(edge)); // grab random node to be a edge
                i++;
            }
        }
        return vertices;
    }
}
