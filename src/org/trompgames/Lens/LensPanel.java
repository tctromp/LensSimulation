package org.trompgames.Lens;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

import org.trompgames.utils.Location;

public class LensPanel extends JPanel{

	private LensFrame frame;
	
	private int midX = -1;
	private int midY = -1;	
	public LensPanel(LensFrame frame) {

		this.frame = frame;
		midX = frame.getWidth()/2;
		midY = frame.getHeight()/2;
	}
	
	private int xOffset = 0;
	private int yOffset = 0;
	
	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.WHITE);
		g2d.clearRect(0, 0, frame.getWidth(), frame.getHeight());
		
		//g2d.drawOval(100, 100, 50, 50);
		//g2.draw(new Ellipse2D.Double(x, y, rectwidth, rectheight));
		g2d.setColor(Color.BLACK);
		
		midX = frame.getWidth()/2;
		midY = frame.getHeight()/2;
		
		Lens lens = frame.getHandler().getLense();
		
		
		if(lens.getLensType().equals(LensType.CONVEX)){
			drawConvexLens(g2d, lens);			
		}
		
		
		
		
	}
	
	public void drawConvexLens(Graphics2D g2d, Lens lens){
		
		lens.setLocation(new Location(midX, midY));
		g2d.draw(new Ellipse2D.Double(lens.getLocation().getX() - lens.getWidth()/2, lens.getLocation().getY() - lens.getHeight()/2, lens.getWidth(), lens.getHeight()));	
		int focus = 0;

		focus = lens.getFocus();
		
		//xOffset += 1;
		
		
		int xLoc = frame.getHandler().getSimObject().getRayOriginLocation().getX() + xOffset;
		int yLoc = frame.getHandler().getSimObject().getRayOriginLocation().getY() + yOffset;
		
		
		//g2d.drawLine(0, this.getHeight()/2, this.getWidth(), this.getHeight()/2);
		
		
		//g2d.setColor(Color.RED);
		
		
		//MIDDLE
		//g2d.drawRect(midX, midY, 0, 0);
		
		//X
		g2d.drawLine(0, midY, frame.getWidth(), midY);
		
		//Y
		g2d.drawLine(midX, 0, midX, frame.getHeight());
		
		//Object
		g2d.setColor(Color.DARK_GRAY);
		SimObject obj = frame.getHandler().getSimObject();
		//NOTE: Height is subracted due to java's cordinate system
		g2d.drawLine(xLoc, obj.getLocation().getY(), xLoc, yLoc);
		
		//Focus
		g2d.setColor(Color.MAGENTA);
		Location focus1Loc = new Location(midX - focus, midY);
		Location focus2Loc = new Location(midX + focus, midY);
		g2d.drawRect(midX - focus - 3, midY - 3, 6, 6);
		g2d.drawRect(midX + focus - 3, midY - 3, 6, 6);


		
		//Line1 (Center then focus2)
		g2d.setColor(Color.RED);
		g2d.drawLine(xLoc, yLoc, midX, yLoc);
		
		double m1 = (1.0 * focus2Loc.getY() - yLoc)/(1.0 * focus2Loc.getX() - midX);
		int x1 = frame.getWidth();
		int y1 = (int) (m1*(x1 - midX) + yLoc);
		
		g2d.drawLine(midX, yLoc, x1, y1);

		
		//Line2 (focus1 then center)
		g2d.setColor(Color.BLUE);
		double m2 = (1.0 * focus1Loc.getY() - yLoc)/(1.0 * focus1Loc.getX() - xLoc);
		int x2 = midX;
		int y2 = (int) (m2*(x2 - focus1Loc.getX()) + focus1Loc.getY());
		
		g2d.drawLine(xLoc, yLoc, x2, y2);
		g2d.drawLine(x2, y2, frame.getWidth(), y2);
		
		m2 = 0;
		
		
		//Line3 (center)
		g2d.setColor(new Color(0, 255, 0));
		double m3 = (1.0 * midY - yLoc)/(1.0 * midX - xLoc);
		
		int x3 = frame.getWidth();
		int y3 = (int) (m3*(x3 - midX) + midY);
		
		
		g2d.drawLine(xLoc, yLoc, x3, y3);


		Location intersection = calculateIntersection(m1, x1, y1, m2, x2, y2);
		
		g2d.setColor(Color.BLACK);
		
		g2d.drawLine(intersection.getX(), intersection.getY(), intersection.getX(), midY);
		
		if(intersection.getX() < midX){
			float dash1[] = {10.0f};
			BasicStroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
			
			g2d.setStroke(dashed);
			
			
			g2d.setColor(Color.RED);
			g2d.drawLine(intersection.getX(), intersection.getY(), midX, yLoc);
			
			g2d.setColor(Color.BLUE);
			g2d.drawLine(intersection.getX(), intersection.getY(), midX, y2);
			
			g2d.setColor(Color.GREEN);
			g2d.drawLine(intersection.getX(), intersection.getY(), xLoc, yLoc);

		}
		
		
		
	}
	
	public Location calculateIntersection(double m1, int x1, int y1, double m2, int x2, int y2){
		
		double x = (m1*x1 - y1 - m2*x2 + y2)/(m1-m2);
		double y = m1*(x - x1) + y1;
		return new Location((int) x, (int) y);
	}
	
	
}
