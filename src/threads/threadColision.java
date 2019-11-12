package threads;

import controller.Controller;

public class threadColision extends Thread{

	Controller c;

	public threadColision(Controller mc) {
		c = mc;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				c.Colision();
				sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
