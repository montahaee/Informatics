package GroProOnlinestrategiespiel.framework;

import GroProOnlinestrategiespiel.graph.Field;

/**
 * @author Montahaee
 * @version 1.0
 * @created 26-July-2022 12:37:40 PM
 */
public interface Description {

    default void way(Field field) {
        System.out.println("The field: " + field + " is currently empty!");
    }


    /**
     * To represent {@link #way(Field)}
     * in visual form on a display{@link #<GroProOnlinestrategiespiel.Demo>}.
     */
    void render();

}
