package com.epam.smyrnov.repository.impl;

import com.epam.smyrnov.annotation.Repository;
import com.epam.smyrnov.constants.SQLQueries;
import com.epam.smyrnov.exception.DataAccessException;
import com.epam.smyrnov.entity.user.User;
import com.epam.smyrnov.exception.NoSuchElementException;
import com.epam.smyrnov.repository.UserRepository;
import com.epam.smyrnov.entity.user.Role;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl extends AbstractRepository implements UserRepository {

	private static final long serialVersionUID = -5931541748654132164L;
	private static final Logger logger = Logger.getLogger(ItemRepositoryImpl.class);

	@Override
	public long count() {
		long counter = 0;
		try (Connection connection = getConnection();
		     PreparedStatement preparedStatement =
				     connection.prepareStatement(SQLQueries.Users.SELECT_ALL_USERS)) {
			preparedStatement.execute();
			ResultSet resultSet = preparedStatement.getResultSet();
			while (resultSet.next()) {
				counter++;
			}
			close(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataAccessException(e.getMessage(), e);
		}
		return counter;
	}

	@Override
	public boolean delete(Long id) {
		if (existsById(id)) {
			try (Connection connection = getConnection();
			     PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.Users.DELETE_USER_BY_ID)) {
				preparedStatement.setLong(1, id);
				preparedStatement.execute();
				return true;
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				throw new DataAccessException(e.getMessage(), e);
			}
		} else {
			logger.error("There is no such user in database.");
			throw new NoSuchElementException("There is no such user in database.");
		}
	}

	@Override
	public boolean deleteByEmail(String email) {
		if (existsByEmail(email)) {
			try (Connection connection = getConnection();
			     PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.Users.DELETE_USER_BY_EMAIL)) {
				preparedStatement.setString(1, email);
				preparedStatement.execute();
				return true;
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				throw new DataAccessException(e.getMessage(), e);
			}
		} else {
			logger.error("There is no such user in database.");
			throw new NoSuchElementException("There is no such user in database.");
		}
	}

	@Override
	public boolean existsById(Long id) {
		boolean res = false;
		try (Connection connection = getConnection();
		     PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.Users.SELECT_USER_BY_ID)) {
			preparedStatement.setLong(1, id);
			preparedStatement.execute();
			ResultSet resultSet = preparedStatement.getResultSet();
			if (resultSet.next()) {
				res = true;
			}
			close(resultSet);
			return res;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	@Override
	public boolean existsByEmail(String email) {
		boolean res = false;
		try (Connection connection = getConnection();
		     PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.Users.SELECT_USER_BY_EMAIL)) {
			preparedStatement.setString(1, email);
			preparedStatement.execute();
			ResultSet resultSet = preparedStatement.getResultSet();
			if (resultSet.next()) {
				res = true;
			}
			close(resultSet);
			return res;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	@Override
	public User findById(Long id) {
		try (Connection connection = getConnection();
		     PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.Users.SELECT_USER_BY_ID)) {
			preparedStatement.setLong(1, id);
			preparedStatement.execute();
			ResultSet resultSet = preparedStatement.getResultSet();
			User user = null;
			if (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getLong("id"));
				user.setEmail(resultSet.getString("email"));
				user.setPassword(resultSet.getString("password"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setLastName(resultSet.getString("last_name"));
				user.setBlocked(resultSet.getBoolean("blocked"));
				user.setRole(resultSet.getInt("role_id") == 1 ? Role.CLIENT : Role.ADMIN);
			}
			close(resultSet);
			return user;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	@Override
	public User findByEmail(String email) {
		try (Connection connection = getConnection();
		     PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.Users.SELECT_USER_BY_EMAIL)) {
			preparedStatement.setString(1, email);
			preparedStatement.execute();
			ResultSet resultSet = preparedStatement.getResultSet();
			User user = null;
			if (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getLong("id"));
				user.setEmail(resultSet.getString("email"));
				user.setPassword(resultSet.getString("password"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setLastName(resultSet.getString("last_name"));
				user.setBlocked(resultSet.getBoolean("blocked"));
				user.setRole(resultSet.getInt("role_id") == 1 ? Role.CLIENT : Role.ADMIN);
			}
			close(resultSet);
			return user;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	@Override
	public List<User> findAll() {
		try (Connection connection = getConnection();
		     PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.Users.SELECT_ALL_USERS)) {
			preparedStatement.execute();
			ResultSet resultSet = preparedStatement.getResultSet();
			List<User> users = new ArrayList<>();
			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getLong("id"));
				user.setEmail(resultSet.getString("email"));
				user.setPassword(resultSet.getString("password"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setLastName(resultSet.getString("last_name"));
				user.setBlocked(resultSet.getBoolean("blocked"));
				user.setRole(resultSet.getInt("role_id") == 1 ? Role.CLIENT : Role.ADMIN);
				users.add(user);
			}
			close(resultSet);
			return users;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	@Override
	public User update(User entity) {
		Connection connection = getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.Users.UPDATE_USER)) {
			int k = 1;
			preparedStatement.setString(k++, entity.getEmail());
			preparedStatement.setString(k++, entity.getPassword());
			preparedStatement.setString(k++, entity.getFirstName());
			preparedStatement.setString(k++, entity.getLastName());
			preparedStatement.setBoolean(k++, entity.isBlocked());
			preparedStatement.setInt(k++, entity.getRole().ordinal());
			preparedStatement.setLong(k, entity.getId());
			preparedStatement.execute();
			connection.commit();
			close(connection);
		} catch (SQLException e) {
			rollback(connection);
			logger.error(e.getMessage(), e);
			throw new DataAccessException(e.getMessage(), e);
		}
		return entity;
	}

	@Override
	public User create(User entity) {
		if (!existsByEmail(entity.getEmail())) {
			Connection connection = getConnection();
			try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.Users.INSERT_USER)) {
				int k = 1;
				preparedStatement.setString(k++, entity.getEmail());
				preparedStatement.setString(k++, entity.getPassword());
				preparedStatement.setString(k++, entity.getFirstName());
				preparedStatement.setString(k++, entity.getLastName());
				preparedStatement.setBoolean(k++, entity.isBlocked());
				preparedStatement.setInt(k, entity.getRole().ordinal());
				preparedStatement.execute();
				connection.commit();
				close(connection);
			} catch (SQLException e) {
				rollback(connection);
				logger.error(e.getMessage(), e);
				throw new DataAccessException(e.getMessage(), e);
			}
		}
		return entity;
	}

	@Override
	public List<User> findAllByRole(Role role) {
		try (Connection connection = getConnection();
		     PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.Users.SELECT_USERS_BY_ROLE)) {
			preparedStatement.setInt(1, role.ordinal());
			preparedStatement.execute();
			ResultSet resultSet = preparedStatement.getResultSet();
			List<User> users = new ArrayList<>();
			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getLong("id"));
				user.setEmail(resultSet.getString("email"));
				user.setPassword(resultSet.getString("password"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setLastName(resultSet.getString("last_name"));
				user.setBlocked(resultSet.getBoolean("blocked"));
				user.setRole(resultSet.getInt("role_id") == 1 ? Role.CLIENT : Role.ADMIN);
				users.add(user);
			}
			close(resultSet);
			return users;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	@Override
	public List<User> saveAll(List<User> entities) {
		for (User user : entities) {
			update(user);
		}
		return entities;
	}
}
