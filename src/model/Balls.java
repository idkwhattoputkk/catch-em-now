package model;

import java.security.SecureRandom;

import javafx.scene.paint.Color;

public class Balls {
	
	//Constants
	public final static String LEFT = "LEFT"; 
	public final static String RIGHT = "RIGHT"; 
	public final static String UP = "UP"; 
	public final static String DOWN = "DOWN";
	static SecureRandom random = new SecureRandom();
	public final static int MOV = 1+ random.nextInt(5);
	
	
	// Attributes	
	private double radious, posX, posY;
	private int bounces, wait;
	private boolean isTouched;
	private String direction;
	private Color color;
	
	//methods
	
	public void move(boolean wideP, boolean highP)
	{
		if(direction.equals(RIGHT)) {
			posX += MOV;
		}else if(direction.equals(LEFT)) {
			posX -= MOV;
		}else if(direction.equals(UP)) {
			posY -= MOV;
		}else if(direction.equals(DOWN)) {
			posY += MOV;
		}
		if(wideP==true) {
			posX*=MOV;
			bounces++;
		}
		if(highP==true) {
			posY*=MOV;
			bounces++;
		}
	}
	public Balls(double radious, double posX, double posY, int bounces, int wait, boolean isTouched, String direction) {
		super();
		this.radious = radious;
		this.posX = posX;
		this.posY = posY;
		this.bounces = bounces;
		this.wait = wait;
		this.isTouched = isTouched;
		this.direction = direction;
	}
	public boolean touched(double x, double y) {
		boolean flag = false;
		if(posX<=x && x<=posX+radious && posY<=y && y<=posY+radious) {
			flag = true;
		}
		return flag;
	}
	public double getRadious() {
		return radious;
	}
	public void setRadious(double radious) {
		this.radious = radious;
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
	public int getBounces() {
		return bounces;
	}
	public void setBounces(int bounces) {
		this.bounces = bounces;
	}
	public int getWait() {
		return wait;
	}
	public void setWait(int wait) {
		this.wait = wait;
	}
	public boolean isTouched() {
		return isTouched;
	}
	public void setTouched(boolean isTouched) {
		this.isTouched = isTouched;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	
	
	
	
	
	

	

}
