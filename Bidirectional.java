package algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import problem.Problem;
import util.Node;
import util.ProblemSolvingAgent;

public class Bidirectional {

	int dideshode1=0;
	int bastdadeshode1=0;
	int max1=0;
	
	public ArrayList<Node> graphSearch() {
		ArrayList<Node> path = new ArrayList<>();
		Queue<Node> frontier1 = new LinkedList<Node>(), frontier2 = new LinkedList<Node>();
		ArrayList<Long> explored1 = new ArrayList<Long>(), explored2 = new ArrayList<Long>();
		frontier1.add(problem.initialState());
		frontier2.add(problem.goalState());
		dideshode1+=2;
		boolean flag = false;
		while(true){
			if(frontier1.size()+frontier2.size()+explored1.size()+explored2.size()>max1)
				max1=frontier1.size()+frontier2.size()+explored1.size()+explored2.size();
			Node right = frontier1.remove(), left = frontier2.remove();
			explored1.add(right.getState());
			explored2.add(left.getState());
			bastdadeshode1+=2;
			long s1 = right.getState();
			long s2 = left.getState();
			for(int act : problem.actions(s1)){
				Node child = problem.result(s1, act);
				child.setParent(right);
				child.setCost(problem.stepCost(right.getState(), act, child.getState()));
				if(explored1.contains(child.getState()))
					continue;
				frontier1.add(child);
				dideshode1++;
			}
			Object[] f1 =  frontier1.toArray();
			Object[] f2= frontier2.toArray();
			int i,j = 0,r = 0,l=0;
			for(i=0; i<f1.length; i++)
				for(j=0; j<f2.length; j++)
					if(((Node) f1[i]).getState() == ((Node) f2[j]).getState()){
						r=i;
						l=j;
						i=f1.length;
						flag=true;
						break;
					}
			if(flag){
				ArrayList<Node> tmp = new ArrayList<>();
				System.out.println("number of visited node is "+dideshode1);
				System.out.println("number of explored node is "+bastdadeshode1);
				System.out.println("maximum node is "+max1);				
				Node n1 = (Node) f1[r];
				while(n1!=null){
					path.add(n1);
//					System.out.println(n1.getState());
					n1 = n1.getParent();
				}
				Node n2 = (Node) f2[l];
				while(n2!=null){
					tmp.add(n2);
//					System.out.println(n2.getState());
					n2 = n2.getParent();
				}
				for(int h=1; h<tmp.size(); h++){
					tmp.get(h).setParent(path.get(0));
					tmp.get(h).setCost(tmp.get(h-1).getCost());
					path.add(0, tmp.get(h));
				}
				return path;
			}
//				return ProblemSolvingAgent.solution(problem.goalState());
			for(int act : problem.actions(s2)){
				Node child = problem.result(s2, act);
				child.setParent(left);
				child.setCost(problem.stepCost(left.getState(), act, child.getState()));
				if(explored2.contains(child.getState()))
					continue;
				frontier2.add(child);
				dideshode1++;
			}
			f1 =  frontier1.toArray();
			f2=frontier2.toArray();
			for(i=0; i<f1.length; i++)
				for(j=0; j<f2.length; j++)
					if(((Node) f1[i]).getState() == ((Node) f2[j]).getState()){
						r=i;
						l=j;
						i=f1.length;
						flag=true;
						break;
					}
			if(flag){
				System.out.println("number of visited node is "+dideshode1);
				System.out.println("number of explored node is "+bastdadeshode1);
				System.out.println("maximum node is "+max1);
				ArrayList<Node> tmp = new ArrayList<>();
				Node n1 = (Node) f1[r];
				while(n1!=null){
					path.add(n1);
//					System.out.println(n1.getState());
					n1 = n1.getParent();
				}
				Node n2 = (Node) f2[l];
				while(n2!=null){
					tmp.add(n2);
//					System.out.println(n2.getState());
					n2 = n2.getParent();
				}
				for(int h=1; h<tmp.size(); h++){
					tmp.get(h).setParent(path.get(0));
					tmp.get(h).setCost(tmp.get(h-1).getCost());
					path.add(0, tmp.get(h));
				}
				
				return path;
			}
//				return ProblemSolvingAgent.solution(problem.goalState());
		}
	}

	public ArrayList<Node> treeSearch() {
		ArrayList<Node> path = new ArrayList<>();
		return path;
	}

	Problem problem;

	public Bidirectional(Problem problem) {
		// TODO Auto-generated constructor stub
		this.problem = problem;
	}

}
