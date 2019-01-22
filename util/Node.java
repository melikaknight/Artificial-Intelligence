package util;

public class Node {
	
	private Node parent;
	private int pathCost;
	private long state;
	
	public Node(long state) {
		// TODO Auto-generated constructor stub
		this.setState(state);
	}

	public long getState() {
		return state;
	}

	public void setState(long state) {
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
	public int getCost(){
		return pathCost - parent.getPathCost();
	}

	public Node getParent() {
		return parent;
	}

}
