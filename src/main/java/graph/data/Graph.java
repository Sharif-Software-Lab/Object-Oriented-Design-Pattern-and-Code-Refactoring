package graph.data;

import lombok.Getter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;
import org.javatuples.Pair;

public class Graph {
    @Getter
    private final ArrayList<Node> graph;

    public Graph(ArrayList<Node> graph) {
        this.graph = graph;
    }

    public void addNode(Node node) {
        graph.add(node);
    }

    public void resetGraph() {
        this.graph.clear();
    }

}
