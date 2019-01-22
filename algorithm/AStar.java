package algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

import problem.Problem;
import util.Node;
import util.ProblemSolvingAgent;

public class AStar {

	int dideshode1=0, dideshode2=0;
	int bastdadeshode1=0, bastdadeshode2=0;
	int max1=0, max2=0;
	
	class Compare implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			// TODO Auto-generated method stub
			return (int) ((o2.getPathCost()+problem.huristic(o2.getState())) - (o1.getPathCost()+problem.huristic(o1.getState())));
		}

	}

	public ArrayList<Node> graphSearch() {
//		int max=0;
		ArrayList<Node> path = new ArrayList<Node>();
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new Compare());
		ArrayList<Long> explored = new ArrayList<Long>();
		frontier.add(problem.initialState());
		dideshode1++;
		while (frontier.size() > 0) {
			if(frontier.size()+explored.size()>max1)
				max1=frontier.size()+explored.size();
			Node current = frontier.remove();
			bastdadeshode1++;
			long s = current.getState();			
			for (int act : problem.actions(s)) {
				Node child = problem.result(s, act);
				child.setParent(current);
				if (problem.goalTest(s)){
//					System.err.println(max);
					System.out.println("number of visited node is "+dideshode1);
					System.out.println("number of explored node is "+bastdadeshode1);
					System.out.println("maximum node is "+max1);
					return ProblemSolvingAgent.solution(current);
				}
				child.setCost(problem.stepCost(s, act, child.getState()));
				if(explored.contains(child.getState()))
					continue;
				boolean exist = false;
				for (Node node : frontier)
					if (node.getState() == child.getState()) {
						if(node.getPathCost() > child.getPathCost()){
							node.setPathCost(child.getPathCost());
							node.setParent(current);
						}
						exist=true;
						break;
					}
				if(!exist){
					frontier.add(child);
					dideshode1++;
//					if(max < frontier.size())
//						max = frontier.size();
				}
			}
			explored.add(current.getState());
		}
		return path;
	}

	public ArrayList<Node> treeSearch() {
		ArrayList<Node> path = new ArrayList<Node>();
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new Compare());
		frontier.add(problem.initialState());
		dideshode2++;
		while (frontier.size() > 0) {
			if(frontier.size()>max2)
				max2=frontier.size();
			Node current = frontier.remove();
			bastdadeshode2++;
			long s = current.getState();			
			for (int act : problem.actions(s)) {
				Node child = problem.result(s, act);
				child.setParent(current);
				if (problem.goalTest(s)){
					System.out.println("number of visited node is "+dideshode2);
					System.out.println("number of explored node is "+bastdadeshode2);
					System.out.println("maximum node is "+max2);
					return ProblemSolvingAgent.solution(current);
				}
				child.setCost(problem.stepCost(s, act, child.getState()));
				boolean exist = false;
				for (Node node : frontier)
					if (node.getState() == child.getState()) {
						if(node.getPathCost() > child.getPathCost()){
							node.setPathCost(child.getPathCost());
							node.setParent(current);
						}
						exist=true;
						break;
					}
				if(!exist){
					frontier.add(child);
					dideshode2++;
				}
			}
		}
		return path;
	}

	Problem problem;
	public AStar(Problem problem) {
		// TODO Auto-generated constructor stub
		this.problem = problem;
	}
}
