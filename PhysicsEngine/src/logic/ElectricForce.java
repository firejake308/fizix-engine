package logic;

public class ElectricForce extends Force {
	private static final double K = 8.99e9;
	
	public ElectricForce(PhysicsObject agent, PhysicsObject obj) {
		super(getForceBetween(agent, obj), agent, obj);
	}
	
	/**
	 * Updates the electric force based on the current positions of
	 * <code>agent</code> and <code>object</code>
	 */
	public void update() {
		Vector v = getForceBetween(agent, object);
		xComp = v.getxComp();
		yComp = v.getyComp();
	}
	
	/**
	 * A helper method that calculates the magnitude of the gravitational force
	 * between 2 objects
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	private static double magnitudeBetween(PhysicsObject obj1, PhysicsObject obj2) {
		double r = Math.sqrt(Math.pow(obj1.getX()-obj2.getX(), 2) + Math.pow(obj1.getY()-obj2.getY(), 2));
		double q1 = obj1.getCharge();
		double q2 = obj2.getCharge();
		return K*Math.abs(q1*q2)/Math.pow(r, 2);
	}
	/**
	 * A helper method that calculates the vector of the gravitational force
	 * exerted by <code>agent</code> on <code>object</code>. The vector that is
	 * returned points in the direction of <code>agent</code>.
	 * @param agent object exerting gravitational force
	 * @param obj object being pulled by gravity
	 * @return vector of resulting force
	 */
	private static Vector getForceBetween(PhysicsObject agent, PhysicsObject obj) {
		double magnitude = magnitudeBetween(agent, obj);
		double angle = Math.atan2(obj.getY()-agent.getY(), obj.getX()-agent.getX());
		if(agent.getCharge()*obj.getCharge() > 0)
			angle = Math.PI + angle;
		double x = magnitude*Math.cos(angle);
		double y = magnitude*Math.sin(angle);
		if(angle == Math.PI/2 || angle == -Math.PI)
			x = 0;
		else if(angle == Math.PI)
			y = 0;
		return new Vector(x, y);
	}
}
