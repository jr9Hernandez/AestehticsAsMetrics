package br.ufv.dpi.metrics;

import graphBuilder.BlockNode;

import java.util.ArrayList;
import java.util.Iterator;

import beauty.Elements;
import beauty.ElementsToPlace;
import dk.itu.mario.engine.sprites.SpriteTemplate;
import dk.itu.mario.level.Level;

public class Reacheability extends Metrics {

	private ArrayList<BlockNode> elementsSelected;
	private double xCenterMassGeneral;
	private double yCenterMassGeneral;
	protected byte[][] mapLevel;

	public Reacheability(int width, int height, Level level) {

		super(width, height, level);
		mapLevel = level.getMap();
	}

	@Override
	public double compute() {

		// calculation of scanning of level
		ScanLabeledLevel objScanLevel = new ScanLabeledLevel(level, width,
				height);
		elementsSelected = objScanLevel.DeterminePositions();

		// System.out.println("Symmetry "+symmetryValue);

		// testing the captured elements
		double totalUnreacheable = 0;
		Iterator<BlockNode> it = elementsSelected.iterator();
		while (it.hasNext()) {
			BlockNode elemento = it.next();

			int xInitial = elemento.getX();
			int yInitial = elemento.getY();
			int widthElement = (elemento.getElement()).getWidth();
			int heigthElement = (elemento.getElement()).getHeigth();
			int typeElement = (elemento.getElement()).getTypeElem();
			int idElemento = (elemento.getElement()).getIdElem();

			boolean validateBottom =true;
			if (typeElement != 0) {
				
				if(typeElement==8)
				{
				validateBottom = ValidateBottomBlocks(idElemento, xInitial,yInitial, widthElement, heigthElement, typeElement);
				}
				else
				{
				validateBottom = ValidateBottom(idElemento, xInitial,yInitial, widthElement, heigthElement, typeElement);
				}
				if (validateBottom == false) {
					totalUnreacheable = totalUnreacheable + 1;
					System.out.println("aqui");
				}
			}
			// System.out.println("type "+typeElement+" x "+xInitial+" y "+yInitial+" widthElement "+widthElement+
			// " heigthElement "+heigthElement);
		}

		double percentUnreachables = totalUnreacheable
				/ ((double) elementsSelected.size());

		return percentUnreachables;
	}

	public boolean ValidateBottomBlocks(int idElemento, int xInitial, int yInitial,int widthElement, int heigthElement, int typeElement) {

		for (int i = xInitial - 5; i < (xInitial + widthElement + 5 - 1); i++) {
			if (i < 0 && i > 14) {
				continue;
			} else {
				int jndex = 4;
				int jini = 0;
				if (i < xInitial ) {
					jndex = jndex - (xInitial - i - 1);
					
				} else if (i > (xInitial + widthElement - 1)) {
					jndex = jndex - (i - (xInitial + widthElement - 1)-1);
				} else {
					jini = heigthElement;
					
				}
				int y = yInitial - heigthElement + 1;
				System.out.println("y jini jndex"+y+jini+jndex);
				for (int j = y + jini; j < (y + jini + jndex); j++) {
					if (mapLevel[i][j] != (byte) (0)) {
						System.out.println("xdfdfdsfi j "+i+" "+j);
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean ValidateBottom(int idElemento, int xInitial, int yInitial,int widthElement, int heigthElement, int typeElement) {

		for (int i = xInitial - 5; i < (xInitial + widthElement + 5 - 1); i++) {
			if (i < 0 && i > 14) {
				continue;
			} else {
				int jndex = 4;
				int jini = 0;
				if (i < xInitial ) {
					jndex = jndex - (xInitial - i - 1);
					
				} else if (i > (xInitial + widthElement - 1)) {
					jndex = jndex - (i - (xInitial + widthElement - 1)-1);
				} else {
					continue;
					
				}
				int y = yInitial - heigthElement + 1;
				System.out.println("y jini jndex"+y+jini+jndex);
				for (int j = y + jini; j < (y + jini + jndex); j++) {
					if (mapLevel[i][j] != (byte) (0)) {
						System.out.println("xdfdfdsfi j "+i+" "+j);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	@Override
	public double compute(Level toCompare) {
		return compute();
	}

}
