package controllerDemo.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;



import controllerDemo.controls.DemoObject;
import controllerDemo.controls.PlayerObject;

public class DemoFrame extends JFrame {
	private DisplayPanel display;
	private JButton leftMove, rightMove, jump, attack;
	private LinkedList<DemoObject> objects;
	private volatile boolean playerCollided, playerAttacked;
	
	public DemoFrame() {
		super("Basic Controller Demo");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		
		display = new DisplayPanel();
		leftMove = new JButton("Left");
		rightMove = new JButton("Right");
		jump = new JButton("Jump");
		attack = new JButton("Attack");
		
		objects = new LinkedList<DemoObject>();
		
		playerCollided = false;
		playerAttacked = false;
		
		leftMove.addActionListener(new LeftListener());
		rightMove.addActionListener(new RightListener());
		jump.addActionListener(new JumpListener());
		attack.addActionListener(new AttackListener());
		
		this.add(display);
		this.add(leftMove);
		this.add(rightMove);
		this.add(jump);
		this.add(attack);
		
		pack();
		setVisible(true);
	}
	
	public boolean addObject(DemoObject o) {
		return objects.add(o);
	}
	
	public void setPlayerCollided(boolean playerCollided) {
		this.playerCollided = playerCollided;
	}

	public void setPlayerAttacked(boolean playerAttacked) {
		this.playerAttacked = playerAttacked;
	}
	
	private class LeftListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			((PlayerObject) objects.get(2)).moveLeft();
			repaint();
		}
		
	}
	
	private class RightListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			((PlayerObject) objects.get(2)).moveRight();
			repaint();
		}
		
	}
	
	private class JumpListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			((PlayerObject) objects.get(2)).jump();
			repaint();
		}
		
	}
	
	private class AttackListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			((PlayerObject) objects.get(2)).attack();
		}
		
	}
	
	private class DisplayPanel extends JComponent {
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(500, 500);
		}
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			//Collision indicator
			if(playerCollided || playerAttacked) {
				if(playerCollided) {
					g.setColor(Color.RED);
				}
				else {
					g.setColor(Color.YELLOW);
				}
			}
			else {
				g.setColor(Color.GREEN);
			}
			
			g.fillRect(10, 10, 10, 10);
			
			//Objects
			for(DemoObject o: objects) {
				g.setColor(o.getColor());
				
				if(o.isExists()) {
					g.fillOval((int) (o.getXPos() - o.getRadius()), (int) (o.getYPos() - o.getRadius()), (int) (o.getRadius() * 2), (int) (o.getRadius() * 2));
				}
			}
		}
	}
}