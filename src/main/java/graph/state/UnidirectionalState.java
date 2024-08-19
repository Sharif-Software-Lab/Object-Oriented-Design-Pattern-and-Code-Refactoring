package graph.state;

import graph.data.Edge;
import graph.data.Node;
import graph.route.Router;

public class UnidirectionalState implements State {
    @Override
    public void execute(Router router) {
        setAll(router, true);
        router.setState(new BidirectionalState());
    }
}
