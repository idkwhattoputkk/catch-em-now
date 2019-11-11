package threads;

import model.Balls;
import controller.Controller;
import javafx.geometry.Bounds;

public class BallsThread extends Thread{
	private Controller c;
	private Balls b;
	private Bounds bounds;
	public BallsThread(Controller controller, Balls balls,Bounds bounds) {
		this.c=controller;
		this.b=balls;
		this.bounds=bounds;
	}

	//TODO
	@Override
	public void run(){
		while(!b.isTouched()) {
			try {
				b.move(c.getWitgh(bounds),c.getHigh(bounds));
				c.paintPacmans();
				Thread.sleep(b.getWait());
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
