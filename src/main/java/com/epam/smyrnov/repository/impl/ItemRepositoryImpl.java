package com.epam.smyrnov.repository.impl;

import com.epam.smyrnov.annotation.Repository;
import com.epam.smyrnov.entity.Item;
import com.epam.smyrnov.exception.DataAccessException;
import com.epam.smyrnov.exception.NoSuchElementException;
import com.epam.smyrnov.repository.ItemRepository;
import com.epam.smyrnov.constants.SQLQueries;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

@Repository
public class ItemRepositoryImpl extends AbstractRepository implements ItemRepository {

	private static final long serialVersionUID = -5956124129875167245L;
	private static final Logger logger = Logger.getLogger(ItemRepositoryImpl.class);

	@Override
	public long count() {
		long counter = 0;
		try (Connection connection = getConnection();
		     PreparedStatement preparedStatement =
				     connection.prepareStatement(SQLQueries.Items.SELECT_ALL_ITEMS)) {
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
			Connection connection = getConnection();
			try (PreparedStatement preparedStatement =
					     connection.prepareStatement(SQLQueries.Items.DELETE_ITEM_BY_ID)) {
				preparedStatement.setLong(1, id);
				preparedStatement.execute();
				connection.commit();
				close(connection);
				deleteImages(id);
				return true;
			} catch (SQLException e) {
				rollback(connection);
				logger.error(e.getMessage(), e);
				throw new DataAccessException(e.getMessage(), e);
			}
		} else {
			logger.error("There is no such item in database.");
			throw new NoSuchElementException("There is no such item in database.");
		}
	}

	@Override
	public boolean existsById(Long id) {
		boolean res = false;
		try(Connection connection = getConnection();
		    PreparedStatement preparedStatement =
				    connection.prepareStatement(SQLQueries.Items.SELECT_ITEM_BY_ID)) {
			preparedStatement.setLong(1, id);
			preparedStatement.execute();
			ResultSet resultSet = preparedStatement.getResultSet();
			if (resultSet.next()) {
				res = true;
			}
			close(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataAccessException(e.getMessage(), e);
		}
		return res;
	}

	@Override
	public Item findById(Long id) {
		try (Connection connection = getConnection();
		     PreparedStatement preparedStatement =
				     connection.prepareStatement(SQLQueries.Items.SELECT_ITEM_BY_ID)) {
			preparedStatement.setLong(1, id);
			preparedStatement.execute();
			ResultSet resultSet = preparedStatement.getResultSet();
			Item item = null;
			if (resultSet.next()) {
				item = new Item();
				item.setId(resultSet.getLong("id"));
				item.setCategory(resultSet.getString("category"));
				item.setName(resultSet.getString("name"));
				item.setSize(resultSet.getString("size"));
				item.setWeight(resultSet.getInt("weight"));
				item.setColor(resultSet.getString("color"));
				item.setPrice(resultSet.getBigDecimal("price"));
				item.setDate(resultSet.getDate("date_of_addition"));
				setImagesForItem(item);
			}
			close(resultSet);
			return item;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	/**
	 * Extracts all items from the database.
	 * @return items
	 */
	@Override
	public List<Item> findAll() {
		try (Connection connection = getConnection();
		     PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.Items.SELECT_ALL_ITEMS)) {
			preparedStatement.execute();
			ResultSet resultSet = preparedStatement.getResultSet();
			List<Item> items = new ArrayList<>();
			while (resultSet.next()) {
				Item item = new Item();
				item.setId(resultSet.getLong("id"));
				item.setCategory(resultSet.getString("category"));
				item.setName(resultSet.getString("name"));
				item.setSize(resultSet.getString("size"));
				item.setWeight(resultSet.getInt("weight"));
				item.setColor(resultSet.getString("color"));
				item.setPrice(resultSet.getBigDecimal("price"));
				item.setDate(resultSet.getDate("date_of_addition"));
				setImagesForItem(item);
				items.add(item);
			}
			close(resultSet);
			return items;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	@Override
	public Item update(Item entity) {
		Connection connection = getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.Items.UPDATE_ITEM)) {
			int k = 1;
			preparedStatement.setString(k++, entity.getCategory());
			preparedStatement.setString(k++, entity.getName());
			preparedStatement.setString(k++, entity.getSize());
			preparedStatement.setInt(k++, entity.getWeight());
			preparedStatement.setString(k++, entity.getColor().toLowerCase());
			preparedStatement.setBigDecimal(k++, entity.getPrice());
			preparedStatement.setDate(k++, entity.getDate());
			preparedStatement.setLong(k, entity.getId());
			insertImagesOfItemIntoDB(entity);
			preparedStatement.execute();
			connection.commit();
		} catch (SQLException e) {
			rollback(connection);
			logger.error(e.getMessage(), e);
			throw new DataAccessException(e.getMessage(), e);
		}
		return entity;
	}

	@Override
	public Item create(Item entity) {
		Connection connection = getConnection();
		try (PreparedStatement preparedStatement =
				     connection.prepareStatement(SQLQueries.Items.INSERT_ITEM)) {
			int k = 1;
			preparedStatement.setString(k++, entity.getCategory());
			preparedStatement.setString(k++, entity.getName());
			preparedStatement.setString(k++, entity.getSize());
			preparedStatement.setInt(k++, entity.getWeight());
			preparedStatement.setString(k++, entity.getColor().toLowerCase());
			preparedStatement.setBigDecimal(k++, entity.getPrice());
			preparedStatement.setDate(k, entity.getDate());
			preparedStatement.execute();
			connection.commit();
		} catch (SQLException e) {
			rollback(connection);
			logger.error(e.getMessage(), e);
			throw new DataAccessException(e.getMessage(), e);
		}
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.Items.SELECT_ITEM_BY_NAME)) {
			preparedStatement.setString(1, entity.getName());
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				entity.setId(resultSet.getLong("id"));
			}
			insertImagesOfItemIntoDB(entity);
			close(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataAccessException(e.getMessage(), e);
		}
		return entity;
	}

	@Override
	public List<Item> saveAll(List<Item> entities) {
		List<Item> list = new ArrayList<>();
		for (Item item : entities) {
			list.add(update(item));
		}
		return list;
	}

	@Override
	public List<String> getAllCategories() {
		List<String> categories = new ArrayList<>();
		try (Connection connection = getConnection();
		     PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.Items.SELECT_CATEGORIES)) {
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String s = resultSet.getString("category").toLowerCase(); //laptop
				char c = s.charAt(0); //l
				String c1 = Character.toString(c).toUpperCase(Locale.getDefault()); //L
				s = s.substring(1);
				categories.add(c1 + s);
			}
			close(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataAccessException(e.getMessage(), e);
		}
		return categories;
	}

	@Override
	public List<String> getAllColors() {
		List<String> colors = new ArrayList<>();
		try (Connection connection = getConnection();
		     PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.Items.SELECT_COLORS)) {
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String s = resultSet.getString("color").toLowerCase(); //blue
				char c = s.charAt(0); //b
				String c1 = Character.toString(c).toUpperCase(); //B
				s = s.substring(1);
				colors.add(c1 + s);
			}
			close(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataAccessException(e.getMessage(), e);
		}
		return colors;
	}

	@Override
	public List<Item> findAllByCategory(String category) {
		try (Connection connection = getConnection();
		     PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.Items.SELECT_ALL_ITEMS_BY_CATEGORY)) {
			preparedStatement.setString(1, category);
			preparedStatement.execute();
			ResultSet resultSet = preparedStatement.getResultSet();
			List<Item> items = new ArrayList<>();
			while (resultSet.next()) {
				Item item = new Item();
				item.setId(resultSet.getLong("id"));
				item.setCategory(resultSet.getString("category"));
				item.setName(resultSet.getString("name"));
				item.setSize(resultSet.getString("size"));
				item.setWeight(resultSet.getInt("weight"));
				item.setColor(resultSet.getString("color"));
				item.setPrice(resultSet.getBigDecimal("price"));
				item.setDate(resultSet.getDate("date_of_addition"));
				setImagesForItem(item);
				items.add(item);
			}
			close(resultSet);
			return items;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	private void setImagesForItem(Item item) {
		try (Connection connection = getConnection();
		     PreparedStatement preparedStatement =
				     connection.prepareStatement(SQLQueries.Items.SELECT_IMAGES_OF_ITEM)) {
			preparedStatement.setLong(1, item.getId());
			preparedStatement.execute();
			ResultSet resultSet = preparedStatement.getResultSet();
			List<String> urls = new ArrayList<>();
			while (resultSet.next()) {
				urls.add(resultSet.getString("path"));
			}
			item.setImageURLs(urls);
			close(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	private void insertImagesOfItemIntoDB(Item item) {
		Connection connection = getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.Items.INSERT_IMAGES)) {
			if (item.getId() != null) {
				deleteImages(item.getId());
			}
			for (Iterator<String> iterator = item.getImageURLs().iterator(); iterator.hasNext(); ) {
				String url = iterator.next();
				if (url != null) {
					int k = 1;
					preparedStatement.setLong(k++, item.getId());
					preparedStatement.setString(k, url);
					preparedStatement.execute();
					connection.commit();
					close(connection);
				} else {
					iterator.remove();
				}
			}
		} catch (SQLException e) {
			rollback(connection);
			logger.error(e.getMessage(), e);
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	private void deleteImages(Long id) {
		Connection connection = getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.Items.DELETE_IMAGES)) {
			preparedStatement.setLong(1, id);
			preparedStatement.execute();
			connection.commit();
			connection.close();
		} catch (SQLException e) {
			rollback(connection);
			logger.error(e.getMessage(), e);
			throw new DataAccessException(e.getMessage(), e);
		}
	}
}
