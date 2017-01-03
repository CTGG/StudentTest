package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface DaoHelper {
	/**
	 * get database connection
	 * @return
	 */
	public Connection getConnection();
	
	/**
	 * close connection of database
	 * @param connection
	 */
	public void closeConnection(Connection connection);
	
	
	public void closePreparedStatement(PreparedStatement pStatement);
	
	public void closeResult(ResultSet resultSet);

}
