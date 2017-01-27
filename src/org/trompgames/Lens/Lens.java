package org.trompgames.Lens;

import org.trompgames.utils.Location;

public class Lens {

	private LensType type;
	private Location loc;
	private int height;
	private int width;
	
	private int focus;
	
	public Lens(LensType type, Location loc, int width, int height){
		this.type = type;
		this.loc = loc;
		this.width = width;
		this.height = height;
		focus = height/2;
	}
	
	public LensType getLensType(){
		return type;
	}
	
	public Location getLocation(){
		return loc;
	}
	
	
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void setWidth(int width){
		this.width = width;
	}
	
	public void setHeight(int height){
		this.height = height;
	}
	
	public int getFocus(){
		return focus;
	}
	
}
