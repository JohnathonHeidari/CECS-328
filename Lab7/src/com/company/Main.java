package com.company;


import java.util.*;

public class Main {
    /**
     *
     * @node.java design my own to work with the program
     */
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        questionPartA(userInput);
        questionPartB(userInput);
    }

    private static void questionPartA(Scanner userInput) {
        // creating vertices
        Node a = new Node(0); Node b = new Node(1); Node c = new Node(2);
        Node d = new Node(3); Node e = new Node(4); Node f = new Node(5);
        Node g = new Node(6); Node h = new Node(7);
        ArrayList<Node> vertices = new ArrayList<>();
        vertices.add(a); vertices.add(b); vertices.add(c); vertices.add(d); vertices.add(e);
        vertices.add(f); vertices.add(g); vertices.add(h);
        // assigning adj
        a.adj(d); a.adj(c);
        b.adj(c); b.adj(e);
        c.adj(d); c.adj(b); c.adj(a);
        d.adj(a); d.adj(c); d.adj(e); d.adj(f);
        e.adj(b); e.adj(d); e.adj(f);
        f.adj(e); f.adj(d); f.adj(h);
        h.adj(f);

        // determine the starting vertex
        int start = startingPoint(userInput);
        Node origin = vertices.get(start);
        System.out.println("The shortest path with distance");
        bfs(origin);
        System.out.println();
    }

    private static void bfs(Node origin) {
        // setting up fifo
        Queue<Node> queue = new LinkedList<>();
        Node curr;
        queue.add(origin);

        // assign first origin a distance
        origin.setDistance(-1);
        while (queue.size() > 0){
            curr = queue.peek();
            ArrayList<Node> adj = curr.getAdj();

            int size = adj.size();
            for (int i = 0; i < size; i++) {
                // determine if the adj have been explore
                if(adj.get(i).parent == null && adj.get(i).distance != -1){
                    queue.add(adj.get(i));
                    adj.get(i).setDistance((curr.getDistance() == -1 ? 0:curr.getDistance()) + 1);
                    adj.get(i).parent = curr;
                }
            }
            // output
            if(queue.size() == 1)
                System.out.format("dst: %d '%s' " , curr.distance, curr);
            else
                System.out.format("dst: %d '%s' -> " ,  curr.distance, curr);

            queue.remove();
        }
    }

    private static int startingPoint(Scanner userInput) {
        // determine the origin vertices
        while (true){
            System.out.println("Please select a starting vertex: ");
            String start = userInput.next();
            start = start.toLowerCase();
            switch (start){
                case "a":
                    return 0;
                case "b":
                    return 1;
                case "c":
                    return 2;
                case "d":
                    return 3;
                case "e":
                    return 4;
                case "f":
                    return 5;
                case "g":
                    return 6;
                case "h":
                    return 7;
                case "I":
                    return 8;
                default:
                    System.out.println("Invalid vertex please try again.");
            }
        }
    }

    private static void questionPartB(Scanner userInput) {
        // creating vertices
        Node a = new Node(0); Node b = new Node(1); Node c = new Node(2);
        Node d = new Node(3); Node e = new Node(4); Node f = new Node(5);
        ArrayList<Node> vertices = new ArrayList<>();
        vertices.add(a); vertices.add(b); vertices.add(c); vertices.add(d); vertices.add(e); vertices.add(f);
        // assigning adj
        a.adj(d);
        b.adj(d); b.adj(f);
        c.adj(d); c.adj(e);
        d.adj(a); d.adj(c); d.adj(b);
        e.adj(c);
        f.adj(b);

        Explore(vertices);

        // creating vertices
        Node g = new Node(6); Node h = new Node(7); Node i = new Node(8);
        vertices.clear();
        vertices.add(g); vertices.add(h); vertices.add(i);

        // assigning adj
        g.adj(h); g.adj(i);
        h.adj(g); h.adj(i);
        i.adj(g); i.adj(h);

        Explore(vertices);
    }
    
    private static void Explore(ArrayList<Node> vertices) {
        // initialize all unexplored areas
        String status = "";
        for (Node v: vertices) {
            v.color = "gray";
        }
        // exploring all possible vertices
        for (Node v: vertices) {
            if(v.color.equals("gray")){
                v.color = "blue";
                status = Is_bipartite(v);
                if(status.equals("NOT bipartite"))
                    break;
            }
        }
        System.out.println(status);
        for (Node v: vertices) {
            System.out.format("'%s' (%s) " , v, v.color);
        }
        System.out.println();
    }

    private static String Is_bipartite(Node origin) {
        // setting up fifo
        Queue<Node> u = new LinkedList<>();
        Node v;
        u.add(origin);

        // assign first origin a distance
        origin.setDistance(-1);

        while (u.size() > 0){
            v = u.peek();
            ArrayList<Node> adj = v.getAdj();

            int size = adj.size();
            // determine if the adj have ben explore
            for (int i = 0; i < size; i++) {
                if (adj.get(i).color.equals("gray")) {
                    adj.get(i).color = v.color.equals("blue") ? "red" : "blue"; // opposite color
                    u.add(adj.get(i));
                }
                else if (v.color.equals(adj.get(i).color)) {
                    return "NOT bipartite";
                }
            }
            u.remove();
        }
        return "IS bipartite";
    }

}
