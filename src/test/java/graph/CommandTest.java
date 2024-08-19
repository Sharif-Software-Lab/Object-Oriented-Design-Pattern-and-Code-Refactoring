package graph;

import graph.data.Edge;
import graph.data.Graph;
import graph.data.Node;
import static org.junit.Assert.*;

import graph.route.BusRouter;
import graph.route.Router;
import graph.route.TrainRouter;
import org.junit.Test;

import java.util.ArrayList;

public class CommandTest {
    public void resetGraph(Router router) {
        router.getGraph().resetGraph();
        router.setBlacklist(new ArrayList<>());
    }

    /*
    Set-ups the following graph with 5 nodes
    1 -> 5 , w = 0
    2 -> 4 , w = 2
    3 -> 2 , w = 0
    3 -> 4 , w = 3
    4 -> 5 , w = 2
    5 -> 4 , w = 1
     */
    public void setupGraph1(Router router) {
        resetGraph(router);
        Node node1 = new Node();
        Node node2 = new Node();
        Node node3 = new Node();
        Node node4 = new Node();
        Node node5 = new Node();

        Graph graph = router.getGraph();

        Edge.createEdge(node1, node5, false, 0);
        Edge.createEdge(node2, node4, false, 2);
        Edge.createEdge(node3, node2, false, 0);
        Edge.createEdge(node3, node4, false, 3);
        Edge.createEdge(node4, node5, false, 2);
        Edge.createEdge(node5, node4, false, 1);

        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        graph.addNode(node5);
    }

    /*
    Set-ups the following graph with 6 nodes
    2 -> 1 , w = 2
    2 -> 3 , w = 2
    2 -> 5 , w = 0
    5 -> 2 , w = 1
    5 -> 4 , w = 2
    5 -> 6 , w = 2
     */
    public void setupGraph2(Router router) {
        resetGraph(router);
        Node node1 = new Node();
        Node node2 = new Node();
        Node node3 = new Node();
        Node node4 = new Node();
        Node node5 = new Node();
        Node node6 = new Node();

        Graph graph = router.getGraph();

        Edge.createEdge(node2, node1, false, 2);
        Edge.createEdge(node2, node3, false, 2);
        Edge.createEdge(node2, node5, false, 0);
        Edge.createEdge(node5, node2, false, 1);
        Edge.createEdge(node5, node4, false, 2);
        Edge.createEdge(node5, node6, false, 2);

        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        graph.addNode(node5);
        graph.addNode(node6);
    }

    @Test
    public void testBidirectionalTrainGraph1() {
        Graph graph = new Graph(new ArrayList<>());
        Router router = new TrainRouter(graph);
        setupGraph1(router);
        Command command = new Command(router);

        Node node1 = router.getGraph().getGraph().get(0);
        Node node2 = router.getGraph().getGraph().get(1);
        Node node3 = router.getGraph().getGraph().get(2);
        Node node4 = router.getGraph().getGraph().get(3);
        Node node5 = router.getGraph().getGraph().get(4);

        assertEquals(command.distance(node1, node2), 3);
        assertEquals(command.distance(node1, node3), 3);
        assertEquals(command.distance(node1, node4), 2);
    }

    @Test
    public void testBidirectionalTrainGraph2() {
        Graph graph = new Graph(new ArrayList<>());
        Router router = new TrainRouter(graph);
        setupGraph2(router);
        Command command = new Command(router);

        Node node1 = router.getGraph().getGraph().get(0);
        Node node2 = router.getGraph().getGraph().get(1);
        Node node3 = router.getGraph().getGraph().get(2);
        Node node4 = router.getGraph().getGraph().get(3);
        Node node5 = router.getGraph().getGraph().get(4);
        Node node6 = router.getGraph().getGraph().get(5);

        assertEquals(command.distance(node1, node2), 1);
        assertEquals(command.distance(node1, node3), 2);
        assertEquals(command.distance(node1, node5), 2);
        assertEquals(command.distance(node3, node6), 3);
    }

    @Test
    public void testBidirectionalBusGraph1() {
        Graph graph = new Graph(new ArrayList<>());
        Router router = new BusRouter(graph);
        setupGraph1(router);
        Command command = new Command(router);

        Node node1 = router.getGraph().getGraph().get(0);
        Node node2 = router.getGraph().getGraph().get(1);
        Node node3 = router.getGraph().getGraph().get(2);
        Node node4 = router.getGraph().getGraph().get(3);
        Node node5 = router.getGraph().getGraph().get(4);

        assertEquals(command.distance(node1, node2), 3);
        assertEquals(command.distance(node1, node3), 3);
        assertEquals(command.distance(node1, node4), 1);
    }

    @Test
    public void testBidirectionalBusGraph2() {
        Graph graph = new Graph(new ArrayList<>());
        Router router = new BusRouter(graph);
        setupGraph2(router);
        Command command = new Command(router);

        Node node1 = router.getGraph().getGraph().get(0);
        Node node2 = router.getGraph().getGraph().get(1);
        Node node3 = router.getGraph().getGraph().get(2);
        Node node4 = router.getGraph().getGraph().get(3);
        Node node5 = router.getGraph().getGraph().get(4);
        Node node6 = router.getGraph().getGraph().get(5);

        assertEquals(command.distance(node1, node2), 2);
        assertEquals(command.distance(node1, node3), 4);
        assertEquals(command.distance(node1, node5), 2);
        assertEquals(command.distance(node3, node6), 4);
    }

    @Test
    public void testUnidirectionalTrainGraph1() {
        Graph graph = new Graph(new ArrayList<>());
        Router router = new TrainRouter(graph);
        setupGraph1(router);
        Command command = new Command(router);
        command.toggleDirectional();

        Node node1 = router.getGraph().getGraph().get(0);
        Node node2 = router.getGraph().getGraph().get(1);
        Node node3 = router.getGraph().getGraph().get(2);
        Node node4 = router.getGraph().getGraph().get(3);
        Node node5 = router.getGraph().getGraph().get(4);

        assertEquals(command.distance(node1, node2), -1);
        assertEquals(command.distance(node1, node3), -1);
        assertEquals(command.distance(node1, node4), 2);
    }

    @Test
    public void testUnidirectionalTrainGraph2() {
        Graph graph = new Graph(new ArrayList<>());
        Router router = new TrainRouter(graph);
        setupGraph2(router);
        Command command = new Command(router);
        command.toggleDirectional();

        Node node1 = router.getGraph().getGraph().get(0);
        Node node2 = router.getGraph().getGraph().get(1);
        Node node3 = router.getGraph().getGraph().get(2);
        Node node4 = router.getGraph().getGraph().get(3);
        Node node5 = router.getGraph().getGraph().get(4);
        Node node6 = router.getGraph().getGraph().get(5);

        assertEquals(command.distance(node1, node2), -1);
        assertEquals(command.distance(node1, node3), -1);
        assertEquals(command.distance(node1, node5), -1);
        assertEquals(command.distance(node3, node6), -1);
        assertEquals(command.distance(node2, node5), 1);
    }

    @Test
    public void testUnidirectionalBusGraph1() {
        Graph graph = new Graph(new ArrayList<>());
        Router router = new BusRouter(graph);
        setupGraph1(router);
        Command command = new Command(router);
        command.toggleDirectional();

        Node node1 = router.getGraph().getGraph().get(0);
        Node node2 = router.getGraph().getGraph().get(1);
        Node node3 = router.getGraph().getGraph().get(2);
        Node node4 = router.getGraph().getGraph().get(3);
        Node node5 = router.getGraph().getGraph().get(4);

        assertEquals(command.distance(node1, node2), -1);
        assertEquals(command.distance(node1, node3), -1);
        assertEquals(command.distance(node1, node4), 1);
    }

    @Test
    public void testUnidirectionalBusGraph2() {
        Graph graph = new Graph(new ArrayList<>());
        Router router = new BusRouter(graph);
        setupGraph2(router);
        Command command = new Command(router);
        command.toggleDirectional();

        Node node1 = router.getGraph().getGraph().get(0);
        Node node2 = router.getGraph().getGraph().get(1);
        Node node3 = router.getGraph().getGraph().get(2);
        Node node4 = router.getGraph().getGraph().get(3);
        Node node5 = router.getGraph().getGraph().get(4);
        Node node6 = router.getGraph().getGraph().get(5);

        assertEquals(command.distance(node1, node2), -1);
        assertEquals(command.distance(node1, node3), -1);
        assertEquals(command.distance(node1, node5), -1);
        assertEquals(command.distance(node3, node6), -1);
        assertEquals(command.distance(node2, node6), 2);
    }

    @Test
    public void testTrainBlacklistGraph1() {
        Graph graph = new Graph(new ArrayList<>());
        Router router = new TrainRouter(graph);
        setupGraph1(router);
        Command command = new Command(router);

        Node node1 = router.getGraph().getGraph().get(0);
        Node node2 = router.getGraph().getGraph().get(1);
        Node node3 = router.getGraph().getGraph().get(2);
        Node node4 = router.getGraph().getGraph().get(3);
        Node node5 = router.getGraph().getGraph().get(4);

        command.addHatedCity(node4);

        assertEquals(command.distance(node1, node5), 1);
        assertEquals(command.distance(node1, node3), -1);
        assertEquals(command.distance(node1, node2), -1);
    }

    @Test
    public void testBusBlacklistGraph1() {
        Graph graph = new Graph(new ArrayList<>());
        Router router = new BusRouter(graph);
        setupGraph1(router);
        Command command = new Command(router);

        Node node1 = router.getGraph().getGraph().get(0);
        Node node2 = router.getGraph().getGraph().get(1);
        Node node3 = router.getGraph().getGraph().get(2);
        Node node4 = router.getGraph().getGraph().get(3);
        Node node5 = router.getGraph().getGraph().get(4);

        command.addHatedCity(node4);

        assertEquals(command.distance(node1, node5), 0);
        assertEquals(command.distance(node1, node3), -1);
        assertEquals(command.distance(node1, node2), -1);
    }

    @Test
    public void whichGraph1() {
        Graph graph = new Graph(new ArrayList<>());
        Router router = new BusRouter(graph);
        setupGraph1(router);
        Command command = new Command(router);

        Node node1 = router.getGraph().getGraph().get(0);
        Node node2 = router.getGraph().getGraph().get(1);
        Node node3 = router.getGraph().getGraph().get(2);
        Node node4 = router.getGraph().getGraph().get(3);
        Node node5 = router.getGraph().getGraph().get(4);

        assertEquals(command.which(node1, node5), "Bus");
        assertEquals(command.which(node1, node4), "Bus");
        assertEquals(command.which(node3, node4), "Train");
        assertEquals(command.which(node3, node2), "Bus");
    }

    @Test
    public void whichGraph2() {
        Graph graph = new Graph(new ArrayList<>());
        Router router = new BusRouter(graph);
        setupGraph2(router);
        Command command = new Command(router);

        Node node1 = router.getGraph().getGraph().get(0);
        Node node2 = router.getGraph().getGraph().get(1);
        Node node3 = router.getGraph().getGraph().get(2);
        Node node4 = router.getGraph().getGraph().get(3);
        Node node5 = router.getGraph().getGraph().get(4);
        Node node6 = router.getGraph().getGraph().get(5);

        assertEquals(command.which(node2, node1), "Train");
        assertEquals(command.which(node2, node5), "Bus");
        assertEquals(command.which(node6, node3), "Train");
        assertEquals(command.which(node4, node6), "Train");
    }
}
