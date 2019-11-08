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
			try (Connection connection = getConnection();
			     PreparedStatement preparedStatement =
					     connection.prepareStatement(SQLQueries.Items.DELETE_ITEM_BY_ID)) {
				preparedStatement.setLong(1, id);
				preparedStatement.execute();
				return true;
			} catch (SQLException e) {
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
		try (Connection connection = getConnection();
		     PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.Items.UPDATE_ITEM)) {
			int k = 1;
			preparedStatement.setString(k++, entity.getCategory());
			preparedStatement.setString(k++, entity.getName());
			preparedStatement.setString(k++, entity.getSize());
			preparedStatement.setInt(k++, entity.getWeight());
			preparedStatement.setString(k++, entity.getColor());
			preparedStatement.setBigDecimal(k++, entity.getPrice());
			preparedStatement.setDate(k++, entity.getDate());
			preparedStatement.setLong(k, entity.getId());
			insertImagesOfItemIntoDB(entity);
			preparedStatement.execute();
			connection.commit();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataAccessException(e.getMessage(), e);
		}
		return entity;
	}

	@Override
	public Item create(Item entity) {
		try (Connection connection = getConnection();
		     PreparedStatement preparedStatement =
				     connection.prepareStatement(SQLQueries.Items.INSERT_ITEM)) {
			int k = 1;
			preparedStatement.setString(k++, entity.getCategory());
			preparedStatement.setString(k++, entity.getName());
			preparedStatement.setString(k++, entity.getSize());
			preparedStatement.setInt(k++, entity.getWeight());
			preparedStatement.setString(k++, entity.getColor());
			preparedStatement.setBigDecimal(k++, entity.getPrice());
			preparedStatement.setDate(k, entity.getDate());
			insertImagesOfItemIntoDB(entity);
			preparedStatement.execute();
			connection.commit();
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
		     Statement statement = connection.createStatement()) {
			statement.executeQuery(SQLQueries.Items.SELECT_CATEGORIES);
			ResultSet resultSet = statement.getResultSet();
			while (resultSet.next()) {
				String s = resultSet.getString("category"); //laptop
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

	@Override
	public List<Item> findAllByColorFromOneCategory(String category, String color) {
		List<Item> items = findAllByCategory(category);
		List<Item> itemList = new ArrayList<>();
		for (Item item : items) {
			if (item.getColor().equals(color)) {
				itemList.add(item);
			}
		}
		return itemList;
	}

	@Override
	public List<Item> findAllByPriceRangeFromOneCategory(String category, BigDecimal left, BigDecimal right) {
		List<Item> items = findAllByCategory(category);
		List<Item> itemList = new ArrayList<>();
		for (Item item : items) {
			if (item.getPrice().compareTo(right) <= 0 && item.getPrice().compareTo(left) >= 0) {
				itemList.add(item);
			}
		}
		return itemList;
	}

	@Override
	public List<Item> findAllByWeightFromOneCategory(String category, int weight) {
		List<Item> items = findAllByCategory(category);
		List<Item> itemList = new ArrayList<>();
		for (Item item : items) {
			if (item.getWeight() < weight) {
				itemList.add(item);
			}
		}
		return itemList;
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
		try (Connection connection = getConnection();
		     PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.Items.INSERT_IMAGES)) {
			deleteImages(item);
			for (Iterator<String> iterator = item.getImageURLs().iterator(); iterator.hasNext(); ) {
				String url = iterator.next();
				if (url != null) {
					int k = 1;
					preparedStatement.setLong(k++, item.getId());
					preparedStatement.setString(k, url);
					preparedStatement.execute();
					connection.commit();
				} else {
					iterator.remove();
				}
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	private void deleteImages(Item item) {
		try (Connection connection = getConnection();
		     PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.Items.DELETE_IMAGES)) {
			preparedStatement.setLong(1, item.getId());
			preparedStatement.execute();
			connection.commit();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataAccessException(e.getMessage(), e);
		}
	}
}
