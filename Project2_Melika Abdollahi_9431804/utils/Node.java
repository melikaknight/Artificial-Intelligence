package utils;

public class Node {
	
	private Node parent;
	private int pathCost;
	private Object state;
	
	public Node(Object state) {
		// TODO Auto-generated constructor stub
		this.setState(state);
	}

	public Object getState() {
		return state;
	}

	public void setState(Object state) {
		this.state = state;
	}

	public int getPathCost() {
		return pathCost;
	}

	public void setPathCost(int pathCost) {
		this.pathCost = pathCost;
	}
	
	public void setParent(Node parent){
		this.parent = parent;
	}
	public void setCost(int cost){
		pathCost = getParent().getPathCost() + cost;
	}

	public Node getParent() {
		return parent;
	}

}
