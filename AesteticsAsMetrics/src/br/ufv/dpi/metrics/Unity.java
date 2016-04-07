package br.ufv.dpi.metrics;

import graphBuilder.BlockNode;

import java.util.ArrayList;
import java.util.Iterator;

import beauty.Elements;
import beauty.ElementsToPlace;
import dk.itu.mario.engine.sprites.SpriteTemplate;
import dk.itu.mario.level.Level;

public class Unity extends Metrics  {
	

	private ArrayList<BlockNode>  elementsSelected;
	private int numElements;
	private int numDifferentSizes;
	private double UMForm;
	private double UMSpace;
	private double UM;
	private double totalAreas;
	
	private int moreLeft;
	private int moreRight;
	private int moreUp;
	private int moreLow;
	private int[][] hotZones;
	private int totalAreaLayout;
	
	public Unity(int width, int height, Level level) 
	{
		super(width, height, level);
	}
	
	@Override
	public double compute() {
		
		//calculation of scanning of level
		ScanLabeledLevel objScanLevel=new ScanLabeledLevel(level,width, height );
		elementsSelected=objScanLevel.DeterminePositions();
		hotZones=objScanLevel.hotZones(elementsSelected);
		
		//calculate of nsize, n and sum_ai
		CalculateAreas(elementsSelected);

		//calculate UFForm
		UMForm=1-((numDifferentSizes-1)/numElements);
		
		//calculate AreaLayout->moreLeft, moreRight, moreLow, moreUp
		AreaLayout(elementsSelected);
		
		//testing values
		/*System.out.println("numElements "+numElements);
		System.out.println("numDifferentSizes "+numDifferentSizes);
		System.out.println("totalAreaLayout "+totalAreaLayout);
		System.out.println("totalAreas "+totalAreas);*/

		
		//calculate UMSpace
		double areaFrame=height*(level.getxExit()-18);
		UMSpace=1-((totalAreaLayout-totalAreas)/(areaFrame-totalAreas));
		
		//calculate UM
		UM=(Math.abs(UMForm)+Math.abs(UMSpace))/2;
				
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
		return UM;
	}
	
	public void CalculateAreas(ArrayList<BlockNode>  elementsSelected)
	{
		totalAreas=0;
		numDifferentSizes=0;
		numElements=elementsSelected.size();
		
		Iterator<BlockNode> it = elementsSelected.iterator();
		while(it.hasNext()){
			BlockNode elemento = it.next();
			
			int xInitial = elemento.getX();
	        int yInitial= elemento.getY();
	        int widthElement=(elemento.getElement()).getWidth();
	        int heigthElement=(elemento.getElement()).getHeigth();	        
	        int typeElement=(elemento.getElement()).getTypeElem();
	        int idElement=(elemento.getElement()).getIdElem();
	        
	        //calculate the totalareas of the objects
	        
	        double partialArea=widthElement*heigthElement;
	        totalAreas=totalAreas+partialArea;
	        
	        int counterElements=idElement;
			for(int i=0;i<idElement;i++)
			{
				
				
				BlockNode elemento2 = elementsSelected.get(i);
				
				int xInitial2 = elemento2.getX();
		        int yInitial2= elemento2.getY();
		        int widthElement2=(elemento2.getElement()).getWidth();
		        int heigthElement2=(elemento2.getElement()).getHeigth();	        
		        int typeElement2=(elemento2.getElement()).getTypeElem();
		        int idElement2=(elemento2.getElement()).getIdElem();
		        
		        if(widthElement!=widthElement2 	|| heigthElement!=heigthElement2)
		        {
		        	counterElements--;
		        }
			}
			
			if(counterElements==0)
			{
				numDifferentSizes++;
			}
	        
		}
		//System.out.println(" Areastotais "+totalAreas);
		//System.out.println(" numDifferentSizes "+numDifferentSizes);
	}
	
	public void AreaLayout(ArrayList<BlockNode>  elementsSelected)
	{
		moreLeft=9999;
		moreRight=0;
		moreUp=9999;
		moreLow=0;
		
		for(int i=0;i<width;i++)
		{
			for(int j=0;j<height;j++)
			{
				if(hotZones[i][j]!=0)
				{
					if(i<moreLeft)
					{
						moreLeft=i;
					}
					if(i>moreRight)
					{
						moreRight=i;
					}
					if(j<moreUp)
					{
						moreUp=j;
					}
					if(j>moreLow)
					{
						moreLow=j;
					}
				}
			}
		}
		
		System.out.println("moreLeft "+moreLeft);
		System.out.println("moreRight "+moreRight);
		System.out.println("moreUp "+moreUp);
		System.out.println("moreLow "+moreLow);
		
		//13 is because 5 from the start piece, and 3 from the final piece
		totalAreaLayout=(moreUp*(level.getxExit()-18)) + ((height-moreLow-1)*(level.getxExit()-18))  + ((moreLeft-10)*height) + ( ((level.getxExit()-9)-moreRight)*height  );
		//System.out.println("level.getxExit()-8 "+(level.getxExit()-8));
		
	}
	
	

	@Override
	public double compute(Level toCompare) {
		return compute();
	}
		
}