package algorithm;

import java.util.ArrayList;

import algorithm.DepthLimitedSearch;
import problem.Problem;
import util.Node;

public class IterativeDepeningDFS {

	DepthLimitedSearch dls;
	public ArrayList<Node> graphSearch(){
		ArrayList<Node> path = null;
		for(int depth=1; depth<Integer.MAX_VALUE; depth++){
			System.out.println(depth);
			path = dls.graphSearch(depth);
			if(path == null)
				continue;
			if(path.size() == 0)
				return null;
			else{
				System.out.println("depth = " + depth);
				break;
			}
		}
		return path;
	}
	public ArrayList<Node> treeSearch(){
		ArrayList<Node> path = null;
		for(int depth=0; depth<Integer.MAX_VALUE; depth++){
			path = dls.treeSearch(depth);
			if(path == null)
				continue;
			if(path.size() == 0)
				return null;
			else{
				System.out.println("depth = " + depth);
				break;
			}
		}
		return path;
	}
	Problem problem;
	public IterativeDepeningDFS(Problem problem) {
		// TODO Auto-generated constructor stub
		this.problem = problem;
		dls = new DepthLimitedSearch(problem);
	}

}
