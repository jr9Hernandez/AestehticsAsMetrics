package graphBuilder;

import beauty.Elements;

public class BlockNode {

	private int typeElement;
	private int id;
	private int x;
	private int y;
	private int idElement;
	private Elements element;
	public BlockNode(int x,int y, int id, int idElementToPlace, int idElement){
		this.x=x;
		this.y=y;
		this.id=id;
		this.typeElement=idElementToPlace;
		this.idElement=idElement;
	};
	public BlockNode(int x,int y, int id, int idElementToPlace, Elements element){
		this.x=x;
		this.y=y;
		this.id=id;
		this.typeElement=idElementToPlace;
		this.element=element;
	};
	public BlockNode(BlockNode obj2)
	{
		this.x=obj2.x;
		this.y=obj2.y;
		this.id=obj2.id;
		this.typeElement=obj2.typeElement;
		this.idElement=obj2.idElement;
	}
	public void setX(int x)
	{
		this.x=x;
	}
	public void setY(int y)
	{
		this.y=y;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public int getID()
	{
		return id;
	}
	public int getType()
	{
		return typeElement;
	}
	public int getIdElement() {
		return idElement;
	}
	public Elements getElement() {
		return element;
	}
}
