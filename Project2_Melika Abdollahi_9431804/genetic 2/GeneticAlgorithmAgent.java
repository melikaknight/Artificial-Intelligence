package genetic;

public class GeneticAlgorithmAgent {
	
	public GeneticAlgorithmAgent() {
		new Genetic(new KeyboardGeneration(),1000, 100);
	}
	
	public static void main(String[] args) {
		new GeneticAlgorithmAgent();
	}

}
