package com.company;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        //  Since the matrix is given there is no need to implement
        // a random matrix generator
        int[][] m1 = {{0,0},{1,2},{3,1},{4,4}};
        int[][] m2 = {{0,0},{0,1},{2,0},{3,0},{4,3}};
        int[][] m3 = {{0,1,1,1},
                    {0,0,1,0},
                    {0,0,0,1},
                    {1,1,0,0}};
        int[][] m4 = {{1,1,1,0,1},
                    {1,0,0,0,0},
                    {0,0,1,1,0}};
        Random random = new Random();
        Scanner userInput = new Scanner(System.in);

        int selection = 0;
        while (selection != -1) {
            selection = displayMenu(userInput);
            switch (selection) {
                case 1:
                    System.out.println("Problem 2 ----- \nExample 1:");
                    questionTwo(m1);
                    System.out.println("Example 2:");
                    questionTwo(m2);
                    break;
                case 2:
                    System.out.println("Problem 3 ----- \nExample 1:");
                    questionThree(m3);
                    System.out.println("Example 2:");
                    questionThree(m4);
                    break;
                case -1:
                    System.out.println("Thanks for using the program");
                    System.out.println();
                    break;
                default:
                    System.out.println("Error: Invalid option");
                    System.out.println();
                    break;
            }
        }

    }

    // since converting the matrix into a node cost to much time and mememory
    // one iterator for converting to nodes and other assign adj.
    private static void questionThree(int[][] m) {
        final int COL = m.length;
        final int ROW = m[0].length;
        boolean[][] isVisted = new boolean[COL][ROW];

        int islands = 0;
        for (int i = 0; i < COL; i++) {
            for (int j = 0; j < ROW; j++) {
                if(!isVisted[i][j] && m[i][j] == 1){
                    //long begin = System.nanoTime(), elapsed = 0; // (to calculate the runtime) 2 Part D
                    bfs(m,i,j,isVisted, COL, ROW);
                    //elapsed = System.nanoTime() - begin; // (to calculate the runtime)
                    //System.out.format("Time that bfs executed: %d nanoseconds\n", elapsed); // (to calculate the runtime) nanoseconds
                    islands++;
                }
            }
        }
        System.out.format("The total number of connected components is %d\n", islands);
    }

    private static void bfs(int[][] m, int row, int col, boolean[][] isVisted, final int COL, final int ROW) {
        // setting up fifo
        Queue<Node> queue = new LinkedList<>();
        //                          lB  lM  lU  mB mU rB rM rU check 8 neighbors from origin
        final int rowNeighbor[] = { -1, -1, -1, 0, 0, 1, 1, 1 };
        final int colNeighbor[] = { -1, 0, 1, -1, 1, -1, 0, 1 };
        queue.add(new Node(row,col)); //  starting fifo with origin coordinate
        isVisted[row][col] = true; // 'visit' origin node

        while (queue.size() > 0){
            Node curr = queue.peek(); // grab the node in line and save (coordinate)

            // check all potential neighbors
            for (int i = 0; i < 8; i++) {
                int x = curr.x + rowNeighbor[i];
                int y = curr.y + colNeighbor[i];
                // this check if it out bounce, equal 1, or has it been visit
                if(((x < COL) && (x >= 0)) && ((y < ROW) && (y >= 0)) && (m[x][y] == 1) && (!isVisted[x][y])) {
                    isVisted[x][y] = true; // check it so it won't be execute again
                    queue.add(new Node(x,y)); // saving the coordinate and add to line
                }
            }
            queue.remove(); // once fully check it is 'dequeue'
        }
    }


    private static void questionTwo(int[][] m) {
        ArrayList<Node> vertices = converMatrixtoNode(m);
        ArrayList<Node> v = (ArrayList<Node>) vertices.clone(); // copy list for total sum
        assignWeight(vertices);
        long begin = System.nanoTime(), elapsed = 0; // (to calculate the runtime) 2 Part D
        prim(vertices.get(0),vertices);
        elapsed = System.nanoTime() - begin; // (to calculate the runtime)
        System.out.format("Time that primm executed: %d nanoseconds\n", elapsed); // (to calculate the runtime) nanoseconds
    }

    private static void prim(Node origin, ArrayList<Node> vertices) {
        origin.parent = new Node(-1,-1); // setting -1 null parent
        origin.cost = 0; // setting 0
        double total = 0;
        build_MinHeap(vertices); //build a min heap based on cost

        while (vertices.size() > 0){
            Node curr = removeRoot(vertices); // pick a node with smallest cost
            HashMap<Node, Double> adj_weight = curr.getAdj(); // dictionary of node with adj weights
            Iterator<Node> itr = adj_weight.keySet().iterator(); // list of adj nodes

            // check all the potential adj path
            for (int i = 0; i < adj_weight.size(); i++) {
                Node curr_adj = itr.next(); // grab the current adj node
                if(adj_weight.get(curr_adj) < curr.cost && curr_adj.parent != null ) {
                    // check if curr adj node weight is bigger than curr adj and if minimum tree
                    curr.parent = curr_adj;
                    curr.cost = adj_weight.get(curr_adj); // update the cost
                    min_heapify(vertices,vertices.size(),0); // update the node
                }
            }
            total+= curr.cost;
            System.out.format("(%s -> %s) cost: %.3f\n", curr,curr.parent,curr.cost);
        }
        System.out.format("The minimum budget required to connect all the buildings is %.2f\n", total);
    }

    private static void assignWeight(ArrayList<Node> vertices) {
        for (int i = 0; i < vertices.size(); i++) {
            Node curr = vertices.get(i);
            for (int j = i+1; j < vertices.size(); j++) {
                Node adj = vertices.get(j);
                double weight = Math.sqrt(Math.pow((curr.x - adj.x), 2) + Math.pow((curr.y - adj.y), 2));
                curr.adj(adj,weight);
                adj.adj(curr,weight);
            }
        }
    }

    private static ArrayList<Node> converMatrixtoNode(int[][] matrix) {
        ArrayList<Node> vertices = new ArrayList<>();
        for (int[] column: matrix) {
            vertices.add(new Node(column[0], column[1]));
        }
        return vertices;
    }

    private static void min_heapify(ArrayList<Node> a, int size, int i){
        int min = i;
        int left = (2 * i) + 1;
        int right = (2 * i) + 2;
        if(left < size && a.get(left).cost < a.get(min).cost)
            min = left;
        if(right < size && a.get(right).cost < a.get(min).cost)
            min = right;
        if(min != i){
            swap(a,i,min);
            min_heapify(a,size,min);
        }
    }
    private static void build_MinHeap(ArrayList<Node> a){
        int size = a.size();
        int start = (size/2) - 1;
        for (int i = start; i >= 0 ; i--) {
            min_heapify(a,size,i);
        }
    }
    private static Node removeRoot(ArrayList<Node> a){
        Node value = a.get(0);
        a.remove(0);
        return value;
    }
    private static void  swap(ArrayList<Node> a, int left, int right){
        Node temp = a.get(right);
        a.set(right, a.get(left));
        a.set(left,temp);
    }
    public static int displayMenu(Scanner userInput) {
        System.out.println("------------------------------------------------------------------");
        System.out.println("              -Menu-            ");
        System.out.println(" 1: Construct lowest cost paved paths to all buildings");
        System.out.println(" 2: Find the exact number of connected components in a map");
        System.out.println("-1: Exit program");
        System.out.println("------------------------------------------------------------------");
        System.out.print("Please enter a option: ");
        return userInput.nextInt();
    }
}
