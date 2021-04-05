package com.ddd.sampleDomain;

import com.ddd.annotation.DddOutputClass;
import com.ddd.annotation.DddOutputMethod;
import com.ddd.sampleDomain.delivery.Delivery;
import com.ddd.sampleDomain.item.Item;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@DddOutputClass
public class Customer {
    private String id;
    private String name;
    private List<Order> orders;
    private int age;

    @DddOutputMethod(comment = "注文への商品追加")
    public void addItemToOrder(String orderId, Item addItem) {
        Optional<Order> orderOpt = orders.stream().filter(o -> orderId.equals(o.getId())).findAny();
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            Item[] items = addItem.addItem(Arrays.asList(order.getItems().clone())).toArray(Item[]::new);
            order.setItems(items);
        }
    }

    @DddOutputMethod
    public Delivery derlveryRequest(String deliveryAddress, LocalDateTime deliveryDate, String orderId, Delivery delivery) {
        Optional<Order> orderOpt = orders.stream().filter(o -> orderId.equals(o.getId())).findAny();
        if (orderOpt.isPresent()) {
            delivery.deliveryRegister(deliveryAddress, deliveryDate, orderOpt.get());
            return delivery;
        } else {
            return null;
        }
    }

    @DddOutputMethod
    private void deleteOrder(List<String> deleteOrderIds) {
        this.orders = this.orders.stream().filter(o -> !deleteOrderIds.contains(o)).collect(Collectors.toList());
    }

}
