package threads;

import controller.Controller;

public class threadMoveBall extends Thread {

	Controller c;

	public threadMoveBall(Controller mc) {
		c = mc;
	}

	@Override
	public void run() {
		int i = 0;
		while (c.getStop() != true) {
			if (i < c.getBalls().size()) {
				try {
					c.moveBall(i);
					if (c.getLevel() == 0)
						sleep(10);
					if (c.getLevel() == 1)
						sleep(15);
					if (c.getLevel() == 2)
						sleep(5);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				i++;
			} else {
				i = 0;
			}
		}
	}

}
