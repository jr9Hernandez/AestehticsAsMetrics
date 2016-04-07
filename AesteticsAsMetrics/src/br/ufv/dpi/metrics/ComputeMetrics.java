package br.ufv.dpi.metrics;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import br.ufv.dpi.labeledlevels.LabeledLevel;
import dk.itu.mario.level.Level;

public class ComputeMetrics {

	private ArrayList<LabeledLevel> labeledLevels = new ArrayList<LabeledLevel>();
	private ArrayList<Double> leniencyValues = new ArrayList<Double>();
	private ArrayList<Double> linearityValues = new ArrayList<Double>();
	private ArrayList<Double> densityValues = new ArrayList<Double>();
//	private ArrayList<Double> distanceValues = new ArrayList<Double>();
	private ArrayList<Double> symmetryValues = new ArrayList<Double>();
	private ArrayList<Double> numobjectsValues = new ArrayList<Double>();
	private ArrayList<Double> balanceValues = new ArrayList<Double>();
	private ArrayList<Double> equilibriumValues = new ArrayList<Double>();
	private ArrayList<Double> reacheabilityValues = new ArrayList<Double>();
	private ArrayList<Double> rulethirdsValues = new ArrayList<Double>();
	private ArrayList<Double> symmetryVerticalValues = new ArrayList<Double>();
	private ArrayList<Double> balanceVerticalValues = new ArrayList<Double>();
	private ArrayList<Double> balanceHorizontalValues = new ArrayList<Double>();
	private ArrayList<Double> unityValues = new ArrayList<Double>();

	public void computeSingle(String tela) {

		File folder = new File(tela);
		//String files[] = folder.list();

		
		//for (int i = 0; i < files.length; i++) {
			LabeledLevel labeledLevel = new LabeledLevel(tela);
			labeledLevels.add(labeledLevel);
		//}
		
		//for (LabeledLevel labeledLevel : labeledLevels) {
			Level level = labeledLevel.getLevel();

			Metrics leniency = new Leniency(level.getWidth(), level.getHeight(), level);
			Metrics linearity = new Linearity(level.getWidth(), level.getHeight(), level);
			Metrics density = new Density(level.getWidth(), level.getHeight(), level);
			Metrics symmetry = new Symmetry(level.getWidth(), level.getHeight(), level);
			Metrics numobjects = new Numobjects(level.getWidth(), level.getHeight(), level);
			Metrics balance = new Balance(level.getWidth(), level.getHeight(), level);
			Metrics equilibrium = new Equilibrium(level.getWidth(), level.getHeight(), level);
			Metrics reacheability = new Reacheability(level.getWidth(), level.getHeight(), level);
			Metrics rulethirds = new ThirdsRule(level.getWidth(), level.getHeight(), level);
			Metrics symmetryvertical = new SymmetryVertical(level.getWidth(), level.getHeight(), level);
			Metrics balancevertical = new BalanceVertical(level.getWidth(), level.getHeight(), level);
			Metrics balancehorizontal = new BalanceHorizontal(level.getWidth(), level.getHeight(), level);
			Metrics unity = new Unity(level.getWidth(), level.getHeight(), level);

			
			leniencyValues.add(leniency.compute());
			linearityValues.add(linearity.compute());
			densityValues.add(density.compute());
			symmetryValues.add(symmetry.compute());
			numobjectsValues.add(numobjects.compute());
			balanceValues.add(balance.compute());
			equilibriumValues.add(equilibrium.compute());
			reacheabilityValues.add(reacheability.compute());
			rulethirdsValues.add(rulethirds.compute());
			symmetryVerticalValues.add(symmetryvertical.compute());
			balanceVerticalValues.add(balancevertical.compute());
			balanceHorizontalValues.add(balancehorizontal.compute());
			unityValues.add(unity.compute());
			
		//}
		
		//leniencyValues = normalize(leniencyValues);
		//linearityValues = normalize(linearityValues);
		//densityValues = normalize(densityValues);
		
/*
		for (LabeledLevel labeledLevel1 : labeledLevels) {
			Level level1 = labeledLevel1.getLevel();
			for (LabeledLevel labeledLevel2 : labeledLevels) {
				Level level2 = labeledLevel2.getLevel();
				Metrics distance = new CompressionDistance(level1.getWidth(), level1.getHeight(), level1);
				distanceValues.add(distance.compute(level2));
			}
		}
	*/			
	//	System.out.println("Total number of labeled levels: " + labeledLevels.size());
	}
	
	public void compute() {

		File folder = new File("labeledlevels/levels/");
		String files[] = folder.list();

		
		for (int i = 0; i < files.length; i++) {
			LabeledLevel labeledLevel = new LabeledLevel(files[i]);
			labeledLevels.add(labeledLevel);
		}
		
		for (LabeledLevel labeledLevel : labeledLevels) {
			Level level = labeledLevel.getLevel();

			Metrics leniency = new Leniency(level.getWidth(), level.getHeight(), level);
			Metrics linearity = new Linearity(level.getWidth(), level.getHeight(), level);
			Metrics density = new Density(level.getWidth(), level.getHeight(), level);
			Metrics symmetry = new Symmetry(level.getWidth(), level.getHeight(), level);
			Metrics numobjects = new Numobjects(level.getWidth(), level.getHeight(), level);
			Metrics balance = new Balance(level.getWidth(), level.getHeight(), level);
			Metrics equilibrium = new Equilibrium(level.getWidth(), level.getHeight(), level);
			Metrics reacheability = new Reacheability(level.getWidth(), level.getHeight(), level);
			Metrics rulethirds = new ThirdsRule(level.getWidth(), level.getHeight(), level);
			Metrics symmetryvertical = new SymmetryVertical(level.getWidth(), level.getHeight(), level);
			Metrics balancevertical = new BalanceVertical(level.getWidth(), level.getHeight(), level);
			Metrics balancehorizontal = new BalanceHorizontal(level.getWidth(), level.getHeight(), level);
			Metrics unity = new Unity(level.getWidth(), level.getHeight(), level);

			leniencyValues.add(leniency.compute());
			linearityValues.add(linearity.compute());
			densityValues.add(density.compute());
			symmetryValues.add(symmetry.compute());
			numobjectsValues.add(numobjects.compute());
			balanceValues.add(balance.compute());
			equilibriumValues.add(equilibrium.compute());
			reacheabilityValues.add(reacheability.compute());
			rulethirdsValues.add(rulethirds.compute());
			symmetryVerticalValues.add(symmetryvertical.compute());
			balanceVerticalValues.add(balancevertical.compute());
			balanceHorizontalValues.add(balancehorizontal.compute());
			unityValues.add(unity.compute());
		}
		
		leniencyValues = normalize(leniencyValues);
		linearityValues = normalize(linearityValues);
		densityValues = normalize(densityValues);
		symmetryValues=normalize(symmetryValues);
		balanceValues=normalize(balanceValues);
		equilibriumValues=normalize(equilibriumValues);
		//reacheabilityValues=normalize(reacheabilityValues);
		//numobjectsValues=normalize(numobjectsValues);
		rulethirdsValues=normalize(rulethirdsValues);
		symmetryVerticalValues=normalize(symmetryVerticalValues);
		balanceVerticalValues=normalize(balanceVerticalValues);
		balanceHorizontalValues=normalize(balanceHorizontalValues);
		unityValues=normalize(unityValues);
		
/*
		for (LabeledLevel labeledLevel1 : labeledLevels) {
			Level level1 = labeledLevel1.getLevel();
			for (LabeledLevel labeledLevel2 : labeledLevels) {
				Level level2 = labeledLevel2.getLevel();
				Metrics distance = new CompressionDistance(level1.getWidth(), level1.getHeight(), level1);
				distanceValues.add(distance.compute(level2));
			}
		}
	*/			
	//	System.out.println("Total number of labeled levels: " + labeledLevels.size());
	}
	
	public void printMetrics() {
		System.out.println("Printing Metrics: Leniency, Linearity, Density and symmetry");
		/*for(int i = 0; i < leniencyValues.size(); i++) {
			System.out.println(leniencyValues.get(i) + "," + linearityValues.get(i) + "," 
		+ densityValues.get(i) + "," + symmetryValues.get(i));
		}*/
		int i=0;
		for(LabeledLevel level : labeledLevels) {
			
			System.out.println(level.getVisualAesthetics()+","+equilibriumValues.get(i));
			i++;			
			
		}
		/*for(LabeledLevel level : labeledLevels) {
			double temp=symmetryValues.get(i)*1.428+3.326;
			System.out.println(level.getVisualAesthetics()+","+temp);
			i++;			
			
		}*/
	}
	
	public void printLabels() {
		System.out.println("Printing Labels: Fun, Visual Aesthetics, and Difficulty");
		/*for (LabeledLevel level : labeledLevels) {
			System.out.println(level.getFun() + "," + level.getVisualAesthetics() + "," + level.getDifficulty());
		}*/
		for (LabeledLevel level : labeledLevels) {
			if(level.getVisualAesthetics()<8)
			{
				System.out.println(level.getVisualAesthetics());
			}
		}
	}
	
	private ArrayList<Double> normalize(ArrayList<Double> list) {
		if(list.size() == 0) return list;
		
		double min = list.get(0);
		double max = list.get(0);
		for (Double value : list) {
			if(value > max) max = value;
			if(value < min) min = value;
		}
		ArrayList<Double> normalizedList = new ArrayList<Double>();
		for (Double value : list) {
			normalizedList.add((value - min)/(max - min));
		}
		
		return normalizedList;
	}

	public static void copyFilesWithLabels() {

		File folder = new File("labeledlevels/levels/");
		String files[] = folder.list();

		for (int i = 0; i < files.length; i++) {
			File filelabel = new File("labeledlevels/labels/" + files[i]);

			if(filelabel.exists()) {
				File source = new File("labeledlevels/levels/" + files[i]);
				File destination = new File("labeledlevels/levels2/" + files[i]);
				try {
					Files.copy(source.toPath(), destination.toPath());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}		
	}
}
