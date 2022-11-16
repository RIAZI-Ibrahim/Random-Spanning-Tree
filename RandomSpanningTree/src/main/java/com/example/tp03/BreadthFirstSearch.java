package com.example.tp03;

import java.util.*;

public class BreadthFirstSearch {

    Graph graph;
    LinkedList<Arc> arcSortants;
    ArrayList<Arc> arbreCouvrant;
    List<Integer> visité;
    private BreadthFirstSearch(Graph graph) {
        this.graph = graph;
        this.arcSortants = new LinkedList<>();
        this.arbreCouvrant = new ArrayList<>();
        this.visité = new ArrayList<>();
    }

    public static ArrayList<Arc> generateTree(Graph graph, int root) {
        BreadthFirstSearch monParcour = new BreadthFirstSearch(graph);
        Arc arcActuel;
        monParcour.visité.add(root);
        for (Arc arc : graph.outNeighbours(root)) monParcour.arcSortants.add(arc);
        while (!monParcour.arcSortants.isEmpty()) {
            arcActuel = monParcour.arcSortants.remove();
            if (!monParcour.visité.contains(arcActuel.getDest())) {
                monParcour.visité.add(arcActuel.getDest());
                monParcour.arbreCouvrant.add(arcActuel);
                for (Arc arc : graph.outNeighbours(arcActuel.getDest())) monParcour.arcSortants.add(arc);
            }
        }
        return monParcour.arbreCouvrant;
    }

}
