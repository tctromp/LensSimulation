package org.trompgames.Lens;

import org.trompgames.utils.Location;


public class SimObject {

	
	private Location loc;
	private Location rayOrigin;
	private int height;
	
	
	public SimObject(Location loc, int height){
		this.loc = loc;
		this.height = height;	
		this.rayOrigin = new Location(loc.getX(), loc.getY() - height);//Height is subtracted due to Java's cordinate system
	}
	
	public Location getLocation(){
		return loc;
	}
	
	public void setLocation(Location loc){
		this.loc = loc;
		this.rayOrigin = new Location(loc.getX(), loc.getY() - height);//Height is subtracted due to Java's cordinate system

	}
	
	public int getHeight(){
		return height;
	}
	
	public Location getRayOriginLocation(){
		return rayOrigin;
	}
	
	
}
