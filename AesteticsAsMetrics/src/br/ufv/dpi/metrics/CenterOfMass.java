package br.ufv.dpi.metrics;

import graphBuilder.BlockNode;

import java.util.ArrayList;
import java.util.Iterator;

import beauty.Elements;

public class CenterOfMass {

	private double x;
	private double y;
	public CenterOfMass(){

	}
	public void CalculatecenterOfMass(ArrayList states)
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
		x=summatoryAreasXG/summatoryAreasG;
        y=summatoryAreasYG/summatoryAreasG;
		//x=20.0;
		//y=6.0;
        
		
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	
}
