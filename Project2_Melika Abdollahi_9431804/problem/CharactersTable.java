package problem;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import utils.Node;

public class CharactersTable implements Problem {

	public CharactersTable() {
		// TODO Auto-generated constructor stub
		getInput();
	}
	
	char [][] tabel;
	ArrayList<String> dict;
	int n, m;
	
	@Override
	public Node initialState() {
		// TODO Auto-generated method stub
		String ss = new String();
		for(int i=0; i<n; i++)
			for(int j=0; j<m; j++)
				ss+=tabel[i][j];
		Node init = new Node(ss);
		return init;
	}

	@Override
	public Node goalState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Integer> actions(Object state) {
		// TODO Auto-generated method stub
		ArrayList<Integer> acts = new ArrayList<>();
		for(int i=0; i<((String) state).length(); i++)
			for(int j=i+1; j<((String) state).length(); j++)
				if(((String) state).charAt(i)!=((String) state).charAt(j))
					acts.add(i*100+j);
		return acts;
	}

	@Override
	public Node result(Object state, int action) {
		// TODO Auto-generated method stub
		int c1= action%100;
		int c2 = (action/100)%100;
		char[] s = ((String) state).toCharArray();
		s[c1] = s[c2];
		s[c2] = ((String) state).charAt(c1);
		
		return new Node(new String(s));
	}

	@Override
	public boolean goalTest(int state) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int stepCost(Object currentState, int action, Object nextState) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public double huristic(Object state) {
		// TODO Auto-generated method stub
		int match=0;
//		System.out.println("state "+ state);
		for(String word : dict){
			int pos=((String) state).indexOf(word.charAt(0));
			
			while(pos!=-1){
//				System.out.println("pos "+pos);
				int p=pos;
				boolean flag = false;
				for(int i=1; i<word.length(); i++){
					if(p+1<((String) state).length() && (p%m)!=m+1 && word.charAt(i) == ((String) state).charAt(p+1)){
						p=p+1;
//						System.out.print("+1");
					}
					else if(p>0 && p%m!=0 && word.charAt(i) == ((String) state).charAt(p-1)){
						p = p-1;
//						System.out.print("-1");
					}
					else if(p-m >=0 && word.charAt(i) == ((String) state).charAt(p-m)){
						p=p-m;
//						System.out.print("-m");
					}
					else if(p+m <((String) state).length() && word.charAt(i) == ((String) state).charAt(p+m)){
						p=p+m;
//						System.out.print("+m");
					}
					else{
						flag = true;
						break;
					}
				}
				if(!flag){
					match++;
					break;
				}
				pos = ((String) state).indexOf(word.charAt(0), pos+1);
			}
//			System.out.println("  word "+word+"  ");
		}
		return dict.size()-match;
	}
	

	@Override
	public Node generateRandomNode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getInput() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		dict = new ArrayList<>();
		tabel = new char[n][m];
		for(int i=0; i<n; i++)
			for(int j=0; j<m; j++)
				tabel[i][j] = sc.next().charAt(0);
		
		int k=sc.nextInt();
		for(int i=0; i<k; i++){
			dict.add(sc.next());
		}
	}

	@Override
	public void showResult(Object state) {
		// TODO Auto-generated method stub
		for(int i=0; i<n; i++){
			for(int j=0; j<m; j++)
				System.out.print(tabel[i][j]);
			System.out.println();
		}
	}

}
