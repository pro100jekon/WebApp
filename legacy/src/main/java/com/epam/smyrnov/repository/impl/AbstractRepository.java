package com.epam.smyrnov.repository.impl;

import com.epam.smyrnov.constants.Constants;
import com.epam.smyrnov.exception.DataAccessException;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractRepository implements Serializable {

	private static final long serialVersionUID = 5145613218646545649L;
	private DataSource dataSource;

	private static final Logger logger = Logger.getLogger(AbstractRepository.class);

	protected AbstractRepository() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/web_app");
		} catch (NamingException ex) {
			ex.printStackTrace();
			logger.error(Constants.Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
			throw new DataAccessException(Constants.Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
		}
	}

	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(Constants.Messages.ERR_CANNOT_OBTAIN_CONNECTION, e);
			throw new DataAccessException(Constants.Messages.ERR_CANNOT_OBTAIN_CONNECTION, e);
		}
		return connection;
	}

	protected static void close(ResultSet resultSet) {
		try {
			resultSet.close();
		} catch (SQLException e) {
			logger.error(Constants.Messages.ERR_CANNOT_CLOSE_RESULT_SET);
			throw new DataAccessException(Constants.Messages.ERR_CANNOT_CLOSE_RESULT_SET);
		}
	}

	protected static void close(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			logger.error(Constants.Messages.ERR_CANNOT_CLOSE_CONNECTION);
			throw new DataAccessException(Constants.Messages.ERR_CANNOT_CLOSE_CONNECTION);
		}
	}

	protected static void close(Statement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			logger.error(Constants.Messages.ERR_CANNOT_CLOSE_STATEMENT);
			throw new DataAccessException(Constants.Messages.ERR_CANNOT_CLOSE_STATEMENT);
		}
	}

	protected static void rollback(Connection connection, Throwable t) {
		try {
			if (connection != null) {
				connection.rollback();
				close(connection);
			}
		} catch (SQLException e) {
			logger.error(Constants.Messages.ERR_CANNOT_ROLLBACK_CONNECTION, t);
			throw new DataAccessException(Constants.Messages.ERR_CANNOT_ROLLBACK_CONNECTION, t);
		}
	}
}
