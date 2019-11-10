package controller;

import static model.Game.SCORE_PATH;
import model.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import threads.BallsThread;
public class Controller {

	@FXML
	private Pane pane;
	private Game atrapaBalls;
	
	//methods
	@FXML
	public void loadGameC(ActionEvent event) throws IOException {
		try {
			FileChooser fch= new FileChooser();
			File f = fch.showOpenDialog(null);
			if(f !=null) {
				atrapaBalls.loadGame(f);
				threadsInitiation();
				paintPacmans();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	@FXML
	public void saveGameC(ActionEvent event) throws FileNotFoundException, IOException {
		try {
		File f = new File(SCORE_PATH);
		atrapaBalls.saveGame(f);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	@FXML
	public void leaveGame(ActionEvent event){
			System.exit(0);
	}
	public void paintPacmans() {
			for(int i=0; atrapaBalls.getBalls() !=null && i<atrapaBalls.getBalls().size();i++) {
				Random rand = new Random();
				float r = rand.nextFloat();
				float g = rand.nextFloat();
				float b = rand.nextFloat();
				//Color randomColor = new Color(r, g, b);
				Circle c= new Circle();
				//c.setFill(randomColor);
				double ra = atrapaBalls.getBalls().get(i).getRadious();
				c.setCenterX(atrapaBalls.getBalls().get(i).getPosX());
				c.setCenterY(atrapaBalls.getBalls().get(i).getPosY());
				c.setRadius(ra);
				pane.getChildren().addAll(c);
			}
			
		}
	public void touchThePac(double x, double y){
		atrapaBalls.stopBalls(x, y);
		if(atrapaBalls.endGame()){
				int thisScore = atrapaBalls.numBounces();
				TextInputDialog t = new TextInputDialog();
				t.setTitle("Hall of Fame");
				t.setContentText("Su puntaje de: "+thisScore+" es uno de los 10 mejores.\nPor favor digite su name para el Hall de la Fama: ");
				Optional<String> result =t.showAndWait();
				if(result.isPresent()&&result.get().toString().length()!=0) {
					atrapaBalls.saveNewScore(result.get());
				}else {
					Alert a = new Alert(AlertType.ERROR);
					a.setContentText("Digite un nombre valido");
					a.show();
					
				}
		}
	}
	private void threadsInitiation(){
		BallsThread[] threads = new BallsThread[atrapaBalls.getBalls().size()];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new BallsThread(this, atrapaBalls.getBalls().get(i));
			threads[i].start();
		}
	}
	@FXML
	public void getScoresC(ActionEvent event) {
		try{
			String msj="";
			msj = atrapaBalls.getScores();
			Alert info = new Alert(AlertType.CONFIRMATION);
	    	info.setTitle("Best Scores");
	    	info.setHeaderText(null);
	    	info.setContentText(msj);
	    	info.show();
		}catch(Exception e) {
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("no game initialize");
			error.setContentText(e.getMessage());
		}
		
	}
	public int getHigh() {
		Bounds bounds = pane.getBoundsInLocal();
		int y = (int) (bounds.getMaxY()+bounds.getMinY());
		return y;
	}
	public int getWitgh(){
		Bounds bounds = pane.getBoundsInLocal();
		int x = (int) (bounds.getMaxX()+bounds.getMinX());
		return x;
		}
	public void mouseClicked(MouseEvent e) {
		if(!atrapaBalls.getBalls().isEmpty())
			touchThePac(e.getX(), e.getY());
	}
	@FXML
	void AboutGame(ActionEvent event){
		Alert info = new Alert(AlertType.CONFIRMATION);
    	info.setTitle("About capture the catch-em-now");
    	info.setHeaderText(null);
    	info.setContentText("To win you have to stop all the circles \n as fast as you can");
    	info.show();
	}
}
