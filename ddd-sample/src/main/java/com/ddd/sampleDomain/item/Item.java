package com.ddd.sampleDomain.item;

import com.ddd.annotation.DddOutputClass;
import com.ddd.annotation.DddOutputMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@DddOutputClass
public class Item {
    private String id;
    private String name;
    private int price;
    private int stock;

    @DddOutputMethod
    public List<Item> addItem(List<Item> items) {
        if (stock > 0) {
            stock--;
            return Stream.concat(items.stream(), Arrays.asList(this).stream())
                    .collect(Collectors.toList());
        }
        return items;
    }
}
