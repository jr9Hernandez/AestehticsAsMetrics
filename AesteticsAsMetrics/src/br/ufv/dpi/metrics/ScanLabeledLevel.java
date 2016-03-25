package br.ufv.dpi.metrics;

import java.util.ArrayList;

import beauty.Elements;
import graphBuilder.BlockNode;
import dk.itu.mario.level.Level;

public class ScanLabeledLevel {

    protected static final byte BLOCK_EMPTY	= (byte) (0 + 1 * 16);
    protected static final byte BLOCK_POWERUP	= (byte) (4 + 2 + 1 * 16);
    protected static final byte BLOCK_COIN	= (byte) (4 + 1 + 1 * 16);
    protected static final byte GROUND		= (byte) (1 + 9 * 16);
    protected static final byte ROCK			= (byte) (9 + 0 * 16);
    protected static final byte COIN			= (byte) (2 + 2 * 16);


    protected static final byte LEFT_GRASS_EDGE = (byte) (0+9*16);
    protected static final byte RIGHT_GRASS_EDGE = (byte) (2+9*16);
    protected static final byte RIGHT_UP_GRASS_EDGE = (byte) (2+8*16);
    protected static final byte LEFT_UP_GRASS_EDGE = (byte) (0+8*16);
    protected static final byte LEFT_POCKET_GRASS = (byte) (3+9*16);
    protected static final byte RIGHT_POCKET_GRASS = (byte) (3+8*16);

    protected static final byte HILL_FILL = (byte) (5 + 9 * 16);
    protected static final byte HILL_LEFT = (byte) (4 + 9 * 16);
    protected static final byte HILL_RIGHT = (byte) (6 + 9 * 16);
    protected static final byte HILL_TOP = (byte) (5 + 8 * 16);
    protected static final byte HILL_TOP_LEFT = (byte) (4 + 8 * 16);
    protected static final byte HILL_TOP_RIGHT = (byte) (6 + 8 * 16);

    protected static final byte HILL_TOP_LEFT_IN = (byte) (4 + 11 * 16);
    protected static final byte HILL_TOP_RIGHT_IN = (byte) (6 + 11 * 16);

    protected static final byte TUBE_TOP_LEFT = (byte) (10 + 0 * 16);
    protected static final byte TUBE_TOP_RIGHT = (byte) (11 + 0 * 16);

    protected static final byte TUBE_SIDE_LEFT = (byte) (10 + 1 * 16);
    protected static final byte TUBE_SIDE_RIGHT = (byte) (11 + 1 * 16);
	
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
		int limitBlocks=0;
		int limitBlueBlocks=0;
		int limitBrownBlocks=0;
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
					buildElement(i, j+heightElement-1, heightElement, widthElement,3);
					
				}
				
				//case of a gap!
				if ((j == (mapLevel[i].length) - 1) && (mapLevel[i][j] == (byte) (0)) && i>limitGap) {
					
					for (int k = i; k < width; k++)
					{
						if(mapLevel[k][j] != (byte) (0) )
						{
							
							limitGap=k-1;
							widthElement=k-i;
							break;
						}

					}
					//calculating the height
					
					heightElement=(mapLevel[i].length - 1)-SearchforFloorReverse(i-1,(mapLevel[i].length) - 1)+1;
					
					buildElement(i, 14, heightElement, widthElement,0);
					
				}
				
				//case of an external Hill!
				if(mapLevel[i][j] == HILL_TOP_LEFT)
				{
					//calculating the width
					
					for (int k = i; k < width; k++)
					{
						if(mapLevel[k][j] == HILL_TOP_RIGHT_IN || mapLevel[k][j] ==HILL_TOP_RIGHT)
						{
							widthElement=k-i+1;
							break;
						}

					}
					//calculating the height
					//heightElement=SearchforFloor(i,j)-j;
					heightElement=SearchforFloorReverse(i,(mapLevel[i].length) - 1)-j;
					
					buildElement(i, j+heightElement-1, heightElement, widthElement,2);
					
				}
				
				//case of an internal Hill!
				if(mapLevel[i][j] == HILL_TOP_LEFT_IN)
				{
					//calculating the width
					
					for (int k = i; k < width; k++)
					{
						if(mapLevel[k][j] == HILL_TOP_RIGHT_IN || mapLevel[k][j] ==HILL_TOP_RIGHT)
						{
							widthElement=k-i+1;
							break;
						}

					}
					//calculating the height
					
					//calculating the height
					//heightElement=SearchforFloor(i,j)-j;
					heightElement=SearchforFloorReverse(i,(mapLevel[i].length) - 1)-j;
					
					buildElement(i, j+heightElement-1, heightElement, widthElement,2);
					
				}
				
				//case of an floating Hill!
				if(mapLevel[i][j] == (byte) (0+8*16) && SearchforFloorReverse(i,(mapLevel[i].length) - 1)!= j)
				{
					//calculating the width
					
					for (int k = i; k < width; k++)
					{
						if(mapLevel[k][j] == (byte) (2+8*16))
						{
							widthElement=k-i+1;
							break;
						}

					}
					//calculating the height
					heightElement=SearchforFloorFloatMountain(i,j)-j+1;
						//heightElement=SearchforFloorReverse(i,(mapLevel[i].length) - 1)-j;
					
					buildElement(i, j+heightElement-1, heightElement, widthElement,7);
					
				}
				//case of blocks!
				if((mapLevel[i][j] == BLOCK_POWERUP || mapLevel[i][j] == BLOCK_COIN || mapLevel[i][j] == (byte) (2 + 1 * 16) || mapLevel[i][j] == (byte) (1 + 1 * 16)|| mapLevel[i][j] == (byte) (0 + 1 * 16)) && i>limitBlocks)
				{
					//calculating the width
					
					for (int k = i; k < width; k++)
					{
						if(mapLevel[k][j] != BLOCK_POWERUP && mapLevel[k][j] != BLOCK_COIN && mapLevel[k][j] != (byte) (2 + 1 * 16) && mapLevel[k][j] != (byte) (1 + 1 * 16) && mapLevel[k][j] != (byte) (0 + 1 * 16))
						{
							limitBlocks=k-1;
							widthElement=k-i;
							break;
						}

					}
					//calculating the height
					heightElement=1;
					
					buildElement(i, j, heightElement, widthElement,8);
					
				}	
				
				//case of blue blocks!
				if((mapLevel[i][j] == (byte) 28) && i>limitBlueBlocks)
				{
					//calculating the width
					
					for (int k = i; k < width; k++)
					{
						if(mapLevel[k][j] != (byte) 28)
						{
							limitBlueBlocks=k-1;
							widthElement=k-i;
							break;
						}

					}
					//calculating the height
					heightElement=1;
					
					buildElement(i, j, heightElement, widthElement,10);
					
				}
				
				//case of brown blocks!
				if((mapLevel[i][j] == (byte) 12) && i>limitBrownBlocks)
				{
					//calculating the width
					
					for (int k = i; k < width; k++)
					{
						if(mapLevel[k][j] != (byte) 12)
						{
							limitBrownBlocks=k-1;
							widthElement=k-i;
							break;
						}

					}
					//calculating the height
					heightElement=1;
					
					buildElement(i, j, heightElement, widthElement,11);
					
				}
				
				//case of small tube!
				if((mapLevel[i][j] == (byte) 24) )
				{
					//calculating the width
					
					//for (int k = i; k < width; k++)
					//{
						//if(mapLevel[k][j] != (byte) 12)
						//{
							//limitBrownBlocks=k-1;
							widthElement=1;
							//break;
						//}

					//}
					//calculating the height
					heightElement=3;
					
					buildElement(i, j+2, heightElement, widthElement,6);
					
				}
				
				//case of wood!
				if((mapLevel[i][j] == (byte) 25) )
				{
					//calculating the width
					
					//for (int k = i; k < width; k++)
					//{
						//if(mapLevel[k][j] != (byte) 12)
						//{
							//limitBrownBlocks=k-1;
							widthElement=1;
							//break;
						//}

					//}
					//calculating the height
					heightElement=3;
					
					buildElement(i, j+2, heightElement, widthElement,6);
					
				}
				
				
			}
		}
		return elementsSelected;
	}
	public int SearchforFloorFloatMountain(int iInitial,int jInitial)
	{
		for (int j = jInitial; j < mapLevel[iInitial].length; j++) {
			if (mapLevel[iInitial][j] != (byte) (0+9*16) && mapLevel[iInitial][j] != (byte) (0+8*16) ) {// RIGHT_UP_GRASS_EDGE
				return j;				
			}			
			
		}
		return -1;
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
			if (mapLevel[iInitial][j] == (byte) (0)) {// gap
				return -1;				
			}
			else if (mapLevel[iInitial][j] == (byte) (2+8*16)) {// RIGHT_UP_GRASS_EDGE
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
