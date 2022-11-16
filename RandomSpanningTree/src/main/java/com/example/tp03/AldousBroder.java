package com.example.tp03;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class AldousBroder {
    public static ArrayList<Edge> generateTree(Graph graph, int sommetActuel) {
        ArrayList<Edge> arbreCouvrant = new ArrayList<>();
        List<Arc> arcVoisins = new ArrayList<>();
        Arc arcActuel;
        List<Integer> sommetsMareque = new ArrayList<>();
        sommetsMareque.add(sommetActuel);

        while(arbreCouvrant.size() < graph.order-1) {
            arcVoisins = graph.outNeighbours(sommetActuel);
            arcActuel = arcVoisins.get(ThreadLocalRandom.current().nextInt(0, arcVoisins.size()));

            if(!sommetsMareque.contains(arcActuel.getDest())) {
                arbreCouvrant.add(arcActuel.support);
                sommetsMareque.add(arcActuel.getDest());
            }
            sommetActuel = arcActuel.getDest();
        }
        sommetsMareque.add(sommetActuel);

        return arbreCouvrant;
    }
}
