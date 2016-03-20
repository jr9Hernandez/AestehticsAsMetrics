package br.ufv.dpi.metrics;

import dk.itu.mario.level.Level;

public class Density extends Metrics {

	public Density(int width, int height, Level level) 
	{
		super(width, height, level);
	}

	@Override
	public double compute() {
		return countOtherElements(level.getMap());
	}

	@Override
	public double compute(Level toCompare) {
		return compute();
	}

	public double countOtherElements(byte[][] array) {
		double sumHills = 0;
		for (int i = 0; i < width; i++) {
			int counterHills = 0;
			for (int j = 0; j < array[i].length; j++) {

				if (array[i][j] == (byte) (2+8*16)) {// RIGHT_UP_GRASS_EDGE
					counterHills=counterHills+1;				
				}
				else if (array[i][j] == (byte) (0+8*16)) {// LEFT_UP_GRASS_EDGE
					counterHills=counterHills+1;				
				}
				else if (array[i][j] == (byte) (5 + 8 * 16)) {// HILL_TOP
					counterHills=counterHills+1;
				}
				else if (array[i][j] == (byte) (4 + 8 * 16)) {//HILL_TOP_LEFT
					counterHills=counterHills+1;				
				}
				else if (array[i][j] == (byte) (6 + 8 * 16)) {// HILL_TOP_RIGHT
					counterHills=counterHills+1;				
				}
				else if (array[i][j] == (byte) (4 + 11 * 16)) {// HILL_TOP_LEFT_IN
					counterHills=counterHills+1;				
				}
				else if (array[i][j] == (byte) (6 + 11 * 16)) {// HILL_TOP_RIGHT_IN
					counterHills=counterHills+1;
				}
				else if (array[i][j] == (byte) (1 + 8 * 16)) {// HILL_TOP_W
					counterHills=counterHills+1;
				}
			}
			//System.out.println("counterHills "+counterHills);
			sumHills = sumHills + counterHills;
		}
		double avgHills = sumHills / width;
		//System.out.println("sum" + sumHills);
		//System.out.println("width" + width);
		//System.out.println("avg" + avgHills);
		
		return avgHills;
	}
}
