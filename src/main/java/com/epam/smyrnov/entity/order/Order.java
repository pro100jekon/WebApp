package com.epam.smyrnov.entity.order;

import com.epam.smyrnov.entity.Entity;
import com.epam.smyrnov.entity.Item;
import com.epam.smyrnov.entity.user.User;

import java.util.Map;
import java.util.Objects;

public class Order extends Entity {

    private static final long serialVersionUID = 0L;
    private User user;
    private Map<Item, Integer> itemsAndQuantities;
    private DeliveryType deliveryType;
    private PaymentType paymentType;
    private Status status;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<Item, Integer> getItemsAndQuantities() {
        return itemsAndQuantities;
    }

    public void setItemsAndQuantities(Map<Item, Integer> itemsAndQuantities) {
        this.itemsAndQuantities = itemsAndQuantities;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return user.equals(order.user) &&
                itemsAndQuantities.equals(order.itemsAndQuantities) &&
                deliveryType.equals(order.deliveryType) &&
                paymentType.equals(order.paymentType) &&
                status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user.hashCode(), itemsAndQuantities, deliveryType, paymentType, status);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Order id: ").append(id).append("<br>");
        stringBuilder.append("User: ").append(user.getFirstName()).append(" ").append(user.getLastName()).append("<br>");
        stringBuilder.append("Email: ").append(user.getEmail()).append("<br>");
        stringBuilder.append("Payment type: ").append(paymentType.value()).append("<br>");
        stringBuilder.append("Delivery type: ").append(deliveryType.value()).append("<br>");
        stringBuilder.append("Status: ").append(status.value()).append("<br>");
        stringBuilder.append("Items:").append("<br>");
        for (Map.Entry<Item, Integer> entry : itemsAndQuantities.entrySet()) {
            stringBuilder.append(entry.getKey().getName()).append(" -> ").append(entry.getValue()).append("<br>");
        }
        return stringBuilder.toString();
    }
}
