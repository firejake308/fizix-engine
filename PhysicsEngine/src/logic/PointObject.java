package logic;
/**
 * An object that exists at a point and ignores all collisions
 * @author Adel Hassan
 *
 */
public class PointObject extends PhysicsObject{
	public PointObject(double x, double y, double mass, double charge) {
		super(x, y, mass, charge);
	}
}
