package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javafx.scene.paint.Color;

public class Game {
	public static final String SCORE_PATH= "./data" + File.separator + "score.txt";
	public static final String GAME_PATH= "./data" + File.separator + "game.txt";
	private ArrayList<Balls> balls;
	private Score score;
	private int level;
	private BufferedReader reader;
	
	public Game() throws ClassNotFoundException, IOException {
		balls = new ArrayList<>();
//		score = new Score();
		level = 0;
	}
	public void loadGame(File f) throws IOException {
		balls.clear();
		try {
			FileReader levels = new FileReader(GAME_PATH);
			BufferedReader br = new BufferedReader(levels);
			String line = br.readLine();
			while (line != null) {
				String[] configuration = line.split("\t");
				double radious = Integer.parseInt(configuration[0]);
				double posX = Integer.parseInt(configuration[1]);
				double posY = Integer.parseInt(configuration[2]);
				int wait = Integer.parseInt(configuration[3]);
				String direction = configuration[4];
				int bounces = Integer.parseInt(configuration[5]);
				boolean stoped = false;
				if (configuration[6].equalsIgnoreCase("false")) {
					stoped = false;
				} else {
					stoped = true;
				}
				Balls b = new Balls( radious,  posX,  posY,  bounces,  wait,stoped,direction);
				balls.add(b);
				line = br.readLine();
			}
			br.close();
	}catch(Exception e) {
		throw new IOException();
	}
		
	}

	public void loadScore() throws IOException, ClassNotFoundException {
		File file = new File(SCORE_PATH);
		boolean exists = file.exists() && file.isFile();
		if (exists) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			try {
			score = (Score) ois.readObject();
			} finally {
				ois.close();
			}
		} else {
			throw new FileNotFoundException("No se ha encontrado el archivo");
		}
	}
	public void saveScore() throws FileNotFoundException, IOException {
		File file = new File(SCORE_PATH);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
		oos.writeObject(score);
		oos.close();
	}
	public void saveGame(File f) throws IOException, FileNotFoundException {
		BufferedWriter wr = new BufferedWriter(new FileWriter(f));
		PrintWriter pw = new PrintWriter(wr);
		
		pw.print("#nivel");
		pw.println();
		pw.print(level);
		pw.println();
		pw.print("#radio posX posY espera direccion rebotes touched");
		pw.println();
		for (int i = 0; i < balls.size(); i++) {
			Balls p = balls.get(i);
			pw.print(p.getRadious() + "\t" + p.getPosX() + "\t" + p.getPosY() + "\t" + p.getWait()+ "\t" + p.getDirection() + "\t" + p.getBounces() + "\t" + p.isTouched());
			pw.println();
		}

		pw.close();
	}
	public void stopBalls(double x, double y) {
		int x1 = (int) x;
		int y1 = (int) y;
		for(int i=0; i<balls.size();i++) {
			Balls pc = balls.get(i);
			if(pc.touched(x1, y1)) {
				pc.setTouched(true);
			}
		}
	}
	public boolean endGame() {
		boolean flag = false;
		for(int i=0; i<balls.size() && flag; i++) {
			Balls pc = balls.get(i);
			if(!pc.isTouched()) {
				flag = false;
			}
		}
		return flag;
	}
	public int numBounces() {
		int numBounces = 0;
		for(Balls p : balls) {
			numBounces += p.getBounces();
		}
		return numBounces;
	}
	public String getScores(){
		String msj="";
		msj = score.giveScores();
		return msj;
	}
	public void saveNewScore(String string) {
		score.addScore(level, string, numBounces());
	}
	public ArrayList<Balls> getBalls() {
		return balls;
	}
	public void setBalls(ArrayList<Balls> balls) {
		this.balls = balls;
	}
	

}
