package process;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Storage {
    private final List<CompoundPipe> stocklist;

    public Storage() {
        super();
        this.stocklist = new ArrayList<>();
        stocklist.add(new CompoundPipe(2));
        stocklist.add(new CompoundPipe(3));
        stocklist.add(new CompoundPipe(4));
        stocklist.add(new CompoundPipe(5));
    }
    public List<CompoundPipe> getStocklist() {
        return Collections.unmodifiableList(stocklist);
    }
}
