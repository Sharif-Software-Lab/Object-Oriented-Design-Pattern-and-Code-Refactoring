package graph;


import graph.data.Edge;
import graph.data.Graph;
import graph.data.Node;
import graph.route.BusRouter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandParameterizedTest {

    @ParameterizedTest
    @MethodSource("sourceAndDestParams")
    void hasWayFromSourceToDest(Graph graph, int source, int dest) {
        Command command = new Command(new BusRouter(graph));
        assertTrue(command.distance(graph.getGraph().get(source), graph.getGraph().get(dest)) > -1);
    }

    private static Stream<Arguments> sourceAndDestParams() {
        ArrayList<Node> nodes = new ArrayList<Node>();
        for (int i = 0; i < 5; i++) nodes.add(new Node());
        Edge.createEdge(nodes.get(0), nodes.get(1), false, 1);
        Edge.createEdge(nodes.get(0), nodes.get(2), false, 1);
        Edge.createEdge(nodes.get(2), nodes.get(3), false, 1);
        Edge.createEdge(nodes.get(2), nodes.get(3), false, 1);

        Graph graph = new Graph(nodes);
        return Stream.of(
                Arguments.of(graph, 0, 3),
                Arguments.of(graph, 1, 3),
                Arguments.of(graph, 1, 2)
        );
    }


}
