package logic;

public class Force extends Vector{
	protected PhysicsObject agent;
	protected PhysicsObject object;
	/**
	 * Creates a new force exerted by <code>agent</code> on <code>obj</code>
	 * represented by the vector <x, y>
	 * @param x x-component of the force
	 * @param y y-component of the force
	 * @param agent object exerting the force
	 * @param obj object acted upon
	 */
	public Force(double x, double y, PhysicsObject agent, PhysicsObject obj) {
		super(x, y);
		
		this.agent = agent;
		this.object = obj;
		obj.applyForce(this);
	}
	
	public Force(Vector v, PhysicsObject agent, PhysicsObject obj) {
		this(v.getxComp(), v.getyComp(), agent, obj);
	}
	
	public PhysicsObject getAgent() {
		return agent;
	}
}
