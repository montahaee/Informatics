package GroProOnlinestrategiespiel.graph;

import GroProOnlinestrategiespiel.utility.Coordinates;
import GroProOnlinestrategiespiel.utility.Shape;

import java.util.*;

public class SimpelMaze {
    private final List<List<Integer>> matrix;
//    private Shape<Integer,Coordinates> shape;

    public SimpelMaze(int[][] mat) {
        this.matrix = new ArrayList<>();
        for (int[] row : mat) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int column : row) {
                list.add(column);
            }
            matrix.add(list);
        }
    }

//  public Pair<List<Integer>, Integer> bestWay(int row, int column, Store store) {
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
        while (!queue.isEmpty()) {
            store = queue.poll();
            List<Integer> temp = store.getStorage();
//          add the path after cell-wise traverse
            if (store.cord().getRow() == row - 1 &&
                    store.cord().getColumn() == column - 1) {
                paths.add(store.getStorage());
                positions.add(store.cord());
            }// moving to right if traversing to the last row of square field completed.
            else if (store.cord().getRow() == row -1) {
//              Update the current path
                temp.add(matrix.get(store.cord().getRow()).get(store.cord().getColumn() + 1));
                queue.add(new Store(temp, store.cord().getRow(), store.cord().getColumn() + 1));
            }// downward moving if traversing to the last column of square field completed.
            else if (store.cord().getColumn() == column -1) {
                temp.add(matrix.get(store.cord().getRow() + 1).get(store.cord().getColumn()));
                queue.add(new Store(temp, store.cord().getRow() + 1, store.cord().getColumn()));
            }// Traversing in the both right and downward directions
            else {
//              move to the right
                temp.add(matrix.get(store.cord().getRow()).get(store.cord().getColumn() + 1));
                queue.add(new Store(temp, store.cord().getRow(), store.cord().getColumn() + 1));

//              downward travers
                temp.remove(temp.size() - 1);
//              update the current path
                temp.add(matrix.get(store.cord().getRow() + 1).get(store.cord().getColumn()));
                queue.add(new Store(temp, store.cord().getRow() + 1, store.cord().getColumn()));

            }
        }
        return new Shape<>(optimalPath(paths).getContents(),positions);
    }

    private List<Integer> getSums(List<List<Integer>> mazePaths) {
        List<Integer> sums = new ArrayList<>();
        for (List<Integer> ints: mazePaths) {

//            for java 8 or later
//            sum = ints.stream().reduce(0, (a,b) -> a+b)
            sums.add(ints.stream().reduce(0,Integer::sum));
        }
        return sums;
    }

    private Shape<Integer,Integer> optimalPath(List<List<Integer>> mazePaths) {
        Map<List<Integer>, Integer> paths = new HashMap<>();
        for (List<Integer> path: mazePaths) {

//          value = path.stream().reduce(0, (a,b) -> a+b)
            paths.put(path, path.stream().reduce(0, Integer::sum));
        }
        int min = getSums(mazePaths).stream().reduce(Integer.MAX_VALUE, Integer::min);
        List<Integer> shortestPath = new ArrayList<>();

        for (Map.Entry<List<Integer>, Integer> entry: paths.entrySet()) {
            if (min == entry.getValue()) {
                shortestPath = entry.getKey();
            }
        }
        return new Shape<>(shortestPath);
    }

}
