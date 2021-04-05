package com.ddd.sampleDomain.delivery;

import com.ddd.annotation.DddOutputClass;
import com.ddd.annotation.DddOutputMethod;
import com.ddd.sampleDomain.Order;

import java.time.LocalDateTime;

@DddOutputClass
public interface Delivery {
    @DddOutputMethod
    public void deliveryRegister(String deliveryAddress, LocalDateTime deliveryDate, Order order);
}
