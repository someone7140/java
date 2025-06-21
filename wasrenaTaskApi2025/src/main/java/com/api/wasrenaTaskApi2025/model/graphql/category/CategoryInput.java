package com.api.wasrenaTaskApi2025.model.graphql.category;

import java.util.Optional;

public record CategoryInput(
        String name,
        Optional<Integer> displayOrder
) {
}
