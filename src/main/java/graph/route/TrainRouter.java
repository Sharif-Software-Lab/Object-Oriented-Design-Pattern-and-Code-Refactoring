package graph.route;

import graph.data.Graph;
import graph.data.Node;
import org.javatuples.Pair;

import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class TrainRouter extends Router{
    public TrainRouter(Graph graph) {
        super(graph);
    }

    public TrainRouter(Graph graph, String state) {
        super(graph, state);
    }

    @Override
    public void route(Node node) {
        this.resetVisits();
        visitBlacklist();

        Queue<Pair<Node, Integer>> nodes = new LinkedList<>();
        nodes.add(new Pair<Node, Integer>(node, 0));
        while (!nodes.isEmpty()) {
            Pair<Node, Integer> front = nodes.poll();
            Node frontNode = front.getValue0();
            if (!frontNode.isVisited()) {
                frontNode.setVisited(true);
                int distance = front.getValue1();
                frontNode.setDistance(distance);
                nodes.addAll(frontNode.getAvailableNeighbors()
                        .stream()
                        .map(neighbor -> new Pair<Node, Integer>(neighbor, distance + 1))
                        .collect(Collectors.toCollection(ArrayList::new)));
            }
        }
    }
}
