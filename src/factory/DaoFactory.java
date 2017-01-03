package factory;

import dao.ScoreDao;
import dao.ScoreDaoImpl;

public class DaoFactory {
	
	public static ScoreDao getScoreDao() {
		return ScoreDaoImpl.getInstance();
	}

}
