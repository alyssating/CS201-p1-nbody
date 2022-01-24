

/**
 * Celestial Body class for NBody
 * Modified from original Planet class
 * used at Princeton and Berkeley
 * @author ola
 *
 * If you add code here, add yourself as @author below
 *
 *
 */
public class CelestialBody {

	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;

	/**
	 * Create a Body from parameters	
	 * @param xp initial x position
	 * @param yp initial y position
	 * @param xv initial x velocity
	 * @param yv initial y velocity
	 * @param mass of object
	 * @param filename of image for object animation
	 */
	public CelestialBody(double xp, double yp, double xv,
			             double yv, double mass, String filename){
		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;

	}

	/**
	 *
	 * @return
	 */
	public double getX() {
		// TODO: complete method
		return myXPos;
	}

	/**
	 *
	 * @return
	 */
	public double getY() {
		// TODO: complete method
		return myYPos;
	}

	/**
	 * Accessor for the x-velocity
	 * @return the value of this object's x-velocity
	 */
	public double getXVel() {
		// TODO: complete method
		return myXVel;
	}
	/**
	 * Return y-velocity of this Body.
	 * @return value of y-velocity.
	 */
	public double getYVel() {
		// TODO: complete method
		return myYVel;
	}

	/**
	 *
	 * @return
	 */
	public double getMass() {
		// TODO: complete method
		return myMass;
	}

	/**
	 *
	 * @return
	 */
	public String getName() {
		// TODO: complete method
		return myFileName;
	}

	/**
	 * Return the distance between this body and another
	 * @param b the other body to which distance is calculated
	 * @return distance between this body and b
	 */
	public double calcDistance(CelestialBody b) {
		return Math.sqrt((this.myXPos - b.getX()) * (this.myXPos - b.getX()) + (this.myYPos - b.getY()) * (this.myYPos - b.getY()));
	}

	public double calcForceExertedBy(CelestialBody b) {
		return (6.67*1E-11) * ((this.myMass * b.getMass()) / (calcDistance(b) * calcDistance(b)));
	}

	public double calcForceExertedByX(CelestialBody b) {
		return ((calcForceExertedBy(b) * (b.getX() - this.myXPos)) / calcDistance(b));
	}
	public double calcForceExertedByY(CelestialBody b) {
		return ((calcForceExertedBy(b) * (b.getY() - this.myYPos)) / calcDistance(b));
	}

	public double calcNetForceExertedByX(CelestialBody[] bodies) {
		double sum = 0.0;

		for (CelestialBody b: bodies) {
			if (! b.equals(this)) {
				sum += calcForceExertedByX(b);
			}
		}

		return sum;
	}

	public double calcNetForceExertedByY(CelestialBody[] bodies) {
		double sum = 0.0;
		
		for (CelestialBody b: bodies) {
			if (! b.equals(this)) {
				sum += calcForceExertedByY(b);
			}
		}

		return sum;
	}

	public void update(double deltaT, 
			           double xforce, double yforce) {
		// TODO: complete method
		double ax;
		double ay;
		double nvx;
		double nvy;
		double nx;
		double ny;

		ax = xforce / this.myMass;
		ay = yforce / this.myMass;
		
		nvx = myXVel + deltaT * ax;
		nvy = myYVel + deltaT * ay;
		
		nx = myXPos + deltaT * nvx;
		ny = myYPos + deltaT * nvy;

		myXPos = nx;
		myYPos = ny;
		myXVel = nvx;
		myYVel = nvy;	
	}

	/**
	 * Draws this planet's image at its current position
	 */
	public void draw() {
		StdDraw.picture(myXPos,myYPos,"images/"+myFileName);
	}
}
