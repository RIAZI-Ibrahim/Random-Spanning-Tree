package com.example.tp03;

public class Complete {

	Graph graph;
	
	public Complete(int order) {
		this.graph = new Graph(order);
		for(int i = 0; i < order; i++)
			for (int j = i+1; j < order; j++)
				graph.addEdge(new Edge(i,j,0));
		
	}
	
	
	
	
}
