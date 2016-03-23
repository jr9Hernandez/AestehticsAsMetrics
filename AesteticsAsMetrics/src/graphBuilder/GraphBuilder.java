/* ==========================================
 * JGraphT : a free Java graph-theory library
 * ==========================================
 *
 * Project Info:  http://jgrapht.sourceforge.net/
 * Project Creator:  Barak Naveh (http://sourceforge.net/users/barak_naveh)
 *
 * (C) Copyright 2003-2008, by Barak Naveh and Contributors.
 *
 * This program and the accompanying materials are dual-licensed under
 * either
 *
 * (a) the terms of the GNU Lesser General Public License version 2.1
 * as published by the Free Software Foundation, or (at your option) any
 * later version.
 *
 * or (per the licensee's choosing)
 *
 * (b) the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation.
 */
/* -----------------
 * HelloJGraphT.java
 * -----------------
 * (C) Copyright 2003-2008, by Barak Naveh and Contributors.
 *
 * Original Author:  Barak Naveh
 * Contributor(s):   -
 *
 * $Id$
 *
 * Changes
 * -------
 * 27-Jul-2003 : Initial revision (BN);
 *
 */
package graphBuilder;

import java.net.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;



import beauty.ConstraintsPlacement;
import beauty.Elements;
import beauty.ElementsToPlace;
import dk.itu.mario.level.Level;


/**
 * A simple introduction to using JGraphT.
 *
 * @author Barak Naveh
 * @since Jul 27, 2003
 */
public class GraphBuilder
{

	private int counterIDs;
	private int counterBranches;
	private ArrayList<BlockNode> Beststates;
	private ArrayList<BlockNode> Worststates;
	private Branch BeststatesObj;
	private Branch WorststatesObj;
	private ArrayList bestBranches;
	private double xCenterMassGeneral;
	private double yCenterMassGeneral;
	private double xCenterMassCoins;
	private double yCenterMassCoins;
	private double symmetryV;
	private double bestSymmetryV=9999999;
	private double worstSymmetryV=0;
	private double bestAverageX=0;
	//private ArrayList <double[]> gul;
	//private ArrayList <double[]> gur;
	//private ArrayList <double[]> gll;
	//private ArrayList <double[]> glr;
	private double [] gulAG;
	private double [] gurAG;
	private double [] gllAG;
	private double [] glrAG;
	
	private double [] gulAC;
	private double [] gurAC;
	private double [] gllAC;
	private double [] glrAC;
	
	
	private int localMaxObjLeft;
	private int localMaxObjRight;
	private int localMaxRight;
	private int localMaxLeft;

	
	private TreeSet localcurrentState;
	//Iterator<Integer> finalListIterator;
	
    public GraphBuilder(int counterIDs)
    {
    	bestBranches = new ArrayList<Branch>();
    	this.counterIDs=counterIDs;
    	//finalListIterator = finalList.iterator();
    } // ensure non-instantiability.

    



    /**
     * Creates a toy directed graph based on URL objects that represents link
     * structure.
     *
     * @return a graph based on URL objects.
     */
    /*private static DirectedGraph<URL, DefaultEdge> createHrefGraph()
    {
        DirectedGraph<URL, DefaultEdge> g =
            new DefaultDirectedGraph<URL, DefaultEdge>(DefaultEdge.class);

        try {
            URL amazon = new URL("http://www.amazon.com");
            URL yahoo = new URL("http://www.yahoo.com");
            URL ebay = new URL("http://www.ebay.com");

            // add the vertices
            g.addVertex(amazon);
            g.addVertex(yahoo);
            g.addVertex(ebay);

            // add edges to create linking structure
            g.addEdge(yahoo, amazon);
            g.addEdge(yahoo, ebay);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return g;
    }*/

    /**
     * Craete a toy graph based on String objects.
     *
     * @return a graph based on String objects.
     */
    /*private static UndirectedGraph<String, DefaultEdge> createStringGraph()
    {
        UndirectedGraph<String, DefaultEdge> g =
            new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);

        String v1 = "v1";
        String v2 = "v2";
        String v3 = "v3";
        String v4 = "v4";

        // add the vertices
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);

        // add edges to create a circuit
        g.addEdge(v1, v2);
        g.addEdge(v2, v3);
        g.addEdge(v3, v4);
        g.addEdge(v4, v1);

        return g;
    }*/
 
    
    /**
     * Craete a toy graph based on BlockNode objects.
     *
     * @return a graph based on BlockNode objects.
     */
   /* private static UndirectedGraph<BlockNode, DefaultEdge> createBlockNodeGraph()
    {
        UndirectedGraph<BlockNode, DefaultEdge> g =
            new SimpleGraph<BlockNode, DefaultEdge>(DefaultEdge.class);

        BlockNode objBlockNode1=new BlockNode(1,3);
        BlockNode objBlockNode2=new BlockNode(2,3);
        BlockNode objBlockNode3=new BlockNode(3,3);
        BlockNode objBlockNode4=new BlockNode(4,3);
        BlockNode objBlockNode5=new BlockNode(5,3);
        BlockNode objBlockNode6=new BlockNode(6,3);
        BlockNode objBlockNode7=new BlockNode(7,3);
        BlockNode objBlockNode8=new BlockNode(8,3);
        BlockNode objBlockNode9=new BlockNode(9,3);
        BlockNode objBlockNode10=new BlockNode(10,3);
        BlockNode objBlockNode16=new BlockNode(16,3);

        // add the vertices
        g.addVertex(objBlockNode1);
        g.addVertex(objBlockNode2);
        g.addVertex(objBlockNode3);
        g.addVertex(objBlockNode4);
        g.addVertex(objBlockNode5);
        g.addVertex(objBlockNode6);
        g.addVertex(objBlockNode7);
        g.addVertex(objBlockNode8);
        g.addVertex(objBlockNode9);
        g.addVertex(objBlockNode10);
        g.addVertex(objBlockNode16);

        // add edges to create a circuit
        g.addEdge(objBlockNode1, objBlockNode2);
        g.addEdge(objBlockNode1, objBlockNode3);
        g.addEdge(objBlockNode1, objBlockNode4);
        g.addEdge(objBlockNode2, objBlockNode5);
        g.addEdge(objBlockNode2, objBlockNode6);
        g.addEdge(objBlockNode3, objBlockNode8);
        g.addEdge(objBlockNode4, objBlockNode9);
        g.addEdge(objBlockNode4, objBlockNode10);
        g.addEdge(objBlockNode5, objBlockNode7);
        g.addEdge(objBlockNode5, objBlockNode16);

        GraphIterator<BlockNode, DefaultEdge> iterator = 
                new DepthFirstIterator<BlockNode, DefaultEdge>(g);
        while (iterator.hasNext()) {
            System.out.println( iterator.next().getX() );
           
        }
        return g;
    }*/
    
    public ArrayList  relativeTransPositionDepthSearch(int width,int height,   int countElements, int countElementsFinal,ArrayList states, ConstraintsPlacement objConstraints, ArrayList finalList, ElementsToPlace objElemP,int maxLeft, int maxRight,int floorTileHeight, int maxObjLeft, int maxObjRight, TreeSet currentState, Hashtable hTable)
    {    
    	
    	if(countElements==countElementsFinal)
    	{
    		countElements--;
    		counterIDs=counterIDs+1;
    		Elements objElem= (Elements)finalList.get(0);
        	int idElem=objElem.getIdElem();
        	int typeElem=objElem.getTypeElem();
        	int widthElem=objElem.getWidth();
        	int heigthElem=objElem.getHeigth();
    		BlockNode objBlockNode2=new BlockNode(0,floorTileHeight-1,counterIDs,typeElem,idElem);
    		states.add(objBlockNode2);
    		currentState=UpdateStringState(typeElem,widthElem,heigthElem,0,floorTileHeight-1,currentState);
    		hTable=UpdateTranspositionTable(hTable,currentState);
    		maxLeft=maxLeft+objElem.getWidth()-1;
    		maxObjRight=maxObjRight+objElem.getWidth()-1;
    	}
    	
    	countElements--;
    	Elements objElem= (Elements)finalList.get(countElementsFinal-countElements-1);
    	int idElem=objElem.getIdElem();
    	int typeElem=objElem.getTypeElem(); 
    	int widthElem=objElem.getWidth();
    	int heigthElem=objElem.getHeigth();
  
    	
    	for(int i=maxLeft;i<=maxRight;i++)
    	{
    	 for(int j=0;j<height;j++)
    	 {  
    		 if(typeElem==objElemP.getOddsCannons() || typeElem==objElemP.getOddsTubes())
    		 {    			
    			
    			if(objConstraints.ConstraintsOverlaid(states, i, j, finalList,objElemP)==false)
     		 	{
     			 	
    				//System.out.println("falseadoo");
     			 	continue;
     		 	}
    			else if(objConstraints.ConstraintsWidthMaxTileRelative(states, i, j, finalList, objElem.getWidth(),maxRight)==false)
    			{ 
    				
    				continue;
    			}
    			else if(objConstraints.ConstraintsFloorRelative(states, i, j, finalList,floorTileHeight,objElemP)==false)
    			{ 
    				
    				continue;
    			}
    		 }
    		 else if(typeElem==objElemP.getBlockElement())
    		 {
    			 if(objConstraints.ConstraintsOverlaid(states, i, j, finalList,objElemP)==false)
       		 	{
       			 	
      				//System.out.println("falseadoo");
       			 	continue;
       		 	}
    			else if(objConstraints.ConstraintsWidthMaxTileRelative(states, i, j, finalList, objElem.getWidth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsFloorFloatingsRelative(states, i, j, finalList,floorTileHeight,objElemP)==false)
     			{ 
     				
     				continue;
     			}
    			
    			 /*if(objConstraints.ConstraintsPosibleGoalJump(i, j)==false)
    			 {
    				 continue;
    			 }*/
    		 }
    		     	
    		 
    		 if(objConstraints.StateRepeated(typeElem,widthElem,heigthElem,i,j,currentState,hTable)==true)
    		 {
    			 continue;
    		 }
    		 
    		counterIDs=counterIDs+1;
    		BlockNode objBlockNode2=new BlockNode(i,j,counterIDs,typeElem,idElem);
    		states.add(objBlockNode2);
    		localcurrentState=UpdateStringState(typeElem,widthElem,heigthElem,i,j,currentState);
    		
    		if(counterIDs<10000)
    		{
    			    		
    			hTable=UpdateTranspositionTable(hTable,localcurrentState);
    		}
    		
    		if(countElements>0)
    		{
    			localMaxObjLeft=maxObjLeft;
    			localMaxObjRight=maxObjRight;
    			localMaxRight=maxRight;
    			localMaxLeft=maxLeft;
    			if(i<maxObjLeft)
    			{
    				int tempMaxObjLeft=maxObjLeft;
    				localMaxObjLeft=i;
    				localMaxRight=maxRight+(localMaxObjLeft-tempMaxObjLeft);
    			}
    			else if(i+objElem.getWidth()-1>maxObjRight)
    			{
    				int tempMaxObjRight=maxObjRight;
    				localMaxObjRight=i+objElem.getWidth()-1;
    				localMaxLeft=maxLeft+(localMaxObjRight-tempMaxObjRight);
    				//maxLeft=maxLeft+objElem.getWidth()-1;
    			}
    			
    			
    			relativeTransPositionDepthSearch(width,height, countElements,countElementsFinal,states,objConstraints,finalList,objElemP,localMaxLeft,localMaxRight,floorTileHeight,localMaxObjLeft,localMaxObjRight,localcurrentState,hTable);
    		}
    		else{
    			//System.out.println("aca se debe calcular la formula");
    			validateBestBranch(states,objElemP,height,floorTileHeight,localMaxObjLeft);
    			 
    		}
    		//System.out.println("Aqui deberia eliminar del array");
    		states.remove(states.size() - 1);
    		 
    	 }
    	}
    	return Beststates;
    } 


	public ArrayList  relativePositionDepthSearch(int width,int height,   int countElements, int countElementsFinal,ArrayList states, ConstraintsPlacement objConstraints, ArrayList finalList, ElementsToPlace objElemP,int maxLeft, int maxRight,int floorTileHeight, int maxObjLeft, int maxObjRight, int numEnemies, Random random, int globalControlSearch)
    {    
    	if(countElements==countElementsFinal)
    	{
    		countElements--;
    		counterIDs=counterIDs+1;
    		Elements objElem= (Elements)finalList.get(0);
        	int idElem=objElem.getIdElem();
        	int typeElem=objElem.getTypeElem();
        	if(typeElem==objElemP.getOddsJump())
        	{
        		BlockNode objBlockNode2=new BlockNode(0,height-1,counterIDs,typeElem,idElem);
        		states.add(objBlockNode2);
        	}
        	else
        	{
        		BlockNode objBlockNode2=new BlockNode(0,floorTileHeight-1,counterIDs,typeElem,idElem);
        		states.add(objBlockNode2);
        	}    		
    		maxLeft=maxLeft+objElem.getWidth()-1;
    		maxObjRight=maxObjRight+objElem.getWidth()-1;
    	}
    	
    	countElements--;
    	Elements objElem= (Elements)finalList.get(countElementsFinal-countElements-1);
    	int idElem=objElem.getIdElem();
    	int typeElem=objElem.getTypeElem(); 
    	
    	
    	
    	for(int i=maxLeft;i<=maxRight;i++)
    	{
    	 for(int j=(height/3);j<height;j++)
    	 {     		 
    		 if(objElem.getIdElem()==1)
    		 {
    			 
    			 double widthZeroA=(double)(((Elements)finalList.get(0)).getWidth());
    			 double widthZero= widthZeroA/2;
    			
    			 if(widthZero!=(int)widthZero)
    			 {
    				 widthZero=widthZero+0.5; 
    			 }
    			
    			 if(i>widthZero-1)
    			 {
    				 
    				 continue;
    			 }
    		 }
    		 
    		 if(typeElem==objElemP.getOddsCannons() || typeElem==objElemP.getOddsTubes() || typeElem==objElemP.getTubesFlower())
    		 {    			
    			if(objConstraints.ConstraintsOverlaid(states, i, j, finalList,objElemP)==false)
     		 	{
     			 	
    				//System.out.println("falseadoo");
     			 	continue;
     		 	}
    			else if(objElem.getWidth()==1 && objConstraints.ConstraintsMinWidth(states, i, j, finalList,objElemP)==false)
      			{
      				continue;
      			}
    			else if(objConstraints.ConstraintsWidthMaxTileRelative(states, i, j, finalList, objElem.getWidth(),maxRight)==false)
    			{ 
    				
    				continue;
    			}
    			else if(objConstraints.ConstraintsHeightMaxTileRelative(states, i, j, finalList, objElem.getHeigth(),maxRight)==false)
    			{ 
    				
    				continue;
    			}
    			else if(objConstraints.ConstraintsFloorRelative(states, i, j, finalList,floorTileHeight,objElemP)==false)
    			{ 
    				
    				continue;
    			}
    			else if(objConstraints.ConstraintsMinSpace(states, i, j, finalList,objElemP)==false)
     		 	{
     			 	
    				//System.out.println("falseadoo");
     			 	continue;
     		 	}
    		 }
    		 else if( typeElem==objElemP.getOddsHillStraightFloat())
    		 {
    			 if(objConstraints.ConstraintsOverlaid(states, i, j, finalList,objElemP)==false)
        		 	{
        			 	
       				//System.out.println("falseadoo");
        			 	continue;
        		 	}
     			else if(objConstraints.ConstraintsWidthMaxTileRelative(states, i, j, finalList, objElem.getWidth(),maxRight)==false)
      			{ 
      				
      				continue;
      			}
     			else if(objConstraints.ConstraintsHeightMaxTileRelative(states, i, j, finalList, objElem.getHeigth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
     			 else if(objConstraints.ConstraintsFloorFloatingsHillsRelative(states, i, j, finalList,floorTileHeight,objElemP)==false)
      			{ 
      				
      				continue;
      			}
     			 else if(objConstraints.ConstraintsMinSpaceHillFloat(states, i, j, finalList,objElemP,floorTileHeight)==false)
       		 	{
       			 	
      				//System.out.println("falseadoo");
       			 	continue;
       		 	}
     			 /*if(objConstraints.ConstraintsPosibleGoalJump(i, j)==false)
     			 {
     				 continue;
     			 }*/   			 
    		 }
    		 else if(typeElem==objElemP.getBlockElement() || typeElem==objElemP.getCoins() || typeElem==objElemP.getBlockBlue() || typeElem==objElemP.getBlockWood() || typeElem==objElemP.getSmallTube() || typeElem==objElemP.getWood() )
    		 {
    			 if(objConstraints.ConstraintsOverlaid(states, i, j, finalList,objElemP)==false)
       		 	{
       			 	
      				//System.out.println("falseadoo");
       			 	continue;
       		 	}
    			else if(objConstraints.ConstraintsWidthMaxTileRelative(states, i, j, finalList, objElem.getWidth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
    			else if(objConstraints.ConstraintsHeightMaxTileRelative(states, i, j, finalList, objElem.getHeigth(),maxRight)==false)
    			{ 
    				
    				continue;
    			}
    			 else if(objConstraints.ConstraintsFloorFloatingsRelative(states, i, j, finalList,floorTileHeight,objElemP)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsMinSpaceFloat(states, i, j, finalList,objElemP,floorTileHeight)==false && (typeElem==objElemP.getBlockElement() || typeElem==objElemP.getBlockBlue() || typeElem==objElemP.getBlockWood() || typeElem==objElemP.getSmallTube() || typeElem==objElemP.getWood()))
      		 	{
      			 	
     				//System.out.println("falseadoo");
      			 	continue;
      		 	}
    			 else if(objConstraints.ConstraintsMinSpaceCoins(states, i, j, finalList,objElemP)==false && typeElem==objElemP.getCoins())
        		 	{
        			 	
       				//System.out.println("falseadoo");
        			 	continue;
        		 	}
    			 /*if(objConstraints.ConstraintsPosibleGoalJump(i, j)==false)
    			 {
    				 continue;
    			 }*/
    		 }
    		 else if(typeElem==objElemP.getOddsJump())
    		 {
    			
    			if(objConstraints.ConstraintsWidthMaxTileRelative(states, i, j, finalList, objElem.getWidth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsFloorGapsRelative(i, j)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsMinSpace(states, i, j, finalList,objElemP)==false)
      		 	{
      			 	
     				//System.out.println("falseadoo");
      			 	continue;
      		 	}
    			
    			 /*if(objConstraints.ConstraintsPosibleGoalJump(i, j)==false)
    			 {
    				 continue;
    			 }*/
    		 }
    		 else if(typeElem==objElemP.getOddsHillStraight())
    		 {
    			 if(objConstraints.ConstraintsOverlaidHills(states, i, j, finalList, objElemP)==false)
      		 	{      			 	
     				//System.out.println("falseadoo");
      			 	continue;
      		 	}
    			 else if(objConstraints.ConstraintsWidthMaxTileRelative(states, i, j, finalList, objElem.getWidth(),maxRight)==false)
      			{ 
      				
      				continue;
      			}
    			 else if(objConstraints.ConstraintsHeightMaxTileRelative(states, i, j, finalList, objElem.getHeigth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsFloorRelative(states, i, j, finalList,floorTileHeight,objElemP)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsMinSpaceHills(states, i, j, finalList,objElemP)==false)
      		 	{
      			 	
     				//System.out.println("falseadoo");
      			 	continue;
      		 	}
    		 }
    		 else if(typeElem==objElemP.getEnemyRedKoopa() || typeElem==objElemP.getEnemyGreenKoopa() || typeElem==objElemP.getEnemyGoomba() || typeElem==objElemP.getEnemySpiky() || typeElem==objElemP.getEnemyFlower() || typeElem==objElemP.getEnemyArmoredTurtle() || typeElem==objElemP.getEnemyJumpFlower() || typeElem==objElemP.getEnemyCannonBall() || typeElem==objElemP.getEnemyChompFlower())
    		 {
     			if(objConstraints.ConstraintsOverlaidHills(states, i, j, finalList,objElemP)==false)
     		 	{
     			 	
    				//System.out.println("falseadoo");
     			 	continue;
     		 	}
    			else if(objConstraints.ConstraintsFloorEnemiesRelative(states, i, j, finalList,floorTileHeight,objElemP)==false)
    			{ 
    				
    				continue;
    			}
    		 }
    		 
    		counterIDs=counterIDs+1;
    		BlockNode objBlockNode2=new BlockNode(i,j,counterIDs,typeElem,idElem);
    		states.add(objBlockNode2);
    		
			localMaxObjLeft=maxObjLeft;
			localMaxObjRight=maxObjRight;
			localMaxRight=maxRight;
			localMaxLeft=maxLeft;
			if(i<maxObjLeft)
			{
				int tempMaxObjLeft=maxObjLeft;
				localMaxObjLeft=i;
				localMaxRight=maxRight+(localMaxObjLeft-tempMaxObjLeft);
			}
			else if(i+objElem.getWidth()-1>maxObjRight)
			{
				int tempMaxObjRight=maxObjRight;
				localMaxObjRight=i+objElem.getWidth()-1;
				localMaxLeft=maxLeft+(localMaxObjRight-tempMaxObjRight);
				//maxLeft=maxLeft+objElem.getWidth()-1;
			}
    		if(countElements>0)
    		{    			
    			relativePositionDepthSearch(width,height, countElements,countElementsFinal,states,objConstraints,finalList,objElemP,localMaxLeft,localMaxRight,floorTileHeight,localMaxObjLeft,localMaxObjRight,numEnemies,random,globalControlSearch+1);
    		}
    		else{
    			//System.out.println("aca se debe calcular la formula");
    			validateBestBranch(states,objElemP,height,floorTileHeight,localMaxObjLeft);
    			 
    		}
    		//System.out.println("Aqui deberia eliminar del array");
    		states.remove(states.size() - 1);
    		 
    	 }
    	}
    	//putting enemies
    	if(globalControlSearch==0)
    	{
    	for(int i=0;i<numEnemies;i++)
    	{
    		countElements--;
        	Elements objElemEnem= (Elements)finalList.get(countElementsFinal+i);
        	int idElemEnem=objElemEnem.getIdElem();
        	int typeElemEnem=objElemEnem.getTypeElem(); 
        	
        	counterIDs=counterIDs+1;
    		BlockNode objBlockNode2=new BlockNode(RandomCoordenateGenerator(random,0,width-1),RandomCoordenateGenerator(random,0,floorTileHeight),counterIDs,typeElemEnem,idElemEnem);
    		Beststates.add(objBlockNode2);
    	}
    	}
    	return Beststates;
    }    
    
	public ArrayList  relativePositionDepthSearchTopK(int width,int height,   int countElements, int countElementsFinal,ArrayList states, ConstraintsPlacement objConstraints, ArrayList finalList, ElementsToPlace objElemP,int maxLeft, int maxRight,int floorTileHeight, int maxObjLeft, int maxObjRight,int maxScreens, int numEnemies,Random random, int globalControlSearch)
    {     
    	
    	if(countElements==countElementsFinal)
    	{
    		countElements--;
    		counterIDs=counterIDs+1;
    		Elements objElem= (Elements)finalList.get(0);
        	int idElem=objElem.getIdElem();
        	int typeElem=objElem.getTypeElem();
    		
        	if(typeElem==objElemP.getOddsJump())
        	{
        		BlockNode objBlockNode2=new BlockNode(0,height-1,counterIDs,typeElem,idElem);
        		states.add(objBlockNode2);
        	}
        	else
        	{
        		BlockNode objBlockNode2=new BlockNode(0,floorTileHeight-1,counterIDs,typeElem,idElem);
        		states.add(objBlockNode2);
        	}  
    		
    		maxLeft=maxLeft+objElem.getWidth()-1;
    		maxObjRight=maxObjRight+objElem.getWidth()-1;
    	}
    	
    	countElements--;
    	Elements objElem= (Elements)finalList.get(countElementsFinal-countElements-1);
    	int idElem=objElem.getIdElem();
    	int typeElem=objElem.getTypeElem(); 
    	
    	
    	
    	for(int i=maxLeft;i<=maxRight;i++)
    	{
    	 for(int j=(height/3);j<height;j++)
    	 {  
    		 if(typeElem==objElemP.getOddsCannons() || typeElem==objElemP.getOddsTubes() || typeElem==objElemP.getTubesFlower())
    		 {    			
    			
    			if(objConstraints.ConstraintsOverlaid(states, i, j, finalList,objElemP)==false)
     		 	{
     			 	
    				//System.out.println("falseadoo");
     			 	continue;
     		 	}
    			else if(objElem.getWidth()==1 && objConstraints.ConstraintsMinWidth(states, i, j, finalList,objElemP)==false)
      			{
      				continue;
      			}
    			else if(objConstraints.ConstraintsWidthMaxTileRelative(states, i, j, finalList, objElem.getWidth(),maxRight)==false)
    			{ 
    				
    				continue;
    			}
    			else if(objConstraints.ConstraintsHeightMaxTileRelative(states, i, j, finalList, objElem.getHeigth(),maxRight)==false)
    			{ 
    				
    				continue;
    			}
    			else if(objConstraints.ConstraintsFloorRelative(states, i, j, finalList,floorTileHeight,objElemP)==false)
    			{ 
    				
    				continue;
    			}
    			else if(objConstraints.ConstraintsMinSpace(states, i, j, finalList,objElemP)==false)
     		 	{
     			 	
    				//System.out.println("falseadoo");
     			 	continue;
     		 	}
    		 }
    		 else if( typeElem==objElemP.getOddsHillStraightFloat())
    		 {
    			 if(objConstraints.ConstraintsOverlaid(states, i, j, finalList,objElemP)==false)
        		 	{
        			 	
       				//System.out.println("falseadoo");
        			 	continue;
        		 	}
     			else if(objConstraints.ConstraintsWidthMaxTileRelative(states, i, j, finalList, objElem.getWidth(),maxRight)==false)
      			{ 
      				
      				continue;
      			}
     			else if(objConstraints.ConstraintsHeightMaxTileRelative(states, i, j, finalList, objElem.getHeigth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
     			 else if(objConstraints.ConstraintsFloorFloatingsHillsRelative(states, i, j, finalList,floorTileHeight,objElemP)==false)
      			{ 
      				
      				continue;
      			}
     			 else if(objConstraints.ConstraintsMinSpaceHillFloat(states, i, j, finalList,objElemP,floorTileHeight)==false)
       		 	{
       			 	
      				//System.out.println("falseadoo");
       			 	continue;
       		 	}
     			 /*if(objConstraints.ConstraintsPosibleGoalJump(i, j)==false)
     			 {
     				 continue;
     			 }*/   			 
    		 }
    		 else if(typeElem==objElemP.getBlockElement() || typeElem==objElemP.getCoins()|| typeElem==objElemP.getBlockBlue() || typeElem==objElemP.getBlockWood() || typeElem==objElemP.getSmallTube() || typeElem==objElemP.getWood() )
    		 {
    			 if(objConstraints.ConstraintsOverlaid(states, i, j, finalList,objElemP)==false)
       		 	{
       			 	
      				//System.out.println("falseadoo");
       			 	continue;
       		 	}
    			else if(objConstraints.ConstraintsWidthMaxTileRelative(states, i, j, finalList, objElem.getWidth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
    			else if(objConstraints.ConstraintsHeightMaxTileRelative(states, i, j, finalList, objElem.getHeigth(),maxRight)==false)
    			{ 
    				
    				continue;
    			}
    			 else if(objConstraints.ConstraintsFloorFloatingsRelative(states, i, j, finalList,floorTileHeight,objElemP)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsMinSpaceFloat(states, i, j, finalList,objElemP,floorTileHeight)==false && (typeElem==objElemP.getBlockElement() || typeElem==objElemP.getBlockBlue() || typeElem==objElemP.getBlockWood() || typeElem==objElemP.getSmallTube() || typeElem==objElemP.getWood()))
      		 	{
      			 	
     				//System.out.println("falseadoo");
      			 	continue;
      		 	}
    			 else if(objConstraints.ConstraintsMinSpaceCoins(states, i, j, finalList,objElemP)==false && typeElem==objElemP.getCoins())
       		 	{
       			 	
      				//System.out.println("falseadoo");
       			 	continue;
       		 	}
    			 /*if(objConstraints.ConstraintsPosibleGoalJump(i, j)==false)
    			 {
    				 continue;
    			 }*/
    		 }
    		 else if(typeElem==objElemP.getOddsJump())
    		 {
    			if(objConstraints.ConstraintsWidthMaxTileRelative(states, i, j, finalList, objElem.getWidth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsFloorGapsRelative(i, j)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsMinSpace(states, i, j, finalList,objElemP)==false)
      		 	{
      			 	
     				//System.out.println("falseadoo");
      			 	continue;
      		 	}
    			 /*if(objConstraints.ConstraintsPosibleGoalJump(i, j)==false)
    			 {
    				 continue;
    			 }*/
    		 } 
    		 else if(typeElem==objElemP.getOddsHillStraight())
    		 {
    			 if(objConstraints.ConstraintsOverlaidHills(states, i, j, finalList, objElemP)==false)
      		 	{      			 	
     				//System.out.println("falseadoo");
      			 	continue;
      		 	}
    			 else if(objConstraints.ConstraintsWidthMaxTileRelative(states, i, j, finalList, objElem.getWidth(),maxRight)==false)
      			{ 
      				
      				continue;
      			}
    			 else if(objConstraints.ConstraintsHeightMaxTileRelative(states, i, j, finalList, objElem.getHeigth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsFloorRelative(states, i, j, finalList,floorTileHeight,objElemP)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsMinSpaceHills(states, i, j, finalList,objElemP)==false)
      		 	{
      			 	
     				//System.out.println("falseadoo");
      			 	continue;
      		 	}
    		 }
    		 else if(typeElem==objElemP.getEnemyRedKoopa() || typeElem==objElemP.getEnemyGreenKoopa() || typeElem==objElemP.getEnemyGoomba() || typeElem==objElemP.getEnemySpiky() || typeElem==objElemP.getEnemyFlower() || typeElem==objElemP.getEnemyArmoredTurtle() || typeElem==objElemP.getEnemyJumpFlower() || typeElem==objElemP.getEnemyCannonBall() || typeElem==objElemP.getEnemyChompFlower())
    		 {
     			if(objConstraints.ConstraintsOverlaidHills(states, i, j, finalList,objElemP)==false)
     		 	{
     			 	
    				//System.out.println("falseadoo");
     			 	continue;
     		 	}
    			else if(objConstraints.ConstraintsFloorEnemiesRelative(states, i, j, finalList,floorTileHeight,objElemP)==false)
    			{ 
    				
    				continue;
    			}
    		 }
    		 
    		counterIDs=counterIDs+1;
    		BlockNode objBlockNode2=new BlockNode(i,j,counterIDs,typeElem,idElem);
    		states.add(objBlockNode2);
    
			localMaxObjLeft=maxObjLeft;
			localMaxObjRight=maxObjRight;
			localMaxRight=maxRight;
			localMaxLeft=maxLeft;
			if(i<maxObjLeft)
			{
				int tempMaxObjLeft=maxObjLeft;
				localMaxObjLeft=i;
				localMaxRight=maxRight+(localMaxObjLeft-tempMaxObjLeft);
			}
			else if(i+objElem.getWidth()-1>maxObjRight)
			{
				int tempMaxObjRight=maxObjRight;
				localMaxObjRight=i+objElem.getWidth()-1;
				localMaxLeft=maxLeft+(localMaxObjRight-tempMaxObjRight);
				//maxLeft=maxLeft+objElem.getWidth()-1;
			}    
    		
    		if(countElements>0)
    		{    			
    			relativePositionDepthSearchTopK(width,height, countElements,countElementsFinal,states,objConstraints,finalList,objElemP,localMaxLeft,localMaxRight,floorTileHeight,localMaxObjLeft,localMaxObjRight,maxScreens,numEnemies,random,globalControlSearch+1);
    		}
    		else{
    			//System.out.println("aca se debe calcular la formula");
    			counterBranches=counterBranches+1;
    			validateBestBranchTopK(states,objElemP,height,counterBranches,maxScreens,localMaxObjLeft,floorTileHeight);
    			 
    		}
    		//System.out.println("Aqui deberia eliminar del array");
    		states.remove(states.size() - 1);
    		 
    	 }
    	}
    	//putting enemies
    	if(globalControlSearch==0)
    	{
    	for(int i=0;i<numEnemies;i++)
    	{
    		countElements--;
        	Elements objElemEnem= (Elements)finalList.get(countElementsFinal+i);
        	int idElemEnem=objElemEnem.getIdElem();
        	int typeElemEnem=objElemEnem.getTypeElem(); 
        	
        	counterIDs=counterIDs+1;
    		BlockNode objBlockNode2=new BlockNode(RandomCoordenateGenerator(random,0,width-1),RandomCoordenateGenerator(random,0,floorTileHeight),counterIDs,typeElemEnem,idElemEnem);
    		//states.add(objBlockNode2);

    		Iterator<Branch> nombreIterator = bestBranches.iterator();
            while(nombreIterator.hasNext()){
            	Branch elemento = nombreIterator.next();
            	elemento.getStates().add(objBlockNode2);
            }
    	}
    	}
    	return bestBranches;
    }	
	
    public ArrayList  basicDepthSearch(int width,int height,   int countElements, int countElementsFinal,ArrayList states, ConstraintsPlacement objConstraints, ArrayList finalList, ElementsToPlace objElemP,int floorTileHeight)
    {    
    	
    	countElements--;
    	Elements objElem= (Elements)finalList.get(countElementsFinal-countElements-1);
    	int idElem=objElem.getIdElem();
    	int typeElem=objElem.getTypeElem();
    	for(int i=0;i<width;i++)
    	{
    	 for(int j=0;j<height;j++)
    	 {  
    		 if(typeElem==objElemP.getOddsCannons() || typeElem==objElemP.getOddsTubes())
    		 {    			
    			
    			if(objConstraints.ConstraintsOverlaid(states, i, j, finalList,objElemP)==false)
     		 	{
     			 	
    				//System.out.println("falseadoo");
     			 	continue;
     		 	}
    			else if(objConstraints.ConstraintsWidthMaxTile(states, i, j, finalList, width)==false)
    			{ 
    				
    				continue;
    			}
    			else if(objConstraints.ConstraintsFloor(i,j)==false)
    			{ 
    				
    				continue;
    			}
    		 }
    		 else if(typeElem==objElemP.getBlockElement())
    		 {
    			 if(objConstraints.ConstraintsOverlaid(states, i, j, finalList,objElemP)==false)
       		 	{
       			 	
      				//System.out.println("falseadoo");
       			 	continue;
       		 	}
    			 else if(objConstraints.ConstraintsWidthMaxTile(states, i, j, finalList, width)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsFloorFloatings(states, i, j, finalList)==false)
     			{ 
     				
     				continue;
     			}
    			
    			 /*if(objConstraints.ConstraintsPosibleGoalJump(i, j)==false)
    			 {
    				 continue;
    			 }*/
    		 }
    		 
    		counterIDs=counterIDs+1;
    		BlockNode objBlockNode2=new BlockNode(i,j,counterIDs,typeElem,idElem);
    		states.add(objBlockNode2);
    		if(countElements>0)
    			basicDepthSearch(width,height, countElements,countElementsFinal,states,objConstraints,finalList,objElemP,floorTileHeight);
    		else{
    			//System.out.println("aca se debe calcular la formula");
    			validateBestBranch(states,objElemP,height,floorTileHeight,localMaxObjLeft);
    			 
    		}
    		//System.out.println("Aqui deberia eliminar del array");
    		states.remove(states.size() - 1);
    		 
    	 }
    	}
    	return Beststates;
    }
    
    private void validateBestBranch(ArrayList states, ElementsToPlace objElemP,int height,int floor,int maxObjLeft) {
		// TODO Auto-generated method stub
    	
    	//impresion de array actual
    	
    	Iterator<BlockNode> nombreIterator = states.iterator();
        while(nombreIterator.hasNext()){
        	BlockNode elemento = nombreIterator.next();
        	//System.out.print(elemento.getID()+"("+elemento.getX()+" "+elemento.getY()+" )  / ");
        }
        
        //here we will calculate the center of mass
        centerOfMass(states,objElemP,height,floor);
        symmetryV=symettry1(states, objElemP, xCenterMassGeneral, yCenterMassGeneral,xCenterMassCoins, yCenterMassCoins);
        double DistanceX=distanceBetweenX(states, objElemP, xCenterMassGeneral, yCenterMassGeneral,xCenterMassCoins, yCenterMassCoins);
        
        if(symmetryV<bestSymmetryV)
        {
        		bestSymmetryV=symmetryV;
            	Beststates= new ArrayList<BlockNode>(states);
            	Beststates=FormattingElementsSingle(Beststates, maxObjLeft);
            	bestAverageX=DistanceX;
        	
        }
        else if(symmetryV==bestSymmetryV )
        {
        	   if(DistanceX>bestAverageX)
        	   {
        		bestSymmetryV=symmetryV;
        		Beststates= new ArrayList<BlockNode>(states);
        		Beststates=FormattingElementsSingle(Beststates, maxObjLeft);
        		bestAverageX=DistanceX;
        	   }
        }
    	
        
    			
	}
    
    private void validateBestBranchTopK(ArrayList states, ElementsToPlace objElemP,int height,int counterBranches, int maxScreens, int maxObjLeft,int floor) {
		// TODO Auto-generated method stub
    	
    	
    	
    	//impresion de array actual
    	
    	Iterator<BlockNode> nombreIterator = states.iterator();
        while(nombreIterator.hasNext()){
        	BlockNode elemento = nombreIterator.next();
        	//System.out.print(elemento.getID()+"("+elemento.getX()+" "+elemento.getY()+" )  / ");
        }
        
        //here we will calculate the center of mass
        centerOfMass(states,objElemP,height,floor);
        symmetryV=symettry1(states, objElemP, xCenterMassGeneral, yCenterMassGeneral,xCenterMassCoins, yCenterMassCoins);
        
      //creating object Branch
    	Branch objBranch=new Branch(symmetryV,new ArrayList<BlockNode>(states));
        
        
        
    	if(counterBranches<=maxScreens)
        {
        	if(symmetryV>worstSymmetryV)
            {
            	worstSymmetryV=symmetryV;
            	WorststatesObj=objBranch;
            }
        	objBranch=FormattingElementsTopK(objBranch, maxObjLeft);
        	bestBranches.add(objBranch);
        	bestBranches=objBranch.sortBranches(bestBranches);
        }
        else
        {
        	
        	if(objBranch.getHeuristicValue()<WorststatesObj.getHeuristicValue())
        	{
        		bestBranches.remove(WorststatesObj);
        		objBranch=FormattingElementsTopK(objBranch, maxObjLeft);
        		bestBranches.add(objBranch);
        		bestBranches=objBranch.sortBranches(bestBranches);
        		WorststatesObj=(Branch)bestBranches.get(bestBranches.size()-1);
        	}
        }
    			
	}    
    
	public double distanceBetweenX(ArrayList states,ElementsToPlace objElemP, double xCenterMassGeneral, double yCenterMassGeneral, double xCenterMassCoins, double yCenterMassCoins)
	{
		double widthElement=0;
		double heigthElement=0;
		
		double widthElementN=0;
		double heigthElementN=0;

		double averageXDistance=0;
		//Symmetry General
		Iterator<BlockNode> itSymmetry1 = states.iterator();
		while(itSymmetry1.hasNext()){
			BlockNode elemento = itSymmetry1.next();
			Elements element=(Elements)objElemP.getFinalList().get(elemento.getIdElement());
			
			int xInitial = elemento.getX();
	        int yInitial= elemento.getY();
	        widthElement=element.getWidth();
	        heigthElement=element.getHeigth()+1;
	        
	        int xMid=(int)((xInitial+widthElement)/2);
	        Iterator<BlockNode> itPlaces = states.iterator();
	        while(itPlaces.hasNext()){
	        	
	        	BlockNode elementoN = itPlaces.next();
				Elements elementN=(Elements)objElemP.getFinalList().get(elementoN.getIdElement());
				
				int xInitialN = elementoN.getX();
		        int yInitialN= elementoN.getY();
		        widthElementN=elementN.getWidth();
		        heigthElementN=elementN.getHeigth()+1;
		        int xMidN=(int)((xInitialN+widthElementN)/2);
	        	averageXDistance=averageXDistance+Math.abs(xMid-xMidN);
	        
	        }
	        
			}

		return averageXDistance;
	}
    
	public void centerOfMass(ArrayList states,ElementsToPlace objElemP,double height,int floor)
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
			BlockNode elemento = itCenterMass.next();
			Elements element=(Elements)objElemP.getFinalList().get(elemento.getIdElement());
			if(elemento.getType()!=objElemP.getCoins() && elemento.getType()!=objElemP.getEnemyArmoredTurtle() && elemento.getType()!=objElemP.getEnemyCannonBall() && elemento.getType()!=objElemP.getEnemyChompFlower() && elemento.getType()!=objElemP.getEnemyFlower() && elemento.getType()!=objElemP.getEnemyGoomba() && elemento.getType()!=objElemP.getEnemyGreenKoopa() && elemento.getType()!=objElemP.getEnemyJumpFlower() && elemento.getType()!=objElemP.getEnemyRedKoopa() && elemento.getType()!=objElemP.getEnemySpiky())
			{
			int xInitial = elemento.getX();
	        int yInitial= elemento.getY();
	        widthElementG=element.getWidth();
	        heigthElementG=element.getHeigth()+1;
	        
	        areaG=widthElementG*heigthElementG;
	        xG=xInitial+(widthElementG/2);
	        yG=yInitial-(heigthElementG/2);
	        //System.out.println(elemento.getIdElement()+"x "+xG+"y "+yG);
	        
	        summatoryAreasG=summatoryAreasG+areaG;
	        summatoryAreasXG=summatoryAreasXG+(areaG*(xG));
	        summatoryAreasYG=summatoryAreasYG+(areaG*(yG));
	        
	        if(element.getIdElem()==0 && element.getTypeElem()==objElemP.getBlockElement() || element.getTypeElem()==objElemP.getOddsHillStraightFloat())
	        {
	        	flagPivotFloating=true;
	        }
	        
			}
	        
	        		        
		}
		
		xCenterMassGeneral=summatoryAreasXG/summatoryAreasG;
        yCenterMassGeneral=summatoryAreasYG/summatoryAreasG;
        
        yCenterMassGeneral=9.0;
        if(flagPivotFloating==true)
        {
        	yCenterMassGeneral=7.0;
        }
        //System.out.println("xCenterMassG "+xCenterMassGeneral);
        //System.out.println("yCenterMassG "+yCenterMassGeneral);
		
		//center of mass of Coins
		Iterator<BlockNode> itCenterMass2 = states.iterator();
		while(itCenterMass2.hasNext()){
			BlockNode elemento = itCenterMass2.next();
			Elements element=(Elements)objElemP.getFinalList().get(elemento.getIdElement());
			if(elemento.getType()==objElemP.getCoins())
			{
			int xInitial = elemento.getX();
	        int yInitial= elemento.getY();
	        widthElementC=element.getWidth();
	        heigthElementC=element.getHeigth()+1;
	        
	        areaC=widthElementC*heigthElementC;
	        xC=xInitial+(widthElementC/2);
	        yC=yInitial-(heigthElementC/2);
	        //System.out.println(elemento.getIdElement()+"x "+xC+"y "+yC);
	        
	        summatoryAreasC=summatoryAreasC+areaC;
	        summatoryAreasXC=summatoryAreasXC+(areaC*(xC));
	        summatoryAreasYC=summatoryAreasYC+(areaC*(yC));
			}
	        
	        		        
		}
		
		/*System.out.println("summatoryAreasX "+summatoryAreasX);
        System.out.println("summatoryAreasY "+summatoryAreasY);
        System.out.println("summatoryAreas "+summatoryAreas);*/
		
		xCenterMassCoins=summatoryAreasXC/summatoryAreasC;
        yCenterMassCoins=summatoryAreasYC/summatoryAreasC;
        
        yCenterMassCoins=floor/2;
        //System.out.println("xCenterMassC "+xCenterMassCoins);
        //System.out.println("yCenterMassC "+yCenterMassCoins);
	}
	
	public double symettry1Vertical(ArrayList states,ElementsToPlace objElemP, double xCenterMassGeneral, double yCenterMassGeneral, double xCenterMassCoins, double yCenterMassCoins)
	{
		//gul=new ArrayList<double[]>();
		//gur=new ArrayList<double[]>();
		//gll=new ArrayList<double[]>();
		//glr=new ArrayList<double[]>();
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
			Elements element=(Elements)objElemP.getFinalList().get(elemento.getIdElement());
			if(elemento.getType()!=objElemP.getCoins() && elemento.getType()!=objElemP.getEnemyArmoredTurtle() && elemento.getType()!=objElemP.getEnemyCannonBall() && elemento.getType()!=objElemP.getEnemyChompFlower() && elemento.getType()!=objElemP.getEnemyFlower() && elemento.getType()!=objElemP.getEnemyGoomba() && elemento.getType()!=objElemP.getEnemyGreenKoopa() && elemento.getType()!=objElemP.getEnemyJumpFlower() && elemento.getType()!=objElemP.getEnemyRedKoopa() && elemento.getType()!=objElemP.getEnemySpiky())
			{
			int xInitial = elemento.getX();
	        int yInitial= elemento.getY();
	        widthElement=element.getWidth();
	        heigthElement=element.getHeigth()+1;
	        
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
		}
		//System.out.println("gulAT "+gulATG[0]+" "+gulATG[1]+" "+gulATG[2]+" "+gulATG[3]);
		//System.out.println("gurAT "+gurATG[0]+" "+gurATG[1]+" "+gurATG[2]+" "+gurATG[3]);
		//System.out.println("gllAT "+gllATG[0]+" "+gllATG[1]+" "+gllATG[2]+" "+gllATG[3]);
		//System.out.println("glrAT "+glrATG[0]+" "+glrATG[1]+" "+glrATG[2]+" "+glrATG[3]);
		//symmetryValueGeneral=SubstractionSymmetries(gulATG,gllATG)+SubstractionSymmetries(gurATG,glrATG)+SubstractionSymmetries(gulATG,gurATG)+SubstractionSymmetries(gllATG,glrATG)+SubstractionSymmetries(gulATG,glrATG)+SubstractionSymmetries(gurATG,gllATG);
		symmetryValueGeneral=SubstractionSymmetries(gulATG,gurATG)+SubstractionSymmetries(gllATG,glrATG);
		//System.out.println("symmetryValue "+symmetryValueGeneral);
		
		
		//Symmetry Coins
		Iterator<BlockNode> itSymmetry2 = states.iterator();
		while(itSymmetry2.hasNext()){
			BlockNode elemento = itSymmetry2.next();
			Elements element=(Elements)objElemP.getFinalList().get(elemento.getIdElement());
			if(elemento.getType()==objElemP.getCoins() )
			{
			int xInitial = elemento.getX();
	        int yInitial= elemento.getY();
	        widthElement=element.getWidth();
	        heigthElement=element.getHeigth()+1;
	        
	        if((xInitial+widthElement)<=xCenterMassCoins )
	        {
	        	//block up left
	        	if(yInitial<=yCenterMassCoins)
	        	{
	        		x=xInitial+(widthElement/2);
	        		y=yInitial-(heigthElement/2);
	        		gulAC[0]=Math.abs(x-xCenterMassCoins);
	        		gulATC[0]=gulATC[0]+gulAC[0];
	        		gulAC[1]=Math.abs(y-yCenterMassCoins);
	        		gulATC[1]=gulATC[1]+gulAC[1];
	        		gulAC[2]=widthElement;
	        		gulATC[2]=gulATC[2]+gulAC[2];
	        		gulAC[3]=heigthElement;
	        		gulATC[3]=gulATC[3]+gulAC[3];
	        		//gul.add(gulA);
	        	}
	        	
	        	//block low left
	        	else if(yInitial-heigthElement>=yCenterMassCoins)
	        	{
	        		x=xInitial+(widthElement/2);
	        		y=yInitial-(heigthElement/2);
	        		gllAC[0]=Math.abs(x-xCenterMassCoins);
	        		gllATC[0]=gllATC[0]+gllAC[0];
	        		gllAC[1]=Math.abs(y-yCenterMassCoins);
	        		gllATC[1]=gllATC[1]+gllAC[1];
	        		gllAC[2]=widthElement;
	        		gllATC[2]=gllATC[2]+gllAC[2];
	        		gllAC[3]=heigthElement;
	        		gllATC[3]=gllATC[3]+gllAC[3];
	        		//gll.add(gllA);
	        	}
	        	else
	        	{
	        		x=xInitial+(widthElement/2);
	        	
	        		//first block of the element (up left)
	        		//y=(yInitial-heigthElement)+(yCenterMassCoins-yInitial)/2;
	        		y=(yInitial-heigthElement)+(yCenterMassCoins-(yInitial-heigthElement))/2;
	        		gulAC[0]=Math.abs(x-xCenterMassCoins);
	        		gulATC[0]=gulATC[0]+gulAC[0];
	        		gulAC[1]=Math.abs(y-yCenterMassCoins);
	        		gulATC[1]=gulATC[1]+gulAC[1];
	        		gulAC[2]=widthElement;
	        		gulATC[2]=gulATC[2]+gulAC[2];
	        		gulAC[3]=yCenterMassCoins-(yInitial-heigthElement);
	        		gulATC[3]=gulATC[3]+gulAC[3];
	        		//gul.add(gulA);
	        	
	        		//second block of the element (low left)
	        		y=yInitial-(yInitial-yCenterMassCoins)/2;
	        		gllAC[0]=Math.abs(x-xCenterMassCoins);
	        		gllATC[0]=gllATC[0]+gllAC[0];
	        		gllAC[1]=Math.abs(y-yCenterMassCoins);
	        		gllATC[1]=gllATC[1]+gllAC[1];
	        		gllAC[2]=widthElement;
	        		gllATC[2]=gllATC[2]+gllAC[2];
	        		gllAC[3]=(yInitial-yCenterMassCoins);
	        		gllATC[3]=gllATC[3]+gllAC[3];
	        		//gll.add(gllA);
	        	}
	        }
	        else if(xInitial>=xCenterMassCoins )
	        {
	        	
	        	//block up right
	        	if(yInitial<=yCenterMassCoins)
	        	{
	        		x=xInitial+(widthElement/2);
	        		y=yInitial-(heigthElement/2);
	        		gurAC[0]=Math.abs(x-xCenterMassCoins);
	        		gurATC[0]=gurATC[0]+gurAC[0];
	        		gurAC[1]=Math.abs(y-yCenterMassCoins);
	        		gurATC[1]=gurATC[1]+gurAC[1];
	        		gurAC[2]=widthElement;
	        		gurATC[2]=gurATC[2]+gurAC[2];
	        		gurAC[3]=heigthElement;
	        		gurATC[3]=gurATC[3]+gurAC[3];
	        		//gur.add(gurA);
	        	}
	        	//block low right
	        	else if(yInitial-heigthElement>=yCenterMassCoins)
	        	{
	        		x=xInitial+(widthElement/2);
	        		y=yInitial-(heigthElement/2);
	        		glrAC[0]=Math.abs(x-xCenterMassCoins);
	        		glrATC[0]=glrATC[0]+glrAC[0];
	        		glrAC[1]=Math.abs(y-yCenterMassCoins);
	        		glrATC[1]=glrATC[1]+glrAC[1];
	        		glrAC[2]=widthElement;
	        		glrATC[2]=glrATC[2]+glrAC[2];
	        		glrAC[3]=heigthElement;
	        		glrATC[3]=glrATC[3]+glrAC[3];
	        		//glr.add(glrA);
	        	}
	        	else
	        	{
	        		
	        		x=xInitial+(widthElement/2);
	        		
	        		//first block of the element (up right)
	        		y=(yInitial-heigthElement)+(yCenterMassCoins-(yInitial-heigthElement))/2;
	        		gurAC[0]=Math.abs(x-xCenterMassCoins);
	        		gurATC[0]=gurATC[0]+gurAC[0];
	        		gurAC[1]=Math.abs(y-yCenterMassCoins);
	        		gurATC[1]=gurATC[1]+gurAC[1];
	        		gurAC[2]=widthElement;
	        		gurATC[2]=gurATC[2]+gurAC[2];
	        		gurAC[3]=yCenterMassCoins-(yInitial-heigthElement);
	        		gurATC[3]=gurATC[3]+gurAC[3];
	        		//gur.add(gulA);
		        	
	        		//second block of the element  (low right)
	        		y=yInitial-(yInitial-yCenterMassCoins)/2;
	        		glrAC[0]=Math.abs(x-xCenterMassCoins);
	        		glrATC[0]=glrATC[0]+glrAC[0];
	        		glrAC[1]=Math.abs(y-yCenterMassCoins);
	        		glrATC[1]=glrATC[1]+glrAC[1];
	        		glrAC[2]=widthElement;
	        		glrATC[2]=glrATC[2]+glrAC[2];
	        		glrAC[3]=yInitial-yCenterMassCoins;
	        		glrATC[3]=glrATC[3]+glrAC[3];
	        		//glr.add(gllA);
	        	}
	        	
	        }
	        else
	        {
	        	if(yInitial<=yCenterMassCoins)
	        	{
	        		y=yInitial-(heigthElement/2);
	        		
	        		//first block of the element (up left)
	        		x=(xInitial+(xCenterMassCoins-xInitial)/2);
	        		gulAC[0]=Math.abs(x-xCenterMassCoins);
	        		gulATC[0]=gulATC[0]+gulAC[0];
			        gulAC[1]=Math.abs(y-yCenterMassCoins);
			        gulATC[1]=gulATC[1]+gulAC[1];
			        gulAC[2]=xCenterMassCoins-xInitial;
			        gulATC[2]=gulATC[2]+gulAC[2];
			        gulAC[3]=heigthElement;
			        gulATC[3]=gulATC[3]+gulAC[3];
			        //gul.add(gurA);
			        
			        //second block of the element (up right)
			        x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassCoins)/2;
	        		gurAC[0]=Math.abs(x-xCenterMassCoins);
	        		gurATC[0]=gurATC[0]+gurAC[0];
			        gurAC[1]=Math.abs(y-yCenterMassCoins);
			        gurATC[1]=gurATC[1]+gurAC[1];
			        gurAC[2]=(xInitial+widthElement)-xCenterMassCoins;
			        gurATC[2]=gurATC[2]+gurAC[2];
			        gurAC[3]=heigthElement;
			        gurATC[3]=gurATC[3]+gurAC[3];
			        //gur.add(gurA);
	        		
	        	}
	        	else if(yInitial-heigthElement>=yCenterMassCoins)
	        	{
	        		y=yInitial-(heigthElement/2);
	        		
	        		//first block of the element (low left)
	        		x=(xInitial+(xCenterMassCoins-xInitial)/2);
	        		gllAC[0]=Math.abs(x-xCenterMassCoins);
	        		gllATC[0]=gllATC[0]+gllAC[0];
			        gllAC[1]=Math.abs(y-yCenterMassCoins);
			        gllATC[1]=gllATC[1]+gllAC[1];
			        gllAC[2]=(xCenterMassCoins-xInitial);
			        gllATC[2]=gllATC[2]+gllAC[2];
			        gllAC[3]=heigthElement;
			        gllATC[3]=gllATC[3]+gllAC[3];
			        //gll.add(gurA);
			        
			        //second block of the element (low right)
			        x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassCoins)/2;
	        		glrAC[0]=Math.abs(x-xCenterMassCoins);
	        		glrATC[0]=glrATC[0]+glrAC[0];
			        glrAC[1]=Math.abs(y-yCenterMassCoins);
			        glrATC[1]=glrATC[1]+glrAC[1];
			        glrAC[2]=(xCenterMassCoins-xInitial);
			        glrATC[2]=glrATC[2]+glrAC[2];
			        glrAC[3]=heigthElement;
			        glrATC[3]=glrATC[3]+glrAC[3];
			        //gur.add(gurA);
	        	}
	        	else
	        	{
	        		//falta implementar caso todos los cuadrantes
	        		//first block of the element (up left)
	        		x=(xInitial+(xCenterMassCoins-xInitial)/2);
	        		y=(yInitial-heigthElement)+(yCenterMassCoins-(yInitial-heigthElement))/2;
	        		
	        		gulAC[0]=Math.abs(x-xCenterMassCoins);
	        		gulATC[0]=gulATC[0]+gulAC[0];
			        gulAC[1]=Math.abs(y-yCenterMassCoins);
			        gulATC[1]=gulATC[1]+gulAC[1];
			        gulAC[2]=xCenterMassCoins-xInitial;
			        gulATC[2]=gulATC[2]+gulAC[2];
			        gulAC[3]=yCenterMassCoins-(yInitial-heigthElement);
			        gulATC[3]=gulATC[3]+gulAC[3];
			        
	        		//second block of the element (up right)
	        		x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassCoins)/2;
	        		y=(yInitial-heigthElement)+(yCenterMassCoins-(yInitial-heigthElement))/2;
	        		
	        		gurAC[0]=Math.abs(x-xCenterMassCoins);
	        		gurATC[0]=gurATC[0]+gurAC[0];
			        gurAC[1]=Math.abs(y-yCenterMassCoins);
			        gurATC[1]=gurATC[1]+gurAC[1];
			        gurAC[2]=(xInitial+widthElement)-xCenterMassCoins;
			        gurATC[2]=gurATC[2]+gurAC[2];
			        gurAC[3]=yCenterMassCoins-(yInitial-heigthElement);
			        gurATC[3]=gurATC[3]+gurAC[3];
	        		
	        		//first block of the element (low left)
	        		x=(xInitial+(xCenterMassCoins-xInitial)/2);
	        		y=yInitial-(yInitial-yCenterMassCoins)/2;
	        		
	        		gllAC[0]=Math.abs(x-xCenterMassCoins);
	        		gllATC[0]=gllATC[0]+gllAC[0];
			        gllAC[1]=Math.abs(y-yCenterMassCoins);
			        gllATC[1]=gllATC[1]+gllAC[1];
			        gllAC[2]=(xCenterMassCoins-xInitial);
			        gllATC[2]=gllATC[2]+gllAC[2];
			        gllAC[3]=yInitial-yCenterMassCoins;
			        gllATC[3]=gllATC[3]+gllAC[3];
			        
	        		//second block of the element (low right)
	        		x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassCoins)/2;
	        		y=yInitial-(yInitial-yCenterMassCoins)/2;
	        		
	        		glrAC[0]=Math.abs(x-xCenterMassCoins);
	        		glrATC[0]=glrATC[0]+glrAC[0];
			        glrAC[1]=Math.abs(y-yCenterMassCoins);
			        glrATC[1]=glrATC[1]+glrAC[1];
			        glrAC[2]=(xInitial+widthElement)-xCenterMassCoins;
			        glrATC[2]=glrATC[2]+glrAC[2];
			        glrAC[3]=yInitial-yCenterMassCoins;
			        glrATC[3]=glrATC[3]+glrAC[3];
	        	}
	        }
	        
		}
		}
		//System.out.println("gulAT "+gulATC[0]+" "+gulATC[1]+" "+gulATC[2]+" "+gulATC[3]);
		//System.out.println("gurAT "+gurATC[0]+" "+gurATC[1]+" "+gurATC[2]+" "+gurATC[3]);
		//System.out.println("gllAT "+gllATC[0]+" "+gllATC[1]+" "+gllATC[2]+" "+gllATC[3]);
		//System.out.println("glrAT "+glrATC[0]+" "+glrATC[1]+" "+glrATC[2]+" "+glrATC[3]);
		//symmetryValueCoins=SubstractionSymmetries(gulATC,gllATC)+SubstractionSymmetries(gurATC,glrATC)+SubstractionSymmetries(gulATC,gurATC)+SubstractionSymmetries(gllATC,glrATC)+SubstractionSymmetries(gulATC,glrATC)+SubstractionSymmetries(gurATC,gllATC);
		symmetryValueCoins=SubstractionSymmetries(gulATC,gurATC)+SubstractionSymmetries(gllATC,glrATC);

		//System.out.println("symmetryValue "+symmetryValueCoins);
		
		symmetryValue=symmetryValueGeneral+symmetryValueCoins;
		return symmetryValue;
	}

	public double symettry1(ArrayList states,ElementsToPlace objElemP, double xCenterMassGeneral, double yCenterMassGeneral, double xCenterMassCoins, double yCenterMassCoins)
	{
		//gul=new ArrayList<double[]>();
		//gur=new ArrayList<double[]>();
		//gll=new ArrayList<double[]>();
		//glr=new ArrayList<double[]>();
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
			Elements element=(Elements)objElemP.getFinalList().get(elemento.getIdElement());
			if(elemento.getType()!=objElemP.getCoins() && elemento.getType()!=objElemP.getEnemyArmoredTurtle() && elemento.getType()!=objElemP.getEnemyCannonBall() && elemento.getType()!=objElemP.getEnemyChompFlower() && elemento.getType()!=objElemP.getEnemyFlower() && elemento.getType()!=objElemP.getEnemyGoomba() && elemento.getType()!=objElemP.getEnemyGreenKoopa() && elemento.getType()!=objElemP.getEnemyJumpFlower() && elemento.getType()!=objElemP.getEnemyRedKoopa() && elemento.getType()!=objElemP.getEnemySpiky())
			{
			int xInitial = elemento.getX();
	        int yInitial= elemento.getY();
	        widthElement=element.getWidth();
	        heigthElement=element.getHeigth()+1;
	        
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
		}
		//System.out.println("gulAT "+gulATG[0]+" "+gulATG[1]+" "+gulATG[2]+" "+gulATG[3]);
		//System.out.println("gurAT "+gurATG[0]+" "+gurATG[1]+" "+gurATG[2]+" "+gurATG[3]);
		//System.out.println("gllAT "+gllATG[0]+" "+gllATG[1]+" "+gllATG[2]+" "+gllATG[3]);
		//System.out.println("glrAT "+glrATG[0]+" "+glrATG[1]+" "+glrATG[2]+" "+glrATG[3]);
		symmetryValueGeneral=SubstractionSymmetries(gulATG,gllATG)+SubstractionSymmetries(gurATG,glrATG)+SubstractionSymmetries(gulATG,gurATG)+SubstractionSymmetries(gllATG,glrATG)+SubstractionSymmetries(gulATG,glrATG)+SubstractionSymmetries(gurATG,gllATG);
		//symmetryValueGeneral=SubstractionSymmetries(gulATG,gurATG)+SubstractionSymmetries(gllATG,glrATG);
		//System.out.println("symmetryValue "+symmetryValueGeneral);
		
		
		//Symmetry Coins
		Iterator<BlockNode> itSymmetry2 = states.iterator();
		while(itSymmetry2.hasNext()){
			BlockNode elemento = itSymmetry2.next();
			Elements element=(Elements)objElemP.getFinalList().get(elemento.getIdElement());
			if(elemento.getType()==objElemP.getCoins() )
			{
			int xInitial = elemento.getX();
	        int yInitial= elemento.getY();
	        widthElement=element.getWidth();
	        heigthElement=element.getHeigth()+1;
	        
	        if((xInitial+widthElement)<=xCenterMassCoins )
	        {
	        	//block up left
	        	if(yInitial<=yCenterMassCoins)
	        	{
	        		x=xInitial+(widthElement/2);
	        		y=yInitial-(heigthElement/2);
	        		gulAC[0]=Math.abs(x-xCenterMassCoins);
	        		gulATC[0]=gulATC[0]+gulAC[0];
	        		gulAC[1]=Math.abs(y-yCenterMassCoins);
	        		gulATC[1]=gulATC[1]+gulAC[1];
	        		gulAC[2]=widthElement;
	        		gulATC[2]=gulATC[2]+gulAC[2];
	        		gulAC[3]=heigthElement;
	        		gulATC[3]=gulATC[3]+gulAC[3];
	        		//gul.add(gulA);
	        	}
	        	
	        	//block low left
	        	else if(yInitial-heigthElement>=yCenterMassCoins)
	        	{
	        		x=xInitial+(widthElement/2);
	        		y=yInitial-(heigthElement/2);
	        		gllAC[0]=Math.abs(x-xCenterMassCoins);
	        		gllATC[0]=gllATC[0]+gllAC[0];
	        		gllAC[1]=Math.abs(y-yCenterMassCoins);
	        		gllATC[1]=gllATC[1]+gllAC[1];
	        		gllAC[2]=widthElement;
	        		gllATC[2]=gllATC[2]+gllAC[2];
	        		gllAC[3]=heigthElement;
	        		gllATC[3]=gllATC[3]+gllAC[3];
	        		//gll.add(gllA);
	        	}
	        	else
	        	{
	        		x=xInitial+(widthElement/2);
	        	
	        		//first block of the element (up left)
	        		//y=(yInitial-heigthElement)+(yCenterMassCoins-yInitial)/2;
	        		y=(yInitial-heigthElement)+(yCenterMassCoins-(yInitial-heigthElement))/2;
	        		gulAC[0]=Math.abs(x-xCenterMassCoins);
	        		gulATC[0]=gulATC[0]+gulAC[0];
	        		gulAC[1]=Math.abs(y-yCenterMassCoins);
	        		gulATC[1]=gulATC[1]+gulAC[1];
	        		gulAC[2]=widthElement;
	        		gulATC[2]=gulATC[2]+gulAC[2];
	        		gulAC[3]=yCenterMassCoins-(yInitial-heigthElement);
	        		gulATC[3]=gulATC[3]+gulAC[3];
	        		//gul.add(gulA);
	        	
	        		//second block of the element (low left)
	        		y=yInitial-(yInitial-yCenterMassCoins)/2;
	        		gllAC[0]=Math.abs(x-xCenterMassCoins);
	        		gllATC[0]=gllATC[0]+gllAC[0];
	        		gllAC[1]=Math.abs(y-yCenterMassCoins);
	        		gllATC[1]=gllATC[1]+gllAC[1];
	        		gllAC[2]=widthElement;
	        		gllATC[2]=gllATC[2]+gllAC[2];
	        		gllAC[3]=(yInitial-yCenterMassCoins);
	        		gllATC[3]=gllATC[3]+gllAC[3];
	        		//gll.add(gllA);
	        	}
	        }
	        else if(xInitial>=xCenterMassCoins )
	        {
	        	
	        	//block up right
	        	if(yInitial<=yCenterMassCoins)
	        	{
	        		x=xInitial+(widthElement/2);
	        		y=yInitial-(heigthElement/2);
	        		gurAC[0]=Math.abs(x-xCenterMassCoins);
	        		gurATC[0]=gurATC[0]+gurAC[0];
	        		gurAC[1]=Math.abs(y-yCenterMassCoins);
	        		gurATC[1]=gurATC[1]+gurAC[1];
	        		gurAC[2]=widthElement;
	        		gurATC[2]=gurATC[2]+gurAC[2];
	        		gurAC[3]=heigthElement;
	        		gurATC[3]=gurATC[3]+gurAC[3];
	        		//gur.add(gurA);
	        	}
	        	//block low right
	        	else if(yInitial-heigthElement>=yCenterMassCoins)
	        	{
	        		x=xInitial+(widthElement/2);
	        		y=yInitial-(heigthElement/2);
	        		glrAC[0]=Math.abs(x-xCenterMassCoins);
	        		glrATC[0]=glrATC[0]+glrAC[0];
	        		glrAC[1]=Math.abs(y-yCenterMassCoins);
	        		glrATC[1]=glrATC[1]+glrAC[1];
	        		glrAC[2]=widthElement;
	        		glrATC[2]=glrATC[2]+glrAC[2];
	        		glrAC[3]=heigthElement;
	        		glrATC[3]=glrATC[3]+glrAC[3];
	        		//glr.add(glrA);
	        	}
	        	else
	        	{
	        		
	        		x=xInitial+(widthElement/2);
	        		
	        		//first block of the element (up right)
	        		y=(yInitial-heigthElement)+(yCenterMassCoins-(yInitial-heigthElement))/2;
	        		gurAC[0]=Math.abs(x-xCenterMassCoins);
	        		gurATC[0]=gurATC[0]+gurAC[0];
	        		gurAC[1]=Math.abs(y-yCenterMassCoins);
	        		gurATC[1]=gurATC[1]+gurAC[1];
	        		gurAC[2]=widthElement;
	        		gurATC[2]=gurATC[2]+gurAC[2];
	        		gurAC[3]=yCenterMassCoins-(yInitial-heigthElement);
	        		gurATC[3]=gurATC[3]+gurAC[3];
	        		//gur.add(gulA);
		        	
	        		//second block of the element  (low right)
	        		y=yInitial-(yInitial-yCenterMassCoins)/2;
	        		glrAC[0]=Math.abs(x-xCenterMassCoins);
	        		glrATC[0]=glrATC[0]+glrAC[0];
	        		glrAC[1]=Math.abs(y-yCenterMassCoins);
	        		glrATC[1]=glrATC[1]+glrAC[1];
	        		glrAC[2]=widthElement;
	        		glrATC[2]=glrATC[2]+glrAC[2];
	        		glrAC[3]=yInitial-yCenterMassCoins;
	        		glrATC[3]=glrATC[3]+glrAC[3];
	        		//glr.add(gllA);
	        	}
	        	
	        }
	        else
	        {
	        	if(yInitial<=yCenterMassCoins)
	        	{
	        		y=yInitial-(heigthElement/2);
	        		
	        		//first block of the element (up left)
	        		x=(xInitial+(xCenterMassCoins-xInitial)/2);
	        		gulAC[0]=Math.abs(x-xCenterMassCoins);
	        		gulATC[0]=gulATC[0]+gulAC[0];
			        gulAC[1]=Math.abs(y-yCenterMassCoins);
			        gulATC[1]=gulATC[1]+gulAC[1];
			        gulAC[2]=xCenterMassCoins-xInitial;
			        gulATC[2]=gulATC[2]+gulAC[2];
			        gulAC[3]=heigthElement;
			        gulATC[3]=gulATC[3]+gulAC[3];
			        //gul.add(gurA);
			        
			        //second block of the element (up right)
			        x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassCoins)/2;
	        		gurAC[0]=Math.abs(x-xCenterMassCoins);
	        		gurATC[0]=gurATC[0]+gurAC[0];
			        gurAC[1]=Math.abs(y-yCenterMassCoins);
			        gurATC[1]=gurATC[1]+gurAC[1];
			        gurAC[2]=(xInitial+widthElement)-xCenterMassCoins;
			        gurATC[2]=gurATC[2]+gurAC[2];
			        gurAC[3]=heigthElement;
			        gurATC[3]=gurATC[3]+gurAC[3];
			        //gur.add(gurA);
	        		
	        	}
	        	else if(yInitial-heigthElement>=yCenterMassCoins)
	        	{
	        		y=yInitial-(heigthElement/2);
	        		
	        		//first block of the element (low left)
	        		x=(xInitial+(xCenterMassCoins-xInitial)/2);
	        		gllAC[0]=Math.abs(x-xCenterMassCoins);
	        		gllATC[0]=gllATC[0]+gllAC[0];
			        gllAC[1]=Math.abs(y-yCenterMassCoins);
			        gllATC[1]=gllATC[1]+gllAC[1];
			        gllAC[2]=(xCenterMassCoins-xInitial);
			        gllATC[2]=gllATC[2]+gllAC[2];
			        gllAC[3]=heigthElement;
			        gllATC[3]=gllATC[3]+gllAC[3];
			        //gll.add(gurA);
			        
			        //second block of the element (low right)
			        x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassCoins)/2;
	        		glrAC[0]=Math.abs(x-xCenterMassCoins);
	        		glrATC[0]=glrATC[0]+glrAC[0];
			        glrAC[1]=Math.abs(y-yCenterMassCoins);
			        glrATC[1]=glrATC[1]+glrAC[1];
			        glrAC[2]=(xCenterMassCoins-xInitial);
			        glrATC[2]=glrATC[2]+glrAC[2];
			        glrAC[3]=heigthElement;
			        glrATC[3]=glrATC[3]+glrAC[3];
			        //gur.add(gurA);
	        	}
	        	else
	        	{
	        		//falta implementar caso todos los cuadrantes
	        		//first block of the element (up left)
	        		x=(xInitial+(xCenterMassCoins-xInitial)/2);
	        		y=(yInitial-heigthElement)+(yCenterMassCoins-(yInitial-heigthElement))/2;
	        		
	        		gulAC[0]=Math.abs(x-xCenterMassCoins);
	        		gulATC[0]=gulATC[0]+gulAC[0];
			        gulAC[1]=Math.abs(y-yCenterMassCoins);
			        gulATC[1]=gulATC[1]+gulAC[1];
			        gulAC[2]=xCenterMassCoins-xInitial;
			        gulATC[2]=gulATC[2]+gulAC[2];
			        gulAC[3]=yCenterMassCoins-(yInitial-heigthElement);
			        gulATC[3]=gulATC[3]+gulAC[3];
			        
	        		//second block of the element (up right)
	        		x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassCoins)/2;
	        		y=(yInitial-heigthElement)+(yCenterMassCoins-(yInitial-heigthElement))/2;
	        		
	        		gurAC[0]=Math.abs(x-xCenterMassCoins);
	        		gurATC[0]=gurATC[0]+gurAC[0];
			        gurAC[1]=Math.abs(y-yCenterMassCoins);
			        gurATC[1]=gurATC[1]+gurAC[1];
			        gurAC[2]=(xInitial+widthElement)-xCenterMassCoins;
			        gurATC[2]=gurATC[2]+gurAC[2];
			        gurAC[3]=yCenterMassCoins-(yInitial-heigthElement);
			        gurATC[3]=gurATC[3]+gurAC[3];
	        		
	        		//first block of the element (low left)
	        		x=(xInitial+(xCenterMassCoins-xInitial)/2);
	        		y=yInitial-(yInitial-yCenterMassCoins)/2;
	        		
	        		gllAC[0]=Math.abs(x-xCenterMassCoins);
	        		gllATC[0]=gllATC[0]+gllAC[0];
			        gllAC[1]=Math.abs(y-yCenterMassCoins);
			        gllATC[1]=gllATC[1]+gllAC[1];
			        gllAC[2]=(xCenterMassCoins-xInitial);
			        gllATC[2]=gllATC[2]+gllAC[2];
			        gllAC[3]=yInitial-yCenterMassCoins;
			        gllATC[3]=gllATC[3]+gllAC[3];
			        
	        		//second block of the element (low right)
	        		x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassCoins)/2;
	        		y=yInitial-(yInitial-yCenterMassCoins)/2;
	        		
	        		glrAC[0]=Math.abs(x-xCenterMassCoins);
	        		glrATC[0]=glrATC[0]+glrAC[0];
			        glrAC[1]=Math.abs(y-yCenterMassCoins);
			        glrATC[1]=glrATC[1]+glrAC[1];
			        glrAC[2]=(xInitial+widthElement)-xCenterMassCoins;
			        glrATC[2]=glrATC[2]+glrAC[2];
			        glrAC[3]=yInitial-yCenterMassCoins;
			        glrATC[3]=glrATC[3]+glrAC[3];
	        	}
	        }
	        
		}
		}
		//System.out.println("gulAT "+gulATC[0]+" "+gulATC[1]+" "+gulATC[2]+" "+gulATC[3]);
		//System.out.println("gurAT "+gurATC[0]+" "+gurATC[1]+" "+gurATC[2]+" "+gurATC[3]);
		//System.out.println("gllAT "+gllATC[0]+" "+gllATC[1]+" "+gllATC[2]+" "+gllATC[3]);
		//System.out.println("glrAT "+glrATC[0]+" "+glrATC[1]+" "+glrATC[2]+" "+glrATC[3]);
		//symmetryValueCoins=SubstractionSymmetries(gulATC,gllATC)+SubstractionSymmetries(gurATC,glrATC)+SubstractionSymmetries(gulATC,gurATC)+SubstractionSymmetries(gllATC,glrATC)+SubstractionSymmetries(gulATC,glrATC)+SubstractionSymmetries(gurATC,gllATC);
		symmetryValueCoins=SubstractionSymmetries(gulATC,gurATC)+SubstractionSymmetries(gllATC,glrATC);

		//System.out.println("symmetryValue "+symmetryValueCoins);
		
		symmetryValue=symmetryValueGeneral+symmetryValueCoins;
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
	
	public int getCounterIDs()
    {
    	return counterIDs;
    }
	
	public TreeSet UpdateStringState(int typeElem,int widthElem,int heigthElem,int x, int y, TreeSet currentState) {
		
		
		String newSubStringCurrentState=Integer.toString(typeElem)+"|"+Integer.toString(widthElem)+"|"+Integer.toString(heigthElem)+"|"+Integer.toString(x)+"|"+Integer.toString(y);		
		TreeSet copycurrentState=(TreeSet)currentState.clone(); 
		copycurrentState.add(newSubStringCurrentState);
		
		return copycurrentState;
	}
	
    public Hashtable UpdateTranspositionTable(Hashtable hTable, TreeSet currentState) {
		// TODO Auto-generated method stub
    	TreeSet copycurrentState=(TreeSet)currentState.clone(); 
    	hTable.put(copycurrentState, "1");
    	return hTable;
	}
    public ArrayList FormattingElementsSingle(ArrayList bestStates, int maxObjLeft)
    {

    	
		Iterator<BlockNode> it = (bestStates).iterator();
		int counterNodes=0;
		while(it.hasNext()){
			BlockNode elemento = it.next();
			BlockNode elementoNew=new BlockNode(elemento);
			elementoNew.setX((elementoNew.getX()-maxObjLeft)+1);		
			(bestStates).set(counterNodes, elementoNew);
			counterNodes++;
		}
    	return bestStates;
    }
    public Branch FormattingElementsTopK(Branch objBranch, int maxObjLeft)
    {

    	
		Iterator<BlockNode> it = (objBranch.getStates()).iterator();
		int counterNodes=0;
		while(it.hasNext()){
			BlockNode elemento = it.next();
			BlockNode elementoNew=new BlockNode(elemento);
			elementoNew.setX((elementoNew.getX()-maxObjLeft)+1);		
			(objBranch.getStates()).set(counterNodes, elementoNew);
			counterNodes++;
		}
    	return objBranch;
    }
    public int RandomCoordenateGenerator(Random random, int maxLef,int  maxRi)
    {
    	return maxLef+random.nextInt(maxRi-maxLef);
    }
}

// End HelloJGraphT.java