package controllerDemo.controls;

import java.awt.Color;

public class PlayerObject extends DemoObject {
	private boolean moveLeft, moveRight, canJump, canAttack; 
	private volatile boolean jumping, attacking;
	private int attackTime;
	private DemoObject attackSphere;
	final private double SPEED = 20;
	final private double JUMP_SPEED = 1;
	final private double MAX_HEIGHT = 200;
	final private int ATTACK_LENGTH = 20;
	
	public PlayerObject(Color color, double xPos, double yPos, double radius, boolean exists) {
		super(color, xPos, yPos, radius, exists);
		
		attackSphere = new DemoObject(Color.RED, this.xPos + this.radius + 10, this.yPos, 20, false);
		
		canJump = true;
		jumping = false;
		canAttack = true;
		attacking = false;
		attackTime = 0;
	}

	public boolean verticalMovement() {
		boolean moving;
		
		if(jumping) {
			//move up
			yPos -= JUMP_SPEED;
			attackSphere.setYPos(this.yPos);
			moving = true;
		}
		else {
			if(!canJump) {
				//fall down
				yPos += JUMP_SPEED;
				attackSphere.setYPos(this.yPos);
				moving = true;
			}
			else {
				//do nothing
				moving = false;
			}
		}
		
		jumpBoundaries();
		
		return moving;
	}
	
	public boolean attackControl(DemoObject o1, DemoObject o2) {
		boolean hitSomething;
		
		if(attackTime > 0) {
			attackTime--;
			
			if(attackTime <= 0) {
				attackSphere.setExists(false);
				canAttack = true;
				attacking = false;
				attackTime = 0;
				
				hitSomething = false;
			}
			else {
				if(attackSphere.hasCollided(o1) || attackSphere.hasCollided(o2)) {
					hitSomething = true;
				}
				else {
					hitSomething = false;
				}
			}
		}
		else {
			hitSomething = false;
		}
		
		return hitSomething;
	}
	
	public boolean moveLeft() {
		return move(true);
	}
	
	public boolean moveRight() {
		return move(false);
	}
	
	public boolean jump() {
		boolean alreadyJumping;
		
		if(jumping) {
			alreadyJumping = true;
		}
		else {
			if(canJump) {
				jumping = true;
				canJump = false;
				alreadyJumping = false;
			}
			else {
				alreadyJumping = true;
			}
		}
		
		return !alreadyJumping;
	}
	
	public boolean attack() {
		boolean alreadyAttacking;
		
		if(attacking) {
			alreadyAttacking = true;
		}
		else {
			if(canAttack) {
				attacking = true;
				canAttack = false;
				attackSphere.setExists(true);
				attackTime = ATTACK_LENGTH;
				alreadyAttacking = false;
			}
			else {
				alreadyAttacking = true;
			}
		}
		
		return !alreadyAttacking;
	}
	
	private boolean move(boolean leftOrRight) {
		//true if left, false if right
		if(leftOrRight) {
			xPos += -SPEED;
			attackSphere.setXPos(this.xPos + this.radius + 10);
		}
		else {
			xPos += SPEED;
			attackSphere.setXPos(this.xPos + this.radius + 10);
		}
		
		return wall(leftOrRight);
	}
	
	private boolean wall(boolean leftOrRight) {
		boolean walledIn;
		
		//true if left, false if right
		if(leftOrRight) {
			if(xPos < radius) {
				xPos = radius;
				attackSphere.setXPos(this.xPos + this.radius + 0.5);
				walledIn = true;
			}
			else {
				walledIn = false;
			}
		}
		else {
			if(xPos > (500 - radius)) {
				xPos = 500 - radius;
				attackSphere.setXPos(this.xPos + this.radius + 0.5);
				walledIn = true;
			}
			else {
				walledIn = false;
			}
		}
		
		//return the opposite because it's true if you moved
		return !walledIn;
	}
	
	private boolean jumpBoundaries() {
		boolean changedDirection;
		
		if(jumping) {
			//hit the top of the jump?
			if(yPos <= MAX_HEIGHT) {
				yPos = MAX_HEIGHT;
				attackSphere.setYPos(this.yPos);
				jumping = false;
				changedDirection = true;
			}
			else {
				changedDirection = false;
			}
		}
		else {
			//hit the floor?
			if(yPos >= 270) {
				yPos = 270;
				attackSphere.setYPos(this.yPos);
				canJump = true;
				changedDirection = true;
			}
			else {
				changedDirection = false;
			}
		}
		
		return changedDirection;
	}
	
	public DemoObject getAttackSphere() {
		return attackSphere;
	}
}