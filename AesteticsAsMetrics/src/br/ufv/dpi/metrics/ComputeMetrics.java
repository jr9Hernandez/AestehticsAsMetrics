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

			leniencyValues.add(leniency.compute());
			linearityValues.add(linearity.compute());
			densityValues.add(density.compute());
		}
		
		leniencyValues = normalize(leniencyValues);
		linearityValues = normalize(linearityValues);
		densityValues = normalize(densityValues);
		
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
		System.out.println("Printing Metrics: Leniency, Linearity, and Density");
		for(int i = 0; i < leniencyValues.size(); i++) {
			System.out.println(leniencyValues.get(i) + "," + linearityValues.get(i) + "," 
		+ densityValues.get(i));
		}
	}
	
	public void printLabels() {
		System.out.println("Printing Labels: Fun, Visual Aesthetics, and Difficulty");
		for (LabeledLevel level : labeledLevels) {
			System.out.println(level.getFun() + "," + level.getVisualAesthetics() + "," + level.getDifficulty());
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
