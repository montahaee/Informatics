package GroProOnlinestrategiespiel.graph;

public class Edge {
    int u;
    int v;
//    Edge edge

    public Edge(int u, int v) {
        this.u = u;
        this.v = v;
    }

    @Override
    public boolean equals(Object o) {
        return u == ((Edge)o).u && v == ((Edge)o).v;
    }
}
