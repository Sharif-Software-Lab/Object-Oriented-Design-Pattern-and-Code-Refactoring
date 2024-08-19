package graph.route;

import graph.data.Graph;
import graph.data.Node;
import graph.state.BidirectionalState;
import graph.state.State;
import graph.state.UnidirectionalState;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

abstract public class Router {
    @Setter
    private State state;
    @Getter
    private final Graph graph;
    @Setter
    @Getter
    private ArrayList<Node> blacklist;
    public Router(Graph graph) {
        this.state = new UnidirectionalState();
        this.graph = graph;
        this.blacklist = new ArrayList<>();
    }

    public Router(Graph graph, String state){
        this.state = state.equalsIgnoreCase("bidirectional") ? new BidirectionalState() : new UnidirectionalState();
        this.graph = graph;
        this.blacklist = new ArrayList<>();
    }

    public void execute() {
        state.execute(this);
    }

    public void resetVisits() {
        for (Node v : this.getGraph().getGraph())
            v.setVisited(false);
    }

    public int distance(Node source, Node target) {
        this.route(source);
        if (target.isVisited()) return target.getDistance();
        return -1;
    }

    public void visitBlacklist() {
        for (Node node: blacklist) node.setVisited(true);
    }

    abstract public void route(Node node);
}
