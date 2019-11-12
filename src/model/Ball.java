package model;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball {

	public static final String UP = "UP";
	public static final String DOWN = "DOWN";
	public static final String LEFT = "LEFT";
	public static final String RIGHT = "RIGHT";
	
	private String direction;
	private double radius;
	private double posX;
	private double posY;
	private int waitTime;
	private int rebounds;
	private boolean stop;
	private Circle Ball;

	public Ball(double radius, double posX, double posY, String direction, int waitTime, int rebounds, boolean stop) {
		this.direction = direction;
		this.radius = radius;
		this.posX = posX;
		this.posY = posY;
		this.waitTime = waitTime;
		this.rebounds = rebounds;
		this.stop = stop;
		setBall(new Circle(0,0,radius));
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	public boolean getStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public int getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	public Circle getBobj() {
		return Ball;
	}

	public void setBall(Circle Bobj) {
		this.Ball = Bobj;
		Random rand = new Random();
		double r = rand.nextDouble();
		double g = rand.nextDouble();
		double b = rand.nextDouble();
		double x = rand.nextDouble();
		Color randomColor = new Color(r, g, b, x);
		Bobj.setLayoutX(posX);
		Bobj.setLayoutY(posY);
		Bobj.setStroke(Color.BLACK);
		Bobj.setFill(randomColor);
	}
	
	public String opposite(String direction) {
		String opp = "";
		if(direction.equals(UP))
			opp = DOWN;
		if(direction.equals(DOWN))
			opp = UP;
		if(direction.equals(RIGHT))
			opp = LEFT;
		if(direction.equals(LEFT))
			opp = RIGHT;
		return opp;
	}

	public int getRebounds() {
		return rebounds;
	}

	public void setRebounds(int rebounds) {
		this.rebounds = rebounds;
	}

}
