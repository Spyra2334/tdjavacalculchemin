package main;

import java.util.ArrayList;
import java.util.List;


public class Vertex {

    private String name;

    private List<Edge> edges = new ArrayList<Edge>();

    private boolean visited;

    private int dist;

    public Vertex(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void connectTo(Vertex target, int distance) {
        edges.add(new Edge(target, distance));
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

    public int getDist() {
        return dist;
    }

    public boolean isVisited() {
        return visited;
    }


}
