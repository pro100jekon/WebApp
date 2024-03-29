package com.epam.smyrnov.orders.service.impl;

import com.epam.smyrnov.orders.client.ItemsClient;
import com.epam.smyrnov.orders.client.UsersClient;
import com.epam.smyrnov.orders.mapper.OrderMapper;
import com.epam.smyrnov.orders.model.ItemSummary;
import com.epam.smyrnov.orders.model.Order;
import com.epam.smyrnov.orders.model.OrderedItem;
import com.epam.smyrnov.orders.model.dto.request.OrderRequest;
import com.epam.smyrnov.orders.model.dto.response.OrderResponse;
import com.epam.smyrnov.orders.model.kafka.Item;
import com.epam.smyrnov.orders.repository.ItemSummaryRepository;
import com.epam.smyrnov.orders.repository.OrdersRepository;
import com.epam.smyrnov.orders.service.OrdersService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.lang.Nullable;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository<? extends Order> repository;
    private final ItemSummaryRepository<? extends ItemSummary> itemSummaryRepository;
    private final UsersClient usersClient;
    private final ItemsClient itemsClient;
    private final OrderMapper mapper;

    @Override
    public List<OrderResponse> getAll(@Nullable Integer page) {
        List<? extends Order> orders;
        if (page == null) {
            orders = repository.findAll();
        } else {
            orders = repository.findAll(page - 1);
        }
        orders = orders.stream()
                .map(this::fetchDataIfNecessary)
                .collect(Collectors.toList());
        return mapper.mapToResponse(orders);
    }

    @Override
    public OrderResponse get(Long id) {
        val order = fetchDataIfNecessary(repository.findById(id));
        return mapper.mapToResponse(order);
    }

    @Override
    public OrderResponse add(OrderRequest order) {
        return mapper.mapToResponse(
                repository.add(
                        mapper.mapToEntity(order)));
    }

    @Override
    public OrderResponse update(Long id, OrderRequest order) {
        return mapper.mapToResponse(
                repository.update(id, mapper.mapToEntity(order)));
    }

    @KafkaListener(id = "orders-listener", topics = "items-db-test")
    public void listen(@Payload Item updatedItemInfo) {
        itemSummaryRepository.update(
                Long.parseLong(updatedItemInfo.getId()),
                mapper.mapKafkaObject(updatedItemInfo));
        // todo logger
        System.out.println("Updated item summary" + updatedItemInfo);
    }

    private Order fetchDataIfNecessary(Order order) {
        val userSummary = order.getUserSummary();
        if ((userSummary.getFirstName() == null && userSummary.getLastName() == null)
                || order.getOrderedItems().stream()
                .map(OrderedItem::getItemSummary)
                .map(ItemSummary::getName)
                .allMatch(Objects::isNull)) {

            return fetchDataFromOtherServices(order);
        }
        return order;
    }

    private Order fetchDataFromOtherServices(Order order) {
        val user = mapper.mapUserSummary(usersClient.getUser(order.getUserId()));
        order.setUserSummary(user);
        val items = mapper.mapItemSummary(itemsClient.getItems());
        order.setOrderedItems(
                order.getOrderedItems().stream()
                        .map(orderedItem -> {
                            orderedItem.setItemSummary(items.stream().filter(
                                    summary -> summary.getId().equals(orderedItem.getItemId())).findFirst().orElse(null));
                            return orderedItem;
                        }).collect(Collectors.toList()));
        return repository.update(order.getId(), order);
    }
}
