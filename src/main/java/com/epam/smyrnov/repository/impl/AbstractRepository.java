package com.epam.smyrnov.repository.impl;

import com.epam.smyrnov.constants.Constants;
import com.epam.smyrnov.exception.DataAccessException;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractRepository {

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
        Connection con = null;
        try {//  "jdbc:mysql://localhost/web_app?user=user&password=asdf"
            con = dataSource.getConnection();
            //Class.forName("com.mysql.cj.jdbc.Driver");
            //con = DriverManager.getConnection("jdbc:mysql://localhost/web_app?user=user&password=asdf");
        } catch (SQLException /*| ClassNotFoundException*/ ex) {
            ex.printStackTrace();
            logger.error(Constants.Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
           throw new DataAccessException(Constants.Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
        }
        return con;
    }

    protected static void close(ResultSet resultSet) {
        try {
            resultSet.close();
        } catch (SQLException e) {
            logger.error("Cannot close result set.");
            throw new DataAccessException("Cannot close result set.");
        }
    }

    protected static void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.error("Cannot close connection.");
            throw new DataAccessException("Cannot close connection.");
        }
    }
}
