package algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import problem.Problem;
import util.Node;
import util.ProblemSolvingAgent;

public class BreadthFirstSearch {
	Problem problem;
	
	int dideshode1 = 0, dideshode2=0;
	int bastdadeshode1 = 0, bastdadeshode2 = 0;
	int max1=0, max2=0;
	
	public ArrayList<Node> graphSearch(){
		ArrayList<Node> path = new ArrayList<>();
		Queue<Node> frontier = new LinkedList<Node>();
		ArrayList<Long> explored = new ArrayList<Long>();
		frontier.add(problem.initialState());
		dideshode1++;
		while(frontier.size()>0){
			if(frontier.size()+explored.size()>max1)
				max1 = frontier.size()+explored.size();
			Node current = frontier.remove();
			explored.add(current.getState());
			bastdadeshode1++;
			long s = current.getState();
			for(int act : problem.actions(s)){
				Node child = problem.result(s, act);
				child.setParent(current);
				if(explored.contains(child.getState()) || frontier.contains(child))
					continue;
				if(problem.goalTest(child.getState())){
					System.out.println("number of visited node is "+dideshode1);
					System.out.println("number of explored node is "+bastdadeshode1);
					System.out.println("maximum node is "+max1);
					return ProblemSolvingAgent.solution(child);
				}
				frontier.add(child);
				dideshode1++;
			}
		}
		return path;
	}
	
	public ArrayList<Node> treeSearch(){
//		int max=0;
		ArrayList<Node> path = new ArrayList<>();
		Queue<Node> frontier = new LinkedList<Node>();
		frontier.add(problem.initialState());
		dideshode2++;
		while(frontier.size()>0){
			if(frontier.size()>max2)
				max2=frontier.size();
			Node current = frontier.remove();
			bastdadeshode2++;
			long s = current.getState();
			for(int act : problem.actions(s)){
				Node child = problem.result(s, act);
				child.setParent(current);
				if(problem.goalTest(child.getState())){
//					System.err.println(max);
					System.out.println("number of visited node is "+dideshode2);
					System.out.println("number of explored node is "+bastdadeshode2);
					System.out.println("maximum node is "+max2);
					return ProblemSolvingAgent.solution(child);
				}
				frontier.add(child);
				dideshode2++;
//				if(max < frontier.size())
//					max = frontier.size();
			}
		}
		return path;
	}

	public BreadthFirstSearch(Problem problem) {
		// TODO Auto-generated constructor stub
		this.problem = problem;
		
	}
}
