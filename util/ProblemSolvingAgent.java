package util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

import algorithm.AStar;
import algorithm.Bidirectional;
import algorithm.BreadthFirstSearch;
import algorithm.DepthFirstSearch;
import algorithm.DepthLimitedSearch;
import algorithm.IterativeDepeningDFS;
import algorithm.UniformedCost;
import problem.NumberTable;
import problem.Problem;
import problem.RobotPathFinding;
import problem.Rubik;

public class ProblemSolvingAgent {

	public ProblemSolvingAgent(Problem problem) {
		// TODO Auto-generated constructor stub
		Bidirectional algorithm = new Bidirectional(problem);
		ArrayList<Node> ans = algorithm.graphSearch() ;
		ArrayList<Node> ans2 =new ArrayList<>();

		for (int i=ans.size()-1;i>=0;i--)
		{ans2.add(ans.get(i));}
		ArrayList<Character> list = ((NumberTable)problem).showPath(ans2);
		for(Character c : list)
			System.out.print(c);
		System.out.println();
		System.out.println("path cost is "+ans.get(0).getPathCost());
		
		if(ans == null){
			System.out.println("no answer yet!");
			return;
		}
		for(Node nn : ans){
//			System.out.println(nn.getState());
		}
	}
	
	public static ArrayList<Node> solution(Node goal){
		ArrayList<Node> path = new ArrayList<>();
		Node node = goal;
		while(node != null){
			path.add(node);
			node = node.getParent();
		}
		return path;
	}
	
	public static void main(String[] args) {
		
		Problem pr = new NumberTable();
		new ProblemSolvingAgent(pr);
	}
}
