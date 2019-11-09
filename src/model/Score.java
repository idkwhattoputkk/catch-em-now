package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Score implements Serializable {
	public final static int LEVELS = 3;
	public final static int HALL_OF_FAME = 10;
//	private int score;
	private String[][] names;
	private int[][] scores;
	public Score() {
		names = new String[LEVELS][HALL_OF_FAME];
		scores = new int[LEVELS][HALL_OF_FAME];
		for(int i=0; i<scores.length;i++) {
			for(int j=0; j<scores[i].length;i++) {
				scores[i][j]= 0;
				names[i][j]= "";
			}
		}
	}
	public boolean addScore(int level, String name, int score) {
		boolean added= false;
		String sTemp="";
		int iTemp=0;
		for(int i=0;i<HALL_OF_FAME && !added;i++) {
			if(scores[level][i]>score) {
				sTemp=names[level][i];
				iTemp=scores[level][i];
				names[level][i]= name;
				scores[level][i]=score;
				added = true;
			}
		}
		if(added) {
			int i=0;
			int j=HALL_OF_FAME-1;
			while(j>i) {
				names[level][j]=names[level][j-1];
				scores[level][j]=scores[level][j-1];
				j--;
			}
			names[level][i] = sTemp;
			scores[level][i]= iTemp;
		}
		return added;
	}
	public String giveScores() {
		String msg ="";
		for(int i=0; i<names.length;i++) {
			msg+="Nivel "+i;
		}
		msg+="\n";
		for(int j=0;j<HALL_OF_FAME;j++) {
			for(int i=0;i<names.length;i++) {
				if(!names[i][j].equals("")) {
					msg+= names[i][j]+ " " + scores[i][j]+" ";
				}
			}
			msg+="\n";
		}
		return msg;
	}
	public boolean isHallOfFame(int level, int score) {
		boolean flag= false;
		if(score<scores[level][HALL_OF_FAME-1]) {
			flag = true;
		}
		return flag;
	}
	
}
