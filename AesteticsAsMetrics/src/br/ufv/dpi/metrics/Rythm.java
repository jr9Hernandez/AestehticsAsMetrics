package br.ufv.dpi.metrics;

import graphBuilder.BlockNode;

import java.util.ArrayList;
import java.util.Iterator;

import beauty.Elements;
import beauty.ElementsToPlace;
import dk.itu.mario.engine.sprites.SpriteTemplate;
import dk.itu.mario.level.Level;

public class Rythm extends Metrics  {
	

	private ArrayList<BlockNode>  elementsSelected;
	private double xCenterMassGeneral;
	private double yCenterMassGeneral;
	
	private double RHM;
	private double RHMx;
	private double RHMy;
	private double RHMarea;
	
	
	
	public Rythm(int width, int height, Level level) 
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
		yCenterMassGeneral=5;
		
		System.out.println("xCenterMassGeneral "+xCenterMassGeneral);
		System.out.println("yCenterMassGeneral "+yCenterMassGeneral);
		
		//calculation of rythm
		System.out.println("x "+xCenterMassGeneral+" y "+yCenterMassGeneral);
		
		double rythmValueGeneral=99999999;
		for(int i=8;i<32;i++)
		{
		double rythmValueGeneralp=Rythm1Areas(elementsSelected, i, yCenterMassGeneral);
		if(rythmValueGeneralp<rythmValueGeneral)
		{
			rythmValueGeneral=rythmValueGeneralp;
		}
		}
		
		//testing the captured elements
		/*Iterator<BlockNode> it = elementsSelected.iterator();
		while(it.hasNext()){
			BlockNode elemento = it.next();
			
			int xInitial = elemento.getX();
	        int yInitial= elemento.getY();
	        int widthElement=(elemento.getElement()).getWidth();
	        int heigthElement=(elemento.getElement()).getHeigth();
	        int typeElement=(elemento.getElement()).getTypeElem();
	        
	        System.out.println("type "+typeElement+" x "+xInitial+" y "+yInitial+" widthElement "+widthElement+ " heigthElement "+heigthElement);
		}*/
		return rythmValueGeneral;
	}
	
	public double SubstractionRythm(double [] Arr1, double [] Arr2,int type)
	{double summatory=0;
		//for (int i=0;i< Arr1.length;i++)
		//{
		summatory= Math.abs(Arr1[type]-Arr2[type]);
		//}
		return summatory;
	}
	
	
	public double Rythm1Areas(ArrayList states, double xCenterMassGeneral, double yCenterMassGeneral)
	{
		
		double [] gulAG;
		double [] gurAG;
		double [] gllAG;
		double [] glrAG;
		
		double [] gulAC;
		double [] gurAC;
		double [] gllAC;
		double [] glrAC;
		
		double rythmValueX;
		double rythmValueY;
		double rythmValueA;
		double rythmValueGeneral;
		
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
			
			double xInitial = elemento.getX();
	        double yInitial= elemento.getY();
	       
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
	        		
	        		gulAG[3]=heigthElement;
	        		
	        		//gul.add(gulA);
	        			        		
	        		//new symmetry with areas
	        		
	        		gulAG[2]=gulAG[2]*gulAG[3];
	        		gulAG[3]=0;
	        		gulATG[2]=gulATG[2]+gulAG[2];
	        		gulATG[3]=gulATG[3]+gulAG[3];
	        		
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
	        		
	        		gllAG[3]=heigthElement;
	        		
	        		//gll.add(gllA);
	        		
	        		
	        		//new symmetry with areas
	        		gllAG[2]=gllAG[2]*gllAG[3];
	        		gllAG[3]=0;
	        		gllATG[2]=gllATG[2]+gllAG[2];
	        		gllATG[3]=gllATG[3]+gllAG[3];

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
	        		
	        		gulAG[3]=yCenterMassGeneral-(yInitial-heigthElement);
	        		
	        		//gul.add(gulA);	        		
	        		
	        		//new symmetry with areas
	        		gulAG[2]=gulAG[2]*gulAG[3];
	        		gulAG[3]=0;
	        		gulATG[2]=gulATG[2]+gulAG[2];
	        		gulATG[3]=gulATG[3]+gulAG[3];
	        	
	        		//second block of the element (low left)
	        		y=yInitial-(yInitial-yCenterMassGeneral)/2;
	        		gllAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gllATG[0]=gllATG[0]+gllAG[0];
	        		gllAG[1]=Math.abs(y-yCenterMassGeneral);
	        		gllATG[1]=gllATG[1]+gllAG[1];
	        		gllAG[2]=widthElement;
	        		
	        		gllAG[3]=(yInitial-yCenterMassGeneral);
	        		
	        		//gll.add(gllA);
	        			        
	        		
	        		//new symmetry with areas
	        		gllAG[2]=gllAG[2]*gllAG[3];
	        		gllAG[3]=0;
	        		gllATG[2]=gllATG[2]+gllAG[2];
	        		gllATG[3]=gllATG[3]+gllAG[3];
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
	        		
	        		gurAG[3]=heigthElement;
	        		
	        		//gur.add(gurA);
	        		
	        		
	        		//new symmetry with areas
	        		gurAG[2]=gurAG[2]*gurAG[3];
	        		gurAG[3]=0;
	        		gurATG[2]=gurATG[2]+gurAG[2];
	        		gurATG[3]=gurATG[3]+gurAG[3];
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
	        		
	        		glrAG[3]=heigthElement;
	        		
	        		//glr.add(glrA);
	        		
	        		
	        		//new symmetry with areas
	        		glrAG[2]=glrAG[2]*glrAG[3];
	        		glrAG[3]=0;
	        		glrATG[2]=glrATG[2]+glrAG[2];
	        		glrATG[3]=glrATG[3]+glrAG[3];
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
	        		
	        		gurAG[3]=yCenterMassGeneral-(yInitial-heigthElement);
	        		
	        		//gur.add(gulA);
	        		
	        		
	        		//new symmetry with areas
	        		gurAG[2]=gurAG[2]*gurAG[3];
	        		gurAG[3]=0;
	        		gurATG[2]=gurATG[2]+gurAG[2];
	        		gurATG[3]=gurATG[3]+gurAG[3];
		        	
	        		//second block of the element  (low right)
	        		y=yInitial-(yInitial-yCenterMassGeneral)/2;
	        		glrAG[0]=Math.abs(x-xCenterMassGeneral);
	        		glrATG[0]=glrATG[0]+glrAG[0];
	        		glrAG[1]=Math.abs(y-yCenterMassGeneral);
	        		glrATG[1]=glrATG[1]+glrAG[1];
	        		glrAG[2]=widthElement;
	        		
	        		glrAG[3]=yInitial-yCenterMassGeneral;
	        		
	        		//glr.add(gllA);
	        			        	
	        		
	        		//new symmetry with areas
	        		glrAG[2]=glrAG[2]*glrAG[3];
	        		glrAG[3]=0;
	        		glrATG[2]=glrATG[2]+glrAG[2];
	        		glrATG[3]=glrATG[3]+glrAG[3];
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
			        
			        gulAG[3]=heigthElement;
			        
			        //gul.add(gurA);
			        
	        		
	        		//new symmetry with areas
	        		gulAG[2]=gulAG[2]*gulAG[3];
	        		gulAG[3]=0;
	        		gulATG[2]=gulATG[2]+gulAG[2];
	        		gulATG[3]=gulATG[3]+gulAG[3];
			        
			        //second block of the element (up right)
			        x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassGeneral)/2;
	        		gurAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gurATG[0]=gurATG[0]+gurAG[0];
			        gurAG[1]=Math.abs(y-yCenterMassGeneral);
			        gurATG[1]=gurATG[1]+gurAG[1];
			        gurAG[2]=(xInitial+widthElement)-xCenterMassGeneral;
			        
			        gurAG[3]=heigthElement;
			        
			        //gur.add(gurA);
			        
	        		
	        		//new symmetry with areas
	        		gurAG[2]=gurAG[2]*gurAG[3];
	        		gurAG[3]=0;
	        		gurATG[2]=gurATG[2]+gurAG[2];
	        		gurATG[3]=gurATG[3]+gurAG[3];
	        		
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
			        
			        gllAG[3]=heigthElement;
			        
			        //gll.add(gurA);
			        
	        		
	        		//new symmetry with areas
	        		gllAG[2]=gllAG[2]*gllAG[3];
	        		gllAG[3]=0;
	        		gllATG[2]=gllATG[2]+gllAG[2];
	        		gllATG[3]=gllATG[3]+gllAG[3];
			        
			        //second block of the element (low right)
			        x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassGeneral)/2;
	        		glrAG[0]=Math.abs(x-xCenterMassGeneral);
	        		glrATG[0]=glrATG[0]+glrAG[0];
			        glrAG[1]=Math.abs(y-yCenterMassGeneral);
			        glrATG[1]=glrATG[1]+glrAG[1];
			        glrAG[2]=(xInitial+widthElement)-xCenterMassGeneral;
			        
			        glrAG[3]=heigthElement;
			        
			        //gur.add(gurA);
			        
	        		
	        		//new symmetry with areas
	        		glrAG[2]=glrAG[2]*glrAG[3];
	        		glrAG[3]=0;
	        		glrATG[2]=glrATG[2]+glrAG[2];
	        		glrATG[3]=glrATG[3]+glrAG[3];
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
			        
			        gulAG[3]=yCenterMassGeneral-(yInitial-heigthElement);
			        			        
	        		
	        		//new symmetry with areas
	        		gulAG[2]=gulAG[2]*gulAG[3];
	        		gulAG[3]=0;
	        		gulATG[2]=gulATG[2]+gulAG[2];
	        		gulATG[3]=gulATG[3]+gulAG[3];
			        
	        		//second block of the element (up right)
	        		x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassGeneral)/2;
	        		y=(yInitial-heigthElement)+(yCenterMassGeneral-(yInitial-heigthElement))/2;
	        		
	        		gurAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gurATG[0]=gurATG[0]+gurAG[0];
			        gurAG[1]=Math.abs(y-yCenterMassGeneral);
			        gurATG[1]=gurATG[1]+gurAG[1];
			        gurAG[2]=(xInitial+widthElement)-xCenterMassGeneral;
			        
			        gurAG[3]=yCenterMassGeneral-(yInitial-heigthElement);
			        
	        		
	        		//new symmetry with areas
	        		gurAG[2]=gurAG[2]*gurAG[3];
	        		gurAG[3]=0;
	        		gurATG[2]=gurATG[2]+gurAG[2];
	        		gurATG[3]=gurATG[3]+gurAG[3];
	        		
	        		//first block of the element (low left)
	        		x=(xInitial+(xCenterMassGeneral-xInitial)/2);
	        		y=yInitial-(yInitial-yCenterMassGeneral)/2;
	        		
	        		gllAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gllATG[0]=gllATG[0]+gllAG[0];
			        gllAG[1]=Math.abs(y-yCenterMassGeneral);
			        gllATG[1]=gllATG[1]+gllAG[1];
			        gllAG[2]=(xCenterMassGeneral-xInitial);
			        
			        gllAG[3]=yInitial-yCenterMassGeneral;
			        			        
	        		
	        		//new symmetry with areas
	        		gllAG[2]=gllAG[2]*gllAG[3];
	        		gllAG[3]=0;
	        		gllATG[2]=gllATG[2]+gllAG[2];
	        		gllATG[3]=gllATG[3]+gllAG[3];
			        
	        		//second block of the element (low right)
	        		x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassGeneral)/2;
	        		y=yInitial-(yInitial-yCenterMassGeneral)/2;
	        		
	        		glrAG[0]=Math.abs(x-xCenterMassGeneral);
	        		glrATG[0]=glrATG[0]+glrAG[0];
			        glrAG[1]=Math.abs(y-yCenterMassGeneral);
			        glrATG[1]=glrATG[1]+glrAG[1];
			        glrAG[2]=(xInitial+widthElement)-xCenterMassGeneral;
			        
			        glrAG[3]=yInitial-yCenterMassGeneral;
			        
			        
	        		
	        		//new symmetry with areas
	        		glrAG[2]=glrAG[2]*glrAG[3];
	        		glrAG[3]=0;
	        		glrATG[2]=glrATG[2]+glrAG[2];
	        		glrATG[3]=glrATG[3]+glrAG[3];
	        	}
	        }
	        
		
		}
		System.out.println("gulAT "+gulATG[0]+" "+gulATG[1]+" "+gulATG[2]+" "+gulATG[3]);
		System.out.println("gurAT "+gurATG[0]+" "+gurATG[1]+" "+gurATG[2]+" "+gurATG[3]);
		System.out.println("gllAT "+gllATG[0]+" "+gllATG[1]+" "+gllATG[2]+" "+gllATG[3]);
		System.out.println("glrAT "+glrATG[0]+" "+glrATG[1]+" "+glrATG[2]+" "+glrATG[3]);
		rythmValueX=SubstractionRythm(gulATG,gurATG,0)+SubstractionRythm(gllATG,glrATG,0);
		rythmValueX=rythmValueX/states.size();
		System.out.println("rythmValueX "+rythmValueX);
		
		rythmValueY=SubstractionRythm(gulATG,gurATG,1)+SubstractionRythm(gllATG,glrATG,1);
		rythmValueY=rythmValueY/states.size();
		System.out.println("rythmValueY "+rythmValueY);
		
		rythmValueA=SubstractionRythm(gulATG,gurATG,2)+SubstractionRythm(gllATG,glrATG,2);
		rythmValueA=rythmValueA/(gulATG[2]+gurATG[2]+gllATG[2]+glrATG[2]);
		System.out.println("rythmValueA "+rythmValueA);
		//symmetryValueGeneral=SubstractionSymmetries(gulATG,gurATG)+SubstractionSymmetries(gllATG,glrATG);
		//System.out.println("symmetryValue "+symmetryValueGeneral);
		rythmValueGeneral=( Math.abs(rythmValueX)+Math.abs(rythmValueY)+Math.abs(rythmValueA) );
		return rythmValueGeneral;
	}
	

	@Override
	public double compute(Level toCompare) {
		return compute();
	}
		
}
