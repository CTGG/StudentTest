package service;

import java.util.List;

import model.Score;

public interface ScoreManagementService {
	
	public List<Score> getScoresById(String id);
	
	/**
	 * check student id valid
	 * @param id
	 * @return
	 */
	public boolean isExist(String id);

}
