package logic;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A two-dimensional vector.
 * @author Adel Hassan
 *
 */
public class Vector {
	protected double xComp;
	protected double yComp;
	public Vector() {
		this(0, 0);
	}
	/**
	 * Creates a new two-dimensional vector
	 * @param x x-component of vector
	 * @param y y-component of vector
	 */
	public Vector(double x, double y) {
		this.xComp = x;
		this.yComp = y;
	}
	/**
	 * Returns the magnitude of this vector
	 * @return magnitude
	 */
	public double getMagnitude() {
		return Math.sqrt(Math.pow(xComp, 2) + Math.pow(yComp, 2));
	}
	
	/**
	 * @return the xComp
	 */
	public double getxComp() {
		return xComp;
	}
	/**
	 * @return the yComp
	 */
	public double getyComp() {
		return yComp;
	}
	@Override
	public String toString() {
		return "<"+xComp+", "+yComp+">";
	}
	
	/**
	 * Adds all of the vectors in an array list of vectors and returns the resultant
	 * vector
	 * @param forces array list of vectors to add
	 * @return resultant vector
	 */
	public static Vector add(ArrayList<Force> forces) {
		// loop through vectors array, adding up x's and y's
		double xSum = 0;
		double ySum = 0;
		for(Vector v: forces) {
			xSum += v.xComp;
			ySum += v.yComp;
		}
		return new Vector(xSum, ySum);
	}
}
