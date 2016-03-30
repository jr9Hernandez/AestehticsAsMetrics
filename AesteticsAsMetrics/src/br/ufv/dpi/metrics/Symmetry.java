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
		
		//calculation of scanning of level
		ScanLabeledLevel objScanLevel=new ScanLabeledLevel(level,width, height );
		elementsSelected=objScanLevel.DeterminePositions();
		
		//calculation of center of mass
		CenterOfMass objCenterOfMass=new CenterOfMass();
		objCenterOfMass.CalculatecenterOfMass(elementsSelected);
		xCenterMassGeneral=objCenterOfMass.getX();
		yCenterMassGeneral=objCenterOfMass.getY();
		
		//calculation of symmetry
		System.out.println("x "+xCenterMassGeneral+" y "+yCenterMassGeneral);
		double symmetryValue=symettry1(elementsSelected, xCenterMassGeneral, yCenterMassGeneral);
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
	
	public double symettry1Vertical(ArrayList states, double xCenterMassGeneral, double yCenterMassGeneral)
	{
		double [] gulAG;
		double [] gurAG;
		double [] gllAG;
		double [] glrAG;
		
		double [] gulAC;
		double [] gurAC;
		double [] gllAC;
		double [] glrAC;
		
		double symmetryValue;
		double symmetryValueGeneral=0;
		double symmetryValueCoins=0;
		double symmetryValueEnemies;
		
		gulAG=new double[4];
		gurAG=new double[4];
		gllAG=new double[4];
		glrAG=new double[4];
		
		gulAC=new double[4];
		gurAC=new double[4];
		gllAC=new double[4];
		glrAC=new double[4];
	
		
		double [] gulATG=new double[4];
		double [] gurATG=new double[4];
		double [] gllATG=new double[4];
		double [] glrATG=new double[4];
		
		double [] gulATC=new double[4];
		double [] gurATC=new double[4];
		double [] gllATC=new double[4];
		double [] glrATC=new double[4];
	
		
		double widthElement=0;
		double heigthElement=0;
		double x,y;
		
		//Symmetry General
		Iterator<BlockNode> itSymmetry1 = states.iterator();
		//System.out.println("size "+states.size());
		while(itSymmetry1.hasNext()){
			
			BlockNode elemento = itSymmetry1.next();
			Elements element=elemento.getElement();
			
			int xInitial = elemento.getX();
	        int yInitial= elemento.getY();
	        widthElement=element.getWidth();
	        heigthElement=element.getHeigth();
	        
	        if((xInitial+widthElement)<=xCenterMassGeneral )
	        {
	        	//block up left
	        	if(yInitial<=yCenterMassGeneral)
	        	{
	        		x=xInitial+(widthElement/2);
	        		y=yInitial-(heigthElement/2);
	        		gulAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gulATG[0]=gulATG[0]+gulAG[0];
	        		gulAG[1]=Math.abs(y-yCenterMassGeneral);
	        		//gulATG[1]=gulATG[1]+gulAG[1];
	        		gulAG[2]=widthElement;
	        		gulATG[2]=gulATG[2]+gulAG[2];
	        		gulAG[3]=heigthElement;
	        		gulATG[3]=gulATG[3]+gulAG[3];
	        		//gul.add(gulA);
	        	}
	        	
	        	//block low left
	        	else if(yInitial-heigthElement>=yCenterMassGeneral)
	        	{
	        		x=xInitial+(widthElement/2);
	        		y=yInitial-(heigthElement/2);
	        		gllAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gllATG[0]=gllATG[0]+gllAG[0];
	        		gllAG[1]=Math.abs(y-yCenterMassGeneral);
	        		//gllATG[1]=gllATG[1]+gllAG[1];
	        		gllAG[2]=widthElement;
	        		gllATG[2]=gllATG[2]+gllAG[2];
	        		gllAG[3]=heigthElement;
	        		gllATG[3]=gllATG[3]+gllAG[3];
	        		//gll.add(gllA);
	        	}
	        	else
	        	{
	        		x=xInitial+(widthElement/2);
	        	
	        		//first block of the element (up left)
	        		//y=(yInitial-heigthElement)+(yCenterMassGeneral-yInitial)/2;
	        		y=(yInitial-heigthElement)+(yCenterMassGeneral-(yInitial-heigthElement))/2;
	        		gulAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gulATG[0]=gulATG[0]+gulAG[0];
	        		gulAG[1]=Math.abs(y-yCenterMassGeneral);
	        		//gulATG[1]=gulATG[1]+gulAG[1];
	        		gulAG[2]=widthElement;
	        		gulATG[2]=gulATG[2]+gulAG[2];
	        		gulAG[3]=yCenterMassGeneral-(yInitial-heigthElement);
	        		gulATG[3]=gulATG[3]+gulAG[3];
	        		//gul.add(gulA);
	        	
	        		//second block of the element (low left)
	        		y=yInitial-(yInitial-yCenterMassGeneral)/2;
	        		gllAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gllATG[0]=gllATG[0]+gllAG[0];
	        		gllAG[1]=Math.abs(y-yCenterMassGeneral);
	        		//gllATG[1]=gllATG[1]+gllAG[1];
	        		gllAG[2]=widthElement;
	        		gllATG[2]=gllATG[2]+gllAG[2];
	        		gllAG[3]=(yInitial-yCenterMassGeneral);
	        		gllATG[3]=gllATG[3]+gllAG[3];
	        		//gll.add(gllA);
	        	}
	        }
	        else if(xInitial>=xCenterMassGeneral )
	        {
	        	
	        	//block up right
	        	if(yInitial<=yCenterMassGeneral)
	        	{
	        		x=xInitial+(widthElement/2);
	        		y=yInitial-(heigthElement/2);
	        		gurAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gurATG[0]=gurATG[0]+gurAG[0];
	        		gurAG[1]=Math.abs(y-yCenterMassGeneral);
	        		//gurATG[1]=gurATG[1]+gurAG[1];
	        		gurAG[2]=widthElement;
	        		gurATG[2]=gurATG[2]+gurAG[2];
	        		gurAG[3]=heigthElement;
	        		gurATG[3]=gurATG[3]+gurAG[3];
	        		//gur.add(gurA);
	        	}
	        	//block low right
	        	else if(yInitial-heigthElement>=yCenterMassGeneral)
	        	{
	        		x=xInitial+(widthElement/2);
	        		y=yInitial-(heigthElement/2);
	        		glrAG[0]=Math.abs(x-xCenterMassGeneral);
	        		glrATG[0]=glrATG[0]+glrAG[0];
	        		glrAG[1]=Math.abs(y-yCenterMassGeneral);
	        		//glrATG[1]=glrATG[1]+glrAG[1];
	        		glrAG[2]=widthElement;
	        		glrATG[2]=glrATG[2]+glrAG[2];
	        		glrAG[3]=heigthElement;
	        		glrATG[3]=glrATG[3]+glrAG[3];
	        		//glr.add(glrA);
	        	}
	        	else
	        	{
	        		
	        		x=xInitial+(widthElement/2);
	        		
	        		//first block of the element (up right)
	        		y=(yInitial-heigthElement)+(yCenterMassGeneral-(yInitial-heigthElement))/2;
	        		gurAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gurATG[0]=gurATG[0]+gurAG[0];
	        		gurAG[1]=Math.abs(y-yCenterMassGeneral);
	        		//gurATG[1]=gurATG[1]+gurAG[1];
	        		gurAG[2]=widthElement;
	        		gurATG[2]=gurATG[2]+gurAG[2];
	        		gurAG[3]=yCenterMassGeneral-(yInitial-heigthElement);
	        		gurATG[3]=gurATG[3]+gurAG[3];
	        		//gur.add(gulA);
		        	
	        		//second block of the element  (low right)
	        		y=yInitial-(yInitial-yCenterMassGeneral)/2;
	        		glrAG[0]=Math.abs(x-xCenterMassGeneral);
	        		glrATG[0]=glrATG[0]+glrAG[0];
	        		glrAG[1]=Math.abs(y-yCenterMassGeneral);
	        		//glrATG[1]=glrATG[1]+glrAG[1];
	        		glrAG[2]=widthElement;
	        		glrATG[2]=glrATG[2]+glrAG[2];
	        		glrAG[3]=yInitial-yCenterMassGeneral;
	        		glrATG[3]=glrATG[3]+glrAG[3];
	        		//glr.add(gllA);
	        	}
	        	
	        }
	        else
	        {
	        	if(yInitial<=yCenterMassGeneral)
	        	{
	        		y=yInitial-(heigthElement/2);
	        		
	        		//first block of the element (up left)
	        		x=(xInitial+(xCenterMassGeneral-xInitial)/2);
	        		gulAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gulATG[0]=gulATG[0]+gulAG[0];
			        gulAG[1]=Math.abs(y-yCenterMassGeneral);
			        //gulATG[1]=gulATG[1]+gulAG[1];
			        gulAG[2]=xCenterMassGeneral-xInitial;
			        gulATG[2]=gulATG[2]+gulAG[2];
			        gulAG[3]=heigthElement;
			        gulATG[3]=gulATG[3]+gulAG[3];
			        //gul.add(gurA);
			        
			        //second block of the element (up right)
			        x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassGeneral)/2;
	        		gurAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gurATG[0]=gurATG[0]+gurAG[0];
			        gurAG[1]=Math.abs(y-yCenterMassGeneral);
			        //gurATG[1]=gurATG[1]+gurAG[1];
			        gurAG[2]=(xInitial+widthElement)-xCenterMassGeneral;
			        gurATG[2]=gurATG[2]+gurAG[2];
			        gurAG[3]=heigthElement;
			        gurATG[3]=gurATG[3]+gurAG[3];
			        //gur.add(gurA);
	        		
	        	}
	        	else if(yInitial-heigthElement>=yCenterMassGeneral)
	        	{
	        		y=yInitial-(heigthElement/2);
	        		
	        		//first block of the element (low left)
	        		x=(xInitial+(xCenterMassGeneral-xInitial)/2);
	        		gllAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gllATG[0]=gllATG[0]+gllAG[0];
			        gllAG[1]=Math.abs(y-yCenterMassGeneral);
			        //gllATG[1]=gllATG[1]+gllAG[1];
			        gllAG[2]=(xCenterMassGeneral-xInitial);
			        gllATG[2]=gllATG[2]+gllAG[2];
			        gllAG[3]=heigthElement;
			        gllATG[3]=gllATG[3]+gllAG[3];
			        //gll.add(gurA);
			        
			        //second block of the element (low right)
			        x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassGeneral)/2;
	        		glrAG[0]=Math.abs(x-xCenterMassGeneral);
	        		glrATG[0]=glrATG[0]+glrAG[0];
			        glrAG[1]=Math.abs(y-yCenterMassGeneral);
			        //glrATG[1]=glrATG[1]+glrAG[1];
			        glrAG[2]=(xInitial+widthElement)-xCenterMassGeneral;
			        glrATG[2]=glrATG[2]+glrAG[2];
			        glrAG[3]=heigthElement;
			        glrATG[3]=glrATG[3]+glrAG[3];
			        //gur.add(gurA);
	        	}
	        	else
	        	{
	        		//falta implementar caso todos los cuadrantes
	        		//first block of the element (up left)
	        		x=(xInitial+(xCenterMassGeneral-xInitial)/2);
	        		y=(yInitial-heigthElement)+(yCenterMassGeneral-(yInitial-heigthElement))/2;
	        		
	        		gulAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gulATG[0]=gulATG[0]+gulAG[0];
			        gulAG[1]=Math.abs(y-yCenterMassGeneral);
			        //gulATG[1]=gulATG[1]+gulAG[1];
			        gulAG[2]=xCenterMassGeneral-xInitial;
			        gulATG[2]=gulATG[2]+gulAG[2];
			        gulAG[3]=yCenterMassGeneral-(yInitial-heigthElement);
			        gulATG[3]=gulATG[3]+gulAG[3];
			        
	        		//second block of the element (up right)
	        		x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassGeneral)/2;
	        		y=(yInitial-heigthElement)+(yCenterMassGeneral-(yInitial-heigthElement))/2;
	        		
	        		gurAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gurATG[0]=gurATG[0]+gurAG[0];
			        gurAG[1]=Math.abs(y-yCenterMassGeneral);
			        //gurATG[1]=gurATG[1]+gurAG[1];
			        gurAG[2]=(xInitial+widthElement)-xCenterMassGeneral;
			        gurATG[2]=gurATG[2]+gurAG[2];
			        gurAG[3]=yCenterMassGeneral-(yInitial-heigthElement);
			        gurATG[3]=gurATG[3]+gurAG[3];
	        		
	        		//first block of the element (low left)
	        		x=(xInitial+(xCenterMassGeneral-xInitial)/2);
	        		y=yInitial-(yInitial-yCenterMassGeneral)/2;
	        		
	        		gllAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gllATG[0]=gllATG[0]+gllAG[0];
			        gllAG[1]=Math.abs(y-yCenterMassGeneral);
			        //gllATG[1]=gllATG[1]+gllAG[1];
			        gllAG[2]=(xCenterMassGeneral-xInitial);
			        gllATG[2]=gllATG[2]+gllAG[2];
			        gllAG[3]=yInitial-yCenterMassGeneral;
			        gllATG[3]=gllATG[3]+gllAG[3];
			        
	        		//second block of the element (low right)
	        		x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassGeneral)/2;
	        		y=yInitial-(yInitial-yCenterMassGeneral)/2;
	        		
	        		glrAG[0]=Math.abs(x-xCenterMassGeneral);
	        		glrATG[0]=glrATG[0]+glrAG[0];
			        glrAG[1]=Math.abs(y-yCenterMassGeneral);
			        //glrATG[1]=glrATG[1]+glrAG[1];
			        glrAG[2]=(xInitial+widthElement)-xCenterMassGeneral;
			        glrATG[2]=glrATG[2]+glrAG[2];
			        glrAG[3]=yInitial-yCenterMassGeneral;
			        glrATG[3]=glrATG[3]+glrAG[3];
	        	}
	        }
	        
		
		}
		//System.out.println("gulAT "+gulATG[0]+" "+gulATG[1]+" "+gulATG[2]+" "+gulATG[3]);
		//System.out.println("gurAT "+gurATG[0]+" "+gurATG[1]+" "+gurATG[2]+" "+gurATG[3]);
		//System.out.println("gllAT "+gllATG[0]+" "+gllATG[1]+" "+gllATG[2]+" "+gllATG[3]);
		//System.out.println("glrAT "+glrATG[0]+" "+glrATG[1]+" "+glrATG[2]+" "+glrATG[3]);
		//symmetryValueGeneral=SubstractionSymmetries(gulATG,gllATG)+SubstractionSymmetries(gurATG,glrATG)+SubstractionSymmetries(gulATG,gurATG)+SubstractionSymmetries(gllATG,glrATG)+SubstractionSymmetries(gulATG,glrATG)+SubstractionSymmetries(gurATG,gllATG);
		symmetryValueGeneral=SubstractionSymmetries(gulATG,gurATG)+SubstractionSymmetries(gllATG,glrATG);
		//System.out.println("symmetryValue "+symmetryValueGeneral);
		

		symmetryValue=symmetryValueGeneral;
		return symmetryValue;
	}
	
	public double symettry1(ArrayList states, double xCenterMassGeneral, double yCenterMassGeneral)
	{
		double [] gulAG;
		double [] gurAG;
		double [] gllAG;
		double [] glrAG;
		
		double [] gulAC;
		double [] gurAC;
		double [] gllAC;
		double [] glrAC;
		
		double symmetryValue;
		double symmetryValueGeneral=0;
		double symmetryValueCoins=0;
		double symmetryValueEnemies;
		
		gulAG=new double[4];
		gurAG=new double[4];
		gllAG=new double[4];
		glrAG=new double[4];
		
		gulAC=new double[4];
		gurAC=new double[4];
		gllAC=new double[4];
		glrAC=new double[4];
	
		
		double [] gulATG=new double[4];
		double [] gurATG=new double[4];
		double [] gllATG=new double[4];
		double [] glrATG=new double[4];
		
		double [] gulATC=new double[4];
		double [] gurATC=new double[4];
		double [] gllATC=new double[4];
		double [] glrATC=new double[4];
	
		
		double widthElement=0;
		double heigthElement=0;
		double x,y;
		
		//Symmetry General
		Iterator<BlockNode> itSymmetry1 = states.iterator();
		while(itSymmetry1.hasNext()){
			BlockNode elemento = itSymmetry1.next();
			Elements element=elemento.getElement();

			int xInitial = elemento.getX();
	        int yInitial= elemento.getY();
	        widthElement=element.getWidth();
	        heigthElement=element.getHeigth();
	        
	        if((xInitial+widthElement)<=xCenterMassGeneral )
	        {
	        	//block up left
	        	if(yInitial<=yCenterMassGeneral)
	        	{
	        		x=xInitial+(widthElement/2);
	        		y=yInitial-(heigthElement/2);
	        		gulAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gulATG[0]=gulATG[0]+gulAG[0];
	        		gulAG[1]=Math.abs(y-yCenterMassGeneral);
	        		gulATG[1]=gulATG[1]+gulAG[1];
	        		gulAG[2]=widthElement;
	        		gulATG[2]=gulATG[2]+gulAG[2];
	        		gulAG[3]=heigthElement;
	        		gulATG[3]=gulATG[3]+gulAG[3];
	        		//gul.add(gulA);
	        	}
	        	
	        	//block low left
	        	else if(yInitial-heigthElement>=yCenterMassGeneral)
	        	{
	        		x=xInitial+(widthElement/2);
	        		y=yInitial-(heigthElement/2);
	        		gllAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gllATG[0]=gllATG[0]+gllAG[0];
	        		gllAG[1]=Math.abs(y-yCenterMassGeneral);
	        		gllATG[1]=gllATG[1]+gllAG[1];
	        		gllAG[2]=widthElement;
	        		gllATG[2]=gllATG[2]+gllAG[2];
	        		gllAG[3]=heigthElement;
	        		gllATG[3]=gllATG[3]+gllAG[3];
	        		//gll.add(gllA);
	        	}
	        	else
	        	{
	        		x=xInitial+(widthElement/2);
	        	
	        		//first block of the element (up left)
	        		//y=(yInitial-heigthElement)+(yCenterMassGeneral-yInitial)/2;
	        		y=(yInitial-heigthElement)+(yCenterMassGeneral-(yInitial-heigthElement))/2;
	        		gulAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gulATG[0]=gulATG[0]+gulAG[0];
	        		gulAG[1]=Math.abs(y-yCenterMassGeneral);
	        		gulATG[1]=gulATG[1]+gulAG[1];
	        		gulAG[2]=widthElement;
	        		gulATG[2]=gulATG[2]+gulAG[2];
	        		gulAG[3]=yCenterMassGeneral-(yInitial-heigthElement);
	        		gulATG[3]=gulATG[3]+gulAG[3];
	        		//gul.add(gulA);
	        	
	        		//second block of the element (low left)
	        		y=yInitial-(yInitial-yCenterMassGeneral)/2;
	        		gllAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gllATG[0]=gllATG[0]+gllAG[0];
	        		gllAG[1]=Math.abs(y-yCenterMassGeneral);
	        		gllATG[1]=gllATG[1]+gllAG[1];
	        		gllAG[2]=widthElement;
	        		gllATG[2]=gllATG[2]+gllAG[2];
	        		gllAG[3]=(yInitial-yCenterMassGeneral);
	        		gllATG[3]=gllATG[3]+gllAG[3];
	        		//gll.add(gllA);
	        	}
	        }
	        else if(xInitial>=xCenterMassGeneral )
	        {
	        	
	        	//block up right
	        	if(yInitial<=yCenterMassGeneral)
	        	{
	        		x=xInitial+(widthElement/2);
	        		y=yInitial-(heigthElement/2);
	        		gurAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gurATG[0]=gurATG[0]+gurAG[0];
	        		gurAG[1]=Math.abs(y-yCenterMassGeneral);
	        		gurATG[1]=gurATG[1]+gurAG[1];
	        		gurAG[2]=widthElement;
	        		gurATG[2]=gurATG[2]+gurAG[2];
	        		gurAG[3]=heigthElement;
	        		gurATG[3]=gurATG[3]+gurAG[3];
	        		//gur.add(gurA);
	        	}
	        	//block low right
	        	else if(yInitial-heigthElement>=yCenterMassGeneral)
	        	{
	        		x=xInitial+(widthElement/2);
	        		y=yInitial-(heigthElement/2);
	        		glrAG[0]=Math.abs(x-xCenterMassGeneral);
	        		glrATG[0]=glrATG[0]+glrAG[0];
	        		glrAG[1]=Math.abs(y-yCenterMassGeneral);
	        		glrATG[1]=glrATG[1]+glrAG[1];
	        		glrAG[2]=widthElement;
	        		glrATG[2]=glrATG[2]+glrAG[2];
	        		glrAG[3]=heigthElement;
	        		glrATG[3]=glrATG[3]+glrAG[3];
	        		//glr.add(glrA);
	        	}
	        	else
	        	{
	        		
	        		x=xInitial+(widthElement/2);
	        		
	        		//first block of the element (up right)
	        		y=(yInitial-heigthElement)+(yCenterMassGeneral-(yInitial-heigthElement))/2;
	        		gurAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gurATG[0]=gurATG[0]+gurAG[0];
	        		gurAG[1]=Math.abs(y-yCenterMassGeneral);
	        		gurATG[1]=gurATG[1]+gurAG[1];
	        		gurAG[2]=widthElement;
	        		gurATG[2]=gurATG[2]+gurAG[2];
	        		gurAG[3]=yCenterMassGeneral-(yInitial-heigthElement);
	        		gurATG[3]=gurATG[3]+gurAG[3];
	        		//gur.add(gulA);
		        	
	        		//second block of the element  (low right)
	        		y=yInitial-(yInitial-yCenterMassGeneral)/2;
	        		glrAG[0]=Math.abs(x-xCenterMassGeneral);
	        		glrATG[0]=glrATG[0]+glrAG[0];
	        		glrAG[1]=Math.abs(y-yCenterMassGeneral);
	        		glrATG[1]=glrATG[1]+glrAG[1];
	        		glrAG[2]=widthElement;
	        		glrATG[2]=glrATG[2]+glrAG[2];
	        		glrAG[3]=yInitial-yCenterMassGeneral;
	        		glrATG[3]=glrATG[3]+glrAG[3];
	        		//glr.add(gllA);
	        	}
	        	
	        }
	        else
	        {
	        	if(yInitial<=yCenterMassGeneral)
	        	{
	        		y=yInitial-(heigthElement/2);
	        		
	        		//first block of the element (up left)
	        		x=(xInitial+(xCenterMassGeneral-xInitial)/2);
	        		gulAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gulATG[0]=gulATG[0]+gulAG[0];
			        gulAG[1]=Math.abs(y-yCenterMassGeneral);
			        gulATG[1]=gulATG[1]+gulAG[1];
			        gulAG[2]=xCenterMassGeneral-xInitial;
			        gulATG[2]=gulATG[2]+gulAG[2];
			        gulAG[3]=heigthElement;
			        gulATG[3]=gulATG[3]+gulAG[3];
			        //gul.add(gurA);
			        
			        //second block of the element (up right)
			        x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassGeneral)/2;
	        		gurAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gurATG[0]=gurATG[0]+gurAG[0];
			        gurAG[1]=Math.abs(y-yCenterMassGeneral);
			        gurATG[1]=gurATG[1]+gurAG[1];
			        gurAG[2]=(xInitial+widthElement)-xCenterMassGeneral;
			        gurATG[2]=gurATG[2]+gurAG[2];
			        gurAG[3]=heigthElement;
			        gurATG[3]=gurATG[3]+gurAG[3];
			        //gur.add(gurA);
	        		
	        	}
	        	else if(yInitial-heigthElement>=yCenterMassGeneral)
	        	{
	        		y=yInitial-(heigthElement/2);
	        		
	        		//first block of the element (low left)
	        		x=(xInitial+(xCenterMassGeneral-xInitial)/2);
	        		gllAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gllATG[0]=gllATG[0]+gllAG[0];
			        gllAG[1]=Math.abs(y-yCenterMassGeneral);
			        gllATG[1]=gllATG[1]+gllAG[1];
			        gllAG[2]=(xCenterMassGeneral-xInitial);
			        gllATG[2]=gllATG[2]+gllAG[2];
			        gllAG[3]=heigthElement;
			        gllATG[3]=gllATG[3]+gllAG[3];
			        //gll.add(gurA);
			        
			        //second block of the element (low right)
			        x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassGeneral)/2;
	        		glrAG[0]=Math.abs(x-xCenterMassGeneral);
	        		glrATG[0]=glrATG[0]+glrAG[0];
			        glrAG[1]=Math.abs(y-yCenterMassGeneral);
			        glrATG[1]=glrATG[1]+glrAG[1];
			        glrAG[2]=(xInitial+widthElement)-xCenterMassGeneral;
			        glrATG[2]=glrATG[2]+glrAG[2];
			        glrAG[3]=heigthElement;
			        glrATG[3]=glrATG[3]+glrAG[3];
			        //gur.add(gurA);
	        	}
	        	else
	        	{
	        		//falta implementar caso todos los cuadrantes
	        		//first block of the element (up left)
	        		x=(xInitial+(xCenterMassGeneral-xInitial)/2);
	        		y=(yInitial-heigthElement)+(yCenterMassGeneral-(yInitial-heigthElement))/2;
	        		
	        		gulAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gulATG[0]=gulATG[0]+gulAG[0];
			        gulAG[1]=Math.abs(y-yCenterMassGeneral);
			        gulATG[1]=gulATG[1]+gulAG[1];
			        gulAG[2]=xCenterMassGeneral-xInitial;
			        gulATG[2]=gulATG[2]+gulAG[2];
			        gulAG[3]=yCenterMassGeneral-(yInitial-heigthElement);
			        gulATG[3]=gulATG[3]+gulAG[3];
			        
	        		//second block of the element (up right)
	        		x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassGeneral)/2;
	        		y=(yInitial-heigthElement)+(yCenterMassGeneral-(yInitial-heigthElement))/2;
	        		
	        		gurAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gurATG[0]=gurATG[0]+gurAG[0];
			        gurAG[1]=Math.abs(y-yCenterMassGeneral);
			        gurATG[1]=gurATG[1]+gurAG[1];
			        gurAG[2]=(xInitial+widthElement)-xCenterMassGeneral;
			        gurATG[2]=gurATG[2]+gurAG[2];
			        gurAG[3]=yCenterMassGeneral-(yInitial-heigthElement);
			        gurATG[3]=gurATG[3]+gurAG[3];
	        		
	        		//first block of the element (low left)
	        		x=(xInitial+(xCenterMassGeneral-xInitial)/2);
	        		y=yInitial-(yInitial-yCenterMassGeneral)/2;
	        		
	        		gllAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gllATG[0]=gllATG[0]+gllAG[0];
			        gllAG[1]=Math.abs(y-yCenterMassGeneral);
			        gllATG[1]=gllATG[1]+gllAG[1];
			        gllAG[2]=(xCenterMassGeneral-xInitial);
			        gllATG[2]=gllATG[2]+gllAG[2];
			        gllAG[3]=yInitial-yCenterMassGeneral;
			        gllATG[3]=gllATG[3]+gllAG[3];
			        
	        		//second block of the element (low right)
	        		x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassGeneral)/2;
	        		y=yInitial-(yInitial-yCenterMassGeneral)/2;
	        		
	        		glrAG[0]=Math.abs(x-xCenterMassGeneral);
	        		glrATG[0]=glrATG[0]+glrAG[0];
			        glrAG[1]=Math.abs(y-yCenterMassGeneral);
			        glrATG[1]=glrATG[1]+glrAG[1];
			        glrAG[2]=(xInitial+widthElement)-xCenterMassGeneral;
			        glrATG[2]=glrATG[2]+glrAG[2];
			        glrAG[3]=yInitial-yCenterMassGeneral;
			        glrATG[3]=glrATG[3]+glrAG[3];
	        	}
	        }
	        
		
		}
		System.out.println("gulAT "+gulATG[0]+" "+gulATG[1]+" "+gulATG[2]+" "+gulATG[3]);
		System.out.println("gurAT "+gurATG[0]+" "+gurATG[1]+" "+gurATG[2]+" "+gurATG[3]);
		System.out.println("gllAT "+gllATG[0]+" "+gllATG[1]+" "+gllATG[2]+" "+gllATG[3]);
		System.out.println("glrAT "+glrATG[0]+" "+glrATG[1]+" "+glrATG[2]+" "+glrATG[3]);
		symmetryValueGeneral=SubstractionSymmetries(gulATG,gllATG)+SubstractionSymmetries(gurATG,glrATG)+SubstractionSymmetries(gulATG,gurATG)+SubstractionSymmetries(gllATG,glrATG)+SubstractionSymmetries(gulATG,glrATG)+SubstractionSymmetries(gurATG,gllATG);
		//symmetryValueGeneral=SubstractionSymmetries(gulATG,gurATG)+SubstractionSymmetries(gllATG,glrATG);
		//System.out.println("symmetryValue "+symmetryValueGeneral);
		
		

		
		symmetryValue=symmetryValueGeneral;
		return symmetryValue;
	}

	@Override
	public double compute(Level toCompare) {
		return compute();
	}
		
}
