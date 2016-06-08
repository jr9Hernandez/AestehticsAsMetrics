package br.ufv.dpi.metrics;

import graphBuilder.BlockNode;

import java.util.ArrayList;
import java.util.Iterator;

import beauty.Elements;
import beauty.ElementsToPlace;
import dk.itu.mario.engine.sprites.SpriteTemplate;
import dk.itu.mario.level.Level;

public class BalanceVertical extends Metrics  {
	

	private ArrayList<BlockNode>  elementsSelected;
	private double xCenterMassGeneral;
	private double yCenterMassGeneral;
	
	public BalanceVertical(int width, int height, Level level) 
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
		//xCenterMassGeneral=objCenterOfMass.getX();
		//yCenterMassGeneral=objCenterOfMass.getY();
		xCenterMassGeneral=level.getxExit()/2;
		yCenterMassGeneral=6;
		
		//calculation of Balance
		//System.out.println("x "+xCenterMassGeneral+" y "+yCenterMassGeneral);
		double symmetryValue=99999999;
		
		for(int i=0;i<30;i++)
		{
			double symmetryValueP=Balance(elementsSelected, xCenterMassGeneral, yCenterMassGeneral);
			if(symmetryValueP<symmetryValue)
			{
				symmetryValue=symmetryValueP;
			}
		}
		//System.out.println("Symmetry "+symmetryValue);
		
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
		return symmetryValue;
	}
	
	public double SubstractionSymmetries(double [] Arr1, double [] Arr2)
	{double summatory=0;
		for (int i=0;i< Arr1.length;i++)
		{
			
			
			summatory= summatory+Math.abs(Arr1[i]-Arr2[i]);
		}
		return summatory;
	}
	

	public double Balance(ArrayList states, double xCenterMassGeneral, double yCenterMassGeneral)
	{
		double [] gulAG;
		double [] gurAG;
		double [] gllAG;
		double [] glrAG;
		
		double [] gulAC;
		double [] gurAC;
		double [] gllAC;
		double [] glrAC;

		double balanceValueLeft=0;
		double balanceValueRight=0;
		double balanceValueUp=0;
		double balanceValueLow=0;
		
		double balanceVertical=0;
		double balanceHorizontal=0;
		
		
		double balanceValueGeneral=0;

		
		gulAG=new double[5];
		gurAG=new double[5];
		gllAG=new double[5];
		glrAG=new double[5];
		
		gulAC=new double[5];
		gurAC=new double[5];
		gllAC=new double[5];
		glrAC=new double[5];
	
		
		double [] gulATG=new double[5];
		double [] gurATG=new double[5];
		double [] gllATG=new double[5];
		double [] glrATG=new double[5];
		
		double [] gulATC=new double[5];
		double [] gurATC=new double[5];
		double [] gllATC=new double[5];
		double [] glrATC=new double[5];
	
		
		double widthElement=0;
		double heigthElement=0;
		double x,y;
		
		//Balance General
		Iterator<BlockNode> itSymmetry1 = states.iterator();
		while(itSymmetry1.hasNext()){
			BlockNode elemento = itSymmetry1.next();
			Elements element=elemento.getElement();

			int xInitial = elemento.getX();
	        int yInitial= elemento.getY();
	        widthElement=element.getWidth();
	        heigthElement=element.getHeigth();
	        
	        if(xInitial+(widthElement/2)<=xCenterMassGeneral )
	        {
	        	//block up left
	        	if(yInitial-(heigthElement/2)<=yCenterMassGeneral)
	        	{
	        		x=xInitial+(widthElement/2);
	        		y=yInitial-(heigthElement/2);
	        		gulAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gulATG[0]=gulATG[0]+gulAG[0];
	        		gulAG[1]=Math.abs(y-yCenterMassGeneral);
	        		gulATG[1]=gulATG[1]+gulAG[1];
	        		gulAG[2]=widthElement*heigthElement;
	        		gulATG[2]=gulATG[2]+gulAG[2];
	        		
	        		gulAG[3]=(gulAG[2]*gulAG[0]);
	        		gulATG[3]=gulATG[3]+gulAG[3];
	        		gulAG[4]=(gulAG[2]*gulAG[1]);
	        		gulATG[4]=gulATG[4]+gulAG[4];
	        		//gul.add(gulA);
	        	}
	        	
	        	//block low left
	        	else 
	        	{
	        		x=xInitial+(widthElement/2);
	        		y=yInitial-(heigthElement/2);
	        		gllAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gllATG[0]=gllATG[0]+gllAG[0];
	        		gllAG[1]=Math.abs(y-yCenterMassGeneral);
	        		gllATG[1]=gllATG[1]+gllAG[1];
	        		gllAG[2]=widthElement*heigthElement;
	        		gllATG[2]=gllATG[2]+gllAG[2];
	        		
	        		gllAG[3]=(gllAG[2]*gllAG[0]);
	        		gllATG[3]=gllATG[3]+gllAG[3];
	        		gllAG[4]=(gllAG[2]*gllAG[1]);
	        		gllATG[4]=gllATG[4]+gllAG[4];
	        		//gll.add(gllA);
	        	}

	        }
	        else 
	        {
	        	
	        	//block up right
	        	if(yInitial-(heigthElement/2)<=yCenterMassGeneral)
	        	{
	        		x=xInitial+(widthElement/2);
	        		y=yInitial-(heigthElement/2);
	        		gurAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gurATG[0]=gurATG[0]+gurAG[0];
	        		gurAG[1]=Math.abs(y-yCenterMassGeneral);
	        		gurATG[1]=gurATG[1]+gurAG[1];
	        		gurAG[2]=widthElement*heigthElement;
	        		gurATG[2]=gurATG[2]+gurAG[2];
	        		
	        		gurAG[3]=(gurAG[2]*gurAG[0]);
	        		gurATG[3]=gurATG[3]+gurAG[3];
	        		gurAG[4]=(gurAG[2]*gurAG[1]);
	        		gurATG[4]=gurATG[4]+gurAG[4];
	        		//gur.add(gurA);
	        	}
	        	//block low right
	        	else 
	        	{
	        		x=xInitial+(widthElement/2);
	        		y=yInitial-(heigthElement/2);
	        		glrAG[0]=Math.abs(x-xCenterMassGeneral);
	        		glrATG[0]=glrATG[0]+glrAG[0];
	        		glrAG[1]=Math.abs(y-yCenterMassGeneral);
	        		glrATG[1]=glrATG[1]+glrAG[1];
	        		glrAG[2]=widthElement*heigthElement;
	        		glrATG[2]=glrATG[2]+glrAG[2];
	        		
	        		glrAG[3]=(glrAG[2]*glrAG[0]);
	        		glrATG[3]=glrATG[3]+glrAG[3];
	        		glrAG[4]=(glrAG[2]*glrAG[1]);
	        		glrATG[4]=glrATG[4]+glrAG[4];
	        		//glr.add(glrA);
	        	}
	     
	        	
	        }

	        
		
		}
		/*System.out.println("gulAT "+gulATG[0]+" "+gulATG[1]+" "+gulATG[2]+" "+gulATG[3]);
		System.out.println("gurAT "+gurATG[0]+" "+gurATG[1]+" "+gurATG[2]+" "+gurATG[3]);
		System.out.println("gllAT "+gllATG[0]+" "+gllATG[1]+" "+gllATG[2]+" "+gllATG[3]);
		System.out.println("glrAT "+glrATG[0]+" "+glrATG[1]+" "+glrATG[2]+" "+glrATG[3]);*/
		balanceValueLeft=gulATG[3]+gllATG[3];
		balanceValueRight=gurATG[3]+glrATG[3];
		balanceValueUp=gulATG[4]+gurATG[4];
		balanceValueLow=gllATG[4]+glrATG[4];
		
		System.out.println("balanceValueLeft "+balanceValueLeft);
		System.out.println("balanceValueRight "+balanceValueRight);
		System.out.println("balanceValueUp "+balanceValueUp);
		System.out.println("balanceValueLow "+balanceValueLow);
		
		balanceVertical=Math.abs(balanceValueLeft-balanceValueRight);
		balanceHorizontal=Math.abs(balanceValueUp-balanceValueLow);
		
		System.out.println("balanceVertical "+balanceVertical);
		System.out.println("balanceHorizontal "+balanceHorizontal);
		
		balanceValueGeneral=balanceVertical+balanceHorizontal;
		
		System.out.println("balanceValueGeneral "+balanceValueGeneral);
		//symmetryValueGeneral=SubstractionSymmetries(gulATG,gurATG)+SubstractionSymmetries(gllATG,glrATG);
		//System.out.println("symmetryValue "+symmetryValueGeneral);
		
		

		return balanceVertical;
	}

	@Override
	public double compute(Level toCompare) {
		return compute();
	}
		
}
