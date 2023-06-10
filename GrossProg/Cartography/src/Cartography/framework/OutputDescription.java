package Cartography.framework;

import java.util.List;
import java.util.Map;

public class OutputDescription extends Description {


    public OutputDescription() {

    }

    public OutputDescription(OutputDescription target) {
        super(target);
    }

    /**
     * @return output Description as submodule of Description.
     */
    @Override
    public Description clone() {

        return new OutputDescription(this);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("reset\n");
        List<Map.Entry<String, Orbit>> entries = super.getContent();
//        double min = entries.stream().min((e1,e2)->e1.getValue().getX() > e2.getValue().getX());
        double min_x = entries.stream().min((e1,e2)->
                Double.compare(e1.getValue().getX(),e2.getValue().getX())).get().getValue().getX();

        double min_t = entries.stream().min( (e1,e2)->
                Double.compare(e1.getValue().getX(),e2.getValue().getX())).get().getValue().getX();

        double max_x = entries.stream().max((e1,e2)->
                Double.compare(e1.getValue().getX(),e2.getValue().getX())).get().getValue().getX();

        result.append("set x_range [").append(min_x).append(":").append(max_x).append("]\n");
        double min_y = entries.stream().min((e1,e2)->
                Double.compare(e1.getValue().getX(),e2.getValue().getX())).get().getValue().getY();
        double max_y = entries.stream().min((e1,e2)->
                Double.compare(e1.getValue().getX(),e2.getValue().getX())).get().getValue().getY();

        result.append("set y_range [").append(min_y).append(":").append(max_y).append("]\n");
        result.append("size ratio 1.0\n");
        result.append("set titel ").append(super.getSubject()).append(" of the states, Iteration: ");
        result.append(count);
        result.append("\n");
        result.append("unset xtics\n");
        result.append("unset ytics\n");
        result.append("$data << EOD\n");
        for (int i = 0; i < entries.size(); i++) {
            result.append(String.format("%24f%24f%24f%6s%6d", entries.get(i).getValue().getX(), entries.get(i).getValue().getY(),
                    entries.get(i).getValue().radius(), entries.get(i).getKey(),i)).append("\n");
        }
        result.append("EOD\n").append("plot \\\n");
        result.append("'$data' using 1:2:3:5 with circles lc var notitle, \\\n");
        result.append("'$data' using 1:2:4:5 with labels font \"arial 9\" tc var notitle");


//        double min = entries.stream().map(e -> e.getValue().getX()).min(e)
        return result.toString();
    }

//    private double getX_lower(List<Orbit> orbits) {
//        double min = 0;
//        for (O)
//    }
}
