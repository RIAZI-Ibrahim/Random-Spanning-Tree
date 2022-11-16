package com.example.tp03;

import java.util.*;

public class ParcoursAléatoire {
    Graph graph;
    Stack<Arc> arcSortants;
    ArrayList<Edge> arbreCouvrant;
    List<Integer> visité;

    private void parcoursAléatoire(Arc nextArc) {
        if(!visité.contains(nextArc.getDest())) {
            visité.add(nextArc.getDest());
            arbreCouvrant.add(nextArc.support);
            List<Arc> voisins = new ArrayList<>(graph.outNeighbours(nextArc.getDest()));
            Collections.shuffle(voisins);
            for (int i = 0; i < voisins.size(); i++) arcSortants.push(voisins.get(i));
        }
    }

    private ParcoursAléatoire(Graph graph) {
        this.graph = graph;
        this.arcSortants = new Stack<>();
        this.arbreCouvrant = new ArrayList<>();
        this.visité = new ArrayList<>();
    }

    public static ArrayList<Edge> generateTree(Graph graph, int sommet) {
        ParcoursAléatoire monParcour = new ParcoursAléatoire(graph);
        Arc arcActuel;
        monParcour.visité.add(sommet);
        List<Arc> voisins = new ArrayList<>(graph.outNeighbours(sommet));
        Collections.shuffle(voisins);
        for(int i = 0; i < voisins.size(); i++) monParcour.arcSortants.push(voisins.get(i));
        while(!monParcour.arcSortants.isEmpty()) {
            arcActuel = monParcour.arcSortants.pop();
            monParcour.parcoursAléatoire(arcActuel);
        }
        return monParcour.arbreCouvrant;
    }
}
