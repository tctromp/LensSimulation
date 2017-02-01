package org.trompgames.Lens;

import org.trompgames.utils.Location;

public class LensHandler {

	
	private SimObject obj;
	private Lens lens;
	public LensHandler(){
		
		LensFrame frame = new LensFrame(this);
		
		int y = frame.getHeight()/2;
		
		lens = (new Lens(LensType.CONVEX, new Location(frame.getWidth()/2, y), 52, 200));//Make sure width and height are even
		obj = new SimObject(new Location(150, frame.getHeight()/2), 50);
		 
	}
	
	public SimObject getSimObject(){
		return obj;
	}
	
	public Lens getLense(){
		return lens;
	}
	
	
	
	public static class LensThread extends Thread{
		
		private LensHandler handler;
		
		
		public LensThread(LensHandler handler){
			this.handler = handler;
		}
		
		@Override
		public void run(){
			
			long lastTime = System.currentTimeMillis();
			while(true){
				
				
				
				
			}
			
			
			
		}
		
	}
	
	
	public static void main(String[] args) {
		
		LensHandler handler = new LensHandler();
		
	}
	
}
