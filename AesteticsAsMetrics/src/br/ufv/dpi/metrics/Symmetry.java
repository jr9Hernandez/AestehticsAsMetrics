package br.ufv.dpi.metrics;

import graphBuilder.BlockNode;

import java.util.ArrayList;
import java.util.Iterator;

import beauty.Elements;
import beauty.ElementsToPlace;
import dk.itu.mario.engine.sprites.SpriteTemplate;
import dk.itu.mario.level.Level;

public class Symmetry extends Metrics  {
	

	private ArrayList<BlockNode>  elementsSelected;
	private double xCenterMassGeneral;
	private double yCenterMassGeneral;
	
	public Symmetry(int width, int height, Level level) 
	{
		super(width, height, level);
	}
	
	@Override
	public double compute() {
		

		ScanLabeledLevel objScanLevel=new ScanLabeledLevel(level,width, height );
		elementsSelected=objScanLevel.DeterminePositions();
		centerOfMass(elementsSelected);
		System.out.println("x "+xCenterMassGeneral+" y "+yCenterMassGeneral);
		
		//testing the captured elements
		Iterator<BlockNode> it = elementsSelected.iterator();
		while(it.hasNext()){
			BlockNode elemento = it.next();
			
			int xInitial = elemento.getX();
	        int yInitial= elemento.getY();
	        int widthElement=(elemento.getElement()).getWidth();
	        int heigthElement=(elemento.getElement()).getHeigth();
	        int typeElement=(elemento.getElement()).getTypeElem();
	        
	        System.out.println("type "+typeElement+" x "+xInitial+" y "+yInitial+" widthElement "+widthElement+ " heigthElement "+heigthElement);
		}
		return 0;
	}
	
	public void centerOfMass(ArrayList states)
	{
		boolean flagPivotFloating=false;
		double summatoryAreasXG=0;
		double summatoryAreasYG=0;
		double summatoryAreasG=0;
		double widthElementG=0;
		double heigthElementG=0;
		double areaG;
		double xG,yG;
		
		double summatoryAreasXC=0;
		double summatoryAreasYC=0;
		double summatoryAreasC=0;
		double widthElementC=0;
		double heigthElementC=0;
		double areaC;
		double xC,yC;
		
		//Center of mass of all objects
		Iterator<BlockNode> itCenterMass = states.iterator();
		while(itCenterMass.hasNext()){
			//System.out.println("ints");
			BlockNode elemento = itCenterMass.next();
			Elements element=elemento.getElement();
			//if(elemento.getType()!=objElemP.getCoins() && elemento.getType()!=objElemP.getEnemyArmoredTurtle() && elemento.getType()!=objElemP.getEnemyCannonBall() && elemento.getType()!=objElemP.getEnemyChompFlower() && elemento.getType()!=objElemP.getEnemyFlower() && elemento.getType()!=objElemP.getEnemyGoomba() && elemento.getType()!=objElemP.getEnemyGreenKoopa() && elemento.getType()!=objElemP.getEnemyJumpFlower() && elemento.getType()!=objElemP.getEnemyRedKoopa() && elemento.getType()!=objElemP.getEnemySpiky())
			//{
			int xInitial = elemento.getX();
	        int yInitial= elemento.getY();
	        widthElementG=element.getWidth();
	        heigthElementG=element.getHeigth();
	        
	        areaG=widthElementG*heigthElementG;
	        xG=xInitial+(widthElementG/2);
	        yG=yInitial-(heigthElementG/2);
	        //System.out.println(elemento.getIdElement()+" xf "+xG+" yf "+yG);
	        
	        summatoryAreasG=summatoryAreasG+areaG;
	        summatoryAreasXG=summatoryAreasXG+(areaG*(xG));
	        summatoryAreasYG=summatoryAreasYG+(areaG*(yG));
	        
	        		        
		}
		//System.out.println("summatoryAreasYG "+summatoryAreasG);
		xCenterMassGeneral=summatoryAreasXG/summatoryAreasG;
        yCenterMassGeneral=summatoryAreasYG/summatoryAreasG;
        
		
	}

	@Override
	public double compute(Level toCompare) {
		return compute();
	}
		
}
