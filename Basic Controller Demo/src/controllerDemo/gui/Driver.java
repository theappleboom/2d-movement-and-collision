package controllerDemo.gui;

import controllerDemo.controls.MainController;

public class Driver {

	public static void main(String[] args) throws InterruptedException {
		DemoFrame mainView;
		MainController controller;
		
		mainView = new DemoFrame();
		controller = new MainController(mainView);
		
		while(true) {
			controller.runProg();
		}
	}
}
