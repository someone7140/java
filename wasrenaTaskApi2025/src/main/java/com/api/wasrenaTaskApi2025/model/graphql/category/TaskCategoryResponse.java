package com.api.wasrenaTaskApi2025.model.graphql.category;

import java.util.Optional;

public record TaskCategoryResponse(
        String id,
        String name,
        Optional<Integer> displayOrder
) {
}
