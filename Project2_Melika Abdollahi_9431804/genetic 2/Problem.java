package genetic;

public interface Problem {

	double fitness(Individual i);
	Individual generateIndividual();
	Individual[] makeChild(Individual mother, Individual father);
	Object improve(Object oldId);
	
}
