package com.epam.smyrnov.repository.impl;

import com.epam.smyrnov.annotation.Repository;
import com.epam.smyrnov.constants.SQLQueries;
import com.epam.smyrnov.exception.DataAccessException;
import com.epam.smyrnov.entity.user.User;
import com.epam.smyrnov.repository.UserRepository;
import com.epam.smyrnov.entity.user.Role;
import com.epam.smyrnov.util.HashingSha256;
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
	public long getLastId() {
		long counter = 0;
		try (Connection connection = getConnection();
		     PreparedStatement statement =
				     connection.prepareStatement(SQLQueries.Users.GET_LAST_ID)) {
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				counter = resultSet.getLong("id");
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
			Connection connection = getConnection();
			try (PreparedStatement statement = connection.prepareStatement(SQLQueries.Users.DELETE_USER_BY_ID)) {
				statement.setLong(1, id);
				statement.execute();
				connection.commit();
				return true;
			} catch (SQLException e) {
				rollback(connection, e);
				logger.error(e.getMessage(), e);
				throw new DataAccessException(e.getMessage(), e);
			} finally {
				close(connection);
			}
		} else {
			logger.info("There is no user in database: " + id);
			return false;
		}
	}

	@Override
	public boolean existsById(Long id) {
		boolean res = false;
		try (Connection connection = getConnection();
		     PreparedStatement statement = connection.prepareStatement(SQLQueries.Users.SELECT_USER_BY_ID)) {
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
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
		     PreparedStatement statement = connection.prepareStatement(SQLQueries.Users.SELECT_USER_BY_EMAIL)) {
			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();
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
		     PreparedStatement statement = connection.prepareStatement(SQLQueries.Users.SELECT_USER_BY_ID)) {
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			User user = null;
			if (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getLong("id"));
				user.setEmail(resultSet.getString("email"));
				user.setPassword(resultSet.getString("password"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setLastName(resultSet.getString("last_name"));
				user.setBlocked(resultSet.getBoolean("blocked"));
				user.setVerified(resultSet.getBoolean("verified"));
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
		     PreparedStatement statement = connection.prepareStatement(SQLQueries.Users.SELECT_USER_BY_EMAIL)) {
			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();
			User user = null;
			if (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getLong("id"));
				user.setEmail(resultSet.getString("email"));
				user.setPassword(resultSet.getString("password"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setLastName(resultSet.getString("last_name"));
				user.setBlocked(resultSet.getBoolean("blocked"));
				user.setVerified(resultSet.getBoolean("verified"));
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
		     PreparedStatement statement = connection.prepareStatement(SQLQueries.Users.SELECT_ALL_USERS)) {
			ResultSet resultSet = statement.executeQuery();
			List<User> users = new ArrayList<>();
			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getLong("id"));
				user.setEmail(resultSet.getString("email"));
				user.setPassword(resultSet.getString("password"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setLastName(resultSet.getString("last_name"));
				user.setBlocked(resultSet.getBoolean("blocked"));
				user.setVerified(resultSet.getBoolean("verified"));
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
		try (PreparedStatement statement = connection.prepareStatement(SQLQueries.Users.UPDATE_USER)) {
			connection.setAutoCommit(false);
			int k = 1;
			statement.setString(k++, entity.getEmail());
			statement.setString(k++, entity.getPassword());
			statement.setString(k++, entity.getFirstName());
			statement.setString(k++, entity.getLastName());
			statement.setBoolean(k++, entity.isBlocked());
			statement.setBoolean(k++, entity.isVerified());
			statement.setInt(k++, entity.getRole().ordinal());
			statement.setLong(k, entity.getId());
			statement.execute();
			connection.commit();
		} catch (SQLException e) {
			rollback(connection, e);
			logger.error(e.getMessage(), e);
			throw new DataAccessException(e.getMessage(), e);
		} finally {
			close(connection);
		}
		return entity;
	}

	@Override
	public User create(User entity) {
		if (!existsByEmail(entity.getEmail())) {
			Connection connection = getConnection();
			try (PreparedStatement statement = connection.prepareStatement(SQLQueries.Users.INSERT_USER)) {
				connection.setAutoCommit(false);
				int k = 1;
				statement.setString(k++, entity.getEmail());
				statement.setString(k++, entity.getPassword());
				statement.setString(k++, entity.getFirstName());
				statement.setString(k++, entity.getLastName());
				statement.setBoolean(k++, entity.isBlocked());
				statement.setBoolean(k++, entity.isVerified());
				statement.setInt(k, entity.getRole().ordinal());
				statement.execute();
				connection.commit();
				entity.setId(getLastId());
			} catch (SQLException e) {
				rollback(connection, e);
				logger.error(e.getMessage(), e);
				throw new DataAccessException(e.getMessage(), e);
			} finally {
				close(connection);
			}
			setHashInDatabase(entity);
		} else {
			entity = null;
		}
		return entity;
	}

	@Override
	public List<User> findAllByRole(Role role) {
		try (Connection connection = getConnection();
		     PreparedStatement statement = connection.prepareStatement(SQLQueries.Users.SELECT_USERS_BY_ROLE)) {
			statement.setInt(1, role.ordinal());
			ResultSet resultSet = statement.executeQuery();
			List<User> users = new ArrayList<>();
			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getLong("id"));
				user.setEmail(resultSet.getString("email"));
				user.setPassword(resultSet.getString("password"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setLastName(resultSet.getString("last_name"));
				user.setBlocked(resultSet.getBoolean("blocked"));
				user.setVerified(resultSet.getBoolean("verified"));
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

	private boolean setHashInDatabase(User user) {
		Connection connection = getConnection();
		try (PreparedStatement statement = connection.prepareStatement(SQLQueries.Users.SET_HASH)) {
			connection.setAutoCommit(false);
			statement.setLong(1, user.getId());
			statement.setString(2, HashingSha256.hash(user.getEmail()));
			statement.execute();
			connection.commit();
			return true;
		} catch (SQLException e) {
			rollback(connection, e);
			logger.error(e.getMessage(), e);
			return false;
		} finally {
			close(connection);
		}
	}

	@Override
	public boolean verifyUser(String hash) {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try {
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(SQLQueries.Users.SEARCH_HASH);
			statement.setString(1, hash);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				User user = findById(resultSet.getLong("id"));
				if (user.isVerified()) {
					return false;
				}
				statement = connection.prepareStatement(SQLQueries.Users.DELETE_HASH);
				statement.setString(1, hash);
				statement.execute();
				statement = connection.prepareStatement(SQLQueries.Users.VERIFY_USER);
				statement.setLong(1, resultSet.getLong("id"));
				statement.execute();
				connection.commit();
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			rollback(connection, e);
			logger.error(e.getMessage(), e);
			throw new DataAccessException(e.getMessage(), e);
		} finally {
			close(statement);
			close(connection);
		}
	}
}
