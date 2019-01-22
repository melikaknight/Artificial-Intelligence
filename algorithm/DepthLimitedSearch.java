package algorithm;

import java.util.ArrayList;
import java.util.Stack;

import problem.Problem;
import util.Node;
import util.ProblemSolvingAgent;

public class DepthLimitedSearch {

	int dideshode1=0, dideshode2=0;
	int bastdadeshode1=0, bastdadeshode2=0;
	int max1=0, max2=0;
	
	ArrayList<Long> explored;
	
	private ArrayList<Node> solution(Node goal){
		ArrayList<Node> path = new ArrayList<>();
		Node node = goal;
		while(node!=null){
			path.add(node);
			node = node.getParent();
		}
		return path;
	}
	
	public ArrayList<Node> graphSearch(int limit) {
		ArrayList<Node> path = new ArrayList<>();
		Stack<Node> frontier = new Stack<Node>();
		ArrayList<Long> explored = new ArrayList<Long>();
		int depth = 0;
		
		frontier.add(problem.initialState());
		dideshode1++;
		while(frontier.size()>0){
			if(frontier.size()+explored.size()>max1)
				max1 = frontier.size()+explored.size();
			Node current = frontier.pop();
			if (problem.goalTest(current.getState())){
				System.out.println("number of visited node is "+dideshode1);
				System.out.println("number of explored node is "+bastdadeshode1);
				System.out.println("maximum node is "+max1);
				return solution(current);
			}
			else{
				if(depth < limit){
					explored.add(current.getState());
					bastdadeshode1++;
					depth ++ ;
					long s = current.getState();				
					for(int act : problem.actions(s)){
						Node child = problem.result(s, act);
						child.setParent(current);
						if(explored.contains(child.getState()))
							continue;
						frontier.add(child);
						dideshode1++;
					}
				}
			}
			
		}

		return path;
	}

	public ArrayList<Node> treeSearch(int limit) {
		ArrayList<Node> path = new ArrayList<>();
		Stack<Node> frontier = new Stack<Node>();
		int depth = 0;
		
		frontier.add(problem.initialState());
		dideshode2++;
		while(frontier.size()>0){
			if(frontier.size()>max2)
				max2=frontier.size();
			Node current = frontier.pop();
			if (problem.goalTest(current.getState())){
				System.out.println("number of visited node is "+dideshode2);
				System.out.println("number of explored node is "+bastdadeshode2);
				System.out.println("maximum node is "+max2);
				return solution(current);
			}
			else{
				if(depth < limit){
					explored.add(current.getState());
					bastdadeshode2++;
					depth ++ ;
					long s = current.getState();				
					for(int act : problem.actions(s)){
						Node child = problem.result(s, act);
						child.setParent(current);
						frontier.add(child);
						dideshode2++;
					}
				}
			}
		}

		return path;

	}

	Problem problem;
	public DepthLimitedSearch(Problem problem) {
		// TODO Auto-generated constructor stub
		this.problem = problem;
	}
}
