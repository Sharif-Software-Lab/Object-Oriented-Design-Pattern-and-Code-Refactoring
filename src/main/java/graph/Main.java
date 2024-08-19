package graph;

import graph.data.Edge;
import graph.data.Graph;
import graph.data.Node;
import graph.route.BusRouter;
import graph.route.TrainRouter;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final String help = "List of commands:\ntoggle (to toggle direction of edges)\nbus (change travel mode to bus)\ntrain (change travel mode to train)\ndistance node_1 node_2\nwhich node_1 node_2\nhelp";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Graph userGraph = generateGraph();
        Command command = new Command(new TrainRouter(userGraph));
        System.out.println(help);
        String input;
        String []arguments;
        int index1, index2;
        ArrayList<Node> nodes;
        while (!(input = scanner.nextLine()).equals("exit")) {
            arguments = input.split("\\s+");
            switch (arguments[0]) {
                case "toggle":
                    command.toggleDirectional();
                    break;
                case "bus":
                    command.setRouter(new BusRouter(userGraph));
                    break;
                case "train":
                    command.setRouter(new TrainRouter(userGraph));
                    break;
                case "distance":
                    index1 = Integer.parseInt(arguments[1]);
                    index2 = Integer.parseInt(arguments[2]);
                    nodes = userGraph.getGraph();
                    System.out.println(command.distance(nodes.get(index1), nodes.get(index2)));
                    break;
                case "which":
                    index1 = Integer.parseInt(arguments[1]);
                    index2 = Integer.parseInt(arguments[2]);
                    nodes = userGraph.getGraph();
                    System.out.println(command.which(nodes.get(index1), nodes.get(index2)));
                    break;
                case "help":
                    System.out.println(help);

            }
        }
    }

    public static Graph generateGraph() {
        ArrayList<Node> nodes = new ArrayList<Node>();
        System.out.println("input number of nodes:");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) nodes.add(new Node());
        System.out.println("input edges in the format [source destination weight] (0 <= i < num_nodes), input exit when finished");
        String command;
        while (!(command = scanner.nextLine()).equals("exit")) {
            String[] commandArgs = command.split("\\s+");
            Edge.createEdge(nodes.get(Integer.parseInt(commandArgs[0])), nodes.get(Integer.parseInt(commandArgs[1])), false, Integer.parseInt(commandArgs[2]));
        }


        return new Graph(nodes);
    }
}
