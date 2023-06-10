package GroProOnlinestrategiespiel.graph;

import GroProOnlinestrategiespiel.utility.Coordinates;

import java.util.*;

/**
 * This class is employed for Sorting the cells
 * of the matrix and its coordinates
 *
 * @author Montahaee
 * @version 1.0
 * @created 02-August-2022 12:37:40 PM
 */
public class Store {

    private final List<Integer> storage;
    private final Coordinates coordinates;
//  Default constructor
    public Store() {
        this.storage = new ArrayList<>();
        this.coordinates = new Coordinates(0,0);
//        this.coordinates = new Coordinates(1,1);
    }

//  Explicit constructor
    public Store(List<Integer> that_storage, int x, int y) {
        this.storage = new ArrayList<>();
        this.storage.addAll(that_storage);
        this.coordinates = new Coordinates(x, y);
    }

    /**
     *
     * @return the storage's content of matrix's calls.(its length: m*n).
     */
    public List<Integer> getStorage() {
        return storage;
    }

    /**
     *
     * @param x will be inserted in the storage
     */
    public void add(int x) {
        this.storage.add(x);
    }

    /**
     *
     * @return the coordinate of a specific cell
     */
    public Coordinates cord() {
        return coordinates;
    }
}
