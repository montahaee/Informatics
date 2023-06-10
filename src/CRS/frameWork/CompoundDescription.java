package CRS.frameWork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompoundDescription extends BaseDescription{

    private final List<Description> descriptions;

    public CompoundDescription() {
        this.descriptions = new ArrayList<>();
    }

    public void add(Description description) {
        descriptions.add(description);
    }

    public void add(Description... components) {
        this.descriptions.addAll(Arrays.asList(components));
    }

    public void remove(Description component) {
        this.descriptions.remove(component);
    }

    public void remove(Description... components) {
        this.descriptions.removeAll(Arrays.asList(components));
    }

    public void clear() {
        this.descriptions.clear();
    }

    /**
     * @param crs is nonnull object to the store the sparse matrix optimally.
     */
    @Override
    public String content(CRS crs) {
        for (Description description : this.descriptions) {
            description.content(crs);
        }
       return descriptions.toString();
    }
}
