package recycelbin.display.framework;

import GroProOnlinestrategiespiel.graph.Field;
import recycelbin.display.Display;

/**
 * @author Montahaee
 * @version 1.0
 * @created 26-July-2022 12:37:40 PM
 */
public interface Description {

    void way(Field field);

    /**
     * To represent {@link #way(Field)}
     * in visual form on a display{@link #< Display >}.
     */
    void render();

}
