package utils;

import algorithm.FirstChoiseHillClimbing;
import algorithm.RandomHillClimbing;
import algorithm.RandomRestartHillClimbing;
import algorithm.SimpleHillClimbing;
import algorithm.SimulatedAnnealing;
import problem.CharactersTable;
import problem.GraphColoring;
import problem.Problem;

public class NonClassicalAgent {
	
	public NonClassicalAgent() {
		Problem problem = new GraphColoring();
		Node solution = (new RandomRestartHillClimbing(problem)).search(10);
		System.out.println("the found maximum is : ");
		problem.showResult(solution.getState());
		System.out.println("and its worth is "+ problem.huristic(solution.getState()));
	}
	
	public static void main(String[] args) {
		new NonClassicalAgent();
	}

}
