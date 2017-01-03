package factory;

import service.ScoreManagementService;
import service.ScoreManagementServiceImpl;

public class ServiceFactory {
	public static ScoreManagementService getScoreService() {
		return ScoreManagementServiceImpl.getInstance();
	}

}
