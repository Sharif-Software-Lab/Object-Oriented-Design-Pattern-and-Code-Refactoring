package graph.state;

import graph.route.Router;

public class BidirectionalState implements State{

    @Override
    public void execute(Router router) {
        setAll(router, false);
        router.setState(new UnidirectionalState());
    }
}
