package service;

import java.util.List;

import dao.ScoreDao;
import factory.DaoFactory;
import model.Score;

/**
 * Session Bean implementation class ScoreManagementServiceBean
 */
public class ScoreManagementServiceBean implements ScoreManagementService{

	private static ScoreManagementServiceBean scoreService = new ScoreManagementServiceBean();

    /**
     * Default constructor. 
     */
    private ScoreManagementServiceBean() {
        // TODO Auto-generated constructor stub
    }

    public static ScoreManagementService getInstance(){
    	return scoreService;
	}

	private static ScoreDao scoreDao = DaoFactory.getScoreDao();
	@Override
	public List<Score> getScoresById(String id) {
		return scoreDao.findScoresById(id);
	}

	@Override
	public boolean isExist(String id) {
		boolean result = false;
		List<String> users = scoreDao.findAllUsers();
		for (String user : users) {
			if (user.equals(id)) {
				result = true;
			}
		}

		return result;
	}


}