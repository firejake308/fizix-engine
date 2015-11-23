package logic;

import java.util.ArrayList;
import java.util.ListIterator;

public abstract class PhysicsObject {
	private double x;
	private double y;
	private Vector velocity;
	private Vector acceleration;
	private double mass;
	private double charge;
	private ArrayList<Force> forces;
	private ListIterator<Force> forcesIter;
	
	public PhysicsObject(double x, double y, double mass, double charge) {
		// initialize instance variables
		this.x = x;
		this.y = y;
		this.mass = mass;
		this.charge = charge;
		this.velocity = new Vector(0, 0);
		this.acceleration = new Vector(0, 0);
		forces = new ArrayList<Force>();
		forcesIter = forces.listIterator();
		
		//stuff that apply to all objects
		if(!Universe.allObjects.isEmpty()) {
			// add to gravitational field
			for(PhysicsObject obj: Universe.allObjects) {
				new GravitationalForce(this, obj);
				new GravitationalForce(obj, this);
			}
			// add to electric field
			if(charge != 0)
				for(PhysicsObject obj: Universe.allObjects) {
					new ElectricForce(this, obj);
					new ElectricForce(obj, this);
				}
		}
		Universe.allObjects.add(this);
	}
	
	public PhysicsObject() {
		this(0, 0, 0, 0);
	}
	
	/**
	 * @return the x-coordinate of the object
	 */
	public double getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @return the y-coordinate of the object
	 */
	public double getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * @return the mass
	 */
	public double getMass() {
		return mass;
	}

	/**
	 * @return the charge
	 */
	public double getCharge() {
		return charge;
	}
	/**
	 * Calculates the kinetic energy of this object
	 * @return kinetic energy
	 */
	public double getKineticEnergy() {
		return mass * Math.pow(velocity.getMagnitude(), 2) / 2;
	}
	/**
	 * Calculates the linear momentum of this object.
	 * @return linear momentum
	 */
	public double getMomentum() {
		return mass * velocity.getMagnitude();
	}
	public void applyForce(Force f) {
		forcesIter.add(f);
	}
	@Override
	public String toString() {
		return "mass: "+mass+" kg, charge: "+charge+" C, at("+x+", "+y+")\n"
				+"Forces: "+forces;
	}
	/**
	 * Updates the status of this object after <code>time</code> seconds have
	 * passed
	 * @param time seconds elapsed since last update
	 */
	public void update(double time) {
		// update forces
		// gravity
		forcesIter = forces.listIterator();
		while(forcesIter.hasNext()) {
			Force f = forcesIter.next();
			if(f instanceof GravitationalForce) {
				PhysicsObject agent = f.getAgent();
				((GravitationalForce) f).update();
			}
			else if(f instanceof ElectricForce) {
				PhysicsObject agent = f.getAgent();
				forcesIter.set(new ElectricForce(agent, this));
			}
		}
		
		// update acceleration using net force
		Vector netForce = Vector.add(forces);
		double dx = netForce.getxComp()/mass;
		double dy = netForce.getyComp()/mass;
		acceleration = new Vector(acceleration.getxComp() + dx, acceleration.getyComp() + dy);
		// update velocity using acceleration
		velocity = new Vector(velocity.getxComp()+acceleration.getxComp()*time, 
				velocity.getyComp()+acceleration.getyComp()*time);
		// update position using velocity
		x += velocity.getxComp() * time;
		y += velocity.getyComp() * time;
	}
}
