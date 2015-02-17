package OverlapGraph;

public class Node {

	private String read;
	private boolean isVisited;
	private int indegree, outdegree;
	
	public Node(String read) {
		this.read = read;
		this.isVisited = false;
		this.indegree = 0;
		this.outdegree = 0;
	}
	
	public String getRead() { return read; }
	public void setRead(String read) { this.read = read; }
	
	public boolean isVisited() { return isVisited; }
	public void setVisited(boolean visited) { this.isVisited = visited; }
	
	//TODO remove indegree and outdegree if unused
	public int getIndegree() { return this.indegree; }
	public void incrementIndegree() { this.indegree++; }
	
	public int getOutdegree() { return this.outdegree; }
	public void incrementOutdegree() { this.outdegree++; }
	
	public void printNodeInfo() {
		System.out.println("(" + indegree+", " + outdegree + ", " + isVisited + ")");
	}
}