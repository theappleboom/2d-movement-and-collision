package controllerDemo.controls;

import java.awt.Color;

public class DemoObject {
	private Color color;
	protected volatile double xPos, yPos, radius;
	private volatile boolean exists;
	
	public DemoObject(Color color, double xPos, double yPos, double radius, boolean exists) {
		this.color = color;
		this.xPos = xPos;
		this.yPos = yPos;
		this.radius = radius;
		this.exists = exists;
	}

	public boolean hasCollided(DemoObject other) {
		double minDistance, actualDistance;
		boolean collided;
		
		if(!this.exists || !other.isExists()) {
			collided = false;
		}
		else {
			//if distance is less than the combination of the 2 radii
			minDistance = this.radius + other.getRadius();
			
			actualDistance = Math.sqrt(Math.pow((other.getXPos() - this.xPos), 2)
					+ Math.pow((other.getYPos() - this.yPos), 2));
			
			if(minDistance >= actualDistance) {
				collided = true;
			}
			else {
				collided = false;
			}
		}
		
		return collided;
	}

	public Color getColor() {
		return color;
	}

	public void setXPos(double xPos) {
		this.xPos = xPos;
	}

	public void setYPos(double yPos) {
		this.yPos = yPos;
	}

	public boolean isExists() {
		return exists;
	}

	public void setExists(boolean exists) {
		this.exists = exists;
	}

	public double getXPos() {
		return xPos;
	}
	
	public double getYPos() {
		return yPos;
	}
	
	public double getRadius() {
		return radius;
	}
}
