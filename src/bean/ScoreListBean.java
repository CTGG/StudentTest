package bean;

import java.io.Serializable;
import java.util.List;

import model.Score;

public class ScoreListBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Score> scores;

	public List<Score> getScores() {
		return scores;
	}

	
	public void setScores(List<Score> scores) {
		this.scores = scores;
	}
	
	
	
}
