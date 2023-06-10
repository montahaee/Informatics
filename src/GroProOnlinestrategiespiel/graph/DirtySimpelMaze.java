package GroProOnlinestrategiespiel.graph;


import GroProOnlinestrategiespiel.utility.*;

import java.util.*;

/**
 *
 * @<code>SimpelMaze</code> find first all possible path from
 *
 * @author Montahaee
 * @version 1.0
 * @created 02-August-2022 12:37:40 PM
 */

public class DirtySimpelMaze {

    private final List<List<Integer>> matrix;
//    private Shape<Integer,Coordinates> shape;

    public DirtySimpelMaze(int[][] mat) {
        this.matrix = new ArrayList<>();
        for (int[] row : mat) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int column : row) {
                list.add(column);
            }
            matrix.add(list);
        }
//        this.shape = new Shape<>();
    }

//    public Pair<List<Integer>, Integer> bestWay(int row, int column, Store store) {
    public Shape<Integer,Coordinates> bestWay(int row, int column, Store store) {
        List<List<Integer>> paths = new ArrayList<>();
        List<Coordinates> positions = new ArrayList<>();
        Queue<Store> queue = new LinkedList<>();
//       get the value of top corner left
        int tlv = matrix.get(0).get(0);
        store.add(tlv);
        store.cord().setRow(0);
        store.cord().setColumn(0);

//        Additions the top left cell in the que
        queue.add(store);
//        int curr = 0;
        while (!queue.isEmpty()) {
            store = queue.poll();
            List<Integer> temp = store.getStorage();
            Coordinates coordinates = store.cord();
            positions.add(store.cord());
//            curr++;
//          add the path after cell-wise traverse
            if (store.cord().getRow() == row - 1 &&
                store.cord().getColumn() == column - 1) {

//              TODO think about getting indices of matrix and store in a suitable container
//                positions.add(store.cord());
                paths.add(store.getStorage());
//                positions.set(positions.size() -1, store.cord());
//                positions.add(coordinates);
            }// moving to right if traversing to the last row of square field completed.
            else if (store.cord().getRow() == row -1) {
//                positions.add(store.cord());
//                List<Integer> temp = store.getStorage();
//              Update the current path
                coordinates = new Coordinates(coordinates.getRow(), coordinates.getColumn() +1);
//                temp.add(matrix.get(coordinates.getX()).get(coordinates.getY()));
                temp.add(matrix.get(store.cord().getRow()).get(store.cord().getColumn() + 1));
//                queue.add(new Store(temp,coordinates.getX(), coordinates.getY()));
                store = new Store(temp, store.cord().getRow(), store.cord().getColumn() + 1);
                queue.add(store);
//                positions.set(paths.size()-1, new Coordinates(store.cord().getX(),store.cord().getY() + 1));
//                positions.add(new Coordinates(store.cord().getX(),store.cord().getY() + 1));
//                positions.add(coordinates);
//                positions.set(positions.size() -1, coordinates);
                positions.remove(positions.size()-1);

            }// downward moving if traversing to the last column of square field completed.
            else if (store.cord().getColumn() == column -1) {
                coordinates = new Coordinates(coordinates.getRow() + 1, coordinates.getColumn());
                temp.add(matrix.get(store.cord().getRow() + 1).get(store.cord().getColumn()));
//                temp.add(matrix.get(coordinates.getX()).get(coordinates.getY()));
//                queue.add(new Store(temp,coordinates.getX(), coordinates.getY()));
                queue.add(new Store(temp, store.cord().getRow() + 1, store.cord().getColumn()));
//                positions.set(paths.size()-1,new Coordinates(store.cord().getX() + 1,store.cord().getY()));
//                positions.add(new Coordinates(store.cord().getX() + 1,store.cord().getY()));
//                positions.set(positions.size()-1, coordinates);
                positions.remove(positions.size()-1);

            }// Traversing in the both right and downward directions
            else {
//                positions.add(store.cord());

//              move to the right
                coordinates = new Coordinates(coordinates.getRow(), coordinates.getColumn() +1);
//                temp.add(matrix.get(coordinates.getX()).get(coordinates.getY()));
//                queue.add(new Store(temp,coordinates.getX(), coordinates.getY()));
//                store.cord().setColumn(store.cord().getColumn() + 1);
//                temp.add(matrix.get(store.cord().getRow()).get(store.cord().getColumn()));
                temp.add(matrix.get(store.cord().getRow()).get(store.cord().getColumn() + 1));
                queue.add(new Store(temp, store.cord().getRow(), store.cord().getColumn() +1));
//                positions.set(positions.size(),new Coordinates(store.cord().getX() + 1,store.cord().getY()));
//                positions.add(new Coordinates(store.cord().getX() + 1,store.cord().getY()));
//                positions.set(positions.size()-1, coordinates);
//              downward travers
                positions.add(coordinates);

                temp.remove(temp.size() - 1);
//                positions.remove(positions.size()-1);
//              update the current path
                coordinates = new Coordinates(coordinates.getRow() + 1, coordinates.getColumn());
//                positions.set(positions.size()-1, coordinates);
//                positions.remove(positions.size()-1);
//                positions.add(coordinates);

//                temp.add(matrix.get(coordinates.getX()).get(coordinates.getY()));
//                queue.add(new Store(temp,coordinates.getX(), coordinates.getY()));
                temp.add(matrix.get(store.cord().getRow() + 1).get(store.cord().getColumn()));
                queue.add(new Store(temp, store.cord().getRow() + 1, store.cord().getColumn()));
//                positions.set(paths.size()-1,new Coordinates(store.cord().getX() + 1,store.cord().getY()));
//                positions.add(new Coordinates(store.cord().getX() + 1,store.cord().getY()));
//                positions.set(positions.size()-1, coordinates);
//                positions.remove(positions.size()-1);


            }
//            positions.add(coordinates);
        }

//        return getPath(paths);
//        return optimalPath(paths);
//        shape = new Shape<>(optimalPath(paths).getX(),positions);
        return new Shape<>(optimalPath(paths).getContents(),positions);
    }

    private List<Integer> getSums(List<List<Integer>> mazePaths) {
        List<Integer> sums = new ArrayList<>();
        for (List<Integer> ints: mazePaths) {
//            sums.add(ints.stream().reduce(0, Shape::cumulative_sum));
//            for java 8 or later
//            sum = ints.stream().reduce(0, (a,b) -> a+b)
            sums.add(ints.stream().reduce(0,Integer::sum));
        }
        return sums;
    }

//    private Map<List<Integer>,Integer> getPath(List<List<Integer>> mazePaths) {
//    private List<Integer> getPath(List<List<Integer>> mazePaths) {
//
////      Make mapping each row of matrix using its sum
//        List<Integer> sums = getSums(mazePaths);
//        Map<List<Integer>, Integer> map = new HashMap<>();
//        for (int i = 0; i < mazePaths.size(); i++) {
//            map.put(mazePaths.get(i), sums.get(i));
//        }
////        for java 8 or later
//        int min = sums.stream().reduce(Integer.MAX_VALUE, Integer::min);
////        for ()
////        final List<Integer> res;
////        return map.forEach((k, v) -> {
////          res =  (v == min)? k : null
////        });
//            return null;
//    }

//    private Pair<List<Integer>, Integer> optimalPath(List<List<Integer>> mazePaths) {
    private Shape<Integer,Integer> optimalPath(List<List<Integer>> mazePaths) {
        Map<List<Integer>, Integer> paths = new HashMap<>();
        for (List<Integer> path: mazePaths) {

//          value = path.stream().reduce(0, (a,b) -> a+b)
            paths.put(path, path.stream().reduce(0, Integer::sum));
        }
        int min = getSums(mazePaths).stream().reduce(Integer.MAX_VALUE, Integer::min);
        List<Integer> shortestPath = new ArrayList<>();
//        Pair<List<Integer>,Integer>   shortestPath = new Pair<>(mazePaths.get(0), paths.get(mazePaths.get(0)));
        for (Map.Entry<List<Integer>, Integer> entry: paths.entrySet()) {
            if (min == entry.getValue()) {
//                shortestPath = new Pair<>(entry.getKey(), entry.getValue());
                shortestPath = entry.getKey();
            }
        }
//        return shortestPath;
        return new Shape<>(shortestPath);
    }

///    private List<Integer> shortestPath(List<List<Integer>> mazePaths) {
//        Map<List<Integer>, Integer> paths = new HashMap<>();
//        CompoundArithmetic<Integer> compound = new CompoundArithmetic<>();
////        var t = new Shape<Integer,Integer>(mazePaths.get(0)).cumulative_sum();
//        for (int i = 0; i < mazePaths.size(); i++) {
////          TODO think about to find reasonable structure to avoid
//            Shape<Integer,Integer> shape = new Shape<>(mazePaths.get(i));
//            compound.insert(shape.cumulative_sum());
//            int min = shape.cumulative_sum(new Arithmetic<>() {
//
//
//                /**
//                 * @return
//                 */
//                @Override
//                public Integer none() {
//                    return 0;
//                }
//
//                @Override
//                public Integer add(Integer ls, Integer rs) {
//                    return ls + rs;
//                }
//            });
////            int r = shape.cumulative_sum((int a, int b) -> a+b);
//        }
//
//
//
//
//
//
//        return null;
//    }
}
