package controllerDemo.controls;

import java.awt.Color;

import controllerDemo.gui.DemoFrame;

public class MainController {
	private DemoFrame view;
	private DemoObject obstacle1, obstacle2;
	private volatile PlayerObject player;
	
	public MainController(DemoFrame view) {
		obstacle1 = new DemoObject(Color.GREEN, 100, 290, 10, true);
		obstacle2 = new DemoObject(Color.ORANGE, 300, 250, 50, true);
		player = new PlayerObject(Color.BLUE, 50, 270, 30, true);
		
		//give the view the objects
		view.addObject(obstacle1);
		view.addObject(obstacle2);
		view.addObject(player);
		view.addObject(player.getAttackSphere());
		
		this.view = view;
	}
	
	public void runProg() throws InterruptedException {
		player.verticalMovement();
		
		//tell the view about these
		view.setPlayerAttacked(player.attackControl(obstacle1, obstacle2));
		view.setPlayerCollided(player.hasCollided(obstacle1) || player.hasCollided(obstacle2));
		view.repaint();
		
		Thread.sleep(17);
	}
	
	public PlayerObject getPlayer() {
		return player;
	}
}
