package com.example.tp03;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class Graph implements Iterable<Edge>{

	int order;
	int edgeCardinality;
	
	ArrayList<LinkedList<Edge>> adjacency;
	ArrayList<LinkedList<Arc>> inAdjacency;
	ArrayList<LinkedList<Arc>> outAdjacency;
	
	public boolean isVertex(int index) {
		return adjacency.size() > index && adjacency.get(index) != null;
	}
	
	public <T> ArrayList<LinkedList<T>> makeList(int size) {
		ArrayList<LinkedList<T>> res = new ArrayList<>(size);
		for(int i = 0; i <= size; i++) {
			res.add(null);			
		}
		return res;
	}
	
	public Graph(int upperBound) {
		this.order = 0;
		this.adjacency = makeList(upperBound); 
		this.inAdjacency = makeList(upperBound); 
		this.outAdjacency = makeList(upperBound); 
	}
	
	public void addVertex(int indexVertex) {
		if (!isVertex(indexVertex)) {
			adjacency.set(indexVertex, new LinkedList<Edge>());
			inAdjacency.set(indexVertex, new LinkedList<Arc>());
			outAdjacency.set(indexVertex, new LinkedList<Arc>());
			order++;
		}
	}
	
	public void ensureVertex(int indexVertex) {
		if (!isVertex(indexVertex)) addVertex(indexVertex); 
	}	
	
	public void addArc(Arc arc) {
		outAdjacency.get(arc.getSource()).add(arc);
		inAdjacency.get(arc.getDest()).add(arc);
	}
	
	public void addEdge(Edge e) {
		ensureVertex(e.dest);
		ensureVertex(e.source);
		adjacency.get(e.dest).add(e);
		adjacency.get(e.source).add(e);
		
		addArc(new Arc(e,false)); 
		addArc(new Arc(e,true));
	}
	
	public List<Edge> neighbours(int vertex) {
		return this.adjacency.get(vertex);
	}
	
	public List<Arc> inNeighbours(int vertex) {
		return this.inAdjacency.get(vertex);
	}
	
	public List<Arc> outNeighbours(int vertex) {
		return this.outAdjacency.get(vertex);
	}

	public int degree(int vertex) {
		return neighbours(vertex).size();
	}
	
	public int inDegree(int vertex) {
		return inNeighbours(vertex).size();
	}
	
	public int outDegree(int vertex) {
		return outNeighbours(vertex).size();
	}
	

	
	public boolean isEdge(Edge e) {
		return this.adjacency.get(e.dest).contains(e);
	}
	
	public Arc hasArc(int fromVertex, Edge e) {
		for (Arc a : this.outAdjacency.get(fromVertex)) {
			if (a.support == e) return a;
		}
		return null;
	}
	
	public void removeArc(Arc arc) {
		this.outAdjacency.get(arc.getSource()).remove(arc);
		this.inAdjacency.get(arc.getDest()).remove(arc);
	}
	
	public void removeEdge(Edge e) {
		if (this.isEdge(e)) {
			this.adjacency.get(e.dest).remove(e);
			this.adjacency.get(e.source).remove(e);
			removeArc(hasArc(e.source, e));
			removeArc(hasArc(e.dest,e));
			edgeCardinality--;
		}
	}	
	
	public void removeVertex(int vertex) {
		for (Edge e : neighbours(vertex)) {
			removeEdge(e);
		}
		adjacency.set(vertex, null);
		inAdjacency.set(vertex, null);
		outAdjacency.set(vertex, null);
		
	}
	
	
	private class EdgeIterator implements Iterator<Edge>{
		
		Iterator<LinkedList<Arc>> vertexIt;
		Iterator<Arc> arcIt;
		
		public EdgeIterator() {
			vertexIt = outAdjacency.iterator();
		}
		
		private boolean mustRenewArcIt() {
			return arcIt == null || !arcIt.hasNext();
		}
		
		public boolean hasNext() {
			LinkedList<Arc> outAdj;
			while (mustRenewArcIt() && vertexIt.hasNext()) {
				outAdj = vertexIt.next();
				if (outAdj != null) arcIt = outAdj.iterator();
			}
			return arcIt != null && arcIt.hasNext();
		}
		
		public Edge next() {
			return arcIt.next().support;
		}
		
		public void remove() {
			return;
		}

	}
	
	public Iterator<Edge> iterator() {
		return new EdgeIterator();
	}
	
}
