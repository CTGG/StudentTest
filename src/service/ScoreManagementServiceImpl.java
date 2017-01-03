package service;

import java.util.List;

import dao.ScoreDao;
import factory.DaoFactory;
import model.Score;

public class ScoreManagementServiceImpl implements ScoreManagementService{

  private static ScoreManagementService scoreService = new ScoreManagementServiceImpl();

  public static ScoreManagementService getInstance(){
    return scoreService;
  }
	@Override
	public List<Score> getScoresById(String id) {
		return DaoFactory.getScoreDao().findScoresById(id);
	}

	@Override
	public boolean isExist(String id) {
		boolean result = false;
		// TODO Auto-generated method stub
		ScoreDao scoreDao = DaoFactory.getScoreDao();
		List<String> users = scoreDao.findAllUsers();
		for (String user : users) {
			if (user.equals(id)) {
				result = true;
			}
		}
		
		return result;
	}

}
