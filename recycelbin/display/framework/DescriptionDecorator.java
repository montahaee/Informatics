package recycelbin.display.framework;


import GroProOnlinestrategiespiel.graph.Field;
import recycelbin.display.Display;

/**
 * The primary purpose of this class is to define {@link #wrapping}
 * interface for all concrete decorators namely {@link InputDescription
 * #{@link OutputDescription }}.
 * @author Montahaee
 * @version 1.0
 * @created 26-July-2022 12:37:40 PM
 */
public class DescriptionDecorator implements Description {
    protected Description wrapping;


    public DescriptionDecorator(Description description) {
        this.wrapping = description;
    }

    /**
     * @param field is the available place for traversing
     */
    @Override
    public void way(Field field) {
        this.wrapping.way(field);
    }

    /**
     * To represent {@link #way(Field)}
     * in visual form on a display{@link #<Display>}.
     */
    @Override
    public void render() {
        this.wrapping.render();
    }
}
