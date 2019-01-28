package algorithm;

import problem.Problem;
import utils.Node;

public class RandomRestartHillClimbing {

	Node current;
	Problem problem;
	int visited=0, expanded=0;

	public RandomRestartHillClimbing(Problem problem) {
		this.problem = problem;
	}

	public Node search(int cycle) {
		current = problem.initialState();
		visited++;
		Node max = null;
		double betterEvaluate = problem.huristic(current.getState());
		Node betterNeighbour = null;
		
		System.out.println("search started....");
		while (true) {
			Object state = current.getState();
			expanded++;
//			System.out.println(state + "  " + problem.huristic(state));
			boolean isLocalMax = true;
			for (int act : problem.actions(state)) {
				Node neighbour = problem.result(state, act);
				visited++;
				double x = problem.huristic(neighbour.getState());
				if (betterEvaluate > x) {
					betterEvaluate = problem.huristic(neighbour.getState());
					betterNeighbour = neighbour;
					isLocalMax = false;
				}

			}
			if (isLocalMax) { // current node is a local maximum
				for(int i : ((int [])state))
					System.out.print(i+" ");
				System.out.print(" is a local maxium");
				System.out.println();
				if(--cycle != 0){
					if(max==null || (problem.huristic(max.getState()) > problem.huristic(state)))
						max=current;
					current = problem.generateRandomNode();
					visited++;
					betterEvaluate = problem.huristic(current.getState());
//					System.out.println(current.getState());
				}
				else
					break;
			} else {
				current = betterNeighbour;
			}
		}
		
		System.out.println(visited + " node visited and "+ expanded +" node expanded!");
		return max;
	}

}
