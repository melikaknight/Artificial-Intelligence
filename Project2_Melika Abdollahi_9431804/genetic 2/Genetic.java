package genetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Genetic {
	
	Problem problem;
	ArrayList<Individual> population;
	int populationSize;
	
	public Genetic(Problem problem, int populationSize, int cycle) {
		this.problem = problem;
		this.populationSize = populationSize;
		
		int k=0;
		population = new ArrayList<>();
		primaryPopulationGeneration();
		while(k++ != cycle){
			algorithm();
			System.out.println(k +"\t" + bestFitness() + "\t" + meanFitness() + "\t" + worseFitness());
		}
		Individual ind = population.get(0);
		char [] keyboard = (char[]) ind.getId();
		for(char c : keyboard)
			System.out.print(c);
		System.out.println();
	}
	
	private double worseFitness() {
		double min = Double.MAX_VALUE;
		for (Individual i : population)
			if(i.getFitness() < min)
				min = i.getFitness();
		return min;
	}

	private double meanFitness() {
		double sum = 0;
		for (Individual i : population)
			sum += i.getFitness();
		return sum / populationSize;
	}

	private double bestFitness() {
		double max = Double.MIN_VALUE;
		for (Individual i : population)
			if(i.getFitness() > max)
				max = i.getFitness();
		return max;
	}

	private void algorithm() {
		evaluate(population);
		ArrayList<Individual> parents = parentSelection(populationSize);
		ArrayList<Individual> offspring = offspringGeneration(parents);
		population = survivorSelection(parents, offspring);
	}

	private void representation(){
		
	}
	
	
	private void primaryPopulationGeneration(){
		for(int i=0; i<populationSize; i++){
			population.add(problem.generateIndividual());
		}
	}
	
	private void evaluate(ArrayList<Individual> population){
		for(Individual i : population)
			i.setFitness(problem.fitness(i));
		
	}
	
	private ArrayList<Individual> parentSelection(int parentSize){
		ArrayList<Individual> parents = new ArrayList<>();
		double sum=0;
		for(Individual i : population)
			sum += Math.abs(i.getFitness());
		
		for(int i=0; i<parentSize; i++){
			double r = new Random().nextDouble();
			r*=sum;
//			System.out.println(r +" " + sum);
			double untilNow=0;
			for(int j=0; j<population.size(); j++){
				Individual individual = population.get(j);
				untilNow+=Math.abs(individual.getFitness());
				if(r <= untilNow){
					parents.add(individual);
					break;
				}
			}
			
		}
		return parents;
	}
	
	private ArrayList<Individual> offspringGeneration(ArrayList<Individual> parents){
		ArrayList<Individual> offspring = new ArrayList<>();
		for(int i=0; i<parents.size()/2; i++){
			Individual[] children = crossOver(parents.get(2*i), parents.get(2*i+1));
			children[0].setId(mutation(children[0]));
			children[1].setId(mutation(children[1]));
			offspring.add(children[0]);
			offspring.add(children[1]);
		}
		
		return offspring;
	}
	
	private Individual[] crossOver(Individual father, Individual mother){
		//mean of two parents
		Individual[] children = problem.makeChild(mother, father);
		return children;
	}
	
	private Object mutation(Individual child){
		//guassian mutation with mean=0 and variance=0.01
		return problem.improve(child.getId());
	}
	
	private ArrayList<Individual> survivorSelection(ArrayList<Individual> parents , ArrayList<Individual> offspring){
		evaluate(offspring);
		ArrayList<Individual> newPopulation = new ArrayList<>();
		newPopulation.addAll(parents);
		newPopulation.addAll(offspring);
		Collections.sort(newPopulation, new CompareFitness());
		return new ArrayList<>(newPopulation.subList(populationSize, newPopulation.size()));
	}
	
	class CompareFitness implements Comparator<Individual> {

		@Override
		public int compare(Individual i1, Individual i2) {
			return new Double(i1.getFitness()).compareTo(new Double(i2.getFitness()));
		}
	}

}

class Individual{
	
	private Object id;
	private double fitness;
	
	public Individual(Object id) {
		this.id = id;
	}
	
	
	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public Object getId() {
		return id;
	}

	public void setId(Object object) {
		this.id=object;
	}
	
	
}