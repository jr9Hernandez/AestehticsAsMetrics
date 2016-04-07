package br.ufv.dpi.metrics;

import graphBuilder.BlockNode;

import java.util.ArrayList;
import java.util.Iterator;

import beauty.Elements;
import beauty.ElementsToPlace;
import dk.itu.mario.engine.sprites.SpriteTemplate;
import dk.itu.mario.level.Level;

public class Equilibrium extends Metrics  {
	

	private ArrayList<BlockNode>  elementsSelected;
	private double xCenterMassGeneral;
	private double yCenterMassGeneral;
	
	public Equilibrium(int width, int height, Level level) 
	{
		super(width, height, level);
	}
	
	@Override
	public double compute() {
		
		//calculation of scanning of level
		ScanLabeledLevel objScanLevel=new ScanLabeledLevel(level,width, height );
		elementsSelected=objScanLevel.DeterminePositions();
		
		//calculation of center of mass
		CenterOfMass objCenterOfMass=new CenterOfMass();
		objCenterOfMass.CalculatecenterOfMass(elementsSelected);
		xCenterMassGeneral=objCenterOfMass.getX();
		yCenterMassGeneral=objCenterOfMass.getY();
		
		//calculation of equilibrium
		
		System.out.println("xCenterMassGeneralEqu "+xCenterMassGeneral);
		System.out.println("yCenterMassGeneralEqu "+yCenterMassGeneral);
		double xEquilibrium=Math.abs(xCenterMassGeneral-(level.getxExit()/2));
		double yEquilibrium=Math.abs(yCenterMassGeneral-6);
		
		double equilibriumValue=xEquilibrium+yEquilibrium;
		return equilibriumValue;
	}
	

	@Override
	public double compute(Level toCompare) {
		return compute();
	}
		
}
