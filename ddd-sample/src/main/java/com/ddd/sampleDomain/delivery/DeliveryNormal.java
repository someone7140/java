package com.ddd.sampleDomain.delivery;

import com.ddd.annotation.DddOutputClass;
import com.ddd.annotation.DddOutputMethod;
import com.ddd.sampleDomain.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
@Setter
@DddOutputClass(name = "通常配送", comment = "通常配送を登録するクラス")
public class DeliveryNormal implements Delivery {
    private String id;
    private String deliveryAddress;
    private LocalDateTime deliveryDate;
    private Order order;

    @Override
    @DddOutputMethod(comment = "配送の登録", override = true)
    public void deliveryRegister(String deliveryAddress, LocalDateTime deliveryDate, Order order) {
        this.deliveryAddress = deliveryAddress;
        this.deliveryDate = deliveryDate;
        this.order = order;
    }

}
