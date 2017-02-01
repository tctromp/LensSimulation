package org.trompgames.Lens;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import org.trompgames.utils.Location;

public class LensFrame extends JFrame{

	private LensPanel panel;
	private LensHandler handler;
	
	
	public LensFrame(LensHandler handler) {
		
		this.handler = handler;
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1000, 500);
		
		this.panel = new LensPanel(this);
		this.add(panel);
		
		this.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent event) {
				System.out.println(event.getKeyCode());
				int x = handler.getSimObject().getLocation().getX();
				int y = handler.getSimObject().getLocation().getY();
				if(event.getKeyCode() == 65 || event.getKeyCode() == 37){// a
					handler.getSimObject().setLocation(new Location(x-10, y));
				}else if(event.getKeyCode() == 68 || event.getKeyCode() == 39){// d
					handler.getSimObject().setLocation(new Location(x+10, y));
				}else if(event.getKeyCode() == 87 || event.getKeyCode() == 38){// w				
					handler.getSimObject().setLocation(new Location(x, y-10));
				}else if(event.getKeyCode() == 83 || event.getKeyCode() == 40){// s			
					handler.getSimObject().setLocation(new Location(x, y+10));
				}
				
				panel.repaint();
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent event) {
				//System.out.println(event.getKeyCode());
			}			
			
		});
		
		
		this.setVisible(true);
	}
	
	public LensHandler getHandler(){
		return handler;
	}
	
}
