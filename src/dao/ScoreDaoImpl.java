package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Score;

public class ScoreDaoImpl implements ScoreDao{
	private static ScoreDaoImpl scoreDao = new ScoreDaoImpl();
	private static DaoHelper daoHelper = DaoHelperImpl.getInstance();
	
	private ScoreDaoImpl() {
	}
	
	public static ScoreDaoImpl getInstance(){
		return scoreDao;
	}
	
	@Override
	public List<String> findAllUsers() {
		Connection connection = daoHelper.getConnection();
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		ArrayList<String> users = new ArrayList<>();
		try {
			pStatement = connection.prepareStatement("select id from score");
			resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				String user = resultSet.getString("id");
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			daoHelper.closeConnection(connection);
			daoHelper.closePreparedStatement(pStatement);
			daoHelper.closeResult(resultSet);
		}
		
		return users;
	}

	@Override
	public List<Score> findScoresById(String id) {
		// TODO Auto-generated method stub
		Connection connection = daoHelper.getConnection();
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		ArrayList<Score> scores = new ArrayList<>();
		try {
			pStatement = connection.prepareStatement("select id, course,score from score where id = ?");
			pStatement.setString(1, id);
			resultSet = pStatement.executeQuery();
			//TODO
			System.out.println("id   "+id);
			System.out.println("resultset     "+resultSet.getFetchSize());
			while (resultSet.next()) {
				Score score = new Score();
				score.setId(resultSet.getString("id"));
				score.setCourse(resultSet.getString("course"));
				score.setScore(resultSet.getInt("score"));
				scores.add(score);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			daoHelper.closeConnection(connection);
			daoHelper.closePreparedStatement(pStatement);
			daoHelper.closeResult(resultSet);
		}
		
		return scores;
	}

}
