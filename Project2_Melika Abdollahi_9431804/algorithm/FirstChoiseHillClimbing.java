package algorithm;

import problem.Problem;
import utils.Node;

public class FirstChoiseHillClimbing {

	Node current;
	Problem problem;
	int visited=0, expanded=0;

	public FirstChoiseHillClimbing(Problem problem) {
		this.problem = problem;
	}

	public Node search() {
		current = problem.initialState();

		visited++;
		System.out.println("search stated...");
		while (true) {
			Object state = current.getState();

			expanded++;
//			System.out.println(state + "  " + problem.huristic(state));
			double myEvaluate = problem.huristic(state);
			Node betterNeighbour = null;
			boolean isLocalMax = true;
			for (int act : problem.actions(state)) {
				visited++;
				Node neighbour = problem.result(state, act);
				double x= problem.huristic(neighbour.getState());
				if (myEvaluate> x) {
					betterNeighbour = neighbour;
					isLocalMax = false;
					break;
				}

			}
			if (isLocalMax) { // current node is a local maximum
				break;
			} else {
				current = betterNeighbour;
			}
		}
		
		System.out.println(visited + " node visited and "+ expanded +" node expanded!");
		return current;
	}

}
