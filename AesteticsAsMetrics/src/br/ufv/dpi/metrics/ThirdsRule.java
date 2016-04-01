package br.ufv.dpi.metrics;

import graphBuilder.BlockNode;

import java.util.ArrayList;
import java.util.Iterator;

import beauty.Elements;
import beauty.ElementsToPlace;
import dk.itu.mario.engine.sprites.SpriteTemplate;
import dk.itu.mario.level.Level;

public class ThirdsRule extends Metrics  {
	

	private ArrayList<BlockNode>  elementsSelected;
	private double xCenterMassGeneral;
	private double yCenterMassGeneral;
	
	public ThirdsRule(int width, int height, Level level) 
	{
		super(width, height, level);
	}
	
	@Override
	public double compute() {
		
		//calculation of scanning of level
		ScanLabeledLevel objScanLevel=new ScanLabeledLevel(level,width, height );
		elementsSelected=objScanLevel.DeterminePositions();
		
		double infLimit=((height/3)-((height/3)/2))-1;
		double supLimit=((height/3)+((height/3)/2))-1;
		double totalThirdRules=0;
				
		//testing the captured elements
		Iterator<BlockNode> it = elementsSelected.iterator();
		while(it.hasNext()){
			BlockNode elemento = it.next();
			
			int xInitial = elemento.getX();
	        int yInitial= elemento.getY();
	        int widthElement=(elemento.getElement()).getWidth();
	        int heigthElement=(elemento.getElement()).getHeigth();
	        int typeElement=(elemento.getElement()).getTypeElem();
	        
	        
	        if((yInitial-heigthElement+1>=infLimit)&& (yInitial-heigthElement+1<=supLimit))
	        {
	        	totalThirdRules=totalThirdRules+heigthElement;
	        }
	        
	        //System.out.println("type "+typeElement+" x "+xInitial+" y "+yInitial+" widthElement "+widthElement+ " heigthElement "+heigthElement);
		}
		return totalThirdRules;
	}
	
	

	@Override
	public double compute(Level toCompare) {
		return compute();
	}
		
}
