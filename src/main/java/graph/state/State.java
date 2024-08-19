package graph.state;

import graph.route.Router;
import graph.data.Edge;
import graph.data.Node;

public interface State {
    public void execute(Router router);
    default void setAll(Router router, boolean value) {
        for (Node node: router.getGraph().getGraph()) {
            for (Edge edge: node.getEdges()) {
                edge.setDirected(value);
            }
        }
    }
}
