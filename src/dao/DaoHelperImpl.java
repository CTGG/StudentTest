package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DaoHelperImpl implements DaoHelper{
	
	
	private DataSource dataSource = null;
	private Connection connection = null;
	private static DaoHelperImpl daoHelper = new DaoHelperImpl();
	
	private DaoHelperImpl() {
		InitialContext jndiContext = null;
    	
    	Properties properties = new Properties();
    	properties.put(javax.naming.Context.PROVIDER_URL, "jnp:///");
    	properties.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
    	
    	try {
			jndiContext = new InitialContext(properties);
			dataSource = (DataSource) jndiContext.lookup("java:comp/env/jdbc/testscore");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static DaoHelperImpl getInstance() {
		return daoHelper;
	}
	
	@Override
	public Connection getConnection() {
		try {	
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			System.out.println("---------angry");
			e.printStackTrace();
		}
		return connection;
	}

	@Override
	public void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void closePreparedStatement(PreparedStatement pStatement) {
		if (pStatement != null) {
			try {
				pStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void closeResult(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
