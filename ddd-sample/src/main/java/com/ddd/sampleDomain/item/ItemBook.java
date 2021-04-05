package com.ddd.sampleDomain.item;

import com.ddd.annotation.DddOutputClass;
import com.ddd.annotation.DddOutputMethod;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@DddOutputClass(name="商品（書籍）")
public class ItemBook extends Item {
    private String isdnCode;
    private String[] authors;
    private int page;

    @DddOutputMethod(comment="該当のページ数から+-30ページ以内ならそのカテゴリーとみなす")
    public boolean isBookPageCategory(int targetPage) {
        return targetPage - 30 <= page || page <= targetPage + 30;
    }
}
