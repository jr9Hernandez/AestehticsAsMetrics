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
	private int[][]hotZoneElements;

	public Reacheability(int width, int height, Level level) {

		super(width, height, level);
		mapLevel = level.getMap();
	}

	@Override
	public double compute() {

		// calculation of scanning of level
		ScanLabeledLevel objScanLevel = new ScanLabeledLevel(level, width,height);
		elementsSelected = objScanLevel.DeterminePositions();
		hotZoneElements=objScanLevel.hotZones(elementsSelected);

		// System.out.println("Symmetry "+symmetryValue);

		// testing the captured elements
		double totalUnreacheable = 0;
		double totalUnreacheableFloating = 0;
		Iterator<BlockNode> it = elementsSelected.iterator();
		while (it.hasNext()) {
			BlockNode elemento = it.next();

			int xInitial = elemento.getX();
			int yInitial = elemento.getY();
			int widthElement = (elemento.getElement()).getWidth();
			int heigthElement = (elemento.getElement()).getHeigth();
			int typeElement = (elemento.getElement()).getTypeElem();
			int idElemento = (elemento.getElement()).getIdElem();

			boolean validateBottom = true;
			if (typeElement != 0) {

				if (typeElement == 8 || typeElement == 2) {
					validateBottom = ValidateBottomBlocks(idElemento, xInitial,
							yInitial, widthElement, heigthElement, typeElement);
				} else {
					validateBottom = ValidateBottom(idElemento, xInitial,
							yInitial, widthElement, heigthElement, typeElement);
				}
				if (validateBottom == false) {
					totalUnreacheable = totalUnreacheable + 1;
					System.out.println("aqui");
				}
			}
			// System.out.println("type "+typeElement+" x "+xInitial+" y "+yInitial+" widthElement "+widthElement+
			// " heigthElement "+heigthElement);
		}

		if (totalUnreacheable == 0) {

			boolean validateAllFloatingElements = ValidateAllFloatingElements();
			if (validateAllFloatingElements==false) {
				totalUnreacheable=countFloatingElements(); 
				System.out.println("floatingssw "+totalUnreacheable);
			}


		}
		double percentUnreachables = totalUnreacheable
				/ ((double) elementsSelected.size());

		return percentUnreachables;
	}

	public int countFloatingElements() {
		int counter = 0;
		Iterator<BlockNode> it = elementsSelected.iterator();
		while (it.hasNext()) {
			BlockNode elemento = it.next();
			if (elemento.getType() == 7 || elemento.getType() == 8|| elemento.getType() == 10 || elemento.getType() == 11) {
				counter++;
			}
		}
		return counter;
	}

	public boolean ValidateAllFloatingElements() {
		System.out.println("validatingallelements");
		Iterator<BlockNode> it = elementsSelected.iterator();
		while (it.hasNext()) {
			BlockNode elemento = it.next();

			int xInitial = elemento.getX();
			int yInitial = elemento.getY();
			int widthElement = (elemento.getElement()).getWidth();
			int heigthElement = (elemento.getElement()).getHeigth();
			int typeElement = (elemento.getElement()).getTypeElem();
			int idElemento = (elemento.getElement()).getIdElem();

			if (typeElement == 7 || typeElement == 8 || typeElement == 10 || typeElement == 11) {

				System.out.println("typeElement " + " " + typeElement);
				for (int i = xInitial - 9; i <= (xInitial + widthElement + 9 - 1); i++) {
					if (i < 0 || i > ((level.getxExit()-8))) {
						continue;
					} else {
						int jndex = 5;
						int jini = 0;
						if ((i < xInitial - 6)|| (i > (xInitial + widthElement +6 - 1))) {
							jini = 0;
							jndex = 1;
						} else if (i < xInitial) {
							// jndex = jndex - (xInitial - i - 1);
							//jndex = 4;

						} else if (i > (xInitial + widthElement - 1)) {
							//jndex = jndex - (i - (xInitial + widthElement - 1) - 1);
							//jndex = 4;
						} else {
							continue;
						}
						int y = yInitial - heigthElement + 1;
						for (int j = y + jini; j < (y + jini + jndex); j++) {
							System.out.println("y jini jindex" + i + " " + j + " ");
							if (j < 15) {
								if ((mapLevel[i][j] != (byte) (0)) && (mapLevel[i][j] != (byte)25) && (hotZoneElements[i][j]!=7) && (hotZoneElements[i][j]!=8) && (hotZoneElements[i][j]!=10) && (hotZoneElements[i][j]!=11) ) {
									return true;
								}
							}
						}
					}
				}

			}
		}
		System.out.println("falseo");
		return false;
	}

	public boolean ValidateBottomBlocks(int idElemento, int xInitial, int yInitial, int widthElement, int heigthElement, int typeElement) {
		System.out.println("typeElement " + " " + typeElement);
		for (int i = xInitial - 9; i <= (xInitial + widthElement + 9 - 1); i++) {
			if (i < 0 || i > ((level.getxExit()-8))) {
				continue;
			} else {
				int jndex = 5;
				int jini = 0;
				if ((i < xInitial - 6)|| (i > (xInitial + widthElement + 6 - 1))) {
					jini = 0;
					jndex = 1;
				} else if (i < xInitial) {
					// jndex = jndex - (xInitial - i - 1);
					//jndex = 4;

				} else if (i > (xInitial + widthElement - 1)) {
					//jndex = jndex - (i - (xInitial + widthElement - 1) - 1);
					//jndex = 4;
				} else {
					jini = heigthElement;
					//jndex = 4;
				}
				int y = yInitial - heigthElement + 1;
				for (int j = y + jini; j < (y + jini + jndex); j++) {
					System.out.println("y jini jindex" + i + " " + j + " ");
					if (j < 15) {
						if (mapLevel[i][j] != (byte) (0)) {
							return true;
						}
					}
				}
			}
		}
		System.out.println("falseo");
		return false;
	}

	public boolean ValidateBottom(int idElemento, int xInitial, int yInitial, int widthElement, int heigthElement, int typeElement) {
		System.out.println("typeElement " + " " + typeElement);
		for (int i = xInitial - 9; i <= (xInitial + widthElement + 9 - 1); i++) {
			if (i < 0 || i > ((level.getxExit()-8))) {
				continue;
			} else {
				int jndex = 5;
				int jini = 0;
				if ((i < xInitial - 6)|| (i > (xInitial + widthElement +6 - 1))) {
					jini = 0;
					jndex = 1;
				} else if (i < xInitial) {
					// jndex = jndex - (xInitial - i - 1);
					//jndex = 4;

				} else if (i > (xInitial + widthElement - 1)) {
					//jndex = jndex - (i - (xInitial + widthElement - 1) - 1);
					//jndex = 4;
				} else {
					continue;
				}
				int y = yInitial - heigthElement + 1;
				for (int j = y + jini; j < (y + jini + jndex); j++) {
					System.out.println("y jini jindex" + i + " " + j + " ");
					if (j < 15) {
						if (mapLevel[i][j] != (byte) (0)) {
							return true;
						}
					}
				}
			}
		}
		System.out.println("falseo");
		return false;
	}

	@Override
	public double compute(Level toCompare) {
		return compute();
	}

}
