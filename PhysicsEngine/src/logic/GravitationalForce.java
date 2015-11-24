package logic;

public class GravitationalForce extends Force {
	private static final double G = 6.67408e-11;

	public GravitationalForce(PhysicsObject agent, PhysicsObject obj) {
		super(getForceBetween(agent, obj), agent, obj);
	}
	/**
	 * Updates the gravitational force based on the current positions of
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
		double m1 = obj1.getMass();
		double m2 = obj2.getMass();
		return G*m1*m2/Math.pow(r, 2);
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
		double angle = Math.atan2(agent.getY()-obj.getY(), agent.getX()-obj.getX());
		double x = magnitude*Math.cos(angle);
		double y = magnitude*Math.sin(angle);
		if(angle == Math.PI/2 || angle == -Math.PI)
			x = 0;
		else if(angle == Math.PI)
			y = 0;
		return new Vector(x, y);
	}
}
