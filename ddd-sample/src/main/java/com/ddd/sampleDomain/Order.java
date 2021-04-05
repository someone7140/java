package com.ddd.sampleDomain;

import com.ddd.annotation.DddOutputClass;
import com.ddd.annotation.DddOutputMethod;
import com.ddd.sampleDomain.item.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
@DddOutputClass(name="注文",comment = "配下に商品を持つ注文情報")
public class Order {
    private String id;
    private Item[] items;

    @DddOutputMethod
    public int getTotalPrice() {
        return Arrays.stream(items).mapToInt(i -> i.getPrice()).sum();
    }

    @DddOutputMethod
    public List<Item> getFilteringItems(String name) {
        return Arrays.stream(items).filter(i -> i.getName().contains(name)).collect(Collectors.toList());
    }

    private void test() {
        System.out.println("test");
    }
}
