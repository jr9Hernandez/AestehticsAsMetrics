package br.ufv.dpi.labeledlevels;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import dk.itu.mario.level.Level;

public class LabeledLevel {
	
	public static final int TYPE_OVERGROUND = 0;
    public static final int TYPE_UNDERGROUND = 1;
    public static final int TYPE_CASTLE = 2;
    
    final static Charset ENCODING = StandardCharsets.UTF_8;
    
    public static final String path = "labeledlevels/";
    public static final String folderLevels = "levels/";
    public static final String folderLabels = "labels7likert/";
    
    private double difficulty = -1;
	private double visualAesthetics = -1;
    private double fun = -1; 
    private Level level;
    private String name;
    
    public LabeledLevel(String name) {
    	this.name = name;
    	loadLevel();
    }

	public Level loadLevel() {    	
		
		try {
			FileInputStream fis = new FileInputStream(path + folderLevels + name);
			DataInputStream dis = new DataInputStream(fis);			
			level = Level.load(dis);
			fixWalls(level, level.getWidth(), level.getHeight());		
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			File file = new File(path + folderLabels + name);
			
			if(file.exists()) 
			{
				Path p = Paths.get(path + folderLabels + name);
				this.difficulty = Double.valueOf(Files.readAllLines(p, ENCODING).get(0));
				this.visualAesthetics = Double.valueOf(Files.readAllLines(p, ENCODING).get(1));
				this.fun = Double.valueOf(Files.readAllLines(p, ENCODING).get(2));
			}
			else
			{
				System.out.println("File with labels does not exist for " + name);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return level;
	}
	
	public void convertFrom9LikertTo7Likert() {	
		this.difficulty = 6.0/8.0 * (this.difficulty - 1) + 1;
		this.visualAesthetics = 6.0/8.0 * (this.visualAesthetics - 1) + 1;
		this.fun = 6.0/8.0 * (this.fun - 1) + 1;
		
		PrintWriter writer;
		try {
			writer = new PrintWriter(path + "labels2/" + name, "UTF-8");
			writer.println(this.difficulty); 
			writer.println(this.visualAesthetics);
			writer.println(this.fun);
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	private void fixWalls(Level level, int width, int height)
	{
		boolean[][] blockMap = new boolean[width + 1][height + 1];
		for (int x = 0; x < width + 1; x++)
		{
			for (int y = 0; y < height + 1; y++)
			{
				int blocks = 0;
				for (int xx = x - 1; xx < x + 1; xx++)
				{
					for (int yy = y - 1; yy < y + 1; yy++)
					{
						if (level.getBlockCapped(xx, yy) == (byte) (1 + 9 * 16)) blocks++;  //GROUND (Bloco marron)
					}
				}
				blockMap[x][y] = blocks == 4;                
			}
		}
		
		blockify(level, blockMap, width + 1, height + 1);
	}

	private void blockify(Level level, boolean[][] blocks, int width, int height)
	{
		int to = 0;

		boolean[][] b = new boolean[2][2];
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				for (int xx = x; xx <= x + 1; xx++)
				{
					for (int yy = y; yy <= y + 1; yy++)
					{
						int _xx = xx;
						int _yy = yy;
						if (_xx < 0) _xx = 0;
						if (_yy < 0) _yy = 0;
						if (_xx > width - 1) _xx = width - 1;
						if (_yy > height - 1) _yy = height - 1;
						b[xx - x][yy - y] = blocks[_xx][_yy];
					}
				}

				if (b[0][0] == b[1][0] && b[0][1] == b[1][1])
				{
					if (b[0][0] == b[0][1])
					{
						if (b[0][0])
						{
							level.setBlock(x, y, (byte) (1 + 9 * 16 + to)); //bloco marron do meio. Ch�o
						}
					}
					else
					{
						if (b[0][0])
						{
							level.setBlock(x, y, (byte) (1 + 10 * 16 + to));//Bloco com grama inferior
						}
						else
						{
							level.setBlock(x, y, (byte) (1 + 8 * 16 + to)); //Bloco com grama superior
						}
					}
				}
				else if (b[0][0] == b[0][1] && b[1][0] == b[1][1])
				{
					if (b[0][0])
					{
						level.setBlock(x, y, (byte) (2 + 9 * 16 + to));//Bloco de Grama lateral na direita
					}
					else
					{
						level.setBlock(x, y, (byte) (0 + 9 * 16 + to));//Bloco com grama lateral na esquerda
					}
				}
				else if (b[0][0] == b[1][1] && b[0][1] == b[1][0])
				{
					level.setBlock(x, y, (byte) (1 + 9 * 16 + to)); //Bloco todo marron. Ch�o sem grama.                    
				}
				else if (b[0][0] == b[1][0])
				{
					if (b[0][0])
					{
						if (b[0][1])
						{
							level.setBlock(x, y, (byte) (3 + 10 * 16 + to)); //Pequena quina inferior direita.                            
						}
						else
						{
							level.setBlock(x, y, (byte) (3 + 11 * 16 + to)); //Pequena quina inferior esquerda                            
						}
					}
					else
					{
						if (b[0][1])
						{
							level.setBlock(x, y, (byte) (2 + 8 * 16 + to)); //Quina superior direita. Inicio de um buraco                            
						}
						else
						{
							level.setBlock(x, y, (byte) (0 + 8 * 16 + to)); //Quina superior esquerda. Outro lado do buraco                             
						}
					}
				}
				else if (b[0][1] == b[1][1])
				{
					if (b[0][1])
					{
						if (b[0][0])
						{
							level.setBlock(x, y, (byte) (3 + 9 * 16 + to));  //Pequena quina superior direita                            
						}
						else
						{
							level.setBlock(x, y, (byte) (3 + 8 * 16 + to)); //Pequena quina superior esquerda 
						}
					}
					else
					{
						if (b[0][0])
						{
							level.setBlock(x, y, (byte) (2 + 10 * 16 + to)); //Quina inferior direita. Oposto a um buraco
						}
						else
						{
							level.setBlock(x, y, (byte) (0 + 10 * 16 + to)); //Quina inferior esquerda. Oposto a um buraco
						}
					}
				}
				else
				{
					level.setBlock(x, y, (byte) (0 + 1 * 16 + to)); //Blocos amarelos                    
				}
			}
		}
	}
	
    public double getDifficulty() {
		return difficulty;
	}

	public double getVisualAesthetics() {
		return visualAesthetics;
	}

	public double getFun() {
		return fun;
	}
	
	public Level getLevel() {
		return level;
	}
}
