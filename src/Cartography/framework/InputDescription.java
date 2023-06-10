package Cartography.framework;

import java.util.*;

public class InputDescription extends Description {

//    private List<Map.Entry<>> input;
    public InputDescription() {

    }

    public InputDescription(InputDescription target) {
        super(target);
//        if (target != null) {
//            this.input = target.input;
//        }

    }

    /**
     * @return one of the Description subclass
     */
    @Override
    public Description clone() {
        return new InputDescription(this);
    }

    @Override
    public String toString() {
        String subject = super.getSubject();
        StringBuilder result = new StringBuilder(subject);
        result.append(" of the states\n");
        result.append("# ").append(String.format("%8s%10s%10s%13s","State ", subject,"latitude","longitude")).append("\n");
//        result.append("latitude").append("\t longitude");

        List<Map.Entry<String, Orbit>> entries = super.getContent();
        var neighbors = getNeighborhood(entries);
        entries.forEach( e-> result.append(String.format("%8S%10d%11.1f%11.1f", e.getKey(), e.getValue().getCharacteristic(),
                e.getValue().getLongitude(), e.getValue().getLatitude())).append("\n"));
//        entries.forEach(e-> result.append(e.getKey()).append("\t").append(e.getValue().getCharacteristic()).
//                append(e.getValue().getLongitude()).append("\t").append(e.getValue().getLatitude()));
        result.append("\n");
        result.append("# ").append("Neighborhoods");
        result.append("\n");

//        int sum = 0;
        neighbors.forEach( (k,v) ->{
            result.append(k).append(":");
            v.forEach(e -> result.append(" " ).append(e));
            result.append("\n");});

        count = neighbors.values().stream().mapToInt(List::size).sum();
        return result.toString();
    }

//    TODO insert some counter to specify iteration number , which is used really in the outputdescription1.
    private Map<String,List<String>> getNeighborhood(List<Map.Entry<String, Orbit>> entries) {
//      The entries of a linked hashmap is unlike normal hashmap saved as insertion order.
        Map<String,List<String>> neighborhoods = new LinkedHashMap<>();
//        List<Map.Entry<String, Orbit>> entries = super.getContent();
//      TODO comparing each distance of orbits with other one to find some neighborhood
        Orbit first, second;
        for (int i = 0; i < entries.size(); i++) {
            first = entries.get(i).getValue();
            List<String> neighbors = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                second = entries.get(j).getValue();
                if (first.getDistance(second.getLatitude(), second.getLongitude()) ==
                        first.radius() + second.radius()) {
                    neighbors.add(entries.get(j).getKey());
//                    count++;
                } //(else {
//                    count--;
//                }
            }
            for (int j = i + 1; j < entries.size(); j++) {
                second = entries.get(j).getValue();
                if (first.getDistance(second.getLatitude(), second.getLongitude()) ==
                        first.radius() + second.radius()) {
                    neighbors.add(entries.get(j).getKey());
//                    count++;
                } //else {
//                    count--;
//                }
            }
            neighborhoods.put(entries.get(i).getKey(),neighbors);
        }
//      TODO try the above nested loop using lambda operation
//        entries.forEach(e -> e.getValue().getDistance());

//        entries.forEach(e-> e.getValue().);
//        var t= Stream.of(super.getContent())
        return neighborhoods;
    }



}
