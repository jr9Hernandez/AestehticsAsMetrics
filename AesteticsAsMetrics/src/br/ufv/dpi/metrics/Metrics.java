package br.ufv.dpi.metrics;

import dk.itu.mario.level.Level;

public abstract class Metrics { 

	protected int width;
	protected int height;
	protected Level level;

	protected static final int levelElementsGaps         = 0;
	protected static final int levelElementsAvGaps       = 1;
	protected static final int levelElementsEnemies      = 2;
	protected static final int levelElementsCannnFlowers = 3;
	protected static final int levelElementsPowerUps     = 4;

	public Metrics(int width, int height, Level level) 
	{
		this.width = width;
		this.height = height;
		this.level = level;
		this.width = AdjustWidth(width);
	}

	abstract public double compute();
	abstract public double compute(Level toCompare);

	private int AdjustWidth(int width) 
	{
		int last=0;
		for (int i = width-1; i >= 0; i--) 
		{
			if(level.getMap()[i][height-1]!= (byte) (0))
			{
				break;
			}
			else
			{
				last++;
			}
		}
		
		return width-last;
	}
}
