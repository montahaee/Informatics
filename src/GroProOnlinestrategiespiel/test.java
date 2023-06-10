package GroProOnlinestrategiespiel;

import CRS.utility.Pair;
import GroProOnlinestrategiespiel.graph.DirtySimpelMaze;
import GroProOnlinestrategiespiel.graph.Store;
import GroProOnlinestrategiespiel.graph.WeightedGraph;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class test <L,R> {

//    public static List<Integer> topLeft_to_bottomRight(int[][] mat, Stack<Integer> path, int i, int j)
//    {
//        List<Integer> paths = new ArrayList<>();
//        // base case
//        if (mat == null || mat.length == 0) {
//            throw new RuntimeException();
//        }
//
//        int row = mat.length;
//        int column = mat[0].length;
//
//        // if the last cell is reached, print the route
//        if (i == row - 1 && j == column - 1)
//        {
//            path.add(mat[i][j]);
////            System.out.println(path);
//
//            paths.add(path.pop());
//
//            return paths;
//        }
//        // include the current cell in the path
//        path.add(mat[i][j]);
//        // move right
//        if ((i >= 0 && i < row && j + 1 >= 0 && j + 1 < column)) {
//            topLeft_to_bottomRight(mat, path, i, j + 1);
//        }
//
//        // move down
//        if ((i + 1 >= 0 && i + 1 < row && j >= 0 && j < column)) {
//            topLeft_to_bottomRight(mat, path, i + 1, j);
//        }
//        // backtrack: remove the current cell from the path
//        paths.add(path.pop());
////            return paths;
//
//
////        return topLeft_to_bottomRight(mat,path,);
//        return  null;
//    }
    public static void main(String[] args) {
//        int[][] edges = new int[][]{
//                {0, 1,2}, {0, 3, 8},
//                {1, 0, 2}, {1, 2, 7}, {1, 3, 3},
//                {2, 1, 7}, {2, 3, 4}, {2, 4, 5},
//                {3, 0, 8}, {3, 1, 3}, {3, 2, 4}, {3, 4, 6},
//                {4, 2, 5}, {4, 3, 6}
//        };
//        System.out.println(edges[0][1]);
//        for (int[] edge : edges) {
//            for (int i : edge) {
//                System.out.print(i);
//            }
//            System.out.println();
//        }
//        WeightedGraph<Integer> graph2 = new WeightedGraph<>(edges, 10);
//        WeightedGraph<Integer>.ShortestPathTree tree2 =
//                graph2.getShortestPath(3);
//        System.out.println("\n");
//        tree2.printAllPaths();
//        System.out.println("\n");
//        List<Integer> path = tree2.getPath(5);
////        for (int p :
////                path) {
////            System.out.print(p + " ");
////        }
////        int[][] edges1 = new int[][]{
////                {0, 1, 2,0, 3},
////                {0, 4, 2,0, 3},
////                {0, 1, 5,0, 3},
////                {0, 1, 2,6, 3},
////                {0, 1, 2,0, 7},
////        };
////        WeightedGraph<Integer> graph = new WeightedGraph<>(edges1, 5);
////        WeightedGraph<Integer>.ShortestPathTree tree =
////                graph.getShortestPath(3);
////        System.out.println("\n");
////        tree.printAllPaths();
////        for (int[] edge : edges1) {
////            for (int i : edge) {
////                System.out.print(i);
////            }
////            System.out.println();
////        }
////        int[][] edges3 =  new int[4][4];
////        for (int i = 0; i <4 ; i++) {
////            for (int j = 0; j < 4; j ++) {
////                edges3[i][j] = (i+1)*(j+1) ;
//////                edges[i][j] = weights[j];
////            }
////        }
////        for (int[] edge : edges3) {
////            for (int i : edge) {
////                System.out.print(i + " ");
////            }
////            System.out.println();
////        }
//
//        int[][] edges4 = new int[25][3];
////        for (int i = 0; i <= 15; i++) {
////            for (int j = 0; j <= 2; j ++) {
//////                edges4[i][j] = (i+1) ;
////                edges4[i++] = new int[]{i, j, i*j} ;
////
//////                edges[i][j] = weights[j];
////            }
////        }
////        for ()
//        int k = 0;
//        int j =  0;
//        for (int i = 0; i <= 24; i++) {
//
////                edges4[i][j] = (i+1) ;
//            j = i% 5;
//            k = i/5;
//            if (j == 0) {
//                edges4[i] = new int[]{j, k, 20};
//            }else {
//                edges4[i] = new int[]{j, k, (i+1)*(1+k)} ;
//            }
//
//        }
//
//        for (int[] edge : edges4) {
//            for (int e : edge) {
//                System.out.print(e + " ");
//            }
//            System.out.println();
//        }
////        WeightedGraph<Integer> graph3 = new WeightedGraph<>(edges3, 5);
////        WeightedGraph<Integer>.ShortestPathTree tree3 =
////                graph3.getShortestPath(3);
////        System.out.println("\n");
////        tree3.printAllPaths();
//        WeightedGraph<Integer> graph4 = new WeightedGraph<>(edges4, 5);
//        WeightedGraph<Integer>.ShortestPathTree tree4 =
//                graph4.getShortestPath(1);
//
//        tree4.printAllPaths();
//
//        int[][] mat = { { 1, 2, 3 },
//                { 4, 5, 6 } };
//        System.out.println(topLeft_to_bottomRight(mat,new Stack<>(),0,0));

//        testQue();
        // creating a 2-dimensional list
        ArrayList<ArrayList<Integer>> maze = new ArrayList<ArrayList<Integer>>();
        maze.add(new ArrayList<Integer>(Arrays.asList(1, 4, 2)));
        maze.add(new ArrayList<Integer>(Arrays.asList(7, 8, 0)));
        maze.add(new ArrayList<Integer>(Arrays.asList(6, 3, 5)));
//TODO debugging the code to find the reason of the wrong position corresponding to corrected shortest path
        System.out.println("For the following maze: ");
        System.out.println(maze.get(0));
        System.out.println(maze.get(1));
        System.out.println(maze.get(2) + "\n");
        int[][] arr = {{1,4,2},{7,8,0},{6,3,5}};
        DirtySimpelMaze sMaze = new DirtySimpelMaze(arr);
//        SimpelMaze sMaze = new SimpelMaze(arr);
        System.out.println(sMaze.bestWay(3,3,new Store()));
//        int rdn = ThreadLocalRandom.current().nextInt(3,6);
//        System.out.println(rdn);
    }

//    public static List<Integer> topLeft_to_bottomRight(int[][] mat, int m, int n, int i, int j) {
//        Queue<Integer> queue = new LinkedList<>();
//        List<Integer> paths = new ArrayList<>();
////        if (m > mat.length-1 || n > mat[0].length -1) {
////            throw new RuntimeException();
////        }
//        if (i > m || j > n) {
//            throw new RuntimeException();
//        }
//
////        queue.add(mat[m][n]);
//        queue.add(mat[i][j]);
////        if(i == m && j == n){
////            return paths;
////        }
////        if (m== mat.length && n == mat[0].length) {
////            return paths;
////        }
////        while (m!= mat.length && n != mat[0].length){
////            topLeft_to_bottomRight(mat,m++,n);
////            topLeft_to_bottomRight(mat,m,n++);
////            paths.remove(paths.size()-1);
////        }
//        while (i!=m  || j != n){
//            topLeft_to_bottomRight(mat,m,n,i+1,j);
//            topLeft_to_bottomRight(mat,m,n,i,j+1);
//            paths.add(queue.remove());
//        }
//
//
//        return paths;
//    }


    public static void testQue() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.add(list);
        while (!queue.isEmpty()) {
            list = queue.poll();
        }
        System.out.println(list);
    }

}
