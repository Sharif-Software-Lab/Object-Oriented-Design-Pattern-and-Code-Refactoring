package graph;

import graph.data.Node;
import graph.route.BusRouter;
import graph.route.Router;
import graph.route.TrainRouter;
import lombok.Setter;

public class Command {
    @Setter
    private Router router;
    public Command(Router strategy) {
        this.router = strategy;
    }

    public String which(Node source, Node target) {
        Router saveRouter = this.router;
        setRouter(new TrainRouter(this.router.getGraph()));
        int trainDist = distance(source, target);
        setRouter(new BusRouter(this.router.getGraph()));
        int busDist = distance(source, target);
        setRouter(saveRouter);
        if (busDist == trainDist && busDist == -1) return "None";
        else if (busDist == -1) return "Train";
        else if (trainDist == -1) return "Bus";
        else return busDist < trainDist ? "Bus" : "Train";
    }

    public int distance(Node source, Node target) {
        return router.distance(source, target);
    }

    public void addHatedCity(Node node) {
        this.router.getBlacklist().add(node);
    }

    public void resetHatedCity(){
        this.router.getBlacklist().clear();
    }

    public void toggleDirectional() {
        this.router.execute();
    }
}
