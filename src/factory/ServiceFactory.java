package factory;

import service.ScoreManagementService;
import service.ScoreManagementServiceBean;


public class ServiceFactory {
	public static ScoreManagementService getScoreService() {
		return ScoreManagementServiceBean.getInstance();
	}

}
