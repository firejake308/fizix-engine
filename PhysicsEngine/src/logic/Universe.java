package logic;

import java.util.ArrayList;

import graphics.MainFrame;

public class Universe {
	/**
	 * A registry of all objects in existence in the universe.
	 */
	public static ArrayList<PhysicsObject> allObjects = new ArrayList<PhysicsObject>();

	public static void main(String[] args) {
		PointObject p1 = new PointObject(-2, 0, 10000000, 0);
		PointObject p2 = new PointObject(2, 0, 100, 0);
		MainFrame mainFrame = new MainFrame();
		
		final int FRAME_RATE = 60;
		final double FRAME_LENGTH = 1e9/FRAME_RATE;
		long lastFrameTime = System.nanoTime();
		while(true) {
			long now = System.nanoTime();
			long elapsedTime = now - lastFrameTime;
			lastFrameTime = now;
			
			update(elapsedTime/1e9);
			mainFrame.repaint();
			
			try {
				Thread.sleep((long) ((FRAME_LENGTH - (System.nanoTime() - lastFrameTime))/1e6));
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch(IllegalArgumentException e) {
				// last frame took too long
			}
		}
		
		// in case of debug, break comment
		/*
		PointObject p1 = new PointObject(-10, 0, 1000, 0);
		PointObject p2 = new PointObject(10, 0, 10, 0);
		System.out.println(p1);
		System.out.println(p2);
		
		p1.update(1);
		p2.update(1);
		
		System.out.println(p1);
		System.out.println(p2);
		
		p1.update(1);
		p2.update(1);
		
		System.out.println(p1);
		System.out.println(p2);
		
		p1.update(1);
		p2.update(1);
		
		System.out.println(p1);
		System.out.println(p2);
		*/
	}
	private static int x = 0;
	private static void update(double seconds) {
		x++;
		for(PhysicsObject o: allObjects) {
			o.update(seconds);
			// in case of debug, break comment
			//if(x % 60 == 0)
				//System.out.println(o);
		}
	}
}
