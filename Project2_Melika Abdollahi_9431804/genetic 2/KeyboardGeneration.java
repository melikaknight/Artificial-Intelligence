package genetic;

import java.util.ArrayList;
import java.util.Random;

public class KeyboardGeneration implements Problem{

	char [] ch = new char[26];
	char [] usefull={'E', 'T', 'A', 'I', 'N', 'O', 'S', 'H', 'R'};
	char [] remain={'B', 'C', 'D', 'F', 'G', 'J', 'K', 'L', 'M', 'P', 'Q', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	String [] twice = {"TH", "ER", "ON", "AN", "RE", "HE", "IN", "ED"};
	public KeyboardGeneration() {
		// TODO Auto-generated constructor stub
		for (int i=0; i<26; i++)
			ch[i] = (char) ('A'+i);
	}
	@Override
	public double fitness(Individual i) {
		// TODO Auto-generated method stub
		ArrayList<Character> tmp = new ArrayList<>();
		for (int j=0; j<26; j++)
			tmp.add(((char[])i.getId())[j]);
		int right=0, left=0;
		for(char c : usefull){
			if (tmp.indexOf(c)<13)
				right++;
			else
				left++;
		}
		int fit = 5;
		if(right < left)
			fit+=right;
		else
			fit+=left;
		boolean flag1, flag2;
		for(String t : twice){
			if(tmp.indexOf(t.charAt(0))<13)
				flag1=true;
			else
				flag1=false;
			if(tmp.indexOf(t.charAt(1))<13)
				flag2=true;
			else
				flag2=false;
			if(flag1!=flag2)
				fit++;
		}
		return fit;
	}

	@Override
	public Individual generateIndividual() {
		// TODO Auto-generated method stub
		ArrayList<Character> tmp = new ArrayList<>();
		for (int i=0; i<26; i++)
			tmp.add(ch[i]);
		ArrayList<Character> ind = new ArrayList<>();
		for (int i=0; i<26; i++)
			ind.add(tmp.remove(new Random().nextInt(tmp.size())));
//		char [] cc = ind.toArray();
		return new Individual(ind.toString().toCharArray());
	}
	@Override
	public Individual[] makeChild(Individual mother, Individual father) {
		// TODO Auto-generated method stub
		ArrayList<Character> m = new ArrayList<>();
		ArrayList<Character> f = new ArrayList<>();
		for(char c : ((char[])mother.getId()))
			m.add(c);
		for(char c : ((char[])father.getId()))
			f.add(c);
		ArrayList<Character> right1 = new ArrayList<>();
		ArrayList<Character> left1 = new ArrayList<>();
		ArrayList<Character> right2 = new ArrayList<>();
		ArrayList<Character> left2 = new ArrayList<>();
		for(int i=0; i<5; i++){
			if (m.indexOf(usefull[i]) < 13)
				right1.add(usefull[i]);
			else
				left1.add(usefull[i]);
		}
		for(int i=5; i<9; i++){
			if (f.indexOf(usefull[i]) < 13)
				right1.add(usefull[i]);
			else
				left1.add(usefull[i]);
		}
		for (char c : remain){
			if(right1.size()==13)
				left1.add(c);
			else if(left1.size()==13)
				right1.add(c);
			else{
				if(Math.random()<0.5)
					right1.add(c);
				else
					left1.add(c);
			}
		}
		right1.addAll(left1);
		for(int i=0; i<5; i++){
			if (f.indexOf(usefull[i]) < 13)
				right2.add(usefull[i]);
			else
				left2.add(usefull[i]);
		}
		for(int i=5; i<9; i++){
			if (m.indexOf(usefull[i]) < 13)
				right2.add(usefull[i]);
			else
				left2.add(usefull[i]);
		}
		for (char c : remain){
			if(right2.size()==13)
				left2.add(c);
			else if(left2.size()==13)
				right2.add(c);
			else{
				if(Math.random()<0.5)
					right2.add(c);
				else
					left2.add(c);
			}
		}
		right2.addAll(left2);
		Individual [] children = new Individual[2];
		children[0] = new Individual(right1.toString().toCharArray());
		children[1] = new Individual(right2.toString().toCharArray());
		
			
		return children;
	}
	@Override
	public Object improve(Object oldId) {
		// TODO Auto-generated method stub
		Random r =  new Random();
		int r1 = r.nextInt(13);
		int r2 = r.nextInt(13);
		char [] tmp = ((char[]) oldId).clone();
		tmp[r1]=tmp[r2];
		tmp[r2]=((char[]) oldId)[r1];
		return tmp;
	}

}
