package com.epam.smyrnov.repository.impl;

import com.epam.smyrnov.annotation.Repository;
import com.epam.smyrnov.constants.SQLQueries;
import com.epam.smyrnov.entity.Item;
import com.epam.smyrnov.entity.order.DeliveryType;
import com.epam.smyrnov.entity.order.Order;
import com.epam.smyrnov.entity.order.PaymentType;
import com.epam.smyrnov.exception.DataAccessException;
import com.epam.smyrnov.repository.ItemRepository;
import com.epam.smyrnov.repository.OrderRepository;
import com.epam.smyrnov.entity.order.Status;
import com.epam.smyrnov.repository.UserRepository;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderRepositoryImpl extends AbstractRepository implements OrderRepository {

	private static final Logger logger = Logger.getLogger(ItemRepositoryImpl.class);

	@Override
	public long count() {
		long counter = 0;
		try (Connection connection = getConnection();
		     PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.Orders.COUNT_ALL_ORDERS)) {
			preparedStatement.execute();
			ResultSet resultSet = preparedStatement.getResultSet();
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
	public boolean existsById(Long id) {
		try (Connection connection = getConnection();
		     PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.Orders.SELECT_ORDER_BY_ID)) {
			preparedStatement.setLong(1, id);
			preparedStatement.execute();
			ResultSet resultSet = preparedStatement.getResultSet();
			boolean res = resultSet.next();
			close(resultSet);
			return res;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	@Override
	public boolean delete(Long id) {
		throw new DataAccessException("Deletion of orders is restricted");
	}

	@Override
	public Order findById(Long id) {
		try (Connection connection = getConnection();
		     PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.Orders.SELECT_ORDER_BY_ID)) {
			preparedStatement.setLong(1, id);
			preparedStatement.execute();
			ResultSet resultSet = preparedStatement.getResultSet();
			UserRepository userRepository = new UserRepositoryImpl();
			ItemRepository itemRepository = new ItemRepositoryImpl();
			Map<Item, Integer> itemIntegerMap = new LinkedHashMap<>();
			Order order = null;
			if (resultSet.next()) {
				order = new Order();
				order.setId(resultSet.getLong("id"));
				order.setUser(userRepository.findById(resultSet.getLong("user_id")));
				order.setDeliveryType(DeliveryType.values()[resultSet.getInt("delivery_type")]);
				order.setPaymentType(PaymentType.values()[resultSet.getInt("payment_type")]);
				order.setStatus(Status.values()[resultSet.getInt("status_id")]);
				order.setItemsAndQuantities(itemIntegerMap);
				itemIntegerMap.put(itemRepository.findById(resultSet.getLong("item_id")),
						resultSet.getInt("quantity"));
			}
			while (resultSet.next()) {
				itemIntegerMap.put(itemRepository.findById(resultSet.getLong("item_id")),
						resultSet.getInt("quantity"));
			}
			close(resultSet);
			return order;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	@Override
	public List<Order> findAll() {
		try (Connection connection = getConnection();
		     PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.Orders.SELECT_ALL_ORDERS)) {
			preparedStatement.execute();
			ResultSet resultSet = preparedStatement.getResultSet();
			List<Order> orders = new ArrayList<>();
			Map<Item, Integer> itemIntegerMap = new LinkedHashMap<>();
			Long lastId = -1L;
			Order order = null;
			ItemRepository itemRepository = new ItemRepositoryImpl();
			UserRepository userRepository = new UserRepositoryImpl();
			while (resultSet.next()) {
				if (resultSet.getLong("id") != lastId) {
					if (order != null) {
						orders.add(order);
						order = null;
						itemIntegerMap = new LinkedHashMap<>();
					}
					order = new Order();
					order.setId(resultSet.getLong("id"));
					lastId = order.getId();
					order.setUser(userRepository.findById(resultSet.getLong("user_id")));
					order.setDeliveryType(DeliveryType.values()[resultSet.getInt("delivery_type")]);
					order.setPaymentType(PaymentType.values()[resultSet.getInt("payment_type")]);
					order.setStatus(Status.values()[resultSet.getInt("status_id")]);
					order.setItemsAndQuantities(itemIntegerMap);
				}
				itemIntegerMap.put(itemRepository.findById(resultSet.getLong("item_id")),
						resultSet.getInt("quantity"));
			}
			if (order != null) {
				orders.add(order);
			}
			return orders;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	@Override
	public Order update(Order entity) {
		Connection connection = getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.Orders.DELETE_ORDER_BY_ID)) {
			preparedStatement.setLong(1, entity.getId());
			preparedStatement.execute();
			connection.commit();
			return create(entity);
		} catch (SQLException e) {
			rollback(connection);
			logger.error(e.getMessage(), e);
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	@Override
	public Order create(Order entity) {
		Connection connection = getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.Orders.INSERT_ORDER)) {
			preparedStatement.setLong(1, entity.getId());
			preparedStatement.setLong(4, entity.getUser().getId());
			preparedStatement.setInt(5, entity.getDeliveryType().ordinal());
			preparedStatement.setInt(6, entity.getPaymentType().ordinal());
			preparedStatement.setInt(7, entity.getStatus().ordinal());
			for (Map.Entry<Item, Integer> entry : entity.getItemsAndQuantities().entrySet()) {
				preparedStatement.setLong(2, entry.getKey().getId());
				preparedStatement.setInt(3, entry.getValue());
				preparedStatement.execute();
				connection.commit();
			}
			return entity;
		} catch (SQLException e) {
			rollback(connection);
			logger.error(e.getMessage(), e);
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	@Override
	public List<Order> saveAll(List<Order> entities) {
		List<Order> list = new ArrayList<>();
		for (Order entity : entities) {
			list.add(update(entity));
		}
		return list;
	}

	@Override
	public List<Order> findAllByUserId(Long id) {
		try (Connection connection = getConnection();
		     PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.Orders.SELECT_ORDERS_BY_USER_ID)) {
			preparedStatement.setLong(1, id);
			preparedStatement.execute();
			ResultSet resultSet = preparedStatement.getResultSet();
			List<Order> orders = new ArrayList<>();
			Map<Item, Integer> itemIntegerMap = new LinkedHashMap<>();
			Long lastId = -1L;
			Order order = null;
			ItemRepository itemRepository = new ItemRepositoryImpl();
			UserRepository userRepository = new UserRepositoryImpl();
			while (resultSet.next()) {
				if (resultSet.getLong("id") != lastId) {
					if (order != null) {
						orders.add(order);
						itemIntegerMap = new LinkedHashMap<>();
					}
					order = new Order();
					order.setId(resultSet.getLong("id"));
					lastId = order.getId();
					order.setUser(userRepository.findById(id));
					order.setDeliveryType(DeliveryType.values()[resultSet.getInt("delivery_type")]);
					order.setPaymentType(PaymentType.values()[resultSet.getInt("payment_type")]);
					order.setStatus(Status.values()[resultSet.getInt("status_id")]);
					order.setItemsAndQuantities(itemIntegerMap);
				}
				itemIntegerMap.put(itemRepository.findById(resultSet.getLong("item_id")),
						resultSet.getInt("quantity"));
			}
			if (order != null) {
				orders.add(order);
			}
			return orders;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	public static void main(String[] args) {
		OrderRepository orderRepository = new OrderRepositoryImpl();
		Order order = orderRepository.findById(1L);
		order.setStatus(Status.CONFIRMED);
		System.out.println(orderRepository.update(order));
	}
}
