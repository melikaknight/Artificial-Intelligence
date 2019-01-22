package algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import problem.Problem;
import util.Node;
import util.ProblemSolvingAgent;

public class DepthFirstSearch {
	int bastdadeshode;
	int dideshode;
	int bastdadeshode2;
	int dideshode2;
	int max;
	int max2;
	Problem problem;
	public ArrayList<Node> graphSearch(){
//		int max=0;
		ArrayList<Node> path = new ArrayList<>();
		Stack<Node> frontier = new Stack<Node>();
//		ArrayList<Long> explored = new ArrayList<Long>();
		HashSet<Long> explored = new HashSet<>();
		frontier.add(problem.initialState());
		dideshode++;
		while(frontier.size()>0){
			if (frontier.size()+ explored.size()>max){
				max=frontier.size()+ explored.size();
			}
			Node current = frontier.pop();
			bastdadeshode++;
			explored.add(current.getState());
			long s = current.getState();
			for(int act : problem.actions(s)){
				Node child = problem.result(s, act);
				child.setParent(current);
				dideshode++;
				if(explored.contains(child.getState()))
					continue;
				if(problem.goalTest(child.getState())){
					System.out.println("number of visited node is "+dideshode);
					System.out.println("number of explored node is "+bastdadeshode);
					System.out.println("maximum node is "+max);
					return ProblemSolvingAgent.solution(child);

				}
				frontier.add(child);
//				if(max < frontier.size())
//					max = frontier.size();
			}
		}

		return path;

	}
	
	public ArrayList<Node> treeSearch(){
		ArrayList<Node> path = new ArrayList<>();
		Stack<Node> frontier = new Stack<Node>();
		frontier.add(problem.initialState());
		dideshode++;
		while(frontier.size()>0){
			if (frontier.size()>max2){
				max2=frontier.size();
			}
			Node current = frontier.pop();
			bastdadeshode2++;
			long s = current.getState();
			for(int act : problem.actions(s)){
				Node child = problem.result(s, act);
				child.setParent(current);
				dideshode2++;
				if(problem.goalTest(child.getState())){
					System.out.println("number of visited node is "+dideshode2);
					System.out.println("number of explored node is "+bastdadeshode2);
					System.out.println("maximum node is "+max2);
					return ProblemSolvingAgent.solution(child);
				}
				frontier.add(child);
			}
		}
		return path;
	}
	public DepthFirstSearch(Problem problem) {
		// TODO Auto-generated constructor stub
		this.problem = problem;
	}
}
