package controller;

import model.Ball;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.BestScores;
import model.Ball;
import threads.*;

public class Controller {

	@FXML Pane pane;
	@FXML Label lbl;
	int level;
	BestScores bestScores = new BestScores();
	ArrayList<Ball> balls;
	boolean stop;
	int rebounds = 0;
	boolean charged;
	private Ball ballc;
	
	// methods
	public void initialize() {
		lbl.setText(String.valueOf(rebounds));
	}
	@FXML
	void leaveGame(ActionEvent event) {
		System.exit(0);
	}
	public void moveBall(int index) {

		if (balls.get(index).getStop() == false) {
			if (balls.get(index).getDirection().equals(ballc.UP)) {
				balls.get(index).getBobj().setLayoutY(balls.get(index).getBobj().getLayoutY() + 18);
			}
			if (balls.get(index).getDirection().equals(ballc.DOWN)) {
				balls.get(index).getBobj().setLayoutY(balls.get(index).getBobj().getLayoutY() - 18);
			}
			if (balls.get(index).getDirection().equals(ballc.RIGHT)) {
				balls.get(index).getBobj().setLayoutX(balls.get(index).getBobj().getLayoutX() - 18);
			}
			if (balls.get(index).getDirection().equals(ballc.LEFT)) {
				balls.get(index).getBobj().setLayoutX(balls.get(index).getBobj().getLayoutX() + 18);
			}

			if (balls.get(index).getBobj().getLayoutX() > pane.getWidth()) {
				balls.get(index).setDirection(balls.get(index).opposite(balls.get(index).getDirection()));
				balls.get(index).setRebounds(balls.get(index).getRebounds() + 1);
				rebounds++;
			}

			if (balls.get(index).getBobj().getLayoutX() < 0) {
				balls.get(index).setDirection(balls.get(index).opposite(balls.get(index).getDirection()));
				rebounds++;
			}

			if (balls.get(index).getBobj().getLayoutY() > pane.getHeight()) {
				balls.get(index).setDirection(balls.get(index).opposite(balls.get(index).getDirection()));
				balls.get(index).setRebounds(balls.get(index).getRebounds() + 1);
				rebounds++;
			}

			if (balls.get(index).getBobj().getLayoutY() < 0) {
				balls.get(index).setDirection(balls.get(index).opposite(balls.get(index).getDirection()));
				balls.get(index).setRebounds(balls.get(index).getRebounds() + 1);
				rebounds++;
			}
		}
		setRebounds();
	}
	private void setRebounds() {
		if(charged != false) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					lbl.setText(String.valueOf(rebounds));
				}
			});
		}
	}
	public void Colision() {
	double distancia = 0;
	double cx1 = 0;
	double cy1 = 0;
	double r1 = 0;
	double cx2 = 0;
	double cy2 = 0;
	double r2 = 0;
	for (int i = 0; i < balls.size(); i++) {
		for (int j = 0; j < balls.size(); j++) {
			if (balls.get(i) != balls.get(j)) {
				if (balls.get(i).getBobj().getCenterX() > balls.get(j).getBobj().getCenterX()
						&& balls.get(i).getBobj().getCenterY() > balls.get(j).getBobj().getCenterY()) {
					cx1 = balls.get(i).getBobj().getLayoutX();
					cy1 = balls.get(i).getBobj().getLayoutY();
					r1 = balls.get(i).getBobj().getRadius();
					cx2 = balls.get(j).getBobj().getLayoutX();
					cy2 = balls.get(j).getBobj().getLayoutY();
					r2 = balls.get(j).getBobj().getRadius();
				} else {
					cx1 = balls.get(j).getBobj().getLayoutX();
					cy1 = balls.get(j).getBobj().getLayoutY();
					r1 = balls.get(j).getBobj().getRadius();
					cx2 = balls.get(i).getBobj().getLayoutX();
					cy2 = balls.get(i).getBobj().getLayoutY();
					r2 = balls.get(i).getBobj().getRadius();
				}
				distancia = Math.sqrt((cx1 - cx2) * (cx1 - cx2) + (cy1 - cy2) * (cy1 - cy2));
				if (distancia < r1 + r2) {
					if (balls.get(i).getDirection().equals(ballc.UP)) {
						balls.get(i).getBobj().setLayoutY(balls.get(i).getBobj().getLayoutY()
								- (balls.get(i).getBobj().getRadius() + 6));
					}
					if (balls.get(i).getDirection().equals(ballc.DOWN)) {
						balls.get(i).getBobj().setLayoutY(balls.get(i).getBobj().getLayoutY()
								+ (balls.get(i).getBobj().getRadius() + 6));
					}
					if (balls.get(i).getDirection().equals(ballc.RIGHT)) {
						balls.get(i).getBobj().setLayoutX(balls.get(i).getBobj().getLayoutX()
								+ (balls.get(i).getBobj().getRadius() + 6));
					}
					if (balls.get(i).getDirection().equals(ballc.LEFT)) {
						balls.get(i).getBobj().setLayoutX(balls.get(i).getBobj().getLayoutX()
								- (balls.get(i).getBobj().getRadius() + 6));
					}
					if (balls.get(j).getDirection().equals(ballc.UP)) {
						balls.get(j).getBobj().setLayoutY(balls.get(i).getBobj().getLayoutY()
								- (balls.get(i).getBobj().getRadius() + 6));
					}
					if (balls.get(j).getDirection().equals(ballc.DOWN)) {
						balls.get(j).getBobj().setLayoutY(balls.get(i).getBobj().getLayoutY()
								+ (balls.get(i).getBobj().getRadius() + 6));
					}
					if (balls.get(j).getDirection().equals(ballc.RIGHT)) {
						balls.get(j).getBobj().setLayoutX(balls.get(i).getBobj().getLayoutX()
								+ (balls.get(i).getBobj().getRadius() + 6));
					}
					if (balls.get(j).getDirection().equals(ballc.LEFT)) {
						balls.get(j).getBobj().setLayoutX(balls.get(i).getBobj().getLayoutX()
								- (balls.get(i).getBobj().getRadius() + 6));
					}
					balls.get(i).setDirection(balls.get(i).opposite(balls.get(i).getDirection()));
					balls.get(j).setDirection(balls.get(j).opposite(balls.get(j).getDirection()));
				}
			}
		}
	}
	}
	public ArrayList<Ball> getBalls() {
		return balls;
	}
	@FXML
	public void levelZero(ActionEvent event) {
		level = 0;
		loadGame();
	}

	@FXML
	public void levelOne(ActionEvent event) {
		level = 1;
		loadGame();
	}
	@FXML
	void getScoresC(ActionEvent event) {
		Alert a = new Alert(AlertType.INFORMATION); 
		a.setTitle("Best Scores");
		a.setContentText(bestScores.messageScore());
		a.showAndWait();
	}
	@FXML
	public void onPressed(MouseEvent event) {
	try {
		for (int i = 0; i < balls.size(); i++) {
			if (balls.get(i).getStop() != true) {
				double x = balls.get(i).getBobj().getLayoutX();
				double y = balls.get(i).getBobj().getLayoutY();
				double r = balls.get(i).getRadius();

				if (event.getSceneX() < (x + r + 100) && event.getSceneX() > (x - r)
						&& event.getSceneY() < (y + r + 100) && event.getSceneY() > (y - r)) {
					balls.get(i).setStop(true);
				}
			}
		}
	} catch (RuntimeException e) {
		Alert a = new Alert(AlertType.ERROR); 
		a.setTitle("Error");
		a.setContentText("No existe ningun pacman para atrapar aun.");
		a.show();
	}
	}
	@FXML
	void loadGame() {
		charged = false;
		stop = true;
		rebounds = 0;
		pane.getChildren().clear();
		balls = new ArrayList<Ball>();
		String filePath = "";
		BufferedReader br = null;
		FileReader fr = null;

		if (level == 0) {
			filePath = "data"+File.separator+"Lv0.txt";
		} else if (level == 1) {
			filePath = "data"+File.separator+"Lv1.txt";
		} else if (level == 2)
			filePath = "data"+File.separator+"Lv2.txt";

		try {
			fr = new FileReader(filePath);
			br = new BufferedReader(fr);

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {

				if (sCurrentLine.charAt(0) != '#') {
					if (sCurrentLine.length() > 1) {
						String[] parts = sCurrentLine.split(" ");
						double radius = Double.parseDouble(parts[0]);
						double x = Double.parseDouble(parts[1]);
						double y = Double.parseDouble(parts[2]);
						int wait = Integer.parseInt(parts[3]);
						String direction = parts[4];
						int rebounds = Integer.parseInt(parts[5]);
						boolean stopped = Boolean.parseBoolean(parts[5]);
						ballc = new Ball(radius, x, y, direction, wait, rebounds, stopped);
						balls.add(ballc);
					} else {
						level = Integer.parseInt(sCurrentLine);
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();

				if (fr != null)
					fr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		charged = true;
		startGame();
	}
	void startGame() {
		if (charged) {
			for (int i = 0; i < balls.size(); i++) {
				pane.getChildren().add(balls.get(i).getBobj());
			}
			stop = false;
			threadMoveBall moveB = new threadMoveBall(this);
			threadColision threadColision = new threadColision(this);
			moveB.setDaemon(true);
			threadColision.setDaemon(true);
			moveB.start();
			threadColision.start();
		}
	}
	@FXML
	public void levelTwo(ActionEvent event) {
		level = 2;
		loadGame();
	}
	
	public boolean getStop() {
		return stop;
	}
	
	public int getLevel() {
		return level;
	}
	@FXML
	public void saveGame(ActionEvent e) {
		//TODO
	}
	
}