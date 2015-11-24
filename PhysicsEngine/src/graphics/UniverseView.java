package graphics;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

public class UniverseView extends JPanel {
	private LinkedList<Integer> gridX = new LinkedList<Integer>();
	private ListIterator<Integer> gridXIter = gridX.listIterator();
	private LinkedList<Integer> gridY = new LinkedList<Integer>();
	private ListIterator<Integer> gridYIter = gridY.listIterator();
	private int startX = -1;
	private int endX = 0;
	private int startY = -1;
	private int endY = 0;
	private int pixelsPerMeter = 100;
	
	public UniverseView() {
		setBackground(Color.WHITE);
		
		
		// add resize listener to update size of components
		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				// create grid
				for(int x = -getWidth()/2/pixelsPerMeter; x <= getWidth()/2/pixelsPerMeter+1; x++) {
					gridXIter.add(getWidth()/2 + x*pixelsPerMeter);
				}
				for(int y = -getHeight()/2/pixelsPerMeter; y <= getHeight()/2/pixelsPerMeter+1; y++) {
					gridYIter.add(getHeight()/2 + y*pixelsPerMeter);
				}
				repaint();
			}
		});
		
		//add mouse listener
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				// store location where mouse pressed
				startX = e.getX();
				startY = e.getY();
				System.out.println("pressed");
			}
			public void mouseReleased(MouseEvent e) {
				startX = -1;
				startY = -1;
			}
		});
		this.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				if(startX == -1 && startY == -1)
					return;
				// store location where mouse released
				endX = e.getX();
				endY = e.getY();
				
				// move grid lines according to delta mouse location
				gridXIter = gridX.listIterator();
				while(gridXIter.hasNext()) {
					Integer i = gridXIter.next();
					int newX = i + endX - startX;
					if(newX > getWidth())
						newX -= getWidth()/pixelsPerMeter*pixelsPerMeter;
					else if(newX < 0)
						newX += getWidth()/pixelsPerMeter*pixelsPerMeter;
					gridXIter.set(newX);
				}
				gridYIter = gridY.listIterator();
				while(gridYIter.hasNext()) {
					Integer i = gridYIter.next();
					int newY = i + endY - startY;
					if(newY > getHeight())
						newY -= getHeight()/pixelsPerMeter*pixelsPerMeter;
					else if(newY < 0)
						newY += getHeight()/pixelsPerMeter*pixelsPerMeter;
					gridYIter.set(newY);
				}
				startX = endX;
				startY = endY;
				repaint();
			}
		});
	}
	
	public void paint(Graphics g) {
		// clear field
		super.paint(g);
		
		// draw grid lines
		g.setColor(Color.DARK_GRAY);
		gridXIter = gridX.listIterator();
		while(gridXIter.hasNext()) {
			int x = gridXIter.next();
			g.drawLine(x, 0, x, getHeight());
		}
		gridYIter = gridY.listIterator();
		while(gridYIter.hasNext()) {
			int y = gridYIter.next();
			g.drawLine(0, y, getWidth(), y);
		}
	}
}
