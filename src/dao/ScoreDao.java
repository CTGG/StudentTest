package dao;

import java.util.List;

import model.Score;

public interface ScoreDao {
	/**
	 * get all students id
	 * @return
	 */
	public List<String> findAllUsers();
	
	
	/**
	 * get all scores of a student by id
	 * @param id
	 * @return
	 */
	public List<Score> findScoresById(String id);
	

}
