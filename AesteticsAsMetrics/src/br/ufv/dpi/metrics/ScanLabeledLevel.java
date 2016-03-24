package br.ufv.dpi.metrics;

import java.util.ArrayList;

import beauty.Elements;
import graphBuilder.BlockNode;
import dk.itu.mario.level.Level;

public class ScanLabeledLevel {

	protected int width;
	protected int height;
	protected Level level;
	protected byte[][] mapLevel;
	private ArrayList<BlockNode>  elementsSelected;
	private int counterGral;

	public ScanLabeledLevel(Level level, int width, int height)
	{
		this.level=level;
		this.width=width;
		this.height=height;
		mapLevel=level.getMap();
		elementsSelected=new ArrayList<BlockNode>();
		counterGral=0;
	}
	public ArrayList<BlockNode> DeterminePositions()
	{
		int limitGap=0;
		for (int i = 0; i < width; i++) {
			int counterHills = 0;
			
			for (int j = 0; j < mapLevel[i].length; j++) {

				int widthElement=0;
				int heightElement=0;
				
				//case of a pipe!
				if(mapLevel[i][j] == (byte) (10 + 0 * 16) )
				{
					//calculating the width
					
					for (int k = i; k < width; k++)
					{
						if(mapLevel[k][j] == (byte) (11 + 0 * 16) )
						{
							widthElement=k-i+1;
							break;
						}

					}
					//calculating the height
					if(SearchforFloor(i,j)!=-1)
					{
						heightElement=SearchforFloor(i,j)-j;
					}
					else
					{
						heightElement=-1;
					}	
					buildElement(i, j+heightElement-1, heightElement, widthElement,4);
					
				}
				
				//case of a canion!
				if(mapLevel[i][j] == (byte) (byte) (14 + 0 * 16) )
				{
					//calculating the width
					
					for (int k = i; k < width; k++)
					{
						if(mapLevel[k][j] == (byte) (14 + 0 * 16) )
						{
							widthElement=k-i+1;
							break;
						}

					}
					//calculating the height
					if(SearchforFloor(i,j)!=-1)
					{
						heightElement=SearchforFloor(i,j)-j;
					}
					else
					{
						heightElement=-1;
					}	
					buildElement(i, j+heightElement-1, heightElement, widthElement,4);
					
				}
				
				//case of a gap!
				if ((j == (mapLevel[i].length) - 1) && (mapLevel[i][j] == (byte) (0)) && i>limitGap) {
					
					for (int k = i; k < width; k++)
					{
						if(mapLevel[k][j] != (byte) (0) )
						{
							
							limitGap=k;
							widthElement=k-i;
							break;
						}

					}
					//calculating the height
					
					heightElement=(mapLevel[i].length - 1)-SearchforFloorReverse(i-1,(mapLevel[i].length) - 1)+1;
					
					buildElement(i, j+heightElement-1, heightElement, widthElement,0);
					
				}
				
				
				
			}
		}
		return elementsSelected;
	}
	public int SearchforFloor(int iInitial,int jInitial)
	{
		for (int j = jInitial; j < mapLevel[iInitial].length; j++) {
			if (mapLevel[iInitial][j] == (byte) (2+8*16)) {// RIGHT_UP_GRASS_EDGE
				return j;				
			}
			else if (mapLevel[iInitial][j] == (byte) (0+8*16)) {// LEFT_UP_GRASS_EDGE
				return j;				
			}
			else if (mapLevel[iInitial][j] == (byte) (5 + 8 * 16)) {// HILL_TOP
				return j;
			}
			else if (mapLevel[iInitial][j] == (byte) (4 + 8 * 16)) {//HILL_TOP_LEFT
				return j;				
			}
			else if (mapLevel[iInitial][j] == (byte) (6 + 8 * 16)) {// HILL_TOP_RIGHT
				return j;				
			}
			else if (mapLevel[iInitial][j] == (byte) (4 + 11 * 16)) {// HILL_TOP_LEFT_IN
				return j;				
			}
			else if (mapLevel[iInitial][j] == (byte) (6 + 11 * 16)) {// HILL_TOP_RIGHT_IN
				return j;
			}
			else if (mapLevel[iInitial][j] == (byte) (1 + 8 * 16)) {// HILL_TOP_W
				return j;
			}
			
		}
		return -1;
	}
	public int SearchforFloorReverse(int iInitial,int jInitial)
	{
		for (int j = jInitial; j > 0; j--) {
			if (mapLevel[iInitial][j] == (byte) (2+8*16)) {// RIGHT_UP_GRASS_EDGE
				return j;				
			}
			else if (mapLevel[iInitial][j] == (byte) (0+8*16)) {// LEFT_UP_GRASS_EDGE
				return j;				
			}
			else if (mapLevel[iInitial][j] == (byte) (5 + 8 * 16)) {// HILL_TOP
				return j;
			}
			else if (mapLevel[iInitial][j] == (byte) (4 + 8 * 16)) {//HILL_TOP_LEFT
				return j;				
			}
			else if (mapLevel[iInitial][j] == (byte) (6 + 8 * 16)) {// HILL_TOP_RIGHT
				return j;				
			}
			else if (mapLevel[iInitial][j] == (byte) (4 + 11 * 16)) {// HILL_TOP_LEFT_IN
				return j;				
			}
			else if (mapLevel[iInitial][j] == (byte) (6 + 11 * 16)) {// HILL_TOP_RIGHT_IN
				return j;
			}
			else if (mapLevel[iInitial][j] == (byte) (1 + 8 * 16)) {// HILL_TOP_W
				return j;
			}
			
		}
		return -1;
	}
	public void buildElement(int x,int y,int height, int width,int typeElement)
	{
		Elements newElement = new Elements();
		newElement.setIdElem(counterGral);
		counterGral++;
		newElement.setHeigth(height);
		newElement.setWidth(width);
		newElement.setTypeElem(typeElement);
		
		BlockNode objBlockNode2=new BlockNode(x,y,counterGral,typeElement,newElement);
		elementsSelected.add(objBlockNode2);
		
	}
}
