package graph.route;

import graph.data.Graph;
import graph.data.Node;
import org.javatuples.Pair;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class BusRouter extends Router{
    public BusRouter(Graph graph) {
        super(graph);
    }

    public BusRouter(Graph graph, String state) {
        super(graph, state);
    }

    @Override
    public void route(Node node) {
        this.resetVisits();
        visitBlacklist();

        PriorityQueue<Pair<Integer, Node>> nodes = new PriorityQueue<>();
        nodes.add(new Pair<Integer, Node>(0, node));
        while (!nodes.isEmpty()) {
            Pair<Integer, Node> front = nodes.poll();
            Node frontNode = front.getValue1();
            if (!frontNode.isVisited()) {
                frontNode.setVisited(true);
                int distance = front.getValue0();
                frontNode.setDistance(distance);
                nodes.addAll(frontNode.getAvailableWeightedNeighbors()
                        .stream()
                        .map(neighbor -> new Pair<Integer, Node>(neighbor.getValue1() + distance,
                                neighbor.getValue0()))
                        .collect(Collectors.toCollection(PriorityQueue::new)));
            }
        }
    }
}
